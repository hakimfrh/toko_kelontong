/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package toko.kelontong;

import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 *
 * @author User
 */
public class pn_hutang extends javax.swing.JPanel {

    /**
     * Creates new form pn_hutang
     */
    
    int sisahutang = 0;
    int bayar = 0;
    String id_penghutang ="0";
    
    public pn_hutang() {
        initComponents();
        ringkasan.setVisible(true);
        menu.setVisible(true);
        daftar_penghutang.setVisible(false);
        riwayat.setVisible(false);
        edit_hutang.setVisible(false);
        load_table_daftar();
        load_table_riwayat();
        tampil();
        hitung_ringkasan();
        penghutang();
    }

    private void hitung_ringkasan(){
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("MM");
        try {
        String sql = "select sum(sisa_hutang) as total FROM hutang WHERE MONTH(tanggal_hutang) =" +f.format(date) ;
        String sql2 = "select sum(sisa_hutang) as total FROM hutang";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.PreparedStatement stm2=conn.prepareStatement(sql2);
            java.sql.ResultSet res = stm.executeQuery(sql);
            java.sql.ResultSet res2 = stm2.executeQuery(sql2);
            while (res.next()) {
                lbl_total_tampil.setText("Rp. " +res.getString("total"));
            }
        } catch (Exception e) {
    }
        load_table_daftar();
    }
    
    public void load_table_daftar() {
        DefaultTableModel  model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Nama");
        model.addColumn("Total Hutang");
        model.addColumn("Tanggal Hutang");
        try {
            String sql = "SELECT * FROM hutang WHERE nama like '%" + txt_cari_dp.getText() + "%'"; 
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[] {
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
            });
            }
            tbl_daftar_penghutang.setModel (model);
        } catch (Exception e) {
    }
    }
    
    public void load_table_riwayat() {
        DefaultTableModel  model_riwayat = new DefaultTableModel();
        model_riwayat.addColumn("Id");
        model_riwayat.addColumn("Nama");
        model_riwayat.addColumn("Jumlah Bayar");
        model_riwayat.addColumn("Tanggal Bayar");
        
        try {
            String sql = "SELECT * FROM riwayat_hutang";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model_riwayat.addRow(new Object[] {
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
            });
            }
            tbl_riwayat.setModel (model_riwayat);
        } catch (Exception e) {
    }
    }
    
    
     public void tampil() {
       
     }
     
     private void penghutang() {
        int penghutangg = 0;
        try {
        String sql = "SELECT * FROM hutang ORDER BY sisa_hutang DESC, LENGTH(sisa_hutang) DESC;";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
           
                if(res.next()){
                lbl_penghutang_tampil.setText("1. " +res.getString("nama")+" - Rp."+res.getString("sisa_hutang"));
                }else lbl_penghutang_tampil.setText("");
                if(res.next()){
                lbl_penghutang_tampil2.setText("2. " +res.getString("nama")+" - Rp."+res.getString("sisa_hutang"));
                }else lbl_penghutang_tampil2.setText("");
                if(res.next()){
                lbl_penghutang_tampil3.setText("3. " +res.getString("nama")+" - Rp."+res.getString("sisa_hutang"));
                }else lbl_penghutang_tampil3.setText("");
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
     }
     
     public void tampil_hutang() {
           //lbl_sisahutang_tampil.setText(tbl_daftar_penghutang.getValueAt(tbl_daftar_penghutang.getSelectedRow(), 1).toString());
     }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ringkasan = new javax.swing.JPanel();
        lbl_total_tampil = new javax.swing.JLabel();
        lbl_penghutang_tampil = new javax.swing.JLabel();
        lbl_penghutang_tampil2 = new javax.swing.JLabel();
        lbl_penghutang_tampil3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lbl_total_bg = new javax.swing.JLabel();
        lbl_penghutang_bg = new javax.swing.JLabel();
        lbl_penghutang_bg1 = new javax.swing.JLabel();
        menu = new javax.swing.JPanel();
        btn_riwayat = new javax.swing.JLabel();
        btn_daftar_penghutang = new javax.swing.JLabel();
        btn_ringkasan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        edit_hutang = new javax.swing.JPanel();
        lbl_sisahutang_tampil3 = new javax.swing.JLabel();
        edit_nama_bayar = new javax.swing.JTextField();
        lbl_sisahutang_tampil2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        btn_simpan_bayar = new javax.swing.JLabel();
        btn_kembali_bayar = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        lbl_hutangtersisa_tampil = new javax.swing.JLabel();
        lbl_sisahutang_tampil = new javax.swing.JLabel();
        lbl_hutangtersisa_bg = new javax.swing.JLabel();
        lbl_sisahutang_bg = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        edit_bayar = new javax.swing.JTextField();
        edit_tanggal = new javax.swing.JTextField();
        daftar_penghutang = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_daftar_penghutang = new javax.swing.JTable();
        txt_cari_dp = new javax.swing.JTextField();
        btn_cari_dp = new javax.swing.JLabel();
        riwayat = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_riwayat = new javax.swing.JTable();
        txt_cari_riwayat = new javax.swing.JTextField();
        btn_cari_riwayat = new javax.swing.JLabel();

        setMinimumSize(new java.awt.Dimension(879, 448));
        setPreferredSize(new java.awt.Dimension(879, 448));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ringkasan.setBackground(new java.awt.Color(255, 255, 255));
        ringkasan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_total_tampil.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_total_tampil.setForeground(new java.awt.Color(255, 255, 255));
        ringkasan.add(lbl_total_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 220, 230, 40));

        lbl_penghutang_tampil.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_penghutang_tampil.setForeground(new java.awt.Color(255, 255, 255));
        ringkasan.add(lbl_penghutang_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 230, 30));

        lbl_penghutang_tampil2.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_penghutang_tampil2.setForeground(new java.awt.Color(255, 255, 255));
        ringkasan.add(lbl_penghutang_tampil2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, 230, 30));

        lbl_penghutang_tampil3.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_penghutang_tampil3.setForeground(new java.awt.Color(255, 255, 255));
        ringkasan.add(lbl_penghutang_tampil3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 260, 230, 30));

        jLabel5.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel5.setText("Penghutang Terbanyak");
        ringkasan.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, -1, -1));

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel6.setText("Hutang Bulan Ini ");
        ringkasan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 180, -1, -1));

        lbl_total_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg blue_1.png"))); // NOI18N
        ringkasan.add(lbl_total_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 210, 250, 60));

        lbl_penghutang_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg pastel2.png"))); // NOI18N
        ringkasan.add(lbl_penghutang_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 250, 80));

        lbl_penghutang_bg1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg pastel2.png"))); // NOI18N
        ringkasan.add(lbl_penghutang_bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 250, 80));

        add(ringkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 740, 450));

        menu.setBackground(new java.awt.Color(255, 255, 255));
        menu.setPreferredSize(new java.awt.Dimension(135, 448));
        menu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat off.png"))); // NOI18N
        btn_riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_riwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_riwayatMouseClicked(evt);
            }
        });
        menu.add(btn_riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, -1, -1));

        btn_daftar_penghutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/daftar penghutang off.png"))); // NOI18N
        btn_daftar_penghutang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_daftar_penghutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_daftar_penghutangMouseClicked(evt);
            }
        });
        menu.add(btn_daftar_penghutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 190, -1, -1));

        btn_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ringkasan on.png"))); // NOI18N
        btn_ringkasan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ringkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_ringkasanMouseClicked(evt);
            }
        });
        menu.add(btn_ringkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg submenu.png"))); // NOI18N
        menu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, -1, -1));

        add(menu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 450));

        edit_hutang.setBackground(new java.awt.Color(255, 255, 255));
        edit_hutang.setPreferredSize(new java.awt.Dimension(351, 370));
        edit_hutang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_sisahutang_tampil3.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_sisahutang_tampil3.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sisahutang_tampil3.setText("RP.");
        edit_hutang.add(lbl_sisahutang_tampil3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 300, 40, 30));

        edit_nama_bayar.setEditable(false);
        edit_nama_bayar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        edit_nama_bayar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 146, 210)));
        edit_hutang.add(edit_nama_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 230, 30));

        lbl_sisahutang_tampil2.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_sisahutang_tampil2.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sisahutang_tampil2.setText("RP.");
        edit_hutang.add(lbl_sisahutang_tampil2, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 300, 40, 30));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel17.setText("Pembayaran");
        edit_hutang.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel18.setText("Hutang Tersisa");
        edit_hutang.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 260, -1, -1));

        btn_simpan_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn simpan.png"))); // NOI18N
        btn_simpan_bayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_simpan_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_simpan_bayarMouseClicked(evt);
            }
        });
        edit_hutang.add(btn_simpan_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 370, -1, -1));

        btn_kembali_bayar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/kembali.png"))); // NOI18N
        btn_kembali_bayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_kembali_bayar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_kembali_bayarMouseClicked(evt);
            }
        });
        edit_hutang.add(btn_kembali_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, -1, -1));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel19.setText("Nama");
        edit_hutang.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, -1, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel20.setText("Total Hutang");
        edit_hutang.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 260, -1, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel21.setText("Tanggal Bayar");
        edit_hutang.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, -1, -1));

        lbl_hutangtersisa_tampil.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_hutangtersisa_tampil.setForeground(new java.awt.Color(255, 255, 255));
        lbl_hutangtersisa_tampil.setText("0");
        edit_hutang.add(lbl_hutangtersisa_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 300, 170, 30));

        lbl_sisahutang_tampil.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        lbl_sisahutang_tampil.setForeground(new java.awt.Color(255, 255, 255));
        edit_hutang.add(lbl_sisahutang_tampil, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 170, 30));

        lbl_hutangtersisa_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg blue_1.png"))); // NOI18N
        edit_hutang.add(lbl_hutangtersisa_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 290, -1, -1));

        lbl_sisahutang_bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg blue_1.png"))); // NOI18N
        edit_hutang.add(lbl_sisahutang_bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, -1));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 22)); // NOI18N
        jLabel23.setText("Bayar");
        edit_hutang.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, -1, -1));

        edit_bayar.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        edit_bayar.setToolTipText("");
        edit_bayar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 146, 210)));
        edit_bayar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_bayarActionPerformed(evt);
            }
        });
        edit_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                edit_bayarKeyReleased(evt);
            }
        });
        edit_hutang.add(edit_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 140, 230, 30));

        edit_tanggal.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        edit_tanggal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 146, 210)));
        edit_hutang.add(edit_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 200, 230, 30));

        add(edit_hutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 740, 450));

        daftar_penghutang.setBackground(new java.awt.Color(255, 255, 255));
        daftar_penghutang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_daftar_penghutang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Total Hutang", "Tanggal Hutang"
            }
        ));
        tbl_daftar_penghutang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_daftar_penghutangMouseClicked(evt);
            }
        });
        tbl_daftar_penghutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_daftar_penghutangKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_daftar_penghutang);

        daftar_penghutang.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 625, 290));

        txt_cari_dp.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_cari_dp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 146, 210)));
        txt_cari_dp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cari_dpActionPerformed(evt);
            }
        });
        txt_cari_dp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cari_dpKeyReleased(evt);
            }
        });
        daftar_penghutang.add(txt_cari_dp, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 209, 40));

        btn_cari_dp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn cari edit.png"))); // NOI18N
        btn_cari_dp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cari_dp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cari_dpMouseClicked(evt);
            }
        });
        daftar_penghutang.add(btn_cari_dp, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, 40));

        add(daftar_penghutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 740, 450));

        riwayat.setBackground(new java.awt.Color(255, 255, 255));
        riwayat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_riwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Id", "Nama", "Jumlah Bayar", "Tanggal Bayar"
            }
        ));
        tbl_riwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_riwayatMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_riwayat);

        riwayat.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 625, 290));

        txt_cari_riwayat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txt_cari_riwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(28, 146, 210)));
        txt_cari_riwayat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cari_riwayatActionPerformed(evt);
            }
        });
        txt_cari_riwayat.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_cari_riwayatKeyReleased(evt);
            }
        });
        riwayat.add(txt_cari_riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 340, 209, 40));

        btn_cari_riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/btn cari edit.png"))); // NOI18N
        btn_cari_riwayat.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cari_riwayat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_cari_riwayatMouseClicked(evt);
            }
        });
        riwayat.add(btn_cari_riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, 40));

        add(riwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 740, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void btn_riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_riwayatMouseClicked
        btn_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ringkasan off.png")));
        btn_daftar_penghutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/daftar penghutang off.png")));
        btn_riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat on.png")));
        riwayat.setVisible(true);
        ringkasan.setVisible(false);
        daftar_penghutang.setVisible(false);
        edit_hutang.setVisible(false);
        load_table_riwayat();
    }//GEN-LAST:event_btn_riwayatMouseClicked

    private void btn_daftar_penghutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_daftar_penghutangMouseClicked
        btn_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ringkasan off.png")));
        btn_daftar_penghutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/daftar penghutang on.png")));
        btn_riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat off.png")));
        daftar_penghutang.setVisible(true);
        ringkasan.setVisible(false);
        riwayat.setVisible(false);
        edit_hutang.setVisible(false);
        load_table_daftar();
    }//GEN-LAST:event_btn_daftar_penghutangMouseClicked

    private void btn_ringkasanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_ringkasanMouseClicked
        btn_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ringkasan on.png")));
        btn_daftar_penghutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/daftar penghutang off.png")));
        btn_riwayat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/riwayat off.png")));
        ringkasan.setVisible(true);
        daftar_penghutang.setVisible(false);
        riwayat.setVisible(false);
        edit_hutang.setVisible(false);
        tampil();
        penghutang();
        load_table_daftar();
        hitung_ringkasan();
    }//GEN-LAST:event_btn_ringkasanMouseClicked

    private void tbl_daftar_penghutangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_daftar_penghutangMouseClicked
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        int baris = tbl_daftar_penghutang.rowAtPoint(evt.getPoint());
        id_penghutang = tbl_daftar_penghutang.getValueAt(baris, 0).toString();
        edit_bayar.setText("");
        bayar=0;
        sisahutang=0;
        if(tbl_daftar_penghutang.getValueAt(baris, 1)==null) {
        } else {
            edit_nama_bayar.setText(tbl_daftar_penghutang.getValueAt(baris,1).toString());
        }
        if(tbl_daftar_penghutang.getValueAt(baris, 2)==null) {
        } else {
            sisahutang = Integer.parseInt(tbl_daftar_penghutang.getValueAt(baris,2).toString());
            lbl_sisahutang_tampil.setText(Integer.toString(sisahutang));
        }
        edit_tanggal.setText(f.format(date));
        load_table_daftar();
        edit_hutang.setVisible(true);
        riwayat.setVisible(false);
        ringkasan.setVisible(false);
        daftar_penghutang.setVisible(false);
        
    }//GEN-LAST:event_tbl_daftar_penghutangMouseClicked

    private void tbl_daftar_penghutangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_daftar_penghutangKeyReleased
    
    }//GEN-LAST:event_tbl_daftar_penghutangKeyReleased

    private void txt_cari_dpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cari_dpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cari_dpActionPerformed

    private void btn_cari_dpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_dpMouseClicked
        DefaultTableModel  model = new DefaultTableModel();
           model.addColumn("Id");
           model.addColumn("Nama");
           model.addColumn("Tanggal Hutang");
           model.addColumn("Total Hutang");
        try {
            String sql = "SELECT * FROM hutang WHERE nama like '%" + txt_cari_dp.getText()+ "%'"
            +" OR tanggal_hutang like '%" + txt_cari_dp.getText()+ "%'";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[] {
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                });
            }
            tbl_daftar_penghutang.setModel (model);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_cari_dpMouseClicked

    private void tbl_riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_riwayatMouseClicked
       
    }//GEN-LAST:event_tbl_riwayatMouseClicked

    private void txt_cari_riwayatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cari_riwayatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cari_riwayatActionPerformed

    private void btn_cari_riwayatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_cari_riwayatMouseClicked
        DefaultTableModel  model_riwayat = new DefaultTableModel();
        model_riwayat.addColumn("Id");
        model_riwayat.addColumn("Nama");
        model_riwayat.addColumn("Jumlah Bayar");
        model_riwayat.addColumn("Tanggal Bayar");

        try {
            String sql = "SELECT * FROM riwayat_hutang WHERE nama like '%" + txt_cari_riwayat.getText()+ "%'"
            +" OR tanggal_hutang like '%" + txt_cari_riwayat.getText()+ "%';";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model_riwayat.addRow(new Object[] {
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                });
            }
            tbl_riwayat.setModel (model_riwayat);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_btn_cari_riwayatMouseClicked

    private void btn_simpan_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_simpan_bayarMouseClicked
        try {
            java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
            String sql = "INSERT INTO riwayat_hutang (`id_penghutang`,`nama`, `jumlah_bayar`, `tanggal_pembayaran`)"
                    + " VALUES('" +id_penghutang+"','"+ edit_nama_bayar.getText() +"','"+ edit_bayar.getText() 
                    +"','"+ f.format(date) +"')"; 
            java.sql.Connection conn=(Connection) koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Pembayaran Berhasil");
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Gagal Disimpan");
            System.out.println(e);
        }
        load_table_daftar();
    }//GEN-LAST:event_btn_simpan_bayarMouseClicked

    private void btn_kembali_bayarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_kembali_bayarMouseClicked
        daftar_penghutang.setVisible(true);
        edit_hutang.setVisible(false);
    }//GEN-LAST:event_btn_kembali_bayarMouseClicked

    private void edit_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_edit_bayarKeyReleased
        // TODO add your handling code here:
        bayar = Integer.parseInt(edit_bayar.getText());
        lbl_hutangtersisa_tampil.setText(Integer.toString(sisahutang-bayar));
    }//GEN-LAST:event_edit_bayarKeyReleased

    private void edit_bayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_bayarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_edit_bayarActionPerformed

    private void txt_cari_riwayatKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cari_riwayatKeyReleased
        // TODO add your handling code here:
        DefaultTableModel  model_riwayat = new DefaultTableModel();
        model_riwayat.addColumn("Id");
        model_riwayat.addColumn("Nama");
        model_riwayat.addColumn("Jumlah Bayar");
        model_riwayat.addColumn("Tanggal Bayar");

        try {
            String sql = "SELECT * FROM riwayat_hutang WHERE nama like '%" + txt_cari_riwayat.getText()+ "%'"
            +" OR tanggal_pembayaran like '%" + txt_cari_riwayat.getText()+ "%';";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model_riwayat.addRow(new Object[] {
                res.getString(1),
                res.getString(2),
                res.getString(3),
                res.getString(4),
                });
            }
            tbl_riwayat.setModel (model_riwayat);
        } catch (Exception e) {
            System.out.println(e);
        }
    }//GEN-LAST:event_txt_cari_riwayatKeyReleased

    private void txt_cari_dpKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_cari_dpKeyReleased
        // TODO add your handling code here:
        DefaultTableModel  model = new DefaultTableModel();
           model.addColumn("Id");
           model.addColumn("Nama");
           model.addColumn("Tanggal Hutang");
           model.addColumn("Total Hutang");
        try {
            String sql = "SELECT * FROM hutang WHERE nama like '%" + txt_cari_dp.getText()+ "%'"
            +" OR tanggal_hutang like '%" + txt_cari_dp.getText()+ "%'";
            java.sql.Connection conn = (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[] {
                    res.getString(1),
                    res.getString(2),
                    res.getString(3),
                    res.getString(4),
                });
            }
            tbl_daftar_penghutang.setModel (model);
        } catch (Exception e) {
        }
    }//GEN-LAST:event_txt_cari_dpKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btn_cari_dp;
    private javax.swing.JLabel btn_cari_riwayat;
    private javax.swing.JLabel btn_daftar_penghutang;
    private javax.swing.JLabel btn_kembali_bayar;
    private javax.swing.JLabel btn_ringkasan;
    private javax.swing.JLabel btn_riwayat;
    private javax.swing.JLabel btn_simpan_bayar;
    private javax.swing.JPanel daftar_penghutang;
    private javax.swing.JTextField edit_bayar;
    private javax.swing.JPanel edit_hutang;
    private javax.swing.JTextField edit_nama_bayar;
    private javax.swing.JTextField edit_tanggal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_hutangtersisa_bg;
    private javax.swing.JLabel lbl_hutangtersisa_tampil;
    private javax.swing.JLabel lbl_penghutang_bg;
    private javax.swing.JLabel lbl_penghutang_bg1;
    private javax.swing.JLabel lbl_penghutang_tampil;
    private javax.swing.JLabel lbl_penghutang_tampil2;
    private javax.swing.JLabel lbl_penghutang_tampil3;
    private javax.swing.JLabel lbl_sisahutang_bg;
    private javax.swing.JLabel lbl_sisahutang_tampil;
    private javax.swing.JLabel lbl_sisahutang_tampil2;
    private javax.swing.JLabel lbl_sisahutang_tampil3;
    private javax.swing.JLabel lbl_total_bg;
    private javax.swing.JLabel lbl_total_tampil;
    private javax.swing.JPanel menu;
    private javax.swing.JPanel ringkasan;
    private javax.swing.JPanel riwayat;
    private javax.swing.JTable tbl_daftar_penghutang;
    private javax.swing.JTable tbl_riwayat;
    private javax.swing.JTextField txt_cari_dp;
    private javax.swing.JTextField txt_cari_riwayat;
    // End of variables declaration//GEN-END:variables
}
