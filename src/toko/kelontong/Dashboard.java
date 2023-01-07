package toko.kelontong;

import toko.kelontong.TRANSAKSI;
import com.mysql.jdbc.Statement;
import java.sql.Connection;
import java.util.Date;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import java.awt.Cursor;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author eichenar
 */
public class Dashboard extends javax.swing.JPanel {

    /**
     * Creates new form Dashboard
     */
    public String namauser, namatoko;//buat nyimpen nama toko
    Date tgl = new Date();
    SimpleDateFormat hari = new SimpleDateFormat("dd");
    SimpleDateFormat bulan = new SimpleDateFormat("MM");
    String day = hari.format(tgl), month = bulan.format(tgl);//hari mbek bulan saiki
    
    
    
    public Dashboard() {
        initComponents();
        load_bio();
        barang_terlaku();
        barang_terjual();
        modal();
        hutang();
        Pengaturan.setVisible(false);
        ringkasan.setVisible(true);
        
    }
    
    public void modal(){
        try{
            String sql = "select sum(modal), month(tanggal) bulan from pengeluaran"
                    + " group by bulan having bulan = "+month;
            java.sql.Connection con = (Connection)koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            rs.next();
            lbl_modal.setText(rs.getString(1));
            laba(rs.getInt(1));
        }catch(SQLException w){
            System.err.println(w.getMessage());
        }
    }
    public void laba(int modal){
        try{
            String sql = "select sum(total_harga), month(tanggal) bulan"
                    + " from transaksi group by bulan having bulan = "+month;
            java.sql.Connection con = (Connection)koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            rs.next();
            int kurangi = rs.getInt(1)-modal;
            String hasil = Integer.toString(kurangi);
            if(hasil.contains("-")){
                lbl_laba.setText("Rp. 0");
                warn1.setText("Total transaksi bulan ini Rp. "+rs.getString(1));
                warn2.setText("Belum dapat balik modal");
            } else {
                lbl_laba.setText("Rp. "+hasil);
                warn1.setText("");
                warn2.setText("");
            }
        }catch(SQLException e){
            
        }
    }
    public void hutang(){
        
        try{
            String sql = "select sum(Sisa_hutang) from hutang";
            java.sql.Connection con = (Connection) koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rs = stm.executeQuery(sql);
            rs.next();
            lbl_sisahutang.setText("Rp. "+rs.getString(1));
        }
        catch(SQLException e){
            
        }
    }
    public void barang_terjual(){
        try{
            String sqlbulan = "select sum(total_barang), month(tanggal) m from transaksi group by m"
                    + " having m = "+month;
            String sqlhari = "select sum(total_barang), day(tanggal) d, month(tanggal) m from transaksi group by d"
                    + " having d = "+day+" and m = "+month;
            java.sql.Connection con = (Connection)koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.Statement stm2 = con.createStatement();
            java.sql.ResultSet rst = stm.executeQuery(sqlbulan);
            java.sql.ResultSet rst2 = stm2.executeQuery(sqlhari);
            rst.next();
            rst2.next();
            lbl_terjualbulan.setText(rst.getString(1));
            lbl_terjualhari.setText(rst2.getString(1));
        }catch(SQLException e){
            
        }
    }
    
    private void barang_terlaku(){
        try{
            String sql = "select stok_barang.nama_barang, sum(detail_transaksi.qty) jumlah, month(transaksi.tanggal) bulan"
                    + " from detail_transaksi join stok_barang on stok_barang.kode_barang = detail_transaksi.kode_barang"
                    + " join transaksi on transaksi.kode_struk = detail_transaksi.kode_struk"
                    + " group by stok_barang.kode_barang"
                        + " having bulan = "+ month 
                    + " order by jumlah desc";
            java.sql.Connection con = (Connection)koneksi.configDB();
            java.sql.Statement stm = con.createStatement();
            java.sql.ResultSet rst = stm.executeQuery(sql);
            rst.next();
            lbl_terlaku1.setText(rst.getString(1)+" terjual "+rst.getString(2));
            rst.next();
            lbl_terlaku2.setText(rst.getString(1)+" terjual "+rst.getString(2));
            rst.next();
            lbl_terlaku3.setText(rst.getString(1)+" terjual "+rst.getString(2));
        }catch(SQLException e){
            
        }
    }
    private void load_bio(){
        try{
        String sql = "select nama_toko, nama_Pemilik, email, alamat, username, limit_hutang from akun";
            java.sql.Connection con=(Connection) koneksi.configDB();
            java.sql.Statement stm=con.createStatement();
            java.sql.ResultSet rslt=stm.executeQuery (sql);
            rslt.next();
                namauser = rslt.getString(5);
                namatoko = rslt.getString(1);
                lbl_namatoko.setText(rslt.getString(1));
                lbl_namatoko2.setText(rslt.getString(1));
                lbl_owner.setText("Owner : "+rslt.getString(2));
                lbl_namapemilik.setText(rslt.getString(2));
                lbl_email.setText(rslt.getString(3));
                lbl_alamat.setText(rslt.getString(4));
                lbl_username.setText(rslt.getString(5));
                lbl_limithutang.setText(rslt.getString(6));
                
        }catch(SQLException e){
            System.err.println("Kesalahan "+e.getMessage());
        }
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        submenu = new javax.swing.JPanel();
        lbl_owner = new javax.swing.JLabel();
        lbl_namatoko = new javax.swing.JLabel();
        ppic = new javax.swing.JLabel();
        bt_pengaturan = new javax.swing.JLabel();
        bt_ringkasan = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Pengaturan = new javax.swing.JPanel();
        pn_data = new javax.swing.JPanel();
        bt_edit = new javax.swing.JLabel();
        lbl_limithutang = new javax.swing.JLabel();
        lbl_namatoko2 = new javax.swing.JLabel();
        lbl_namapemilik = new javax.swing.JLabel();
        lbl_email = new javax.swing.JLabel();
        lbl_alamat = new javax.swing.JLabel();
        lbl_username = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        pn_ubah = new javax.swing.JPanel();
        bt_batal = new javax.swing.JLabel();
        txt_namatoko = new javax.swing.JTextField();
        txt_namapemilik = new javax.swing.JTextField();
        txt_email = new javax.swing.JTextField();
        txt_alamat = new javax.swing.JTextField();
        txt_username = new javax.swing.JTextField();
        txt_limithutang = new javax.swing.JTextField();
        bt_simpanperubahan = new javax.swing.JLabel();
        lbl_emailconfirm = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ringkasan = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbl_terlaku3 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        lbl_terlaku2 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        lbl_terlaku1 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        lbl_terjualbulan = new javax.swing.JLabel();
        lbl_terjualhari = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lbl_modal = new javax.swing.JLabel();
        lbl_sisahutang = new javax.swing.JLabel();
        lbl_laba = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        warn2 = new javax.swing.JLabel();
        lbl_refresh = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        warn1 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        submenu.setBackground(new java.awt.Color(255, 255, 255));
        submenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lbl_owner.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        lbl_owner.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_owner.setText("Owner: dl");
        submenu.add(lbl_owner, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 190, -1));

        lbl_namatoko.setFont(new java.awt.Font("Century Gothic", 0, 24)); // NOI18N
        lbl_namatoko.setForeground(new java.awt.Color(66, 133, 244));
        lbl_namatoko.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_namatoko.setText("me");
        submenu.add(lbl_namatoko, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 200, 50));

        ppic.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ppic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/profile pic.png"))); // NOI18N
        submenu.add(ppic, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 190, -1));

        bt_pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn off pengaturan akun.png"))); // NOI18N
        bt_pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_pengaturan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bt_pengaturanMouseReleased(evt);
            }
        });
        submenu.add(bt_pengaturan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, -1, -1));

        bt_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn on ringkasan.png"))); // NOI18N
        bt_ringkasan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_ringkasan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                bt_ringkasanMouseReleased(evt);
            }
        });
        submenu.add(bt_ringkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, -1, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/bg submenu.png"))); // NOI18N
        submenu.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 11, -1, -1));

        add(submenu, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, -1));

        Pengaturan.setBackground(new java.awt.Color(255, 255, 255));
        Pengaturan.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Pengaturan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pn_data.setBackground(new java.awt.Color(255, 255, 255));
        pn_data.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_edit.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        bt_edit.setForeground(new java.awt.Color(66, 133, 244));
        bt_edit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_edit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn edit.png"))); // NOI18N
        bt_edit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_edit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_editMouseClicked(evt);
            }
        });
        pn_data.add(bt_edit, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        lbl_limithutang.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_limithutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_limithutang.setText("000");
        pn_data.add(lbl_limithutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 280, -1));

        lbl_namatoko2.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_namatoko2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_namatoko2.setText("xyz");
        pn_data.add(lbl_namatoko2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 360, -1));

        lbl_namapemilik.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_namapemilik.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_namapemilik.setText("xyz");
        pn_data.add(lbl_namapemilik, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 360, -1));

        lbl_email.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_email.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_email.setText("xyz");
        pn_data.add(lbl_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 320, -1));

        lbl_alamat.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_alamat.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_alamat.setText("xyz");
        pn_data.add(lbl_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 120, 320, -1));

        lbl_username.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_username.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_username.setText("xyz");
        pn_data.add(lbl_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 280, -1));

        jLabel18.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText(": Rp.");
        pn_data.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 40, -1));

        jLabel25.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel25.setText(":");
        pn_data.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 10, -1));

        jLabel26.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel26.setText(":");
        pn_data.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 10, -1));

        jLabel27.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText(":");
        pn_data.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 10, -1));

        jLabel28.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText(":");
        pn_data.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 10, -1));

        jLabel29.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText(":");
        pn_data.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 10, -1));

        Pengaturan.add(pn_data, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 130, 450, 300));

        pn_ubah.setBackground(new java.awt.Color(255, 255, 255));
        pn_ubah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        bt_batal.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        bt_batal.setForeground(new java.awt.Color(66, 133, 244));
        bt_batal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn batal.png"))); // NOI18N
        bt_batal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_batal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_batalMouseClicked(evt);
            }
        });
        pn_ubah.add(bt_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, -1, -1));

        txt_namatoko.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        pn_ubah.add(txt_namatoko, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 30));

        txt_namapemilik.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        pn_ubah.add(txt_namapemilik, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 220, 30));

        txt_email.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txt_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_emailFocusLost(evt);
            }
        });
        txt_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_emailKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_emailKeyReleased(evt);
            }
        });
        pn_ubah.add(txt_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 220, 30));

        txt_alamat.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        pn_ubah.add(txt_alamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 220, 30));

        txt_username.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        pn_ubah.add(txt_username, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 160, 220, 30));

        txt_limithutang.setFont(new java.awt.Font("Century Gothic", 0, 14)); // NOI18N
        txt_limithutang.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        pn_ubah.add(txt_limithutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 220, 30));

        bt_simpanperubahan.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        bt_simpanperubahan.setForeground(new java.awt.Color(66, 133, 244));
        bt_simpanperubahan.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bt_simpanperubahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn simpan.png"))); // NOI18N
        bt_simpanperubahan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bt_simpanperubahan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bt_simpanperubahanMouseClicked(evt);
            }
        });
        pn_ubah.add(bt_simpanperubahan, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 250, -1, -1));

        lbl_emailconfirm.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        lbl_emailconfirm.setForeground(new java.awt.Color(255, 0, 0));
        lbl_emailconfirm.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        pn_ubah.add(lbl_emailconfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, 190, 30));

        Pengaturan.add(pn_ubah, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 130, 440, 300));

        jLabel19.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Pengaturan Akun");
        Pengaturan.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        jLabel20.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Nama Pemilik");
        Pengaturan.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 170, -1, -1));

        jLabel21.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("E-mail");
        Pengaturan.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, -1, 30));

        jLabel22.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Alamat");
        Pengaturan.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 250, -1, 30));

        jLabel23.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel23.setText("Username");
        Pengaturan.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 290, -1, 30));

        jLabel24.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel24.setText("Nama Toko");
        Pengaturan.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 130, -1, -1));

        jLabel34.setFont(new java.awt.Font("Century Gothic", 0, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("Limit Hutang (per-orang)");
        Pengaturan.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 326, -1, 40));

        add(Pengaturan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 680, 450));

        ringkasan.setBackground(new java.awt.Color(255, 255, 255));
        ringkasan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 51, 51));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Barang Terlaku");
        ringkasan.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 120, -1, -1));

        lbl_terlaku3.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lbl_terlaku3.setForeground(new java.awt.Color(51, 51, 51));
        lbl_terlaku3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_terlaku3.setText("xx");
        ringkasan.add(lbl_terlaku3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 192, 190, 30));

        jLabel41.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel41.setForeground(new java.awt.Color(51, 51, 51));
        jLabel41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel41.setText("3.");
        ringkasan.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 192, -1, 30));

        lbl_terlaku2.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lbl_terlaku2.setForeground(new java.awt.Color(51, 51, 51));
        lbl_terlaku2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_terlaku2.setText("xx");
        ringkasan.add(lbl_terlaku2, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 172, 190, 30));

        jLabel39.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(51, 51, 51));
        jLabel39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel39.setText("2.");
        ringkasan.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 172, -1, 30));

        lbl_terlaku1.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lbl_terlaku1.setForeground(new java.awt.Color(51, 51, 51));
        lbl_terlaku1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_terlaku1.setText("xx");
        ringkasan.add(lbl_terlaku1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 152, 190, 30));

        jLabel37.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel37.setForeground(new java.awt.Color(51, 51, 51));
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel37.setText("1. ");
        ringkasan.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 152, -1, 30));

        lbl_terjualbulan.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lbl_terjualbulan.setForeground(new java.awt.Color(51, 51, 51));
        lbl_terjualbulan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_terjualbulan.setText("0");
        ringkasan.add(lbl_terjualbulan, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 180, 120, -1));

        lbl_terjualhari.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        lbl_terjualhari.setForeground(new java.awt.Color(51, 51, 51));
        lbl_terjualhari.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_terjualhari.setText("0");
        ringkasan.add(lbl_terjualhari, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 152, 120, 30));

        jLabel33.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(51, 51, 51));
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel33.setText(":");
        ringkasan.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 180, 30, -1));

        jLabel32.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(51, 51, 51));
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel32.setText(":");
        ringkasan.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 152, 30, 30));

        jLabel14.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(51, 51, 51));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel14.setText("Bulan ini");
        ringkasan.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 180, -1, -1));

        jLabel31.setFont(new java.awt.Font("Century Gothic", 0, 17)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 51, 51));
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel31.setText("Hari ini");
        ringkasan.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 152, -1, 30));

        jLabel7.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(66, 133, 244));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/bg pastel2.png"))); // NOI18N
        ringkasan.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, -1, -1));

        lbl_modal.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_modal.setForeground(new java.awt.Color(255, 255, 255));
        lbl_modal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_modal.setText("0");
        ringkasan.add(lbl_modal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 270, 190, -1));

        lbl_sisahutang.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_sisahutang.setForeground(new java.awt.Color(255, 255, 255));
        lbl_sisahutang.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_sisahutang.setText("Rp. 0");
        ringkasan.add(lbl_sisahutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 320, 230, 30));

        lbl_laba.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        lbl_laba.setForeground(new java.awt.Color(255, 255, 255));
        lbl_laba.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lbl_laba.setText("Rp. 0");
        ringkasan.add(lbl_laba, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 230, -1));

        jLabel9.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Rp.");
        ringkasan.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, 30));

        jLabel10.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(66, 133, 244));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/bg blue.png"))); // NOI18N
        ringkasan.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 310, -1, -1));

        jLabel11.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(66, 133, 244));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/bg pastel.png"))); // NOI18N
        ringkasan.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 150, -1, -1));

        jLabel12.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(51, 51, 51));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Hutang belum terbayar");
        ringkasan.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 280, -1, -1));

        jLabel13.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(66, 133, 244));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/bg blue.png"))); // NOI18N
        ringkasan.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 350, -1, -1));

        jLabel15.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(51, 51, 51));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel15.setText("Ringkasan Bulan ini");
        ringkasan.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, -1, -1));

        warn2.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N
        warn2.setForeground(new java.awt.Color(51, 51, 51));
        warn2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        warn2.setText("Belum mencukupi untuk balik modal");
        ringkasan.add(warn2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, -1, 30));

        lbl_refresh.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        lbl_refresh.setForeground(new java.awt.Color(0, 153, 51));
        lbl_refresh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbl_refresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/refresh.png"))); // NOI18N
        lbl_refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_refreshMouseClicked(evt);
            }
        });
        ringkasan.add(lbl_refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 60, 70, -1));

        jLabel35.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 51));
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Barang Terjual");
        ringkasan.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, -1, -1));

        jLabel16.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(51, 51, 51));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel16.setText("Laba Bersih");
        ringkasan.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, -1));

        warn1.setFont(new java.awt.Font("Century Gothic", 2, 12)); // NOI18N
        warn1.setForeground(new java.awt.Color(51, 51, 51));
        warn1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        warn1.setText("Total transaksi bulan ini Rp.");
        ringkasan.add(warn1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, -1, 20));

        jLabel17.setFont(new java.awt.Font("Century Gothic", 0, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(66, 133, 244));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/bg blue.png"))); // NOI18N
        ringkasan.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 260, -1, -1));

        jLabel30.setFont(new java.awt.Font("Century Gothic", 0, 18)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 51));
        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel30.setText("Modal");
        ringkasan.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 230, -1, -1));

        jLabel36.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(255, 255, 255));
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Rp.");
        ringkasan.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, 30));

        add(ringkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 0, 680, 450));
    }// </editor-fold>//GEN-END:initComponents

    private void bt_ringkasanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_ringkasanMouseReleased
        // TODO add your handling code here:
        ringkasan.setVisible(true);
        Pengaturan.setVisible(false);
        bt_pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn off pengaturan akun.png")));
        bt_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn on ringkasan.png")));
    }//GEN-LAST:event_bt_ringkasanMouseReleased

    private void bt_pengaturanMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_pengaturanMouseReleased
        // TODO add your handling code here:
        Pengaturan.setVisible(true);
        ringkasan.setVisible(false);
        pn_ubah.setVisible(false);
        pn_data.setVisible(true);
        bt_ringkasan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn off ringkasan.png")));
        bt_pengaturan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn on pengaturan akun.png")));    
    }//GEN-LAST:event_bt_pengaturanMouseReleased

    private void bt_batalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_batalMouseClicked
        // TODO add your handling code here:
        
        lbl_emailconfirm.setText("");
        bt_simpanperubahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn simpan.png")));
        pn_ubah.setVisible(false);
        pn_data.setVisible(true);
    }//GEN-LAST:event_bt_batalMouseClicked

    private void bt_editMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_editMouseClicked
        // TODO add your handling code here:
        
        pn_data.setVisible(false);
        pn_ubah.setVisible(true);
        txt_username.disable();
        txt_namatoko.setText(lbl_namatoko.getText());
        txt_namapemilik.setText(lbl_namapemilik.getText());
        txt_email.setText(lbl_email.getText());
        txt_alamat.setText(lbl_alamat.getText());
        txt_username.setText(lbl_username.getText());
        txt_limithutang.setText(lbl_limithutang.getText());
        lbl_emailconfirm.setText("");
        bt_simpanperubahan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        
    }//GEN-LAST:event_bt_editMouseClicked

    private void bt_simpanperubahanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bt_simpanperubahanMouseClicked
        // TODO add your handling code here:
        if(!txt_email.getText().contains("@")){
            lbl_emailconfirm.setText("Masukkan email dengan benar");
        }else{
            lbl_emailconfirm.setText("");
            try{
                String sql = "update akun set nama_toko ='"+txt_namatoko.getText()+ "', nama_pemilik ='" +txt_namapemilik.getText()+
                        "', email ='"+txt_email.getText()+"', alamat = '" +txt_alamat.getText()+ 
                        "', limit_hutang ="+Integer.parseInt(txt_limithutang.getText())+" where username = '"+namauser+"'";
            
                Statement stmt = (Statement) koneksi.configDB().createStatement();
                stmt.executeUpdate(sql);
                stmt.close();
                JOptionPane.showMessageDialog(null,"Data berhasil disimpan");
            }catch(Exception e){
                JOptionPane.showMessageDialog(null,"Gagal \n"+e.getMessage());
            }
            load_bio();
            TRANSAKSI tr = new TRANSAKSI();
            tr.pemilik();
            pn_ubah.setVisible(false);
            pn_data.setVisible(true);
        }
        
    }//GEN-LAST:event_bt_simpanperubahanMouseClicked

    private void txt_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_emailFocusLost
        // TODO add your handling code here:
        if(!txt_email.getText().contains("@")){
            lbl_emailconfirm.setText("Masukkan email dengan benar");
        }else{
            lbl_emailconfirm.setText("");
        }
    }//GEN-LAST:event_txt_emailFocusLost

    private void lbl_refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_refreshMouseClicked
        // TODO add your handling code here:
        barang_terjual();
        barang_terlaku();
        modal();
    }//GEN-LAST:event_lbl_refreshMouseClicked

    private void txt_emailKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailKeyPressed
        // TODO add your handling code here:
        if(txt_email.getText().contains("@")){
            bt_simpanperubahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn simpan.png")));
            lbl_emailconfirm.setText("");
        }
    }//GEN-LAST:event_txt_emailKeyPressed

    private void txt_emailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_emailKeyReleased
        // TODO add your handling code here:
        if(!txt_email.getText().contains("@")){
            bt_simpanperubahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn simpan disabled.png")));
            bt_simpanperubahan.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            lbl_emailconfirm.setText("Masukkan email dengan benar");
        }else{
            bt_simpanperubahan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Dashboard/btn simpan.png")));
            bt_simpanperubahan.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lbl_emailconfirm.setText("");
        }
    }//GEN-LAST:event_txt_emailKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Pengaturan;
    private javax.swing.JLabel bt_batal;
    private javax.swing.JLabel bt_edit;
    private javax.swing.JLabel bt_pengaturan;
    private javax.swing.JLabel bt_ringkasan;
    private javax.swing.JLabel bt_simpanperubahan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lbl_alamat;
    private javax.swing.JLabel lbl_email;
    private javax.swing.JLabel lbl_emailconfirm;
    private javax.swing.JLabel lbl_laba;
    private javax.swing.JLabel lbl_limithutang;
    private javax.swing.JLabel lbl_modal;
    private javax.swing.JLabel lbl_namapemilik;
    private javax.swing.JLabel lbl_namatoko;
    private javax.swing.JLabel lbl_namatoko2;
    private javax.swing.JLabel lbl_owner;
    private javax.swing.JLabel lbl_refresh;
    private javax.swing.JLabel lbl_sisahutang;
    private javax.swing.JLabel lbl_terjualbulan;
    private javax.swing.JLabel lbl_terjualhari;
    private javax.swing.JLabel lbl_terlaku1;
    private javax.swing.JLabel lbl_terlaku2;
    private javax.swing.JLabel lbl_terlaku3;
    private javax.swing.JLabel lbl_username;
    private javax.swing.JPanel pn_data;
    private javax.swing.JPanel pn_ubah;
    private javax.swing.JLabel ppic;
    private javax.swing.JPanel ringkasan;
    private javax.swing.JPanel submenu;
    private javax.swing.JTextField txt_alamat;
    private javax.swing.JTextField txt_email;
    private javax.swing.JTextField txt_limithutang;
    private javax.swing.JTextField txt_namapemilik;
    private javax.swing.JTextField txt_namatoko;
    private javax.swing.JTextField txt_username;
    private javax.swing.JLabel warn1;
    private javax.swing.JLabel warn2;
    // End of variables declaration//GEN-END:variables
}
