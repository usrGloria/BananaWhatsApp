package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.modelos.Mensaje;
import com.banana.bananawhatsapp.modelos.Usuario;
import com.banana.bananawhatsapp.exceptions.UsuarioException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.sql.*;

@Setter
@Getter
public class MensajeJDBCRepository implements IMensajeRepository {

    @Value("${db_url}")
    private String connUrl;

    @Override
    public Mensaje crear(Mensaje mensaje) throws SQLException {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(connUrl);
            conn.setAutoCommit(false);

            mensaje.valido();

            // Insertar Mensaje
            String sql = "INSERT INTO Mensaje VALUES(null,?,?,?,?)";
            // ID, Cuerpo, Fecha, From, To
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, mensaje.getCuerpo());
            pstm.setDate(2, Date.valueOf(mensaje.getFecha()));
            pstm.setInt(3, mensaje.getRemitente().getId());
            pstm.setInt(4, mensaje.getDestinatario().getId());

            int rows = pstm.executeUpdate();

            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                mensaje.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("No ID Asignado");
            }
            pstm.close();

            System.out.println("Nuevo Mensaje:" + mensaje);
            System.out.println("Transaccion exitosa!!");
            conn.commit();

        } catch (UsuarioException e) {
            e.printStackTrace();
            throw new UsuarioException("Mensaje No Valido");
        } catch (Exception e) {
            System.out.println("Transaccion rollback!!");
            conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }

        return mensaje;
    }

    @Override
    public List<Mensaje> obtener(Usuario usuario) throws SQLException {

        return null;
    }

    @Override
    public boolean borrarTodos(Usuario usuario) throws SQLException {

        return true;
    }

}
