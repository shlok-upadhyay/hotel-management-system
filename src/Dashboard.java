import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
  Dashboard(boolean isAdmin) {
    setTitle("HMS - Dashboard");
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setBounds(0, 0, 1550, 1000);
    setLayout(null);

    ImageIcon logo = new ImageIcon(ClassLoader.getSystemResource("icons/logo.png"));
    setIconImage(logo.getImage());

    ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/third.jpg"));
    Image i2 = i1.getImage().getScaledInstance(1550, 1000, Image.SCALE_DEFAULT);
    ImageIcon i3 = new ImageIcon(i2);
    JLabel image = new JLabel(i3);
    image.setBounds(0, 0, 1550, 1000);
    add(image);

    JLabel text = new JLabel("THE ROYAL ORCHID GROUP WELCOMES YOU");
    text.setBounds(300, 80, 1000, 50);
    text.setFont(new Font("Tahoma", Font.PLAIN, 45));
    text.setForeground(Color.WHITE);
    image.add(text);

    JMenuBar mb = new JMenuBar();
    mb.setBounds(0, 0, 1550, 30);
    image.add(mb);

    JMenu hotel = new JMenu("HOTEL MANAGEMENT");
    hotel.setForeground(Color.RED);
    mb.add(hotel);

    JMenuItem reception = new JMenuItem("RECEPTION");
    reception.addActionListener(this);
    hotel.add(reception);

    JMenu admin = new JMenu("ADMIN");
    admin.setForeground(Color.BLUE);
    mb.add(admin);

    JMenuItem addEmployee = new JMenuItem("ADD EMPLOYEE");
    addEmployee.addActionListener(this);
    admin.add(addEmployee);

    JMenuItem addRoom = new JMenuItem("ADD ROOM");
    addRoom.addActionListener(this);
    admin.add(addRoom);

    JMenuItem addDriver = new JMenuItem("ADD DRIVER");
    addDriver.addActionListener(this);
    admin.add(addDriver);

    JMenuItem removeEmployee = new JMenuItem("REMOVE EMPLOYEE");
    removeEmployee.addActionListener(this);
    admin.add(removeEmployee);

    JMenuItem removeDriver = new JMenuItem("REMOVE DRIVER");
    removeDriver.addActionListener(this);
    admin.add(removeDriver);

    JMenu logout = new JMenu("LOGOUT");
    logout.setForeground(Color.BLACK);
    mb.add(logout);

    JMenuItem logoutOnly = new JMenuItem("LOGOUT");
    logoutOnly.addActionListener(this);
    logout.add(logoutOnly);

    JMenuItem logoutExit = new JMenuItem("LOGOUT & EXIT");
    logoutExit.addActionListener(this);
    logout.add(logoutExit);

    if (!isAdmin) {
      admin.setEnabled(false);
    }

    setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent ae) {
    if (ae.getActionCommand().equals("ADD EMPLOYEE")) {
      new AddEmployee();
    } else if (ae.getActionCommand().equals("ADD ROOM")) {
      new AddRoom();
    } else if (ae.getActionCommand().equals("ADD DRIVER")) {
      new AddDriver();
    } else if (ae.getActionCommand().equals("REMOVE EMPLOYEE")) {
      new RemoveEmployee();
    } else if (ae.getActionCommand().equals("REMOVE DRIVER")) {
      new RemoveDriver();
    } else if (ae.getActionCommand().equals("RECEPTION")) {
      new Reception();
    } else if (ae.getActionCommand().equals("LOGOUT")) {
      setVisible(false);
      new Login();
    } else if (ae.getActionCommand().equals("LOGOUT & EXIT")) {
      setVisible(false);
      System.exit(0);
    }
  }
}
