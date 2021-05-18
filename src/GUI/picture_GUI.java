package GUI;

import BUS.*;
import DTO.Picture;
import DTO.Promotion;
import DTO.Staff;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

public class picture_GUI extends JFrame {


    /**
     * Creates new form staff_GUI
     */
    public picture_GUI() {
        initComponents();
        initHeader();
        loadData();
    }

    private void initHeader(){
        this.table_header = new Vector();
        this.table_header.add("ID");
        this.table_header.add("Barcode");
        this.table_header.add("Type");
        this.table_header.add("Price");
        this.table_header.add("Promotion");
        this.table_header.add("Description");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTable1 = new JTable();
        jTable2 = new JTable();
        jScrollPane3 = new JScrollPane();
        jTable3 = new JTable();
        frameLabel = new JTextField(){
            @Override
            public void setBorder(Border border) {
                // No!
            }
        };
        jPanel1 = new JPanel();
        addBtn = new JButton();
        applyBtn = new JButton();
        removeBtn = new JButton();


        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        data = new Vector<>();
        initHeader();

        tableModel = new DefaultTableModel(
                data, table_header
        ) {
            Class[] types = new Class [] {
                    String.class, String.class, Integer.class, Integer.class, String.class, String.class
            };
            boolean[] canEdit = new boolean [] {
                    false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };

        jTable3.setModel(this.tableModel);


        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();
        cellRenderer.setHorizontalAlignment(JLabel.CENTER);
        jTable3.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);

        jTable3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(jTable3);

        frameLabel.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        frameLabel.setHorizontalAlignment(JTextField.CENTER);
        frameLabel.setText("Picture Management");
        frameLabel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        frameLabel.setEditable(false);

        addBtn.setText("Add");
        addBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        applyBtn.setText("Apply");
        applyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        removeBtn.setText("Remove");
        removeBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                jButton3ActionPerformed(e);
            }
        });

        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(addBtn)
                                .addGap(222, 222, 222)
                                .addComponent(applyBtn)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(removeBtn)
                                .addGap(136, 136, 136))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(76, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(addBtn)
                                        .addComponent(applyBtn)
                                        .addComponent(removeBtn))
                                .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(frameLabel, GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane3, GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                        .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(frameLabel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jPanel1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        pack();
    }// </editor-fold>

    private void loadData(){
        this.data = picture_BUS.getAllPicture();
        tableModel.setDataVector(data, this.table_header);
    }



    private boolean isValidRow(int index){

        if(jTable3.getValueAt(index, 2) == "" || jTable3.getValueAt(index, 3) == "" || jTable3.getValueAt(index,5) == ""){
            return false;
        }

        ArrayList<String> listIDPromotion = promotion_BUS.getListIDPromotion();

        Integer price = (Integer) jTable3.getValueAt(index, 3);
        Integer type = (Integer) jTable3.getValueAt(index, 2);
        String promotion = (String) jTable3.getValueAt(index, 4);

        if (type < 0 || price < 0) {
            return false;
        }

        if (!listIDPromotion.contains(promotion)){
            return false;
        }

        return true;
    }


    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        Vector vector = new Vector();
        if(!data.isEmpty()){
            if(isValidRow(data.size() - 1)){
                int ID = Integer.parseInt(String.valueOf(data.get(data.size() - 1).get(0))) + 1;
                vector.add(String.valueOf(ID));
                vector.add(String.valueOf(ID));
                vector.add("");
                vector.add("");
                vector.add("0");
                vector.add("none");

                this.data.add(vector);
                tableModel.setDataVector(data, table_header);
            }
        }
        else {
            int ID = 1;
            vector.add(String.valueOf(ID));
            vector.add(String.valueOf(ID));
            vector.add("");
            vector.add("");
            vector.add("0");
            vector.add("none");

            this.data.add(vector);
            tableModel.setDataVector(data, table_header);
        }

    }


    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        ArrayList<Picture> list = new ArrayList<>();

        for(int row = 0; row < data.size(); row++){
            if(isValidRow(row)){
                String ID = (String) jTable3.getValueAt(row, 0);
                String barcode = (String) jTable3.getValueAt(row, 1);
                Integer type = (Integer) jTable3.getValueAt(row, 2);
                Integer price = (Integer) jTable3.getValueAt(row, 3);
                String promotion  = (String) jTable3.getValueAt(row, 4);
                String description  = (String) jTable3.getValueAt(row, 5);

                list.add(new Picture(ID,barcode,price,type,promotion,description));
            }
        }

        if(picture_BUS.updatePicture(list) > 0){
            this.loadData();
        }
    }

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = jTable3.getSelectedRow();
        if(selectedRow >= 0){
            String barcode = (String) jTable3.getValueAt(selectedRow, 1);

            picture_BUS.deletePicture(barcode);
            this.loadData();
        }
    }


    private Vector table_header;
    private Vector<Vector> data;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBtn;
    private javax.swing.JButton applyBtn;
    private javax.swing.JButton removeBtn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField frameLabel;
    private DefaultTableModel tableModel;
    // End of variables declaration//GEN-END:variables
}
