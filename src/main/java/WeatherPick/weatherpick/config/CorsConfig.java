package WeatherPick.weatherpick.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        
        // 프론트엔드 도메인 허용

        config.addAllowedOrigin("https://localhost");
        config.addAllowedOrigin("http://13.209.68.253:3000");
        config.addAllowedOrigin("https://13.209.68.253");
        config.addAllowedOrigin("http://localhost:3000");
        
        // 모든 HTTP 메서드 허용
        config.addAllowedMethod("*");
        
        // 모든 헤더 허용
        config.addAllowedHeader("*");
        
        // Credentials 허용
        config.setAllowCredentials(true);
        
        // preflight 요청 캐시 시간 설정
        config.setMaxAge(3600L);
        
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
