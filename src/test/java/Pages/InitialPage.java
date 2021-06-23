package Pages;

import Tests.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class InitialPage extends TestBase {

    @FindBy(xpath =".//img[@title='Cognizant Technology Solutions']")
    private WebElement cognizant;

    @FindBy(xpath =".//a[text()='Carriere']")
    private WebElement btnCareers;

    @FindBy(xpath =".//a[starts-with(text(),'Submit')]")
    //@FindBy(xpath =".//a[@class='submit']")
    private WebElement btnSubmit;

    @FindBy(xpath =".//a[@class='cancel nocon']")
    private WebElement btnCancel;

    @FindBy(xpath =".//a[@class='call']")
    private WebElement btnAgree;

    @FindBy(xpath =".//input[@name='phsKeywords']")
    private WebElement jobTitleField;

    @FindBy(xpath =".//a[@id='Industries']")
    private WebElement btnIndustries;

    @FindBy(xpath =".//iframe[@title='TrustArc Cookie Consent Manager']")
    private WebElement iFrame;

    @FindBy(xpath =".//a[@role='button']")
    private List<WebElement> buttons;

    @FindBy(xpath =".//a[@id='scrollUp']")
    private WebElement btnBackToTop;

    @FindBy(xpath =".//button[@type='submit']")
    private WebElement btnFindJob;

    @FindBy(xpath =".//span[@class='result-count']")
    private WebElement numberJobs;

    //@FindBy(xpath ="(.//div[@class='col-sm-4'])[1]//li/a/ppc-content")
    @FindBy(xpath ="(.//div[@class='columns'])[2]//a")
    private List<WebElement> industries1;

    //@FindBy(xpath ="(.//div[@class='col-sm-4'])[2]//li/a/ppc-content")
    @FindBy(xpath ="(.//div[@class='columns'])[3]//a")
    private List<WebElement> industries2;

    //@FindBy(xpath ="(.//div[@class='col-sm-4'])[3]//li/a/ppc-content")
    @FindBy(xpath ="(.//div[@class='columns'])[4]//a")
    private List<WebElement> industries3;

    private WebDriver driver;
    public InitialPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    public String logoCheck()  {
        String s = cognizant.getAttribute("alt");
        System.out.println("-----> " +s);
        //return driver.getTitle();
        return s;
                 }

    public String getCurrentWindow(){
        return driver.getWindowHandle();
    }

 /*   public int languages(){
        Select languagesList = new Select(languageOnSite);
        List<WebElement> allLanguages = languagesList.getOptions();
        for(WebElement language:allLanguages){
            System.out.println(language.getText());
        }
        System.out.println("There are "+allLanguages.size()+" languages you can pick");
        int numberLanguages = allLanguages.size();
        return numberLanguages;
    }*/

    public int clickCareersSection() throws InterruptedException {
        System.out.println("Click Cancel on pop-up!!!");
        int size = driver.findElements(By.tagName("iframe")).size();
        System.out.println(size);
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,150)");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(iFrame));
        driver.switchTo().frame(iFrame);
        System.out.println(buttons.size()+ " buttons on page....");

        for (WebElement button:buttons) {
            System.out.println(button.getText());
        }
        System.out.println("I got the double click!!!!!!!");
        btnSubmit.click();
        btnSubmit.click();
        System.out.println("Click submit");
        Thread.sleep(4000);
        //System.out.println("Click Careers button!!");
        driver.switchTo().defaultContent();
        System.out.println("out from frame.........");
        Actions a = new Actions(driver);
        a.moveToElement(btnBackToTop).click(btnBackToTop).build().perform();
        //btnBackToTop.click();
        WebDriverWait wait3 = new WebDriverWait(driver, 10);
        wait3.until(ExpectedConditions.elementToBeClickable(btnCareers));

        btnCareers.click();
        System.out.println("Click careers");
        Thread.sleep(5000);
        //btnAllow.click();
        jobTitleField.sendKeys("Test Automation");
        btnFindJob.click();
        System.out.println("Test Automation jobs are: "+Integer.parseInt(numberJobs.getText()));
        return Integer.parseInt(numberJobs.getText());
    }

    public int footerIndustries() {
        JavascriptExecutor je = (JavascriptExecutor) driver;
        WebElement element = driver.findElement(By.xpath("(.//h6[@class='text-blue'])[1]"));
        je.executeScript("arguments[0].scrollIntoView(true);",element);
        WebDriverWait wait3 = new WebDriverWait(driver, 10);
        wait3.until(ExpectedConditions.visibilityOf(element));
        System.out.println("Industries in first footer column:");
         for (WebElement industry1:industries1) { System.out.println(industry1.getText());}
        System.out.println("1st column there are: "+industries1.size());
        System.out.println("Industries in second footer column:");
        for (WebElement industry2:industries2) {System.out.println(industry2.getText()); }
        System.out.println("2nd column there are: "+industries2.size());
        System.out.println("Industries in third footer column:");
        for (WebElement industry3:industries3) {System.out.println(industry3.getText()); }
        System.out.println("3rd column there are: "+industries3.size());
        //return TOTAL LINK INDUSTRIES
        int total = industries1.size() + industries2.size() +industries3.size();
        return total;
    }

}
