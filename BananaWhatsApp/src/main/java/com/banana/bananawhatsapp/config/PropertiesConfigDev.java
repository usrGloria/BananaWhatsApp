package com.banana.bananawhatsapp.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Profile("dev")
public class PropertiesConfigDev {

}

