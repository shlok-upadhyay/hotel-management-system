import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveDriver extends JFrame implements ActionListener {
  JComboBox<String> cbname;
  JTextField tfcarcompany, tfcarmodel, tflocation;
  JButton remove, cancel;
  Boolean getDetailsFlag = false;

  RemoveDriver() {
    setTitle("HMS - Remove Driver");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Remove Driver");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(100, 20, 200, 30);
    add(heading);

    JLabel lblroom = new JLabel("Select Name");
    lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblroom.setBounds(30, 80, 120, 30);
    add(lblroom);

    cbname = new JComboBox<>();
    cbname.setBackground(Color.WHITE);
    cbname.setBounds(180, 80, 150, 30);
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

    JLabel lblcarcompany = new JLabel("Car Company");
    lblcarcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcarcompany.setBounds(30, 130, 120, 30);
    add(lblcarcompany);

    tfcarcompany = new JTextField();
    tfcarcompany.setBounds(180, 130, 150, 30);
    tfcarcompany.setEditable(false);
    add(tfcarcompany);

    JLabel lblcarmodel = new JLabel("Car Model");
    lblcarmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcarmodel.setBounds(30, 180, 120, 30);
    add(lblcarmodel);

    tfcarmodel = new JTextField();
    tfcarmodel.setBounds(180, 180, 150, 30);
    tfcarmodel.setEditable(false);
    add(tfcarmodel);


    JLabel lbllocation = new JLabel("Location");
    lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lbllocation.setBounds(30, 230, 120, 30);
    add(lbllocation);

    tflocation = new JTextField();
    tflocation.setBounds(180, 230, 150, 30);
    tflocation.setEditable(false);
    add(tflocation);

    getDetails();
    getDetailsFlag = true;

    remove = new JButton("Remove");
    remove.setBackground(Color.BLACK);
    remove.setForeground(Color.WHITE);
    remove.setBounds(45, 290, 120, 30);
    remove.addActionListener(this);
    add(remove);

    cancel = new JButton("Cancel");
    cancel.setBackground(Color.BLACK);
    cancel.setForeground(Color.WHITE);
    cancel.setBounds(185, 290, 120, 30);
    cancel.addActionListener(this);
    add(cancel);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifteen.png"));
    Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(400, 50, 250, 250);
    add(image);

    setBounds(400, 200, 730, 400);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == cbname) {
      if (getDetailsFlag) {
        getDetails();
      }
    } else if (ae.getSource() == remove) {
      try {
        Conn c = new Conn();
        String query = "delete from driver where name='" + cbname.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Driver Removed Successfully");

        setVisible(false);
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (ae.getSource() == cancel) {
      setVisible(false);
    }
  }

  public void getDetails() {
    try {
      Conn c = new Conn();
      String query = "select * from driver where name = '" + cbname.getSelectedItem().toString() + "'";
      ResultSet rs = c.s.executeQuery(query);
      while (rs.next()) {
        tfcarcompany.setText(rs.getString("car_company"));
        tfcarmodel.setText(rs.getString("car_model"));
        tflocation.setText(rs.getString("location"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
