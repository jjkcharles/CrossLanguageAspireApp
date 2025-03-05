package com.jjkcharles.Common;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "api.weather")
public class WeatherConfig {
    private String url;

    
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
      }
}
