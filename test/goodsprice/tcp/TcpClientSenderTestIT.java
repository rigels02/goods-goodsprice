
package goodsprice.tcp;

import goodsprice.impoexpo.ExportImport;
import goodsprice.model.Good;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
public class TcpClientSenderTestIT {

    private static TcpClient client;
    private static List<Good> data;
    
    
    public TcpClientSenderTestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        data = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
        data.add(new Good(new Date(), "Good_" + i, "Shop_" + i, 2.34, 0.00));
        }
         client = new TcpClient("localhost", TcpServerReceiverTestIT.port, Status.SENDER);
        ExportImport expo = new ExportImport();
        
        try {
            expo.exportData(TcpServerReceiverTestIT.filePath, data);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TcpClientSenderTestIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        client.setGoods(data);
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
    public void testClientSender() {
        System.out.println("goodsprice.tcp.TcpClientSenderTestIT.testClientSender()");
        
        client.run();
        Status status = client.getStatus();
        assertEquals(Status.COMPLETED, status);
        
        System.out.println("goodsprice.tcp.TcpClientSenderTestIT.testClientSender() - OK");
    }
}
