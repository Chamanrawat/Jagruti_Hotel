import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class view_all_customer {
    JTable table = new JTable();
    //JButton viewAllCustomerButton = new JButton("View All Customer");

    view_all_customer() {
        JFrame viewFrame = new JFrame("View All Customer");
        viewFrame.setBounds(200, 100, 800, 600); // Adjusted the frame width
        viewFrame.setVisible(true);
        viewFrame.setLayout(new BorderLayout()); // Use BorderLayout for a better layout

        //viewFrame.add(viewAllCustomerButton, BorderLayout.NORTH); // Added button to the top

        String url = "jdbc:mysql://localhost:3306/jagrutihotel";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM customer ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("customer_id");
            tableModel.addColumn("customer_name");
            tableModel.addColumn("customer_gender");
            tableModel.addColumn("customer_country");
            tableModel.addColumn("customer_checkintime");
            tableModel.addColumn("customer_deposit");

            while (resultSet.next()) {
                String id = resultSet.getString("customer_id");
                String name = resultSet.getString("customer_name");
                String gender = resultSet.getString("customer_gender");
                String country = resultSet.getString("customer_country");
                String checkinTime = resultSet.getString("customer_checkintime");
                String deposit = resultSet.getString("customer_deposit");

                tableModel.addRow(new Object[]{id, name, gender, country, checkinTime, deposit});
            }

            // Create the table and scroll pane outside the loop
            table.setModel(tableModel);
            JScrollPane scrollPane = new JScrollPane(table);
            viewFrame.add(scrollPane, BorderLayout.CENTER);

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            // Handle the exception appropriately
        }
    }

   
}
