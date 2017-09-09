
package goodsprice.timer;

import goodsprice.control.DataControl;
import goodsprice.impoexpo.CSV;
import goodsprice.impoexpo.ExportImport;
import goodsprice.model.Good;
import goodsprice.time.TimeProcess;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TimeProcessJUnitTest {
    
    public TimeProcessJUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    private void timeTask(){
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.timeTask()- running...");
    }
    
    long startTime;
    @Test
    public void testTimeProcess() throws InterruptedException {
        startTime = System.currentTimeMillis();
        TimeProcess timeProc = new TimeProcess(new TimerTask() {
            @Override
            public void run() {
                timeTask();
                System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" msec");
                startTime = System.currentTimeMillis();
            }
        }, 3000,1000);
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.testTimeProcess() - started");
        
        Thread.sleep(20000);
        timeProc.cancel();
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.testTimeProcess() - Canceled!");
    }
    @Test 
    public void testDataControlExpoImpo() throws IOException, ClassNotFoundException, Exception{
    
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.testDataControlExpoImpo()");
        DataControl dc = new DataControl(null,null);
        //get empty list
        List<Good> lst = dc.getList();
        assertTrue("is empty?", lst.isEmpty());
        
        //--------------------------------
        dc.add(new Good(new Date(), "Item_1", "Shop_1", 2.35d, 0));
        dc.add(new Good(new Date(), "Item_2", "Shop_1", 5.35d, 0));
        dc.add(new Good(new Date(), "Item_3", "Shop_2", 5.00d, 0));
        lst = dc.getList();
        assertTrue("is size 3?", lst.size()==3);
         String filePath="CSV_test.txt";
        ExportImport expoImpo = new ExportImport();
         dc.setExpImp(expoImpo);
        dc.exportData(filePath);
        List<Good> resLst = expoImpo.importData(filePath);
        //String csvStr = CSV.makeCSVString(resLst);
         assertEquals("Is the same List?", lst.toString(), resLst.toString());
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.testDataControlExpoImpo() - OK");
    }
            
    @Test
    public void testTimeProcessWithDataControl() 
            throws IOException, 
            ClassNotFoundException, 
            InterruptedException,
            Exception{
    
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.testTimeProcessWithDataControl()");
        DataControl dc = new DataControl(null,null);
        //get empty list
        List<Good> lst = dc.getList();
        assertTrue("is empty?", lst.isEmpty());
        
        //--------------------------------
        dc.add(new Good(new Date(), "Item_1", "Shop_1", 2.35d, 0));
        dc.add(new Good(new Date(), "Item_2", "Shop_1", 5.35d, 0));
        dc.add(new Good(new Date(), "Item_3", "Shop_2", 5.00d, 0));
        lst = dc.getList();
        assertTrue("is size 3?", lst.size()==3);
        startTime= System.currentTimeMillis();
       
        String FMT="CSV_Test_%s.txt";
        SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy_HH_MM_SS");
        
        ExportImport expoImpo = new ExportImport();
        dc.setExpImp(expoImpo);
        
         TimeProcess timeProc = new TimeProcess(new TimerTask() {
            @Override
            public void run() {
                try {
                    String fp= String.format(FMT, df.format(new Date()) );
                    dc.exportData(fp);
                    System.out.println("Time: "+(System.currentTimeMillis()-startTime)+" msec");
                    startTime = System.currentTimeMillis();
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(TimeProcessJUnitTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 3000,1000);
         Thread.sleep(10000);
        //List<Good> resLst = expoImpo.importData(filePath);
        //assertEquals("Is the same?", lst.toString(), resLst.toString());
          
        System.out.println("goodsprice.timer.TimeProcessJUnitTest.testTimeProcessWithDataControl( - OK)");
    }
}
