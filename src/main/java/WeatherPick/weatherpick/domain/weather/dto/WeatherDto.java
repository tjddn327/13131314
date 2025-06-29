package WeatherPick.weatherpick.domain.weather.dto;

public class WeatherDto {
    private String hour;
    private String temperature;
    private String pcp;
    private String sky;

    public WeatherDto(String hour, String temperature, String pcp, String sky){
        this.hour = hour;
        this.temperature = temperature;
        this.pcp = pcp;
        this.sky = sky;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPcp() {
        return pcp;
    }

    public void setPcp(String pcp) {
        this.pcp = pcp;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }
}
