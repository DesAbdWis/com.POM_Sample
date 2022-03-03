package utilities;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public abstract class TestBaseCross {

    protected WebDriver driver;

    // @Optinal : Eger browser diye herhangi bir
    // parametre gelmezse bile burası calissin diyoruz.
    // xml de parameter belirtilmezse conf. properties de browser ne ise o calistirir conf properties deki default oluyor yani

    // Bize parametre olarak browser gelecek(gelirse)
    // Biz de o parametreyi kullanacagiz.
    // Bu paramatre, xml dosyasindan gelecek

    @Parameters("browser") //@Parameters : xml de yazilan parameter name=browser i buraya getirir
    @BeforeMethod
    public void setUp(@Optional String browser){
        driver = DriverCross.getDriver(browser); //DriverCross a browser i alip gider
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void tearDown(){

        //DriverCross.closeDriver();
    }

}