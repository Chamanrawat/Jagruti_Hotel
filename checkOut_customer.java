
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;
import java.util.Date;
public class checkOut_customer {

    JLabel nameLabel, checkOutLabel, roomallocateLabel;
    JTextField namTextField;
    JButton checkOutButton = new JButton("Check Out");
    JDateChooser date;

    checkOut_customer() {
        JFrame frame = new JFrame("Check out");
        frame.setSize(800, 400);
        frame.setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 50, 100, 30);
        namTextField = new JTextField();
        namTextField.setBounds(200, 50, 150, 30);

         checkOutLabel = new JLabel("Check Out:");
        checkOutLabel.setBounds(400, 50, 150, 30);
        date=new JDateChooser();
        frame.add(date);
        date.setBounds(500, 50, 150, 30);

       
        checkOutButton.setBounds(500, 100, 150, 30);

        frame.add(nameLabel);
        frame.add(namTextField);
        frame.add(checkOutLabel);
        frame.add(date);
        frame.add(new JLabel()); // Empty label for spacing
        frame.add(checkOutButton);
        frame.setVisible(true);

        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = namTextField.getText();
                String dateTime = ((JTextField) date.getDateEditor().getUiComponent()).getText();

                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement stmt = connection.createStatement();

                    String query = "INSERT INTO `check_out`(  `customer_name`, `check_out`) VALUES ('"
                            + name + "','" + dateTime + "')";
                    int rowsUpdated = stmt.executeUpdate(query);
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(frame, "checkOut successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: the customer check out.");
                    }

                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: Unable to book the room.");
                }
            }
        });

    }
}
