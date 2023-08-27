// import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Delete_employee {

    JButton searchButton = new JButton("Search");
    JButton delateButton = new JButton("Delate");
    JLabel employeeIdLabel,employeeNameLabel;
    JTextField employeeIdTextField ,employeeNameTextField;
    Delete_employee(){
        JFrame delateFrJFrame = new JFrame("Delate customer");
        delateFrJFrame.setBounds(400, 200, 600, 500);
        delateFrJFrame.setLayout(null);
        delateFrJFrame.setVisible(true);

        employeeIdLabel = new JLabel("Employee Id :");
        employeeIdLabel.setBounds(100, 50, 100,30);
        employeeNameLabel = new JLabel("Employee Name: ");
        employeeNameLabel.setBounds(100, 100, 100,30);

        searchButton.setBounds(400, 50, 100, 30);
        delateButton.setBounds(250, 150, 100, 30);

        employeeIdTextField = new JTextField();
        employeeIdTextField.setBounds(250, 50, 100, 30);
        employeeNameTextField = new JTextField();
        employeeNameTextField.setBounds(250, 100, 100, 30);

        delateFrJFrame.add(employeeIdLabel);
        delateFrJFrame.add(employeeIdTextField);
        delateFrJFrame.add(employeeNameLabel);
        delateFrJFrame.add(employeeNameTextField);
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

                    String query = "DELETE FROM `hotel_employee` WHERE employee_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, employeeIdTextField.getText());
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(delateFrJFrame, "Employee Delete successfully.");
                    } else {
                        JOptionPane.showMessageDialog(delateFrJFrame, "Error: Unable to Delete Employee.");
                    }
                   

                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // Handle database errors and display an error message
                    JOptionPane.showMessageDialog(delateFrJFrame, "Error: not delete the Employee.");
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

                    String query = "SELECT * FROM hotel_employee WHERE employee_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, employeeIdTextField.getText());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        employeeNameTextField.setText(resultSet.getString("employee_name"));
                    } else {
                        JOptionPane.showMessageDialog(delateFrJFrame, "Employee not found.");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    // JOptionPane.showMessageDialog(frame, idTextField.getText());
                    JOptionPane.showMessageDialog(delateFrJFrame, "Error: Unable to search for the Employee.");
                }
            
            }
            
        });

    }
    
    
}
