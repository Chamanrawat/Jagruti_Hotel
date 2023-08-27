import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.*;
import com.toedter.calendar.JDateChooser;

class add_customer implements ActionListener {
    JFrame frame = new JFrame("Add Customer");
    JButton saveAddCustomer = new JButton("Save");
    JButton cancel = new JButton("Cancel");

    JLabel nameLabel, phoneLabel, genderLabel, countryLabel, allocatLabel, checkintimLabel, depositLabel, freeLabel;
    JTextField nameTextField, phoneTextField, countryTextField, allocateroomTextField, checkTextField,
            depositTextField;
    String name, gender, country;
    Integer phone, allocateroom, checkintime, deposit;

    JRadioButton  male, female;
    ButtonGroup radiobutton;
    JDateChooser date;

    add_customer() {
        frame.setBounds(500, 200, 600, 600);
        frame.setVisible(true);

        male = new JRadioButton("Male");
        female = new JRadioButton("Female");
        frame.add(male);
        frame.add(female);
        radiobutton = new ButtonGroup();
        radiobutton.add(male);
        radiobutton.add(female);

        // JLables
        nameLabel = new JLabel("Name :");
        phoneLabel = new JLabel("Phone :");
        genderLabel = new JLabel("Gender :");
        countryLabel = new JLabel("Country");
        allocatLabel = new JLabel("Allocate Room :");
        checkintimLabel = new JLabel("Check in Time :");
        depositLabel = new JLabel("deposit :");
        freeLabel = new JLabel("");

        // TextAeras
        nameTextField = new JTextField();
        phoneTextField = new JTextField();
        // genderTextField = new JTextField("GEnder");
        countryTextField = new JTextField();
        allocateroomTextField = new JTextField();
        checkTextField = new JTextField();
        depositTextField = new JTextField();

        // Buttons
        saveAddCustomer.setBounds(200, 400, 80, 30);
        cancel.setBounds(100, 400, 80, 30);

        // SetBounds for labels
        nameLabel.setBounds(100, 50, 150, 40);
        phoneLabel.setBounds(100, 100, 150, 40);
        genderLabel.setBounds(100, 150, 150, 40);
        countryLabel.setBounds(100, 200, 150, 40);
        allocatLabel.setBounds(100, 250, 150, 40);
        checkintimLabel.setBounds(100, 300, 150, 40);
        depositLabel.setBounds(100, 350, 150, 40);

        // SetBounds for TextFiels
        nameTextField.setBounds(200, 50, 150, 30);
        phoneTextField.setBounds(200, 100, 150, 30);
        // radiobuttton
        male.setBounds(200, 150, 100, 30);
        female.setBounds(300, 150, 140, 30);

        countryTextField.setBounds(200, 200, 150, 30);
        allocateroomTextField.setBounds(200, 250, 150, 30);
        date=new JDateChooser();
        frame.add(date);

       date.setBounds(200, 300, 150, 30);
        depositTextField.setBounds(200, 350, 150, 30);

        frame.add(nameLabel);
        frame.add(nameTextField);
        frame.add(phoneLabel);
        frame.add(phoneTextField);
        frame.add(genderLabel);
        // frame.add(genderTextField);

        frame.add(countryLabel);

        frame.add(countryTextField);
        frame.add(allocatLabel);
        frame.add(allocateroomTextField);
        frame.add(checkintimLabel);
        frame.add(checkTextField);
        frame.add(depositLabel);
        frame.add(depositTextField);
        frame.add(cancel);
        frame.add(saveAddCustomer);
        frame.add(freeLabel);

        saveAddCustomer.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {

        name = nameTextField.getText();
        phone = Integer.parseInt(phoneTextField.getText());
        String gender = "";
        if (male.isSelected()) {
            gender = "Male";
        } else if (female.isSelected()) {
            gender = "Female";

        }
        country = countryTextField.getText();
        allocateroom = Integer.parseInt(allocateroomTextField.getText());
        String dateTime = ((JTextField) date.getDateEditor().getUiComponent()).getText();
        //checkintime = Integer.parseInt(checkTextField.getText());
        deposit = Integer.parseInt(depositTextField.getText());

        if (e.getSource() == saveAddCustomer) {

            try {
                String DB_URL = "jdbc:mysql://localhost:3306/jagrutihotel";
                String USER = "root";
                String PASS = "";
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                Statement stmt = conn.createStatement();
                String sql = "INSERT INTO `customer`(`customer_name`, `customer_phone`, `customer_gender`, `customer_country`, `customer_allocateroom`, `customer_checkintime`, `customer_deposit`) values('"
                        + name + "', " + phone + ", '" + gender + "' , '" + country + "', " + allocateroom + ",'"
                        + dateTime + "'," + deposit + ")";
                stmt.executeUpdate(sql);
                JOptionPane.showMessageDialog(frame, "Record insert Successfully");
            } catch (Exception e1) {
                e1.printStackTrace();
            }

        }
         cancel.addActionListener(event->{
            frame.dispose();
        });
       
    }
    
}
