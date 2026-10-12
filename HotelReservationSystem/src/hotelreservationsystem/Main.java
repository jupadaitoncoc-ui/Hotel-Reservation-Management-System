package hotelreservationsystem;

import javax.swing.SwingUtilities;

/**
 *
 * @author User
 */
public class Main {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {
            UserGUI2 login = new UserGUI2();
            login.setLocationRelativeTo(null);
            login.setVisible(true);
        });

    }
}