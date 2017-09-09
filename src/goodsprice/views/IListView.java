
package goodsprice.views;

import goodsprice.model.Good;
import java.util.List;

/**
 *
 * @author Raitis
 */
public interface IListView {
    
    /**
     * Update the list view
     * @param goods 
     */
    void updateListView( List<Good> goods);
}
