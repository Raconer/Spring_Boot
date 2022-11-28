package com.project.basic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.basic.config.properties.YamlMessageSource;

@Configuration
public class WebConfig implements WebMvcConfigurer{
    @Bean
    public MessageSource messageSource( @Value("${spring.messages.basename}") String basename, @Value("${spring.messages.encoding}") String encoding){
        YamlMessageSource ms = new YamlMessageSource();
        ms.setBasename(basename);
        ms.setDefaultEncoding(encoding);
        return ms;
    }
}
