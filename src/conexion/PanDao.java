package conexion;

import conexion.ConexionBd;
import entidades.Pan;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PanDao {

    public List<Pan> obtenerPanes() {
        List<Pan> listaPanes = new ArrayList<>();
        try (Connection con = ConexionBd.establecerConexion();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id_panes, nombre, cantidad_disponible, precio_unidad FROM panes_disponibles")) {

            while (rs.next()) {
                int id_panes = rs.getInt("id_panes");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad_disponible");
                double precio = rs.getDouble("precio_unidad");
                listaPanes.add(new Pan(id_panes, nombre, cantidad, precio));
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Muestra la traza del error
        }
        return listaPanes;
    }

    public boolean insertarPan(String nombre, int cantidad, double precio) {
        String sql = "INSERT INTO panes_disponibles (nombre, cantidad_disponible, precio_unidad) VALUES (?, ?, ?)";
        try (Connection con = ConexionBd.establecerConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setInt(2, cantidad);
            pst.setDouble(3, precio);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();  // Muestra la traza del error
            return false;
        }
    }

    public boolean modificarPan(int id_panes, String nombre, int cantidad, double precio) {
        String sql = "UPDATE panes_disponibles SET nombre = ?, cantidad_disponible = ?, precio_unidad = ? WHERE id_panes = ?";
        try (Connection con = ConexionBd.establecerConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, nombre);
            pst.setInt(2, cantidad);
            pst.setDouble(3, precio);
            pst.setInt(4, id_panes);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();  // Muestra la traza del error
            return false;
        }
    }

    public boolean eliminarPan(int id_panes) {
        String sql = "DELETE FROM panes_disponibles WHERE id_panes = ?";
        try (Connection con = ConexionBd.establecerConexion();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, id_panes);
            int filasAfectadas = pst.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            e.printStackTrace();  // Muestra la traza del error
            return false;
        }
    }
}
