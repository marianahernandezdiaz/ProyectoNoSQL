package db;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Jonathan Esquivel Flores
 * db gme varchar20
 * nomgme varchar60
 */
public class Conexion 
{
    private static Connection conn = null;
    private static String wallet = "C:\\Users\\Jonathan Flores\\OneDrive\\Documents\\ITTOL\\9 Noveno semestre\\Bases de datos No SQL\\Wallet_PROYECTO";
    private static final String alias = "proyecto_high";
    private static String url = "jdbc:oracle:thin:@"+ alias +"?TNS_ADMIN=" + wallet; //listener
    private static String login = "ADMIN";
    private static String pswd = "pw";
    
    public static Connection getConnection()
    {
        try
        {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, login, pswd);
            conn.setAutoCommit(false);
            
            if (conn != null) System.out.println("Conexion exitosa");
            else System.out.println("Conexion erronea");
            
        }catch(ClassNotFoundException | SQLException e)
        {
            JOptionPane.showMessageDialog(null, "Conexion erronea " + e.getMessage());
        }
        return conn;
    }
    
    public void desconexion()
    {
        try
        {
            conn.close();
        } catch (Exception e)
        {
            System.out.println("Error al desconectar " + e.getMessage());
        }
    }
    
    public static void main(String[] args) 
    {
        Conexion c = new Conexion();
        c.getConnection();
    }
}