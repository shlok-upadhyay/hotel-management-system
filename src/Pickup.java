import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class Pickup extends JFrame implements ActionListener {
  JComboBox<String> cbcar;
  JCheckBox available;
  JTable table;
  JButton submit, back;

  public Pickup() {
    setTitle("HMS - Pickup Service");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Pickup Service");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(440, 30, 200, 30);
    add(heading);

    JLabel lblcar = new JLabel("Type of Car");
    lblcar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblcar.setBounds(50, 100, 100, 20);
    add(lblcar);

    cbcar = new JComboBox<>();
    cbcar.setBackground(Color.WHITE);
    cbcar.setFont(new Font("Tahoma", Font.PLAIN, 14));
    cbcar.setBounds(150, 100, 150, 25);
    add(cbcar);

    try {
      Conn c = new Conn();
      String query1 = "select distinct car_model from driver";
      ResultSet rs = c.s.executeQuery(query1);

      while(rs.next()) {
        cbcar.addItem(rs.getString("car_model"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    available = new JCheckBox("Only Display Available Cars");
    available.setBackground(Color.WHITE);
    available.setFont(new Font("Tahoma", Font.PLAIN, 14));
    available.setBounds(650, 100, 250, 25);
    add(available);

    JLabel l1 = new JLabel("Name");
    l1.setFont(new Font("Tahoma", Font.BOLD, 14));
    l1.setBounds(55, 170, 100, 20);
    add(l1);

    JLabel l2 = new JLabel("Age");
    l2.setFont(new Font("Tahoma", Font.BOLD, 14));
    l2.setBounds(190, 170, 100, 20);
    add(l2);

    JLabel l3 = new JLabel("Gender");
    l3.setFont(new Font("Tahoma", Font.BOLD, 14));
    l3.setBounds(300, 170, 100, 20);
    add(l3);

    JLabel l4 = new JLabel("Car Company");
    l4.setFont(new Font("Tahoma", Font.BOLD, 14));
    l4.setBounds(400, 170, 100, 20);
    add(l4);

    JLabel l5 = new JLabel("Car Model");
    l5.setFont(new Font("Tahoma", Font.BOLD, 14));
    l5.setBounds(535, 170, 100, 20);
    add(l5);

    JLabel l6 = new JLabel("Phone");
    l6.setFont(new Font("Tahoma", Font.BOLD, 14));
    l6.setBounds(670, 170, 100, 20);
    add(l6);

    JLabel l7 = new JLabel("Availability");
    l7.setFont(new Font("Tahoma", Font.BOLD, 14));
    l7.setBounds(785, 170, 100, 20);
    add(l7);

    JLabel l8 = new JLabel("Location");
    l8.setFont(new Font("Tahoma", Font.BOLD, 14));
    l8.setBounds(910, 170, 100, 20);
    add(l8);

    table = new JTable();
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    table.setBounds(20, 200, 990, 300);
    add(table);

    try {
      Conn c = new Conn();
      String query1 = "select * from driver";
      ResultSet rs = c.s.executeQuery(query1);
      table.setModel(DbUtils.resultSetToTableModel(rs));

    } catch (Exception e) {
      e.printStackTrace();
    }

    submit = new JButton("Submit");
    submit.setBackground(Color.BLACK);
    submit.setForeground(Color.WHITE);
    submit.setBounds(350, 500, 120, 30);
    submit.addActionListener(this);
    add(submit);

    back = new JButton("Back");
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setBounds(550, 500, 120, 30);
    back.addActionListener(this);
    add(back);

    setBounds(250, 200, 1050, 600);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == submit) {
      try {
        Conn c = new Conn();
        String query1 = "select * from driver where car_model='"+ cbcar.getSelectedItem().toString() + "'";
        String query2 = "select * from driver where car_model='"+ cbcar.getSelectedItem().toString() + "' and availability='Available'";
        ResultSet rs;

        if (available.isSelected()) {
          rs = c.s.executeQuery(query2);
        } else {
          rs = c.s.executeQuery(query1);
        }
        
        table.setModel(DbUtils.resultSetToTableModel(rs));

      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (ae.getSource() == back) {
      setVisible(false);
      new Reception();
    }
  }
}
