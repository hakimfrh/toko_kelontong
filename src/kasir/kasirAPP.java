
package kasir;

import java.sql.*;
import javax.swing.JOptionPane;
import toko.kelontong.koneksi;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Hakim frh
 */
public class kasirAPP extends javax.swing.JFrame {

    /**
     * Creates new form kasirAPP
     */
    int harga;
    int jml;
    int subtotal;
    int totalbrg;
    int grandtotal;
    int kembali;
    int tunai;
    int hutang;
    int sisahutang;
    int stok;
    String kode;
    String nama;
    String id_hutang;
    boolean ishutang;
    boolean isnew;
    
    
    
    DefaultTableModel model = new DefaultTableModel();
    
    public kasirAPP() {
        initComponents();
        pn_hutang.setVisible(false);
        pn_tunai.setVisible(true);
        lbl_takcukup.setVisible(false);
        load_CmbBox();
        load_hutang();
        model.addColumn("Kode Barang");
        model.addColumn("Nama Barang");
        model.addColumn("Harga");
        model.addColumn("Jumlah");
        model.addColumn("Sub Total");
    }
    private void load_hutang(){
        try{
            String sql = "select * from hutang;";
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
           
            while(res.next()){
                box_daftarhutang.addItem(res.getString("id_penghutang") +" - " +res.getString("nama"));
                System.out.println(res.getString("id_penghutang") +" - " +res.getString("nama"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    /* BAGIAN ROSAK
    private void refresh_hutang(){
        try{
            String sql = "select * from hutang;";
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
           int item = box_daftarhutang.getItemCount();
            for(int i = 0; i < item; i++){
                box_daftarhutang.removeItemAt(0);
            }   box_daftarhutang.addItem("Hutang Baru");
            while(res.next()){
                box_daftarhutang.addItem(res.getString("id_penghutang") +" - " +res.getString("nama"));
                System.out.println(res.getString("id_penghutang") +" - " +res.getString("nama"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    */
    private void load_CmbBox(){
        try{
            String sql = "SELECT * FROM stok_barang order by nama_barang asc;";
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while(res.next()){
                box_Barang.addItem(res.getString("kode_barang") +" - " +res.getString("nama_barang"));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
    
    static String generate_struk(){
         java.util.Date date = new java.util.Date();
         SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
         SimpleDateFormat m = new SimpleDateFormat("MM");
         SimpleDateFormat t = new SimpleDateFormat("dd");
         SimpleDateFormat y = new SimpleDateFormat("YY");
         int month=Integer.parseInt(m.format(date));
         int transaksi=0;
         try{
            
            String sql = "select count(kode_struk) from transaksi where tanggal='"+f.format(date)+"';";
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()){
            transaksi=Integer.parseInt(res.getString(1));
            }
        }catch (Exception e){
        }
         
         int d=transaksi;
         String c=y.format(date);
         String b=t.format(date);
         String a="";
         String struk ="";
         if(month==1){ a="J";}//januari
         if(month==2){ a="F";}//februari
         if(month==3){ a="M";}//maret
         if(month==4){ a="A";}//april
         if(month==5){ a="M";}//mei
         if(month==6){ a="U";}//juni
         if(month==7){ a="L";}//juli
         if(month==8){ a="G";}//agustus
         if(month==9){ a="S";}//september
         if(month==10){ a="O";}//oktokber
         if(month==11){ a="N";}//november
         if(month==12){ a="D";}//desember
 
        struk= String.format("%s%s%s%03d",a,b,c,d);
   
         return struk.toUpperCase();
   }
    private void kosong(){
        kode="";
        nama="";
        jml=0;
        subtotal=0;
        harga=0;
        stok=0;
        txt_qty.setText("");
        lbl_namabrg.setText("");
        lbl_subtotal.setText("Rp. 0");
        lbl_harga.setText("Rp. 0");
        lbl_stok.setText("");
    }
    
    private void reset(){
        kosong();
        load_hutang();
        //refresh_hutang();
        model.setRowCount(0);
        lbl_grandtotal.setText("0");
        lbl_kembali.setText("");
        txt_tunai.setText("");
        txt_namahutang.setText("");
        txt_bayarhutang.setText("");
        grandtotal=0;
        kembali=0;
        tunai=0;
        totalbrg=0;
    }
    
    private void load_table(){
        
        
        model.addRow(new Object[]{
            kode,
            nama,
            Integer.toString(harga),
            Integer.toString(jml),
            Integer.toString(subtotal)
        });
            jTable1.setModel(model);        
    }
    
    private void tampil_hutang(){
        hutang = grandtotal-tunai;
        hutang += sisahutang;
        lbl_hutang.setText("Rp. " +Integer.toString(hutang));
        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        kode_barang = new javax.swing.JLabel();
        txt_qty = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbl_subtotal = new javax.swing.JLabel();
        lbl_namabrg = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        btn_tambah = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        lbl_grandtotal = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        btn_selesai = new javax.swing.JButton();
        pn_hutang = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        txt_namahutang = new javax.swing.JTextField();
        txt_bayarhutang = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        lbl_hutang = new javax.swing.JLabel();
        box_daftarhutang = new javax.swing.JComboBox<>();
        pn_tunai = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        txt_tunai = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        lbl_kembali = new javax.swing.JLabel();
        lbl_harga = new javax.swing.JLabel();
        lbl_takcukup = new javax.swing.JLabel();
        box_Barang = new javax.swing.JComboBox<>();
        lbl_stok = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Jumlah", "Harga", "Sub Total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 80, 620, 430));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel1.setText("Kasir APP");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, -1, -1));

        kode_barang.setText("Barang");
        getContentPane().add(kode_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 80, 20));

        txt_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_qtyKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_qtyKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_qtyKeyTyped(evt);
            }
        });
        getContentPane().add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 180, -1));

        jLabel3.setText("Quantity");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 80, 20));

        jLabel4.setText("Nama Barang ");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 90, 20));

        jLabel5.setText("Harga");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 60, 20));

        jLabel6.setText("Sub Total");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 60, 20));

        jLabel7.setText(":");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 10, 20));

        jLabel8.setText(":");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 10, 20));

        jLabel9.setText(":");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 10, 20));

        lbl_subtotal.setText("Rp. 0");
        getContentPane().add(lbl_subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 240, 120, 20));
        getContentPane().add(lbl_namabrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 120, 20));

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        jPanel1.setForeground(new java.awt.Color(200, 200, 200));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 176, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 306, -1, -1));

        btn_tambah.setText("Tambah");
        btn_tambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tambahActionPerformed(evt);
            }
        });
        getContentPane().add(btn_tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 270, 80, -1));

        jLabel13.setText("Metode Bayar");
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 90, 20));

        jLabel14.setText(":");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 340, 10, 20));

        lbl_grandtotal.setText("0");
        getContentPane().add(lbl_grandtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 320, 100, 20));

        jLabel16.setText("Grand Total");
        getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 90, 20));

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "Hutang" }));
        jComboBox1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 340, 80, -1));

        jLabel17.setText(": Rp.");
        getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 30, 20));

        btn_selesai.setText("SELESAI");
        btn_selesai.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_selesai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_selesaiActionPerformed(evt);
            }
        });
        getContentPane().add(btn_selesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 160, 40));

        pn_hutang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel22.setText("Nama :");
        pn_hutang.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 40, 20));
        pn_hutang.add(txt_namahutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 40, 140, -1));

        txt_bayarhutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_bayarhutangActionPerformed(evt);
            }
        });
        txt_bayarhutang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarhutangKeyReleased(evt);
            }
        });
        pn_hutang.add(txt_bayarhutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 140, -1));

        jLabel23.setText("Hutang :");
        pn_hutang.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 52, 20));

        jLabel24.setText("Bayar :");
        pn_hutang.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 40, 20));

        lbl_hutang.setText("Rp. 0");
        pn_hutang.add(lbl_hutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, 122, 20));

        box_daftarhutang.setMaximumRowCount(100);
        box_daftarhutang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hutang Baru" }));
        box_daftarhutang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        box_daftarhutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_daftarhutangActionPerformed(evt);
            }
        });
        pn_hutang.add(box_daftarhutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 180, 20));

        getContentPane().add(pn_hutang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 120));

        jLabel18.setText("Tunai");

        txt_tunai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_tunaiActionPerformed(evt);
            }
        });
        txt_tunai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tunaiKeyReleased(evt);
            }
        });

        jLabel19.setText("Kembali");

        lbl_kembali.setText("Rp.0");

        javax.swing.GroupLayout pn_tunaiLayout = new javax.swing.GroupLayout(pn_tunai);
        pn_tunai.setLayout(pn_tunaiLayout);
        pn_tunaiLayout.setHorizontalGroup(
            pn_tunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tunaiLayout.createSequentialGroup()
                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(txt_tunai, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pn_tunaiLayout.createSequentialGroup()
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(lbl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pn_tunaiLayout.setVerticalGroup(
            pn_tunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_tunaiLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pn_tunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tunai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(pn_tunaiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbl_kembali, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        getContentPane().add(pn_tunai, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 360, -1, 90));

        lbl_harga.setText("Rp. 0");
        getContentPane().add(lbl_harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 120, 20));

        lbl_takcukup.setForeground(new java.awt.Color(255, 102, 0));
        lbl_takcukup.setText("Stok tidak mencukupi");
        getContentPane().add(lbl_takcukup, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 120, 30));

        box_Barang.setMaximumRowCount(1000);
        box_Barang.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        box_Barang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                box_BarangActionPerformed(evt);
            }
        });
        getContentPane().add(box_Barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 180, -1));

        lbl_stok.setText("10");
        getContentPane().add(lbl_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 80, 20));

        jLabel2.setText("Sisa Stok");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, 20));

        jLabel10.setText(":");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 10, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tambahActionPerformed
        // TODO add your handling code here:
        load_table();
        
        
        //grandtotal += Integer.parseInt(jTable1.getValueAt(totalbrg, 4).toString());
        grandtotal += subtotal;
        lbl_grandtotal.setText(Integer.toString(grandtotal));
        totalbrg++;
        kosong();
        tampil_hutang();
    }//GEN-LAST:event_btn_tambahActionPerformed

    private void btn_selesaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_selesaiActionPerformed
        // TODO add your handling code here:
        java.util.Date date = new java.util.Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String kodestruk = generate_struk();
        System.out.println("kodestruk   :" +kodestruk);
        System.out.println("totalbarang :" +totalbrg);
        System.out.println("grandtotal  :" +grandtotal);
        System.out.println("tanggal     :" +f.format(date));
        int baris=jTable1.getRowCount();
        boolean status=true;
        try{
            String sql = "INSERT INTO `transaksi`(`kode_struk`, `total_barang`, `total_harga`, `tanggal`) VALUES ('"
                    +kodestruk+"','" +totalbrg +"','" +grandtotal +"','" +f.format(date) +"')";
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
        }catch (Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
            status = false;
        }  
                 
        for(int i=0; i<=baris-1; i++){
             
            try{
               
                 String sql = "UPDATE stok_barang SET "
                    +"`sisa_stok`= sisa_stok-" +jTable1.getValueAt(i,3).toString()
                    +" WHERE `kode_barang`='"+jTable1.getValueAt(i,0).toString()+"';";
                 System.out.println(sql);
                 String sql1 ="INSERT INTO `detail_transaksi`(`kode_struk`, `kode_barang`, `sub_total`, `qty`) VALUES ('"
                         +kodestruk +"','" //kodestruk
                         +jTable1.getValueAt(i,0).toString() +"','" //kodebarang
                         +jTable1.getValueAt(i,4).toString() +"','"//subtotal
                         +jTable1.getValueAt(i,3).toString()+"')";//qty
                java.sql.Connection conn= (Connection) koneksi.configDB();                
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);               
                java.sql.PreparedStatement pst1=conn.prepareStatement(sql1);
                pst.execute();
                pst1.execute();
            }catch (Exception e){
                JOptionPane.showMessageDialog(this, e.getMessage());
                status=false;
            }
             System.out.println("kodestruk  :" +kodestruk);
             System.out.println("kodebarang :" +jTable1.getValueAt(i,0).toString());
             System.out.println("subtotal   :" +jTable1.getValueAt(i,4).toString());
             System.out.println("quantity   :" +jTable1.getValueAt(i,3).toString());
            
            }
        
        if(ishutang){
            if(!isnew){
                try{
                    String sqla = "SELECT * from hutang where nama='"+txt_namahutang.getText()+"';";
                    java.sql.Connection conn= (Connection) koneksi.configDB();
                    java.sql.PreparedStatement stma=conn.prepareStatement(sqla);
                    java.sql.ResultSet res = stma.executeQuery(sqla);
                    String sql ="";
                    if(!res.next()){
                              sql = "INSERT INTO `hutang`(`nama`, `sisa_hutang`, `Tanggal_Hutang`) VALUES ('"
                        +txt_namahutang.getText()+"','"+hutang+"','"+f.format(date)+"')";  
                    }else{
                        sql="UPDATE `hutang` SET "
                                + "`Tanggal_Hutang`='"+f.format(date)+"',"
                                + "`sisa_hutang`= sisa_hutang + "+hutang 
                                + " WHERE id_penghutang = '"+id_hutang+"';";
                    }

                        System.out.println(sql);
                    java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                    pst.execute();
                }catch (Exception e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    status = false;
                }
            }else{
                try{
                String sql = "INSERT INTO `hutang`(`nama`, `sisa_hutang`, `Tanggal_Hutang`) VALUES ('"
                        +txt_namahutang.getText()+"','"+hutang+"','"+f.format(date)+"')";
                java.sql.Connection conn= (Connection) koneksi.configDB();
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.execute();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(this, e.getMessage());
                    status = false;
                }
            }
            
        }
            System.out.println("nama         : " +txt_namahutang.getText());
            System.out.println("sisa hutang  : +" +hutang);
            System.out.println("total hutang : +" +hutang);
            System.out.println("tanggal      : " +f.format(date));
            
            if(status){
            JOptionPane.showMessageDialog(null, "Transaksi berhasil");
        }
        
            
        reset();
    }//GEN-LAST:event_btn_selesaiActionPerformed
    private void txt_bayarhutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_bayarhutangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_bayarhutangActionPerformed
    private void txt_tunaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_tunaiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_tunaiActionPerformed
    private void txt_qtyKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyTyped
        // TODO add your handling code here:
    
        
    }//GEN-LAST:event_txt_qtyKeyTyped
    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        if(jComboBox1.getSelectedItem() == "Hutang"){
            pn_hutang.setVisible(true);
            pn_tunai.setVisible(false);
            ishutang=true;
        }
        if(jComboBox1.getSelectedItem() == "Tunai"){
            pn_hutang.setVisible(false);
            pn_tunai.setVisible(true);
            ishutang=false;
        }
        
    }//GEN-LAST:event_jComboBox1ItemStateChanged
    private void txt_qtyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyPressed

    }//GEN-LAST:event_txt_qtyKeyPressed
    private void txt_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_qtyKeyReleased
        // TODO add your handling code here:
        
        jml = Integer.parseInt(txt_qty.getText());
        if(stok<jml) {
            lbl_takcukup.setVisible(true);
        }else{
            lbl_takcukup.setVisible(false);
        }
        subtotal = harga*jml;
        lbl_subtotal.setText("Rp. "+Integer.toString(subtotal));
    }//GEN-LAST:event_txt_qtyKeyReleased

    private void txt_tunaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tunaiKeyReleased
        // TODO add your handling code here:
        tunai= Integer.valueOf(txt_tunai.getText());
        if((tunai < grandtotal) && (grandtotal > 0)){
            lbl_kembali.setText("Uang Kurang");
        }else{
            kembali = tunai-grandtotal;
            lbl_kembali.setText("Rp. " +Integer.toString(kembali));
        }
    }//GEN-LAST:event_txt_tunaiKeyReleased

    private void txt_bayarhutangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarhutangKeyReleased
        // TODO add your handling code here:
        tunai= Integer.valueOf(txt_bayarhutang.getText());
        tampil_hutang();
    }//GEN-LAST:event_txt_bayarhutangKeyReleased

    private void box_BarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_BarangActionPerformed
        // TODO add your handling code here:
        try{
            kode = box_Barang.getSelectedItem().toString();
            kode=kode.substring(0,kode.indexOf("-"));
            String sql = "SELECT * FROM stok_barang WHERE kode_barang ='" +kode +"';";
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            if(res.next()){
                lbl_stok.setText(res.getString("sisa_stok") +" " +res.getString("satuan_stok"));
                nama = res.getString(2);
                lbl_namabrg.setText(nama);
                harga = Integer.parseInt(res.getString(5));
                lbl_harga.setText("Rp. "+Integer.toString(harga));
                stok=Integer.parseInt(res.getString(3));
               
                
            }else{}
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }//GEN-LAST:event_box_BarangActionPerformed

    private void box_daftarhutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_box_daftarhutangActionPerformed
        // TODO add your handling code here:
      if(box_daftarhutang.getSelectedItem().toString() == "Hutang Baru"){
          txt_namahutang.enable();
          txt_namahutang.setText("");
          isnew=true;
          sisahutang=0;
          tampil_hutang();
      }else{
        try{
            isnew=false;
            id_hutang = box_daftarhutang.getSelectedItem().toString();
            id_hutang=id_hutang.substring(0,id_hutang.indexOf("-"));
            System.out.println(id_hutang);
            String sql = "SELECT * FROM hutang WHERE id_penghutang ='" +id_hutang +"';";
            System.out.println(sql);
            java.sql.Connection conn= (Connection) koneksi.configDB();
            java.sql.PreparedStatement stm=conn.prepareStatement(sql);
            java.sql.ResultSet res = stm.executeQuery(sql);
            if(res.next()){
                
                txt_namahutang.setText(res.getString("nama"));
                txt_namahutang.disable();
                sisahutang = Integer.parseInt(res.getString("sisa_hutang"));
                if(hutang<=0){
                lbl_hutang.setText("Rp. "+Integer.toString(sisahutang));
                }
                tampil_hutang();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
      }
    }//GEN-LAST:event_box_daftarhutangActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(kasirAPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(kasirAPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(kasirAPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(kasirAPP.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new kasirAPP().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> box_Barang;
    private javax.swing.JComboBox<String> box_daftarhutang;
    private javax.swing.JButton btn_selesai;
    private javax.swing.JButton btn_tambah;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel kode_barang;
    private javax.swing.JLabel lbl_grandtotal;
    private javax.swing.JLabel lbl_harga;
    private javax.swing.JLabel lbl_hutang;
    private javax.swing.JLabel lbl_kembali;
    private javax.swing.JLabel lbl_namabrg;
    private javax.swing.JLabel lbl_stok;
    private javax.swing.JLabel lbl_subtotal;
    private javax.swing.JLabel lbl_takcukup;
    private javax.swing.JPanel pn_hutang;
    private javax.swing.JPanel pn_tunai;
    private javax.swing.JTextField txt_bayarhutang;
    private javax.swing.JTextField txt_namahutang;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JTextField txt_tunai;
    // End of variables declaration//GEN-END:variables
}
