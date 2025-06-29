package WeatherPick.weatherpick.domain.weather.service;


import WeatherPick.weatherpick.domain.weather.dto.WeatherDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class WeatherService {


    public List<WeatherDto> fetchWeatherData(){
        List<WeatherDto> result = new ArrayList<>();
        try {
            //오늘 날짜 가져오기
            String dateTime = getBaseDateTime();


            String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst"
                    + "?serviceKey=CQA5Q3GFH8k2apzdKNEWy1cT%2F1rt82qG534rrAZ7fJts7s8ajjS8V1PzKfNp9wbbY7snmpktgwr%2FFN0BtZPqcQ%3D%3D" // 실제 키로 교체
                    + "&pageNo=1&numOfRows=1000&dataType=JSON"
                    + "&base_date="+dateTime+"&base_time=0500"
                    + "&nx=55&ny=127";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader rd = new BufferedReader(new InputStreamReader(
                    conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300
                            ? conn.getInputStream()
                            : conn.getErrorStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }
            rd.close();
            conn.disconnect();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode items = mapper.readTree(sb.toString())
                    .path("response").path("body").path("items").path("item");

            // 시간대별 정보를 모으기 위한 임시 Map
            Map<String, Map<String, String>> timeMap = new HashMap<>();

            for (JsonNode item : items) {
                String time = item.path("fcstTime").asText();
                //if (!time.startsWith("0") || Integer.parseInt(time.substring(0, 2)) > 12) continue;

                String category = item.path("category").asText();
                String value = item.path("fcstValue").asText();

                timeMap.putIfAbsent(time, new HashMap<>());
                timeMap.get(time).put(category, value);
            }

            for (String time : new TreeSet<>(timeMap.keySet())) {
                Map<String, String> data = timeMap.get(time);
                String hour = time.substring(0, 2) + "시";
                String temperature = data.getOrDefault("TMP", "") + "℃";
                String pcp = data.getOrDefault("PCP", "강수없음").replace("강수없음", "없음");
                String sky = convertSkyCode(data.getOrDefault("SKY", ""));
                // 강수 정보가 "없음"이 아니면 "비"로 설정
                if (!pcp.equals("없음") && !pcp.equals("0")) {
                    sky = "비";
                }
                result.add(new WeatherDto(hour, temperature, pcp, sky));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    //구름량으로 맑음 흐림 판단
    private String convertSkyCode(String s_code) {
        int code = Integer.parseInt(s_code);
        if (code >= 0 && code <= 5) {
            return "맑음";
        } else if (code >= 6 && code <= 8) {
            return "구름많음";
        } else if (code >= 9 && code <= 10) {
            return "흐림";
        } else {
            return "정보없음";
        }
    }
    //오늘 날짜 가져오기
    private String getBaseDateTime() {
        LocalDateTime now = LocalDateTime.now();

        String baseDate = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));


        // 자정 이전일 경우 날짜를 하루 전으로 조정
        if (now.getHour() < 6) {
            baseDate = now.minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        }

        return baseDate;
    }



}
