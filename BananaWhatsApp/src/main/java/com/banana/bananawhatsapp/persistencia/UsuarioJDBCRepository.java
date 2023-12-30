package com.banana.bananawhatsapp.persistencia;

import com.banana.bananawhatsapp.exceptions.UsuarioException;
import com.banana.bananawhatsapp.modelos.Usuario;

import java.sql.SQLException;
import java.util.Set;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

//@Component
//@Repository
@Setter
public class UsuarioJDBCRepository implements IUsuarioRepository {
    @Value("${db_url}")
    private String connUrl;

    @Override
    public Usuario crear(Usuario usuario) throws SQLException, UsuarioException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connUrl);
            conn.setAutoCommit(false);

//            // OBTENEMOS un Usuario
//            String sql = "SELECT * FROM usuario WHERE pid = ?";
//            PreparedStatement pstm = conn.prepareStatement(sql);
//            pstm.setInt(1, usuario.getId());
//
//            ResultSet rs = pstm.executeQuery();
//            double precio = 0;
//            int existencias = 0;
//            if (rs.next()) {
//                precio = rs.getDouble("precio");
//                existencias = rs.getInt("existencias");
//            } else {
//                throw new UsuarioException();
//            }
//            pstm.close();

            // Si el usuario NO es valido, ya lanza una excepcion
            usuario.valido();
            // INSERTAR EN COMPRA
            String sql = "INSERT INTO Usuario VALUES(null,?,?,?,?)";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setBoolean(1, usuario.isActivo());
            pstm.setDate(2, Date.valueOf(usuario.getAlta()));
            pstm.setString(3, usuario.getEmail());
            pstm.setString(4, usuario.getNombre());

            int rows = pstm.executeUpdate();


            ResultSet generatedKeys = pstm.getGeneratedKeys();
            if (generatedKeys.next()) {
                usuario.setId(generatedKeys.getInt(1));
            } else {
                throw new SQLException("No ID Asignado");

            }
            pstm.close();

            System.out.println("Nuevo Usuario:" + usuario);

//            // ACTUALIZAR SALDO DE USUARIO
//            sql = "UPDATE usuario u SET u.saldo = u.saldo - ? WHERE u.uid=?";
//            pstm = conn.prepareStatement(sql);
//            pstm.setDouble(1, nuevaCompra.getCantidad() * precio);
//            pstm.setInt(2, nuevaCompra.getUsuario());
//            rows = pstm.executeUpdate();
//            pstm.close();

            System.out.println("Transaccion exitosa!!");
            conn.commit();

        } catch (UsuarioException e) {
            e.printStackTrace();
            throw new UsuarioException("Usuario No Valido");
        } catch (Exception e) {
            System.out.println("Transaccion rollback!!");
            conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }

        return usuario;
    }

    @Override
    public Usuario actualizar(Usuario usuario) throws SQLException {
        return null;
    }

    @Override
    public boolean borrar(Usuario usuario) throws SQLException {
        // Transacciones
        return false;
    }

    @Override
    public Set<Usuario> obtenerPosiblesDestinatarios(Integer id, Integer max) throws SQLException {
        return null;
    }

    @Override
    public Usuario getById(Integer id) throws SQLException {
        Usuario usuario = new Usuario();
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(connUrl);
            conn.setAutoCommit(false);

            // INSERTAR EN COMPRA
            String sql = "Select * From Usuario Where ID=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, id.intValue());

            ResultSet rows = pstm.executeQuery();
            if (rows.next()) {
                usuario.setId(rows.getInt(1));
                usuario.setActivo(rows.getBoolean(2));
                usuario.setAlta(rows.getDate(3).toLocalDate());
                usuario.setEmail(rows.getString(4));
                usuario.setNombre(rows.getString(5));
            } else {
                throw new SQLException("No Existe el usuario");
            }
            pstm.close();

            System.out.println("Nuevo Usuario:" + usuario);

            System.out.println("Transaccion exitosa!!");

        } catch (UsuarioException e) {
            e.printStackTrace();
            throw new UsuarioException("Usuario No Valido");
        } catch (Exception e) {
            System.out.println("Transaccion rollback!!");
            conn.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (conn != null) conn.close();
        }

        return usuario;

    }
}
