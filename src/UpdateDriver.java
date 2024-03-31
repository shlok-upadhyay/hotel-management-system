import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateDriver extends JFrame implements ActionListener {
  JComboBox<String> cbname, cbavailable;
  JTextField tfcar, tflocation;
  JButton update, back;
  Boolean getDetailsFlag = false;

  UpdateDriver() {
    setTitle("HMS - Update Driver Status");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Update Driver Status");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(90, 20, 250, 30);
    add(heading);

    JLabel lblname = new JLabel("Select Driver");
    lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblname.setBounds(30, 80, 120, 30);
    add(lblname);

    cbname = new JComboBox<>();
    cbname.setBackground(Color.WHITE);
    cbname.setBounds(200, 80, 170, 30);
    cbname.addActionListener(this);
    add(cbname);

    try {
      Conn c = new Conn();
      String query = "select * from driver order by name";
      ResultSet rs = c.s.executeQuery(query);

      while (rs.next()) {
        cbname.addItem(rs.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lblcar = new JLabel("Car Model");
    lblcar.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcar.setBounds(30, 130, 120, 30);
    add(lblcar);

    tfcar = new JTextField();
    tfcar.setBounds(200, 130, 170, 30);
    tfcar.setEditable(false);
    add(tfcar);

    JLabel lbllocation = new JLabel("Location");
    lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lbllocation.setBounds(30, 180, 120, 30);
    add(lbllocation);

    tflocation = new JTextField();
    tflocation.setBounds(200, 180, 170, 30);
    tflocation.setEditable(false);
    add(tflocation);

    JLabel lblavailable = new JLabel("Availability");
    lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblavailable.setBounds(30, 230, 120, 30);
    add(lblavailable);

    String statusOptions[] = { "Available", "Busy" };
    cbavailable = new JComboBox<>(statusOptions);
    cbavailable.setBounds(200, 230, 170, 30);
    add(cbavailable);

    getDetails();
    getDetailsFlag = true;

    update = new JButton("Update");
    update.setBackground(Color.BLACK);
    update.setForeground(Color.WHITE);
    update.setBounds(65, 300, 120, 30);
    update.addActionListener(this);
    add(update);

    back = new JButton("Back");
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setBounds(205, 300, 120, 30);
    back.addActionListener(this);
    add(back);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/thirteen.png"));
    Image i2 = i1.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(415, 45, 300, 300);
    add(image);

    setBounds(380, 200, 780, 430);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == cbname) {
      if (getDetailsFlag) {
        getDetails();
      }
    } else if (ae.getSource() == update) {
      String availability = cbavailable.getSelectedItem().toString();
      
      try {
        Conn c = new Conn();
        String query = "update driver set availability='" + availability + "' where name='" + cbname.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Driver Status Updated Successfully");

        setVisible(false);
        new Reception();
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (ae.getSource() == back) {
      setVisible(false);
      new Reception();
    }
  }

  public void getDetails() {
    try {
      Conn c = new Conn();
      String query = "select * from driver where name='" + cbname.getSelectedItem().toString() + "'";
      ResultSet rs = c.s.executeQuery(query);
      while (rs.next()) {
        tfcar.setText(rs.getString("car_model"));
        tflocation.setText(rs.getString("location"));
        cbavailable.setSelectedItem(rs.getString("availability"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
