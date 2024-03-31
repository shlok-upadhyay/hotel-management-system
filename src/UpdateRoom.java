import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateRoom extends JFrame implements ActionListener {
  JComboBox<String> cbroom, cbstatus;
  JTextField tftype, tfavailable;
  JButton update, back;
  Boolean getDetailsFlag = false;

  UpdateRoom() {
    setTitle("HMS - Update Room Status");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Update Room Status");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(95, 20, 250, 30);
    add(heading);

    JLabel lblroom = new JLabel("Select Room");
    lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblroom.setBounds(30, 80, 120, 30);
    add(lblroom);

    cbroom = new JComboBox<>();
    cbroom.setBackground(Color.WHITE);
    cbroom.setBounds(200, 80, 170, 30);
    cbroom.addActionListener(this);
    add(cbroom);

    try {
      Conn c = new Conn();
      String query = "select * from room order by roomnumber";
      ResultSet rs = c.s.executeQuery(query);

      while (rs.next()) {
        cbroom.addItem(rs.getString("roomnumber"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lbltype = new JLabel("Room Type");
    lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lbltype.setBounds(30, 130, 120, 30);
    add(lbltype);

    tftype = new JTextField();
    tftype.setBounds(200, 130, 170, 30);
    tftype.setEditable(false);
    add(tftype);

    JLabel lblavailable = new JLabel("Availability");
    lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblavailable.setBounds(30, 180, 120, 30);
    add(lblavailable);

    tfavailable = new JTextField();
    tfavailable.setBounds(200, 180, 170, 30);
    tfavailable.setEditable(false);
    add(tfavailable);

    JLabel lblstatus = new JLabel("Cleaning Status");
    lblstatus.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblstatus.setBounds(30, 230, 120, 30);
    add(lblstatus);

    String statusOptions[] = { "Cleaned", "Dirty" };
    cbstatus = new JComboBox<>(statusOptions);
    cbstatus.setBounds(200, 230, 170, 30);
    add(cbstatus);

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

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
    Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(420, 55, 500, 300);
    add(image);

    setBounds(300, 200, 980, 450);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == cbroom) {
      if (getDetailsFlag) {
        getDetails();
      }
    } else if (ae.getSource() == update) {
      String status = cbstatus.getSelectedItem().toString();
      
      try {
        Conn c = new Conn();
        String query = "update room set cleaning_status='" + status + "' where roomnumber='" + cbroom.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Room Status Updated Successfully");

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
      String query = "select * from room where roomnumber='" + cbroom.getSelectedItem().toString() + "'";
      ResultSet rs = c.s.executeQuery(query);
      while (rs.next()) {
        tftype.setText(rs.getString("bed_type"));
        tfavailable.setText(rs.getString("availability"));
        cbstatus.setSelectedItem(rs.getString("cleaning_status"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
