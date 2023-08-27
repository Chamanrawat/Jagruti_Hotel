import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Add_employee implements ActionListener {
    JFrame employeeFrame = new JFrame("Add Employee");

    JLabel nameJLabel, ageJLabel, genderJLabel, phoneJLabel, jobJLabel, sellaryLabel, adharLabel, emaiLabel;
    JTextField namTextField, ageTextField, phonTextField, sellaryTextField, adharTextField, emailTextField;
    JButton saveEmployeButton = new JButton("Save");
    JButton cancelEmployeeButton = new JButton("Cansel");

    JRadioButton male, female;
    ButtonGroup radiobutton;

    JComboBox jobListJcombobox;

    String name, adhar, email;
    Integer age, phone, sellary;

    Add_employee() {
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

        // Lables
        nameJLabel = new JLabel("Name :");
        ageJLabel = new JLabel("Age :");
        genderJLabel = new JLabel("Gender :");
        phoneJLabel = new JLabel("Phone :");
        jobJLabel = new JLabel("Job :");
        sellaryLabel = new JLabel("Sallary :");
        adharLabel = new JLabel("Adhar :");
        emaiLabel = new JLabel("Email :");

        // Textfild's

        namTextField = new JTextField();
        ageTextField = new JTextField();
        phonTextField = new JTextField();
        sellaryTextField = new JTextField();
        adharTextField = new JTextField();
        emailTextField = new JTextField();

        // Jcombobox
        String jobList[] = { "Chef", "Watior", "Driver", "Maneger", "Receptionist" };
        jobListJcombobox = new JComboBox<>(jobList);
        employeeFrame.add(jobListJcombobox);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        employeeFrame.add(male);
        employeeFrame.add(female);
        radiobutton = new ButtonGroup();
        radiobutton.add(male);
        radiobutton.add(female);

        // set Bounds

        nameJLabel.setBounds(100, 50, 100, 30);
        namTextField.setBounds(200, 50, 100, 30);

        ageJLabel.setBounds(350, 50, 60, 30);
        ageTextField.setBounds(430, 50, 100, 30);

        genderJLabel.setBounds(100, 100, 100, 30);
        male.setBounds(180, 100, 60, 30);
        female.setBounds(250, 100, 80, 30);

        phoneJLabel.setBounds(350, 100, 100, 30);
        phonTextField.setBounds(430, 100, 100, 30);

        jobJLabel.setBounds(100, 150, 100, 30);
        jobListJcombobox.setBounds(200, 150, 100, 30);

        sellaryLabel.setBounds(350, 150, 100, 30);
        sellaryTextField.setBounds(430, 150, 100, 30);

        adharLabel.setBounds(100, 200, 100, 30);
        adharTextField.setBounds(200, 200, 100, 30);

        emaiLabel.setBounds(350, 200, 100, 30);
        emailTextField.setBounds(430, 200, 100, 30);

        cancelEmployeeButton.setBounds(150, 300, 100, 30);
        saveEmployeButton.setBounds(400, 300, 100, 30);

        // add farme to lables
        employeeFrame.add(nameJLabel);
        employeeFrame.add(ageJLabel);
        employeeFrame.add(genderJLabel);
        employeeFrame.add(phoneJLabel);
        employeeFrame.add(jobJLabel);
        employeeFrame.add(sellaryLabel);
        employeeFrame.add(adharLabel);
        employeeFrame.add(emaiLabel);

        // Add textfilds

        employeeFrame.add(namTextField);
        employeeFrame.add(ageTextField);
        employeeFrame.add(phonTextField);
        employeeFrame.add(sellaryTextField);
        employeeFrame.add(adharTextField);
        employeeFrame.add(emailTextField);

        employeeFrame.add(saveEmployeButton);
        employeeFrame.add(cancelEmployeeButton);

        saveEmployeButton.addActionListener(this);
        cancelEmployeeButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        name = namTextField.getText();
        age = Integer.parseInt(ageTextField.getText());
        String job = (String) jobListJcombobox.getSelectedItem();
        phone = Integer.parseInt(phonTextField.getText());
        String gender = null;
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";

        }

        sellary = Integer.parseInt(sellaryTextField.getText());
        adhar = adharTextField.getText();
        email = emailTextField.getText();

        if (e.getSource() == saveEmployeButton) {

            try {
                String DB_URL = "jdbc:mysql://localhost:3306/jagrutihotel";
                String USER = "root";
                String PASS = "";
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO `hotel_employee`( `employee_name`, `employee_age`, `employee_gender`, `employee_phone`, `employee_job`, `employee_sellary`, `employee_adhar`, `employee_email`) VALUES ('"
                        + name + "', " + age + ", '" + gender + "', " + phone + ", '" + job + "', " + sellary + ", '"
                        + adhar + "', '" + email + "')";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(employeeFrame, "Record insert Successfully");
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            cancelEmployeeButton.addActionListener(event -> {
                employeeFrame.dispose();
            });
        }

    }
}
