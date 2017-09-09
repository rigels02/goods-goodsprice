
package goodsprice;

import goodsprice.control.DataControl;
import goodsprice.impoexpo.ExportImport;
import goodsprice.io.FileIOImpl;
import goodsprice.io.PersistenceManager;
import goodsprice.model.Good;
import goodsprice.time.TimeProcess;
import goodsprice.ui.ListView;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @version 1.5
 * @author Raitis
 */
public class GoodsPrice {

     private final static String FMT="CSVBackUp_%s.txt";
     private final static SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy_HH_mm_ss");
    private static TimeProcess timeProc;
        
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        
        ListView lv = new ListView(null);
        
        FileIOImpl<Good> fileIO = new FileIOImpl<>("Goods.bin");

        PersistenceManager<Good> pm = new PersistenceManager<>(fileIO);
        
        DataControl control = new DataControl(pm, lv);
        
        lv.setCallBack(control);
        
         ExportImport impoExpo = new ExportImport();
        control.setExpImp(impoExpo);
        
        
         timeProc = new TimeProcess(new TimerTask() {
            @Override
            public void run() {
                try {
                    String fp= String.format(FMT, df.format(new Date()) );
                    control.exportData(fp);
                    //for test
                     //control.persistDataControlMemory();
                  
                } catch (FileNotFoundException | UnsupportedEncodingException ex) {
                    Logger.getLogger(GoodsPrice.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }, 3000,1000*60*15); //500,1000*8); 
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        
            public void run() {
                lv.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
                lv.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        //In case of using of SwingWorkers check on running
                        //tasks have to be done..
                        //Just return if Worker is active.
                        //Use of while loop would cause a blocking situation here
                        //what is not acceptable
                        if(lv.isWorkersActive()){
                        try {
                        Thread.sleep(500);
                        return; 
                        //System.out.println("Waiting for working stop...");
                        } catch (InterruptedException ex) {
                        Logger.getLogger(GoodsPrice.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        }
                        System.out.println("APPClosing()...");
                        System.exit(0);
                        
                    }
                    
                });
                 lv.setVisible(true);
                lv.getListAndUpdateView();
               
            }
        });
    }
    
}
