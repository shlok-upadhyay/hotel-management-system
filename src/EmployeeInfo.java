import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class EmployeeInfo extends JFrame implements ActionListener {
  JTable table;
  JButton back;

  public EmployeeInfo() {
    setTitle("HMS - All Employee Details");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel l1 = new JLabel("Name");
    l1.setFont(new Font("Tahoma", Font.BOLD, 14));
    l1.setBounds(55, 30, 100, 20);
    add(l1);

    JLabel l2 = new JLabel("Age");
    l2.setFont(new Font("Tahoma", Font.BOLD, 14));
    l2.setBounds(180, 30, 100, 20);
    add(l2);

    JLabel l3 = new JLabel("Gender");
    l3.setFont(new Font("Tahoma", Font.BOLD, 14));
    l3.setBounds(285, 30, 100, 20);
    add(l3);

    JLabel l4 = new JLabel("Job");
    l4.setFont(new Font("Tahoma", Font.BOLD, 14));
    l4.setBounds(415, 30, 100, 20);
    add(l4);

    JLabel l5 = new JLabel("Salary");
    l5.setFont(new Font("Tahoma", Font.BOLD, 14));
    l5.setBounds(520, 30, 100, 20);
    add(l5);

    JLabel l6 = new JLabel("Phone");
    l6.setFont(new Font("Tahoma", Font.BOLD, 14));
    l6.setBounds(635, 30, 100, 20);
    add(l6);

    JLabel l7 = new JLabel("Email");
    l7.setFont(new Font("Tahoma", Font.BOLD, 14));
    l7.setBounds(760, 30, 100, 20);
    add(l7);

    JLabel l8 = new JLabel("Aadhar ID");
    l8.setFont(new Font("Tahoma", Font.BOLD, 14));
    l8.setBounds(860, 30, 100, 20);
    add(l8);

    table = new JTable();
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    table.setBounds(20, 60, 940, 400);
    add(table);

    try {
      Conn c = new Conn();
      String query = "select * from employee";
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
