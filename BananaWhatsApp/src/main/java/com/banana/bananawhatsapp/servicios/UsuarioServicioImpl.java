package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
//@Service
public class UsuarioServicioImpl implements IServicioUsuarios{
    @Autowired
    IUsuarioRepository usrRepo;
    @Override
    public Usuario crearUsuario(Usuario usuario) throws UsuarioException {
        try {
            usrRepo.crear(usuario);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException("Error al crear el usuario: "+ e.getMessage());
        }
        return usuario;
    }
    ;

    @Override
    public boolean borrarUsuario(Usuario usuario) throws UsuarioException {

        return true;
    }

    ;

    @Override
    public Usuario actualizarUsuario(Usuario usuario) throws UsuarioException {

        return usuario;
    }

    ;

    @Override
    public Usuario obtenerPosiblesDestinatarios(Usuario usuario, int max) throws UsuarioException {

        return usuario;
    }

    ;

}
