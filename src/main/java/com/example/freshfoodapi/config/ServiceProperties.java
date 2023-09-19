package com.example.freshfoodapi.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
@Data
// @ConfigurationProperties  // auto access
public class ServiceProperties {
    @Value("${service.api.base-url}")
    private String baseUrl;
    @Value("${service.api.get-all-classrooms}")
    private String classroomListUrl;
}
