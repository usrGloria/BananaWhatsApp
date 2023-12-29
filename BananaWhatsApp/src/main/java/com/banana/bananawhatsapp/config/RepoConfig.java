package com.banana.bananawhatsapp.config;

import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import com.banana.bananawhatsapp.persistencia.UsuarioJDBCRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
//@Profile("Default")
//@Import({UsuarioJDBCRepository.class, PropertiesConfig.class});
public class RepoConfig {
    @Bean
    IUsuarioRepository crearUsuarioRepo(){
        UsuarioJDBCRepository repo = new UsuarioJDBCRepository();
        return repo;
    }

}

