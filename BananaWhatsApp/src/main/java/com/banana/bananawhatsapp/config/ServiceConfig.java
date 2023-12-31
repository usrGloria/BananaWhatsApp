package com.banana.bananawhatsapp.config;

import com.banana.bananawhatsapp.servicios.IServicioMensajeria;
import com.banana.bananawhatsapp.servicios.IServicioUsuarios;
import com.banana.bananawhatsapp.servicios.MensajeServicioImpl;
import com.banana.bananawhatsapp.servicios.UsuarioServicioImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
    @Bean
    IServicioUsuarios crearServicioUsuario(){
        IServicioUsuarios service = new UsuarioServicioImpl();
        return service;
    }

    @Bean
    IServicioMensajeria crearServicioMensaje(){
        IServicioMensajeria serviceMsg = new MensajeServicioImpl();
        return serviceMsg;
    }

}
