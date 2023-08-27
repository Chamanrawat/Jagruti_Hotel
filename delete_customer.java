// import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class delete_customer {

    JButton searchButton = new JButton("Search");
    JButton delateButton = new JButton("Delate");
    JLabel customerIdLabel,customerNamLabel;
    JTextField customerIdTextField ,customerNameTextField;
    delete_customer(){
        JFrame delateFrJFrame = new JFrame("Delate customer");
        delateFrJFrame.setBounds(400, 200, 600, 500);
        delateFrJFrame.setLayout(null);
        delateFrJFrame.setVisible(true);

        customerIdLabel = new JLabel("Customer Id :");
        customerIdLabel.setBounds(100, 50, 100,30);
        customerNamLabel = new JLabel("Customer Name: ");
        customerNamLabel.setBounds(100, 100, 100,30);

        searchButton.setBounds(400, 50, 100, 30);
        delateButton.setBounds(250, 150, 100, 30);

        customerIdTextField = new JTextField();
        customerIdTextField.setBounds(250, 50, 100, 30);
        customerNameTextField = new JTextField();
        customerNameTextField.setBounds(250, 100, 100, 30);

        delateFrJFrame.add(customerIdLabel);
        delateFrJFrame.add(customerIdTextField);
        delateFrJFrame.add(customerNamLabel);
        delateFrJFrame.add(customerNameTextField);
        delateFrJFrame.add(searchButton);
        delateFrJFrame.add(delateButton);

        delateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                     Statement statement = connection.createStatement();

                    String query = "DELETE FROM `customer` WHERE customer_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, customerIdTextField.getText());
                   // preparedStatement.setString(1, customerNameTextField.getText());
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(delateFrJFrame, "Customer Delete successfully.");
                    } else {
                        JOptionPane.showMessageDialog(delateFrJFrame, "Error: Unable to Delete customer.");
                    }
                   

                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // Handle database errors and display an error message
                    JOptionPane.showMessageDialog(delateFrJFrame, "Error: not delete the customer.");
                }
            }
            
        });

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "SELECT * FROM customer WHERE customer_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, customerIdTextField.getText());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        customerNameTextField.setText(resultSet.getString("customer_name"));
                    } else {
                        JOptionPane.showMessageDialog(delateFrJFrame, "Customer not found.");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // JOptionPane.showMessageDialog(frame, idTextField.getText());
                    JOptionPane.showMessageDialog(delateFrJFrame, "Error: Unable to search for the customer.");
                }
            
            }
            
        });

    }
    
    
}
