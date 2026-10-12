package hotelreservationsystem;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UITheme {

    // =========================================================
    // COLORS
    // =========================================================

    public static final Color NAVY = new Color(15, 35, 55);
    public static final Color NAVY2 = new Color(25, 55, 80);
    public static final Color GOLD = new Color(212, 175, 55);
    public static final Color CREAM = new Color(245, 242, 235);
    public static final Color GREEN = new Color(46, 125, 50);
    public static final Color RED = new Color(180, 50, 50);
    public static final Color WHITE = Color.WHITE;
    public static final Color LIGHT_GRAY = new Color(235, 235, 235);

    // =========================================================
    // FRAME SETUP
    // =========================================================

    public static void setupFrame(JFrame frame, String title, int width, int height) {

        frame.setTitle(title);

        frame.setSize(width, height);
        frame.setMinimumSize(new Dimension(1000, 650));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setLocationRelativeTo(null);

        // Open maximized
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        frame.getContentPane().setBackground(CREAM);
    }

    // =========================================================
    // COLORS
    // =========================================================

    public static Color getNavy() {
        return NAVY;
    }

    public static Color getNavy2() {
        return NAVY2;
    }

    public static Color getGold() {
        return GOLD;
    }

    public static Color getCream() {
        return CREAM;
    }

    public static Color getGreen() {
        return GREEN;
    }

    public static Color getRed() {
        return RED;
    }

    // =========================================================
    // FONTS
    // =========================================================

    // Default title font
    public static Font titleFont() {
        return new Font("Serif", Font.BOLD, 24);
    }

    // Title font with custom size
    // This is used by UserGUI, for example:
    // UITheme.titleFont(23)
    // UITheme.titleFont(40)
    // UITheme.titleFont(34)
    public static Font titleFont(int size) {
        return new Font("Serif", Font.BOLD, size);
    }

    // Normal font with custom size
    public static Font normalFont(int size) {
        return new Font("Arial", Font.PLAIN, size);
    }

    // =========================================================
    // LABEL
    // =========================================================

    public static JLabel createTitle(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Serif", Font.BOLD, 28));
        label.setForeground(GOLD);

        return label;
    }

    public static JLabel createLabel(String text) {

        JLabel label = new JLabel(text);

        label.setFont(new Font("Arial", Font.PLAIN, 15));
        label.setForeground(NAVY);

        return label;
    }

    // Label used by UserGUI
    static JLabel label(String text, int size, boolean bold) {

        JLabel label = new JLabel(text);

        if (bold) {
            label.setFont(
                new Font("Arial", Font.BOLD, size)
            );
        } else {
            label.setFont(
                new Font("Arial", Font.PLAIN, size)
            );
        }

        label.setForeground(NAVY);

        return label;
    }

    // =========================================================
    // BUTTON
    // =========================================================

    public static JButton createButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(WHITE);
        button.setBackground(NAVY);

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
            BorderFactory.createEmptyBorder(
                10, 20, 10, 20
            )
        );

        return button;
    }

    // Gold button
    public static JButton createGoldButton(String text) {

        JButton button = new JButton(text);

        button.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(NAVY);
        button.setBackground(GOLD);

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
            BorderFactory.createEmptyBorder(
                10, 20, 10, 20
            )
        );

        return button;
    }

    // Button used by UserGUI
    static JButton button(String text) {

        JButton button = new JButton(text);

        button.setFont(
            new Font("Arial", Font.BOLD, 14)
        );

        button.setForeground(WHITE);
        button.setBackground(NAVY);

        button.setFocusPainted(false);
        button.setOpaque(true);

        button.setBorder(
            BorderFactory.createEmptyBorder(
                8, 15, 8, 15
            )
        );

        return button;
    }

    // =========================================================
    // TEXT FIELD
    // =========================================================

    public static JTextField createTextField() {

        JTextField field = new JTextField();

        field.setFont(
            new Font("Arial", Font.PLAIN, 15)
        );

        field.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(200, 200, 200)
                ),
                new EmptyBorder(
                    8, 10, 8, 10
                )
            )
        );

        return field;
    }

    // Text field used by UserGUI
    static JTextField field() {

        JTextField field = new JTextField();

        field.setFont(
            new Font("Arial", Font.PLAIN, 15)
        );

        field.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(
                    new Color(200, 200, 200)
                ),
                new EmptyBorder(
                    8, 10, 8, 10
                )
            )
        );

        return field;
    }

    // =========================================================
    // PANEL
    // =========================================================

    public static JPanel createPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(CREAM);

        return panel;
    }

    // =========================================================
    // NAVIGATION PANEL
    // =========================================================

    public static JPanel createNavPanel() {

        JPanel panel = new JPanel();

        panel.setBackground(NAVY);

        panel.setBorder(
            new EmptyBorder(
                10, 15, 10, 15
            )
        );

        return panel;
    }

    // =========================================================
    // CENTER WINDOW
    // =========================================================

    public static void centerWindow(Window window) {

        window.setLocationRelativeTo(null);
    }
}