import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
  JTextField username;
  JPasswordField password;
  JButton login, cancel;

  Login() {
    setTitle("HMS - Login");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBounds(500, 200, 600, 300);
    getContentPane().setBackground(Color.WHITE);
    setLayout(null);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel user = new JLabel("Username");
    user.setBounds(40, 20, 100, 30);
    add(user);

    username = new JTextField();
    username.setBounds(150, 20, 150, 30);
    add(username);

    JLabel pass = new JLabel("Password");
    pass.setBounds(40, 70, 100, 30);
    add(pass);

    password = new JPasswordField();
    password.setBounds(150, 70, 150, 30);
    add(password);

    login = new JButton("Login");
    login.setBackground(Color.BLACK);
    login.setBounds(40, 150, 120, 30);
    login.setForeground(Color.WHITE);
    login.addActionListener(this);
    add(login);

    cancel = new JButton("Cancel");
    cancel.setBounds(180, 150, 120, 30);
    cancel.setBackground(Color.BLACK);
    cancel.setForeground(Color.WHITE);
    cancel.addActionListener(this);
    add(cancel);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
    Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(350, 10, 200, 200);
    add(image);

    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == login) {
      String user = username.getText();
      String pass = new String(password.getPassword());

      try {
        Conn c = new Conn();

        String query = "select * from login where username = '" + user + "' and password = '" + pass + "'";

        ResultSet rs = c.s.executeQuery(query);

        if (rs.next()) {
          setVisible(false);
          if (user.equals("admin")) {
            new Dashboard(true);
          } else {
            new Dashboard(false);
          }
        } else {
          JOptionPane.showMessageDialog(null, "Invalid username or password");
          username.setText("");
          password.setText("");
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (ae.getSource() == cancel) {
      setVisible(false);
      System.exit(0);
    }
  }
}
