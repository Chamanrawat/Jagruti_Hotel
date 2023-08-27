
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class room_booking {

    JLabel nameLabel, roomTypeLabel, roomallocateLabel;
    JTextField namTextField;
    JButton bookButton = new JButton("Book Now");

    room_booking() {
        JFrame frame = new JFrame("Hotel Room Booking");
        frame.setSize(800, 400);
        frame.setLayout(null);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(100, 50, 100, 30);
        namTextField = new JTextField();
        namTextField.setBounds(200, 50, 150, 30);

        JLabel roomTypeLabel = new JLabel("Room Type:");
        roomTypeLabel.setBounds(400, 50, 150, 30);
        JComboBox<String> roomTypeComboBox = new JComboBox<>(new String[] { "Single", "Double", "Suite" });
        roomTypeComboBox.setBounds(500, 50, 150, 30);

        roomallocateLabel = new JLabel("Allocate ROOM");
        roomallocateLabel.setBounds(100, 100, 150, 30);

        JComboBox<String> roomAllocateComboBox = new JComboBox<>(
                new String[] { "101", "102", "103", "104", "105", "106", "107", "108", "109", "110" });
        roomAllocateComboBox.setBounds(200, 100, 150, 30);

        bookButton.setBounds(500, 100, 150, 30);

        frame.add(nameLabel);
        frame.add(namTextField);
        frame.add(roomTypeLabel);
        frame.add(roomTypeComboBox);
        frame.add(roomallocateLabel);
        frame.add(roomAllocateComboBox);
        frame.add(new JLabel()); // Empty label for spacing
        frame.add(bookButton);
        frame.setVisible(true);

        bookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Perform booking logic here
                String name = namTextField.getText();
                String roomType = (String) roomTypeComboBox.getSelectedItem();
                String roomAlllocate = (String) roomAllocateComboBox.getSelectedItem();

                String url = "jdbc:mysql://localhost:3306/jagrutihotel";
                String username = "root";
                String password = "";

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement stmt = connection.createStatement();

                    String query = "INSERT INTO `room`( `room_customerName`, `room_allocate`, `room_type`) VALUES ('"
                            + name + "','" + roomAlllocate + "','" + roomType + "')";
                    int rowsUpdated = stmt.executeUpdate(query);
                    if (rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(frame, "Room book successfully.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Error: Unable to book the room.");
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
