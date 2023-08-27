import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

class App extends JFrame {
    public static void main(String[] args) {
        JFrame frame = new JFrame();

        frame.setTitle("Jagruti Hotel");
        frame.setVisible(true);
        frame.setSize(1000, 700);

        JMenuBar hotel = new JMenuBar();
        JMenu customer = new JMenu("Customer");

        JMenuItem add = new JMenuItem("Add Customer");
        JMenuItem update_customer = new JMenuItem("Update Customer");
        JMenuItem delateCustomer = new JMenuItem("Delate Customer");
        JMenuItem view_customer = new JMenuItem("View All Customer");

        customer.add(add);
        customer.add(update_customer);
        customer.add(delateCustomer);
        customer.add(view_customer);

        hotel.add(customer);
        frame.setJMenuBar(hotel);

        JMenu employee = new JMenu("Employee");

        JMenuItem addEmployee = new JMenuItem("Add Employee");
        JMenuItem updateEmployee = new JMenuItem("Update Employee");
        JMenuItem delateEmployee = new JMenuItem("Delate Employee");
        JMenuItem view_employee = new JMenuItem("View All Employee");

        employee.add(addEmployee);
        employee.add(updateEmployee);
        employee.add(delateEmployee);
        employee.add(view_employee);

        hotel.add(employee);

        JMenu roombook = new JMenu("Room Booking");

        JMenuItem roombooking = new JMenuItem("Room Booking");
        JMenuItem checkout = new JMenuItem("Check out");
JMenuItem viewCheckOutItem = new JMenuItem("View All Check out");
        roombook.add(roombooking);
        roombook.add(checkout);
        roombook.add(viewCheckOutItem);

        hotel.add(roombook);


        

        add.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new add_customer();
            }
        });

        // update customer

        update_customer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new update_customer();
            }
        });
        view_customer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new view_all_customer();

            }
        });
        delateCustomer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new delete_customer();
            }
        });

        addEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Add_employee();
            }

        });
        updateEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Update_employee();
            }

        });

        delateEmployee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Delete_employee();
            }
        });

        view_employee.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new View_all_Employees();
            }
        });
        roombooking.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new room_booking();
            }
        });
         checkout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new checkOut_customer();
            }
        });
        viewCheckOutItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new view_all_CustomerCheckOut();
            }
        });

    }
}
