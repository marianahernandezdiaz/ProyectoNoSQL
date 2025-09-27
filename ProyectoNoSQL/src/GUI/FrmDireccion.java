/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
/**
 *
 * @author ulise
 */
public class FrmDireccion  extends JFrame{
    private JTextField noUsuarioField;
    private JTextField calleField;
    private JTextField noExtField;
    private JTextField noIntField;
    private JTextField cpField;
    private JTextField coloniaField;
    private JComboBox<String> estadoComboBox;
    private JComboBox<String> municipioComboBox;
    
    // Mapa para almacenar los IDs de los municipios
    private HashMap<String, Integer> municipioMap;

    public FrmDireccion() {
       initComponents();
       loadEstados();
    }
    
    private void initComponents(){
         setTitle("Registro de Usuario");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(9, 2, 10, 10)); // 9 filas, 2 columnas, con espacios

        // Inicializar componentes
        noUsuarioField = new JTextField(20);
        calleField = new JTextField(20);
        noExtField = new JTextField(20);
        noIntField = new JTextField(20);
        cpField = new JTextField(20);
        coloniaField = new JTextField(20);
        estadoComboBox = new JComboBox<>();
        municipioComboBox = new JComboBox<>();
        municipioMap = new HashMap<>();

        // Añadir componentes al panel
        formPanel.add(new JLabel("No. de Usuario:"));
        formPanel.add(noUsuarioField);
        formPanel.add(new JLabel("Calle:"));
        formPanel.add(calleField);
        formPanel.add(new JLabel("No. Exterior:"));
        formPanel.add(noExtField);
        formPanel.add(new JLabel("No. Interior:"));
        formPanel.add(noIntField);
        formPanel.add(new JLabel("Código Postal:"));
        formPanel.add(cpField);
        formPanel.add(new JLabel("Colonia:"));
        formPanel.add(coloniaField);
        formPanel.add(new JLabel("Estado:"));
        formPanel.add(estadoComboBox);
        formPanel.add(new JLabel("Municipio:"));
        formPanel.add(municipioComboBox);
        
        JButton saveButton = new JButton("Guardar Usuario");
        formPanel.add(new JLabel()); // Espacio vacío
        formPanel.add(saveButton);

        add(formPanel, BorderLayout.CENTER);

        // Lógica para cargar datos dinámicamente
        //loadEstados();
        //estadoComboBox.addActionListener(e -> loadMunicipios());
        
        // Manejador del botón
        saveButton.addActionListener(e -> saveUser());

        setVisible(true);
        
    estadoComboBox.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Llama al método para recargar los municipios
            loadMunicipios();
        }
    });
    }
    
    // Método para guardar el usuario en la BD
    private void saveUser() {
        String query = "INSERT INTO usuarios (NO_USUARIO, CALLE, NO_EXT, NO_INT, CP, COLONIA, ID_MUNICIPIO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Obtener los datos del formulario
            String noUsuario = noUsuarioField.getText();
            String calle = calleField.getText();
            String noExt = noExtField.getText();
            String noInt = noIntField.getText();
            String cp = cpField.getText();
            String colonia = coloniaField.getText();
            
            // Obtener el ID del municipio seleccionado
            String selectedMunicipio = (String) municipioComboBox.getSelectedItem();
            Integer idMunicipio = municipioMap.get(selectedMunicipio);
            
            if (idMunicipio == null) {
                JOptionPane.showMessageDialog(this, "Selecciona un municipio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Asignar los valores a la consulta
            pstmt.setString(1, noUsuario);
            pstmt.setString(2, calle);
            pstmt.setString(3, noExt);
            pstmt.setString(4, noInt);
            pstmt.setString(5, cp);
            pstmt.setString(6, colonia);
            pstmt.setInt(7, idMunicipio);
            
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar campos después de guardar
            noUsuarioField.setText("");
            calleField.setText("");
            // ... y así sucesivamente
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    

    
    private void loadEstados() {
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione un estado");
        
        String query = "SELECT NOM_ESTADO FROM CAT_ESTADOS WHERE ESTATUS = 'ACTIVO' ORDER BY NOM_ESTADO";
        
        try (Connection conn = db.Conexion.getConnection(); // Usa tu clase de conexión
             java.sql.Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            if (!rs.isBeforeFirst()) {
            System.out.println("⚠️ El ResultSet está vacío. No hay estados para mostrar.");
            }
            while (rs.next()) {
                System.out.println("sisis");
                String nombreEs = rs.getString("NOM_ESTADO");
                modelo.addElement(nombreEs);
            }
            
            estadoComboBox.setModel(modelo);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar estados.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadMunicipios() {
        municipioComboBox.removeAllItems(); // Limpiar el combo de municipios
        municipioMap.clear();
        
        String selectedEstado = (String) estadoComboBox.getSelectedItem();
        System.out.println(selectedEstado);
        
        DefaultComboBoxModel<String> modelo = new DefaultComboBoxModel<>();
        modelo.addElement("Seleccione un municipio");
        String edo = "";
        //String selectedEstado = "MEX";
        String queryIdE = "SELECT ID_ESTADO FROM CAT_ESTADOS WHERE NOM_ESTADO = ?";
        
        try (Connection conn = db.Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(queryIdE)) {
            
            pstmt.setString(1, selectedEstado);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    edo = rs.getString("ID_ESTADO");
                }
                
            }
            
            
             
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar municipios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        System.out.println(edo);

        //if (selectedEstado == null) return;
        
        String query = "SELECT NOM_MUNICIPIO FROM CAT_MUNICIPIOS m " + 
                        "JOIN CAT_ESTADOS e ON m.ID_ESTADO = e.ID_ESTADO " +
                        "WHERE e.ID_ESTADO = ? ORDER BY m.ID_ESTADO";
                       
        try (Connection conn = db.Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            
            
            pstmt.setString(1, edo);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (!rs.isBeforeFirst()) {
                System.out.println("⚠️ El ResultSet está vacío. No hay estados para mostrar.");
                }
                while (rs.next()) {
                    System.out.println("leyendo");
                    String nombreM = rs.getString("NOM_MUNICIPIO");
                    modelo.addElement(nombreM);
                    //String idMunicipio = rs.getString("ID_MUNICIPIO");
                    
                    //municipioMap.put(nombreMunicipio, idMunicipio);
                }
                municipioComboBox.setModel(modelo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar municipios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FrmDireccion());
    }
}
