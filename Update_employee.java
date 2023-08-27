import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Update_employee {
    JFrame employeeFrame = new JFrame("Add Employee");

    JLabel employeeidLabel, nameJLabel, ageJLabel, genderJLabel, phoneJLabel, jobJLabel, sellaryLabel, adharLabel,
            emaiLabel;
    JTextField employeeidTextField, namTextField, ageTextField, phonTextField, sellaryTextField, adharTextField,
            emailTextField;
    JButton saveUpdateEmployeButton = new JButton("Save");
    JButton cancelEmployeeButton = new JButton("Cansel");
    JButton searchButton = new JButton("Search");

    JRadioButton gendeRadioButton1, male, female;
    ButtonGroup radiobutton;

    JComboBox jobComboBox;

    Update_employee() {
        employeeFrame.setBounds(400, 100, 600, 500);
        employeeFrame.setVisible(true);
        employeeFrame.setLayout(null);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        employeeFrame.add(male);
        employeeFrame.add(female);
        radiobutton = new ButtonGroup();
        radiobutton.add(male);
        radiobutton.add(female);

        String jobList[] = { "Chef", "Watior", "Driver", "Maneger", "Receptionist" };
        jobComboBox = new JComboBox<>(jobList);
        employeeFrame.add(jobComboBox);

        // Lables
        employeeidLabel = new JLabel("Employee Id :");
        nameJLabel = new JLabel("Name :");
        ageJLabel = new JLabel("Age :");
        genderJLabel = new JLabel("Gender :");
        phoneJLabel = new JLabel("Phone :");
        jobJLabel = new JLabel("Job :");
        sellaryLabel = new JLabel("Sallary :");
        adharLabel = new JLabel("Adhar :");
        emaiLabel = new JLabel("Email :");

        // Textfild's
        employeeidTextField = new JTextField();
        namTextField = new JTextField();
        ageTextField = new JTextField();
        phonTextField = new JTextField();
        sellaryTextField = new JTextField();
        adharTextField = new JTextField();
        emailTextField = new JTextField();

        // set Bounds
        employeeidLabel.setBounds(100, 50, 100, 30);
        employeeidTextField.setBounds(200, 50, 100, 30);
        searchButton.setBounds(350, 50, 100, 30);

        nameJLabel.setBounds(100, 100, 100, 30);
        namTextField.setBounds(200, 100, 100, 30);

        ageJLabel.setBounds(350, 100, 60, 30);
        ageTextField.setBounds(430, 100, 100, 30);

        genderJLabel.setBounds(100, 150, 100, 30);
        male.setBounds(180, 150, 60, 30);
        female.setBounds(250, 150, 80, 30);

        phoneJLabel.setBounds(350, 150, 100, 30);
        phonTextField.setBounds(430, 150, 100, 30);

        jobJLabel.setBounds(100, 200, 100, 30);
        jobComboBox.setBounds(200, 200, 100, 30);

        sellaryLabel.setBounds(350, 200, 100, 30);
        sellaryTextField.setBounds(430, 200, 100, 30);

        adharLabel.setBounds(100, 250, 100, 30);
        adharTextField.setBounds(200, 250, 100, 30);

        emaiLabel.setBounds(350, 250, 100, 30);
        emailTextField.setBounds(430, 250, 100, 30);

        cancelEmployeeButton.setBounds(150, 300, 100, 30);
        saveUpdateEmployeButton.setBounds(400, 300, 100, 30);

        // add farme to lables
        employeeFrame.add(employeeidLabel);
        employeeFrame.add(nameJLabel);
        employeeFrame.add(ageJLabel);
        employeeFrame.add(genderJLabel);
        employeeFrame.add(phoneJLabel);
        employeeFrame.add(jobJLabel);
        employeeFrame.add(sellaryLabel);
        employeeFrame.add(adharLabel);
        employeeFrame.add(emaiLabel);

        // Add textfilds
        employeeFrame.add(employeeidTextField);
        employeeFrame.add(namTextField);
        employeeFrame.add(ageTextField);
        employeeFrame.add(phonTextField);
        employeeFrame.add(sellaryTextField);
        employeeFrame.add(adharTextField);
        employeeFrame.add(emailTextField);

        employeeFrame.add(searchButton);
        employeeFrame.add(cancelEmployeeButton);
        employeeFrame.add(saveUpdateEmployeButton);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "SELECT * FROM hotel_employee WHERE employee_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, employeeidTextField.getText());

                    ResultSet resultSet = preparedStatement.executeQuery();

                    if (resultSet.next()) {
                        namTextField.setText(resultSet.getString("employee_name"));
                        ageTextField.setText(resultSet.getString("employee_age"));
                        String gender = resultSet.getString("employee_gender");
                        if ("Male".equals(gender)) {
                            male.setSelected(true);
                        } else if ("Female".equals(gender)) {
                            female.setSelected(true);
                        }

                        String job = (String) jobComboBox.getSelectedItem();
                        phonTextField.setText(resultSet.getString("employee_phone"));
                        jobComboBox.setSelectedItem(resultSet.getString("employee_job"));
                        sellaryTextField.setText(resultSet.getString("employee_sellary"));
                        adharTextField.setText(resultSet.getString("employee_adhar"));
                        emailTextField.setText(resultSet.getString("employee_email"));
                    } else {
                        JOptionPane.showMessageDialog(employeeFrame, "Employee not found.");
                    }

                    resultSet.close();
                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(employeeFrame, "Error: Unable to search for the Employee.");
                }
            }
        });

        saveUpdateEmployeButton.addActionListener(new ActionListener() {
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

                String job = (String) jobComboBox.getSelectedItem();

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);

                    String query = "UPDATE hotel_employee SET employee_name=?, employee_age=?, employee_gender=?, employee_phone=?, employee_job=?, employee_sellary=?, employee_adhar=?, employee_email=? WHERE employee_id=?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, namTextField.getText());
                    preparedStatement.setString(2, ageTextField.getText());
                    preparedStatement.setString(3, selectedGender);
                    preparedStatement.setString(4, phonTextField.getText());
                    preparedStatement.setString(5, job);
                    preparedStatement.setString(6, sellaryTextField.getText());
                    preparedStatement.setString(7, adharTextField.getText());
                    preparedStatement.setString(8, emailTextField.getText());
                    preparedStatement.setString(9, employeeidTextField.getText());

                    int rowsUpdated = preparedStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(employeeFrame, "Employee updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(employeeFrame, "Error: Unable to update the Employee.");
                    }

                    preparedStatement.close();
                    connection.close();
                } catch (ClassNotFoundException | SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(employeeFrame, "Error: Unable to update the customer.");
                }
            }
        });
    }

}
