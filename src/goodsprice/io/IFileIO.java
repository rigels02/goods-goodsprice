
package goodsprice.io;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Raitis
 * @param <T>
 */
public interface IFileIO<T> {
   
    /**
     * Save List of entities to a Stream (File).
     * @param ListOfObjects
     * @throws IOException 
     */
    public void saveList(List<T> ListOfObjects) throws IOException;
    
    /**
     * Read list from Stream (File)
     * @return
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public  List<T> readListStream() throws IOException, ClassNotFoundException;
    
    /**
     * Get curent file's timestamp
     * @return timestamp java.​sql.Timestamp
     */
    public Timestamp getTimeStamp();
}
