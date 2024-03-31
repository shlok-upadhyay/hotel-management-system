import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class Checkout extends JFrame implements ActionListener {
  JComboBox<String> cbroom;
  JTextField tfname, tfcheckin, tfcheckout;
  JButton checkout, back;
  Boolean getDetailsFlag = false;

  Checkout() {
    setTitle("HMS - Checkout");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Checkout");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(130, 20, 100, 30);
    add(heading);

    JLabel lblroom = new JLabel("Select Room");
    lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblroom.setBounds(30, 80, 120, 30);
    add(lblroom);

    cbroom = new JComboBox<>();
    cbroom.setBackground(Color.WHITE);
    cbroom.setBounds(180, 80, 150, 30);
    cbroom.addActionListener(this);
    add(cbroom);

    try {
      Conn c = new Conn();
      String query = "select * from customer order by room";
      ResultSet rs = c.s.executeQuery(query);

      while (rs.next()) {
        cbroom.addItem(rs.getString("room"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
    Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(340, 85, 20, 20);
    add(image);

    JLabel lblname = new JLabel("Name");
    lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblname.setBounds(30, 130, 120, 30);
    add(lblname);

    tfname = new JTextField();
    tfname.setBounds(180, 130, 150, 30);
    tfname.setEditable(false);
    add(tfname);

    JLabel lblcheckin = new JLabel("Checkin Time");
    lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcheckin.setBounds(30, 180, 120, 30);
    add(lblcheckin);

    tfcheckin = new JTextField();
    tfcheckin.setBounds(180, 180, 150, 30);
    tfcheckin.setEditable(false);
    add(tfcheckin);

    getDetails();
    getDetailsFlag = true;

    JLabel lblcheckout = new JLabel("Checkout Time");
    lblcheckout.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcheckout.setBounds(30, 230, 120, 30);
    add(lblcheckout);

    Date date = new Date();

    tfcheckout = new JTextField(date.toString().substring(0, 16));
    tfcheckout.setBounds(180, 230, 150, 30);
    tfcheckout.setEditable(false);
    add(tfcheckout);

    checkout = new JButton("Checkout");
    checkout.setBackground(Color.BLACK);
    checkout.setForeground(Color.WHITE);
    checkout.setBounds(45, 290, 120, 30);
    checkout.addActionListener(this);
    add(checkout);

    back = new JButton("Back");
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setBounds(185, 290, 120, 30);
    back.addActionListener(this);
    add(back);

    ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
    Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
    ImageIcon i6 = new ImageIcon(i5);
    JLabel image2 = new JLabel(i6);
    image2.setBounds(380, 50, 400, 250);
    add(image2);

    setBounds(300, 200, 820, 400);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == cbroom) {
      if (getDetailsFlag) {
        getDetails();
      }
    } else if (ae.getSource() == checkout) {
      try {
        Conn c = new Conn();
        String query1 = "delete from customer where room='" + cbroom.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query1);

        String query2 = "update room set availability='Available' where roomnumber='" + cbroom.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query2);

        JOptionPane.showMessageDialog(null, "Checkout Done");

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
      String query = "select * from customer where room = '" + cbroom.getSelectedItem().toString() + "'";
      ResultSet rs = c.s.executeQuery(query);
      while (rs.next()) {
        tfname.setText(rs.getString("name"));
        tfcheckin.setText(rs.getString("checkin"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
