package com.idle.server.global.config;


import com.idle.server.global.config.properties.GoogleAuthProperties;
import com.idle.server.global.config.properties.KakaoAuthProperties;
import com.idle.server.global.jwt.properties.JwtProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties ({JwtProperties.class, GoogleAuthProperties.class, KakaoAuthProperties.class})
public class PropertiesConfig {
}
