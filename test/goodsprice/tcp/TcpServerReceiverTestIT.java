
package goodsprice.tcp;

import goodsprice.impoexpo.ExportImport;
import goodsprice.model.Good;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
public class TcpServerReceiverTestIT {

    public static int port =63219;
    public static String filePath = "testGoods.csv";
    private static TcpServer server;
    //private static TcpClient client;
    private static List<Good> expected;
    
    public TcpServerReceiverTestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
        /*data = new ArrayList<>();
        for (int i = 0; i < 300; i++) {
        data.add(new Good(new Date(), "Good_" + i, "Shop_" + i, 2.34, 0.00));
        }*/
        
        //port = TcpServer.getRandomPortNumber();
        server = new TcpServer(port, Status.RECEIVER);
        //server.setGoods(data);
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

    @Test
    public void testServerRun() {
        System.out.println("goodsprice.tcp.TcpServerReceiverTestIT.testServerRun()");
        System.out.println("testServerRun(): Run client Sender on other side...");
        server.run();
        Status status = server.getStatus();
        assertEquals("Completed", Status.COMPLETED, status);
        
        ExportImport exp = new ExportImport();
        try {
            expected =  exp.importData(filePath);
        } catch (Exception ex) {
            Logger.getLogger(TcpServerReceiverTestIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Good> goods = server.getGoods();
        assertEquals("The same result", expected.toString(), goods.toString());
        System.out.println("goodsprice.tcp.TcpServerReceiverTestIT.testServerRun() - OK");
    }
    
    
}
