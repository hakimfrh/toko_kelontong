/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko.kelontong;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author SYNSGATS
 */
public class TRANSAKSI2 extends javax.swing.JPanel {

    /**
     * Creates new form TRANSAKSI2
     */
    public TRANSAKSI2() {
        initComponents();
        BUTTON.setVisible(true);
        Rtrnski.setVisible(true);
        Dtrnski.setVisible(false);
        tabletransaksi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BUTTON = new javax.swing.JPanel();
        lbl_Rtransaksi = new javax.swing.JLabel();
        lbl_Dtransaksi = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        Rtrnski = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_transaksi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Dtrnski = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtcari = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbl_tanggal = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_detail_transaksi = new javax.swing.JTable();
        lbl_cari = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(879, 448));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BUTTON.setBackground(new java.awt.Color(255, 255, 255));
        BUTTON.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_Rtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat transaksi on.png"))); // NOI18N
        lbl_Rtransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Rtransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_RtransaksiMouseClicked(evt);
            }
        });
        BUTTON.add(lbl_Rtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, -1, -1));

        lbl_Dtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail transaksi off.png"))); // NOI18N
        lbl_Dtransaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_Dtransaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_DtransaksiMouseClicked(evt);
            }
        });
        BUTTON.add(lbl_Dtransaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/submenu.png"))); // NOI18N
        BUTTON.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 160, 460));

        add(BUTTON, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 160, -1));

        Rtrnski.setBackground(new java.awt.Color(255, 255, 255));
        Rtrnski.setLayout(null);

        tbl_transaksi.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tbl_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_transaksi.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tbl_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_transaksi);

        Rtrnski.add(jScrollPane1);
        jScrollPane1.setBounds(40, 90, 660, 310);

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Riwayat Transaksi");
        Rtrnski.add(jLabel1);
        jLabel1.setBounds(10, 30, 205, 31);

        add(Rtrnski, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 800, 440));

        Dtrnski.setBackground(new java.awt.Color(255, 255, 255));
        Dtrnski.setLayout(null);

        jLabel2.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel2.setText("Detail Transaksi");
        Dtrnski.add(jLabel2);
        jLabel2.setBounds(30, 30, 205, 31);

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setText("Kode Struk       :");
        Dtrnski.add(jLabel3);
        jLabel3.setBounds(100, 120, 120, 30);
        Dtrnski.add(txtcari);
        txtcari.setBounds(210, 120, 140, 30);

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setText("Tanggal            :");
        Dtrnski.add(jLabel4);
        jLabel4.setBounds(100, 170, 110, 17);

        lbl_tanggal.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        lbl_tanggal.setText("XXX- XX - XX");
        lbl_tanggal.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                lbl_tanggalInputMethodTextChanged(evt);
            }
        });
        Dtrnski.add(lbl_tanggal);
        lbl_tanggal.setBounds(210, 170, 140, 17);

        tbl_detail_transaksi.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        tbl_detail_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tbl_detail_transaksi);

        Dtrnski.add(jScrollPane2);
        jScrollPane2.setBounds(100, 220, 570, 90);

        lbl_cari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bt cari.png"))); // NOI18N
        lbl_cari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_cari.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_cariMouseClicked(evt);
            }
        });
        Dtrnski.add(lbl_cari);
        lbl_cari.setBounds(360, 110, 60, 50);

        add(Dtrnski, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 0, 720, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void tabletransaksi(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode Struk");
        model.addColumn("Total Barang");
        model.addColumn("Total Harga");
        model.addColumn("Tanggal");
        try {
            int no=1;
            String sql = "SELECT * FROM transaksi";
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            while (res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)});
                }
            tbl_transaksi.setModel(model);
            }catch (Exception e) {             
        }
    }

   
    private void tbl_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_transaksiMouseClicked
        // TODO add your handling code here:
        int baris = tbl_transaksi.rowAtPoint(evt.getPoint());
        String kdstruk = tbl_transaksi.getValueAt(baris, 0).toString();
        txtcari.setText(kdstruk);
        Rtrnski.setVisible(false);
        Dtrnski.setVisible(true);
        lbl_Rtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat transaksi off.png")));
        lbl_Dtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail transaksi on.png")));
    }//GEN-LAST:event_tbl_transaksiMouseClicked

    private void lbl_RtransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_RtransaksiMouseClicked
        // TODO add your handling code here:
        Rtrnski.setVisible(true);
        Dtrnski.setVisible(false);
        lbl_Rtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat transaksi on.png")));
        lbl_Dtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail transaksi off.png")));
    }//GEN-LAST:event_lbl_RtransaksiMouseClicked

    private void lbl_DtransaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_DtransaksiMouseClicked
        // TODO add your handling code here:
        Dtrnski.setVisible(true);
        Rtrnski.setVisible(false);
        lbl_Rtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat transaksi off.png")));
        lbl_Dtransaksi.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/detail transaksi on.png")));
    }//GEN-LAST:event_lbl_DtransaksiMouseClicked

    private void lbl_cariMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_cariMouseClicked
        // TODO add your handling code here:
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Nama Barang");
        model.addColumn("Total Barang");
        model.addColumn("Harga");
        model.addColumn("Total");
        try {
            int no=1;
            String sql = "select stok_barang.nama_barang, detail_transaksi.qty,\n" +
                        "detail_transaksi.sub_total, transaksi.total_harga\n" +
                        "from detail_transaksi\n" +
                        "join stok_barang\n" +
                        "on stok_barang.kode_barang = detail_transaksi.kode_barang \n" +
                        "join transaksi\n" +
                        "on transaksi.kode_struk = detail_transaksi.kode_struk\n" +
                        "where detail_transaksi.kode_struk ='"+txtcari.getText()+"'\n" +
                        "group by stok_barang.nama_barang;";
             
            String sql2 = "SELECT tanggal FROM transaksi WHERE kode_struk='" +txtcari.getText()+"';";
            String sql3= "SELECT * FROM transaksi WHERE kode_struk='" +txtcari.getText()+"';";
            java.sql.Connection conn=(Connection)koneksi.configDB();
            java.sql.Statement stm=conn.createStatement();
            java.sql.Statement stm2=conn.createStatement();
            java.sql.Statement stm3=conn.createStatement();
            java.sql.ResultSet res=stm.executeQuery(sql);
            java.sql.ResultSet res2=stm2.executeQuery(sql2);
            java.sql.ResultSet res3=stm3.executeQuery(sql3);
            if(!res3.next()){
                JOptionPane.showMessageDialog(null,"Data Tidak Ditemukan");
            }
            while (res.next()){
                model.addRow(new Object[]{res.getString(1),
                    res.getString(2),res.getString(3),res.getString(4)});
                }
            
            while (res2.next()){
                lbl_tanggal.setText(res2.getString(1));
            }
            
            tbl_detail_transaksi.setModel(model);
            }catch (Exception e) {
        }      
    }//GEN-LAST:event_lbl_cariMouseClicked

    private void lbl_tanggalInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_lbl_tanggalInputMethodTextChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_lbl_tanggalInputMethodTextChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel BUTTON;
    private javax.swing.JPanel Dtrnski;
    private javax.swing.JPanel Rtrnski;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_Dtransaksi;
    private javax.swing.JLabel lbl_Rtransaksi;
    private javax.swing.JLabel lbl_cari;
    private javax.swing.JLabel lbl_tanggal;
    private javax.swing.JTable tbl_detail_transaksi;
    private javax.swing.JTable tbl_transaksi;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
