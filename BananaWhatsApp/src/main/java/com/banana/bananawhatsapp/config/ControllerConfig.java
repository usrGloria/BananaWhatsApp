package com.banana.bananawhatsapp.config;

import com.banana.bananawhatsapp.controladores.ControladorMensajes;
import com.banana.bananawhatsapp.controladores.ControladorUsuarios;
import com.banana.bananawhatsapp.servicios.IServicioUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Autowired
    IServicioUsuarios usrService;

    @Bean
    ControladorUsuarios crearControllerUsuario() {
        ControladorUsuarios controller = new ControladorUsuarios();
        controller.setServicioUsuarios(usrService);
        return controller;
    }
    @Bean
    ControladorMensajes crearControllerMensaje() {
        ControladorMensajes controllerMsg = new ControladorMensajes();
        controllerMsg.setServicioMensajeria(controllerMsg.getServicioMensajeria());
        return controllerMsg;
    }

}
