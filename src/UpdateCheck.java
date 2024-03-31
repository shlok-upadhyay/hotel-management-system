import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCheck extends JFrame implements ActionListener {
  JComboBox<String> cbroom, cbnewroom;
  JTextField tfname, tfcheckin, tfpaid, tfpending;
  JButton check, update, back;
  String idNumber = "", currRoom = "";
  Boolean getDetailsFlag = false;

  UpdateCheck() {
    setTitle("HMS - Update Status");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Update Status");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(125, 20, 200, 30);
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
      String query = "select * from customer order by room";
      ResultSet rs = c.s.executeQuery(query);

      while (rs.next()) {
        cbroom.addItem(rs.getString("room"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lblname = new JLabel("Name");
    lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblname.setBounds(30, 130, 120, 30);
    add(lblname);

    tfname = new JTextField();
    tfname.setBounds(200, 130, 170, 30);
    add(tfname);

    JLabel lblroomno = new JLabel("Room Number");
    lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblroomno.setBounds(30, 180, 120, 30);
    add(lblroomno);

    currRoom = cbroom.getSelectedItem().toString();

    cbnewroom = new JComboBox<>();
    cbnewroom.addItem(currRoom);
    cbnewroom.setBackground(Color.WHITE);
    cbnewroom.setBounds(200, 180, 170, 30);
    add(cbnewroom);

    try {
      Conn c = new Conn();
      String query = "select * from room where availability='Available' order by roomnumber";
      ResultSet rs = c.s.executeQuery(query);

      while (rs.next()) {
        cbnewroom.addItem(rs.getString("roomnumber"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lblcheckin = new JLabel("Checkin Time");
    lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcheckin.setBounds(30, 230, 120, 30);
    add(lblcheckin);

    tfcheckin = new JTextField();
    tfcheckin.setBounds(200, 230, 170, 30);
    tfcheckin.setEditable(false);
    add(tfcheckin);

    JLabel lblpaid = new JLabel("Amount Paid");
    lblpaid.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblpaid.setBounds(30, 280, 120, 30);
    add(lblpaid);

    tfpaid = new JTextField();
    tfpaid.setBounds(200, 280, 170, 30);
    add(tfpaid);

    JLabel lblpending = new JLabel("Pending Amount");
    lblpending.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblpending.setBounds(30, 330, 120, 30);
    add(lblpending);

    tfpending = new JTextField();
    tfpending.setBounds(200, 330, 170, 30);
    add(tfpending);

    getDetails();
    getDetailsFlag = true;

    update = new JButton("Update");
    update.setBackground(Color.BLACK);
    update.setForeground(Color.WHITE);
    update.setBounds(60, 390, 120, 30);
    update.addActionListener(this);
    add(update);

    back = new JButton("Back");
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setBounds(200, 390, 120, 30);
    back.addActionListener(this);
    add(back);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
    JLabel image = new JLabel(i1);
    image.setBounds(400, 50, 500, 300);
    add(image);

    setBounds(300, 200, 980, 500);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == cbroom) {
      if (getDetailsFlag) {
        cbnewroom.removeItem(currRoom);
        currRoom = cbroom.getSelectedItem().toString();
        cbnewroom.addItem(currRoom);
        getDetails();
      }
    } else if (ae.getSource() == update) {
      String name = tfname.getText();
      String roomno = cbnewroom.getSelectedItem().toString();
      String deposit = tfpaid.getText();
      String pendingAmount = tfpending.getText();

      if (name.equals("")) {
        JOptionPane.showMessageDialog(null, "Name cannot be empty");
        return;
      }

      if (deposit.equals("")) {
        JOptionPane.showMessageDialog(null, "Amount Paid cannot be empty");
        return;
      }

      if (pendingAmount.equals("")) {
        JOptionPane.showMessageDialog(null, "Pending Amount cannot be empty");
        return;
      }
      
      try {
        Conn c = new Conn();
        String query = "update customer set name='" + name + "', room='" + roomno + "', deposit='" + deposit + "' where document_number='" + idNumber + "'";
        String query2 = "update room set availability='Available' where roomnumber='" + cbroom.getSelectedItem().toString() + "'";
        String query3 = "update room set availability='Occupied' where roomnumber='" + cbnewroom.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query);
        if (!(cbroom.getSelectedItem().toString().equals(cbnewroom.getSelectedItem().toString()))) {
          c.s.executeUpdate(query2);
          c.s.executeUpdate(query3);
        }

        JOptionPane.showMessageDialog(null, "Data updated successfully");

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
      String query = "select * from customer where room='" + cbroom.getSelectedItem().toString() + "'";
      ResultSet rs = c.s.executeQuery(query);
      
      while (rs.next()) {
        tfname.setText(rs.getString("name"));
        cbnewroom.setSelectedItem(rs.getString("room"));
        tfcheckin.setText(rs.getString("checkin"));
        tfpaid.setText(rs.getString("deposit"));
        idNumber = rs.getString("document_number");
      }

      String query2 = "select * from room where roomnumber='" + cbroom.getSelectedItem().toString() + "'";
      ResultSet rs2 = c.s.executeQuery(query2);
      while(rs2.next()) {
        String price = rs2.getString("price");
        int amountPending = Integer.parseInt(price) - Integer.parseInt(tfpaid.getText());
        tfpending.setText("" + amountPending);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
