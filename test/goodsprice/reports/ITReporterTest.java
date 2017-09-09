
package goodsprice.reports;

import goodsprice.model.Good;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
public class ITReporterTest {
    
    public ITReporterTest() {
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
    public void testMonthReport() throws IllegalAccessException {
        System.out.println("goodsprice.reports.ITReporteTest.testMonthReport()");
        List<Good> allList= new ArrayList<>();
        
        allList.add(new Good(new Date(116, 1, 10), "Item_1", "Shop_1", 1.50, 1.23));
        allList.add(new Good(new Date(116, 1, 10), "Item_2", "Shop_1", 3.20, 3.03));
        allList.add(new Good(new Date(116, 1, 13), "Item_1", "Shop_1", 1.50, 1.03));
        allList.add(new Good(new Date(116, 1, 14), "Item_2", "Shop_1", 3.20, 2.90));
        
        Reporter reporter = new Reporter(allList);
        reporter.initData(new Date(116,1,5), "Shop_1");
        //reporter.makeTimeList("Shop_1");
       
        Map<Integer, DayReport> report = reporter.monthReport(1,116);
        System.out.println("report: "+report);
        String result = report.toString();
        String expected="{10=DayReport{\n" +
"perDay=[\n" +
">d=d=10, m=1, y=116, name=Item_1, dprice=0.0, dpricePercent=0.0, ddiscount=0.0, ddiscountPercent=0.0, shop=Shop_1, \n" +
">d=d=10, m=1, y=116, name=Item_2, dprice=0.0, dpricePercent=0.0, ddiscount=0.0, ddiscountPercent=0.0, shop=Shop_1]\n" +
"}, 13=DayReport{\n" +
"perDay=[\n" +
">d=d=13, m=1, y=116, name=Item_1, dprice=0.0, dpricePercent=0.0, ddiscount=-0.19999999999999996, ddiscountPercent=-16.260162601626014, shop=Shop_1]\n" +
"}, 14=DayReport{\n" +
"perDay=[\n" +
">d=d=14, m=1, y=116, name=Item_2, dprice=0.0, dpricePercent=0.0, ddiscount=-0.1299999999999999, ddiscountPercent=-4.290429042904288, shop=Shop_1]\n" +
"}}";
        assertEquals("Should be", expected, result);
        System.out.println("goodsprice.reports.ITReporteTest.testMonthReport() - OK");
    }
  
    private List<Good> initGoodList(){
      List<Good> allList= new ArrayList<>();
        allList.add(new Good(new Date(116, 1, 10), "Item_1", "Shop_1", 1.50, 1.23));
          //   allList.add(new Good(new Date(116, 1, 10), "Item_1", "Shop_1", 1.40, 1.23));
        allList.add(new Good(new Date(116, 1, 10), "Item_2", "Shop_1", 3.20, 3.03));
        allList.add(new Good(new Date(116, 1, 13), "Item_1", "Shop_1", 1.50, 1.03));
        allList.add(new Good(new Date(116, 1, 14), "Item_2", "Shop_1", 3.20, 2.90));
        allList.add(new Good(new Date(116, 2, 10), "Item_1", "Shop_1", 1.50, 1.13));
        allList.add(new Good(new Date(116, 2, 10), "Item_2", "Shop_1", 3.20, 3.00));
        allList.add(new Good(new Date(116, 2, 13), "Item_1", "Shop_1", 1.50, 1.25));
        allList.add(new Good(new Date(116, 2, 14), "Item_2", "Shop_1", 3.20, 2.80));
        return allList;
    }
    
    private List<Good> initGoodListManyShops(){
      List<Good> allList= new ArrayList<>();
        allList.add(new Good(new Date(117, 0, 10), "Item_1", "Shop_1", 1.50, 1.23));
        allList.add(new Good(new Date(117, 0, 10), "Item_1", "Shop_2", 1.30, 1.13)); 
        allList.add(new Good(new Date(117, 0, 10), "Item_2", "Shop_1", 3.20, 3.03));
        allList.add(new Good(new Date(117, 0, 12), "Item_3", "Shop_3", 43.20, 43.03));
        allList.add(new Good(new Date(117, 0, 13), "Item_1", "Shop_1", 1.50, 1.03));
        allList.add(new Good(new Date(117, 0, 14), "Item_2", "Shop_1", 3.20, 2.90));
        allList.add(new Good(new Date(117, 1, 10), "Item_1", "Shop_2", 1.40, 1.03));
        allList.add(new Good(new Date(117, 1, 10), "Item_2", "Shop_1", 3.20, 3.00));
        allList.add(new Good(new Date(117, 1, 13), "Item_1", "Shop_1", 1.50, 1.25));
        allList.add(new Good(new Date(117, 1, 12), "Item_3", "Shop_3", 43, 42));
        allList.add(new Good(new Date(117, 1, 14), "Item_2", "Shop_1", 3.20, 2.80));
        return allList;
    }
    
    private List<Good> initGoodListCrossYearManyShops(){
      List<Good> allList= new ArrayList<>();
        allList.add(new Good(new Date(116, 11, 10), "Item_1", "Shop_1", 1.50, 1.23));
        allList.add(new Good(new Date(116, 11, 10), "Item_1", "Shop_2", 1.30, 1.13)); 
        allList.add(new Good(new Date(116, 11, 10), "Item_2", "Shop_1", 3.20, 3.03));
        allList.add(new Good(new Date(116, 11, 12), "Item_3", "Shop_3", 43.20, 43.03));
        allList.add(new Good(new Date(116, 11, 13), "Item_1", "Shop_1", 1.50, 1.03));
        allList.add(new Good(new Date(116, 11, 14), "Item_2", "Shop_1", 3.20, 2.90));
        allList.add(new Good(new Date(117, 0, 10), "Item_1", "Shop_2", 1.40, 1.03));
        allList.add(new Good(new Date(117, 0, 10), "Item_2", "Shop_1", 3.20, 3.00));
        allList.add(new Good(new Date(117, 0, 13), "Item_1", "Shop_1", 1.50, 1.25));
        allList.add(new Good(new Date(117, 0, 12), "Item_3", "Shop_3", 43, 42));
        allList.add(new Good(new Date(117, 0, 14), "Item_2", "Shop_1", 3.20, 2.80));
        return allList;
    }
    @Test 
    public void testtimeReport(){
        System.out.println("goodsprice.reports.ITReporterTest.testtimeReport()");
          List<Good> allList= initGoodList();
          
        Reporter reporter = new Reporter(allList);
        
        Date startDate=null;
        reporter.initData(startDate=new Date(116,1,10), "Shop_1");
        List<MonthYearReport> report = reporter.timeReport(startDate, new Date(116,2,14));
        String expected="[\n" +
"MonthYearReport{my={mm=1, yy=116},\n" +
"reportsPerDay=\n" +
"{10=DayReport{\n" +
"perDay=[\n" +
">d=d=10, m=1, y=116, name=Item_1, dprice=0.0, dpricePercent=0.0, ddiscount=0.0, ddiscountPercent=0.0, shop=Shop_1, \n" +
">d=d=10, m=1, y=116, name=Item_2, dprice=0.0, dpricePercent=0.0, ddiscount=0.0, ddiscountPercent=0.0, shop=Shop_1]\n" +
"}, 13=DayReport{\n" +
"perDay=[\n" +
">d=d=13, m=1, y=116, name=Item_1, dprice=0.0, dpricePercent=0.0, ddiscount=-0.19999999999999996, ddiscountPercent=-16.260162601626014, shop=Shop_1]\n" +
"}, 14=DayReport{\n" +
"perDay=[\n" +
">d=d=14, m=1, y=116, name=Item_2, dprice=0.0, dpricePercent=0.0, ddiscount=-0.1299999999999999, ddiscountPercent=-4.290429042904288, shop=Shop_1]\n" +
"}}}, \n" +
"MonthYearReport{my={mm=2, yy=116},\n" +
"reportsPerDay=\n" +
"{10=DayReport{\n" +
"perDay=[\n" +
">d=d=10, m=2, y=116, name=Item_1, dprice=0.0, dpricePercent=0.0, ddiscount=-0.10000000000000009, ddiscountPercent=-8.130081300813014, shop=Shop_1, \n" +
">d=d=10, m=2, y=116, name=Item_2, dprice=0.0, dpricePercent=0.0, ddiscount=-0.029999999999999805, ddiscountPercent=-0.9900990099009837, shop=Shop_1]\n" +
"}, 13=DayReport{\n" +
"perDay=[\n" +
">d=d=13, m=2, y=116, name=Item_1, dprice=0.0, dpricePercent=0.0, ddiscount=0.020000000000000018, ddiscountPercent=1.6260162601626031, shop=Shop_1]\n" +
"}, 14=DayReport{\n" +
"perDay=[\n" +
">d=d=14, m=2, y=116, name=Item_2, dprice=0.0, dpricePercent=0.0, ddiscount=-0.22999999999999998, ddiscountPercent=-7.590759075907591, shop=Shop_1]\n" +
"}}}]";
        assertEquals("Two months MonthYearReport's", expected, report.toString());
        //report = reporter.timeReport(startDate, new Date(116,2,20));
        System.out.println("MonthYearReport's: "+report.toString());
         System.out.println("goodsprice.reports.ITReporterTest.testtimeReport() - OK");
    }
    
    @Test 
    public void testtimeReport_1(){
        System.out.println("goodsprice.reports.ITReporterTest.testtimeReport_1()");
          List<Good> allList= initGoodList();
          
        Reporter reporter = new Reporter(allList);
        
        Date startDate=null;
        reporter.initData(startDate=new Date(116,1,10), "Shop_1");
        List<MonthYearReport> report = reporter.timeReport(startDate, new Date(116,2,14));
        
        for (MonthYearReport monthYearReport : report) {
            Set<Integer> keys = monthYearReport.getReportsPerDay().keySet();
            if(monthYearReport.getMy().getMm()==1 && 
                   monthYearReport.getMy().getYy()==116 ){
            HashSet<Integer> keyExpect = new HashSet<Integer>(Arrays.asList(10,13,14));
            for (Integer key : keys) {
                assertTrue("key as expected 1", keyExpect.contains(key));
            }
                
            }
            Map<Integer, DayReport> dayReports = monthYearReport.getReportsPerDay();
            for (Integer day : dayReports.keySet()) {
                if(monthYearReport.getMy().getMm()==1 && day == 10){
                    List<ReportModel> perDay = dayReports.get(day).getPerDay();
                    //perDay.get(0).ddiscount==0.0;
                    assertEquals("discount m=1, day=10", 0,perDay.get(0).ddiscount,0.001);
                    assertEquals("discount m=1, day=10", 0,perDay.get(1).ddiscount,0.001);
                }
                if(monthYearReport.getMy().getMm()==1 && day == 13){
                    List<ReportModel> perDay = dayReports.get(day).getPerDay();
                    //-0.19999999999999996
                    assertEquals("discount m=1, day=13", -0.2,
                            perDay.get(0).ddiscount,0.001);
                    
                }
                if(monthYearReport.getMy().getMm()==1 && day == 14){
                    List<ReportModel> perDay = dayReports.get(day).getPerDay();
                    //-0.1299999999999999
                    assertEquals("discount m=1, day=14", -0.13,
                            perDay.get(0).ddiscount,0.001);
                    
                }
                if(monthYearReport.getMy().getMm()==2 && day == 10){
                    List<ReportModel> perDay = dayReports.get(day).getPerDay();
                    
                    assertEquals("discount m=2, day=10", -0.10,
                            perDay.get(0).ddiscount,0.001);
                    assertEquals("discount m=2, day=10", -0.03,
                            perDay.get(1).ddiscount,0.001);
                    
                }
            }
           
        }
        
         System.out.println("goodsprice.reports.ITReporterTest.testtimeReport_1() - OK");
    }
    
    @Test 
    public void testTimeReportWithManyShops(){
        System.out.println("goodsprice.reports.ITReporterTest.testTimeReportWithManyShops()");
        List<Good> allList= initGoodListManyShops();
          
        Reporter reporter = new Reporter(allList);
        
        Date startDate=null;
        reporter.initData(startDate=new Date(116,1,10), "Shop_3");
        List<MonthYearReport> report = reporter.timeReport(startDate, new Date(116,2,14));
        
        System.out.println("Report: "+report);
        System.out.println("goodsprice.reports.ITReporterTest.testTimeReportWithManyShops() - OK");
    }
    
    @Test 
    public void testtesttimeReportManualy(){
        System.out.println("goodsprice.reports.ITReporterTest.testtesttimeReportManualy()");
        List<Good> allList= new ArrayList<>();
         allList.add(new Good(new Date(116, 0, 9), "Item_3", "Shop_1", 1.50, 1.23));
        allList.add(new Good(new Date(116, 1, 10), "Item_1", "Shop_1", 1.50, 1.23));
        allList.add(new Good(new Date(116, 1, 10), "Item_2", "Shop_1", 3.20, 3.03));
        allList.add(new Good(new Date(116, 1, 13), "Item_1", "Shop_1", 1.50, 1.03));
        allList.add(new Good(new Date(116, 1, 14), "Item_2", "Shop_1", 3.20, 2.90));
        allList.add(new Good(new Date(116, 2, 10), "Item_1", "Shop_1", 1.40, 1.13));
        allList.add(new Good(new Date(116, 2, 10), "Item_2", "Shop_1", 3.10, 3.00));
        allList.add(new Good(new Date(116, 2, 13), "Item_1", "Shop_1", 1.55, 1.25));
        allList.add(new Good(new Date(116, 2, 14), "Item_2", "Shop_1", 3.25, 2.80));
        Reporter reporter = new Reporter(allList);
        
        Date startDate=null;
        reporter.initData(startDate=new Date(116,0,9), "Shop_1");
        List<MonthYearReport> report = reporter.timeReport(startDate, new Date(116,2,14));
        
         System.out.println("MonthYearReport's: "+report.toString());
        System.out.println("goodsprice.reports.ITReporterTest.testtesttimeReportManualy() - OK");
    }
    
    @Test
    public void testStringFormaterForMonthReport(){
        System.out.println("goodsprice.reports.ITReporterTest.testStringFormaterForMonthReport()");
         List<Good> allList= initGoodList();
          
        Reporter reporter = new Reporter(allList);
        
        Date startDate=null;
        reporter.initData(startDate=new Date(116,1,10), "Shop_1");
        List<MonthYearReport> reports = reporter.timeReport(startDate, new Date(116,2,14));
        
        StringFormater formater = new StringFormater();
        String report = formater.printFmtStr(reports.get(0));
        System.out.println("report: "+report);
        System.out.println("goodsprice.reports.ITReporterTest.testStringFormaterForMonthReport() - OK");
    }
    @Test
    public void testStringFormaterForMonthReports(){
        System.out.println("goodsprice.reports.ITReporterTest.testStringFormaterForMonthReports()");
         List<Good> allList= initGoodListManyShops();
          
        Reporter reporter = new Reporter(allList);
        
        Date startDate=null;
        reporter.initData(startDate=new Date(116,1,10), null);
        List<MonthYearReport> reports = reporter.timeReport(startDate, new Date(116,2,14));
        
        StringFormater formater = new StringFormater();
        String report = formater.printFmtStr(reports);
        System.out.println("report: "+report);
        System.out.println("goodsprice.reports.ITReporterTest.testStringFormaterForMonthReports() - OK");
    }
    @Test
    public void testStringFormatergetAndTimeReportForAllShops(){
        System.out.println("goodsprice.reports.ITReporterTest.testStringFormatergetAndTimeReportForAllShops()");
        
         List<Good> allList= initGoodListCrossYearManyShops();
          
        Reporter reporter = new Reporter(allList);
        Date startDate = new Date(116, 11, 1);
        Date endDate = new Date();
        List<MonthYearReport> reports = reporter.getTimeReportForAllShops(startDate, endDate);
        StringFormater formater = new StringFormater();
        String reportTxt = formater.printFmtStr(reports);
        System.out.println("goodsprice.reports.ITReporterTest.testStringFormatergetAndTimeReportForAllShops() - OK");
    }
}
