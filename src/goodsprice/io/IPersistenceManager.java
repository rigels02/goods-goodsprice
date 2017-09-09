
package goodsprice.io;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author Raitis
 * @param <T>
 */
public interface IPersistenceManager<T> {

    /**
     * Read entities from DB (JPA)
     * @return
     */
    List<T> readListDB();

    /**
     * Read list from Stream (File)
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
     List<T> readListStream() throws IOException, ClassNotFoundException;

    /**
     * Save List of entities into both Stream (File) or DB
     * @param ListOfObjects
     * @throws IOException
     */
     void saveList(List<T> ListOfObjects) throws IOException;
    
}
