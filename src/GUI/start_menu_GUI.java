package GUI;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
import BUS.*;
import DTO.*;

/**
 *
 * @author HuyHoang
 */
public class start_menu_GUI extends javax.swing.JFrame {
    // Thread load data
    Thread load_data_thread, bill_clock_thread;
    int clock_hour, clock_min, clock_day, clock_month, clock_year;
    public static int gap_time_update_data = 2000;
    /**
     * Creates new form start_menu_GUI
     */
    public start_menu_GUI() {
        initComponents();
        initBillClock();
        initHeader();
        setItemInTableClickListener();
        load_staf_data();
        createAutoLoadUpdateData();
    }

    private void load_staf_data() {
        staff = staff_BUS.getFirstStaffByName("admin");

        if (staff == null) {
            JOptionPane.showMessageDialog(jPanel_main, "No admin staff found", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        jTextField_bill_staff.setText(staff.getName());
    }

    private void initBillClock() { //clock update every 1 minute
        bill_clock_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Calendar calendar = Calendar.getInstance();

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
                    jTextField_bill_customer_date.setText(formatter.format(calendar.getTime()));

                    try {
                        Thread.sleep(60000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        bill_clock_thread.start();
    }

    private void setItemInTableClickListener() {
        // picture table
        jTable_list_picture.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn(); // select a column
                    Vector row_data = picture_tableModel.getDataVector().elementAt(row);

                    bill_table_controller.insertToBill(row_data);
                }
            }
        });

        // bill table
        jTable_bill.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn(); // select a column

                    bill_table_controller.deleteItemOutOfBill(row);
                }
            }
        });

        // search table
        search_table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    int column = target.getSelectedColumn(); // select a column
                    Vector row_data = search_tableModel.getDataVector().elementAt(row);

                    bill_table_controller.insertToBill(row_data);
                }
            }
        });
    }

    private void createAutoLoadUpdateData() {
        load_data_thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        // Read pictures data
                        picture_data.clear();
                        picture_data.addAll(picture_BUS.getAvailablePictures());
                        picture_tableModel.fireTableDataChanged();

                        // wait "gap_time_update_data" milliseconds to update new data
                        Thread.sleep(2000);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(jPanel_picture,
                            "Loading pictures data error.\nPlease check your connection to database and restart the software",
                            "database connection error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        load_data_thread.start();
    }

    private void initHeader() {
        // Table picture
        this.table_picture_header = new Vector();
        this.table_picture_header.add("ID");
        this.table_picture_header.add("Barcode");
        this.table_picture_header.add("Type");
        this.table_picture_header.add("Price");
        this.table_picture_header.add("Promotion");
        this.table_picture_header.add("Description");

        // Table bill
        this.table_bill_header = new Vector();
        this.table_bill_header.add("ID");
        this.table_bill_header.add("Barcode");
        this.table_bill_header.add("Type");
        this.table_bill_header.add("Price");
        this.table_bill_header.add("Promotion");
        this.table_bill_header.add("Description");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {
        // search
        search_data = new Vector<>();
        search_tableModel = new DefaultTableModel(
                search_data, table_picture_header
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        };

        search_table = new JTable();
        search_table.setModel(search_tableModel);

        //
        jPanel1 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jPanel_main = new javax.swing.JPanel();
        jPanel_list_picture = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jScrollPane_list_picture = new javax.swing.JScrollPane();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable_list_picture = new javax.swing.JTable();
        jPanel_input = new javax.swing.JPanel();
        jPanel_customer = new javax.swing.JPanel();
        label2 = new java.awt.Label();
        jPanel_cutomer_inner = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        textField_cus_name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textField_cus_addr = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textField_cus_phone = new javax.swing.JTextField();
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 30), new java.awt.Dimension(0, 30), new java.awt.Dimension(32767, 30));
        jPanel_picture = new javax.swing.JPanel();
        label6 = new java.awt.Label();
        jPanel_picture_inner = new javax.swing.JPanel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        textField_pic_barcode = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        textField_pic_name = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        textField_pic_price = new javax.swing.JTextField();
        jButton_find_picture = new javax.swing.JButton();
        jButton_add_to_bill = new javax.swing.JButton();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jPanel_bill = new javax.swing.JPanel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jPanel_bill_inner = new javax.swing.JPanel();
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jPanel_bill_info = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField_bill_no = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField_bill_customer_name = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField_bill_customer_addr = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField_bill_customer_phone = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField_bill_customer_date = new javax.swing.JTextField();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 20), new java.awt.Dimension(0, 20), new java.awt.Dimension(32767, 20));
        jScrollPane_bill = new javax.swing.JScrollPane();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable_bill = new javax.swing.JTable();
        jPanel_bill_end_panel = new javax.swing.JPanel();
        jButton_bill_pay = new javax.swing.JButton();
        jButton_bill_print = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 0), new java.awt.Dimension(10, 32767));
        jPanel6 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jTextField_bill_total = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField_bill_staff = new javax.swing.JTextField();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 0), new java.awt.Dimension(20, 32767));
        jPanel_navigator = new javax.swing.JPanel();
        jButton_manage_picture = new javax.swing.JButton();
        jButton_manage_promotion = new javax.swing.JButton();
        jButton_manage_staff = new javax.swing.JButton();
        jButton_manage_customer = new javax.swing.JButton();
        jButton_manage_bill = new javax.swing.JButton();

        // init data tables header
        picture_data = new Vector<>();
        bill_table_controller = new bill_table_UI();

        // init tables headers
        initHeader();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setTitle("art gallery store");

        jPanel1.setLayout(new javax.swing.BoxLayout(jPanel1, javax.swing.BoxLayout.LINE_AXIS));

        label1.setAlignment(java.awt.Label.CENTER);
        label1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label1.setText("Art gallery store");
        jPanel1.add(label1);

        jPanel_main.setLayout(new java.awt.GridLayout(1, 3, 30, 0));

        jPanel_list_picture.setLayout(new javax.swing.BoxLayout(jPanel_list_picture, javax.swing.BoxLayout.Y_AXIS));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("List pictures");
        jPanel_list_picture.add(jLabel7);
        jPanel_list_picture.add(filler8);

        // >>>>>>>>>>>>>>>>> Picture table
        picture_tableModel = new DefaultTableModel(
                picture_data, table_picture_header
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }

        };

        jTable_list_picture.setModel(picture_tableModel);
        jScrollPane4.setViewportView(jTable_list_picture);
        jTable_list_picture.getAccessibleContext().setAccessibleName("table_picture");

        jScrollPane_list_picture.setViewportView(jScrollPane4);

        jPanel_list_picture.add(jScrollPane_list_picture);

        jPanel_main.add(jPanel_list_picture);

        jPanel_input.setLayout(new javax.swing.BoxLayout(jPanel_input, javax.swing.BoxLayout.Y_AXIS));

        jPanel_customer.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_customer.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_customer.setLayout(new javax.swing.BoxLayout(jPanel_customer, javax.swing.BoxLayout.Y_AXIS));

        label2.setAlignment(java.awt.Label.CENTER);
        label2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label2.setText("Customer Infomation");
        jPanel_customer.add(label2);

        jPanel_cutomer_inner.setLayout(new javax.swing.BoxLayout(jPanel_cutomer_inner, javax.swing.BoxLayout.X_AXIS));
        jPanel_cutomer_inner.add(filler6);

        jPanel3.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jPanel3.setToolTipText("input phone number and press enter to find customer");
        jPanel3.setLayout(new java.awt.GridLayout(3, 2, 0, 20));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setLabelFor(textField_cus_name);
        jLabel2.setText("Name");
        jLabel2.setToolTipText("");
        jPanel3.add(jLabel2);

        textField_cus_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textField_cus_name.setName("customer_name"); // NOI18N
        jPanel3.add(textField_cus_name);
        textField_cus_name.getAccessibleContext().setAccessibleName("");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setLabelFor(textField_cus_addr);
        jLabel3.setText("Type");
        jLabel3.setToolTipText("");
        jPanel3.add(jLabel3);

        textField_cus_addr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textField_cus_addr.setName("customer_addr"); // NOI18N
        jPanel3.add(textField_cus_addr);

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setLabelFor(textField_cus_phone);
        jLabel4.setText("Phone");
        jPanel3.add(jLabel4);

        textField_cus_phone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        textField_cus_phone.setName("customer_phone"); // NOI18N
        textField_cus_phone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField_cus_phoneKeyPressed(evt);
            }
        });
        jPanel3.add(textField_cus_phone);

        jPanel_cutomer_inner.add(jPanel3);
        jPanel_cutomer_inner.add(filler7);

        jPanel_customer.add(jPanel_cutomer_inner);
        jPanel_customer.add(filler3);

        jPanel_input.add(jPanel_customer);
        jPanel_input.add(filler4);

        jPanel_picture.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_picture.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_picture.setLayout(new javax.swing.BoxLayout(jPanel_picture, javax.swing.BoxLayout.Y_AXIS));

        label6.setAlignment(java.awt.Label.CENTER);
        label6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        label6.setText("Picture");
        jPanel_picture.add(label6);

        jPanel_picture_inner.setLayout(new javax.swing.BoxLayout(jPanel_picture_inner, javax.swing.BoxLayout.X_AXIS));
        jPanel_picture_inner.add(filler1);

        jPanel5.setLayout(new java.awt.GridLayout(4, 2, 0, 20));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setLabelFor(textField_pic_barcode);
        jLabel1.setText("Barcode");
        jPanel5.add(jLabel1);

        textField_pic_barcode.setName("picture_barcode"); // NOI18N
        textField_pic_barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField_pic_barcodeKeyPressed(evt);
            }
        });
        jPanel5.add(textField_pic_barcode);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setLabelFor(textField_pic_name);
        jLabel5.setText("Description");
        jPanel5.add(jLabel5);

        textField_pic_name.setName("picture_name"); // NOI18N
        textField_pic_name.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField_pic_nameKeyPressed(evt);
            }
        });
        jPanel5.add(textField_pic_name);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setLabelFor(textField_pic_price);
        jLabel6.setText("Price");
        jPanel5.add(jLabel6);

        textField_pic_price.setName("picutre_price"); // NOI18N
        textField_pic_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textField_pic_priceKeyPressed(evt);
            }
        });
        jPanel5.add(textField_pic_price);

        jButton_find_picture.setText("Find");
        jButton_find_picture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_find_pictureActionPerformed(evt);
            }
        });
        jPanel5.add(jButton_find_picture);

        jButton_add_to_bill.setText("Add to bill");
        jButton_add_to_bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_add_to_billActionPerformed(evt);
            }
        });
        jPanel5.add(jButton_add_to_bill);

        jPanel_picture_inner.add(jPanel5);
        jPanel_picture_inner.add(filler2);

        jPanel_picture.add(jPanel_picture_inner);
        jPanel_picture.add(filler5);

        jPanel_input.add(jPanel_picture);

        jPanel_main.add(jPanel_input);

        jPanel_bill.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel_bill.setLayout(new javax.swing.BoxLayout(jPanel_bill, javax.swing.BoxLayout.X_AXIS));
        jPanel_bill.add(filler11);

        jPanel_bill_inner.setLayout(new javax.swing.BoxLayout(jPanel_bill_inner, javax.swing.BoxLayout.Y_AXIS));
        jPanel_bill_inner.add(filler9);

        jPanel_bill_info.setLayout(new java.awt.GridLayout(5, 2, 0, 10));

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Bill No");
        jPanel_bill_info.add(jLabel8);

        jTextField_bill_no.setEditable(false);
        jTextField_bill_no.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_bill_info.add(jTextField_bill_no);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Customer Name");
        jPanel_bill_info.add(jLabel9);

        jTextField_bill_customer_name.setEditable(false);
        jTextField_bill_customer_name.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_bill_info.add(jTextField_bill_customer_name);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Type");
        jPanel_bill_info.add(jLabel10);

        jTextField_bill_customer_addr.setEditable(false);
        jTextField_bill_customer_addr.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_bill_info.add(jTextField_bill_customer_addr);

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Phone");
        jPanel_bill_info.add(jLabel11);

        jTextField_bill_customer_phone.setEditable(false);
        jTextField_bill_customer_phone.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_bill_info.add(jTextField_bill_customer_phone);

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Date");
        jPanel_bill_info.add(jLabel12);

        jTextField_bill_customer_date.setEditable(false);
        jTextField_bill_customer_date.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel_bill_info.add(jTextField_bill_customer_date);

        jPanel_bill_inner.add(jPanel_bill_info);
        jPanel_bill_inner.add(filler13);

        // Bill table
        bill_tableModel = new DefaultTableModel(
                bill_table_controller.getData(), table_bill_header
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        jTable_bill.setModel(bill_tableModel);

        jScrollPane5.setViewportView(jTable_bill);
        jTable_bill.getAccessibleContext().setAccessibleName("table_bill");

        jScrollPane_bill.setViewportView(jScrollPane5);

        jPanel_bill_inner.add(jScrollPane_bill);

        jButton_bill_pay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_bill_pay.setText("Pay");
        jButton_bill_pay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bill_payActionPerformed(evt);
            }
        });

        jButton_bill_print.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_bill_print.setText("Clear");
        jButton_bill_print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_bill_printActionPerformed(evt);
            }
        });

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));
        jPanel4.add(filler10);

        jPanel6.setLayout(new java.awt.GridLayout(2, 2, 0, 10));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Total");
        jPanel6.add(jLabel13);

        jTextField_bill_total.setEditable(false);
        jTextField_bill_total.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel6.add(jTextField_bill_total);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Staff");
        jPanel6.add(jLabel14);

        jTextField_bill_staff.setEditable(false);
        jTextField_bill_staff.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel6.add(jTextField_bill_staff);

        jPanel4.add(jPanel6);

        javax.swing.GroupLayout jPanel_bill_end_panelLayout = new javax.swing.GroupLayout(jPanel_bill_end_panel);
        jPanel_bill_end_panel.setLayout(jPanel_bill_end_panelLayout);
        jPanel_bill_end_panelLayout.setHorizontalGroup(
                jPanel_bill_end_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel_bill_end_panelLayout.createSequentialGroup()
                                .addComponent(jButton_bill_pay)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton_bill_print)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel_bill_end_panelLayout.setVerticalGroup(
                jPanel_bill_end_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_bill_end_panelLayout.createSequentialGroup()
                                .addContainerGap(39, Short.MAX_VALUE)
                                .addGroup(jPanel_bill_end_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel_bill_end_panelLayout.createSequentialGroup()
                                                .addGroup(jPanel_bill_end_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(jButton_bill_print, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                                                        .addComponent(jButton_bill_pay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(1, 1, 1))
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(10, 10, 10))
        );

        jPanel_bill_inner.add(jPanel_bill_end_panel);

        jPanel_bill.add(jPanel_bill_inner);
        jPanel_bill.add(filler12);

        jPanel_main.add(jPanel_bill);

        jPanel_navigator.setLayout(new java.awt.GridLayout(1, 4, 50, 0));

        jButton_manage_picture.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_manage_picture.setText("Manage Pictures");
        jButton_manage_picture.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_pictureActionPerformed(evt);
            }
        });
        jPanel_navigator.add(jButton_manage_picture);

        jButton_manage_promotion.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_manage_promotion.setText("Manage Promotion");
        jButton_manage_promotion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_promotionActionPerformed(evt);
            }
        });
        jPanel_navigator.add(jButton_manage_promotion);

        jButton_manage_staff.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_manage_staff.setText("Manage Staff");
        jButton_manage_staff.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_staffActionPerformed(evt);
            }
        });
        jPanel_navigator.add(jButton_manage_staff);

        jButton_manage_customer.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_manage_customer.setText("Manage Customer");
        jButton_manage_customer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_customerActionPerformed(evt);
            }
        });
        jPanel_navigator.add(jButton_manage_customer);

        jButton_manage_bill.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton_manage_bill.setText("Manage Bill");
        jButton_manage_bill.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_manage_billActionPerformed(evt);
            }
        });
        jPanel_navigator.add(jButton_manage_bill);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jPanel_navigator, javax.swing.GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
                                        .addComponent(jPanel_main, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
                                        .addContainerGap()))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(jPanel_main, javax.swing.GroupLayout.PREFERRED_SIZE, 725, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jPanel_navigator, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(854, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>

    // ------------------------------ Functions ------------------------------
    // >>>>>>>> pay the bill
    private boolean check_customer_info_before_pay() {
        String name, phone, type_string;
        int type = 0;
        name = jTextField_bill_customer_name.getText().toString();
        type_string = jTextField_bill_customer_addr.getText().toString();
        phone = jTextField_bill_customer_phone.getText().toString();

        // add customer
        if (phone != null && !phone.isEmpty()) {
            if (!isDigitOnly(phone)) {
                //Show error message
                JOptionPane.showMessageDialog(jPanel_customer, "Phone should contain number only", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                customer = customer_BUS.getCustomerByPhone(phone);

                if (customer == null) {
                    if (name == null || name.isEmpty()) {
                        JOptionPane.showMessageDialog(jPanel_customer, "Please enter customer name first", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }

                    if (type_string != null && !type_string.isEmpty()) {
                        try {
                            type = Integer.parseInt(type_string);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(jPanel_customer, "Type of customer must be number", "Error", JOptionPane.ERROR_MESSAGE);
                            return false;
                        }
                    } else {
                        type = 0;
                    }

                    if (type > 1 || type < 0) {
                        JOptionPane.showMessageDialog(jPanel_customer, "Please enter customer correct customer type", "Warning", JOptionPane.WARNING_MESSAGE);
                        return false;
                    }

                    customer = new Customer();
                    customer.setType(type);
                    customer.setName(name);
                    customer.setPhone(phone);

                    customer_BUS.insertCustomer(customer);

                    customer = customer_BUS.getCustomerByPhone(phone);
                }

                update_customer_info_ui(customer);
            }
        } else {
            JOptionPane.showMessageDialog(jPanel_customer, "Please enter customer info", "Warning", JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    private void perform_pay_bill() {
        if (bill_table_controller.bill_data.size() < 1) {
            JOptionPane.showMessageDialog(jPanel_bill, "Bill empty", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // check customer exist or add and load customer before pay
        if (check_customer_info_before_pay()) {

            // save to bill
            try {
                int total_bill = bill_table_controller.getTotal_bill();
                int staff_id = Integer.parseInt(staff.getID());
                int customer_id = customer.getID();
                Date date = new Date();

                Bill bill = new Bill(total_bill, staff_id, customer_id, date);

                int insert_res_bill_id = bill_BUS.insertBill(bill);

                if (insert_res_bill_id > 0) {
                    bill_table_controller.pay_the_bill(insert_res_bill_id);
                    jTextField_bill_no.setText(String.valueOf(insert_res_bill_id));
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(jPanel_bill, "An error occurs when pay the bill", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private void link_pictures_to_bill_pay() {

    }

    // >>>>>>>> customer info input
    private boolean isDigitOnly(String str) {
        for (int i=0; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9')
                return false;
        }

        return true;
    }

    private void update_customer_info_ui(Customer cus) {
        String cus_name = cus.getName();
        String cus_phone = cus.getPhone();
        int cus_type = cus.getType();

        update_customer_info_ui(cus_name, cus_type, cus_phone);
    }

    private void update_customer_info_ui(String name, int type, String phone) {
        // load to ui
        textField_cus_name.setText(name);
        jTextField_bill_customer_name.setText(name);

        textField_cus_addr.setText(String.valueOf(type));
        jTextField_bill_customer_addr.setText(String.valueOf(type));

        jTextField_bill_customer_phone.setText(phone);
    }

    private void perform_add_customer() {
        String name, phone, type_string;
        int type = 0;
        name = textField_cus_name.getText().toString();
        type_string = textField_cus_addr.getText().toString();
        phone = textField_cus_phone.getText().toString();

        if (type_string != null && !type_string.isEmpty()) {
            try {
                type = Integer.parseInt(type_string);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(jPanel_customer, "Type of customer must be number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (phone != null && !phone.isEmpty()) {
            if (!isDigitOnly(phone)) {
                //Show error message
                JOptionPane.showMessageDialog(jPanel_customer, "Phone should contain number only", "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                customer = customer_BUS.getCustomerByPhone(phone);

                if (customer != null) {
                    update_customer_info_ui(customer);
                } else {
                    update_customer_info_ui(name, type, phone);
                }
            }
        }
    }

    // >>>>>>>>> search
    private void perform_search() {
        String barcode, description, price_string;
        int price;

        barcode = textField_pic_barcode.getText().toString();
        description = textField_pic_name.getText().toString();
        price_string = textField_pic_price.getText().toString();

        try {
            if (price_string == null || price_string.isEmpty()) {
                price = -1;
            } else {
                price = Integer.parseInt(price_string);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(jPanel_picture, "price should be number only", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Vector<Vector> result_search_data = picture_BUS.getPicturesByValues(barcode, description, price);

        if (result_search_data != null && !result_search_data.isEmpty()) {
            search_data.clear();
            search_data.addAll(result_search_data);

            search_tableModel.setDataVector(search_data, table_picture_header);
        } else {
            JOptionPane.showMessageDialog(jPanel_picture, "No result found");
            return;
        }

        final JComponent[] components = new JComponent[] {
                this.search_table
        };

        JOptionPane.showMessageDialog(jPanel_main, components, "Search result", JOptionPane.INFORMATION_MESSAGE);
    }

    // --------------------------------- Button press -----------------------------
    private void jButton_find_pictureActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        perform_search();
    }

    private void jButton_add_to_billActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jButton_bill_payActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        perform_pay_bill();
    }

    private void jButton_bill_printActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        bill_table_controller.clearData_bill();
    }

    // -------------------------------- Navigator --------------------------------
    private void jButton_manage_pictureActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new picture_GUI().setVisible(true);
            }
        });
    }

    private void jButton_manage_promotionActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new promotion_GUI().setVisible(true);
            }
        });
    }

    private void jButton_manage_staffActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new staff_GUI().setVisible(true);
            }
        });
    }

    private void jButton_manage_customerActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new customer_GUI().setVisible(true);
            }
        });
    }

    private void jButton_manage_billActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new bill_GUI().setVisible(true);
            }
        });
    }

    // -------------------------------- key press ---------------------------------
    private void textField_cus_phoneKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            perform_add_customer();
        }
    }

    // -------------------------------- search ------------------------------------
    private void textField_pic_barcodeKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            perform_search();
    }

    private void textField_pic_nameKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            perform_search();
    }

    private void textField_pic_priceKeyPressed(java.awt.event.KeyEvent evt) {
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
            perform_search();
    }

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
            java.util.logging.Logger.getLogger(start_menu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(start_menu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(start_menu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(start_menu_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new start_menu_GUI().setVisible(true);
            }
        });
    }
    // Staff
    Staff staff = null;

    // Customer data
    Customer customer = null;

    // Search table
    private JTable search_table;
    private DefaultTableModel search_tableModel;
    private Vector<Vector> search_data;

    // Table define
    private DefaultTableModel picture_tableModel;
    private Vector table_picture_header;
    private Vector<Vector> picture_data;

    private DefaultTableModel bill_tableModel;
    private Vector table_bill_header;
    private bill_table_UI bill_table_controller;

    // Variables declaration - do not modify
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JButton jButton_add_to_bill;
    private javax.swing.JButton jButton_bill_pay;
    private javax.swing.JButton jButton_bill_print;
    private javax.swing.JButton jButton_find_picture;
    private javax.swing.JButton jButton_manage_customer;
    private javax.swing.JButton jButton_manage_picture;
    private javax.swing.JButton jButton_manage_promotion;
    private javax.swing.JButton jButton_manage_staff;
    private javax.swing.JButton jButton_manage_bill;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel_bill;
    private javax.swing.JPanel jPanel_bill_end_panel;
    private javax.swing.JPanel jPanel_bill_info;
    private javax.swing.JPanel jPanel_bill_inner;
    private javax.swing.JPanel jPanel_customer;
    private javax.swing.JPanel jPanel_cutomer_inner;
    private javax.swing.JPanel jPanel_input;
    private javax.swing.JPanel jPanel_list_picture;
    private javax.swing.JPanel jPanel_main;
    private javax.swing.JPanel jPanel_navigator;
    private javax.swing.JPanel jPanel_picture;
    private javax.swing.JPanel jPanel_picture_inner;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane_bill;
    private javax.swing.JScrollPane jScrollPane_list_picture;
    private javax.swing.JTable jTable_bill;
    private javax.swing.JTable jTable_list_picture;
    private javax.swing.JTextField jTextField_bill_customer_addr;
    private javax.swing.JTextField jTextField_bill_customer_date;
    private javax.swing.JTextField jTextField_bill_customer_name;
    private javax.swing.JTextField jTextField_bill_customer_phone;
    private javax.swing.JTextField jTextField_bill_no;
    private javax.swing.JTextField jTextField_bill_staff;
    private javax.swing.JTextField jTextField_bill_total;
    private java.awt.Label label1;
    private java.awt.Label label2;
    private java.awt.Label label6;
    private javax.swing.JTextField textField_cus_addr;
    private javax.swing.JTextField textField_cus_name;
    private javax.swing.JTextField textField_cus_phone;
    private javax.swing.JTextField textField_pic_barcode;
    private javax.swing.JTextField textField_pic_name;
    private javax.swing.JTextField textField_pic_price;
    // End of variables declaration

    class bill_table_UI {
        private Vector<Vector> bill_data;
        private int total_bill;

        public bill_table_UI() {
            bill_data = new Vector<>();
            total_bill = 0;

            updateBillTotalPrice();
        }

        public void setData(Vector<Vector> data) {
            bill_data = data;

            updateBillTotalPrice();
        }

        public void insertToBill(Vector row) {
            if (!isRowExist(row)) {
                bill_data.add(row);
                bill_tableModel.fireTableDataChanged();

                updateBillTotalPrice();
            }
        }

        public void deleteItemOutOfBill(int row_num) {
            bill_data.remove(row_num);
            bill_tableModel.fireTableDataChanged();

            updateBillTotalPrice();
        }

        public Vector<Vector> getData() {
            return bill_data;
        }

        public boolean isRowExist(Vector row) {
            String ID = (String)row.get(0);

            for (Vector v : bill_data) {
                if (v.equals(row)) return true;
            }

            return false;
        }

        public void updateBillTotalPrice() {
            int totalprice = 0;

            try {
                for (Vector v : bill_data) {
                    totalprice += (int) v.get(3); //get price at column 3
                }
            }catch (Exception e) {
                System.out.println(e);
            }

            DecimalFormat decimalFormat = new DecimalFormat("#,###");
            String numberAsString = decimalFormat.format(totalprice);

            jTextField_bill_total.setText(numberAsString);
            this.total_bill = totalprice;
        }

        public int getTotal_bill() {
            updateBillTotalPrice();
            return this.total_bill;
        }

        public void pay_the_bill(int bill_id) {
            for (Vector v : bill_data) {
                String barcode = (String) v.get(1);
                picture_BUS.update(barcode, bill_id);
            }
        }

        public void clearData_bill() {
            bill_data.clear();
            bill_tableModel.fireTableDataChanged();
            updateBillTotalPrice();

            // clear info
            jTextField_bill_no.setText("");
            jTextField_bill_customer_name.setText("");
            jTextField_bill_customer_addr.setText("");
            jTextField_bill_customer_phone.setText("");

            customer = null;
        }
    }
}
