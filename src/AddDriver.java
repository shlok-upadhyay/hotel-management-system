import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddDriver extends JFrame implements ActionListener {
  JTextField tfname, tfage, tfcarcompany, tfcarmodel, tfphone, tfloaction;  
  JComboBox<String> availableCombo;
  JRadioButton rbmale, rbfemale;
  JButton addDriver, cancel;

  AddDriver() {
    setTitle("HMS - Add New Driver");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Add Driver");
    heading.setFont(new Font("Tahoma", Font.BOLD, 18));
    heading.setBounds(150, 20, 200, 20);
    add(heading);

    JLabel lblname = new JLabel("Name");
    lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblname.setBounds(60, 80, 120, 30);
    add(lblname);

    tfname = new JTextField();
    tfname.setBounds(200, 80, 150, 30);
    add(tfname);

    JLabel lblage = new JLabel("Age");
    lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblage.setBounds(60, 130, 120, 30);
    add(lblage);

    tfage = new JTextField();
    tfage.setBounds(200, 130, 150, 30);
    add(tfage);

    JLabel lblgender = new JLabel("Gender");
    lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblgender.setBounds(60, 180, 120, 30);
    add(lblgender);

    rbmale = new JRadioButton("Male");
    rbmale.setBackground(Color.WHITE);
    rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rbmale.setBounds(200, 180, 70, 30);
    add(rbmale);

    rbfemale = new JRadioButton("Female");
    rbfemale.setBackground(Color.WHITE);
    rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rbfemale.setBounds(280, 180, 70, 30);
    add(rbfemale);

    ButtonGroup bg = new ButtonGroup();
    bg.add(rbmale);
    bg.add(rbfemale);
    
    JLabel lblcarcompany = new JLabel("Car Company");
    lblcarcompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcarcompany.setBounds(60, 230, 120, 30);
    add(lblcarcompany);

    tfcarcompany = new JTextField();
    tfcarcompany.setBounds(200, 230, 150, 30);
    add(tfcarcompany);

    JLabel lblcarmodel = new JLabel("Car Model");
    lblcarmodel.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblcarmodel.setBounds(60, 280, 120, 30);
    add(lblcarmodel);

    tfcarmodel = new JTextField();
    tfcarmodel.setBounds(200, 280, 150, 30);
    add(tfcarmodel);

    JLabel lblphone = new JLabel("Phone");
    lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblphone.setBounds(60, 330, 120, 30);
    add(lblphone);

    tfphone = new JTextField();
    tfphone.setBounds(200, 330, 150, 30);
    add(tfphone);

    JLabel lblavailable = new JLabel("Availability");
    lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblavailable.setBounds(60, 380, 120, 30);
    add(lblavailable);

    String availableOptions[] = { "Available", "Busy" };
    availableCombo = new JComboBox<>(availableOptions);
    availableCombo.setBounds(200, 380, 150, 30);
    availableCombo.setBackground(Color.WHITE);
    add(availableCombo);

    JLabel lbllocation = new JLabel("Location");
    lbllocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lbllocation.setBounds(60, 430, 120, 30);
    add(lbllocation);

    tfloaction = new JTextField();
    tfloaction.setBounds(200, 430, 150, 30);
    add(tfloaction);

    addDriver = new JButton("Add Driver");
    addDriver.setForeground(Color.WHITE);
    addDriver.setBackground(Color.BLACK);
    addDriver.setBounds(60, 490, 130, 30);
    addDriver.addActionListener(this);
    add(addDriver);

    cancel = new JButton("Cancel");
    cancel.setForeground(Color.WHITE);
    cancel.setBackground(Color.BLACK);
    cancel.setBounds(220, 490, 130, 30);
    cancel.addActionListener(this);
    add(cancel);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/eleven.jpg"));
    Image i2 = i1.getImage().getScaledInstance(510, 400, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(400, 80, 510, 400);
    add(image);

    setBounds(280, 200, 980, 600);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == addDriver) {
      String name = tfname.getText();
      String age = tfage.getText();
      String gender = (rbmale.isSelected()) ? "Male" : ((rbfemale.isSelected()) ? "Female" : "");
      String carCompany = tfcarcompany.getText();
      String carModel = tfcarmodel.getText();
      String phone = tfphone.getText();
      String availability = availableCombo.getSelectedItem().toString();
      String location = tfloaction.getText();

      if (name.equals("")) {
        JOptionPane.showMessageDialog(null, "Name cannot be empty");
        return;
      }

      if (age.equals("")) {
        JOptionPane.showMessageDialog(null, "Age cannot be empty");
        return;
      }

      if (gender.equals("")) {
        JOptionPane.showMessageDialog(null, "Gender is not selected");
        return;
      }

      if (carCompany.equals("")) {
        JOptionPane.showMessageDialog(null, "Car Company cannot be empty");
        return;
      }

      if (carModel.equals("")) {
        JOptionPane.showMessageDialog(null, "Car Model cannot be empty");
        return;
      }

      if (phone.equals("")) {
        JOptionPane.showMessageDialog(null, "Phone cannot be empty");
        return;
      }

      if ((phone.length() < 10) || (phone.length() > 15)) {
        JOptionPane.showMessageDialog(null, "Phone is Invalid");
        return;
      }

      if (location.equals("")) {
        JOptionPane.showMessageDialog(null, "Location cannot be empty");
        return;
      }

      try {
        Conn c = new Conn();

        String query = "insert into driver values('" + name + "', '" + age + "', '" + gender + "', '" + carCompany + "', '" + carModel + "', '" + phone + "', '" + availability + "', '" + location + "')";

        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "New Driver Added Successfully");

        setVisible(false);

      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (ae.getSource() == cancel) {
      setVisible(false);
    }
  }
}
