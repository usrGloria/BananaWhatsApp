package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.modelos.Mensaje;
import org.junit.jupiter.api.Test;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})

class ServicioMensajeriaTest {

    @Autowired
    IServicioMensajeria servicio;

    @Autowired
    IUsuarioRepository usuarioRepo;

    @Test
    void dadoRemitenteYDestinatarioYTextoValido_cuandoEnviarMensaje_entoncesMensajeValido() throws SQLException {

        Usuario remitente = usuarioRepo.getById(7);
        Usuario destinatario = usuarioRepo.getById(12);
        Mensaje mensaje = servicio.enviarMensaje(remitente, destinatario, "Feliz Navidad!!!");

        assertThat(mensaje, notNullValue());
        assertThat(mensaje.getId(), greaterThan(0));

    }

    @Test
    void dadoRemitenteYDestinatarioYTextoNOValido_cuandoEnviarMensaje_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoMostrarChatConUsuario_entoncesListaMensajes() throws SQLException {
        Usuario remitente = usuarioRepo.getById(7);
        Usuario destinatario = usuarioRepo.getById(12);
        List <Mensaje> chat = servicio.mostrarChatConUsuario(remitente, destinatario);

        assertNotNull(chat);

    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoMostrarChatConUsuario_entoncesExcepcion() {
    }

    @Test
    void dadoRemitenteYDestinatarioValido_cuandoBorrarChatConUsuario_entoncesOK() {

    }

    @Test
    void dadoRemitenteYDestinatarioNOValido_cuandoBorrarChatConUsuario_entoncesExcepcion() {
    }
}