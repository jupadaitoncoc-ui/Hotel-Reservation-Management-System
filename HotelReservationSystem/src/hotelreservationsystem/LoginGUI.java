package hotelreservationsystem;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private final Color NAVY = new Color(24, 43, 61);
    private final Color GOLD = new Color(198, 153, 72);
    private final Color WHITE = Color.WHITE;

    public LoginGUI() {

        setTitle("A MAN Hotel & Reservation");
        setSize(1100, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        buildUI();
    }

    private void buildUI() {

        HotelBackground background = new HotelBackground();
        background.setLayout(new GridBagLayout());

        JPanel loginCard = new JPanel();
        loginCard.setOpaque(false);
        loginCard.setLayout(new BorderLayout());

        // LEFT SIDE
        JPanel leftPanel = new JPanel();
        leftPanel.setOpaque(false);
        leftPanel.setPreferredSize(new Dimension(500, 600));
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

        leftPanel.add(Box.createVerticalGlue());

        JLabel logo = new JLabel("A");
        logo.setFont(new Font("Serif", Font.BOLD, 90));
        logo.setForeground(GOLD);
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(logo);

        JLabel hotelName = new JLabel("A MAN");
        hotelName.setFont(new Font("Serif", Font.BOLD, 48));
        hotelName.setForeground(WHITE);
        hotelName.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(hotelName);

        JLabel hotelTitle = new JLabel("HOTEL & RESERVATION");
        hotelTitle.setFont(new Font("Serif", Font.PLAIN, 20));
        hotelTitle.setForeground(GOLD);
        hotelTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(hotelTitle);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JPanel linePanel = new JPanel();
        linePanel.setPreferredSize(new Dimension(230, 2));
        linePanel.setMaximumSize(new Dimension(230, 2));
        linePanel.setBackground(GOLD);
        linePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(linePanel);

        leftPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        JLabel slogan = new JLabel("STAY  •  RELAX  •  ENJOY");
        slogan.setFont(new Font("SansSerif", Font.PLAIN, 15));
        slogan.setForeground(new Color(235, 225, 205));
        slogan.setAlignmentX(Component.CENTER_ALIGNMENT);
        leftPanel.add(slogan);

        leftPanel.add(Box.createVerticalGlue());

        // RIGHT SIDE
        JPanel rightPanel = new RoundedPanel(
                30,
                new Color(255, 255, 255, 242)
        );

        rightPanel.setPreferredSize(new Dimension(420, 500));
        rightPanel.setLayout(new BorderLayout());

        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        45, 45, 40, 45
                )
        );

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(
                new BoxLayout(formPanel, BoxLayout.Y_AXIS)
        );

        JLabel welcome = new JLabel("Welcome Back");
        welcome.setFont(new Font("Serif", Font.BOLD, 34));
        welcome.setForeground(NAVY);
        welcome.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(welcome);

        JLabel subtitle = new JLabel(
                "Sign in to your hotel account"
        );

        subtitle.setFont(
                new Font("SansSerif", Font.PLAIN, 14)
        );

        subtitle.setForeground(new Color(110, 110, 110));
        subtitle.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(subtitle);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 35)
                )
        );

        // USERNAME
        JLabel usernameLabel = new JLabel("USERNAME");
        usernameLabel.setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        usernameLabel.setForeground(NAVY);
        usernameLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(usernameLabel);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        usernameField = createTextField();
        formPanel.add(usernameField);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 20)
                )
        );

        // PASSWORD
        JLabel passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        passwordLabel.setForeground(NAVY);
        passwordLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        formPanel.add(passwordLabel);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 8)
                )
        );

        passwordField = new JPasswordField();
        styleTextField(passwordField);
        formPanel.add(passwordField);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 28)
                )
        );

        // LOGIN
        JButton loginButton = createGoldButton("LOGIN");
        loginButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        loginButton.addActionListener(e -> login());
        formPanel.add(loginButton);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 15)
                )
        );

        // ADMIN LOGIN
        JButton adminButton = createOutlineButton("ADMIN LOGIN");
        adminButton.setAlignmentX(Component.LEFT_ALIGNMENT);
        adminButton.addActionListener(e -> adminLogin());
        formPanel.add(adminButton);

        formPanel.add(
                Box.createRigidArea(
                        new Dimension(0, 25)
                )
        );

        // REGISTER
        JPanel registerPanel = new JPanel(
                new FlowLayout(
                        FlowLayout.CENTER,
                        5,
                        0
                )
        );

        registerPanel.setOpaque(false);

        JLabel noAccount = new JLabel(
                "Don't have an account?"
        );

        noAccount.setFont(
                new Font("SansSerif", Font.PLAIN, 12)
        );

        noAccount.setForeground(Color.GRAY);

        JButton registerButton = new JButton(
                "Create Account"
        );

        registerButton.setFont(
                new Font("SansSerif", Font.BOLD, 12)
        );

        registerButton.setForeground(GOLD);
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        registerButton.addActionListener(
                e -> register()
        );

        registerPanel.add(noAccount);
        registerPanel.add(registerButton);

        formPanel.add(registerPanel);

        rightPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        loginCard.add(
                leftPanel,
                BorderLayout.WEST
        );

        loginCard.add(
                rightPanel,
                BorderLayout.EAST
        );

        background.add(loginCard);

        add(background);
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    private JTextField createTextField() {

        JTextField field = new JTextField();

        styleTextField(field);

        return field;
    }

    private void styleTextField(JTextField field) {

        field.setFont(
                new Font("SansSerif", Font.PLAIN, 15)
        );

        field.setForeground(NAVY);

        field.setBackground(
                new Color(248, 248, 248)
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(210, 210, 210),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                10, 12, 10, 12
                        )
                )
        );

        field.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        field.setPreferredSize(
                new Dimension(300, 45)
        );
    }

    // =========================================================
    // GOLD BUTTON
    // =========================================================

    private JButton createGoldButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(Color.WHITE);
        button.setBackground(GOLD);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        12, 20, 12, 20
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        button.setPreferredSize(
                new Dimension(300, 45)
        );

        return button;
    }

    // =========================================================
    // OUTLINE BUTTON
    // =========================================================

    private JButton createOutlineButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(NAVY);
        button.setBackground(Color.WHITE);

        button.setBorder(
                BorderFactory.createLineBorder(
                        GOLD,
                        1
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        button.setPreferredSize(
                new Dimension(300, 40)
        );

        return button;
    }

    // =========================================================
    // USER LOGIN
    // =========================================================

    private void login() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                ).trim();

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your username and password.",
                    "Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // ADMIN ACCOUNT
        if (username.equalsIgnoreCase("admin")
                && password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, Administrator!",
                    "Admin Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            new AdminGUI().setVisible(true);

            return;
        }

        // NORMAL USER
        HotelManager manager =
                HotelManager.getInstance();

        User user =
                manager.loginUser(
                        username,
                        password
                );

        if (user != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, "
                    + user.getFirstName()
                    + "!",
                    "Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            new UserGUI(user).setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid username or password.",
                    "Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ADMIN LOGIN
    // =========================================================

    private void adminLogin() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                ).trim();

        if (username.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the admin username and password.",
                    "Admin Login",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (username.equalsIgnoreCase("admin")
                && password.equals("admin123")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome, Administrator!",
                    "Admin Login Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            dispose();

            new AdminGUI().setVisible(true);

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid admin username or password.",
                    "Admin Login Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // REGISTER
    // =========================================================

    private void register() {

        JTextField firstName = new JTextField();
        JTextField lastName = new JTextField();
        JTextField username = new JTextField();
        JPasswordField password = new JPasswordField();
        JTextField contact = new JTextField();

        JPanel panel = new JPanel(
                new GridLayout(0, 1, 5, 5)
        );

        panel.add(new JLabel("First Name:"));
        panel.add(firstName);

        panel.add(new JLabel("Last Name:"));
        panel.add(lastName);

        panel.add(new JLabel("Username:"));
        panel.add(username);

        panel.add(new JLabel("Password:"));
        panel.add(password);

        panel.add(new JLabel("Contact Number:"));
        panel.add(contact);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Create New Account",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        String first =
                firstName.getText().trim();

        String last =
                lastName.getText().trim();

        String user =
                username.getText().trim();

        String pass =
                new String(
                        password.getPassword()
                );

        String phone =
                contact.getText().trim();

        if (first.isEmpty()
                || last.isEmpty()
                || user.isEmpty()
                || pass.isEmpty()
                || phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please complete all fields.",
                    "Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        HotelManager manager =
                HotelManager.getInstance();

        User newUser =
                manager.registerUser(
                        first,
                        last,
                        user,
                        pass,
                        phone
                );

        if (newUser != null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Account created successfully!\n\n"
                    + "You can now login using your new account.",
                    "Registration Successful",
                    JOptionPane.INFORMATION_MESSAGE
            );

            usernameField.setText(user);
            passwordField.setText("");

        } else {

            JOptionPane.showMessageDialog(
                    this,
                    "That username already exists.",
                    "Registration Failed",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ROUNDED PANEL
    // =========================================================

    private static class RoundedPanel extends JPanel {

        private final int radius;
        private final Color color;

        public RoundedPanel(
                int radius,
                Color color
        ) {

            this.radius = radius;
            this.color = color;

            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            g2.setColor(color);

            g2.fillRoundRect(
                    0,
                    0,
                    getWidth(),
                    getHeight(),
                    radius,
                    radius
            );

            g2.dispose();

            super.paintComponent(g);
        }
    }

    // =========================================================
    // HOTEL BACKGROUND
    // =========================================================

    private static class HotelBackground extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {

            super.paintComponent(g);

            Graphics2D g2 =
                    (Graphics2D) g.create();

            g2.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON
            );

            int width = getWidth();
            int height = getHeight();

            GradientPaint gradient =
                    new GradientPaint(
                            0,
                            0,
                            new Color(18, 35, 50),
                            width,
                            height,
                            new Color(40, 62, 78)
                    );

            g2.setPaint(gradient);

            g2.fillRect(
                    0,
                    0,
                    width,
                    height
            );

            g2.setColor(
                    new Color(
                            198,
                            153,
                            72,
                            25
                    )
            );

            g2.fillOval(
                    -180,
                    -180,
                    500,
                    500
            );

            g2.setColor(
                    new Color(
                            255,
                            255,
                            255,
                            12
                    )
            );

            g2.fillOval(
                    width - 350,
                    height - 350,
                    500,
                    500
            );

            g2.setColor(
                    new Color(
                            198,
                            153,
                            72
                    )
            );

            g2.setStroke(
                    new BasicStroke(
                            4f,
                            BasicStroke.CAP_ROUND,
                            BasicStroke.JOIN_ROUND
                    )
            );

            int[] xPoints = {
                0, 120, 240, 360, 500
            };

            int[] yPoints = {
                130, 90, 105, 70, 85
            };

            for (int i = 0;
                    i < xPoints.length - 1;
                    i++) {

                g2.drawLine(
                        xPoints[i],
                        yPoints[i],
                        xPoints[i + 1],
                        yPoints[i + 1]
                );
            }

            g2.setColor(
                    new Color(
                            198,
                            153,
                            72
                    )
            );

            g2.setStroke(
                    new BasicStroke(3f)
            );

            g2.drawArc(
                    width - 420,
                    height - 160,
                    500,
                    180,
                    20,
                    130
            );

            g2.setColor(
                    new Color(
                            255,
                            255,
                            255,
                            35
                    )
            );

            for (int i = 0; i < 20; i++) {

                int x =
                        (i * 83 + 50)
                        % Math.max(width, 1);

                int y =
                        (i * 47 + 30)
                        % Math.max(height, 1);

                g2.fillOval(
                        x,
                        y,
                        4,
                        4
                );
            }

            g2.dispose();
        }
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager
                        .getSystemLookAndFeelClassName()
                );

            } catch (Exception e) {

                // Default appearance
            }

            new LoginGUI().setVisible(true);
        });
    }
}