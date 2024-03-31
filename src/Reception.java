import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reception extends JFrame implements ActionListener {
  JButton newCustomer, searchRoom, allEmployee, managerInfo, customers,  checkout, update, roomStatus, pickup, driverStatus, back;

  Reception() {
    setTitle("HMS - Reception");
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    newCustomer = new JButton("New Customer Form");
    newCustomer.setBounds(15, 30, 200, 30);
    newCustomer.setBackground(Color.BLACK);
    newCustomer.setForeground(Color.WHITE);
    newCustomer.addActionListener(this);
    add(newCustomer);

    searchRoom = new JButton("Search Rooms");
    searchRoom.setBounds(15, 75, 200, 30);
    searchRoom.setBackground(Color.BLACK);
    searchRoom.setForeground(Color.WHITE);
    searchRoom.addActionListener(this);
    add(searchRoom);

    allEmployee = new JButton("All Employees");
    allEmployee.setBounds(15, 120, 200, 30);
    allEmployee.setBackground(Color.BLACK);
    allEmployee.setForeground(Color.WHITE);
    allEmployee.addActionListener(this);
    add(allEmployee);

    managerInfo = new JButton("Manager Info");
    managerInfo.setBounds(15, 165, 200, 30);
    managerInfo.setBackground(Color.BLACK);
    managerInfo.setForeground(Color.WHITE);
    managerInfo.addActionListener(this);
    add(managerInfo);

    customers = new JButton("Customer Info");
    customers.setBounds(15, 210, 200, 30);
    customers.setBackground(Color.BLACK);
    customers.setForeground(Color.WHITE);
    customers.addActionListener(this);
    add(customers);

    checkout = new JButton("Checkout");
    checkout.setBounds(15, 255, 200, 30);
    checkout.setBackground(Color.BLACK);
    checkout.setForeground(Color.WHITE);
    checkout.addActionListener(this);
    add(checkout);

    update = new JButton("Update Status");
    update.setBounds(15, 300, 200, 30);
    update.setBackground(Color.BLACK);
    update.setForeground(Color.WHITE);
    update.addActionListener(this);
    add(update);

    roomStatus = new JButton("Update Room Status");
    roomStatus.setBounds(15, 345, 200, 30);
    roomStatus.setBackground(Color.BLACK);
    roomStatus.setForeground(Color.WHITE);
    roomStatus.addActionListener(this);
    add(roomStatus);

    pickup = new JButton("Pickup Service");
    pickup.setBounds(15, 390, 200, 30);
    pickup.setBackground(Color.BLACK);
    pickup.setForeground(Color.WHITE);
    pickup.addActionListener(this);
    add(pickup);

    driverStatus = new JButton("Update Driver Status");
    driverStatus.setBounds(15, 435, 200, 30);
    driverStatus.setBackground(Color.BLACK);
    driverStatus.setForeground(Color.WHITE);
    driverStatus.addActionListener(this);
    add(driverStatus);

    back = new JButton("Back");
    back.setBounds(15, 480, 200, 30);
    back.setBackground(Color.BLACK);
    back.setForeground(Color.WHITE);
    back.addActionListener(this);
    add(back);

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
    JLabel image = new JLabel(i1);
    image.setBounds(250, 30, 500, 480);
    add(image);

    setBounds(360, 200, 800, 580);
    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == newCustomer) {
      setVisible(false);
      new AddCustomer();
    } else if (ae.getSource() == searchRoom) {
      setVisible(false);
      new SearchRoom();
    } else if (ae.getSource() == allEmployee) {
      setVisible(false);
      new EmployeeInfo();
    } else if (ae.getSource() == managerInfo) {
      setVisible(false);
      new ManagerInfo();
    } else if (ae.getSource() == customers) {
      setVisible(false);
      new CustomerInfo();
    } else if (ae.getSource() == update) {
      setVisible(false);
      new UpdateCheck();
    } else if (ae.getSource() == roomStatus) {
      setVisible(false);
      new UpdateRoom();
    } else if (ae.getSource() == pickup) {
      setVisible(false);
      new Pickup();
    } else if (ae.getSource() == checkout) {
      setVisible(false);
      new Checkout();
    } else if (ae.getSource() == driverStatus) {
      setVisible(false);
      new UpdateDriver();
    } else if (ae.getSource() == back) {
      setVisible(false);
    }
  }
}
