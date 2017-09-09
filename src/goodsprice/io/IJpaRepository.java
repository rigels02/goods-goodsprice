
package goodsprice.io;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Raitis
 * @param <T>
 */
public interface IJpaRepository<T> {
    
  
     /**
     * Save List of entities into DB (Jpa)
     * @param ListOfObjects
     * @throws IOException 
     */
    public void saveList(List<T> ListOfObjects) throws IOException;
    
    /**
     * Read entities from DB (JPA)
     * @return 
     */
    public  List<T> readListDB();
}
