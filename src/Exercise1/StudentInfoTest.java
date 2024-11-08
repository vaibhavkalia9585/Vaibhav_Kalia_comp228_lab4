package Exercise1;

import javax.swing.*;

public class StudentInfoTest {
    public static void main(String[] args) {
        // Launching the StudentInfo application
        SwingUtilities.invokeLater(() -> {
            StudentInfo studentInfo = new StudentInfo();

            // Setting up frame properties
            studentInfo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit application on close
            studentInfo.setResizable(false);  // Prevent resizing
            studentInfo.setTitle("Student Application");  // Set window title
            studentInfo.setSize(770, 500);  // Set window size
            studentInfo.setLocationRelativeTo(null);  // Center the window
            studentInfo.setVisible(true);  // Make window visible
        });
    }
}