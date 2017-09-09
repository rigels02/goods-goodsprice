
package goodsprice.tcp;

import goodsprice.impoexpo.ExportImport;
import goodsprice.model.Good;
import java.util.List;
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
public class TcpClientReceiverTestIT {

    private static TcpClient client;
    private static List<Good> data;
    
    
    public TcpClientReceiverTestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
         client = new TcpClient("localhost", TcpServerSenderTestIT.port, Status.RECEIVER);
        ExportImport impo = new ExportImport();
        try {
            data= impo.importData(TcpServerSenderTestIT.filePath);
        } catch (Exception ex) {
            Logger.getLogger(TcpClientReceiverTestIT.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testClientReceiver() {
        System.out.println("goodsprice.tcp.TcpClientReceiverTestIT.testClientReceiver()");
        
        client.run();
        Status status = client.getStatus();
        assertEquals(Status.COMPLETED, status);
        List<Good> goods = client.getGoods();
        System.out.println("Goods received: "+ goods);
        
        assertEquals("Should be the same as expected", 
                data.toString(), 
                goods.toString());
        System.out.println("goodsprice.tcp.TcpClientReceiverTestIT.testClientReceiver() - OK");
    }
}
