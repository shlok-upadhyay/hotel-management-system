import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class CustomerInfo extends JFrame implements ActionListener {
  JTable table;
  JButton back;

  public CustomerInfo() {
    setTitle("HMS - All Customers Details");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel l1 = new JLabel("ID");
    l1.setFont(new Font("Tahoma", Font.BOLD, 14));
    l1.setBounds(65, 30, 100, 20);
    add(l1);

    JLabel l2 = new JLabel("ID Number");
    l2.setFont(new Font("Tahoma", Font.BOLD, 14));
    l2.setBounds(155, 30, 100, 20);
    add(l2);

    JLabel l3 = new JLabel("Name");
    l3.setFont(new Font("Tahoma", Font.BOLD, 14));
    l3.setBounds(290, 30, 100, 20);
    add(l3);

    JLabel l4 = new JLabel("Gender");
    l4.setFont(new Font("Tahoma", Font.BOLD, 14));
    l4.setBounds(400, 30, 100, 20);
    add(l4);

    JLabel l5 = new JLabel("Country");
    l5.setFont(new Font("Tahoma", Font.BOLD, 14));
    l5.setBounds(515, 30, 100, 20);
    add(l5);

    JLabel l6 = new JLabel("Room");
    l6.setFont(new Font("Tahoma", Font.BOLD, 14));
    l6.setBounds(645, 30, 100, 20);
    add(l6);

    JLabel l7 = new JLabel("Checkin Time");
    l7.setFont(new Font("Tahoma", Font.BOLD, 14));
    l7.setBounds(735, 30, 100, 20);
    add(l7);

    JLabel l8 = new JLabel("Deposit");
    l8.setFont(new Font("Tahoma", Font.BOLD, 14));
    l8.setBounds(870, 30, 100, 20);
    add(l8);

    table = new JTable();
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    table.setBounds(20, 60, 940, 400);
    add(table);

    try {
      Conn c = new Conn();
      String query = "select * from customer order by room";
      ResultSet rs = c.s.executeQuery(query);

      table.setModel(DbUtils.resultSetToTableModel(rs));

    } catch (Exception e) {
      e.printStackTrace();
    }

    back = new JButton("Back");
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setBounds(420, 500, 120, 30);
    back.addActionListener(this);
    add(back);  

    setBounds(300, 200, 1000, 600);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    setVisible(false);
    new Reception();
  }
}
