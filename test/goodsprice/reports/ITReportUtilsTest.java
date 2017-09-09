
package goodsprice.reports;

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
public class ITReportUtilsTest {
    
    public ITReportUtilsTest() {
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
    public void testdate2String() {
        System.out.println("goodsprice.reports.ITUtilsTest.testdate2String()");
    
        String sdate = ReportUtils.date2String(new Date(117, 1, 23), null);
        assertEquals("23-02-2017", sdate, "23-02-2017");
       // System.out.println("sdate= "+sdate);
       sdate = ReportUtils.date2String(new Date(117, 1, 23), "dd-MM-yyyy");
       assertEquals("23-02-2017", sdate, "23-02-2017");
    System.out.println("goodsprice.reports.ITUtilsTest.testdate2String() - OK");
    }
    @Test
    public void testgetDDMMYYStr() {
        System.out.println("goodsprice.reports.ITUtilsTest.testgetDDStr()");
        String sdd = ReportUtils.getDDStr(23);
        String smm= ReportUtils.getMMStr(1);
        String syy= ReportUtils.getYYStr(117);
        assertEquals("sdd string", sdd, "23");
        assertEquals("smm string", smm, "1");
        assertEquals("syy string", syy, "117");
        System.out.println("goodsprice.reports.ITUtilsTest.testgetDDStr() - OK");
    }
    
    @Test
    public void testmakeMonthYearList(){
        System.out.println("goodsprice.reports.ITUtilsTest.testmakeMonthYearList()");
        
        //inside one year
        List<MonthYear> lst = ReportUtils.makeMonthYearList(new Date(117,1,1), new  Date(117,3,1));
        String expected="[{mm=1, yy=117}, {mm=2, yy=117}, {mm=3, yy=117}]";
        assertEquals("MonthYear's int the same year", expected, lst.toString());
        //System.out.println("MonthYear's: "+lst);
        //on two years crossing
        lst = ReportUtils.makeMonthYearList(new Date(116,9,1), new  Date(117,3,1));
       
        expected="[{mm=9, yy=116}, {mm=10, yy=116}, {mm=11, yy=116}, {mm=0, yy=117}, {mm=1, yy=117}, {mm=2, yy=117}, {mm=3, yy=117}]";
        assertEquals("MonthYear's on two years crossing", expected, lst.toString());
        //one year full between
         lst = ReportUtils.makeMonthYearList(new Date(115,9,1), new  Date(117,3,1));
         
         expected="[{mm=9, yy=115}, {mm=10, yy=115}, {mm=11, yy=115}, {mm=0, yy=116}, {mm=1, yy=116}, {mm=2, yy=116}, {mm=3, yy=116}, {mm=4, yy=116}, {mm=5, yy=116}, {mm=6, yy=116}, {mm=7, yy=116}, {mm=8, yy=116}, {mm=9, yy=116}, {mm=10, yy=116}, {mm=11, yy=116}, {mm=0, yy=117}, {mm=1, yy=117}, {mm=2, yy=117}, {mm=3, yy=117}]";
         assertEquals("MonthYear's on one year full between", expected, lst.toString());
         //-----special cases
         //in one month
         lst = ReportUtils.makeMonthYearList(new Date(117,1,1), new  Date(117,1,28));
         expected= "[{mm=1, yy=117}]";
         assertEquals("MonthYear's in one month", expected, lst.toString());
         //What happens if we put 29th february(mm=1) for 2017 year (yy=117)?:
         // no existing day in 2017 ...
         //it will pass this day to the next month!
         lst = ReportUtils.makeMonthYearList(new Date(117,1,1), new  Date(117,1,29));
         expected= "[{mm=1, yy=117}, {mm=2, yy=117}]";
         assertEquals("MonthYear's with 29th february in 2017", expected, lst.toString());
         //One day
         lst = ReportUtils.makeMonthYearList(new Date(117,1,1), new  Date(117,1,1));
         expected="[{mm=1, yy=117}]";
         assertEquals("MonthYear's in one day", expected, lst.toString());
         
         System.out.println("goodsprice.reports.ITUtilsTest.testmakeMonthYearList() - OK");
    }
}
