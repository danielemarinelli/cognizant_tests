package Tests;

import Pages.InitialPage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import core.TestReporter;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RunTestSuite extends TestBase{

    InitialPage initPage;
    TestReporter test;

    @Test(priority=1,description="This test checks the initial web page")
    public void checkInitialPage() throws Exception {
        initPage = new InitialPage(driver());
        //Assert.assertEquals(initPage.logoCheck(),"Cognizant Technology Solutions",
        //       "The initial webpage is wrong");
        System.out.println("Title page:::: " + initPage.logoCheck());
        test = new TestReporter();
        if(initPage.logoCheck().equals("Cognizant Technology Solutions")){
        test.report(LogStatus.PASS, "step1", "Navigated to the specified URL", false);
        }else{
            test.report(LogStatus.FAIL, "step1", "Navigated to the specified URL", true);
        }
    }

    @Test(priority=2,description="This test checks the jobs in the career section")
    public void checkJobsInCareerSection() throws Exception {
        initPage = new InitialPage(driver());
        int countJobs = initPage.clickCareersSection();
        test = new TestReporter();
        if(countJobs>=0){
            test.report(LogStatus.PASS, "step2", "Jobs count after job search", false);
        }else{
            test.report(LogStatus.FAIL, "step2", "Jobs count after job search", true);
        }

    }

    @Test(priority=3,description="This test checks the industries footer")
    public void checkIndustriesFooter() throws Exception {
        initPage = new InitialPage(driver());
        int footerInd = initPage.footerIndustries();
        System.out.println("Cognizant industries printed...");
        test = new TestReporter();
        if(footerInd==11){
            test.report(LogStatus.PASS, "step3", "Cognizant industries in footer", false);
        }else{
            test.report(LogStatus.FAIL, "step3", "Cognizant industries in footer", true);
        }
    }

    @Test(priority=4,description="This test checks the customer info sending")
    public void checkSentCustomerInfo() throws Exception {
        initPage = new InitialPage(driver());
        initPage.customerRequest();
        System.out.println("fine test....");
    }
    
}
