/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelreservationsystem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
/**
 *
 * @author User
 */
public class RoomEditorFrame extends JFrame {
     private final HotelManager manager = HotelManager.getInstance();
    private final Room existingRoom;
    private final Runnable onSave;

    private JTextField roomNumberField;
    private JComboBox<String> roomTypeCombo;
    private JTextField priceField;
    private JComboBox<String> statusCombo;
    private JTextField amenitiesField;

    private final Color NAVY = new Color(19, 31, 52);
    private final Color GOLD = new Color(198, 158, 74);
    private final Color CREAM = new Color(247, 243, 235);

    public RoomEditorFrame(Room room, Runnable onSave) {
        this.existingRoom = room;
        this.onSave = onSave;

        setTitle("A MAN HOTEL - Edit Room");
        setSize(550, 420);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        buildEditor();
        setVisible(true);
    }

    private void buildEditor() {
        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBackground(CREAM);
        main.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel title = new JLabel("EDIT ROOM DETAILS");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(NAVY);
        main.add(title, BorderLayout.NORTH);

        JPanel form = new JPanel(new GridLayout(5, 2, 12, 15));
        form.setBackground(CREAM);

        roomNumberField = new JTextField(existingRoom.getRoomNumber());

        roomTypeCombo = new JComboBox<>(new String[]{
            "Superior King",
            "Superior Twin",
            "Superior Queen",
            "Standard Double",
            "Standard Twin",
            "Economy Single",
            "Economy Double",
            "Junior Suite",
            "Presidential Suite"
        });
        roomTypeCombo.setEditable(true);
        roomTypeCombo.setSelectedItem(existingRoom.getRoomType());

        priceField = new JTextField(
            String.valueOf(existingRoom.getPrice())
        );

        statusCombo = new JComboBox<>(new String[]{
            "Available",
            "Reserved",
            "Occupied",
            "Unavailable"
        });
        statusCombo.setEditable(true);
        statusCombo.setSelectedItem(existingRoom.getRoomStatus());

        amenitiesField = new JTextField(existingRoom.getAmenities());

        form.add(new JLabel("Room Number:"));
        form.add(roomNumberField);

        form.add(new JLabel("Room Type:"));
        form.add(roomTypeCombo);

        form.add(new JLabel("Price (PHP):"));
        form.add(priceField);

        form.add(new JLabel("Room Status:"));
        form.add(statusCombo);

        form.add(new JLabel("Amenities:"));
        form.add(amenitiesField);

        main.add(form, BorderLayout.CENTER);

        JButton saveButton = new JButton("SAVE CHANGES");
        saveButton.setBackground(NAVY);
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);

        JButton cancelButton = new JButton("CANCEL");
        cancelButton.setBackground(GOLD);
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFocusPainted(false);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttons.setBackground(CREAM);
        buttons.add(cancelButton);
        buttons.add(saveButton);

        main.add(buttons, BorderLayout.SOUTH);

        saveButton.addActionListener(e -> saveRoom());
        cancelButton.addActionListener(e -> dispose());

        setContentPane(main);
    }

    private void saveRoom() {
        String number = roomNumberField.getText().trim();
        String type = String.valueOf(
            roomTypeCombo.getSelectedItem()
        ).trim();
        String priceText = priceField.getText().trim();
        String status = String.valueOf(
            statusCombo.getSelectedItem()
        ).trim();
        String amenities = amenitiesField.getText().trim();

        if (number.isEmpty() || type.isEmpty()
                || priceText.isEmpty() || status.isEmpty()
                || amenities.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please complete all fields.",
                "Missing Information",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        try {
            double price = Double.parseDouble(priceText);

            if (price <= 0) {
                JOptionPane.showMessageDialog(
                    this,
                    "Price must be greater than zero.",
                    "Invalid Price",
                    JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            int capacity = getCapacity(type);

            Room updated = new Room(
                existingRoom.getRoomID(),
                number,
                type,
                price,
                status,
                amenities,
                capacity
            );

            if (manager.updateRoom(updated)) {
                JOptionPane.showMessageDialog(
                    this,
                    "Room details updated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
                );

                if (onSave != null) {
                    onSave.run();
                }

                dispose();

            } else {
                JOptionPane.showMessageDialog(
                    this,
                    "Unable to update the room. "
                    + "The room number may already exist.",
                    "Update Failed",
                    JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                this,
                "Please enter a valid numeric price.",
                "Invalid Price",
                JOptionPane.WARNING_MESSAGE
            );
        }
    }

    private int getCapacity(String type) {
        if (type == null) {
            return 2;
        }

        if (type.equalsIgnoreCase("Superior Twin")
                || type.equalsIgnoreCase("Standard Twin")) {
            return 4;
        }

        if (type.equalsIgnoreCase("Economy Single")) {
            return 1;
        }

        return 2;
    }

}
