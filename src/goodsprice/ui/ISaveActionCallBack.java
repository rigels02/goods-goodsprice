
package goodsprice.ui;

import goodsprice.model.Good;

/**
 *
 * @author Raitis
 */
interface ISaveActionCallBack {
    
    /**
     * Save callBack method for AddEditDlg
     * @param good 
     */
    void saveAction(Good good);
}
