import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RemoveEmployee extends JFrame implements ActionListener {
  JComboBox<String> cbname;
  JTextField tfjob, tfsalary, tfaadhar;
  JButton remove, cancel;
  Boolean getDetailsFlag = false;

  RemoveEmployee() {
    setTitle("HMS - Remove Employee");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Remove Employee");
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    heading.setBounds(90, 20, 200, 30);
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
      String query = "select * from employee order by name";
      ResultSet rs = c.s.executeQuery(query);

      while (rs.next()) {
        cbname.addItem(rs.getString("name"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lbljob = new JLabel("Job");
    lbljob.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lbljob.setBounds(30, 130, 120, 30);
    add(lbljob);

    tfjob = new JTextField();
    tfjob.setBounds(180, 130, 150, 30);
    tfjob.setEditable(false);
    add(tfjob);

    JLabel lblsalary = new JLabel("Salary");
    lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblsalary.setBounds(30, 180, 120, 30);
    add(lblsalary);

    tfsalary = new JTextField();
    tfsalary.setBounds(180, 180, 150, 30);
    tfsalary.setEditable(false);
    add(tfsalary);


    JLabel lblaadhar = new JLabel("Aadhar ID");
    lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblaadhar.setBounds(30, 230, 120, 30);
    add(lblaadhar);

    tfaadhar = new JTextField();
    tfaadhar.setBounds(180, 230, 150, 30);
    tfaadhar.setEditable(false);
    add(tfaadhar);

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

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourteen.png"));
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
        String query = "delete from employee where name='" + cbname.getSelectedItem().toString() + "'";
        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Employee Removed Successfully");

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
      String query = "select * from employee where name = '" + cbname.getSelectedItem().toString() + "'";
      ResultSet rs = c.s.executeQuery(query);
      while (rs.next()) {
        tfjob.setText(rs.getString("job"));
        tfsalary.setText(rs.getString("salary"));
        tfaadhar.setText(rs.getString("aadhar"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
