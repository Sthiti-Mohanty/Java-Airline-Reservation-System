import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;
import java.util.*;

public class Main {
    public static void add_admin() {
        JFrame j1 = new JFrame();
        Font f = new Font("Times New Roman", Font.BOLD, 26);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        j1.setVisible(true);
        j1.setTitle("FLIGHT DETAILS");
        j1.setResizable(false);
        j1.setSize(500, 600);
        j1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        JLabel label1 = new JLabel(": Customer Details :");
        label1.setFont(f);
        label1.setBounds(130, 30, 300, 30);
        panel.add(label1);
        
        JTextArea F1 = new JTextArea();
        F1.setLineWrap(true);
        Font fF1 = new Font("Arial", Font.BOLD, 20);
        F1.setFont(fF1);
        JScrollPane scrollPane = new JScrollPane(F1);
        scrollPane.setBounds(25, 110, 440, 400); 
        panel.add(scrollPane);
        
        JLabel label2 = new JLabel("Enter Password:");
        label2.setBounds(50, 70, 120, 30); // Adjusted position for label2
        panel.add(label2);
        
        JTextField F2 = new JTextField();
        F2.setBounds(170, 70, 180, 30); // Adjusted position for F2
        panel.add(F2);
        
        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(360, 70, 100, 30); // Positioned the button next to the text field
        panel.add(submitButton);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password;
                password = F2.getText();
                if(password.equals("unlock")){
                    F1.setText("");
                    // F1.setText("Authenicated.");
                    Backend B = new Backend();
                    // F1.setText(Arrays.toString(B.getData()));

                    String[] response = B.getData();

                    for(int i = 0; i < response.length; i++){
                        String tempLine = response[i];
                        if(tempLine!=null){

                            F1.setText(F1.getText() + tempLine);
                        }
                    }
                }
            }
        });

        j1.add(panel);
    }
    
    
    public void add_data() {
        JFrame j1 = new JFrame();
        JPanel panel = new JPanel();
        panel.setLayout(null);
        j1.setVisible(true);
        j1.setTitle("FLIGHT DETAILS");
        j1.setResizable(false);
        j1.setSize(500, 600); // Adjusted size to accommodate all components
        j1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel label1 = new JLabel("Enter Seat No:  ");
        label1.setBounds(50, 30, 300, 30);
        panel.add(label1);
        
        JTextField F1 = new JTextField();
        F1.setBounds(50, 70, 300, 30);
        panel.add(F1);

        JLabel label2 = new JLabel("Enter Name: ");
        label2.setBounds(50, 110, 300, 30);
        panel.add(label2);

        JTextField F2 = new JTextField();
        F2.setBounds(50, 140, 300, 30);
        panel.add(F2);

        JLabel label3 = new JLabel("Enter Age:  ");
        label3.setBounds(50, 180, 300, 30);
        panel.add(label3);

        JTextField F3 = new JTextField();
        F3.setBounds(50, 210, 300, 30);
        panel.add(F3);

        JLabel label4 = new JLabel("Enter Email:  ");
        label4.setBounds(50, 250, 300, 30);
        panel.add(label4);

        JTextField F4 = new JTextField();
        F4.setBounds(50, 280, 300, 30);
        panel.add(F4);

        JLabel label5 = new JLabel("Enter Source :  ");
        label5.setBounds(50, 310, 300, 30);
        panel.add(label5);

        JTextField F5 = new JTextField();
        F5.setBounds(50, 340, 300, 30);
        panel.add(F5);

        JLabel label6 = new JLabel("Enter Destination :  "); 
        label6.setBounds(50, 390, 300, 30);
        panel.add(label6);

        JTextField F6 = new JTextField();
        F6.setBounds(50, 420, 300, 30);
        panel.add(F6);

        JButton submitButton = new JButton("Submit");
        submitButton.setBounds(50, 490, 100, 30);
        submitButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) { 
                int seat_no = Integer.parseInt(F1.getText());
                String name = F2.getText();
                int Age = Integer.parseInt(F3.getText());
                String Email = F4.getText();
                String Source = F5.getText();
                String destination = F6.getText();

                Backend B = new Backend();
                int response = B.insertData(seat_no, name, Age, Email, Source, destination);

                if(response == 1){
                    String wt = "\nSeat_no : "+ seat_no + "\n Name: " + name + "\n Age: "+ Age + "\n Email: "+ Email +"\nSource: "+ Source + "\n Destination: "+ destination + "\n\nInserted Successfully.";
                    JOptionPane.showMessageDialog(null, wt);
                }
                else if(response == -1){
                    String wt = "Seat Already Booked.";
                    JOptionPane.showMessageDialog(null, wt);
                }
                else{
                    String wt = "Error Occured !!";
                    JOptionPane.showMessageDialog(null, wt);
                }
                j1.dispose();
            }
        });
        panel.add(submitButton);

        j1.add(panel);
    }

    public static void main(String[] args) {
        Main g = new Main();
        JFrame j = new JFrame();
        j.setVisible(true);
        j.setTitle("Airline Management System");
        j.setSize(450, 550);
        j.setResizable(false);
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setLayout(null);
        JLabel l = new JLabel(":Airline Reservation System:");
        Font f = new Font("Times New Roman", Font.BOLD, 26);
        l.setFont(f);
        l.setBounds(50, 30, 350, 50); // Adjusted position and size
        j.add(l);
        Font f1 = new Font("Times New Roman", Font.BOLD, 16);
        JButton JB = new JButton("Admin");
        JB.setBounds(50, 100, 300, 50);
        JB.setFont(f1);
        JB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.add_admin();
            }
        });
        JButton Jd = new JButton("User");
        Jd.setBounds(50, 160, 300, 50);
        Jd.setFont(f1);
        Jd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                g.add_data();
            }
        });

        JButton Jc = new JButton("Exit");
        Jc.setFont(f1);
        Jc.setBounds(50, 220, 300, 50);

        Jc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        j.add(Jc);
        j.add(Jd);
        j.add(JB);
    }
}