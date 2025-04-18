package com.example.atm_report_service.configuration;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        yamlMapper.findAndRegisterModules();

        converters.add(new MappingJackson2HttpMessageConverter(yamlMapper) {{
            setSupportedMediaTypes(List.of(
                MediaType.valueOf("application/x-yaml"),
                MediaType.valueOf("text/yaml")
            ));
        }});
    }
}
