package com.banana.bananawhatsapp.servicios;

import com.banana.bananawhatsapp.exceptions.MensajeException;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.persistencia.IMensajeRepository;
import com.banana.bananawhatsapp.persistencia.IUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.JTextComponent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MensajeServicioImpl implements IServicioMensajeria {
    @Autowired
    IMensajeRepository msgRepo;

    @Autowired
    IUsuarioRepository usrRepo;

    @Override
    public Mensaje enviarMensaje(Usuario remitente, Usuario destinatario, String texto) throws UsuarioException, MensajeException {
        Mensaje newMensaje = new Mensaje(null, remitente, destinatario, texto, LocalDate.now());
        try {
//            usrRepo.getById(remitente.getId());
//            usrRepo.getById(destinatario.getId());
            msgRepo.crear(newMensaje);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new UsuarioException("Error al crear el mensaje: " + e.getMessage());
        }

        return newMensaje;
    }


    @Override
    public List<Mensaje> mostrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {
        HashMap<Integer, Mensaje> hMensajes = new HashMap<Integer, Mensaje>();
        //List<Mensaje> chat = new ArrayList<>();

        try {
            List<Mensaje> chatRemitente = msgRepo.obtener(remitente);
            List<Mensaje> chatDestinatario = msgRepo.obtener(destinatario);

//            for (Mensaje msg : chatRemitente) {
//                if ((msg.getRemitente().getId().intValue() == remitente.getId().intValue())
//                        && (msg.getDestinatario().getId().intValue() == destinatario.getId().intValue())
//                ){
//                    chat.add(msg);
//                }
//                if((msg.getRemitente().getId().intValue() == destinatario.getId().intValue())
//                        && (msg.getDestinatario().getId().intValue() == remitente.getId().intValue())
//                ){
//                    chat.add(msg);
//                }
//            }


            for (Mensaje msg : chatRemitente) {
                if (msg.getDestinatario().getId().intValue() == destinatario.getId().intValue()) {
                    hMensajes.put(msg.getId(), msg);
                }
            }

            for (Mensaje msg : chatDestinatario) {
                if (msg.getDestinatario().getId().intValue() == remitente.getId().intValue()) {
                    hMensajes.put(msg.getId(), msg);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }



        return new ArrayList<Mensaje>(hMensajes.values());
    }

    ;

    @Override
    public boolean borrarChatConUsuario(Usuario remitente, Usuario destinatario) throws UsuarioException, MensajeException {

        return true;
    }

    ;


}
