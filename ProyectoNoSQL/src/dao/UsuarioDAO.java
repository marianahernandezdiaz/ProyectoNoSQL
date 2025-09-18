package dao;
import entidades.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Jonathan Esquivel Flores
 */
public class UsuarioDAO 
{
    private String mensaje = "";
    
    public String addUsrQ(Connection con, Usuarios usr)
    {
        PreparedStatement pst = null;
        String sql = "INSERT INTO USUARIOS (NO_USUARIO, NOMBRE, APELLIDO_PAT, APELLIDO_MAT, TIPO_CORREO, CURP, RFC, TELEFONO, ID_DEPTO, SEXO, ESTATUS) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            pst = con.prepareStatement(sql);
            pst.setInt(1, usr.getNoUsuario());
            pst.setString(2, usr.getNombre());
            pst.setString(3, usr.getApPat());
            pst.setString(4, usr.getApMat());
            pst.setString(5, usr.getTcorreo());
            pst.setString(6, usr.getCurp());
            pst.setString(7, usr.getRfc());
            pst.setInt(8, usr.getTelefono());
            pst.setString(9, usr.getIdDepto());
            pst.setString(10, usr.getSexo());
            pst.setString(11, usr.getEstatus());
            mensaje = "Guardado exitosamente.";
            pst.execute();
            JOptionPane.showMessageDialog(null, "Guardado exitosamente.");
            pst.close();;
        } catch (SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Error al guardar. " + e.getMessage());
//            mensaje = "Error al guardar. " + e.getMessage();
        }
        return mensaje;
    }
    
    public String modUsrQ(Connection con, Usuarios usr) 
    {
        PreparedStatement pst = null;
        StringBuilder sql = new StringBuilder("UPDATE USUARIOS SET ");
        List<Object> valores = new ArrayList<>();

        if (usr.getNombre() != null) 
        {
            sql.append("NOMBRE = ?, ");
            valores.add(usr.getNombre());
        }
        if (usr.getApPat() != null) 
        {
            sql.append("APELLIDO_PAT = ?, ");
            valores.add(usr.getApPat());
        }
        if (usr.getApMat() != null) 
        {
            sql.append("APELLIDO_MAT = ?, ");
            valores.add(usr.getApMat());
        }
        if (usr.getTcorreo() != null) 
        {
            sql.append("TIPO_CORREO = ?, ");
            valores.add(usr.getTcorreo());
        }
        if (usr.getCurp() != null) 
        {
            sql.append("CURP = ?, ");
            valores.add(usr.getCurp());
        }
        if (usr.getRfc() != null) 
        {
            sql.append("RFC = ?, ");
            valores.add(usr.getRfc());
        }
        if (usr.getTelefono() != 0) 
        { 
            sql.append("TELEFONO = ?, ");
            valores.add(usr.getTelefono());
        }
        if (usr.getIdDepto() != null) 
        {
            sql.append("ID_DEPTO = ?, ");
            valores.add(usr.getIdDepto());
        }
        if (usr.getSexo() != null) 
        {
            sql.append("SEXO = ?, ");
            valores.add(usr.getSexo());
        }
        if (usr.getEstatus() != null) 
        {
            sql.append("ESTATUS = ?, ");
            valores.add(usr.getEstatus());
        }

        sql.setLength(sql.length() - 2);

        sql.append(" WHERE NO_USUARIO = ?");
        valores.add(usr.getNoUsuario());

        try 
        {
            pst = con.prepareStatement(sql.toString());

            // asignar valores en orden
            for (int i = 0; i < valores.size(); i++) 
            {
                pst.setObject(i + 1, valores.get(i));
            }

            pst.executeUpdate();
            pst.close();
            mensaje = "Actualizado exitosamente.";
            JOptionPane.showMessageDialog(null, mensaje);

        } catch (SQLException e) 
        {
            mensaje = "Error al actualizar. " + e.getMessage();
            JOptionPane.showMessageDialog(null, mensaje);
        }

        return mensaje;
    }

    
    public String delUsrQ(Connection con, Usuarios usr)
    {
        PreparedStatement pst = null;
        String sql = "DELETE USUARIOS "
                + "WHERE NO_USUARIO = ?";
        try
        {
            pst = con.prepareStatement(sql);
            pst.setInt(1, usr.getNoUsuario());
            mensaje = "Eliminado exitosamente.";
            pst.execute();
            JOptionPane.showMessageDialog(null, "Eliminado exitosamente.");
            pst.close();;
            
        } catch (SQLException e)
        {
            mensaje = "Error al eliminar. " + e.getMessage();
            JOptionPane.showMessageDialog(null, "Error al eliminar. " + e.getMessage());
        }
        return mensaje;
    }
    
    public void listUsrQ(Connection con, JTable tabla)
    {
        DefaultTableModel model;
        String [] columnas = {"NO_USUARIO", "NOMBRE", "APELLIDO PATERNO", "APELLIDO MATERNO", "T_CORREO", "CURP", "RFC", "TELEFONO", "ID_DEPTO", "SEXO", "ESTATUS"};
        model = new DefaultTableModel(null, columnas);
        
        String sql = "SELECT * FROM USUARIOS";
        
        String [] filas = new String[11];
        Statement st = null;
        ResultSet rs = null;
        
        try
        {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            
            while(rs.next())
            {
                for (int i = 0; i < columnas.length; i++)
                {
                    filas[i] = rs.getString(i + 1);
                }
                model.addRow(filas);
            }
            
            tabla.setModel(model);
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "No se puede listar la tabla.");
        }
    }
}