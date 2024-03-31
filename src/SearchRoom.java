import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener {
  JComboBox<String> cbbed;
  JCheckBox available;
  JTable table;
  JButton submit, back;

  public SearchRoom() {
    setTitle("HMS - Search Room");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Search for Room");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(430, 30, 200, 30);
    add(heading);

    JLabel lblbed = new JLabel("Bed Type");
    lblbed.setFont(new Font("Tahoma", Font.PLAIN, 14));
    lblbed.setBounds(50, 100, 100, 20);
    add(lblbed);

    String bedOptions[] = { "Single Bed", "Double Bed" };
    cbbed = new JComboBox<>(bedOptions);
    cbbed.setBackground(Color.WHITE);
    cbbed.setFont(new Font("Tahoma", Font.PLAIN, 14));
    cbbed.setBounds(150, 100, 150, 25);
    add(cbbed);

    available = new JCheckBox("Only Display Available Rooms");
    available.setBackground(Color.WHITE);
    available.setFont(new Font("Tahoma", Font.PLAIN, 14));
    available.setBounds(650, 100, 250, 25);
    add(available);

    JLabel l1 = new JLabel("Room Number");
    l1.setFont(new Font("Tahoma", Font.BOLD, 14));
    l1.setBounds(65, 170, 180, 20);
    add(l1);

    JLabel l2 = new JLabel("Availability");
    l2.setFont(new Font("Tahoma", Font.BOLD, 14));
    l2.setBounds(275, 170, 180, 20);
    add(l2);

    JLabel l3 = new JLabel("Cleaning Status");
    l3.setFont(new Font("Tahoma", Font.BOLD, 14));
    l3.setBounds(455, 170, 180, 20);
    add(l3);

    JLabel l4 = new JLabel("Bed Type");
    l4.setFont(new Font("Tahoma", Font.BOLD, 14));
    l4.setBounds(675, 170, 180, 20);
    add(l4);

    JLabel l5 = new JLabel("Price");
    l5.setFont(new Font("Tahoma", Font.BOLD, 14));
    l5.setBounds(890, 170, 180, 20);
    add(l5);

    table = new JTable();
    table.setFont(new Font("Tahoma", Font.PLAIN, 14));
    table.setBounds(20, 200, 990, 300);
    add(table);

    try {
      Conn c = new Conn();
      String query = "select * from room";
      ResultSet rs = c.s.executeQuery(query);

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
        String query1 = "select * from room where bed_type='"+ cbbed.getSelectedItem().toString() + "'";
        String query2 = "select * from room where bed_type='" + cbbed.getSelectedItem().toString() + "' and availability='Available' ";
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
