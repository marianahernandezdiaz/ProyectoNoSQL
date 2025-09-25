/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login extends JFrame {
    
     public Login() {
        setTitle("Login");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel Izquierdo (Imagen)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.WHITE);
        leftPanel.setLayout(new BorderLayout());

        // Imagen ilustrativa
        ImageIcon imageIcon = new ImageIcon("src/resources/login.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        leftPanel.add(imageLabel, BorderLayout.CENTER);

        // Panel Derecho (Formulario)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(27, 55, 109)); // Azul
        rightPanel.setLayout(null);

        JLabel titleLabel = new JLabel("Bienvenido!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 32));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBounds(120, 50, 300, 40);
        rightPanel.add(titleLabel);

        JLabel subtitleLabel = new JLabel("Inicie sesión en su cuenta");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        subtitleLabel.setForeground(Color.WHITE);
        subtitleLabel.setBounds(120, 90, 300, 30);
        rightPanel.add(subtitleLabel);

        JLabel userLabel = new JLabel("Usuario");
        userLabel.setForeground(Color.WHITE);
        userLabel.setBounds(120, 150, 100, 30);
        rightPanel.add(userLabel);

        JTextField userField = new RoundedTextField(20);
        userField.setBounds(120, 180, 280, 45);
        rightPanel.add(userField);

        JLabel passLabel = new JLabel("Password");
        passLabel.setForeground(Color.WHITE);
        passLabel.setBounds(120, 240, 100, 30);
        rightPanel.add(passLabel);

        JPasswordField passField = new RoundedPasswordField(20);
        passField.setBounds(120, 270, 280, 45);
        rightPanel.add(passField);

        RoundedButton loginButton = new RoundedButton("Login");
        loginButton.setBounds(180, 340, 160, 50);
        rightPanel.add(loginButton);

        // Agregar paneles al frame
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);

        setVisible(true);



//accion del boton LOGIN
        loginButton.addActionListener(e -> {
        String username = userField.getText();
        String password = new String(passField.getPassword());

        // Llamamos al nuevo método de autenticación directa
        if (db.Conexion.autenticarUsuarioDB(username, password)) {
            JOptionPane.showMessageDialog(this,
                    "¡Login exitoso! Bienvenido " + username + ".",
                    "Acceso Concedido",
                    JOptionPane.INFORMATION_MESSAGE);

            // Aquí podrías cerrar esta ventana y abrir la principal
            // dispose();

        } else {
            JOptionPane.showMessageDialog(this,
                    "Usuario o contraseña de base de datos incorrectos.",
                    "Error de Acceso",
                    JOptionPane.ERROR_MESSAGE);
        }
        });
        

        
        
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Login::new);
    }
    
    

    
    
    

    // Clase para JTextField con bordes redondeados
    class RoundedTextField extends JTextField {
        private int arc;

        public RoundedTextField(int arc) {
            this.arc = arc;
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    // Clase para JPasswordField con bordes redondeados
    class RoundedPasswordField extends JPasswordField {
        private int arc;

        public RoundedPasswordField(int arc) {
            this.arc = arc;
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);
            super.paintComponent(g);
            g2.dispose();
        }
    }

    // Clase para botón redondeado con color personalizado y efecto hover
    class RoundedButton extends JButton {
        private Color colorNormal = new Color(255, 102, 0);
        private Color colorHover = new Color(255, 140, 40);

        public RoundedButton(String text) {
            super(text);
            setFocusPainted(false);
            setBorderPainted(false);
            setForeground(Color.WHITE);
            setFont(new Font("Arial", Font.BOLD, 18));
            setContentAreaFilled(false);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    setBackground(colorHover);
                    repaint();
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    setBackground(colorNormal);
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground() == null ? colorNormal : getBackground());
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);
            super.paintComponent(g);
            g2.dispose();
        }
    }
    
}
