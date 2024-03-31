import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {
  JComboBox<String> cbid, cbroom;
  JTextField tfidnum, tfname, tfcountry, tfdeposit;
  JRadioButton rbmale, rbfemale;
  JLabel checkin;
  JButton addCustomer, back;

  AddCustomer() {
    setTitle("HMS - New Customer Form");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("New Customer Form");
    heading.setBounds(85, 20, 300, 20);
    heading.setFont(new Font("Tahoma", Font.BOLD, 20));
    add(heading);

    JLabel lblid = new JLabel("ID");
    lblid.setBounds(35, 80, 150, 30);
    lblid.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblid);

    String idOptions[] = { "Aadhar Card", "Passport", "Driving License", "Voter-ID Card", "PAN Card" };
    cbid = new JComboBox<>(idOptions);
    cbid.setBounds(200, 80, 150, 30);
    cbid.setBackground(Color.WHITE);
    add(cbid);

    JLabel lblidnum = new JLabel("ID Number");
    lblidnum.setBounds(35, 130, 150, 30);
    lblidnum.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblidnum);

    tfidnum = new JTextField();
    tfidnum.setBounds(200, 130, 150, 30);
    add(tfidnum);

    JLabel lblname = new JLabel("Name");
    lblname.setBounds(35, 180, 150, 30);
    lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblname);

    tfname = new JTextField();
    tfname.setBounds(200, 180, 150, 30);
    add(tfname);

    JLabel lblgender = new JLabel("Gender");
    lblgender.setBounds(35, 230, 150, 30);
    lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblgender);

    rbmale = new JRadioButton("Male");
    rbmale.setBackground(Color.WHITE);
    rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rbmale.setBounds(200, 230, 70, 30);
    add(rbmale);

    rbfemale = new JRadioButton("Female");
    rbfemale.setBackground(Color.WHITE);
    rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rbfemale.setBounds(280, 230, 70, 30);
    add(rbfemale);

    ButtonGroup bg = new ButtonGroup();
    bg.add(rbmale);
    bg.add(rbfemale);

    JLabel lblcountry = new JLabel("Country");
    lblcountry.setBounds(35, 280, 150, 30);
    lblcountry.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblcountry);

    tfcountry = new JTextField();
    tfcountry.setBounds(200, 280, 150, 30);
    add(tfcountry);

    JLabel lblroom = new JLabel("Room Number");
    lblroom.setBounds(35, 330, 150, 30);
    lblroom.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblroom);

    cbroom = new JComboBox<>();
    cbroom.setBounds(200, 330, 150, 30);
    cbroom.setBackground(Color.WHITE);
    add(cbroom);

    try {
      Conn c = new Conn();
      String query = "select * from room where availability = 'Available'";
      ResultSet rs = c.s.executeQuery(query);
      while(rs.next()) {
        cbroom.addItem(rs.getString("roomnumber"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    JLabel lblcheckin = new JLabel("Checkin Date Time");
    lblcheckin.setBounds(35, 380, 150, 30);
    lblcheckin.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblcheckin);

    Date date = new Date();

    checkin = new JLabel(date.toString().substring(0, 16));
    checkin.setBounds(200, 380, 150, 30);
    checkin.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(checkin);

    JLabel lbldeposit = new JLabel("Deposit");
    lbldeposit.setBounds(35, 430, 150, 30);
    lbldeposit.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lbldeposit);

    tfdeposit = new JTextField();
    tfdeposit.setBounds(200, 430, 150, 30);
    add(tfdeposit);

    addCustomer = new JButton("Add");
    addCustomer.setBackground(Color.BLACK);
    addCustomer.setForeground(Color.WHITE);
    addCustomer.setBounds(50, 490, 130, 30);
    addCustomer.addActionListener(this);
    add(addCustomer);

    back = new JButton("Back");
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.setBounds(200, 490, 130, 30);
    back.addActionListener(this);
    add(back);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
    Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(420, 90, 300, 400);
    add(image);

    setBounds(360, 200, 800, 600);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == addCustomer) {
      String id = cbid.getSelectedItem().toString();
      String idNumber = tfidnum.getText();
      String name = tfname.getText();
      String gender = (rbmale.isSelected()) ? "Male" : ((rbfemale.isSelected()) ? "Female" : "");
      String country = tfcountry.getText();
      String room = cbroom.getSelectedItem().toString();
      String checkInDetail = checkin.getText();
      String deposit = tfdeposit.getText();

      if (idNumber.equals("")) {
        JOptionPane.showMessageDialog(null, "ID Number cannot be empty");
        return;
      }

      if ((idNumber.length() < 8) || (idNumber.length() > 16)) {
        JOptionPane.showMessageDialog(null, "ID Number is invalid");
        return;
      }

      if (name.equals("")) {
        JOptionPane.showMessageDialog(null, "Name cannot be empty");
        return;
      }

      if (gender.equals("")) {
        JOptionPane.showMessageDialog(null, "Gender is not selected");
        return;
      }

      if (country.equals("")) {
        JOptionPane.showMessageDialog(null, "Country cannot be empty");
        return;
      }

      if (room.equals("")) {
        JOptionPane.showMessageDialog(null, "No Room Available");
        return;
      }

      if (deposit.equals("")) {
        JOptionPane.showMessageDialog(null, "Deposit cannot be empty");
        return;
      }

      try {
        Conn c = new Conn();
        String query = "insert into customer values('" + id + "', '" + idNumber + "', '" + name + "', '" + gender + "', '" + country + "', '" + room + "', '" + checkInDetail + "', '" + deposit + "')";
        String query2 = "update room set availability = 'Occupied' where roomnumber = '" + room + "'";
        c.s.executeUpdate(query);
        c.s.executeUpdate(query2);

        JOptionPane.showMessageDialog(null, "New Customer Added Successfully");

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
}
