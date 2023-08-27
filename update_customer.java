import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import com.toedter.calendar.JDateChooser;
import javax.swing.*;

public class update_customer {
    JFrame frame = new JFrame("Update Customer");
    JButton saveUpdateCustomer = new JButton("Save");
    JButton cancelUpdateButton = new JButton("Cancel");
    JButton searchButton = new JButton("Search");
    JLabel customerIdJLabel, nameLabel, phoneLabel, genderLabel, countryLabel, allocatLabel, checkintimLabel,
            depositLabel, freeLabel;
    JTextField idTextField, nameTextField, phoneTextField, genderTextField, countryTextField, allocateroomTextField,
            checkTextField,
            depositTextField;
    String name, gender, country;
    Integer phone, allocateroom, checkintime, deposit;

    JRadioButton male, female;
    ButtonGroup radiobutton;
    JDateChooser date;

    update_customer() {
        frame.setBounds(400, 200, 600, 500);
        frame.setVisible(true);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        radiobutton = new ButtonGroup();
        radiobutton.add(male);
        radiobutton.add(female);

        frame.add(male);
        frame.add(female);

        date = new JDateChooser();
        frame.add(date);

        customerIdJLabel = new JLabel("CustomerId");
        nameLabel = new JLabel("Name :");
        phoneLabel = new JLabel("Phone :");
        genderLabel = new JLabel("Gender :");
        countryLabel = new JLabel("Country");
        allocatLabel = new JLabel("Allocate Room :");
        checkintimLabel = new JLabel("Check in Time :");
        depositLabel = new JLabel("deposit :");
        freeLabel = new JLabel("");

        idTextField = new JTextField();
        nameTextField = new JTextField();
        phoneTextField = new JTextField();
        countryTextField = new JTextField();
        allocateroomTextField = new JTextField();
        checkTextField = new JTextField();
        depositTextField = new JTextField();

        // Buttons

        searchButton.setBounds(350, 20, 150, 30);

        customerIdJLabel.setBounds(50, 30, 100, 30);
        idTextField.setBounds(150, 30, 100, 30);

        nameLabel.setBounds(50, 90, 100, 30);
        nameTextField.setBounds(150, 90, 100, 30);

        phoneLabel.setBounds(320, 90, 100, 30);
        phoneTextField.setBounds(400, 90, 100, 30);

        genderLabel.setBounds(50, 150, 100, 30);
        male.setBounds(150, 150, 60, 30);
        female.setBounds(220, 150, 60, 30);

        countryLabel.setBounds(320, 150, 100, 30);
        countryTextField.setBounds(400, 150, 100, 30);

        allocatLabel.setBounds(50, 210, 100, 30);
        allocateroomTextField.setBounds(150, 210, 100, 30);

        checkintimLabel.setBounds(320,210,100,30);
        date.setBounds(410,210,100,30);

        depositLabel.setBounds(50, 270, 100, 30);
        depositTextField.setBounds(150, 270, 100, 30);
        saveUpdateCustomer.setBounds(400, 320, 80, 30);
        cancelUpdateButton.setBounds(100, 320, 80, 30);

        frame.add(customerIdJLabel);
        frame.add(idTextField);
        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(phoneLabel);
        frame.add(phoneTextField);
        frame.add(genderLabel);
        frame.add(male);
        frame.add(female);

        frame.add(countryLabel);
        frame.add(countryTextField);
        frame.add(allocatLabel);
        frame.add(allocateroomTextField);
        frame.add(checkintimLabel);
        //frame.add(checkTextField);
       // frame.add(genderTextField);
        frame.add(depositLabel);
        frame.add(depositTextField);

        frame.add(cancelUpdateButton);
        frame.add(saveUpdateCustomer);
        frame.add(searchButton);
        frame.add(freeLabel);

        // saveUpdateCustomer.addActionListener(this);
        // searchButton.addActionListener(this);

        saveUpdateCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";
        
                String selectedGender = "";
                if (male.isSelected()) {
                    selectedGender = "Male";
                } else if (female.isSelected()) {
                    selectedGender = "Female";
                }
        
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
        
                    String query = "UPDATE customer SET customer_name=?, customer_phone=?, customer_gender=?, customer_country=?, customer_allocateroom=?, customer_checkintime=?,customer_deposit=? WHERE customer_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, nameTextField.getText());
                    preparedStatement.setString(2, phoneTextField.getText());
                    preparedStatement.setString(3, selectedGender);
                    preparedStatement.setString(4, countryTextField.getText());
                    preparedStatement.setString(5, allocateroomTextField.getText());
                    preparedStatement.setString(6, ((JTextField) date.getDateEditor().getUiComponent()).getText());
                    preparedStatement.setString(7, depositTextField.getText());
                    preparedStatement.setString(8, idTextField.getText());
        
                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(frame, "Customer updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Unable to update the customer.");
                    }
        
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: Unable to update the customer.");
                }
            }
        });
        
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";
        
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
        
                    String query = "SELECT * FROM customer WHERE customer_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, idTextField.getText());
        
                    ResultSet resultSet = preparedStatement.executeQuery();
        
                    if (resultSet.next()) {
                        nameTextField.setText(resultSet.getString("customer_name"));
                        phoneTextField.setText(resultSet.getString("customer_phone"));
                        String gender = resultSet.getString("customer_gender");
                        if ("Male".equals(gender)) {
                            male.setSelected(true);
                        } else if ("Female".equals(gender)) {
                            female.setSelected(true);
                        }
                        countryTextField.setText(resultSet.getString("customer_country"));
                        allocateroomTextField.setText(resultSet.getString("customer_allocateroom"));
                        ((JTextField) date.getDateEditor().getUiComponent()).setText(resultSet.getString("customer_checkintime"));
                        depositTextField.setText(resultSet.getString("customer_deposit"));
                    } else {
                        JOptionPane.showMessageDialog(frame, "Customer not found.");
                    }
        
                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error: Unable to search for the customer.");
                }
            }
        });
    }
 
}
