package hotelreservationsystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import java.util.ArrayList;
import java.util.List;



public class HotelManager {

    private static HotelManager instance;

    public static HotelManager getInstance() {

        if (instance == null) {
            instance = new HotelManager();
        }

        return instance;
    }

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    private HotelManager() {

        createMissingRoomColumn();
        createArchiveTable();
        seedDatabase();
    }

    // =====================================================
    // DATABASE CONNECTION
    // =====================================================

    private Connection getConnection() {
        return DatabaseConnection.getConnection();
    }

    // =====================================================
    // MAKE SURE ROOM CAPACITY COLUMN EXISTS
    // =====================================================

    private void createMissingRoomColumn() {

        Connection conn = getConnection();

        if (conn == null) {
            return;
        }

        try {

            String sql =
                    "ALTER TABLE rooms " +
                    "ADD COLUMN capacity INT DEFAULT 2";

            Statement stmt = conn.createStatement();

            try {
                stmt.executeUpdate(sql);
            } catch (SQLException e) {
                // Column probably already exists.
            }

            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Room capacity check: "
                    + e.getMessage()
            );
        }
    }

    // =====================================================
    // CREATE ARCHIVE TABLE IF MISSING
    // =====================================================

    private void createArchiveTable() {

        Connection conn = getConnection();

        if (conn == null) {
            return;
        }

        String sql =
                "CREATE TABLE IF NOT EXISTS archive_records (" +
                "archiveID INT AUTO_INCREMENT PRIMARY KEY, " +
                "recordType VARCHAR(50) NOT NULL, " +
                "recordID INT NOT NULL, " +
                "guestName VARCHAR(150), " +
                "roomNumber VARCHAR(50), " +
                "checkInDate DATE NULL, " +
                "checkOutDate DATE NULL, " +
                "totalAmount DECIMAL(10,2) DEFAULT 0, " +
                "downPayment DECIMAL(10,2) DEFAULT 0, " +
                "paymentMethod VARCHAR(50), " +
                "status VARCHAR(50), " +
                "reason VARCHAR(255), " +
                "archivedAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")";

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println(
                    "Archive table error: " + e.getMessage()
            );
            try {
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    // =====================================================
    // SEED DATABASE
    // =====================================================

    private void seedDatabase() {

        Connection conn = getConnection();

        if (conn == null) {
            return;
        }

        try {

            // -------------------------------------------------
            // ADMIN
            // -------------------------------------------------

            String adminCheck =
                    "SELECT COUNT(*) FROM admins " +
                    "WHERE username = ?";

            PreparedStatement adminStmt =
                    conn.prepareStatement(adminCheck);

            adminStmt.setString(1, "admin");

            ResultSet adminResult =
                    adminStmt.executeQuery();

            if (adminResult.next()
                    && adminResult.getInt(1) == 0) {

                String adminInsert =
                        "INSERT INTO admins " +
                        "(firstName, lastName, username, password, position) " +
                        "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement insertAdmin =
                        conn.prepareStatement(adminInsert);

                insertAdmin.setString(1, "Hotel");
                insertAdmin.setString(2, "Admin");
                insertAdmin.setString(3, "admin");
                insertAdmin.setString(4, "admin123");
                insertAdmin.setString(5, "Administrator");

                insertAdmin.executeUpdate();
                insertAdmin.close();
            }

            adminResult.close();
            adminStmt.close();

            // -------------------------------------------------
            // DEFAULT USER
            // -------------------------------------------------

            String userCheck =
                    "SELECT COUNT(*) FROM users " +
                    "WHERE username = ?";

            PreparedStatement userStmt =
                    conn.prepareStatement(userCheck);

            userStmt.setString(1, "user");

            ResultSet userResult =
                    userStmt.executeQuery();

            if (userResult.next()
                    && userResult.getInt(1) == 0) {

                String userInsert =
                        "INSERT INTO users " +
                        "(firstName, lastName, username, password, email, contactNumber) " +
                        "VALUES (?, ?, ?, ?, ?, ?)";

                PreparedStatement insertUser =
                        conn.prepareStatement(userInsert);

                insertUser.setString(1, "User");
                insertUser.setString(2, "Guest");
                insertUser.setString(3, "user");
                insertUser.setString(4, "1234");
                insertUser.setString(5, "user@example.com");
                insertUser.setString(6, "");

                insertUser.executeUpdate();
                insertUser.close();
            }

            userResult.close();
            userStmt.close();

            // -------------------------------------------------
            // ROOMS
            // -------------------------------------------------

            insertRoomIfMissing(
                    conn, "101", "Superior King",
                    3500.00, "Available",
                    "King Bed, A/C, Private Bathroom, Hot & Cold Shower, WiFi, Smart TV, Refrigerator, Coffee & Tea, Safe",
                    2
            );
            insertRoomIfMissing(
        conn, "122", "Economy Basic",
        1200.00, "Available",
        "Basic Bed, A/C, Private Bathroom, WiFi",
        1
);

            insertRoomIfMissing(
                    conn, "102", "Superior Twin",
                    5500.00, "Available",
                    "2 Twin Beds, A/C, Private Bathroom, Hot & Cold Shower, WiFi, Smart TV, Refrigerator, Coffee & Tea, Safe",
                    2
            );

            insertRoomIfMissing(
                    conn, "103", "Superior Queen",
                    3200.00, "Available",
                    "Queen Bed, A/C, Private Bathroom, Hot & Cold Shower, WiFi, Smart TV, Refrigerator, Coffee & Tea, Safe",
                    2
            );

            insertRoomIfMissing(
                    conn, "111", "Standard Single",
                    1800.00, "Available",
                    "Single Bed, A/C, Private Bathroom, Hot & Cold Shower, WiFi, TV",
                    1
            );

            insertRoomIfMissing(
                    conn, "112", "Standard Double",
                    2200.00, "Available",
                    "Double Bed, A/C, Private Bathroom, Hot & Cold Shower, WiFi, TV",
                    2
            );

            insertRoomIfMissing(
                    conn, "113", "Standard Twin",
                    2200.00, "Available",
                    "2 Twin Beds, A/C, Private Bathroom, Hot & Cold Shower, WiFi, TV",
                    2
            );

            insertRoomIfMissing(
                    conn, "121", "Economy Single",
                    1500.00, "Available",
                    "Single Bed, Fan, Private Bathroom, Hot & Cold Shower, WiFi, TV",
                    1
            );

            insertRoomIfMissing(
                    conn, "131", "Junior Suite",
                    7000.00, "Available",
                    "King Bed, A/C, Private Bathroom, Hot & Cold Shower, WiFi, TV, Refrigerator, Coffee & Tea, Work Desk",
                    2
            );

            insertRoomIfMissing(
                    conn, "132", "Presidential Suite",
                    10000.00, "Available",
                    "King Bed, A/C, Private Bathroom, Living Area, Hot & Cold Shower, WiFi, TV, Refrigerator, Coffee & Tea, Dining Area",
                    4
            );

            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Database seed error: "
                    + e.getMessage()
            );
        }
    }

    // =====================================================
    // INSERT ROOM IF MISSING
    // =====================================================

    private void insertRoomIfMissing(
            Connection conn,
            String roomNumber,
            String roomType,
            double price,
            String status,
            String amenities,
            int capacity) throws SQLException {

        String check =
                "SELECT COUNT(*) FROM rooms " +
                "WHERE roomNumber = ?";

        PreparedStatement checkStmt =
                conn.prepareStatement(check);

        checkStmt.setString(1, roomNumber);

        ResultSet result =
                checkStmt.executeQuery();

        boolean exists = false;

        if (result.next()) {
            exists = result.getInt(1) > 0;
        }

        result.close();
        checkStmt.close();

        if (!exists) {

            String insert =
                    "INSERT INTO rooms " +
                    "(roomNumber, roomType, price, roomStatus, amenities, capacity) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement stmt =
                    conn.prepareStatement(insert);

            stmt.setString(1, roomNumber);
            stmt.setString(2, roomType);
            stmt.setDouble(3, price);
            stmt.setString(4, status);
            stmt.setString(5, amenities);
            stmt.setInt(6, capacity);

            stmt.executeUpdate();

            stmt.close();
        }
    }

    // =====================================================
    // GET ROOMS
    // =====================================================

    public List<Room> getRooms() {

        List<Room> rooms = new ArrayList<>();

        Connection conn = getConnection();

        if (conn == null) {
            return rooms;
        }

        String sql =
                "SELECT roomID, roomNumber, roomType, price, " +
                "roomStatus, amenities, capacity " +
                "FROM rooms ORDER BY roomID";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                int roomID =
                        rs.getInt("roomID");

                String roomNumber =
                        rs.getString("roomNumber");

                String roomType =
                        rs.getString("roomType");

                double price =
                        rs.getDouble("price");

                String status =
                        rs.getString("roomStatus");

                String amenities =
                        rs.getString("amenities");

                int capacity =
                        rs.getInt("capacity");

                Room room =
                        new Room(
                                roomID,
                                roomNumber,
                                roomType,
                                price,
                                status,
                                amenities,
                                capacity
                        );

                rooms.add(room);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error loading rooms: "
                    + e.getMessage()
            );
        }

        return rooms;
    }

    // =====================================================
    // GET GUESTS
    // =====================================================

    public List<Guest> getGuests() {

        List<Guest> guests =
                new ArrayList<>();

        Connection conn = getConnection();

        if (conn == null) {
            return guests;
        }

        String sql =
                "SELECT guestID, firstName, lastName, contactNumber " +
                "FROM guests ORDER BY guestID";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Guest guest =
                        new Guest(
                                rs.getInt("guestID"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("contactNumber")
                        );

                guests.add(guest);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error loading guests: "
                    + e.getMessage()
            );
        }

        return guests;
    }

    // =====================================================
    // GET USERS
    // =====================================================

    public List<User> getUsers() {

        List<User> users =
                new ArrayList<>();

        Connection conn = getConnection();

        if (conn == null) {
            return users;
        }

        String sql =
                "SELECT userID, username, password, firstName, lastName " +
                "FROM users ORDER BY userID";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                User user =
                        new User(
                                rs.getInt("userID"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("firstName"),
                                rs.getString("lastName")
                        );

                users.add(user);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error loading users: "
                    + e.getMessage()
            );
        }

        return users;
    }

    // =====================================================
    // GET RESERVATIONS
    // =====================================================

    public List<Reservation> getReservationsForUser(int userID) {

    List<Reservation> reservations = new ArrayList<>();

    String sql = "SELECT reservationID, guestID, roomID, "
            + "checkInDate, checkOutDate, totalAmount, "
            + "downPayment, status FROM reservations "
            + "WHERE userID = ? "
            + "ORDER BY reservationID DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, userID);

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                Reservation reservation = new Reservation(
                        rs.getInt("reservationID"),
                        rs.getInt("guestID"),
                        rs.getInt("roomID"),
                        rs.getString("checkInDate"),
                        rs.getString("checkOutDate"),
                        rs.getDouble("totalAmount"),
                        rs.getDouble("downPayment"),
                        rs.getString("status")
                );

                reservations.add(reservation);
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reservations;
}
    
    public List<Reservation> getReservations() {
    List<Reservation> reservations = new ArrayList<>();

    String sql = "SELECT reservationID, guestID, roomID, "
            + "checkInDate, checkOutDate, totalAmount, "
            + "downPayment, status FROM reservations "
            + "ORDER BY reservationID DESC";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Reservation reservation = new Reservation(
                    rs.getInt("reservationID"),
                    rs.getInt("guestID"),
                    rs.getInt("roomID"),
                    rs.getString("checkInDate"),
                    rs.getString("checkOutDate"),
                    rs.getDouble("totalAmount"),
                    rs.getDouble("downPayment"),
                    rs.getString("status")
            );

            reservations.add(reservation);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return reservations;
}
    // =====================================================
    // GET PAYMENTS
    // =====================================================

    public List<Payment> getPayments() {

        List<Payment> payments =
                new ArrayList<>();

        Connection conn = getConnection();

        if (conn == null) {
            return payments;
        }

        String sql =
                "SELECT paymentID, reservationID, amountPaid, " +
                "paymentMethod, status " +
                "FROM payments ORDER BY paymentID";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Payment payment =
                        new Payment(
                                rs.getInt("paymentID"),
                                rs.getInt("reservationID"),
                                rs.getDouble("amountPaid"),
                                rs.getString("paymentMethod"),
                                rs.getString("status")
                        );

                payments.add(payment);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error loading payments: "
                    + e.getMessage()
            );
        }

        return payments;
    }

    // =====================================================
    // USER LOGIN
    // =====================================================

    public User loginUser(
            String username,
            String password) {

        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        String sql =
                "SELECT userID, username, password, firstName, lastName " +
                "FROM users " +
                "WHERE username = ? AND password = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                User user =
                        new User(
                                rs.getInt("userID"),
                                rs.getString("username"),
                                rs.getString("password"),
                                rs.getString("firstName"),
                                rs.getString("lastName")
                        );

                rs.close();
                stmt.close();
                conn.close();

                return user;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "User login error: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // =====================================================
    // ADMIN LOGIN CHECK
    // =====================================================

    public boolean loginAdmin(
            String username,
            String password) {

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String sql =
                "SELECT adminID FROM admins " +
                "WHERE username = ? AND password = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs =
                    stmt.executeQuery();

            boolean valid = rs.next();

            rs.close();
            stmt.close();
            conn.close();

            return valid;

        } catch (SQLException e) {

            System.out.println(
                    "Admin login error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // USERNAME EXISTS
    // =====================================================

    public boolean usernameExists(
            String username) {

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String sql =
                "SELECT COUNT(*) FROM users " +
                "WHERE username = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs =
                    stmt.executeQuery();

            boolean exists = false;

            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }

            rs.close();
            stmt.close();
            conn.close();

            return exists;

        } catch (SQLException e) {

            System.out.println(
                    "Username check error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // REGISTER USER
    // =====================================================

    public User registerUser(
            String firstName,
        String lastName,
        String username,
        String password,
        String email) {

    if (usernameExists(username)) {
        return null;
    }

    Connection conn = getConnection();

    if (conn == null) {
        System.out.println("Registration failed: Database connection is null.");
        return null;
    }

    String sql = "INSERT INTO users "
            + "(firstName, lastName, username, password, email, contactNumber) "
            + "VALUES (?, ?, ?, ?, ?, ?)";

    try {

        PreparedStatement stmt = conn.prepareStatement(
                sql,
                Statement.RETURN_GENERATED_KEYS
        );

        stmt.setString(1, firstName);
        stmt.setString(2, lastName);
        stmt.setString(3, username);
        stmt.setString(4, password);
        stmt.setString(5, email);
        stmt.setString(6, "");

        int affected = stmt.executeUpdate();

        if (affected == 0) {
            stmt.close();
            conn.close();
            return null;
        }

        ResultSet keys = stmt.getGeneratedKeys();

        int userID = 0;

        if (keys.next()) {
            userID = keys.getInt(1);
        }

        keys.close();
        stmt.close();
        conn.close();

        if (userID == 0) {
            return null;
        }

        return new User(
                userID,
                username,
                password,
                firstName,
                lastName
        );

    } catch (SQLException e) {

        System.out.println(
                "Registration error: " + e.getMessage()
        );

        try {
            conn.close();
        } catch (SQLException ignored) {
        }
    }

    return null;

            
    }

    // =====================================================
    // FIND ROOM
    // =====================================================

    public Room findRoom(int roomID) {

        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        String sql =
                "SELECT roomID, roomNumber, roomType, price, " +
                "roomStatus, amenities, capacity " +
                "FROM rooms WHERE roomID = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, roomID);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                Room room =
                        new Room(
                                rs.getInt("roomID"),
                                rs.getString("roomNumber"),
                                rs.getString("roomType"),
                                rs.getDouble("price"),
                                rs.getString("roomStatus"),
                                rs.getString("amenities"),
                                rs.getInt("capacity")
                        );

                rs.close();
                stmt.close();
                conn.close();

                return room;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Find room error: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // =====================================================
    // FIND ROOM BY NUMBER
    // =====================================================

    public Room findRoomByNumber(
            String roomNumber) {

        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        String sql =
                "SELECT roomID, roomNumber, roomType, price, " +
                "roomStatus, amenities, capacity " +
                "FROM rooms WHERE roomNumber = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, roomNumber);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                Room room =
                        new Room(
                                rs.getInt("roomID"),
                                rs.getString("roomNumber"),
                                rs.getString("roomType"),
                                rs.getDouble("price"),
                                rs.getString("roomStatus"),
                                rs.getString("amenities"),
                                rs.getInt("capacity")
                        );

                rs.close();
                stmt.close();
                conn.close();

                return room;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Find room number error: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // =====================================================
    // FIND GUEST
    // =====================================================

    public Guest findGuest(int guestID) {

        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        String sql =
                "SELECT guestID, firstName, lastName, contactNumber " +
                "FROM guests WHERE guestID = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, guestID);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                Guest guest =
                        new Guest(
                                rs.getInt("guestID"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("contactNumber")
                        );

                rs.close();
                stmt.close();
                conn.close();

                return guest;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Find guest error: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // =====================================================
    // FIND GUEST BY USERNAME
    // =====================================================

    public Guest findGuestByUserName(
            String username) {

        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        String sql =
                "SELECT g.guestID, g.firstName, g.lastName, " +
                "g.contactNumber " +
                "FROM guests g " +
                "INNER JOIN users u ON g.userID = u.userID " +
                "WHERE u.username = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(1, username);

            ResultSet rs =
                    stmt.executeQuery();

            if (rs.next()) {

                Guest guest =
                        new Guest(
                                rs.getInt("guestID"),
                                rs.getString("firstName"),
                                rs.getString("lastName"),
                                rs.getString("contactNumber")
                        );

                rs.close();
                stmt.close();
                conn.close();

                return guest;
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Find guest username error: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // =====================================================
    // CREATE OR FIND GUEST
    // =====================================================

    public Guest createOrFindGuest(
            User user,
            String contact) {

        if (user == null) {
            return null;
        }

        Connection conn = getConnection();

        if (conn == null) {
            return null;
        }

        try {

            // -------------------------------------------------
            // Check existing guest by USER ID
            // -------------------------------------------------

            String find =
                    "SELECT guestID, firstName, lastName, contactNumber " +
                    "FROM guests WHERE userID = ?";

            PreparedStatement findStmt =
                    conn.prepareStatement(find);

            findStmt.setInt(
                    1,
                    user.getUserID()
            );

            ResultSet rs =
                    findStmt.executeQuery();

            if (rs.next()) {

                int guestID =
                        rs.getInt("guestID");

                String update =
                        "UPDATE guests " +
                        "SET firstName = ?, lastName = ?, contactNumber = ? " +
                        "WHERE guestID = ?";

                PreparedStatement updateStmt =
                        conn.prepareStatement(update);

                updateStmt.setString(
                        1,
                        user.getFirstName()
                );

                updateStmt.setString(
                        2,
                        user.getLastName()
                );

                updateStmt.setString(
                        3,
                        contact
                );

                updateStmt.setInt(
                        4,
                        guestID
                );

                updateStmt.executeUpdate();

                updateStmt.close();
                rs.close();
                findStmt.close();
                conn.close();

                return new Guest(
                        guestID,
                        user.getFirstName(),
                        user.getLastName(),
                        contact
                );
            }

            rs.close();
            findStmt.close();

            // -------------------------------------------------
            // Create new guest
            // -------------------------------------------------

            String insert =
                    "INSERT INTO guests " +
                    "(firstName, lastName, contactNumber, userID) " +
                    "VALUES (?, ?, ?, ?)";

            PreparedStatement insertStmt =
                    conn.prepareStatement(
                            insert,
                            Statement.RETURN_GENERATED_KEYS
                    );

            insertStmt.setString(
                    1,
                    user.getFirstName()
            );

            insertStmt.setString(
                    2,
                    user.getLastName()
            );

            insertStmt.setString(
                    3,
                    contact
            );

            insertStmt.setInt(
                    4,
                    user.getUserID()
            );

            insertStmt.executeUpdate();

            ResultSet keys =
                    insertStmt.getGeneratedKeys();

            int guestID = 0;

            if (keys.next()) {
                guestID = keys.getInt(1);
            }

            keys.close();
            insertStmt.close();
            conn.close();

            return new Guest(
                    guestID,
                    user.getFirstName(),
                    user.getLastName(),
                    contact
            );

        } catch (SQLException e) {

            System.out.println(
                    "Create guest error: "
                    + e.getMessage()
            );
        }

        return null;
    }

    // =====================================================
    // CHECK ROOM AVAILABILITY
    // =====================================================

    

    // =====================================================
    // CREATE RESERVATION
    // =====================================================

    public Reservation createReservation(
        int guestID,
        int roomID,
        String checkInDate,
        String checkOutDate,
        String paymentMethod) {

    // Validate the dates first
    LocalDate checkIn;
    LocalDate checkOut;
    LocalDate today = LocalDate.now();

    try {
        checkIn = LocalDate.parse(checkInDate);
        checkOut = LocalDate.parse(checkOutDate);
    } catch (Exception e) {
        System.out.println("Invalid reservation date.");
        return null;
    }

    // Do not allow past check-in dates
    if (checkIn.isBefore(today)) {
        System.out.println("Check-in date cannot be in the past.");
        return null;
    }

    // Check-out must be after check-in
    if (!checkOut.isAfter(checkIn)) {
        System.out.println(
                "Check-out date must be after check-in date."
        );
        return null;
    }

    // Find the room
    Room room = findRoom(roomID);

    if (room == null) {
        return null;
    }

    // Check room availability
        if (!isRoomAvailable(room, checkIn, checkOut)) {
    System.out.println(
            "Room is not available for the selected dates."
    );
    return null;

    }

    // Calculate the total price
    long nights = ChronoUnit.DAYS.between(checkIn, checkOut);

    if (nights <= 0) {
        return null;
    }

    double totalAmount = room.getPrice() * nights;
    double downPayment = totalAmount * 0.30;

    Connection conn = getConnection();

    if (conn == null) {
        return null;
    }

    try {
        conn.setAutoCommit(false);

        // Find the user connected to the guest
        int userID = 0;

        String guestSQL =
                "SELECT userID FROM guests WHERE guestID = ?";

        try (PreparedStatement guestStmt =
                     conn.prepareStatement(guestSQL)) {

            guestStmt.setInt(1, guestID);

            try (ResultSet guestRS = guestStmt.executeQuery()) {
                if (guestRS.next()) {
                    userID = guestRS.getInt("userID");
                }
            }
        }

        // Insert the reservation
        String reservationSQL =
                "INSERT INTO reservations "
                + "(guestID, roomID, userID, checkInDate, checkOutDate, "
                + "totalAmount, downPayment, paymentMethod, status) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int reservationID;

        try (PreparedStatement reservationStmt =
                     conn.prepareStatement(
                             reservationSQL,
                             Statement.RETURN_GENERATED_KEYS)) {

            reservationStmt.setInt(1, guestID);
            reservationStmt.setInt(2, roomID);

            if (userID > 0) {
                reservationStmt.setInt(3, userID);
            } else {
                reservationStmt.setNull(
                        3,
                        java.sql.Types.INTEGER
                );
            }

            reservationStmt.setString(4, checkInDate);
            reservationStmt.setString(5, checkOutDate);
            reservationStmt.setDouble(6, totalAmount);
            reservationStmt.setDouble(7, downPayment);
            reservationStmt.setString(8, paymentMethod);
            reservationStmt.setString(9, "CONFIRMED");

            reservationStmt.executeUpdate();

            try (ResultSet keys = reservationStmt.getGeneratedKeys()) {
                if (!keys.next()) {
                    conn.rollback();
                    return null;
                }

                reservationID = keys.getInt(1);
            }
        }

        // Update the room status
        String roomSQL =
                "UPDATE rooms SET roomStatus = 'Reserved' "
                + "WHERE roomID = ?";

        try (PreparedStatement roomStmt =
                     conn.prepareStatement(roomSQL)) {
            roomStmt.setInt(1, roomID);
            roomStmt.executeUpdate();
        }

        // Record the 30% down payment
        String paymentSQL =
                "INSERT INTO payments "
                + "(reservationID, amountPaid, paymentMethod, status) "
                + "VALUES (?, ?, ?, ?)";

        try (PreparedStatement paymentStmt =
                     conn.prepareStatement(paymentSQL)) {
            paymentStmt.setInt(1, reservationID);
            paymentStmt.setDouble(2, downPayment);
            paymentStmt.setString(3, paymentMethod);
            paymentStmt.setString(4, "PARTIALLY PAID");
            paymentStmt.executeUpdate();
        }

        conn.commit();

        return new Reservation(
                reservationID,
                guestID,
                roomID,
                checkInDate,
                checkOutDate,
                totalAmount,
                downPayment,
                "CONFIRMED"
        );

    } catch (SQLException e) {
        try {
            conn.rollback();
        } catch (SQLException ignored) {
        }

        System.out.println(
                "Create reservation error: " + e.getMessage()
        );

        return null;

    } finally {
        try {
            conn.setAutoCommit(true);
            conn.close();
        } catch (SQLException ignored) {
        }
    }
}
    public Reservation findReservation(int reservationID) {
    String sql = "SELECT reservationID, guestID, roomID, "
            + "checkInDate, checkOutDate, totalAmount, "
            + "downPayment, status "
            + "FROM reservations WHERE reservationID = ?";

    try (Connection conn = getConnection()) {
        if (conn == null) {
            return null;
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, reservationID);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Reservation(
                            rs.getInt("reservationID"),
                            rs.getInt("guestID"),
                            rs.getInt("roomID"),
                            rs.getString("checkInDate"),
                            rs.getString("checkOutDate"),
                            rs.getDouble("totalAmount"),
                            rs.getDouble("downPayment"),
                            rs.getString("status")
                    );
                }
            }
        }
    } catch (SQLException e) {
        System.out.println("Find reservation error: "
                + e.getMessage());
    }

    return null;
}

    // =====================================================
    // DEFAULT CREATE RESERVATION
    // =====================================================

   

    // =====================================================
    // CANCEL RESERVATION WITH REASON AND REFUND RECORD
    // =====================================================

    public boolean cancelReservation(
            int reservationID,
            String cancellationReason) {

        if (cancellationReason == null
                || cancellationReason.trim().isEmpty()) {
            cancellationReason = "No reason provided";
        }

        Reservation reservation = findReservation(reservationID);

        if (reservation == null
                || reservation.getStatus() == null
                || reservation.getStatus().equalsIgnoreCase("Cancelled")) {
            return false;
        }

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        try {
            conn.setAutoCommit(false);

            // Lock the reservation row so two cancellation attempts
            // cannot both process the same reservation at once.
            String lockSQL =
                    "SELECT status FROM reservations "
                    + "WHERE reservationID = ? FOR UPDATE";

            try (PreparedStatement lockStmt = conn.prepareStatement(lockSQL)) {
                lockStmt.setInt(1, reservationID);

                try (ResultSet lockRS = lockStmt.executeQuery()) {
                    if (!lockRS.next()
                            || "Cancelled".equalsIgnoreCase(
                                    lockRS.getString("status"))) {
                        conn.rollback();
                        return false;
                    }
                }
            }

            String guest = guestName(reservation.getGuestID());
            String room = roomNumber(reservation.getRoomID());
            String paymentMethod = "Unknown";

            // Find the payment method and the amount actually recorded as paid.
            double paidAmount = 0.0;
            String paymentSQL =
                    "SELECT paymentMethod, amountPaid "
                    + "FROM payments "
                    + "WHERE reservationID = ? AND status = 'PAID' "
                    + "ORDER BY paymentID";

            try (PreparedStatement paymentStmt =
                    conn.prepareStatement(paymentSQL)) {
                paymentStmt.setInt(1, reservationID);

                try (ResultSet paymentRS = paymentStmt.executeQuery()) {
                    while (paymentRS.next()) {
                        paymentMethod = paymentRS.getString("paymentMethod");
                        paidAmount += paymentRS.getDouble("amountPaid");
                    }
                }
            }

            // Do not create a second refund record if one already exists.
            String refundCheckSQL =
                    "SELECT COUNT(*) FROM payments "
                    + "WHERE reservationID = ? AND status = 'REFUNDED'";

            boolean alreadyRefunded;
            try (PreparedStatement checkStmt =
                    conn.prepareStatement(refundCheckSQL)) {
                checkStmt.setInt(1, reservationID);

                try (ResultSet checkRS = checkStmt.executeQuery()) {
                    alreadyRefunded = checkRS.next() && checkRS.getInt(1) > 0;
                }
            }

            // The booking down payment is 30% of the reservation total.
            // Record a refund only up to the amount actually marked PAID.
            double refundAmount = Math.min(
                    Math.max(0.0, paidAmount),
                    Math.max(0.0, reservation.getDownPayment())
            );

            if (alreadyRefunded) {
                refundAmount = 0.0;
            }

            String updateReservationSQL =
                    "UPDATE reservations SET status = 'Cancelled' "
                    + "WHERE reservationID = ?";

            try (PreparedStatement updateStmt =
                    conn.prepareStatement(updateReservationSQL)) {
                updateStmt.setInt(1, reservationID);

                if (updateStmt.executeUpdate() != 1) {
                    conn.rollback();
                    return false;
                }
            }

            if (refundAmount > 0.0) {
                String refundSQL =
                        "INSERT INTO payments "
                        + "(reservationID, amountPaid, paymentMethod, status) "
                        + "VALUES (?, ?, ?, 'REFUNDED')";

                try (PreparedStatement refundStmt =
                        conn.prepareStatement(refundSQL)) {
                    refundStmt.setInt(1, reservationID);
                    refundStmt.setDouble(2, refundAmount);
                    refundStmt.setString(3, "Refund - " + paymentMethod);
                    refundStmt.executeUpdate();
                }
            }

            // Make the room available only if no other non-cancelled
            // reservation is still attached to that room.
            String activeReservationSQL =
                    "SELECT COUNT(*) FROM reservations "
                    + "WHERE roomID = ? AND status <> 'Cancelled' "
                    + "AND reservationID <> ?";

            boolean hasOtherReservations;
            try (PreparedStatement activeStmt =
                    conn.prepareStatement(activeReservationSQL)) {
                activeStmt.setInt(1, reservation.getRoomID());
                activeStmt.setInt(2, reservationID);

                try (ResultSet activeRS = activeStmt.executeQuery()) {
                    hasOtherReservations =
                            activeRS.next() && activeRS.getInt(1) > 0;
                }
            }

            if (!hasOtherReservations) {
                String roomSQL =
                        "UPDATE rooms SET roomStatus = 'Available' "
                        + "WHERE roomID = ?";

                try (PreparedStatement roomStmt =
                        conn.prepareStatement(roomSQL)) {
                    roomStmt.setInt(1, reservation.getRoomID());
                    roomStmt.executeUpdate();
                }
            }

            // Save the reason in the existing archive_records table.
            String archiveSQL =
                    "INSERT INTO archive_records "
                    + "(recordType, recordID, guestName, roomNumber, "
                    + "checkInDate, checkOutDate, totalAmount, downPayment, "
                    + "paymentMethod, status, reason) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement archiveStmt =
                    conn.prepareStatement(archiveSQL)) {
                archiveStmt.setString(1, "Reservation");
                archiveStmt.setInt(2, reservationID);
                archiveStmt.setString(3, guest);
                archiveStmt.setString(4, room);
                archiveStmt.setString(5, reservation.getCheckInDate());
                archiveStmt.setString(6, reservation.getCheckOutDate());
                archiveStmt.setDouble(7, reservation.getTotalAmount());
                archiveStmt.setDouble(8, reservation.getDownPayment());
                archiveStmt.setString(9, paymentMethod);
                archiveStmt.setString(10, "Cancelled");
                archiveStmt.setString(11, cancellationReason.trim());
                archiveStmt.executeUpdate();
            }

            conn.commit();
            return true;

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ignored) {
            }

            System.out.println("Cancel reservation error: " + e.getMessage());
            return false;

        } finally {
            try {
                conn.setAutoCommit(true);
                conn.close();
            } catch (SQLException ignored) {
            }
        }
    }

    // =====================================================
    // GET ARCHIVE RECORDS
    // =====================================================

    public List<Object[]> getArchiveRecords() {

        List<Object[]> records =
                new ArrayList<>();

        Connection conn = getConnection();

        if (conn == null) {
            return records;
        }

        String sql =
                "SELECT archiveID, recordType, recordID, guestName, " +
                "roomNumber, checkInDate, checkOutDate, totalAmount, " +
                "downPayment, paymentMethod, status, reason, archivedAt " +
                "FROM archive_records " +
                "ORDER BY archiveID DESC";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    stmt.executeQuery();

            while (rs.next()) {

                Object[] row = new Object[13];

                row[0] = rs.getInt("archiveID");
                row[1] = rs.getString("recordType");
                row[2] = rs.getInt("recordID");
                row[3] = rs.getString("guestName");
                row[4] = rs.getString("roomNumber");
                row[5] = rs.getString("checkInDate");
                row[6] = rs.getString("checkOutDate");
                row[7] = rs.getDouble("totalAmount");
                row[8] = rs.getDouble("downPayment");
                row[9] = rs.getString("paymentMethod");
                row[10] = rs.getString("status");
                row[11] = rs.getString("reason");
                row[12] = rs.getString("archivedAt");

                records.add(row);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {

            System.out.println(
                    "Error loading archive: "
                    + e.getMessage()
            );
        }

        return records;
    }

    // =====================================================
    // DELETE ARCHIVE RECORD
    // =====================================================

    public boolean deleteArchiveRecord(int archiveID) {

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String sql =
                "DELETE FROM archive_records " +
                "WHERE archiveID = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setInt(1, archiveID);

            int affected =
                    stmt.executeUpdate();

            stmt.close();
            conn.close();

            return affected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Delete archive error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // UPDATE RESERVATION
    // =====================================================

    public boolean updateReservation(
            Reservation reservation,
            LocalDate checkIn,
            LocalDate checkOut,
            String status) {

        if (reservation == null) {
            return false;
        }

        if (checkIn == null || checkOut == null) {
            return false;
        }

        if (!checkOut.isAfter(checkIn)) {
            return false;
        }

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String sql =
                "UPDATE reservations " +
                "SET checkInDate = ?, " +
                "checkOutDate = ?, " +
                "status = ? " +
                "WHERE reservationID = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    checkIn.toString()
            );

            stmt.setString(
                    2,
                    checkOut.toString()
            );

            stmt.setString(
                    3,
                    status
            );

            stmt.setInt(
                    4,
                    reservation.getReservationID()
            );

            int affected =
                    stmt.executeUpdate();

            stmt.close();
            conn.close();

            return affected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Update reservation error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // ADD ROOM
    // =====================================================

    public boolean addRoom(
            Room room) {

        if (room == null) {
            return false;
        }

        if (findRoomByNumber(
                room.getRoomNumber()
        ) != null) {

            return false;
        }

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String sql =
                "INSERT INTO rooms " +
                "(roomNumber, roomType, price, roomStatus, amenities, capacity) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    room.getRoomNumber()
            );

            stmt.setString(
                    2,
                    room.getRoomType()
            );

            stmt.setDouble(
                    3,
                    room.getPrice()
            );

            stmt.setString(
                    4,
                    room.getRoomStatus()
            );

            stmt.setString(
                    5,
                    room.getAmenities()
            );

            int capacity =
                    getCapacityFromRoomType(
                            room.getRoomType()
                    );

            stmt.setInt(
                    6,
                    capacity
            );

            int affected =
                    stmt.executeUpdate();

            stmt.close();
            conn.close();

            return affected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Add room error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // UPDATE ROOM
    // =====================================================

    public boolean updateRoom(
            Room updatedRoom) {

        if (updatedRoom == null) {
            return false;
        }

        Room existing =
                findRoom(
                        updatedRoom.getRoomID()
                );

        if (existing == null) {
            return false;
        }

        Room duplicate =
                findRoomByNumber(
                        updatedRoom.getRoomNumber()
                );

        if (duplicate != null
                && duplicate.getRoomID()
                != updatedRoom.getRoomID()) {

            return false;
        }

        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String sql =
                "UPDATE rooms " +
                "SET roomNumber = ?, " +
                "roomType = ?, " +
                "price = ?, " +
                "roomStatus = ?, " +
                "amenities = ?, " +
                "capacity = ? " +
                "WHERE roomID = ?";

        try {

            PreparedStatement stmt =
                    conn.prepareStatement(sql);

            stmt.setString(
                    1,
                    updatedRoom.getRoomNumber()
            );

            stmt.setString(
                    2,
                    updatedRoom.getRoomType()
            );

            stmt.setDouble(
                    3,
                    updatedRoom.getPrice()
            );

            stmt.setString(
                    4,
                    updatedRoom.getRoomStatus()
            );

            stmt.setString(
                    5,
                    updatedRoom.getAmenities()
            );

            stmt.setInt(
                    6,
                    getCapacityFromRoomType(
                            updatedRoom.getRoomType()
                    )
            );

            stmt.setInt(
                    7,
                    updatedRoom.getRoomID()
            );

            int affected =
                    stmt.executeUpdate();

            stmt.close();
            conn.close();

            return affected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Update room error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // DELETE ROOM
    // =====================================================

    public boolean deleteRoom(
            int roomID) {

        // Don't delete a room with active reservation
        Connection conn = getConnection();

        if (conn == null) {
            return false;
        }

        String check =
                "SELECT COUNT(*) " +
                "FROM reservations " +
                "WHERE roomID = ? " +
                "AND status <> 'Cancelled'";

        try {

            PreparedStatement checkStmt =
                    conn.prepareStatement(check);

            checkStmt.setInt(
                    1,
                    roomID
            );

            ResultSet rs =
                    checkStmt.executeQuery();

            if (rs.next()
                    && rs.getInt(1) > 0) {

                rs.close();
                checkStmt.close();
                conn.close();

                return false;
            }

            rs.close();
            checkStmt.close();

            String delete =
                    "DELETE FROM rooms " +
                    "WHERE roomID = ?";

            PreparedStatement deleteStmt =
                    conn.prepareStatement(delete);

            deleteStmt.setInt(
                    1,
                    roomID
            );

            int affected =
                    deleteStmt.executeUpdate();

            deleteStmt.close();
            conn.close();

            return affected > 0;

        } catch (SQLException e) {

            System.out.println(
                    "Delete room error: "
                    + e.getMessage()
            );
        }

        return false;
    }

    // =====================================================
    // CAPACITY HELPER
    // =====================================================

    private int getCapacityFromRoomType(
            String roomType) {

        if (roomType == null) {
            return 2;
        }

        if (roomType.equalsIgnoreCase(
                "Superior Twin")) {

            return 4;
        }

        if (roomType.equalsIgnoreCase(
                "Standard Twin")) {

            return 4;
        }

        if (roomType.equalsIgnoreCase(
                "Economy Single")) {

            return 1;
        }

        return 2;
    }

    // =====================================================
    // GET TOTAL PAID FOR RESERVATION
    // =====================================================

    public double getPaidAmount(int reservationID) {

        String sql = "SELECT COALESCE(SUM(amountPaid), 0) AS totalPaid "
            + "FROM payments "
            + "WHERE reservationID = ? "
            + "AND UPPER(status) IN ('PAID', 'PARTIALLY PAID')";

    try (java.sql.Connection conn = DatabaseConnection.getConnection()) {

        if (conn == null) {
            System.out.println("Database connection is null.");
            return 0.0;
        }

        try (java.sql.PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, reservationID);

            try (java.sql.ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    double amount = rs.getDouble("totalPaid");

                    System.out.println(
                            "Reservation ID: " + reservationID
                            + " | Amount paid: " + amount
                    );

                    return amount;
                }
            }
        }

    } catch (java.sql.SQLException e) {
        System.out.println("Error getting amount paid:");
        e.printStackTrace();
    }

    return 0.0;
}

    // =====================================================
    // GET GUEST NAME
    // =====================================================

    public String guestName(
            int guestID) {

        Guest guest =
                findGuest(guestID);

        if (guest == null) {
            return "Unknown Guest";
        }

        return guest.getFullName();
    }

    // =====================================================
    // GET ROOM NUMBER
    // =====================================================

    public String roomNumber(
            int roomID) {

        Room room =
                findRoom(roomID);

        if (room == null) {
            return "Unknown Room";
        }

        return room.getRoomNumber();
    }

    public boolean cancelReservation(Reservation reservation) {
        if (reservation == null) {
            return false;
        }
        return cancelReservation(
                reservation.getReservationID(),
                "No reason provided"
        );
    }
    public boolean deleteReservation(int reservationID) {
    String checkSql =
            "SELECT status FROM reservations WHERE reservationID = ?";
    String paymentSql =
            "SELECT COUNT(*) FROM payments WHERE reservationID = ?";
    String deleteSql =
            "DELETE FROM reservations WHERE reservationID = ?";

    try (Connection conn = getConnection()) {
        if (conn == null) {
            return false;
        }

        try (PreparedStatement check = conn.prepareStatement(checkSql)) {
            check.setInt(1, reservationID);

            try (ResultSet rs = check.executeQuery()) {
                if (!rs.next()
                        || !"Cancelled".equalsIgnoreCase(rs.getString("status"))) {
                    return false;
                }
            }
        }

        try (PreparedStatement check = conn.prepareStatement(paymentSql)) {
            check.setInt(1, reservationID);

            try (ResultSet rs = check.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }
        }

        try (PreparedStatement delete = conn.prepareStatement(deleteSql)) {
            delete.setInt(1, reservationID);
            return delete.executeUpdate() > 0;
        }

    } catch (SQLException e) {
        System.out.println("Delete reservation error: " + e.getMessage());
        return false;
    }
}

public boolean deletePayment(int paymentID) {
    String checkSql =
            "SELECT status FROM payments WHERE paymentID = ?";
    String deleteSql =
            "DELETE FROM payments WHERE paymentID = ?";

    try (Connection conn = getConnection()) {
        if (conn == null) {
            return false;
        }

        try (PreparedStatement check = conn.prepareStatement(checkSql)) {
            check.setInt(1, paymentID);

            try (ResultSet rs = check.executeQuery()) {
                if (!rs.next()) {
                    return false;
                }

                String status = rs.getString("status");

                if ("PAID".equalsIgnoreCase(status)
                        || "REFUNDED".equalsIgnoreCase(status)) {
                    return false;
                }
            }
        }

        try (PreparedStatement delete = conn.prepareStatement(deleteSql)) {
            delete.setInt(1, paymentID);
            return delete.executeUpdate() > 0;
        }

    } catch (SQLException e) {
        System.out.println("Delete payment error: " + e.getMessage());
        return false;
    }
}

    // CHECK ROOM AVAILABILITY USING ROOM OBJECT
    public boolean isRoomAvailable(
               Room room,
        LocalDate checkIn,
        LocalDate checkOut) {

    if (room == null || checkIn == null || checkOut == null) {
        return false;
    }

    if (!checkOut.isAfter(checkIn)) {
        return false;
    }

    if (checkIn.isBefore(LocalDate.now())) {
        return false;
    }

    String sql =
            "SELECT checkInDate, checkOutDate FROM reservations "
            + "WHERE roomID = ? "
            + "AND (status IS NULL OR UPPER(status) <> 'CANCELLED')";

    try (Connection conn = getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, room.getRoomID());

        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {

                LocalDate existingCheckIn =
                        rs.getDate("checkInDate").toLocalDate();

                LocalDate existingCheckOut =
                        rs.getDate("checkOutDate").toLocalDate();

                boolean overlaps =
                        checkIn.isBefore(existingCheckOut)
                        && checkOut.isAfter(existingCheckIn);

                if (overlaps) {
                    return false;
                }
            }
        }

        return true;

    } catch (SQLException | NullPointerException e) {
        System.out.println(
                "Room availability error: " + e.getMessage()
        );
        return false;
    }
}
}

       


    
