/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package hotelreservationsystem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
/**
 *
 * @author User
 */
 public class RoomFrameNew extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(RoomFrameNew.class.getName());

    /**
     * Creates new form RoomFrameNew
     */
    private javax.swing.JPanel roomsPanel;

// old design helpers
private javax.swing.JPanel oldRoomsPanel;
private javax.swing.JPanel oldTop;
private javax.swing.JPanel oldButtons;
private javax.swing.JPanel oldWrapper;
private javax.swing.JPanel[] oldRooms;
private java.awt.Component[] oldOriginalComponents;
private java.awt.Rectangle[] oldOriginalBounds;
private boolean oldGridMode = false;
private java.awt.Dimension oldPanelSize;

public RoomFrameNew() {
    initComponents();
    setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

    // ===== OLD DESIGN: save where everything sits right now =====
    oldOriginalComponents = jPanel11.getComponents();
    oldOriginalBounds = new java.awt.Rectangle[oldOriginalComponents.length];
    for (int i = 0; i < oldOriginalComponents.length; i++) {
        oldOriginalBounds[i] = oldOriginalComponents[i].getBounds();
    }
    java.awt.Dimension oldSize = jPanel11.getSize();
    if (oldSize.width > 0 && oldSize.height > 0) {
        jPanel11.setPreferredSize(oldSize);
        oldPanelSize = jPanel11.getPreferredSize();
    }

    oldRooms = new javax.swing.JPanel[] {
        jPanel12, jPanel13, jPanel14, jPanel15, jPanel16, jPanel17,
        jPanel18, jPanel19, jPanel20, jPanel21, jPanel22
    };

    oldRoomsPanel = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 50, 20));
    oldRoomsPanel.setOpaque(false);

    oldWrapper = new javax.swing.JPanel(new java.awt.BorderLayout());
oldWrapper.setOpaque(false);
oldWrapper.add(oldRoomsPanel, java.awt.BorderLayout.WEST);

    oldButtons = new javax.swing.JPanel(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 40, 5));
    oldButtons.setOpaque(false);

    oldTop = new javax.swing.JPanel(new java.awt.BorderLayout());
    oldTop.setOpaque(false);

    // ===== NEW DESIGN (jPanel2) =====
    roomsPanel = new javax.swing.JPanel();
    roomsPanel.setLayout(new java.awt.GridLayout(0, 4, 15, 15));

    roomsPanel.add(jPanel3);
    roomsPanel.add(jPanel4);
    roomsPanel.add(jPanel5);
    roomsPanel.add(jPanel6);
    roomsPanel.add(jPanel7);
    roomsPanel.add(jPanel8);
    roomsPanel.add(jPanel9);
    roomsPanel.add(jPanel10);

    javax.swing.JPanel wrapper = new javax.swing.JPanel(new java.awt.BorderLayout());
    wrapper.add(roomsPanel, java.awt.BorderLayout.NORTH);

    javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(wrapper);
    scrollPane.setVerticalScrollBarPolicy(
            javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    scrollPane.getVerticalScrollBar().setUnitIncrement(20);
    scrollPane.setHorizontalScrollBarPolicy(
            javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

    javax.swing.JPanel topPanel = new javax.swing.JPanel();
    topPanel.setLayout(new javax.swing.BoxLayout(
            topPanel, javax.swing.BoxLayout.Y_AXIS));

    javax.swing.JPanel titlePanel = new javax.swing.JPanel(
            new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    titlePanel.add(jLabel4);
    titlePanel.add(jLabel5);

    javax.swing.JPanel categoryPanel = new javax.swing.JPanel(
            new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
    categoryPanel.add(jLabel3);
    categoryPanel.add(jButton5);
    categoryPanel.add(jButton6);
    categoryPanel.add(jButton7);
    categoryPanel.add(jButton8);

    jButton5.addActionListener(e -> showRoomCategory("All Rooms"));
    jButton6.addActionListener(e -> showRoomCategory("Superior"));
    jButton7.addActionListener(e -> showRoomCategory("Standard"));
    jButton8.addActionListener(e -> showRoomCategory("Economy"));

    topPanel.add(titlePanel);
    topPanel.add(categoryPanel);

    jPanel2.removeAll();
    jPanel2.setLayout(new java.awt.BorderLayout(10, 10));
    jPanel2.add(topPanel, java.awt.BorderLayout.NORTH);
    jPanel2.add(scrollPane, java.awt.BorderLayout.CENTER);

    jPanel2.revalidate();
    jPanel2.repaint();

    setVisible(true);




}
    private void filterRooms(String category) {

    javax.swing.JPanel[] rooms = {
        jPanel3, jPanel4, jPanel5, jPanel6,
        jPanel7, jPanel8, jPanel9, jPanel10
    };

    for (javax.swing.JPanel room : rooms) {

        boolean show = category.equalsIgnoreCase("All Rooms");

        if (!show) {
            show = hasCategoryLabel(room, category);
        }

        room.setVisible(show);
    }

    jPanel2.revalidate();
    jPanel2.repaint();
}

private boolean hasCategoryLabel(
        java.awt.Container parent, String category) {

    for (java.awt.Component component : parent.getComponents()) {

        if (component instanceof javax.swing.JLabel) {
            String text = ((javax.swing.JLabel) component).getText();

            if (text != null &&
                    text.trim().equalsIgnoreCase(category)) {
                return true;
            }
        }

        if (component instanceof java.awt.Container) {
            if (hasCategoryLabel(
                    (java.awt.Container) component, category)) {
                return true;
            }
        }
    }

    return false;

}
private void showRoomCategory(String category) {
    boolean all = category.equalsIgnoreCase("All Rooms");

    // New design cards
    javax.swing.JPanel[] rooms = {
        jPanel3, jPanel4, jPanel5, jPanel6,
        jPanel7, jPanel8, jPanel9, jPanel10
    };

    roomsPanel.removeAll();
    for (javax.swing.JPanel room : rooms) {
        if (all || hasCategoryLabel(room, category)) {
            roomsPanel.add(room);
        }
    }
    roomsPanel.revalidate();
    roomsPanel.repaint();

    // Old design cards
    if (all) {
        showOldAllRooms();
    } else {
        showOldCategory(category);
    }
}

// All Rooms: bring back your original design exactly as it was
private void showOldAllRooms() {
    
    if (!oldGridMode) {
      jPanel11.setPreferredSize(new java.awt.Dimension(
                getWidth() - 40, oldPanelSize.height));
        jPanel1.revalidate();
        oldGridMode = true;   return; // already showing the original design
    }

    jPanel11.removeAll();
    jPanel11.setLayout(null);
    jPanel11.setLayout(null);
jLabel67.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
    for (int i = 0; i < oldOriginalComponents.length; i++) {
        jPanel11.add(oldOriginalComponents[i]);
        oldOriginalComponents[i].setBounds(oldOriginalBounds[i]);
    }
    oldGridMode = false;

    jPanel11.revalidate();
    jPanel11.repaint();
       oldGridMode = false;
    jPanel11.setPreferredSize(oldPanelSize);
    jPanel1.revalidate();
}
   
 

// Other categories: show only the matching cards in a clean grid
private void showOldCategory(String category) {
    if (!oldGridMode) {
        jPanel11.removeAll();
        jPanel11.setLayout(new java.awt.BorderLayout(10, 10));

        oldButtons.removeAll();
        oldButtons.add(btnAllRooms);
        oldButtons.add(btnSuperior);
        oldButtons.add(btnStandard);
        oldButtons.add(btnEconomy);

        oldTop.removeAll();
        oldTop.add(jLabel67, java.awt.BorderLayout.NORTH);
        oldTop.add(oldButtons, java.awt.BorderLayout.CENTER);

        jPanel11.add(oldTop, java.awt.BorderLayout.NORTH);
        jPanel11.add(oldWrapper, java.awt.BorderLayout.CENTER);
        oldGridMode = true;
    }

    oldRoomsPanel.removeAll();
    for (javax.swing.JPanel room : oldRooms) {
        if (hasCategoryLabel(room, category)) {
            room.setPreferredSize(new java.awt.Dimension(340, 290)); // card size
            oldRoomsPanel.add(room);
        }
    }

    // The row is exactly as wide as the window, so FlowLayout.CENTER
    // puts the cards in the middle of what you actually see
    oldRoomsPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 20));
    oldRoomsPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(40, 0, 0, 0));
    oldRoomsPanel.setPreferredSize(new java.awt.Dimension(
            getContentPane().getWidth(), 500));

    oldRoomsPanel.revalidate();
    oldRoomsPanel.repaint();
    jPanel11.revalidate();
    jPanel11.repaint();
}

 



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        btnBooknow1 = new javax.swing.JButton();
        jLabel153 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        btnAllRooms = new javax.swing.JButton();
        btnSuperior = new javax.swing.JButton();
        btnStandard = new javax.swing.JButton();
        btnEconomy = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        jLabel76 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        btnBookNow6 = new javax.swing.JButton();
        jLabel154 = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        btnBookNow2 = new javax.swing.JButton();
        jLabel156 = new javax.swing.JLabel();
        jPanel15 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jLabel95 = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        jLabel97 = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        btnBookNow7 = new javax.swing.JButton();
        jLabel155 = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jLabel100 = new javax.swing.JLabel();
        jLabel101 = new javax.swing.JLabel();
        jLabel102 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        jLabel104 = new javax.swing.JLabel();
        jLabel105 = new javax.swing.JLabel();
        jLabel107 = new javax.swing.JLabel();
        btnBookNow3 = new javax.swing.JButton();
        jLabel157 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel108 = new javax.swing.JLabel();
        jLabel109 = new javax.swing.JLabel();
        jLabel110 = new javax.swing.JLabel();
        jLabel111 = new javax.swing.JLabel();
        jLabel112 = new javax.swing.JLabel();
        jLabel113 = new javax.swing.JLabel();
        jLabel115 = new javax.swing.JLabel();
        btnBookNow8 = new javax.swing.JButton();
        jLabel158 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel116 = new javax.swing.JLabel();
        jLabel117 = new javax.swing.JLabel();
        jLabel118 = new javax.swing.JLabel();
        jLabel119 = new javax.swing.JLabel();
        jLabel120 = new javax.swing.JLabel();
        jLabel121 = new javax.swing.JLabel();
        jLabel122 = new javax.swing.JLabel();
        jButton27 = new javax.swing.JButton();
        jLabel159 = new javax.swing.JLabel();
        jPanel19 = new javax.swing.JPanel();
        jLabel114 = new javax.swing.JLabel();
        jLabel123 = new javax.swing.JLabel();
        jLabel124 = new javax.swing.JLabel();
        jLabel125 = new javax.swing.JLabel();
        jLabel126 = new javax.swing.JLabel();
        jLabel127 = new javax.swing.JLabel();
        jLabel128 = new javax.swing.JLabel();
        btnBookNow4 = new javax.swing.JButton();
        jPanel20 = new javax.swing.JPanel();
        jLabel129 = new javax.swing.JLabel();
        jLabel130 = new javax.swing.JLabel();
        jLabel131 = new javax.swing.JLabel();
        jLabel132 = new javax.swing.JLabel();
        jLabel133 = new javax.swing.JLabel();
        jLabel134 = new javax.swing.JLabel();
        jLabel135 = new javax.swing.JLabel();
        btnBookNow5 = new javax.swing.JButton();
        jLabel98 = new javax.swing.JLabel();
        jLabel161 = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        jLabel136 = new javax.swing.JLabel();
        jLabel137 = new javax.swing.JLabel();
        jLabel138 = new javax.swing.JLabel();
        jLabel139 = new javax.swing.JLabel();
        jLabel140 = new javax.swing.JLabel();
        jLabel141 = new javax.swing.JLabel();
        jLabel142 = new javax.swing.JLabel();
        btnBookNow9 = new javax.swing.JButton();
        jLabel160 = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jLabel143 = new javax.swing.JLabel();
        jLabel144 = new javax.swing.JLabel();
        jLabel145 = new javax.swing.JLabel();
        jLabel146 = new javax.swing.JLabel();
        jLabel147 = new javax.swing.JLabel();
        jLabel148 = new javax.swing.JLabel();
        jLabel149 = new javax.swing.JLabel();
        btnBookNow10 = new javax.swing.JButton();
        jLabel106 = new javax.swing.JLabel();
        jLabel162 = new javax.swing.JLabel();
        jLabel150 = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        jLabel151 = new javax.swing.JLabel();
        jLabel152 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jButton9 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jButton11 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jButton12 = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        jLabel44 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jButton15 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 0));
        jLabel1.setText("A MAN HOTEL");

        jLabel2.setFont(new java.awt.Font("Yu Gothic UI Semibold", 2, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(242, 242, 242));
        jLabel2.setText("COMFORT • RELAXATION • MEMORIES");

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setText("HOME");
        jButton1.addActionListener(this::jButton1ActionPerformed);

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("MY RESERVATIONS");
        jButton3.addActionListener(this::jButton3ActionPerformed);

        jPanel12.setBackground(new java.awt.Color(204, 204, 204));

        jLabel68.setBackground(new java.awt.Color(0, 51, 102));
        jLabel68.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(0, 51, 102));
        jLabel68.setText("101 Superior King");

        jLabel69.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(0, 0, 255));
        jLabel69.setText("Superior");

        jLabel70.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel70.setText("₱3,500/ night");

        jLabel71.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel71.setText("King Bed, A/C, Private Bathroom");

        jLabel72.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel72.setText("Hot & Cold Shower");

        jLabel73.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel73.setText("WI-FI, Smart TV, Refrigerator");

        jLabel74.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel74.setText("Coffee & Tea, Safe");

        jLabel75.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(0, 153, 0));
        jLabel75.setText("Available");

        btnBooknow1.setBackground(new java.awt.Color(0, 51, 102));
        btnBooknow1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBooknow1.setForeground(new java.awt.Color(242, 242, 242));
        btnBooknow1.setText("BOOK NOW");
        btnBooknow1.addActionListener(this::btnBooknow1ActionPerformed);

        jLabel153.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel153.setText("Maximum capacity: 2guests");

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel68, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel72)
                            .addComponent(jLabel73)
                            .addComponent(jLabel69, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel75, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnBooknow1))
                            .addComponent(jLabel70, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel71, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel12Layout.createSequentialGroup()
                                .addComponent(jLabel153, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel68)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel69)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel70)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel72)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel73)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel74)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel153)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBooknow1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(23, 23, 23))
        );

        jLabel67.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(0, 51, 102));
        jLabel67.setText("Choose a room category to explore available rooms.");

        btnAllRooms.setBackground(new java.awt.Color(0, 51, 102));
        btnAllRooms.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnAllRooms.setForeground(new java.awt.Color(242, 242, 242));
        btnAllRooms.setText("All Rooms");
        btnAllRooms.addActionListener(this::btnAllRoomsActionPerformed);

        btnSuperior.setBackground(new java.awt.Color(0, 51, 102));
        btnSuperior.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSuperior.setForeground(new java.awt.Color(242, 242, 242));
        btnSuperior.setText("Superior Rooms");
        btnSuperior.addActionListener(this::btnSuperiorActionPerformed);

        btnStandard.setBackground(new java.awt.Color(0, 51, 102));
        btnStandard.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnStandard.setForeground(new java.awt.Color(242, 242, 242));
        btnStandard.setText("Standard Rooms");
        btnStandard.addActionListener(this::btnStandardActionPerformed);

        btnEconomy.setBackground(new java.awt.Color(0, 51, 102));
        btnEconomy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnEconomy.setForeground(new java.awt.Color(242, 242, 242));
        btnEconomy.setText("Economy Rooms");
        btnEconomy.addActionListener(this::btnEconomyActionPerformed);

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));

        jLabel76.setBackground(new java.awt.Color(0, 51, 102));
        jLabel76.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(0, 51, 102));
        jLabel76.setText("102 Superior Twin");

        jLabel77.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(0, 0, 255));
        jLabel77.setText("Superior");

        jLabel78.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel78.setText("₱5,500/ night");

        jLabel79.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel79.setText("2 Twin Beds, A/C, Private Bathroom");

        jLabel80.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel80.setText("Hot & Cold Shower");

        jLabel81.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel81.setText("WI-FI, Smart TV, Refrigerator");

        jLabel82.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel82.setText("Coffee & Tea, Safe");

        jLabel83.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(51, 153, 0));
        jLabel83.setText("Available");

        btnBookNow6.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow6.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow6.setText("BOOK NOW");
        btnBookNow6.addActionListener(this::btnBookNow6ActionPerformed);

        jLabel154.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel154.setText("Maximum capacity: 4 guests");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel76, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel77, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel78, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(btnBookNow6))
                            .addComponent(jLabel154))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel76)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel77)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel78)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel79)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel80)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel81)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel82)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel154)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBookNow6, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(17, 17, 17))
        );

        jPanel14.setBackground(new java.awt.Color(204, 204, 204));

        jLabel84.setBackground(new java.awt.Color(0, 51, 102));
        jLabel84.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel84.setForeground(new java.awt.Color(0, 51, 102));
        jLabel84.setText("103 Superior Queen");

        jLabel85.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel85.setForeground(new java.awt.Color(0, 0, 255));
        jLabel85.setText("Superior");

        jLabel86.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel86.setText("₱3,200/ night");

        jLabel87.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel87.setText("Queen Bed, A/C, Private Bathroom");

        jLabel88.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel88.setText("Hot & Cold Shower");

        jLabel89.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel89.setText("WI-FI, Smart TV, Refrigerator");

        jLabel90.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel90.setText("Coffee & Tea, Safe");

        jLabel91.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(51, 153, 0));
        jLabel91.setText("Available");

        btnBookNow2.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow2.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow2.setText("BOOK NOW");
        btnBookNow2.addActionListener(this::btnBookNow2ActionPerformed);

        jLabel156.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel156.setText("Maximum capacity: 2 guests");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89)
                            .addComponent(jLabel85, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel86, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel90, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(43, 43, 43))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel156)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel91, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBookNow2)
                        .addGap(23, 23, 23))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel85)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel86)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel87)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel88)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel156)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBookNow2)
                    .addComponent(jLabel91))
                .addGap(19, 19, 19))
        );

        jPanel15.setBackground(new java.awt.Color(204, 204, 204));

        jLabel92.setBackground(new java.awt.Color(0, 51, 102));
        jLabel92.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(0, 51, 102));
        jLabel92.setText("111 Standard Single");

        jLabel93.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel93.setForeground(new java.awt.Color(255, 0, 0));
        jLabel93.setText("Standard");

        jLabel94.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel94.setText("₱1,800/ night");

        jLabel95.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel95.setText("Single Bed, A/C, Private Bathroom");

        jLabel96.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel96.setText("Hot & Cold Shower");

        jLabel97.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel97.setText("WI-FI, TV");

        jLabel99.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(51, 153, 0));
        jLabel99.setText("Available");

        btnBookNow7.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow7.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow7.setText("BOOK NOW");
        btnBookNow7.addActionListener(this::btnBookNow7ActionPerformed);

        jLabel155.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel155.setText("Maximum capacity: 1-2 guests");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel99, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(btnBookNow7))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96)
                            .addComponent(jLabel97)
                            .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel155, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel92)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel93)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel97)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel155)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel99)
                    .addComponent(btnBookNow7))
                .addGap(21, 21, 21))
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jLabel100.setBackground(new java.awt.Color(0, 51, 102));
        jLabel100.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel100.setForeground(new java.awt.Color(0, 51, 102));
        jLabel100.setText("112 Standard Double");

        jLabel101.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 51, 0));
        jLabel101.setText("Standard");

        jLabel102.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel102.setText("₱2,200/ night");

        jLabel103.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel103.setText("Double Bed, A/C, Private Bathroom");

        jLabel104.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel104.setText("Hot & Cold Shower");

        jLabel105.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel105.setText("WI-FI, TV");

        jLabel107.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel107.setForeground(new java.awt.Color(51, 153, 0));
        jLabel107.setText("Available");

        btnBookNow3.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow3.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow3.setText("BOOK NOW");
        btnBookNow3.addActionListener(this::btnBookNow3ActionPerformed);

        jLabel157.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel157.setText("Maximum capacity: 2 guests");

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addComponent(jLabel100)
                .addGap(0, 50, Short.MAX_VALUE))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel107, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnBookNow3))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel103)
                        .addComponent(jLabel104)
                        .addComponent(jLabel105)
                        .addComponent(jLabel101, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel102, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel157, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel100)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel102)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel103)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel104)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel105)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel157)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel107)
                    .addComponent(btnBookNow3))
                .addGap(21, 21, 21))
        );

        jPanel17.setBackground(new java.awt.Color(204, 204, 204));

        jLabel108.setBackground(new java.awt.Color(0, 51, 102));
        jLabel108.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel108.setForeground(new java.awt.Color(0, 51, 102));
        jLabel108.setText("113 Standard Twin");

        jLabel109.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel109.setForeground(new java.awt.Color(255, 51, 0));
        jLabel109.setText("Standard");

        jLabel110.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel110.setText("₱2,200/ night");

        jLabel111.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel111.setText("2 Twin Beds, A/C, Private Bathroom");

        jLabel112.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel112.setText("Hot & Cold Shower");

        jLabel113.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel113.setText("WI-FI, TV");

        jLabel115.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel115.setForeground(new java.awt.Color(51, 153, 0));
        jLabel115.setText("Available");

        btnBookNow8.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow8.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow8.setText("BOOK NOW");
        btnBookNow8.addActionListener(this::btnBookNow8ActionPerformed);

        jLabel158.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel158.setText("Maximum capacity: 2 guests");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel115, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBookNow8)
                .addGap(37, 37, 37))
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel108, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel111)
                            .addComponent(jLabel112)
                            .addComponent(jLabel113)
                            .addComponent(jLabel109, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel110, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel158)))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel108)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel109)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel110)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel111)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel112)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel113)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel158)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel115, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBookNow8, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(27, 27, 27))
        );

        jPanel18.setBackground(new java.awt.Color(204, 204, 204));

        jLabel116.setBackground(new java.awt.Color(0, 51, 102));
        jLabel116.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel116.setForeground(new java.awt.Color(0, 51, 102));
        jLabel116.setText("121 Economy Single");

        jLabel117.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel117.setForeground(new java.awt.Color(102, 102, 0));
        jLabel117.setText("Economy");

        jLabel118.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel118.setText("₱1,500/ night");

        jLabel119.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel119.setText("Single Bed, Fan, Private Bathroom");

        jLabel120.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel120.setText("Hot & Cold Shower");

        jLabel121.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel121.setText("WI-FI, TV");

        jLabel122.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel122.setForeground(new java.awt.Color(51, 153, 0));
        jLabel122.setText("Available");

        jButton27.setBackground(new java.awt.Color(0, 51, 102));
        jButton27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton27.setForeground(new java.awt.Color(242, 242, 242));
        jButton27.setText("BOOK NOW");
        jButton27.addActionListener(this::jButton27ActionPerformed);

        jLabel159.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel159.setText("Maximum capacity: 1 guest");

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel120, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel121, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel117, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel118, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(160, Short.MAX_VALUE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel119, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel122, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton27)
                        .addGap(34, 34, 34))))
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel116, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel159)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel116)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel117)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel118)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel119)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel120)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel121)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel159)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                        .addComponent(jLabel122))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton27)))
                .addGap(17, 17, 17))
        );

        jPanel19.setBackground(new java.awt.Color(204, 204, 204));
        jPanel19.setPreferredSize(new java.awt.Dimension(273, 258));

        jLabel114.setBackground(new java.awt.Color(0, 51, 102));
        jLabel114.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel114.setForeground(new java.awt.Color(0, 51, 102));
        jLabel114.setText("121 Economy Single");

        jLabel123.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel123.setForeground(new java.awt.Color(204, 153, 0));
        jLabel123.setText("Economy");

        jLabel124.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel124.setText("₱1,500/ night");

        jLabel125.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel125.setText("Single Twin Bed, A/C, Private Bathroom");

        jLabel126.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel126.setText("Cold Shower");

        jLabel127.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel127.setText("WI-FI, TV");

        jLabel128.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel128.setForeground(new java.awt.Color(51, 255, 0));
        jLabel128.setText("Available");

        btnBookNow4.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow4.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow4.setText("BOOK NOW");
        btnBookNow4.addActionListener(this::btnBookNow4ActionPerformed);

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel114, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel128, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(btnBookNow4))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel126)
                            .addComponent(jLabel127)
                            .addComponent(jLabel123, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel125, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel124, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel114)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel123)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel124)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel125)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addComponent(jLabel126)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel127)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel128))
                    .addGroup(jPanel19Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnBookNow4)))
                .addGap(31, 31, 31))
        );

        jPanel20.setBackground(new java.awt.Color(204, 204, 204));
        jPanel20.setPreferredSize(new java.awt.Dimension(273, 258));

        jLabel129.setBackground(new java.awt.Color(0, 51, 102));
        jLabel129.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel129.setForeground(new java.awt.Color(0, 51, 102));
        jLabel129.setText("131 Junior Suite");

        jLabel130.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel130.setForeground(new java.awt.Color(255, 102, 102));
        jLabel130.setText("Suite");

        jLabel131.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel131.setText("₱7,000/ night");

        jLabel132.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel132.setText("King Bed, A/C, Private Bathroom");

        jLabel133.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel133.setText("Hot & Cold Shower");

        jLabel134.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel134.setText("WI-FI, TV, Refregirator");

        jLabel135.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel135.setForeground(new java.awt.Color(51, 153, 0));
        jLabel135.setText("Available");

        btnBookNow5.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow5.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow5.setText("BOOK NOW");
        btnBookNow5.addActionListener(this::btnBookNow5ActionPerformed);

        jLabel98.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel98.setText("Coffee & Tea, Work Desk");

        jLabel161.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel161.setText("Maximum capacity: 4 guests");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel129, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35))
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addComponent(jLabel135, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(btnBookNow5))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel20Layout.createSequentialGroup()
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel133, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel134, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel130, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel131, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 70, Short.MAX_VALUE)))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel132)
                            .addComponent(jLabel98, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel161))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel129)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel130)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel131)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel132)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel133)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel134)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel98)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel161)
                .addGap(24, 24, 24)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel135)
                    .addComponent(btnBookNow5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel21.setBackground(new java.awt.Color(204, 204, 204));
        jPanel21.setPreferredSize(new java.awt.Dimension(273, 258));

        jLabel136.setBackground(new java.awt.Color(0, 51, 102));
        jLabel136.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel136.setText("122 Economy Basic");

        jLabel137.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel137.setForeground(new java.awt.Color(153, 153, 0));
        jLabel137.setText("Economy");

        jLabel138.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel138.setText("₱1,200/ night");

        jLabel139.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel139.setText("Single Twin Bed, A/C, Private Bathroom");

        jLabel140.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel140.setText("Cold Shower");

        jLabel141.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel141.setText("WI-FI, TV");

        jLabel142.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel142.setForeground(new java.awt.Color(51, 153, 0));
        jLabel142.setText("Available");

        btnBookNow9.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow9.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow9.setText("BOOK NOW");
        btnBookNow9.addActionListener(this::btnBookNow9ActionPerformed);

        jLabel160.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel160.setText("Maximum capacity: 1 guest");

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel21Layout.createSequentialGroup()
                                .addComponent(jLabel142, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(btnBookNow9))
                            .addComponent(jLabel140, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel141, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel137, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel138, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addGap(48, 48, 48)
                        .addComponent(jLabel136, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel139, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel21Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel160)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel136)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel137)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel138)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel139)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel140)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel141)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel160)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel142, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnBookNow9, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(31, 31, 31))
        );

        jPanel22.setBackground(new java.awt.Color(204, 204, 204));
        jPanel22.setPreferredSize(new java.awt.Dimension(273, 258));

        jLabel143.setBackground(new java.awt.Color(0, 51, 102));
        jLabel143.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel143.setForeground(new java.awt.Color(0, 51, 102));
        jLabel143.setText("Presedential Suite");

        jLabel144.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel144.setForeground(new java.awt.Color(255, 102, 102));
        jLabel144.setText("Suite");

        jLabel145.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel145.setText("₱10,000/ night");

        jLabel146.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel146.setText("King Bed, A/C, Private Bathroom, Living Area");

        jLabel147.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel147.setText("Hot & Cold Shower");

        jLabel148.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel148.setText("WI-FI, TV, Refregirator");

        jLabel149.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel149.setForeground(new java.awt.Color(51, 153, 0));
        jLabel149.setText("Available");

        btnBookNow10.setBackground(new java.awt.Color(0, 51, 102));
        btnBookNow10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnBookNow10.setForeground(new java.awt.Color(242, 242, 242));
        btnBookNow10.setText("BOOK NOW");
        btnBookNow10.addActionListener(this::btnBookNow10ActionPerformed);

        jLabel106.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel106.setText("Coffee & Tea, Dining Area");

        jLabel162.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel162.setText("Maximum capacity: 6 guests");

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                                .addComponent(jLabel149, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                                .addComponent(btnBookNow10))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel22Layout.createSequentialGroup()
                                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel147, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel148, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel144, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel145, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(43, 43, 43))
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel146, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel22Layout.createSequentialGroup()
                        .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel106, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel162))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel143, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel143)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel144)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel145)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel146)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel147)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel148)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel106)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel162)
                .addGap(24, 24, 24)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel149)
                    .addComponent(btnBookNow10))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel67, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(btnAllRooms)
                        .addGap(18, 18, 18)
                        .addComponent(btnSuperior)
                        .addGap(18, 18, 18)
                        .addComponent(btnStandard)
                        .addGap(18, 18, 18)
                        .addComponent(btnEconomy)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 299, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel67)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAllRooms)
                    .addComponent(btnSuperior)
                    .addComponent(btnStandard)
                    .addComponent(btnEconomy))
                .addGap(39, 39, 39)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                                    .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE))
                                .addGap(27, 27, 27)))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)
                            .addComponent(jPanel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jLabel150.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel150.setForeground(new java.awt.Color(242, 242, 242));
        jLabel150.setText("A MAN HOTEL");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 96, Short.MAX_VALUE)
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel151.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel151.setForeground(new java.awt.Color(242, 242, 242));
        jLabel151.setText("Your Comfort, Our Priority");

        jLabel152.setText("jLabel152");

        jButton4.setBackground(new java.awt.Color(255, 255, 0));
        jButton4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4.setText("LOGOUT");
        jButton4.addActionListener(this::jButton4ActionPerformed);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton4)
                .addGap(1066, 1066, 1066))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 766, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel150, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel152, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(333, 333, 333)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(645, 645, 645)
                        .addComponent(jLabel151, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1)
                            .addComponent(jButton3)
                            .addComponent(jButton4))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel150)
                    .addComponent(jLabel152))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel151)
                .addGap(27, 27, 27)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1634, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Choose a room category to explore available rooms");

        jButton5.setBackground(new java.awt.Color(0, 51, 102));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(242, 242, 242));
        jButton5.setText("All Rooms");

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton6.setText("Superior Rooms");

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton7.setText("Standard Rooms");

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton8.setText("Economy Rooms");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setText("OUR ROOMS");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Comfortable stays for every traveler");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("101 Superior King");

        jLabel7.setBackground(new java.awt.Color(255, 255, 0));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 255));
        jLabel7.setText("Superior");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel8.setText("₱ 3,500/ night");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 102));
        jLabel9.setText("King Bed, A/C, Private bathroom");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Hot & Cold Shower");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("Wifi, Smart TV, Refrigerator");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Coffee & Tea");

        jLabel13.setBackground(new java.awt.Color(102, 255, 0));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 255, 0));
        jLabel13.setText("✅ Available");

        jButton9.setBackground(new java.awt.Color(0, 51, 102));
        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton9.setForeground(new java.awt.Color(242, 242, 242));
        jButton9.setText("RESERVE");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(112, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jButton9))
                .addGap(40, 40, 40))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 102));
        jLabel14.setText("106 Superior");

        jLabel15.setBackground(new java.awt.Color(255, 255, 0));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 255));
        jLabel15.setText("Superior");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel16.setText("₱ 5,500/ night");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 102));
        jLabel17.setText("2 Twin Beds, A/C, Private bathroom");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 102));
        jLabel18.setText("Hot & Cold Shower");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 102));
        jLabel19.setText("Wifi, Smart TV, Refrigerator");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 102));
        jLabel20.setText("Coffee & Tea, Safe, Work Desk");

        jLabel21.setBackground(new java.awt.Color(102, 255, 0));
        jLabel21.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 255, 0));
        jLabel21.setText("✅ Available");

        jButton10.setBackground(new java.awt.Color(0, 51, 102));
        jButton10.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton10.setForeground(new java.awt.Color(242, 242, 242));
        jButton10.setText("RESERVE");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jButton10))
                .addGap(40, 40, 40))
        );

        jLabel22.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 102));
        jLabel22.setText("102 Superior Twin");

        jLabel23.setBackground(new java.awt.Color(255, 255, 0));
        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 51, 255));
        jLabel23.setText("Superior");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel24.setText("₱ 4,500/ night");

        jLabel25.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(0, 51, 102));
        jLabel25.setText("2 Twin Beds, A/C, Private bathroom");

        jLabel26.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 51, 102));
        jLabel26.setText("Hot & Cold Shower");

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 51, 102));
        jLabel27.setText("Wifi, Smart TV, Refrigerator");

        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(0, 51, 102));
        jLabel28.setText("Coffee & Tea, Safe");

        jLabel29.setBackground(new java.awt.Color(102, 255, 0));
        jLabel29.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(102, 255, 0));
        jLabel29.setText("✅ Available");

        jButton11.setBackground(new java.awt.Color(0, 51, 102));
        jButton11.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton11.setForeground(new java.awt.Color(242, 242, 242));
        jButton11.setText("RESERVE");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel23)
                            .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(112, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel23)
                .addGap(18, 18, 18)
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel25)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(jButton11))
                .addGap(40, 40, 40))
        );

        jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 51, 102));
        jLabel30.setText("109 Superior Deluxe");

        jLabel31.setBackground(new java.awt.Color(255, 255, 0));
        jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(0, 0, 255));
        jLabel31.setText("Superior");

        jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel32.setText("₱ 6,000/ night");

        jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(0, 51, 102));
        jLabel33.setText("1 King + 1 Single Bed, A/C, Private bathroom");

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel34.setForeground(new java.awt.Color(0, 51, 102));
        jLabel34.setText("Hot & Cold Shower");

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(0, 51, 102));
        jLabel35.setText("Wifi, Smart TV, Refrigerator");

        jLabel36.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 51, 102));
        jLabel36.setText("Coffee & Tea, Safe, Sofa");

        jLabel37.setBackground(new java.awt.Color(102, 255, 0));
        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(102, 255, 0));
        jLabel37.setText("✅ Available");

        jButton12.setBackground(new java.awt.Color(0, 51, 102));
        jButton12.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton12.setForeground(new java.awt.Color(242, 242, 242));
        jButton12.setText("RESERVE");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel30)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel32)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel33)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel36)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(jButton12))
                .addGap(40, 40, 40))
        );

        jLabel38.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 51, 102));
        jLabel38.setText("111 Standard Single");

        jLabel39.setBackground(new java.awt.Color(255, 255, 0));
        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(153, 0, 204));
        jLabel39.setText("Standard");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel40.setText("₱ 1,800/ night");

        jLabel41.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(0, 51, 102));
        jLabel41.setText("Single Bed, A/C, Private bathroom");

        jLabel42.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 51, 102));
        jLabel42.setText("Cold Shower");

        jLabel43.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel43.setForeground(new java.awt.Color(0, 51, 102));
        jLabel43.setText("Wifi, Smart TV");

        jLabel45.setBackground(new java.awt.Color(102, 255, 0));
        jLabel45.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(102, 255, 0));
        jLabel45.setText("✅ Available");

        jButton13.setBackground(new java.awt.Color(0, 51, 102));
        jButton13.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton13.setForeground(new java.awt.Color(242, 242, 242));
        jButton13.setText("RESERVE");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41)
                            .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel39, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel38)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(jLabel40)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel42)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel43)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(jButton13))
                .addGap(40, 40, 40))
        );

        jLabel44.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel44.setForeground(new java.awt.Color(0, 51, 102));
        jLabel44.setText("112 Standard Double");

        jLabel46.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel46.setText("₱ 2,200/ night");

        jLabel48.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(102, 51, 255));
        jLabel48.setText("Standard");

        jLabel49.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel49.setText("Double Bed, A/C, Private bathroom");

        jLabel50.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel50.setText("Hot & Cold Shower");

        jLabel51.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel51.setText("WI-FI, TV");

        jLabel52.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel52.setForeground(new java.awt.Color(0, 255, 0));
        jLabel52.setText("✅ Available");

        jButton14.setBackground(new java.awt.Color(0, 51, 102));
        jButton14.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton14.setForeground(new java.awt.Color(242, 242, 242));
        jButton14.setText("RESERVE");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(69, 69, 69)
                        .addComponent(jLabel44))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel48, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel49, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel46, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel50, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel51, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(69, Short.MAX_VALUE))
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel44)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel48)
                .addGap(18, 18, 18)
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel46)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel49)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel50)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel51)
                .addGap(36, 36, 36)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel52)
                    .addComponent(jButton14))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setPreferredSize(new java.awt.Dimension(333, 222));

        jLabel53.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel53.setForeground(new java.awt.Color(0, 51, 102));
        jLabel53.setText("113 Standard Twin");

        jLabel54.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(102, 0, 204));
        jLabel54.setText("Standard");

        jLabel55.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel55.setText("₱ 2,200/ night");

        jLabel56.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel56.setText("2 Twin Beds, A/C, Private bathroom");

        jLabel57.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel57.setText("Hot & Cold Shower");

        jLabel58.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel58.setText("WI-FI, TV");

        jLabel59.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(51, 255, 0));
        jLabel59.setText("✅ Available");

        jButton15.setBackground(new java.awt.Color(0, 51, 102));
        jButton15.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton15.setForeground(new java.awt.Color(242, 242, 242));
        jButton15.setText("RESERVE");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel58, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel57, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE))
                                    .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel59, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton15))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel53, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(61, 61, 61))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel53)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel55)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel56)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel58)
                .addGap(25, 25, 25)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jButton15))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel60.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(0, 51, 102));
        jLabel60.setText("114 Economy Single");

        jLabel61.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(204, 204, 204));
        jLabel61.setText("Economy");

        jLabel62.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel62.setText("₱ 1,500/ night");

        jLabel63.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel63.setText("Single Bed, Fan, Bathroom");

        jLabel64.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel64.setText("Hot & Cold Shower");

        jLabel65.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel65.setText("WI-FI, TV");

        jLabel66.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(51, 255, 51));
        jLabel66.setText("✅ Available");

        jButton16.setBackground(new java.awt.Color(0, 51, 102));
        jButton16.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton16.setForeground(new java.awt.Color(242, 242, 242));
        jButton16.setText("RESERVE");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel61, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel63, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel64, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel65, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel66, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton16))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 74, Short.MAX_VALUE)
                        .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel61)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel62)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel63)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addGap(40, 40, 40)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(jButton16))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jButton5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7))
                            .addComponent(jLabel3))
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8)
                                .addGap(216, 216, 216)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(385, 385, 385)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(879, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(1901, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(31, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 3806, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBookNow7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow7ActionPerformed
     Bookingframe booking = new Bookingframe(
    "Standard Single",
    "₱1800.00",
    2
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow7ActionPerformed

    private void btnAllRoomsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAllRoomsActionPerformed
      
    showRoomCategory("All Rooms");

  // TODO add your handling code here:
    }//GEN-LAST:event_btnAllRoomsActionPerformed

    private void btnSuperiorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuperiorActionPerformed
     showRoomCategory("Superior");   // TODO add your handling code here:
    }//GEN-LAST:event_btnSuperiorActionPerformed

    private void btnStandardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStandardActionPerformed
     showRoomCategory("Standard");   // TODO add your handling code here:
    }//GEN-LAST:event_btnStandardActionPerformed

    private void btnEconomyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEconomyActionPerformed
     showRoomCategory("Economy");   // TODO add your handling code here:
    }//GEN-LAST:event_btnEconomyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       SecondFrame login = new SecondFrame();
    login.setLocationRelativeTo(null);
    login.setVisible(true);
    this.dispose();   // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      User user = UserGUI2.getCurrentUser();

ReservationsFrame reservations = new ReservationsFrame(user);
reservations.setLocationRelativeTo(null);
reservations.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void btnBooknow1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBooknow1ActionPerformed
     Bookingframe booking = new Bookingframe(
    "Superior King",
    "₱3500.00",
    2
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBooknow1ActionPerformed

    private void btnBookNow2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow2ActionPerformed
     Bookingframe booking = new Bookingframe(
    "Superior Queen",
    "₱3200.00",
    2
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow2ActionPerformed

    private void btnBookNow3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow3ActionPerformed
     Bookingframe booking = new Bookingframe(
    "Standard Double",
    "₱2200.00",
    2
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow3ActionPerformed

    private void btnBookNow4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow4ActionPerformed
Bookingframe booking = new Bookingframe(
    "Economy Single",
    "₱1500.00",
    1
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);
   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow4ActionPerformed

    private void btnBookNow5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow5ActionPerformed
    Bookingframe booking = new Bookingframe(
    "Junior Suite",
    "₱7000.00",
    4
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow5ActionPerformed

    private void btnBookNow6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow6ActionPerformed
     Bookingframe booking = new Bookingframe(
    "Superior Twin",
    "₱5500.00",
    4
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow6ActionPerformed

    private void btnBookNow8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow8ActionPerformed
    Bookingframe booking = new Bookingframe(
    "Standard Twin",
    "₱2200.00",
    2
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow8ActionPerformed

    private void btnBookNow9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow9ActionPerformed
     Bookingframe booking = new Bookingframe(
    "Economy Basic",
    "₱1200.00",
    1
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);   // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow9ActionPerformed

    private void btnBookNow10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBookNow10ActionPerformed
    Bookingframe booking = new Bookingframe(
    "Presidential Suite",
    "₱10,000.00",
    6
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_btnBookNow10ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      int confirm = javax.swing.JOptionPane.showConfirmDialog(
        this,
        "Are you sure you want to logout?",
        "Confirm Logout",
        javax.swing.JOptionPane.YES_NO_OPTION,
        javax.swing.JOptionPane.QUESTION_MESSAGE
    );

    if (confirm == javax.swing.JOptionPane.YES_OPTION) {
        UserGUI2 home = new UserGUI2();
        home.setLocationRelativeTo(null);
        home.setVisible(true);
        this.dispose();
    }// TODO add your handling code here:
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
    Bookingframe booking = new Bookingframe(
    "Economy Single",
    "₱1600.00",
    4
);
booking.setLocationRelativeTo(null);
booking.setVisible(true);    // TODO add your handling code here:
    }//GEN-LAST:event_jButton27ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new RoomFrameNew().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAllRooms;
    private javax.swing.JButton btnBookNow10;
    private javax.swing.JButton btnBookNow2;
    private javax.swing.JButton btnBookNow3;
    private javax.swing.JButton btnBookNow4;
    private javax.swing.JButton btnBookNow5;
    private javax.swing.JButton btnBookNow6;
    private javax.swing.JButton btnBookNow7;
    private javax.swing.JButton btnBookNow8;
    private javax.swing.JButton btnBookNow9;
    private javax.swing.JButton btnBooknow1;
    private javax.swing.JButton btnEconomy;
    private javax.swing.JButton btnStandard;
    private javax.swing.JButton btnSuperior;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel104;
    private javax.swing.JLabel jLabel105;
    private javax.swing.JLabel jLabel106;
    private javax.swing.JLabel jLabel107;
    private javax.swing.JLabel jLabel108;
    private javax.swing.JLabel jLabel109;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel110;
    private javax.swing.JLabel jLabel111;
    private javax.swing.JLabel jLabel112;
    private javax.swing.JLabel jLabel113;
    private javax.swing.JLabel jLabel114;
    private javax.swing.JLabel jLabel115;
    private javax.swing.JLabel jLabel116;
    private javax.swing.JLabel jLabel117;
    private javax.swing.JLabel jLabel118;
    private javax.swing.JLabel jLabel119;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel120;
    private javax.swing.JLabel jLabel121;
    private javax.swing.JLabel jLabel122;
    private javax.swing.JLabel jLabel123;
    private javax.swing.JLabel jLabel124;
    private javax.swing.JLabel jLabel125;
    private javax.swing.JLabel jLabel126;
    private javax.swing.JLabel jLabel127;
    private javax.swing.JLabel jLabel128;
    private javax.swing.JLabel jLabel129;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel130;
    private javax.swing.JLabel jLabel131;
    private javax.swing.JLabel jLabel132;
    private javax.swing.JLabel jLabel133;
    private javax.swing.JLabel jLabel134;
    private javax.swing.JLabel jLabel135;
    private javax.swing.JLabel jLabel136;
    private javax.swing.JLabel jLabel137;
    private javax.swing.JLabel jLabel138;
    private javax.swing.JLabel jLabel139;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel140;
    private javax.swing.JLabel jLabel141;
    private javax.swing.JLabel jLabel142;
    private javax.swing.JLabel jLabel143;
    private javax.swing.JLabel jLabel144;
    private javax.swing.JLabel jLabel145;
    private javax.swing.JLabel jLabel146;
    private javax.swing.JLabel jLabel147;
    private javax.swing.JLabel jLabel148;
    private javax.swing.JLabel jLabel149;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel150;
    private javax.swing.JLabel jLabel151;
    private javax.swing.JLabel jLabel152;
    private javax.swing.JLabel jLabel153;
    private javax.swing.JLabel jLabel154;
    private javax.swing.JLabel jLabel155;
    private javax.swing.JLabel jLabel156;
    private javax.swing.JLabel jLabel157;
    private javax.swing.JLabel jLabel158;
    private javax.swing.JLabel jLabel159;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel160;
    private javax.swing.JLabel jLabel161;
    private javax.swing.JLabel jLabel162;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JLabel jLabel98;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
