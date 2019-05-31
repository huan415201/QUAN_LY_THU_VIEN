/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import javax.swing.DefaultComboBoxModel;

public class NhanVienComboModel extends DefaultComboBoxModel<String> {
    
    public NhanVienComboModel(String[] items) {
	super(items);
    }
    
    @Override
    public String getSelectedItem() {
        String selectedJob = (String) super.getSelectedItem();

        // do something with this job before returning...

        return selectedJob;
    }
}
