import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class AddEmployee extends JFrame implements ActionListener 
{
  JTextField tfname, tfage, tfphone, tfemail, tfsalary, tfaadhar;
  JRadioButton rbmale, rbfemale;
  JComboBox<String> cbjob;
  JButton submit, cancel;

  AddEmployee() {
    setTitle("HMS - Add New Employee");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Add Employee");
    heading.setBounds(140, 20, 120, 20);
    heading.setFont(new Font("Tahoma", Font.BOLD, 17));
    add(heading);

    JLabel lblname = new JLabel("Name");
    lblname.setBounds(60, 80, 120, 30);
    lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblname);

    tfname = new JTextField();
    tfname.setBounds(200, 80, 150, 30);
    add(tfname);

    JLabel lblage = new JLabel("Age");
    lblage.setBounds(60, 130, 120, 30);
    lblage.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblage);

    tfage = new JTextField();
    tfage.setBounds(200, 130, 150, 30);
    add(tfage);

    JLabel lblgender = new JLabel("Gender");
    lblgender.setBounds(60, 180, 120, 30);
    lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblgender);

    rbmale = new JRadioButton("Male");
    rbmale.setBounds(200, 180, 70, 30);
    rbmale.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rbmale.setBackground(Color.WHITE);
    add(rbmale);

    rbfemale = new JRadioButton("Female");
    rbfemale.setBounds(280, 180, 70, 30);
    rbfemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
    rbfemale.setBackground(Color.WHITE);
    add(rbfemale);

    ButtonGroup bg = new ButtonGroup();
    bg.add(rbmale);
    bg.add(rbfemale);

    JLabel lbljob = new JLabel("Job");
    lbljob.setBounds(60, 230, 120, 30);
    lbljob.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lbljob);

    String jobOptions[] = { "Front Desk Clerk", "Porters", "Housekeeping", "Kitchen Staff", "Room Service", "Chef", "Waiter/Waitress", "Manager", "Accountant" };
    cbjob = new JComboBox<>(jobOptions);
    cbjob.setBounds(200, 230, 150, 30);
    cbjob.setBackground(Color.WHITE);
    add(cbjob);

    JLabel lblsalary = new JLabel("Salary");
    lblsalary.setBounds(60, 280, 120, 30);
    lblsalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblsalary);

    tfsalary = new JTextField();
    tfsalary.setBounds(200, 280, 150, 30);
    add(tfsalary);

    JLabel lblphone = new JLabel("Phone");
    lblphone.setBounds(60, 330, 120, 30);
    lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblphone);

    tfphone = new JTextField();
    tfphone.setBounds(200, 330, 150, 30);
    add(tfphone);

    JLabel lblemail = new JLabel("Email");
    lblemail.setBounds(60, 380, 120, 30);
    lblemail.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblemail);

    tfemail = new JTextField();
    tfemail.setBounds(200, 380, 150, 30);
    add(tfemail);

    JLabel lblaadhar = new JLabel("Aadhar");
    lblaadhar.setBounds(60, 430, 120, 30);
    lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
    add(lblaadhar);

    tfaadhar = new JTextField();
    tfaadhar.setBounds(200, 430, 150, 30);
    add(tfaadhar);

    submit = new JButton("Submit");
    submit.setBackground(Color.BLACK);
    submit.setForeground(Color.WHITE);
    submit.setBounds(60, 500, 130, 30);
    submit.addActionListener(this);
    add(submit);

    cancel = new JButton("Cancel");
    cancel.setBackground(Color.BLACK);
    cancel.setForeground(Color.WHITE);
    cancel.setBounds(220, 500, 130, 30);
    cancel.addActionListener(this);
    add(cancel);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
    Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(380, 60, 450, 380);
    add(image);

    setBounds(340, 200, 850, 620);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == submit) {
      String name = tfname.getText();
      String age = tfage.getText();
      String salary = tfsalary.getText();
      String phone = tfphone.getText();
      String email = tfemail.getText();
      String aadhar = tfaadhar.getText();
      String gender = (rbmale.isSelected()) ? "Male" : ((rbfemale.isSelected()) ? "Female" : "");
      String job = cbjob.getSelectedItem().toString();

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

      if (salary.equals("")) {
        JOptionPane.showMessageDialog(null, "Salary cannot be empty");
        return;
      }

      if (phone.equals("")) {
        JOptionPane.showMessageDialog(null, "Phone cannot be empty");
        return;
      }

      if ((phone.length() < 10) || (phone.length() > 15)) {
        JOptionPane.showMessageDialog(null, "Phone is invalid");
        return;
      }

      if (email.equals("")) {
        JOptionPane.showMessageDialog(null, "Email cannot be empty");
        return;
      }

      if (!((email.contains("@")) && ((email.contains(".com")) || (email.contains(".in"))))) {
        JOptionPane.showMessageDialog(null, "Email is invalid");
        return;
      }

      if (aadhar.equals("")) {
        JOptionPane.showMessageDialog(null, "Aadhar cannot be empty");
        return;
      }

      if (aadhar.length() != 12) {
        JOptionPane.showMessageDialog(null, "Aadhar is invalid");
        return;
      }

      try {
        Conn c = new Conn();

        String query = "insert into employee values('" + name + "', '" + age + "', '" + gender + "', '" + job + "', '" + salary + "', '" + phone + "', '" + email + "', '" + aadhar + "')";

        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "Employee added successfully");

        setVisible(false);

      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (ae.getSource() == cancel) {
      setVisible(false);
    }
  }
}
