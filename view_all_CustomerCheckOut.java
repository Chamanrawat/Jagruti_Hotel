import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class view_all_CustomerCheckOut {
    JTable table = new JTable();
    //JButton viewAllCustomerButton = new JButton("View All Customer");

    view_all_CustomerCheckOut() {
        JFrame viewFrame = new JFrame("View All check out Customer");
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

            String query = "SELECT * FROM check_out ";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("id");
            tableModel.addColumn("customer_name");
            tableModel.addColumn("check_out");

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("customer_name");
                String checkOut = resultSet.getString("check_out");

                tableModel.addRow(new Object[]{id, name, checkOut});
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
