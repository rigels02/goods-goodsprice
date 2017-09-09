
package goodsprice.ui;

import goodsprice.control.IDataControl;

/**
 *
 * @author Raitis
 */
public interface IToolsJDialogCallbacks {
    
    /**
     * Export button in ToolsJDialog.
     * When file is selected by FileChooser this method 
     * should be called to pass filePath
     * @param filePath null if not selected / canceled etc.
     */
    void filePathSelectedExport(String filePath);
    /**
     * Import button in ToolsJDialog.
     * When file is selected by FileChooser this method 
     * should be called to pass filePath
     * @param filePath null if not selected / canceled etc.
     */
    void filePathSelectedImport(String filePath);
    /**
     * To get access to DataControl methods some tools need to have
     * reference to IListViewCallback
     * @return IListViewCallback
     */
    IDataControl getDataControlInterface();
}
