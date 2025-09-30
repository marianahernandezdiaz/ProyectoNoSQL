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
import java.util.regex.Pattern;
/**
 *
 * @author ulise
 */
public class FrmDireccion  extends JFrame{
    private JLabel noUsuarioLabel;
    private JTextField calleField;
    private JTextField noExtField;
    private JTextField noIntField;
    private JTextField cpField;
    private JTextField coloniaField;
    private JComboBox<String> estadoComboBox;
    private JComboBox<String> municipioComboBox;
    
    // Mapa para almacenar los IDs de los municipios
    private HashMap<String, Integer> municipioMap;

    public FrmDireccion(Integer NoUsr) {
       initComponents(NoUsr);
       loadEstados();
    }
    
    private void initComponents(Integer NoUsr){
         setTitle("Registro de Usuario");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0, 2, 15, 15)); // 9 filas, 2 columnas, con espacios

        // Inicializar componentes
        calleField = new JTextField(20);
        noExtField = new JTextField(20);
        noIntField = new JTextField(20);
        cpField = new JTextField(20);
        coloniaField = new JTextField(20);
        estadoComboBox = new JComboBox<>();
        municipioComboBox = new JComboBox<>();
        municipioMap = new HashMap<>();
        
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        noUsuarioLabel = new JLabel("Numero de usuario: " + NoUsr.toString());
        noUsuarioLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(noUsuarioLabel);

        // Añadir componentes al panel
        add(topPanel, BorderLayout.NORTH);
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
        saveButton.addActionListener(e -> saveUser(NoUsr));

        setVisible(true);
        
        estadoComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Llama al método para recargar los municipios
                loadMunicipios();
            }
        });
        
        //probar si id municipio funciona
//        municipioComboBox.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e){
//                loadIdM();
//            }
//        });
    }
    
    // Método para guardar el usuario en la BD
    private void saveUser(Integer NoUsr) {
        String query = "INSERT INTO DOMICILIOS (NO_USUARIO, CALLE, NO_EXT, NO_INT, CP, COLONIA, ID_MUNICIPIO) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = db.Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            // Obtener los datos del formulario
            String calle = calleField.getText();
            String noExt = noExtField.getText();
            String noInt = noIntField.getText();
            Integer cp = Integer.parseInt(cpField.getText());
            String colonia = coloniaField.getText();
            String idMun = loadIdM();
            // Obtener el ID del municipio seleccionado
            
            if (estadoComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un Estado válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            if (municipioComboBox.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(this, "Selecciona un municipio válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
//          
            String Error = validateDomicilioData(NoUsr, calle, noExt, noInt, cp, colonia);
            if(Error != null){
                JOptionPane.showMessageDialog(this, Error, "Datos Inválidos", JOptionPane.WARNING_MESSAGE);
                return;
            }
            

            // Asignar los valores a la consulta
            pstmt.setInt(1, NoUsr);
            pstmt.setString(2, calle);
            pstmt.setString(3, noExt);
            pstmt.setString(4, noInt);
            pstmt.setInt(5, cp);
            pstmt.setString(6, colonia);
            pstmt.setString(7, idMun);
            
            pstmt.executeUpdate();
            
            JOptionPane.showMessageDialog(this, "Usuario guardado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            
            // Limpiar campos después de guardar
            calleField.setText("");
            // ... y así sucesivamente
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar el usuario: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    private String loadIdM(){
        
        String selectedMunicipio = (String) municipioComboBox.getSelectedItem();
        //System.out.println(selectedMunicipio);
        String mun = "";
        
        String query = "SELECT ID_MUNICIPIO FROM CAT_MUNICIPIOS WHERE NOM_MUNICIPIO = ? ORDER BY ID_MUNICIPIO";
        try (Connection conn = db.Conexion.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, selectedMunicipio);
            
            try(ResultSet rs = pstmt.executeQuery()){
                while(rs.next()){
                    mun = rs.getString("ID_MUNICIPIO");
                }
                System.out.println(mun);
                
            }
        }catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar municipios.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        return mun;
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
    
    
    public static String validateDomicilioData(Integer noUsuario, String calle, String noExt, String noInt, Integer cp, String colonia) {

        // 1. Validar campos obligatorios (NOT NULL)
        if (noUsuario == null  || noUsuario == 0) {
            return "El número de usuario es obligatorio.";
        }
        if (calle == null || calle.trim().isEmpty()) {
            return "La calle es obligatoria.";
        }
        if (noExt == null || noExt.trim().isEmpty()) {
            return "El número exterior es obligatorio.";
        }
        String cpS = cp.toString();
        if (cpS == null) {
            return "El código postal es obligatorio.";
        }
        if (colonia == null || colonia.trim().isEmpty()) {
            return "La colonia es obligatoria.";
        }

        // 2. Validar tipos de datos y longitudes (VARCHAR2, NUMBER)
        // NO_USUARIO (NUMBER(6))
        try {
            String userNumber = noUsuario.toString();
            if (userNumber.length() > 6 || noUsuario < 0) {
                 return "El número de usuario debe ser un número positivo de 6 dígitos como máximo.";
            }
        } catch (NumberFormatException e) {
            return "El número de usuario no es un valor numérico válido.";
        }

        // CALLE (VARCHAR2(40))
        if (calle.length() > 40) {
            return "La calle no puede exceder los 40 caracteres.";
        }
        
        // NO_EXT (VARCHAR2(4))
        if (noExt.length() > 4) {
            return "El número exterior no puede exceder los 4 caracteres.";
        }
        
        // NO_INT (VARCHAR2(3)) - No es obligatorio, pero validamos la longitud si se ingresa.
        if (noInt != null && noInt.length() > 3) {
            return "El número interior no puede exceder los 3 caracteres.";
        }
        
        // CP (NUMBER(5)) - Usamos REGEX para garantizar 5 dígitos
        if (!Pattern.matches("\\d{5}", cpS)) {
            return "El código postal debe ser un número de 5 dígitos.";
        }

        // COLONIA (VARCHAR2(30))
        if (colonia.length() > 30) {
            return "La colonia no puede exceder los 30 caracteres.";
        }

        // 3. (Opcional) Validar formatos adicionales
        // Puedes agregar más validaciones, por ejemplo:
        // - Usar una expresión regular para NO_EXT y NO_INT si tienen un formato específico.
        // - Validar que la colonia no contenga números o caracteres especiales.
        
        // 4. (Opcional) Validar la existencia de claves foráneas
        // La validación de ID_MUNICIPIO (FK_IDMUN_MUN) y NO_USUARIO (FK_NOUSR_DOM) se debe hacer
        // con una consulta a la base de datos justo antes de insertar el registro,
        // ya que esta función solo valida el formato de los datos, no su existencia.

        return null; // Si llegamos aquí, todos los datos son válidos
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
//
//    public static void main(String[] args) {
//        SwingUtilities.invokeLater(() -> new FrmDireccion(2));
//    }
}
