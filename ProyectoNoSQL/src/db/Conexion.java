package db;

import java.sql.*;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;

public class Conexion 
{
    static final String DB_URL = "jdbc:oracle:thin:@proyecto_high?TNS_ADMIN=D:/JonathanFlores/DDocuments/ITTOL/NOSQL/Wallet_PROYECTO"; //poner la ubicacion de la wallet descomprimida
    static final String DB_USER = "ADMIN";
    static final String DB_PASSWORD = "VampBD220203";
    static final String CONN_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";
    
    public static void main(String[] args) throws Exception 
    {
        getConnection();
    }
    
    private static void doSQLWork(Connection conn) throws SQLException {
        String query = "SELECT table_name FROM user_tables FETCH FIRST 5 ROWS ONLY";

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            System.out.println("Tablas en tu esquema ADMIN:");
            while (rs.next()) {
                System.out.println(" - " + rs.getString(1));
            }
        }
    }
    
    public static Connection getConnection() throws Exception
    {
         // Crear PoolDataSource
        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();

        // Configuración de la fuente de datos
        pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
        pds.setURL(DB_URL);
        pds.setUser(DB_USER);
        pds.setPassword(DB_PASSWORD);
        pds.setConnectionPoolName("JDBC_UCP_POOL");

        // Parámetros de pool (ajústalos a tu necesidad)
        pds.setInitialPoolSize(3);
        pds.setMinPoolSize(3);
        pds.setMaxPoolSize(10);

        Connection conn = pds.getConnection();
        try 
        {
            System.out.println("✅ Conexión exitosa a Oracle Autonomous Database");
            doSQLWork(conn);
        } catch (SQLException e) {
            System.out.println("❌ Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}
