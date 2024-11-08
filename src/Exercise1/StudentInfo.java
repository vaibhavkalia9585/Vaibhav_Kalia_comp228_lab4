package Exercise1;
import java.awt.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class StudentInfo extends JFrame {
    // Declaring the variables
    private JPanel masterPanel, panel1, panel2, panel3, panel4, panel5, panel6;
    private JLabel lblFullName, lblAddress, lblCity, lblProvince, lblPostalCode, lblPhoneNumber, lblEmail;
    private JTextField txtFullName, txtAddress, txtCity, txtProvince, txtPostalCode, txtPhoneNumber, txtEmail;
    private JButton btnDisplay;
    private JCheckBox checkBox1, checkBox2;
    private JRadioButton radioButton1, radioButton2;
    private JComboBox<String> courseList;
    private JTextArea txtArea;
    private List<String> addedCourses = new ArrayList<>();
    private JList<String> selectedCourses = new JList<>();

    // Constructor
    public StudentInfo() {
        setupFrame();
        setupPanels();
        setupPersonalInfoPanel();
        setupExtraActivitiesPanel();
        setupMajorSelectionPanel();
        setupCoursesPanel();
        setupDisplayPanel();
        setupDisplayArea();
    }

    // Frame setup
    private void setupFrame() {
        setSize(800, 600);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        masterPanel = new JPanel(null);
        masterPanel.setBounds(6, 6, 740, 450);
        add(masterPanel);
    }

    // Panel setup
    private void setupPanels() {
        panel1 = createPanel(10, 30, 400, 240, "Personal Information");
        panel2 = createPanel(420, 18, 150, 100, "Extra Activities");
        panel4 = createPanel(580, 18, 150, 100, "Student's Major");
        panel5 = createPanel(420, 128, 310, 130, "Student's Courses");
        panel6 = createPanel(10, 300, 720, 140, "Student's Information Display");
    }

    private JPanel createPanel(int x, int y, int width, int height, String title) {
        JPanel panel = new JPanel(null);
        panel.setBounds(x, y, width, height);
        panel.setBorder(BorderFactory.createTitledBorder(title));
        masterPanel.add(panel);
        return panel;
    }

    // Personal Info panel setup
    private void setupPersonalInfoPanel() {
        JPanel personalInfoPanel = new JPanel(new GridLayout(7, 2, 10, 5));
        personalInfoPanel.setBounds(10, 20, 380, 200);

        lblFullName = new JLabel("Full Name: ");
        lblAddress = new JLabel("Address: ");
        lblCity = new JLabel("City: ");
        lblProvince = new JLabel("Province: ");
        lblPostalCode = new JLabel("Postal Code: ");
        lblPhoneNumber = new JLabel("Phone Number: ");
        lblEmail = new JLabel("Email: ");

        txtFullName = new JTextField();
        txtAddress = new JTextField();
        txtCity = new JTextField();
        txtProvince = new JTextField();
        txtPostalCode = new JTextField();
        txtPhoneNumber = new JTextField();
        txtEmail = new JTextField();

        addComponentsToPanel(personalInfoPanel, lblFullName, txtFullName, lblAddress, txtAddress,
                lblCity, txtCity, lblProvince, txtProvince, lblPostalCode, txtPostalCode,
                lblPhoneNumber, txtPhoneNumber, lblEmail, txtEmail);
        panel1.add(personalInfoPanel);
    }

    private void addComponentsToPanel(JPanel panel, Component... components) {
        for (Component component : components) {
            panel.add(component);
        }
    }

    // Extra Activities panel setup
    private void setupExtraActivitiesPanel() {
        JPanel activitiesPanel = new JPanel(new GridLayout(2, 1, 10, 5));
        activitiesPanel.setBounds(5, 22, 140, 75);

        checkBox1 = new JCheckBox("Student Council");
        checkBox2 = new JCheckBox("Volunteer Work");

        activitiesPanel.add(checkBox1);
        activitiesPanel.add(checkBox2);
        panel2.add(activitiesPanel);
    }

    // Major Selection panel setup
    private void setupMajorSelectionPanel() {
        JPanel majorPanel = new JPanel(new GridLayout(2, 1, 10, 5));
        majorPanel.setBounds(5, 22, 140, 75);

        radioButton1 = new JRadioButton("Computer Science", true);
        radioButton2 = new JRadioButton("Business");
        radioButton1.addActionListener(e -> updateCourses());
        radioButton2.addActionListener(e -> updateCourses());

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(radioButton1);
        btnGroup.add(radioButton2);

        majorPanel.add(radioButton1);
        majorPanel.add(radioButton2);
        panel4.add(majorPanel);
    }

    // Courses Panel setup
    private void setupCoursesPanel() {
        courseList = new JComboBox<>();
        courseList.addActionListener(e -> addCourse());

        JScrollPane courseScrollPane = new JScrollPane(selectedCourses);
        courseScrollPane.setBounds(10, 60, 280, 60);
        selectedCourses.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        panel5.add(courseScrollPane);
        panel5.add(createCourseDropdownPanel());
    }

    private JPanel createCourseDropdownPanel() {
        JPanel courseDropdownPanel = new JPanel(new GridLayout(1, 1));
        courseDropdownPanel.setBounds(10, 20, 280, 25);
        courseDropdownPanel.add(courseList);
        return courseDropdownPanel;
    }

    // Display Button panel setup
    private void setupDisplayPanel() {
        btnDisplay = new JButton("Display");
        btnDisplay.setBounds(60, 5, 80, 25);
        btnDisplay.addActionListener(e -> displayInfo());
        panel3 = createPanel(280, 268, 180, 35, "");
        panel3.add(btnDisplay);
        masterPanel.add(panel3);
    }

    // Display area setup
    private void setupDisplayArea() {
        txtArea = new JTextArea();
        txtArea.setLineWrap(true);
        txtArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(txtArea);
        scrollPane.setBounds(10, 20, 700, 110);
        panel6.add(scrollPane);
    }

    // Update courses based on selected major
    private void updateCourses() {
        courseList.removeAllItems();
        String[] courses = radioButton1.isSelected() ?
                new String[]{"Java Programming Language", "Python Programming Language", "C# Programming Language"} :
                new String[]{"Business 1", "Business 2", "Business 3"};

        for (String course : courses) {
            courseList.addItem(course);
        }
    }

    // Add course to the list
    private void addCourse() {
        String course = (String) courseList.getSelectedItem();
        if (course != null && !course.isEmpty() && !addedCourses.contains(course)) {
            addedCourses.add(course);
            selectedCourses.setListData(addedCourses.toArray(new String[0]));
        }
    }

    // Display student information
    private void displayInfo() {
        if (isAnyFieldEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        txtArea.append(String.format("Full Name: %s\nAddress: %s\nCity: %s\nProvince: %s\n" +
                        "Postal Code: %s\nPhone Number: %s\nEmail: %s\n",
                txtFullName.getText(), txtAddress.getText(), txtCity.getText(),
                txtProvince.getText(), txtPostalCode.getText(),
                txtPhoneNumber.getText(), txtEmail.getText()));

        txtArea.append(String.format("Student Council: %s\nVolunteer Work: %s\n",
                checkBox1.isSelected() ? "Yes" : "No",
                checkBox2.isSelected() ? "Yes" : "No"));

        txtArea.append("Courses:\n");
        for (String course : addedCourses) {
            txtArea.append("    - " + course + "\n");
        }
    }

    // Check if any text field is empty
    private boolean isAnyFieldEmpty() {
        return txtFullName.getText().isEmpty() || txtAddress.getText().isEmpty() ||
                txtCity.getText().isEmpty() || txtProvince.getText().isEmpty() ||
                txtPostalCode.getText().isEmpty() || txtPhoneNumber.getText().isEmpty() ||
                txtEmail.getText().isEmpty();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentInfo().setVisible(true));
    }
}
