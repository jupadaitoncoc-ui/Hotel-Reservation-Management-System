/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hotelreservationsystem;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.function.BiConsumer;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
/**
 *
 * @author User
 */
public class AvailabilityCalendar extends JPanel {
     private YearMonth firstMonth = YearMonth.now();
    private LocalDate checkIn;
    private LocalDate checkOut;

    private final JLabel selectedDates = new JLabel(
            "Select your check-in date, then your check-out date",
            SwingConstants.CENTER
    );

    private final JPanel monthsPanel = new JPanel(new GridLayout(1, 2, 18, 0));

    private final BiConsumer<LocalDate, LocalDate> dateListener;

    private final Color blue = new Color(35, 100, 220);
    private final Color navy = new Color(25, 45, 75);
    private final Color lightBlue = new Color(225, 237, 255);

    public AvailabilityCalendar(
            BiConsumer<LocalDate, LocalDate> dateListener) {

        this.dateListener = dateListener;

        setLayout(new BorderLayout(10, 12));
        setBackground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(Color.WHITE);

        JButton previousButton = new JButton("<");
        JButton nextButton = new JButton(">");

        previousButton.addActionListener(e -> {
            firstMonth = firstMonth.minusMonths(1);
            refreshCalendar();
        });

        nextButton.addActionListener(e -> {
            firstMonth = firstMonth.plusMonths(1);
            refreshCalendar();
        });

        topPanel.add(previousButton, BorderLayout.WEST);
        topPanel.add(selectedDates, BorderLayout.CENTER);
        topPanel.add(nextButton, BorderLayout.EAST);

        selectedDates.setFont(new Font("Arial", Font.BOLD, 14));

        add(topPanel, BorderLayout.NORTH);
        add(monthsPanel, BorderLayout.CENTER);

        refreshCalendar();
    }

    private void refreshCalendar() {
        monthsPanel.removeAll();

        monthsPanel.add(createMonth(firstMonth));
        monthsPanel.add(createMonth(firstMonth.plusMonths(1)));

        monthsPanel.revalidate();
        monthsPanel.repaint();
    }

    private JPanel createMonth(YearMonth month) {
        JPanel panel = new JPanel(new BorderLayout(4, 8));
        panel.setBackground(Color.WHITE);

        JLabel monthTitle = new JLabel(
                month.format(DateTimeFormatter.ofPattern("MMMM yyyy")),
                SwingConstants.CENTER
        );

        monthTitle.setFont(new Font("Arial", Font.BOLD, 17));
        monthTitle.setForeground(navy);
        panel.add(monthTitle, BorderLayout.NORTH);

        JPanel daysPanel = new JPanel(new GridLayout(0, 7, 3, 3));
        daysPanel.setBackground(Color.WHITE);

        String[] weekdays = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        for (String day : weekdays) {
            JLabel label = new JLabel(day, SwingConstants.CENTER);
            label.setFont(new Font("Arial", Font.BOLD, 11));
            label.setForeground(Color.GRAY);
            daysPanel.add(label);
        }

        LocalDate firstDay = month.atDay(1);

        int offset = firstDay.getDayOfWeek().getValue() - DayOfWeek.MONDAY.getValue();

        for (int i = 0; i < offset; i++) {
            daysPanel.add(new JLabel(""));
        }

        for (int day = 1; day <= month.lengthOfMonth(); day++) {
            LocalDate date = month.atDay(day);
            JButton dateButton = new JButton(String.valueOf(day));

            dateButton.setFont(new Font("Arial", Font.PLAIN, 13));
            dateButton.setFocusPainted(false);
            dateButton.setMargin(new java.awt.Insets(3, 1, 3, 1));

            boolean isPast = date.isBefore(LocalDate.now());

            if (isPast) {
                dateButton.setEnabled(false);
                dateButton.setForeground(Color.LIGHT_GRAY);
            } else {
                dateButton.setBackground(Color.WHITE);

                if (date.equals(checkIn) || date.equals(checkOut)) {
                    dateButton.setBackground(blue);
                    dateButton.setForeground(Color.WHITE);
                } else if (checkIn != null && checkOut != null
                        && date.isAfter(checkIn) && date.isBefore(checkOut)) {
                    dateButton.setBackground(lightBlue);
                }

                dateButton.addActionListener(e -> selectDate(date));
            }

            daysPanel.add(dateButton);
        }

        panel.add(daysPanel, BorderLayout.CENTER);
        return panel;
    }

    private void selectDate(LocalDate date) {
        if (checkIn == null || checkOut != null || !date.isAfter(checkIn)) {
            checkIn = date;
            checkOut = null;

            selectedDates.setText(
                    "Check-in: " + checkIn + " | Select check-out"
            );
        } else {
            checkOut = date;

            selectedDates.setText(
                    "Check-in: " + checkIn + " | Check-out: " + checkOut
            );

            if (dateListener != null) {
                dateListener.accept(checkIn, checkOut);
            }
        }

        refreshCalendar();
    }

    public LocalDate getCheckInDate() {
        return checkIn;
    }

    public LocalDate getCheckOutDate() {
        return checkOut;
    }

    public void clearDates() {
        checkIn = null;
        checkOut = null;
        selectedDates.setText(
                "Select your check-in date, then your check-out date"
        );
        refreshCalendar();
    }
    public void setSelectedDates(LocalDate checkIn, LocalDate checkOut) {
    this.checkIn = checkIn;
    this.checkOut = checkOut;

    if (checkIn != null && checkOut != null) {
        firstMonth = YearMonth.from(checkIn);

        selectedDates.setText(
                "Check-in: " + checkIn + " | Check-out: " + checkOut
        );
    } else {
        selectedDates.setText(
                "Select your check-in date, then your check-out date"
        );
    }

    refreshCalendar();
}

    
}
