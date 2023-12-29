package com.banana.bananawhatsapp;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.time.LocalDate;

public class BananaWhatsAppApplication {

    public static void main(String[] args) {



        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
//        context.getEnvironment().setActiveProfiles("dev");
        context.register(SpringConfig.class);
        context.refresh();

//        IUsuarioRepository repositorioUsuarios = context.getBean(IUsuarioRepository.class);
//        try {
//            Usuario unUsuario = repositorioUsuarios.crear(new Usuario(null, true, "Pepe", "pepe@gmail.com", LocalDate.now()));
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }


    }

}
