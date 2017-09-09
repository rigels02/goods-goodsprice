
package goodsprice.impoexpo;

import goodsprice.io.TxtReaderWriter;
import goodsprice.model.Good;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
public class CSVJUnitTest {
    
    public CSVJUnitTest() {
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


    
   @Test
    public void testmakeCSVString() {
       System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testmakeCSVString()");
        List<Good> goods = new ArrayList<>();
        goods.add(new Good(new Date(117,0,15), "Ātem_1", "Shop_1", 1.23, 1.20));
        goods.add(new Good(new Date(117,0,20), "Itemš_2", "Shop_1", 3.23, 1.20));
        goods.add(new Good(new Date(117,0,30), "Iteām_3", "Shop_1", 1.53, 1.50));
        goods.add(new Good(new Date(117,0,31), "Ātem_4", "Shop_1", 1.25, 1.21));
        
        String csvString = CSV.makeCSVString(goods);
        System.out.println("csvString = "+csvString);
       System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testmakeCSVString() - OK");
    }
    
String csvStr= "15-01-2017,Ātem_1,Shop_1,1.23,1.2\n" +
"20-01-2017,Itemš_2,Shop_1,3.23,1.2\n" +
"30-01-2017,Iteām_3,Shop_1,1.53,1.5\n" +
"31-01-2017,Ātem_4,Shop_1,1.25,1.21\n";
    
 String csvStrMO= "15-01-2017,  Ātem_1 , Shop_1,1.23,1.2\n\n\n" +
"20-01-2017,Itemš_2, Shop_1,3.23,1.2\n" +
"30-01-2017,Iteām_3,Shop_1, 1.53, 1.5 \n" +
"31-01-2017,Ātem_4,Shop_1,1.25,1.21\n\n\n"; 

String csvStrErr= "15-01-2017, Ātem_1, Shop_1,1.23,1.2\n\n" +
"20-01-2017,   Itemš_2,Shop_1, 3.S23, 1.2\n" +
"30-01-2017,Iteām_3,Shop_1,1.53,1.5\n" +
"31-01-2017,Ātem_4,Shop_1,1.25,1.21  \n\n\n"; 

    @Test
    public void testmakeListFromCSVString() throws Exception{
        System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testmakeListFromCSVString()");
       
       
        List<Good> goods = CSV.makeListFromCSVString(csvStrMO);
        
        System.out.println("goods: "+goods.toString());
        String csvStr1 = CSV.makeCSVString(goods);
        assertEquals("Is it the same?", csvStr, csvStr1);
        System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testmakeListFromCSVString() - OK");
    }
    
    @Test( expected = NumberFormatException.class)
    public void testmakeListFromCSVStringWithErrors() throws Exception{
    
        System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testmakeListFromCSVStringWithErrors()");
        List<Good> goods = CSV.makeListFromCSVString(csvStrErr);
        
        System.out.println("goods: "+goods.toString());
        System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testmakeListFromCSVStringWithErrors() - OK");
    }
    
    @Test
    public void testCSVReaderWriter() throws FileNotFoundException, UnsupportedEncodingException, IOException{
        System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testCSVReaderWriter()");
        TxtReaderWriter csvRW = new TxtReaderWriter("TestCsv.txt");
       csvRW.writeToFile(csvStr);
        String result = csvRW.ReadFromFile();
        System.out.println("result: "+result);
        assertEquals("The same csvStr ", csvStr, result);
        System.out.println("goodsprice.impoexpo.InpoExpoJUnitTest.testCSVReaderWriter() - OK");
    }
}
