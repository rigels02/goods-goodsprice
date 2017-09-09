
package goodsprice.tcp;

import goodsprice.model.Good;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Raitis
 */
public class TcpClientSenderTryManual {
    
    public static void main(String[] args) {
		List<Good> data = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			data.add(new Good(new Date(), "Good_" + i, "Shop_" + i, 2.34, 0.00));
		}

		TcpClient client = new TcpClient("192.168.2.102", 57799, Status.SENDER);
		// TcpClient client = new TcpClient("localhost", 63219);
		client.setGoods(data);
		
		client.run();
		System.out.println("Client Status: "+client.getStatus());
                if(client.getStatus()==Status.ERROR){
             System.out.println("Error: "+client.getError());  
        }
		try {
			//Force to close from external source
			client.getSocket().close();
		} catch (IOException ex) {
			Logger.getLogger(TcpClient.class.getName()).log(Level.SEVERE, null, ex);
		}
		

		System.out.println("Client stopped....");
	}
}
