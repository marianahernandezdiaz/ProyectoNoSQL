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
    static final String DB_URL = "jdbc:oracle:thin:@proyecto_high?TNS_ADMIN=C:/universidad/9np/NoSQL/Wallet_PROYECTO"; //poner la ubicacion de la wallet descomprimida
    static final String DB_USER = "ADMIN";
    static final String DB_PASSWORD = "VampBD220203";
    static final String CONN_FACTORY_CLASS_NAME = "oracle.jdbc.pool.OracleDataSource";
    
//    public static void main(String[] args) throws Exception 
//    {
//        getConnection();
//    }
//    
//    private static void doSQLWork(Connection conn) throws SQLException {
//        String query = "SELECT table_name FROM user_tables FETCH FIRST 5 ROWS ONLY";
//
//        try (Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery(query)) {
//            System.out.println("Tablas en tu esquema ADMIN:");
//            while (rs.next()) {
//                System.out.println(" - " + rs.getString(1));
//            }
//        }
//    }
    
    private static PoolDataSource pds;

    // 2. Inicialízalo una sola vez en un bloque estático
    static {
        try {
            pds = PoolDataSourceFactory.getPoolDataSource();
            pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
            pds.setURL(DB_URL);
            pds.setUser(DB_USER);
            pds.setPassword(DB_PASSWORD);
            pds.setConnectionPoolName("JDBC_UCP_POOL");
            
            // Parámetros del pool
            pds.setInitialPoolSize(3);
            pds.setMinPoolSize(3);
            pds.setMaxPoolSize(10);
            
            System.out.println("✅ Pool de conexiones inicializado.");
            
        } catch (SQLException e) {
            System.err.println("❌ Error al inicializar el pool de conexiones.");
            e.printStackTrace();
        }
    }

    // 3. El método getConnection ahora solo pide una conexión al pool ya creado
    public static Connection getConnection() throws SQLException {
        System.out.println("Solicitando conexión del pool...");
        return pds.getConnection();
    }
    
    public static boolean autenticarUsuarioDB(String username, String password) {
        // No usamos el pool aquí, es una conexión de un solo uso para verificar.
        try (Connection conn = DriverManager.getConnection(DB_URL, username, password)) {
            // Si la línea anterior no lanzó una excepción, la conexión fue exitosa.
            // El 'try-with-resources' cerrará la conexión automáticamente.
            System.out.println("Autenticación exitosa para el usuario DB: " + username);
            return true;
        } catch (SQLException e) {
            // Si ocurre una SQLException (ej. ORA-01017: invalid username/password),
            // las credenciales son incorrectas.
            System.err.println("Fallo de autenticación para el usuario DB: " + username + ". Error: " + e.getMessage());
            return false;
        }
    }
    
//    public static Connection getConnection() throws Exception
//    {
//         // Crear PoolDataSource
//        PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
//
//        // Configuración de la fuente de datos
//        pds.setConnectionFactoryClassName(CONN_FACTORY_CLASS_NAME);
//        pds.setURL(DB_URL);
//        pds.setUser(DB_USER);
//        pds.setPassword(DB_PASSWORD);
//        pds.setConnectionPoolName("JDBC_UCP_POOL");
//
//        // Parámetros de pool (ajústalos a tu necesidad)
//        pds.setInitialPoolSize(3);
//        pds.setMinPoolSize(3);
//        pds.setMaxPoolSize(10);
//
//        Connection conn = pds.getConnection();
//        System.out.println("✅ Conexión exitosa a Oracle Autonomous Database");
//        return conn;
//    }
}