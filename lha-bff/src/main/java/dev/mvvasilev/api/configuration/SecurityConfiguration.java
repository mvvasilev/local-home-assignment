package dev.mvvasilev.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfiguration {

    @Value("${frontend.origin}")
    private String frontendOrigin;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security, CorsConfigurationSource bffCorsConfigurationSource) throws Exception {
        return security
                .httpBasic(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(a -> a.anyRequest().anonymous())
                .cors(c -> c.configurationSource(bffCorsConfigurationSource))
                .build();
    }

    @Bean
    public CorsConfigurationSource bffCorsConfigurationSource(CorsConfiguration frontendCorsConfig) {
        var corsConfigSource = new UrlBasedCorsConfigurationSource();

        corsConfigSource.registerCorsConfiguration("/**", frontendCorsConfig);

        return corsConfigSource;
    }

    @Bean
    public CorsConfiguration frontendCorsConfig() {
        var corsConfig = new CorsConfiguration();

        corsConfig.setAllowedOrigins(List.of(frontendOrigin));

        return corsConfig;
    }

}
