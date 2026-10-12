package hotelreservationsystem;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Box;
import javax.swing.BoxLayout;



public class UserGUI extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================
    private final Color NAVY = new Color(20, 32, 50);
    private final Color NAVY2 = new Color(31, 48, 70);
    private final Color GOLD = new Color(190, 150, 70);
    private final Color CREAM = new Color(248, 246, 240);
    private final Color WHITE = Color.WHITE;
    private final Color TEXT = new Color(45, 45, 45);
    private final Color GREEN = new Color(42, 130, 80);
    private final Color RED = new Color(190, 60, 60);
    private final Color LIGHT = new Color(238, 241, 244);

    // =========================================================
    // MAIN OBJECTS
    // =========================================================
    private HotelManager manager;
    private User user;
   private AvailabilityCalendar availabilityCalendar;
private JPanel roomContainer;

    private JPanel contentPanel;
    private CardLayout cardLayout;

    // =========================================================
    // BOOKING VARIABLES
    // =========================================================
    private Room selectedRoom;

    private LocalDate selectedCheckIn;
    private LocalDate selectedCheckOut;

    private JTextField checkInField;
    private JTextField checkOutField;

    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField contactField;
    private JTextField emailField;

    private JSpinner adultsSpinner;
    private JSpinner childrenSpinner;

    private JComboBox<String> paymentCombo;
    private JTextArea specialRequestArea;

    private JPanel confirmationPanel;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================
    public UserGUI(User user) {

        this.user = user;
        this.manager = HotelManager.getInstance();

        setTitle("A MAN HOTEL - User");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1000, 700));

        // Open maximized
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        buildInterface();

        setLocationRelativeTo(null);
        setVisible(true);
    }

    // =========================================================
    // MAIN INTERFACE
    // =========================================================
    private void buildInterface() {

        getContentPane().setBackground(CREAM);
        setLayout(new BorderLayout());

        add(createNavigationBar(), BorderLayout.NORTH);

        cardLayout = new CardLayout();
        contentPanel = new JPanel(cardLayout);
        contentPanel.setBackground(CREAM);

        contentPanel.add(createHomePage(), "HOME");
        contentPanel.add(createRoomsPage(), "ROOMS");
        contentPanel.add(createBookingPage(), "BOOKING");
        contentPanel.add(createConfirmationPage(), "CONFIRMATION");
        contentPanel.add(createReservationsPage(), "RESERVATIONS");

        add(contentPanel, BorderLayout.CENTER);

        showHome();
    }

    // =========================================================
    // NAVIGATION BAR
    // =========================================================
    private JPanel createNavigationBar() {

        JPanel nav = new JPanel(new BorderLayout());

        nav.setBackground(NAVY);

        nav.setBorder(
                new EmptyBorder(14, 30, 14, 30)
        );

        nav.setPreferredSize(
                new Dimension(0, 72)
        );

        JLabel logo = new JLabel("A MAN HOTEL");

        logo.setForeground(GOLD);
        logo.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        26
                )
        );

        JPanel menu = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        12,
                        0
                )
        );

        menu.setOpaque(false);

        JButton homeButton =
                navButton("HOME");

        JButton roomsButton =
                navButton("ROOMS");

        JButton reservationButton =
                navButton("MY RESERVATIONS");

        JButton logoutButton =
                navButton("LOGOUT");

        homeButton.addActionListener(
                e -> showHome()
        );

        roomsButton.addActionListener(
                e -> showRooms()
        );

        reservationButton.addActionListener(
                e -> showReservations()
        );

        logoutButton.addActionListener(
                e -> logout()
        );

        menu.add(homeButton);
        menu.add(roomsButton);
        menu.add(reservationButton);
        menu.add(logoutButton);

        nav.add(
                logo,
                BorderLayout.WEST
        );

        nav.add(
                menu,
                BorderLayout.EAST
        );

        return nav;
    }

    // =========================================================
    // NAV BUTTON
    // =========================================================
    private JButton navButton(String text) {

        JButton button = new JButton(text);

        button.setForeground(WHITE);
        button.setBackground(NAVY);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setBorderPainted(false);
        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // HOME PAGE
    // =========================================================
    private JPanel createHomePage() {

        JPanel page =
                new JPanel(
                        new BorderLayout()
                );

        page.setBackground(CREAM);

        JPanel main = new JPanel();

        main.setBackground(CREAM);

        main.setLayout(
                new BoxLayout(
                        main,
                        BoxLayout.Y_AXIS
                )
        );

        main.setBorder(
                new EmptyBorder(
                        55,
                        70,
                        55,
                        70
                )
        );

        String firstName = "";

        if (user != null
                && user.getFirstName() != null) {

            firstName =
                    user.getFirstName();
        }

        JLabel welcome =
                new JLabel(
                        firstName.isEmpty()
                                ? "WELCOME TO A MAN HOTEL"
                                : "WELCOME, "
                                + firstName.toUpperCase()
                );

        welcome.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        welcome.setForeground(NAVY);

        welcome.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        42
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Comfort, elegance and exceptional hospitality"
                );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitle.setForeground(TEXT);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        18
                )
        );

        main.add(welcome);

        main.add(
                Box.createVerticalStrut(12)
        );

        main.add(subtitle);

        main.add(
                Box.createVerticalStrut(35)
        );

        JPanel hotelCard =
                new JPanel();

        hotelCard.setBackground(WHITE);

        hotelCard.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                30,
                                40,
                                30,
                                40
                        )
                )
        );

        hotelCard.setLayout(
                new BoxLayout(
                        hotelCard,
                        BoxLayout.Y_AXIS
                )
        );

        hotelCard.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        JLabel hotelName =
                new JLabel("A MAN HOTEL");

        hotelName.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        hotelName.setForeground(GOLD);

        hotelName.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        34
                )
        );

        JLabel location =
                new JLabel(
                        "Manila, Philippines"
                );

        location.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        location.setForeground(NAVY);

        location.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        JLabel description =
                new JLabel(
                        "A comfortable stay in the heart of Manila."
                );

        description.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        description.setForeground(TEXT);

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        16
                )
        );

        hotelCard.add(hotelName);

        hotelCard.add(
                Box.createVerticalStrut(12)
        );

        hotelCard.add(location);

        hotelCard.add(
                Box.createVerticalStrut(10)
        );

        hotelCard.add(description);

        main.add(hotelCard);

        main.add(
                Box.createVerticalStrut(35)
        );

        JButton viewRooms =
                primaryButton("VIEW ROOMS");

        viewRooms.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        viewRooms.setPreferredSize(
                new Dimension(220, 52)
        );

        viewRooms.setMaximumSize(
                new Dimension(220, 52)
        );

        viewRooms.addActionListener(
                e -> showRooms()
        );

        main.add(viewRooms);

        main.add(
                Box.createVerticalStrut(45)
        );

        JPanel features =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                0
                        )
                );

        features.setOpaque(false);

        features.setMaximumSize(
                new Dimension(
                        1100,
                        150
                )
        );

        features.add(
                featureCard(
                        "COMFORT",
                        "Relax in clean and comfortable rooms."
                )
        );

        features.add(
                featureCard(
                        "CONVENIENCE",
                        "Enjoy an easy and simple reservation process."
                )
        );

        features.add(
                featureCard(
                        "HOSPITALITY",
                        "Experience welcoming hotel service."
                )
        );

        main.add(features);

        JScrollPane scroll =
                new JScrollPane(main);

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        page.add(
                scroll,
                BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // FEATURE CARD
    // =========================================================
    private JPanel featureCard(
            String title,
            String text
    ) {

        JPanel card = new JPanel();

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(225, 225, 225)
                        ),
                        new EmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        titleLabel.setForeground(GOLD);

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        JLabel textLabel =
                new JLabel(
                        "<html><div style='text-align:center;'>"
                        + text
                        + "</div></html>"
                );

        textLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        textLabel.setForeground(TEXT);

        textLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        card.add(titleLabel);

        card.add(
                Box.createVerticalStrut(12)
        );

        card.add(textLabel);

        return card;
    }

    // =========================================================
    // ROOMS PAGE
    // =========================================================
    private JPanel createRoomsPage() {

        JPanel page = new JPanel(new BorderLayout());
    page.setBackground(CREAM);

    JPanel topPanel = new JPanel();
    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
    topPanel.setBackground(CREAM);
    topPanel.setBorder(new EmptyBorder(20, 35, 10, 35));

    JLabel title = new JLabel("OUR ROOMS");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setForeground(NAVY);
    title.setFont(new Font("Serif", Font.BOLD, 32));

    JLabel subtitle = new JLabel(
            "Select your dates to check room availability"
    );
    subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
    subtitle.setForeground(TEXT);
    subtitle.setFont(new Font("SansSerif", Font.PLAIN, 15));

    topPanel.add(title);
    topPanel.add(Box.createVerticalStrut(5));
    topPanel.add(subtitle);
    topPanel.add(Box.createVerticalStrut(15));

    // Two-month calendar
    availabilityCalendar = new AvailabilityCalendar((checkIn, checkOut) -> {

        selectedCheckIn = checkIn;
        selectedCheckOut = checkOut;

        // Refresh the rooms for the selected date range
        refreshRoomList();

        // Update booking form fields when a room is selected
    });

    availabilityCalendar.setPreferredSize(
        new Dimension(900, 390)
);

availabilityCalendar.setMinimumSize(
        new Dimension(700, 350)
);

availabilityCalendar.setMaximumSize(
        new Dimension(Integer.MAX_VALUE, 390)
);

availabilityCalendar.setAlignmentX(Component.CENTER_ALIGNMENT);

    topPanel.add(availabilityCalendar);

    JLabel roomHeading = new JLabel("ROOMS AVAILABLE FOR YOUR DATES");
    roomHeading.setAlignmentX(Component.CENTER_ALIGNMENT);
    roomHeading.setForeground(NAVY);
    roomHeading.setFont(new Font("SansSerif", Font.BOLD, 18));
    topPanel.add(Box.createVerticalStrut(10));
topPanel.add(roomHeading);

page.add(topPanel, BorderLayout.NORTH);
    
topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
topPanel.setBackground(CREAM);
topPanel.setBorder(new EmptyBorder(20, 35, 10, 35));

topPanel.add(title);
topPanel.add(Box.createVerticalStrut(5));

    // Room cards
    roomContainer = new JPanel(
            new GridLayout(0, 3, 20, 20)
    );

    roomContainer.setBackground(CREAM);
    roomContainer.setBorder(
            new EmptyBorder(20, 35, 35, 35)
    );

    refreshRoomList();

    JScrollPane scroll = new JScrollPane(roomContainer);
    scroll.setBorder(null);
    scroll.getVerticalScrollBar().setUnitIncrement(16);

    page.add(scroll, BorderLayout.CENTER);

    return page;
}
    // =========================================================
    // ROOM CARD
    // =========================================================
    private JPanel createRoomCard(Room room) {

        JPanel card =
                new JPanel();

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                22,
                                22,
                                22,
                                22
                        )
                )
        );

        card.setLayout(
                new BoxLayout(
                        card,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel roomType =
                new JLabel(
                        room.getRoomType().toUpperCase()
                );

        roomType.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        roomType.setForeground(NAVY);

        roomType.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        23
                )
        );

        JLabel roomNumber =
                new JLabel(
                        "Room "
                        + room.getRoomNumber()
                );

        roomNumber.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        roomNumber.setForeground(GOLD);

        roomNumber.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        JLabel price =
                new JLabel(
                        "₱"
                        + String.format(
                                "%.2f",
                                room.getPrice()
                        )
                        + " / night"
                );

        price.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        price.setForeground(TEXT);

        price.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        String amenities =
                room.getAmenities();

        if (amenities == null
                || amenities.trim().isEmpty()) {

            amenities =
                    "Comfortable hotel room";
        }

        JLabel amenitiesLabel =
                new JLabel(
                        "<html><div style='text-align:center; width:220px;'>"
                        + amenities
                        + "</div></html>"
                );

        amenitiesLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        amenitiesLabel.setForeground(TEXT);

        amenitiesLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        JLabel status =
                new JLabel(
                        room.getRoomStatus()
                );

        status.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        if ("Available".equalsIgnoreCase(
                room.getRoomStatus()
        )) {

            status.setForeground(GREEN);

        } else {

            status.setForeground(RED);
        }

        JButton bookButton =
                primaryButton(
                        "BOOK THIS ROOM"
                );

        bookButton.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        bookButton.setMaximumSize(
                new Dimension(
                        190,
                        42
                )
        );

        boolean available =
                "Available".equalsIgnoreCase(
                        room.getRoomStatus()
                );

        bookButton.setEnabled(
                available
        );

        bookButton.addActionListener(
                e -> {

                    selectedRoom = room;

                    prepareBookingPage();

                    cardLayout.show(
                            contentPanel,
                            "BOOKING"
                    );
                }
        );

        card.add(roomType);

        card.add(
                Box.createVerticalStrut(8)
        );

        card.add(roomNumber);

        card.add(
                Box.createVerticalStrut(15)
        );

        card.add(price);

        card.add(
                Box.createVerticalStrut(15)
        );

        card.add(amenitiesLabel);

        card.add(
                Box.createVerticalStrut(15)
        );

        card.add(status);

        card.add(
                Box.createVerticalStrut(18)
        );

        card.add(bookButton);

        return card;
    }
    
    private void refreshRoomList() {

    if (roomContainer == null) {
        return;
    }

    roomContainer.removeAll();

    List<Room> rooms = manager.getRooms();
    boolean found = false;

    if (rooms != null) {
        for (Room room : rooms) {

            // Ignore rooms that are not generally available
            if (!"Available".equalsIgnoreCase(
                    room.getRoomStatus())) {
                continue;
            }

            // Without dates, show generally available rooms
            if (selectedCheckIn == null || selectedCheckOut == null) {
                roomContainer.add(createRoomCard(room));
                found = true;
                continue;
            }

            // Check actual reservations in the database
            boolean available = manager.isRoomAvailable(
        room,
        selectedCheckIn,
        selectedCheckOut
);

            if (available) {
                roomContainer.add(createRoomCard(room));
                found = true;
            }
        }
    }

    if (!found) {

        String message;

        if (selectedCheckIn == null || selectedCheckOut == null) {
            message = "No rooms are currently available.";
        } else {
            message = "No rooms are available for "
                    + selectedCheckIn + " to " + selectedCheckOut + ".";
        }

        JLabel noRooms = new JLabel(message);
        noRooms.setForeground(RED);
        noRooms.setFont(new Font("SansSerif", Font.BOLD, 16));

        roomContainer.add(noRooms);
    }

    roomContainer.revalidate();
    roomContainer.repaint();
}

    // =========================================================
    // BOOKING PAGE
    // =========================================================
    private JPanel createBookingPage() {

        JPanel page =
                new JPanel(
                        new BorderLayout()
                );

        page.setBackground(CREAM);

        JPanel header =
                new JPanel();

        header.setBackground(CREAM);

        header.setLayout(
                new BoxLayout(
                        header,
                        BoxLayout.Y_AXIS
                )
        );

        header.setBorder(
                new EmptyBorder(
                        25,
                        50,
                        15,
                        50
                )
        );

        JLabel title =
                new JLabel(
                        "COMPLETE YOUR RESERVATION"
                );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setForeground(NAVY);

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        30
                )
        );

        header.add(title);

        page.add(
                header,
                BorderLayout.NORTH
        );

        JPanel form =
                new JPanel();

        form.setBackground(WHITE);

        form.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                30,
                                40,
                                30,
                                40
                        )
                )
        );

        form.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        JLabel selectedRoomLabel =
                new JLabel(
                        "Selected Room"
                );

        selectedRoomLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        JLabel selectedRoomValue =
                new JLabel(
                        "Select a room first"
                );

        selectedRoomValue.setName(
                "selectedRoomValue"
        );

        selectedRoomValue.setForeground(GOLD);

        selectedRoomValue.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        16
                )
        );

        addFormRow(
                form,
                gbc,
                0,
                selectedRoomLabel,
                selectedRoomValue
        );

        JLabel checkInLabel =
                new JLabel(
                        "Check-in Date (YYYY-MM-DD)"
                );

        checkInField =
                new JTextField();

        JLabel checkOutLabel =
                new JLabel(
                        "Check-out Date (YYYY-MM-DD)"
                );

        checkOutField =
                new JTextField();

        addFormRow(
                form,
                gbc,
                1,
                checkInLabel,
                checkInField
        );

        addFormRow(
                form,
                gbc,
                2,
                checkOutLabel,
                checkOutField
        );

        firstNameField =
                new JTextField();

        addFormRow(
                form,
                gbc,
                3,
                new JLabel("First Name"),
                firstNameField
        );

        lastNameField =
                new JTextField();

        addFormRow(
                form,
                gbc,
                4,
                new JLabel("Last Name"),
                lastNameField
        );

        contactField =
                new JTextField();

        addFormRow(
                form,
                gbc,
                5,
                new JLabel("Contact Number"),
                contactField
        );

        emailField =
                new JTextField();

        addFormRow(
                form,
                gbc,
                6,
                new JLabel("Email"),
                emailField
        );

        adultsSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                1,
                                1,
                                20,
                                1
                        )
                );

        addFormRow(
                form,
                gbc,
                7,
                new JLabel("Adults"),
                adultsSpinner
        );

        childrenSpinner =
                new JSpinner(
                        new SpinnerNumberModel(
                                0,
                                0,
                                20,
                                1
                        )
                );

        addFormRow(
                form,
                gbc,
                8,
                new JLabel("Children"),
                childrenSpinner
        );

        paymentCombo =
                new JComboBox<>(
                        new String[]{
                                "GCash",
                                "Maya",
                                "Credit/Debit Card",
                                "Bank Transfer",
                                "Pay at Hotel"
                        }
                );

        addFormRow(
                form,
                gbc,
                9,
                new JLabel("Payment Method"),
                paymentCombo
        );

        specialRequestArea =
                new JTextArea(
                        4,
                        20
                );

        specialRequestArea.setLineWrap(true);
        specialRequestArea.setWrapStyleWord(true);

        JScrollPane requestScroll =
                new JScrollPane(
                        specialRequestArea
                );

        addFormRow(
                form,
                gbc,
                10,
                new JLabel("Special Requests"),
                requestScroll
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        buttons.setOpaque(false);

        JButton backButton =
                secondaryButton("BACK");

        JButton confirmButton =
                primaryButton(
                        "CONTINUE TO PAYMENT"
                );

        backButton.addActionListener(
                e -> showRooms()
        );

        confirmButton.addActionListener(
                e -> processBooking()
        );

        buttons.add(backButton);
        buttons.add(confirmButton);

        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        form.add(
                buttons,
                gbc
        );

        JScrollPane scroll =
                new JScrollPane(form);

        scroll.setBorder(null);

        scroll.getVerticalScrollBar()
                .setUnitIncrement(16);

        page.add(
                scroll,
                BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // ADD FORM ROW
    // =========================================================
    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            Component label,
            Component field
    ) {

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 1;
        gbc.weightx = 0.25;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        if (label instanceof JLabel) {

            ((JLabel) label).setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            13
                    )
            );
        }

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.75;

        if (field instanceof JTextField) {

            field.setPreferredSize(
                    new Dimension(
                            300,
                            38
                    )
            );
        }

        panel.add(
                field,
                gbc
        );
    }

    // =========================================================
    // PREPARE BOOKING PAGE
    // =========================================================
    private void prepareBookingPage() {

        if (selectedRoom == null) {
            return;
        }

        if (user != null) {

            if (user.getFirstName() != null) {

                firstNameField.setText(
                        user.getFirstName()
                );
            }

            if (user.getLastName() != null) {

                lastNameField.setText(
                        user.getLastName()
                );
            }
        }

        checkInField.setText(
        selectedCheckIn == null ? "" : selectedCheckIn.toString()
);

checkOutField.setText(
        selectedCheckOut == null ? "" : selectedCheckOut.toString()
);
        specialRequestArea.setText("");

        updateSelectedRoomLabel(
                "Room "
                + selectedRoom.getRoomNumber()
                + " - "
                + selectedRoom.getRoomType()
                + " - ₱"
                + String.format(
                        "%.2f",
                        selectedRoom.getPrice()
                )
                + " per night"
        );
    }

    // =========================================================
    // UPDATE SELECTED ROOM LABEL
    // =========================================================
    private void updateSelectedRoomLabel(String text) {
    if (contentPanel == null) {
        return;
    }

    Component bookingPage = contentPanel.getComponent(2);

    if (bookingPage instanceof Container) {
        findAndUpdateLabel(
            (Container) bookingPage,
            "selectedRoomValue",
            text
        );
    }
}

    // =========================================================
    // FIND AND UPDATE LABEL
    // =========================================================
    private boolean findAndUpdateLabel(
            Container container,
            String name,
            String text
    ) {

        for (Component component :
                container.getComponents()) {

            if (name.equals(
                    component.getName()
            )
                    && component instanceof JLabel) {

                ((JLabel) component).setText(
                        text
                );

                return true;
            }

            if (component instanceof Container) {

                if (findAndUpdateLabel(
                        (Container) component,
                        name,
                        text
                )) {

                    return true;
                }
            }
        }

        return false;
    }

    // =========================================================
    // PROCESS BOOKING
    // =========================================================
    private void processBooking() {

        if (selectedRoom == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a room first.",
                    "Room Required",
                    JOptionPane.WARNING_MESSAGE
            );

            showRooms();
            return;
        }

        String checkInText =
                checkInField.getText().trim();

        String checkOutText =
                checkOutField.getText().trim();

        if (checkInText.isEmpty()
                || checkOutText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your check-in and check-out dates.",
                    "Missing Dates",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            selectedCheckIn =
                    LocalDate.parse(
                            checkInText
                    );

            selectedCheckOut =
                    LocalDate.parse(
                            checkOutText
                    );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please use the date format:\n"
                    + "YYYY-MM-DD\n\n"
                    + "Example: 2026-10-15",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        if (!selectedCheckOut.isAfter(
                selectedCheckIn
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Check-out date must be after check-in date.",
                    "Invalid Dates",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (selectedCheckIn.isBefore(
                LocalDate.now()
        )) {

            JOptionPane.showMessageDialog(
                    this,
                    "Check-in date cannot be in the past.",
                    "Invalid Date",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        boolean available = manager.isRoomAvailable(
        selectedRoom,
        selectedCheckIn,
        selectedCheckOut
);

        if (!available) {

            JOptionPane.showMessageDialog(
                    this,
                    "This room is not available for the selected dates.",
                    "Room Unavailable",
                    JOptionPane.WARNING_MESSAGE
            );

            showRooms();
            return;
        }

        if (firstNameField.getText()
                .trim()
                .isEmpty()
                || lastNameField.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your first name and last name.",
                    "Guest Information",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (contactField.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your contact number.",
                    "Contact Required",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int adults =
                (Integer) adultsSpinner.getValue();

        int children =
                (Integer) childrenSpinner.getValue();

        int totalGuests =
                adults + children;

        if (totalGuests >
                selectedRoom.getCapacity()) {

            JOptionPane.showMessageDialog(
                    this,
                    "This room has a maximum capacity of "
                    + selectedRoom.getCapacity()
                    + " guest(s).",
                    "Room Capacity",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        long nights =
                ChronoUnit.DAYS.between(
                        selectedCheckIn,
                        selectedCheckOut
                );

        double total =
                selectedRoom.getPrice()
                * nights;

        double downPayment =
                total * 0.30;

        double remaining =
                total - downPayment;

        String paymentMethod =
                paymentCombo
                        .getSelectedItem()
                        .toString();

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "ROOM: "
                        + selectedRoom.getRoomNumber()
                        + "\n"
                        + "CHECK-IN: "
                        + selectedCheckIn
                        + "\n"
                        + "CHECK-OUT: "
                        + selectedCheckOut
                        + "\n"
                        + "NIGHTS: "
                        + nights
                        + "\n"
                        + "TOTAL: ₱"
                        + String.format(
                                "%.2f",
                                total
                        )
                        + "\n"
                        + "30% DOWN PAYMENT: ₱"
                        + String.format(
                                "%.2f",
                                downPayment
                        )
                        + "\n"
                        + "REMAINING BALANCE: ₱"
                        + String.format(
                                "%.2f",
                                remaining
                        )
                        + "\n"
                        + "PAYMENT: "
                        + paymentMethod
                        + "\n\n"
                        + "Continue with this reservation?",
                        "Confirm Reservation",
                        JOptionPane.YES_NO_OPTION
                );

        if (result !=
                JOptionPane.YES_OPTION) {

            return;
        }

        try {

            Guest guest =
                    manager.createOrFindGuest(
                            user,
                            contactField
                                    .getText()
                                    .trim()
                    );

            if (guest == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to create guest information.",
                        "Booking Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            Reservation reservation =
                    manager.createReservation(
                            guest.getGuestID(),
                            selectedRoom.getRoomID(),
                            selectedCheckIn.toString(),
                            selectedCheckOut.toString(),
                            paymentMethod
                    );

            if (reservation == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation could not be created.",
                        "Booking Error",
                        JOptionPane.ERROR_MESSAGE
                );

                return;
            }

            showConfirmation(
                    reservation,
                    nights,
                    total,
                    downPayment,
                    remaining,
                    paymentMethod
            );

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Booking failed:\n"
                    + ex.getMessage(),
                    "Booking Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CONFIRMATION PAGE
    // =========================================================
    private JPanel createConfirmationPage() {

        JPanel page =
                new JPanel(
                        new BorderLayout()
                );

        page.setBackground(CREAM);

        JPanel center =
                new JPanel();

        center.setBackground(CREAM);

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        center.setBorder(
                new EmptyBorder(
                        60,
                        80,
                        60,
                        80
                )
        );

        JLabel title =
                new JLabel(
                        "RESERVATION CONFIRMED"
                );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        title.setForeground(GREEN);

        title.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        36
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Thank you for choosing A MAN HOTEL."
                );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        subtitle.setForeground(TEXT);

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        18
                )
        );

        confirmationPanel =
                new JPanel();

        confirmationPanel.setBackground(WHITE);

        confirmationPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                30,
                                50,
                                30,
                                50
                        )
                )
        );

        confirmationPanel.setLayout(
                new BoxLayout(
                        confirmationPanel,
                        BoxLayout.Y_AXIS
                )
        );

        center.add(title);

        center.add(
                Box.createVerticalStrut(12)
        );

        center.add(subtitle);

        center.add(
                Box.createVerticalStrut(35)
        );

        center.add(
                confirmationPanel
        );

        JPanel buttons =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                15,
                                10
                        )
                );

        buttons.setOpaque(false);

        JButton homeButton =
                primaryButton(
                        "BACK TO HOME"
                );

        JButton reservationsButton =
                secondaryButton(
                        "MY RESERVATIONS"
                );

        homeButton.addActionListener(
                e -> showHome()
        );

        reservationsButton.addActionListener(
                e -> showReservations()
        );

        buttons.add(homeButton);
        buttons.add(reservationsButton);

        center.add(
                Box.createVerticalStrut(25)
        );

        center.add(buttons);

        page.add(
                center,
                BorderLayout.CENTER
        );

        return page;
    }

    // =========================================================
    // SHOW CONFIRMATION
    // =========================================================
    private void showConfirmation(
            Reservation reservation,
            long nights,
            double total,
            double downPayment,
            double remaining,
            String paymentMethod
    ) {

        confirmationPanel.removeAll();

        addConfirmationLine(
                "Reservation Number",
                String.valueOf(
                        reservation.getReservationID()
                )
        );

        addConfirmationLine(
                "Hotel",
                "A MAN HOTEL"
        );

        addConfirmationLine(
                "Location",
                "Manila, Philippines"
        );

        addConfirmationLine(
                "Room",
                selectedRoom.getRoomNumber()
                + " - "
                + selectedRoom.getRoomType()
        );

        addConfirmationLine(
                "Check-in",
                selectedCheckIn.toString()
        );

        addConfirmationLine(
                "Check-out",
                selectedCheckOut.toString()
        );

        addConfirmationLine(
                "Nights",
                String.valueOf(nights)
        );

        addConfirmationLine(
                "Total Amount",
                "₱"
                + String.format(
                        "%.2f",
                        total
                )
        );

        addConfirmationLine(
                "30% Down Payment",
                "₱"
                + String.format(
                        "%.2f",
                        downPayment
                )
        );

        addConfirmationLine(
                "Remaining Balance",
                "₱"
                + String.format(
                        "%.2f",
                        remaining
                )
        );

        addConfirmationLine(
                "Payment Method",
                paymentMethod
        );

        addConfirmationLine(
                "Status",
                "CONFIRMED"
        );

        confirmationPanel.revalidate();
        confirmationPanel.repaint();

        cardLayout.show(
                contentPanel,
                "CONFIRMATION"
        );
    }

    // =========================================================
    // CONFIRMATION LINE
    // =========================================================
    private void addConfirmationLine(
            String label,
            String value
    ) {

        JPanel row =
                new JPanel(
                        new BorderLayout()
                );

        row.setOpaque(false);

        row.setBorder(
                new EmptyBorder(
                        5,
                        0,
                        5,
                        0
                )
        );

        JLabel left =
                new JLabel(label);

        left.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        left.setForeground(NAVY);

        JLabel right =
                new JLabel(value);

        right.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        right.setForeground(TEXT);

        row.add(
                left,
                BorderLayout.WEST
        );

        row.add(
                right,
                BorderLayout.EAST
        );

        confirmationPanel.add(row);
    }

    // =========================================================
    // RESERVATIONS PAGE
    // =========================================================
    private JPanel createReservationsPage() {

        JPanel page = new JPanel(new BorderLayout());
    page.setBackground(CREAM);

    JPanel header = new JPanel();
    header.setBackground(CREAM);
    header.setLayout(
            new BoxLayout(header, BoxLayout.Y_AXIS)
    );

    header.setBorder(
            new EmptyBorder(30, 50, 20, 50)
    );

    JLabel title = new JLabel("MY RESERVATIONS");
    title.setAlignmentX(Component.CENTER_ALIGNMENT);
    title.setForeground(NAVY);
    title.setFont(
            new Font("Serif", Font.BOLD, 34)
    );

    header.add(title);
    page.add(header, BorderLayout.NORTH);

    JPanel list = new JPanel();
    list.setBackground(CREAM);
    list.setBorder(
            new EmptyBorder(10, 50, 40, 50)
    );

    list.setLayout(
            new BoxLayout(list, BoxLayout.Y_AXIS)
    );

    try {

        if (user == null) {

            JLabel noUser = new JLabel(
                    "No user logged in."
            );

            list.add(noUser);

        } else {

            // Load only the reservations belonging
            // to the currently logged-in user.
            List<Reservation> reservations =
                    manager.getReservationsForUser(
                            user.getUserID()
                    );

            if (reservations.isEmpty()) {

                JLabel none = new JLabel(
                        "You have no reservations yet."
                );

                none.setForeground(TEXT);
                none.setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                17
                        )
                );

                none.setAlignmentX(
                        Component.CENTER_ALIGNMENT
                );

                list.add(
                        Box.createVerticalStrut(50)
                );

                list.add(none);

            } else {

                for (Reservation reservation : reservations) {

                    list.add(
                            createReservationCard(reservation)
                    );

                    list.add(
                            Box.createVerticalStrut(15)
                    );
                }
            }
        }

    } catch (Exception ex) {

        ex.printStackTrace();

        JLabel error = new JLabel(
                "Unable to load reservations."
        );

        error.setForeground(RED);
        list.add(error);
    }

    JScrollPane scroll = new JScrollPane(list);
    scroll.setBorder(null);

    scroll.getVerticalScrollBar()
            .setUnitIncrement(16);

    page.add(scroll, BorderLayout.CENTER);

    return page;
}

    // =========================================================
    // RESERVATION CARD
    // =========================================================
    private JPanel createReservationCard(
            Reservation reservation
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(220, 220, 220)
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        Room room =
                manager.findRoom(
                        reservation.getRoomID()
                );

        String roomInfo =
                room == null
                        ? "Room"
                        : room.getRoomNumber()
                        + " - "
                        + room.getRoomType();

        JPanel info =
                new JPanel();

        info.setOpaque(false);

        info.setLayout(
                new BoxLayout(
                        info,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel reservationID =
                new JLabel(
                        "Reservation #"
                        + reservation.getReservationID()
                );

        reservationID.setForeground(GOLD);

        reservationID.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        JLabel roomLabel =
                new JLabel(roomInfo);

        roomLabel.setForeground(NAVY);

        roomLabel.setFont(
                new Font(
                        "Serif",
                        Font.BOLD,
                        21
                )
        );

        JLabel dates =
                new JLabel(
                        "Check-in: "
                        + reservation.getCheckInDate()
                        + "     Check-out: "
                        + reservation.getCheckOutDate()
                );

        dates.setForeground(TEXT);

        dates.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        JLabel total =
        new JLabel(
                "Total: ₱"
                + String.format(
                        "%.2f",
                        reservation.getTotalAmount()
                )
        );

        total.setForeground(TEXT);

        total.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        JLabel status =
                new JLabel(
                        reservation.getStatus()
                );

        status.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        if ("Cancelled".equalsIgnoreCase(
                reservation.getStatus()
        )) {

            status.setForeground(RED);

        } else {

            status.setForeground(GREEN);
        }

        info.add(reservationID);

        info.add(
                Box.createVerticalStrut(5)
        );

        info.add(roomLabel);

        info.add(
                Box.createVerticalStrut(8)
        );

        info.add(dates);

        info.add(
                Box.createVerticalStrut(8)
        );

        info.add(total);

        info.add(
                Box.createVerticalStrut(5)
        );

        info.add(status);

        JPanel actions =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT
                        )
                );

        actions.setOpaque(false);

        if (!"Cancelled".equalsIgnoreCase(
                reservation.getStatus()
        )) {

            JButton cancel =
                    dangerButton("CANCEL");

            cancel.addActionListener(
                    e -> cancelReservation(
                            reservation
                    )
            );

            actions.add(cancel);
        }

        card.add(
                info,
                BorderLayout.CENTER
        );

        card.add(
                actions,
                BorderLayout.EAST
        );

        return card;
    }

    // =========================================================
    // CANCEL RESERVATION
    // =========================================================
    private void cancelReservation(
            Reservation reservation
    ) {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel\n"
                        + "Reservation #"
                        + reservation.getReservationID()
                        + "?",
                        "Cancel Reservation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (result != JOptionPane.YES_OPTION) {
            return;
        }

        try {

            boolean success =
        manager.cancelReservation(
                reservation.getReservationID(),
                "Cancelled by user"
        );

            if (success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Reservation cancelled successfully.",
                        "Reservation Cancelled",
                        JOptionPane.INFORMATION_MESSAGE
                );

                showReservations();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Unable to cancel reservation.",
                        "Cancellation Failed",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancellation failed:\n"
                    + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // SHOW HOME
    // =========================================================
    private void showHome() {

        cardLayout.show(
                contentPanel,
                "HOME"
        );
    }

    // =========================================================
    // SHOW ROOMS
    // =========================================================
    private void showRooms() {

          contentPanel.remove(1);

    contentPanel.add(
            createRoomsPage(),
            "ROOMS",
            1
    );

    contentPanel.revalidate();
    contentPanel.repaint();

    cardLayout.show(contentPanel, "ROOMS");
}

    // =========================================================
    // SHOW RESERVATIONS
    // =========================================================
    private void showReservations() {

        int index =
                contentPanel.getComponentZOrder(
                        contentPanel.getComponent(4)
                );

        contentPanel.remove(4);

        contentPanel.add(
                createReservationsPage(),
                "RESERVATIONS",
                index
        );

        contentPanel.revalidate();
        contentPanel.repaint();

        cardLayout.show(
                contentPanel,
                "RESERVATIONS"
        );
    }

    // =========================================================
    // FIXED LOGOUT
    // =========================================================
    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (result == JOptionPane.YES_OPTION) {

            dispose();

            SwingUtilities.invokeLater(() -> {

                LoginGUI loginGUI =
                        new LoginGUI();

                loginGUI.setVisible(true);
            });
        }
    }

    // =========================================================
    // PRIMARY BUTTON
    // =========================================================
    private JButton primaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setBackground(NAVY);
        button.setForeground(WHITE);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // SECONDARY BUTTON
    // =========================================================
    private JButton secondaryButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setBackground(WHITE);
        button.setForeground(NAVY);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // DANGER BUTTON
    // =========================================================
    private JButton dangerButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setBackground(RED);
        button.setForeground(WHITE);

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);
        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // MAIN
    // =========================================================
    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager.getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            LoginGUI loginGUI =
                    new LoginGUI();

            loginGUI.setVisible(true);
        });
    }

}