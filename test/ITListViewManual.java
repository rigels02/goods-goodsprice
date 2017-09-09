
import goodsprice.control.DataControl;
import goodsprice.io.FileIOImpl;
import goodsprice.io.IFileIO;
import goodsprice.io.IPersistenceManager;
import goodsprice.io.PersistenceManager;
import goodsprice.model.Good;
import goodsprice.ui.ListView;
import goodsprice.views.IListView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import goodsprice.control.IDataControl;



/**
 *
 * @author Raitis
 */
public class ITListViewManual {
    
    class FileIOImplStub<T> implements IFileIO<T> {

       

        private FileIOImplStub() {
     
        }

        @Override
        public void saveList(List<T> ListOfObjects) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<T> readListStream() throws IOException, ClassNotFoundException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public Timestamp getTimeStamp() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
   }
    
    class PersistenceManagerStub<T> implements IPersistenceManager<T> {

        @Override
        public List<T> readListDB() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public List<T> readListStream() throws IOException, ClassNotFoundException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void saveList(List<T> ListOfObjects) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    
    }
    
    class DataControlStub implements IDataControl {

        private final ArrayList<Good> goods;
        private final IPersistenceManager pm;
        private final IListView lv;

        public DataControlStub(IPersistenceManager pm, IListView lv) {
            goods = new ArrayList<>();
            this.pm= pm;
            this.lv= lv;
        }

        private void saveAndUpdateView() throws IOException {
        if (pm != null) {
            pm.saveList(goods);
        }
        if (lv != null) {
            lv.updateListView(goods);
        }
    }
        @Override
        public Good getItem(int id) throws IndexOutOfBoundsException {
          return goods.get(id);
          //  throw new IndexOutOfBoundsException("Ok - IndexOutOfBoundsException!");
        }

        @Override
        public void add(Good good) throws IOException {
           // throw new IOException("OK - IOException1 !"); 
           goods.add(good);
           saveAndUpdateView();
        }

        @Override
        public void del(int id) throws IOException {
            //throw new IOException("OK - IOException2 !");
            goods.remove(id);
             saveAndUpdateView();
        }

        @Override
        public void edit(int id, Good good) throws IOException {
            goods.set(id, good);
              saveAndUpdateView();
            //throw new IOException("OK - IOException3 !");
        }

        @Override
        public List<Good> getList() throws IOException, ClassNotFoundException {
           /**
            * To check exceptions handling by ListView uncomment below statement
            * and comment out return stm.
            */
            // throw new IOException("OK - IOException4 !");
           
           
           /**
            * normally when file is just created or empty then getList() should
            * give empty list. No any exception occurs, because current selected
            * index is -1 and there are protections on -1 in ListView methods.
            * Comment out below to check exceptions handling. See above.
            */
           return goods;
        }

        @Override
        public List<Good> getListFromDataControlMemory() {
          return goods;
        }

        @Override
        public void startPersistDataControlMemoryThread() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
        @Override
        public Good simulateAccessToDataControlMemoryGood(String txt) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void exportData(String filePath) throws FileNotFoundException, UnsupportedEncodingException {
            try {
                //throw new FileNotFoundException("FileNotFoundException:exportData "); //To change body of generated methods, choose Tools | Templates.
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ITListViewManual.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void importData(String filePath) throws IOException, Exception {
            //throw new IOException("IOException : importData "); 
            Thread.sleep(2000);
        }

        @Override
        public void persistDataControlMemory() throws FileNotFoundException, UnsupportedEncodingException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        @Override
        public void importData(List<Good> goods) throws IOException {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
        
    }
    
    private void doTest(){
    
        System.out.println("ITListViewManual.doTest()");
        
         ListView lv = new ListView(null);
        
        IFileIO<Good> fileIO = new FileIOImplStub<>();

        IPersistenceManager<Good> pm = new PersistenceManagerStub<>();
        
        IDataControl control = new DataControlStub(null,lv);
        
        lv.setCallBack(control);
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        
            public void run() {
                  lv.setVisible(true);
                lv.getListAndUpdateView();
              
            }
        });
        
        System.out.println("ITListViewManual.doTest() - OK");
    }
    
    /**
     * First run. no any data (file) available.
     */
    private void doTestFirstRun() throws IOException, ClassNotFoundException{
        String fileName= "testGoods.bin";
        
        File file = new File(fileName);
        if(file.exists()){
        file.delete();
        }
        ListView lv = new ListView(null);
        
        FileIOImpl<Good> fileIO = new FileIOImpl<>(fileName);

        PersistenceManager<Good> pm = new PersistenceManager<>(fileIO);
        
        DataControl control = new DataControl(pm, lv);
        
        lv.setCallBack(control);
        
         /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
        
            public void run() {
                  lv.setVisible(true);
                lv.getListAndUpdateView();
              
            }
        });
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        
        ITListViewManual test = new ITListViewManual();
        test.doTest();
        //test.doTestFirstRun();
    }
}
