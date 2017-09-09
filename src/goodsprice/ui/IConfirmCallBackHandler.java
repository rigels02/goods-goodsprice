
package goodsprice.ui;

/**
 *
 * @author Raitis
 */
public interface IConfirmCallBackHandler {
    
    /**
     * call back handler to be called by ConfirmDialog when Ok/Cancel
     * buttons have been pressed.
     * @param result 
     */
    void handler(boolean result);
}
