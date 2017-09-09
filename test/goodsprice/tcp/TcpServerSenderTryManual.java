package goodsprice.tcp;

import goodsprice.model.Good;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raitis
 */
public class TcpServerSenderTryManual {

    public static void main(String[] args) throws UnknownHostException {
        
        List<Good> data = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			data.add(new Good(new Date(), "Good_" + i, "Shop_" + i, 2.34, 0.00));
		}
                
        TcpServer server = new TcpServer(63219, Status.SENDER);
        server.setGoods(data);
        InetAddress addr = server.getServerHostAddress();
        String saddr = server.getServerHostAddressAsString();
        int port = TcpServer.getRandomPortNumber();

        server.run();
        System.out.println("Status: " + server.getStatus());
        if(server.getStatus()==Status.ERROR){
             System.out.println("Error: "+server.getError());  
        }
        //System.out.println("Received goods: " + server.getGoods());
        try {
            //Force to close from external source
            server.getServerSocket().close();
        } catch (IOException ex) {
            Logger.getLogger(TcpServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
