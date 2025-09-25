package bo;

import dao.UsuarioDAO;
import db.Conexion;
import entidades.Usuarios;
import java.sql.Connection;
import javax.swing.JOptionPane;
import javax.swing.JTable;

/**
 *
 * @author Jonathan Esquivel Flores
 */
public class UsuarioBO 
{
    private String mensaje = "";
    private UsuarioDAO edao = new UsuarioDAO();
    
    public String addUsuario(Usuarios usr) throws Exception
    {
        Connection conn = Conexion.getConnection();
        try
        {
            mensaje = edao.addUsrQ(conn, usr);
        } catch (Exception e)
        {
            mensaje = mensaje + " " + e.getMessage();
        }finally
        {
            try
            {
                if(conn != null) conn.close();
            } catch (Exception e)
            {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    
    public String modUsuario(Usuarios usr) throws Exception
    {
        Connection conn = Conexion.getConnection();
        try
        {
            mensaje = edao.modUsrQ(conn, usr);
        } catch (Exception e)
        {
            mensaje = mensaje + " " + e.getMessage();
        }finally
        {
            try
            {
                if(conn != null) conn.close();
            } catch (Exception e)
            {
                mensaje = mensaje + " " + e.getMessage();
            }
        }
        return mensaje;
    }
    
    public String delUsuarios(Usuarios emp) throws Exception
    {
        Connection conn = Conexion.getConnection();
        try
        {
            mensaje = edao.delUsrQ(conn, emp);
        } catch (Exception e)
        {
            mensaje = mensaje + " " + e.getMessage();
        }finally
        {
            try
            {
                if(conn != null) conn.close();
            } catch (Exception e)
            {
                mensaje = mensaje + e.getMessage();
            }
        }
        return mensaje;
    }
    
    public void listUsuarios(JTable tabla) throws Exception
    {
        Connection con = Conexion.getConnection();
        edao.listUsrQ(con, tabla);
        try
        {
            con.close();
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
