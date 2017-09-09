
package goodsprice.control;

import goodsprice.model.Good;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author Raitis
 */
public interface IDataControl {
    
    /**
     * Get good by index id
     * @param id
     * @return good item
     */
    Good getItem(int id)  throws IndexOutOfBoundsException;
    /**
     * new good is ready to be added
     * @param good  the new object
     * @throws java.io.IOException
     */
    void add(Good good) throws IOException;
    /**
     * the selected good by id is ready to be deleted
     * @param id 
     * @throws java.io.IOException 
     */
    void del(int id) throws IOException;
    /**
     * The selected good (by id) has been modified.
     * @param id
     * @param good 
     * @throws java.io.IOException 
     */
    void edit(int id, Good good) throws IOException;
    /**
     * return the list of Good entities
     * @return 
     * @throws java.io.IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    List<Good> getList() throws IOException, ClassNotFoundException;
    
    /**
     * We do not need always to read goods list from persistence (file,DB).
     * For better performance we can use goods list kept in DataControl
     * memory.
     * @return 
     */
    public List<Good> getListFromDataControlMemory();
   
    /**
      * Export List of Good as CSV to file filePath
      * @param filePath
      * @throws FileNotFoundException
      * @throws UnsupportedEncodingException 
      */
    public void exportData(String filePath) throws FileNotFoundException, UnsupportedEncodingException;
    
    /**
     * Import List of Goods data from CSV file
     * @param filePath
     * @throws IOException
     * @throws Exception 
     */
    public void importData(String filePath) throws IOException, Exception;
    
    /**
     * Import new goods list from given source as argument.
     * Used in case of recieving data via network (Tcp)
     * @param goods
     * @throws IOException 
     */
    public void importData(List<Good> goods) throws IOException;
    
    //Below for Simulation
    
    /**
     * TEst persistence thread startup
     */
    public void startPersistDataControlMemoryThread();
    
    public void persistDataControlMemory() throws FileNotFoundException, UnsupportedEncodingException;
   
    /**
     * TEst persistence thread
     */
    //private void persistDataControlMemory();
    /**
     * Test ui access to locked DataControlMemory
     */
    
    public Good simulateAccessToDataControlMemoryGood(String txt) throws Exception;
}
