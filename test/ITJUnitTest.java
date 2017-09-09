
import goodsprice.control.DataControl;
import goodsprice.io.FileIOImpl;
import goodsprice.io.PersistenceManager;
import goodsprice.model.Good;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raitis
 */
public class ITJUnitTest {
    
    public ITJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
        String fileName= "TestFile.bin";
        File file = new File(fileName);
        if(file.exists()){
         file.delete();
        }
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testPersistenceManager() throws IOException, ClassNotFoundException, CloneNotSupportedException {
        System.out.println("ITJUnitTest.testPersistenceManager()");
        
        FileIOImpl<Good> fileIO = new FileIOImpl<>("Goods.bin");
       
        PersistenceManager<Good> pm = new PersistenceManager<>(fileIO);
        
        ArrayList<Good> goods= new ArrayList<>();
        goods.add(new Good(new Date(), "name_1", "Shop_1", 2.35d, 0));
        goods.add(new Good(new Date(), "name_2", "Shop_2", 5.35d, 0));
        goods.add(new Good(new Date(), "name_3", "Shop_3", 5.00d, 0));
        System.out.println("goods before: "+goods);
       
        //--------
        pm.saveList(goods);
        
        List<Good> goods1 = pm.readListStream();
         System.out.println("goods after: "+goods1);
         String expect= goods.toString();
         String result= goods1.toString();
         assertEquals("The same List", expect, result);
       
        
        System.out.println("ITJUnitTest.testPersistenceManager() - OK");
    }
    
    @Test
    /**
     * TEst controller, no persistence involved
     */
    public void testDataContrl() throws IOException, ClassNotFoundException{
        System.out.println("ITJUnitTest.testDataContrl()");
        
        DataControl dc = new DataControl(null,null);
        //get empty list
        List<Good> lst = dc.getList();
        assertTrue("is empty?", lst.isEmpty());
        
        //try to delete empty list
        try {
            dc.del(0);
        } catch (IndexOutOfBoundsException ex) {
            assertTrue("Is IndexOutOfBoundsException", ex instanceof IndexOutOfBoundsException);
        }
        //try to get item from empty list
        try {
            dc.getItem(0);
        } catch (IndexOutOfBoundsException ex) {
            assertTrue("Is IndexOutOfBoundsException", ex instanceof IndexOutOfBoundsException);
        }
        
        //add
        Good expected= new Good(new Date(),"Item_1","Shop_1",5,4.33);
        dc.add(expected);
        Good result = dc.getItem(0);
        assertSame("is the same?", expected, result);
        
        //edit
        expected.setPrice(10.99);
        dc.edit(0, expected);
        result = dc.getItem(0);
        assertSame("is the same after edit?", expected, result);
        //check size
        lst = dc.getList();
        assertTrue("is size 1?", lst.size()==1);
        
        //delete
        dc.del(0);
        lst = dc.getList();
        assertTrue("is empty?", lst.isEmpty());
        System.out.println("ITJUnitTest.testDataContrl() - OK ");
    }
    
    @Test
    /**
     * TEst all 3 things together: 
     * Controller, Persistence manager with fileIO.
     * No file exists yet
     */
    public void test3TogetherNoFileCreated() throws IOException, ClassNotFoundException {
        System.out.println("ITJUnitTest.test3TogetherNoFileCreated()");
        //Prepare - delete file if exists
        String fileName= "TestFile.bin";
        File file = new File(fileName);
        if(file.exists()){
         file.delete();
        }
        FileIOImpl<Good> fileIO = new FileIOImpl<>(fileName);
       
        //check timestamp
        Timestamp stamp = fileIO.getTimeStamp();
        System.out.println("timestamp= "+stamp);
        PersistenceManager<Good> pm = new PersistenceManager<>(fileIO);
        
        DataControl dc = new DataControl(pm,null);
        
        //--------------------------------
        //get empty list
        List<Good> lst = dc.getList();
        assertTrue("is empty?", lst.isEmpty());
        
        //try to delete empty list
        try {
            dc.del(0);
        } catch (IndexOutOfBoundsException ex) {
            assertTrue("Is IndexOutOfBoundsException", ex instanceof IndexOutOfBoundsException);
        }
        //try to get item from empty list
        try {
            dc.getItem(0);
        } catch (IndexOutOfBoundsException ex) {
            assertTrue("Is IndexOutOfBoundsException", ex instanceof IndexOutOfBoundsException);
        }
        //-------------- other operations
         //add
        Good expected= new Good(new Date(),"Item_1","Shop_1",5,4.33);
        dc.add(expected);
        Good result = dc.getItem(0);
        assertSame("is the same?", expected, result);
        
        //edit
        expected.setPrice(10.99);
        dc.edit(0, expected);
        result = dc.getItem(0);
        assertSame("is the same after edit?", expected, result);
        //check size
        lst = dc.getList();
        assertTrue("is size 1?", lst.size()==1);
        
        //delete
        dc.del(0);
        lst = dc.getList();
        assertTrue("is empty?", lst.isEmpty());
        
         System.out.println("ITJUnitTest.test3TogetherNoFileCreated() - OK");
    }
    
   
    private double genPrice() {

        double p;
        p = Math.random() * 20;
        p = (p == 0.0) ? 0.1 : p;
        //Round on 3 decimals
        p = (double) Math.round(p * 100d) / 100d;
        //System.out.println("p= " +p);
        return p;
    }

    private double genDiscount() {

        double p;
        p = Math.random() * 5;
        //Round on 3 decimals
        p = (double) Math.round(p * 100d) / 100d;
        //System.out.println("p= " + p);
        return p;
    } 
            
    private List<Good> prepareData(){
        List<Good> goods= new ArrayList<>();
        
        for (int y = 117; y <= 119; y++) {
            for (int m = 0; m <= 11; m++) {
                for (int d = 1; d <= 28; d++) {
                    for (int n = 1; n <= 30; n++) {
                       
                      goods.add(new Good(new Date(y,m,d),"Good_"+n,"Shop_"+n,genPrice(),genDiscount()));
                    }
                }}}
                      
                    
        return goods;
    }
     /**
     * TEst all 3 things together: 
     * Controller, Persistence manager with fileIO.
     *BIG file
     */
    @Test
    public void test3TogetherOnBigFileSaveAndRead() throws IOException, ClassNotFoundException{
        System.out.println("ITJUnitTest.test3TogetherOnBigFileSaveAndRead()");
        
        List<Good> goods = prepareData();
        System.out.println("Goods count = "+goods.size());
        String fileName= "TestFile.bin";
        
        FileIOImpl<Good> fileIO = new FileIOImpl<>(fileName);
       
        
        PersistenceManager<Good> pm = new PersistenceManager<>(fileIO);
        
        DataControl dc = new DataControl(pm,null);
        
        //Start to save Data
        //check timestamp
        dc.setListForDataControlMemory(goods);
        long startTime = System.currentTimeMillis();
        //System.out.println("timestamp= "+startTime);
        dc.saveAllData();
        System.out.println("Save Time ellapsed: "+((System.currentTimeMillis()-startTime)/1000d)+" sec");
        
        System.out.println("File size: "+fileIO.getFileSize(fileName)+" bytes");
        //Read from file
        startTime = System.currentTimeMillis();
        //System.out.println("timestamp= "+startTime);
        List<Good> goods1 = dc.getList();
        System.out.println("Read Time ellapsed: "+((System.currentTimeMillis()-startTime)/1000d)+" sec");
         assertEquals("The same size", goods.size(), goods1.size());
         for (int i=0;i< goods.size();i++) {
             assertEquals("The same Good",goods.get(i), goods1.get(i));
        }
        System.out.println("ITJUnitTest.test3TogetherOnBigFileSaveAndRead() - OK");
    }
}
