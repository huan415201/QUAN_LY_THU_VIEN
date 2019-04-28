/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author hau
 */
public class ButtonEditor extends DefaultCellEditor {

    protected JButton button;

    private String label;

    private boolean isPushed;
    DefaultTableModel dm;

    JFrame ff;

    TableModel tableModel = createTableModel();

    private static TableModel createTableModel() {
        TableModel md = new UserTableModel.SachTableModel();
        return md;
    }

    public class FrameRunnable implements Runnable {

        public void run() {
//            String[] items = {"One", "Two", "Three", "Four", "Five"};
//            JComboBox<String> combo = new JComboBox<>(items);
//            JTextField field1 = new JTextField("1234.56");
//            JTextField field2 = new JTextField("9876.54");
            JTable tbSach = new JTable();
            tbSach.setModel(
                    tableModel
            );
            JScrollPane jScrollPane1 = new JScrollPane();

            jScrollPane1.setViewportView(tbSach);

            JPanel panel = new JPanel(new GridLayout(0, 1));
            panel.add(jScrollPane1);
//            panel.add(new JLabel("Field 1:"));
//            panel.add(field1);
//            panel.add(new JLabel("Field 2:"));
//            panel.add(field2);
            int result = JOptionPane.showConfirmDialog(null, panel, "Thêm Sách",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                Object[] newRowData = {2, null, null, null, null, "Thêm"};
                dm.addRow(newRowData);
            } else {
                System.out.println("Cancelled");
            }
        }
    }

    public ButtonEditor(JCheckBox checkBox, DefaultTableModel dm1) {
        super(checkBox);
        this.dm = dm1;
        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Thread(new FrameRunnable()).start();

                fireEditingStopped();
            }
        });
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    public Object getCellEditorValue() {
        if (isPushed) {
            // 
            // 
            // JOptionPane.showMessageDialog(button, label + ": Ouch!");
            // System.out.println(label + ": Ouch!");
        }
        isPushed = false;
        return new String(label);
    }

    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

    protected void fireEditingStopped() {
        super.fireEditingStopped();
    }
}
