package navigation;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class navigation {
WebElement  acceptAccess, advanced, email, pass, logo;
WebDriver driver;

    @BeforeTest
    public void beforeSuite() throws IOException {

    }

    @Test
    public void test() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "D:/risk_register_user_roles_issue/AUTOMATION/source/chromedriver_win32/chromedriver.exe");
        //bypass certificate error
        ChromeOptions option= new ChromeOptions();
        option.addArguments("ignore-certificate-errors");
        driver=new ChromeDriver(option);

        driver.get("https://goldenui.spheracloud.internal.spherasolutions.com/insight/");
        //accept access
        //        advanced = driver.findElement(By.id("details-button")) ;advanced.click();
        //        acceptAccess = driver.findElement(By.id("proceed-link"));acceptAccess.click();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //wait until full load
        WebDriverWait wait=new WebDriverWait(driver, 60);
        By _element=By.xpath("//input[@placeholder='Enter Email...']");
    //        wait.until(ExpectedConditions.presenceOfElementLocated(element));
    //        WebElement getElement=wait.until(ExpectedConditions.presenceOfElementLocated(element));

        email= driver.findElement(_element);
        email.sendKeys("dminhhuy@tma.com.vn");
        driver.findElement(By.xpath("//button[@class='Logon-Button']")).click();

        Thread.sleep(5000);
        pass= driver.findElement(By.xpath("//input[@id='Password']"));
        pass.sendKeys("sphera!23$");
        driver.findElement(By.xpath("//button[@name='button']")).click();

        //wait until full load
        wait.until((ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class=\"Dropdown-Header\"]"))));
        driver.findElement(By.xpath("//div[@class=\"Dropdown-Header\"]")).click();

        //select company
        String _value = "Automation_400";
        List<WebElement> _liDropdown = driver.findElements(By.xpath("//span[@class=\"Dropdown-Item\"]"));
        for (WebElement _elem: _liDropdown) {
            if(_elem.getText().equals(_value)) {
                _elem.click();
                break;
            }
        }
        driver.findElement(By.xpath("//button[@class='Logon-Button']")).click();//click Continue

        driver.findElement(By.xpath("//img[@alt=\"Apps\"]")).click();//click App icon
        //select App
        String _appName="Assessments";
        List<WebElement> _appList =driver.findElements(By.xpath("//div[@class=\"HeaderNavigationItem__LinkContent-sc-1ryrdqc-0 cNFyez\"]"));
        for (WebElement _app: _appList){
            if(_app.getText().equals(_appName)){
                _app.click();
                break;
            }
        }

        logo=driver.findElement(By.xpath("//span[@class='Secondary-Nav-Module-View-Name']"));
        Assert.assertEquals(logo.getText(),"Assessments");

        //sort A->Z Attribute text field
        driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[2]/div[1]/div[2]/div[1]/div[3]/div[1]/div[3]/div[1]")).click();

        List<WebElement> _attName =driver.findElements(By.xpath("//div[contains(@class,'SpheraGrid-Container')]/div[2]/div[3]//div[contains(@class,'Cell')]"));
        List<String> _orList= new ArrayList();
        System.out.println("List: ");
        for (WebElement _name: _attName){
                _orList.add(_name.getText());
//                System.out.println(_name.getText());
            }

        List<String> _teList= _orList;
        Collections.sort(_teList);//sort _teList
//        System.out.println(_teList.toString());
        Assert.assertEquals(_teList,_orList);//compare sort list and list on web
    }

    @AfterTest
    public void afterSuite() {
        driver.close();
    }
}

//    public void loginElement(String field,

