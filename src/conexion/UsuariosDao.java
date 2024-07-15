package conexion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuariosDao {

    public boolean validarUsuario(String username, String password) {
        Connection con = ConexionBd.establecerConexion();
        if (con != null) {
            String query = "SELECT * FROM Usuarios WHERE username = ? AND password = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                System.out.println(e.toString());
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
        }
        return false;
    }
}
