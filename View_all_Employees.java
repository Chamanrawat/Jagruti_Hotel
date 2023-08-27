import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class View_all_Employees {
    JTable table = new JTable();
    // JButton viewAllCustomerButton = new JButton("View All Customer");

    View_all_Employees() {
        JFrame viewFrame = new JFrame("View All Customer");
        viewFrame.setBounds(200, 100, 800, 600); // Adjusted the frame width
        viewFrame.setVisible(true);
        viewFrame.setLayout(new BorderLayout()); // Use BorderLayout for a better layout

        // viewFrame.add(viewAllCustomerButton, BorderLayout.NORTH); // Added button to
        // the top

        String url = "jdbc:mysql://localhost:3306/jagrutihotel";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT * FROM hotel_employee ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("employee_id");
            tableModel.addColumn("employee_name");
            tableModel.addColumn("employee_age");
            tableModel.addColumn("employee_gender");
            tableModel.addColumn("employee_phone");
            tableModel.addColumn("employee_job");
            tableModel.addColumn("employee_sellary");
            tableModel.addColumn("employee_adhar");
            tableModel.addColumn("employee_email");

            while (resultSet.next()) {
                String id = resultSet.getString("employee_id");
                String name = resultSet.getString("employee_name");
                String age = resultSet.getString("employee_age");
                String gender = resultSet.getString("employee_gender");
                String phone = resultSet.getString("employee_phone");
                String job = resultSet.getString("employee_job");
                String sellary = resultSet.getString("employee_sellary");
                String adhar = resultSet.getString("employee_adhar");
                String email = resultSet.getString("employee_email");
                tableModel.addRow(new Object[] { id, name, age, gender, phone, job, sellary, adhar, email });
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
