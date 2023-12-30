package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.config.SpringConfig;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.SQLException;
import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringConfig.class})
class MensajeRepositoryTest {

    @Autowired
    IMensajeRepository repo;
    @Autowired
    IUsuarioRepository usuarioRepo;


    @Test
    void dadoUnMensajeValido_cuandoCrear_entoncesMensajeValido() throws SQLException {

            Usuario remitente = usuarioRepo.getById(7);
            Usuario destinatario = usuarioRepo.getById(12);

            Mensaje nuevo = new Mensaje(null, remitente, destinatario, "Hola, como estas?", LocalDate.now());
            repo.crear(nuevo);

            assertThat(nuevo, notNullValue());
            assertThat(nuevo.getId(), greaterThan(0));

    }

    @Test
    void dadoUnMensajeNOValido_cuandoCrear_entoncesExcepcion() {
    }

    @Test
    void dadoUnUsuarioValido_cuandoObtener_entoncesListaMensajes() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoObtener_entoncesExcepcion() {

        assertThrows(Exception.class, () ->  {
            Usuario usuario = usuarioRepo.getById(120);
        });
    }

    @Test
    void dadoUnUsuarioValido_cuandoBorrarTodos_entoncesOK() {
    }

    @Test
    void dadoUnUsuarioNOValido_cuandoBorrarTodos_entoncesExcepcion() {
    }

}