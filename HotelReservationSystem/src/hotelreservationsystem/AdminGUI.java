package hotelreservationsystem;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.Timer;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class AdminGUI extends JFrame {

    private final HotelManager manager = HotelManager.getInstance();
private JTable occupancyTable;
private DefaultTableModel occupancyModel;
    private JTable guestTable;
    private JTable roomTable;
    private JTable reservationTable;
    private JTable paymentTable;
    private JTable archiveTable;

    private DefaultTableModel guestModel;
    private DefaultTableModel roomModel;
    private DefaultTableModel reservationModel;
    private DefaultTableModel paymentModel;
    private DefaultTableModel archiveModel;

    private JTabbedPane tabs;

    private static final Color NAVY = new Color(19, 31, 52);
    private static final Color NAVY2 = new Color(30, 47, 75);
    private static final Color GOLD = new Color(198, 158, 74);
    private static final Color CREAM = new Color(247, 243, 235);
    private static final Color WHITE = Color.WHITE;
    private static final Color GREEN = new Color(39, 125, 80);
    private static final Color RED = new Color(177, 55, 55);
    private static final Color GRAY = new Color(110, 118, 130);

    // -----------------------------------------------------
    // CONSTRUCTOR
    // -----------------------------------------------------

    public AdminGUI() {
        setTitle("A MAN HOTEL DASHBOARD - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1100, 700));
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        buildGUI();
        setLocationRelativeTo(null);

        SwingUtilities.invokeLater(this::refreshAll);
    }
    
    private void addDateTime(JPanel header) {

    JLabel dateTimeLabel = new JLabel();

    dateTimeLabel.setForeground(Color.WHITE);
    dateTimeLabel.setFont(
            new Font("SansSerif", Font.BOLD, 14)
    );
    dateTimeLabel.setHorizontalAlignment(
            SwingConstants.CENTER
    );

    DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern(
                    "EEEE, MMMM dd, yyyy  |  hh:mm:ss a"
            );

    Timer timer = new Timer(1000, e -> {
        dateTimeLabel.setText(
                LocalDateTime.now().format(formatter)
        );
    });

    timer.start();

    header.add(dateTimeLabel, BorderLayout.CENTER);
}

    // -----------------------------------------------------
    // MAIN GUI
    // -----------------------------------------------------

    private void buildGUI() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(CREAM);

        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(NAVY);
        header.setBorder(BorderFactory.createEmptyBorder(18, 28, 18, 28));

        JLabel title = new JLabel("A Man Hotel | ADMIN DASHBOARD");
        title.setForeground(WHITE);
        title.setFont(new Font("Serif", Font.BOLD, 28));

        JLabel subtitle = new JLabel("Hotel Reservation Management System");
        subtitle.setForeground(new Color(215, 215, 215));
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));

        JPanel titleBox = new JPanel(new GridLayout(2, 1));
        titleBox.setOpaque(false);
        titleBox.add(title);
        titleBox.add(subtitle);

        header.add(titleBox, BorderLayout.WEST);
        addDateTime(header);

        JButton refreshButton = createButton("REFRESH", GOLD);
        refreshButton.addActionListener(e -> refreshAll());

        JButton logoutButton = createButton("LOGOUT", RED);
        logoutButton.addActionListener(e -> logout());

        JPanel headerButtons = new JPanel();
        headerButtons.setOpaque(false);
        headerButtons.add(refreshButton);
        headerButtons.add(logoutButton);

        header.add(headerButtons, BorderLayout.EAST);
        root.add(header, BorderLayout.NORTH);

        tabs = new JTabbedPane();
        tabs.setFont(new Font("SansSerif", Font.BOLD, 14));
        tabs.setBackground(WHITE);
        tabs.setForeground(NAVY);

        tabs.addTab("GUESTS", createGuestsPanel());
tabs.addTab("ROOMS", createRoomsPanel());
tabs.addTab("RESERVATIONS", createReservationsPanel());
tabs.addTab("OCCUPANCY", createOccupancyPanel());
tabs.addTab("PAYMENTS", createPaymentsPanel());
tabs.addTab("ARCHIVE", createArchivePanel());

       tabs.addChangeListener(e -> {
    String selected = tabs.getTitleAt(tabs.getSelectedIndex());

    switch (selected) {
        case "GUESTS":
            refreshGuests();
            break;
        case "ROOMS":
            refreshRooms();
            break;
        case "RESERVATIONS":
            refreshReservations();
            break;
        case "OCCUPANCY":
            refreshOccupancy();
            break;
        case "PAYMENTS":
            refreshPayments();
            break;
        case "ARCHIVE":
            refreshArchive();
            break;
    }
});  
        root.add(tabs, BorderLayout.CENTER);
    setContentPane(root);   
}
    
    
        

    // -----------------------------------------------------
    // GUESTS
    // -----------------------------------------------------

    private JPanel createGuestsPanel() {
        JPanel panel = createBasePanel();

        guestModel = new DefaultTableModel(
                new Object[]{"Guest ID", "First Name", "Last Name", "Contact Number"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        guestTable = new JTable(guestModel);
        styleTable(guestTable);
        panel.add(createScrollPane(guestTable), BorderLayout.CENTER);

        return panel;
    }

    // -----------------------------------------------------
    // ROOMS
    // -----------------------------------------------------

    
private JPanel createRoomsPanel() {
    JPanel panel = createBasePanel();

    roomModel = new DefaultTableModel(
        new Object[]{
            "Room ID", "Room Number", "Room Type",
            "Price", "Status", "Amenities", "Capacity"
        }, 0
    ) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    roomTable = new JTable(roomModel);
    styleTable(roomTable);

    panel.add(createScrollPane(roomTable), BorderLayout.CENTER);

    JPanel buttons = new JPanel();
    buttons.setOpaque(false);

    JButton refresh = createButton("REFRESH", NAVY2);
    refresh.addActionListener(e -> refreshRooms());

    buttons.add(refresh);

    panel.add(buttons, BorderLayout.SOUTH);

    return panel;
}

    // -----------------------------------------------------
    // RESERVATIONS
    // -----------------------------------------------------

    private JPanel createReservationsPanel() {
        JPanel panel = createBasePanel();

        reservationModel = new DefaultTableModel(
                new Object[]{"Reservation ID", "Full Name", "Room",
                    "Check In", "Check Out", "Total",
                    "Down Payment", "Balance", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        reservationTable = new JTable(reservationModel);
        styleTable(reservationTable);
        panel.add(createScrollPane(reservationTable), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);

        JButton cancel = createButton("CANCEL RESERVATION", RED);
        cancel.addActionListener(e -> cancelSelectedReservation());

        JButton refresh = createButton("REFRESH", NAVY2);
        refresh.addActionListener(e -> refreshReservations());

        buttons.add(cancel);
        buttons.add(refresh);

        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    // -----------------------------------------------------
    // PAYMENTS
    // -----------------------------------------------------

    private JPanel createPaymentsPanel() {
        JPanel panel = createBasePanel();

        paymentModel = new DefaultTableModel(
                new Object[]{"Payment ID", "Reservation ID", "Full Name",
                    "Room", "Total", "Amount Paid", "Balance",
                    "Payment Method", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        paymentTable = new JTable(paymentModel);
        styleTable(paymentTable);
        panel.add(createScrollPane(paymentTable), BorderLayout.CENTER);

        JButton refresh = createButton("REFRESH PAYMENTS", NAVY2);
        refresh.addActionListener(e -> refreshPayments());

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);
        buttons.add(refresh);

        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    // -----------------------------------------------------
    // ARCHIVE
    // -----------------------------------------------------

    private JPanel createArchivePanel() {
        JPanel panel = createBasePanel();

        JPanel info = new JPanel(new BorderLayout());
        info.setOpaque(false);

        JLabel archiveTitle = new JLabel("ARCHIVED RECORDS");
        archiveTitle.setFont(new Font("Serif", Font.BOLD, 22));
        archiveTitle.setForeground(NAVY);

        JLabel archiveInfo = new JLabel(
                "Cancelled reservations and historical records are kept here.");
        archiveInfo.setForeground(GRAY);

        info.add(archiveTitle, BorderLayout.NORTH);
        info.add(archiveInfo, BorderLayout.SOUTH);

        panel.add(info, BorderLayout.NORTH);

        archiveModel = new DefaultTableModel(
                new Object[]{"Archive ID", "Type", "Record ID", "Guest",
                    "Room", "Check In", "Check Out", "Total",
                    "Down Payment", "Payment", "Status", "Reason",
                    "Archived At"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        archiveTable = new JTable(archiveModel);
        styleTable(archiveTable);
        archiveTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        panel.add(createScrollPane(archiveTable), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        buttons.setOpaque(false);

        JButton refresh = createButton("REFRESH ARCHIVE", NAVY2);
        refresh.addActionListener(e -> refreshArchive());

        JButton delete = createButton("DELETE ARCHIVE RECORD", RED);
        delete.addActionListener(e -> deleteSelectedArchive());

        buttons.add(refresh);
        buttons.add(delete);

        panel.add(buttons, BorderLayout.SOUTH);

        return panel;
    }

    // -----------------------------------------------------
    // REFRESH ALL TABLES
    // -----------------------------------------------------

    private void refreshAll() {
    refreshGuests();
    refreshRooms();
    refreshReservations();
    refreshOccupancy();
    refreshPayments();
    refreshArchive();
}

    // -----------------------------------------------------
    // REFRESH GUESTS
    // -----------------------------------------------------

    private void refreshGuests() {
        if (guestModel == null) {
            return;
        }

        guestModel.setRowCount(0);

        List<Guest> guests = manager.getGuests();

        for (Guest guest : guests) {
            guestModel.addRow(new Object[]{
                guest.getGuestID(),
                guest.getFirstName(),
                guest.getLastName(),
                guest.getContactNumber()
            });
        }
    }

    // -----------------------------------------------------
    // REFRESH ROOMS
    // -----------------------------------------------------

    private void refreshRooms() {
        if (roomModel == null) {
            return;
        }

        roomModel.setRowCount(0);

        List<Room> rooms = manager.getRooms();

        for (Room room : rooms) {
            roomModel.addRow(new Object[]{
                room.getRoomID(),
                room.getRoomNumber(),
                room.getRoomType(),
                String.format("₱%.2f", room.getPrice()),
                room.getRoomStatus(),
                room.getAmenities(),
                getCapacity(room.getRoomType())
            });
        }
    }

    // -----------------------------------------------------
    // REFRESH RESERVATIONS
    // -----------------------------------------------------

    private void refreshReservations() {
         if (reservationModel == null) {
        return;
    }

    reservationModel.setRowCount(0);

    List<Reservation> reservations = manager.getReservations();

    for (Reservation reservation : reservations) {
        String fullName = manager.guestName(reservation.getGuestID());
        String roomNumber = manager.roomNumber(reservation.getRoomID());
        double paid = manager.getPaidAmount(reservation.getReservationID());
        double balance = Math.max(
                0.0, reservation.getTotalAmount() - paid
        );

        reservationModel.addRow(new Object[]{
            reservation.getReservationID(),
            fullName,
            roomNumber,
            reservation.getCheckInDate(),
            reservation.getCheckOutDate(),
            String.format("₱%.2f", reservation.getTotalAmount()),
            String.format("₱%.2f", reservation.getDownPayment()),
            String.format("₱%.2f", balance),
            reservation.getStatus()
        });
    }
}

    // -----------------------------------------------------
    // REFRESH PAYMENTS
    // -----------------------------------------------------

    private void refreshPayments() {
        if (paymentModel == null) {
            return;
        }

        paymentModel.setRowCount(0);

        List<Payment> payments = manager.getPayments();

        for (Payment payment : payments) {
            Reservation reservation = manager.findReservation(
                    payment.getReservationID());

            String fullName = "Unknown Guest";
            String roomNumber = "Unknown Room";
            double total = 0.0;

            if (reservation != null) {
                fullName = manager.guestName(reservation.getGuestID());
                roomNumber = manager.roomNumber(reservation.getRoomID());
                total = reservation.getTotalAmount();
            }

            double paid = manager.getPaidAmount(
                    payment.getReservationID());

            double balance = Math.max(0.0, total - paid);

            paymentModel.addRow(new Object[]{
                payment.getPaymentID(),
                payment.getReservationID(),
                fullName,
                roomNumber,
                String.format("₱%.2f", total),
                String.format("₱%.2f", paid),
                String.format("₱%.2f", balance),
                payment.getPaymentMethod(),
                payment.getStatus()
            });
        }

        paymentModel.fireTableDataChanged();
    }

    // -----------------------------------------------------
    // REFRESH ARCHIVE
    // -----------------------------------------------------

    private void refreshArchive() {
        if (archiveModel == null) {
            return;
        }

        archiveModel.setRowCount(0);

        List<Object[]> records = manager.getArchiveRecords();

        for (Object[] record : records) {
            archiveModel.addRow(record);
        }
    }

    // -----------------------------------------------------
    // ADD ROOM
    // -----------------------------------------------------

    private void addRoom() {
        JTextField number = new JTextField();

        JComboBox<String> type = new JComboBox<>(new String[]{
            "Superior King", "Superior Twin", "Standard Double",
            "Standard Twin", "Economy Single", "Economy Double"
        });

        JTextField price = new JTextField();

        JComboBox<String> status = new JComboBox<>(new String[]{
            "Available", "Unavailable"
        });

        JTextField amenities = new JTextField();

        JPanel form = createRoomForm(number, type, price, status, amenities);

        int result = JOptionPane.showConfirmDialog(
                this, form, "Add New Room",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result != JOptionPane.OK_OPTION) {
            return;
        }

        try {
            String roomNumber = number.getText().trim();
            String roomType = type.getSelectedItem().toString();
            double roomPrice = Double.parseDouble(price.getText().trim());
            String roomStatus = status.getSelectedItem().toString();
            String roomAmenities = amenities.getText().trim();

            if (roomNumber.isEmpty() || roomAmenities.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this, "Please complete all fields.",
                        "Missing Information", JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (roomPrice <= 0) {
                JOptionPane.showMessageDialog(
                        this, "Price must be greater than zero.",
                        "Invalid Price", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Room room = new Room(
                    0, roomNumber, roomType, roomPrice, roomStatus,
                    roomAmenities, getCapacity(roomType));

            if (manager.addRoom(room)) {
                JOptionPane.showMessageDialog(this, "Room added successfully.");
                refreshRooms();
            } else {
                JOptionPane.showMessageDialog(
                        this, "Could not add room. The room number may already exist.",
                        "Add Room Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(
                    this, "Price must be a valid number.",
                    "Invalid Price", JOptionPane.WARNING_MESSAGE);
        }
    }

    // -----------------------------------------------------
    // EDIT ROOM
    // -----------------------------------------------------

    private void editRoom() {
    int row = roomTable.getSelectedRow();

    if (row < 0) {
        JOptionPane.showMessageDialog(
            this,
            "Please select a room first.",
            "No Room Selected",
            JOptionPane.WARNING_MESSAGE
        );
        return;
    }

    // Convert selected row in case the table is sorted.
    row = roomTable.convertRowIndexToModel(row);

    int roomID = Integer.parseInt(
        roomTable.getModel().getValueAt(row, 0).toString()
    );

    Room selectedRoom = manager.findRoom(roomID);

    if (selectedRoom == null) {
        JOptionPane.showMessageDialog(
            this,
            "Room not found in the database.",
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
        return;
    }

    new RoomEditorFrame(selectedRoom, () -> {
        refreshRooms();
    });
}

    // -----------------------------------------------------
    // DELETE ROOM
    // -----------------------------------------------------

    private void deleteRoom() {
        int row = roomTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Please select a room first.");
            return;
        }

        int roomID = Integer.parseInt(
                roomTable.getValueAt(row, 0).toString());

        String roomNumber = roomTable.getValueAt(row, 1).toString();

        int confirm = JOptionPane.showConfirmDialog(
                this, "Delete room " + roomNumber + "?",
                "Confirm Delete", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (manager.deleteRoom(roomID)) {
            JOptionPane.showMessageDialog(this, "Room deleted successfully.");
            refreshRooms();
        } else {
            JOptionPane.showMessageDialog(
                    this, "Room cannot be deleted because it has an active reservation.",
                    "Delete Failed", JOptionPane.WARNING_MESSAGE);
        }
    }

    // -----------------------------------------------------
    // CANCEL RESERVATION
    // -----------------------------------------------------

    private void cancelSelectedReservation() {
        int row = reservationTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                    this, "Please select a reservation first.");
            return;
        }

        int reservationID = Integer.parseInt(
                reservationTable.getValueAt(row, 0).toString());

        String status = reservationTable.getValueAt(row, 8).toString();

        if (status.equalsIgnoreCase("Cancelled")) {
            JOptionPane.showMessageDialog(
                    this, "This reservation is already cancelled.");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Cancel reservation #" + reservationID
                + "?\n\nThe reservation will be placed in the Archive. ",
                 
                
                "Cancel Reservation",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (manager.cancelReservation(reservationID, "Cancelled by admin")) {
    JOptionPane.showMessageDialog(
            this,
            "Reservation cancelled. The record is now in ARCHIVE."
    );
            refreshReservations();
            refreshPayments();
            refreshArchive();
            refreshRooms();
        } else {
            JOptionPane.showMessageDialog(
                    this, "Could not cancel reservation.",
                    "Cancel Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // -----------------------------------------------------
    // DELETE ARCHIVE
    // -----------------------------------------------------

    private void deleteSelectedArchive() {
        int row = archiveTable.getSelectedRow();

        if (row < 0) {
            JOptionPane.showMessageDialog(
                    this, "Please select an archive record first.");
            return;
        }

        int archiveID = Integer.parseInt(
                archiveTable.getValueAt(row, 0).toString());

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Permanently delete archive record #" + archiveID
                + "?\n\nThis removes the archive copy only. "
                + "The original cancelled reservation and payment history "
                + "will remain if your database logic stores them separately.",
                "Delete Archive Record",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        if (manager.deleteArchiveRecord(archiveID)) {
            JOptionPane.showMessageDialog(this, "Archive record deleted.");
            refreshArchive();
        } else {
            JOptionPane.showMessageDialog(
                    this, "Could not delete archive record.",
                    "Delete Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    // -----------------------------------------------------
    // ROOM FORM
    // -----------------------------------------------------

    private JPanel createRoomForm(
            JTextField number,
            JComboBox<String> type,
            JTextField price,
            JComboBox<String> status,
            JTextField amenities) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(7, 7, 7, 7);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Room Number:"), gbc);

        gbc.gridx = 1;
        panel.add(number, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Room Type:"), gbc);

        gbc.gridx = 1;
        panel.add(type, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Price:"), gbc);

        gbc.gridx = 1;
        panel.add(price, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Status:"), gbc);

        gbc.gridx = 1;
        panel.add(status, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        panel.add(new JLabel("Amenities:"), gbc);

        gbc.gridx = 1;
        panel.add(amenities, gbc);

        return panel;
    }

    // -----------------------------------------------------
    // BASE PANEL
    // -----------------------------------------------------

    private JPanel createBasePanel() {
        JPanel panel = new JPanel(new BorderLayout(12, 12));
        panel.setBackground(CREAM);
        panel.setBorder(BorderFactory.createEmptyBorder(18, 18, 18, 18));
        return panel;
    }

    // -----------------------------------------------------
    // SCROLL PANE
    // -----------------------------------------------------

    private JScrollPane createScrollPane(JTable table) {
        JScrollPane scroll = new JScrollPane(table);
        scroll.setBorder(BorderFactory.createLineBorder(
                new Color(215, 210, 200)));
        return scroll;
    }

    // -----------------------------------------------------
    // TABLE STYLE
    // -----------------------------------------------------

    private void styleTable(JTable table) {
        table.setFont(new Font("SansSerif", Font.PLAIN, 13));
        table.setRowHeight(32);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(225, 213, 180));
        table.setSelectionForeground(Color.BLACK);

        table.getTableHeader().setBackground(NAVY);
        table.getTableHeader().setForeground(WHITE);
        table.getTableHeader().setFont(
                new Font("SansSerif", Font.BOLD, 13));
        table.getTableHeader().setPreferredSize(new Dimension(100, 38));
    }

    // -----------------------------------------------------
    // BUTTON
    // -----------------------------------------------------

    private JButton createButton(String text, Color background) {
        JButton button = new JButton(text);
        button.setFocusPainted(false);
        button.setForeground(WHITE);
        button.setBackground(background);
        button.setFont(new Font("SansSerif", Font.BOLD, 12));
        button.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 16));
        return button;
    }

    // -----------------------------------------------------
    // ROOM CAPACITY
    // -----------------------------------------------------

    private int getCapacity(String roomType) {
        if (roomType == null) {
            return 2;
        }

        if (roomType.equalsIgnoreCase("Superior Twin")
                || roomType.equalsIgnoreCase("Standard Twin")) {
            return 4;
        }

        if (roomType.equalsIgnoreCase("Economy Single")) {
            return 1;
        }

        return 2;
    }
    private JPanel createOccupancyPanel() {
    JPanel panel = createBasePanel();

    occupancyModel = new DefaultTableModel(
            new Object[]{
                "Reservation ID", "Room Number", "Guest Name",
                "Check In", "Check Out", "Status"
            }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    occupancyTable = new JTable(occupancyModel);
    styleTable(occupancyTable);
    panel.add(createScrollPane(occupancyTable), BorderLayout.CENTER);

    JButton refresh = createButton("REFRESH OCCUPANCY", NAVY2);
    refresh.addActionListener(e -> refreshOccupancy());

    JPanel buttons = new JPanel();
    buttons.setOpaque(false);
    buttons.add(refresh);
    panel.add(buttons, BorderLayout.SOUTH);

    return panel;
}

private void refreshOccupancy() {
    if (occupancyModel == null) {
        return;
    }

    occupancyModel.setRowCount(0);

    List<Reservation> reservations = manager.getReservations();

    for (Reservation reservation : reservations) {
        String status = reservation.getStatus();

        if (status == null) {
            status = "";
        }

        // Show reservations that are checked in or still reserved.
        if (status.equalsIgnoreCase("Checked In")
                || status.equalsIgnoreCase("Reserved")
                || status.equalsIgnoreCase("Confirmed")) {

            occupancyModel.addRow(new Object[]{
                reservation.getReservationID(),
                manager.roomNumber(reservation.getRoomID()),
                manager.guestName(reservation.getGuestID()),
                reservation.getCheckInDate(),
                reservation.getCheckOutDate(),
                status
            });
        }
    }
}

    // -----------------------------------------------------
    // LOGOUT
    // -----------------------------------------------------

    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to logout?",
                "Logout",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        dispose();

        SwingUtilities.invokeLater(() -> {
            UserGUI2 login = new UserGUI2();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });
    }

    // -----------------------------------------------------
    // MAIN
    // -----------------------------------------------------

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdminGUI admin = new AdminGUI();
            admin.setVisible(true);
        });
    }
}