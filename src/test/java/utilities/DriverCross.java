package utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class DriverCross {

    private DriverCross() {
    }


    static WebDriver driver;


    public static WebDriver getDriver(String browser) {//chrome -- bu browser parametresi TestBaseCross dan geliyor

        // Eger browser olarak gelen parametrenin değeri "null" ise, yani bos bir deger geldiyse
        // o zaman guvenlik onlemi olarak, .properties dosyasindan browser degerini al ve kullan
        // eger browser'in degeri null degilse hangisi geldiyse onu kullanabilirsiniz.
        browser = browser == null ? ConfigReader.getProperty("browser") : browser;
        //-------------------------------default---------------------------xml-----
        if (driver == null) {
            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver = new OperaDriver();
                    break;
                case "safari":
                    WebDriverManager.getInstance(SafariDriver.class).setup();
                    driver = new SafariDriver();
                    break;
                case "headless-chrome":
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(new ChromeOptions().setHeadless(true));
                    break;
            }

        }

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {
        // Eğer driver nesnesi NULL değilse, yani hafızada varsa
        if (driver != null) {
            driver.close();  // driver'ı kapat
            driver = null;  // driver'ı hafızadan temizle.
        }
    }
}