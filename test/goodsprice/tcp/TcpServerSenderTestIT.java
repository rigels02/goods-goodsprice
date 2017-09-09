
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
public class TcpServerSenderTestIT {

    public static int port =63219;
    public static String filePath = "testGoods.csv";
    private static TcpServer server;
    //private static TcpClient client;
    private static ArrayList<Good> data;
    
    public TcpServerSenderTestIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
       
            data = new ArrayList<>();
            for (int i = 0; i < 300; i++) {
                data.add(new Good(new Date(), "Good_" + i, "Shop_" + i, 2.34, 0.00));
            }
        ExportImport exp = new ExportImport();
        try {
            
            exp.exportData(filePath, data);
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TcpServerSenderTestIT.class.getName()).log(Level.SEVERE, null, ex);
        }
         try {    
            
            //port = TcpServer.getRandomPortNumber();
            server = new TcpServer(port, Status.SENDER);
            server.setGoods(data);
             InetAddress addr = server.getServerHostAddress();
            String saddr = server.getServerHostAddressAsString();
        } catch (UnknownHostException ex) {
            Logger.getLogger(TcpServerSenderTestIT.class.getName()).log(Level.SEVERE, null, ex);
        }
        //client = new TcpClient("localhost", port, Status.RECEIVER);
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
        System.out.println("goodsprice.tcp.TcpServerSenderTestIT.testServerRun()");
        System.out.println("testServerRun(): Run client Receiver on other side...");
        server.run();
        System.out.println("goodsprice.tcp.TcpServerSenderTestIT.testServerRun() - OK");
    }
    
    
}
