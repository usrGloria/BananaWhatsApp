package com.banana.bananawhatsapp.config;

//package com.banana.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@Import({RepoConfig.class, ServiceConfig.class, ControllerConfig.class, PropertiesConfig.class})
//@ComponentScan(basePackages = {"com.banana.bananawhatsapp.servicios"})
public class SpringConfig {
    int a=0;
    /*@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }*/
}
