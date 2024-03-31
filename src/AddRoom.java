import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddRoom extends JFrame implements ActionListener {
  JTextField tfroomno, tfprice;
  JComboBox<String> availableCombo, cleanCombo, typeCombo;
  JButton addRoom, cancel;

  AddRoom() {
    setTitle("HMS - Add New Room");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    JLabel heading = new JLabel("Add Room");
    heading.setFont(new Font("Tahoma", Font.BOLD, 18));
    heading.setBounds(150, 20, 200, 20);
    add(heading);

    JLabel lblroomno = new JLabel("Room Number");
    lblroomno.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblroomno.setBounds(60, 80, 120, 30);
    add(lblroomno);

    tfroomno = new JTextField();
    tfroomno.setBounds(200, 80, 150, 30);
    add(tfroomno);

    JLabel lblavailable = new JLabel("Availability");
    lblavailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblavailable.setBounds(60, 130, 120, 30);
    add(lblavailable);

    String availableOptions[] = { "Available", "Occupied" };
    availableCombo = new JComboBox<>(availableOptions);
    availableCombo.setBounds(200, 130, 150, 30);
    availableCombo.setBackground(Color.WHITE);
    add(availableCombo);

    JLabel lblclean = new JLabel("Cleaning Status");
    lblclean.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblclean.setBounds(60, 180, 120, 30);
    add(lblclean);

    String cleanOptions[] = { "Cleaned", "Dirty" };
    cleanCombo = new JComboBox<>(cleanOptions);
    cleanCombo.setBounds(200, 180, 150, 30);
    cleanCombo.setBackground(Color.WHITE);
    add(cleanCombo);
    
    JLabel lbltype = new JLabel("Bed Type");
    lbltype.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lbltype.setBounds(60, 230, 120, 30);
    add(lbltype);

    String typeOptions[] = { "Single Bed", "Double Bed" };
    typeCombo = new JComboBox<>(typeOptions);
    typeCombo.setBounds(200, 230, 150, 30);
    typeCombo.setBackground(Color.WHITE);
    add(typeCombo);

    JLabel lblprice = new JLabel("Price");
    lblprice.setFont(new Font("Tahoma", Font.PLAIN, 16));
    lblprice.setBounds(60, 280, 120, 30);
    add(lblprice);

    tfprice = new JTextField();
    tfprice.setBounds(200, 280, 150, 30);
    add(tfprice);

    addRoom = new JButton("Add Room");
    addRoom.setForeground(Color.WHITE);
    addRoom.setBackground(Color.BLACK);
    addRoom.setBounds(60, 350, 130, 30);
    addRoom.addActionListener(this);
    add(addRoom);

    cancel = new JButton("Cancel");
    cancel.setForeground(Color.WHITE);
    cancel.setBackground(Color.BLACK);
    cancel.setBounds(220, 350, 130, 30);
    cancel.addActionListener(this);
    add(cancel);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/twelve.jpg"));
    JLabel image = new JLabel(i1);
    image.setBounds(400, 50, 500, 300);
    add(image);

    setBounds(300, 200, 940, 470);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == addRoom) {
      String roomno = tfroomno.getText();
      String availability = availableCombo.getSelectedItem().toString();
      String status = cleanCombo.getSelectedItem().toString();
      String type = typeCombo.getSelectedItem().toString();
      String price = tfprice.getText();

      if (roomno.equals("")) {
        JOptionPane.showMessageDialog(null, "Room Number cannot be empty");
        return;
      }

      if (price.equals("")) {
        JOptionPane.showMessageDialog(null, "Price cannot be empty");
        return;
      }

      try {
        Conn c = new Conn();

        String query = "insert into room values('" + roomno + "', '" + availability + "', '" + status + "', '" + type + "', '" + price + "')";

        c.s.executeUpdate(query);

        JOptionPane.showMessageDialog(null, "New Room Added Successfully");

        setVisible(false);

      } catch (Exception e) {
        e.printStackTrace();
      }

    } else if (ae.getSource() == cancel) {
      setVisible(false);
    }
  }
}
