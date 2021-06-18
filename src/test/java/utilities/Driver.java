package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.concurrent.TimeUnit;

public class Driver {

    private Driver() {
        // Eger default cons'i yazar ve access modifier'ini private yaparsak,
        // Class disindan bu cons'a ulasilamaz ve dolayisiyla bu class'dan
        // obje uretilemez. Buna "SINGLETON CLASS" denir.

    }

    static private WebDriver driver; //static: class ismi ile ulasmak icin - private : baska classslardan degistirilmesin, korumak icin

    public static WebDriver getDriver() {

        if (driver==null){
            // driver ilk degeri olmadigi icin(null) once chromedriver olusturur sonrasinda null olmadigindan chromedriver olusturmayacak
            // WebDriver driver; default degeri null diyebiliriz

            switch (ConfigReader.getProperty("browser")){
                case "chrome" :
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver= new FirefoxDriver();
                    break;
                case "opera":
                    WebDriverManager.operadriver().setup();
                    driver= new OperaDriver();
                    break;
                case "edge":
                    WebDriverManager.edgedriver().setup();
                    driver= new EdgeDriver();
                    break;
                case "safari":
                    WebDriverManager.getInstance(SafariDriver.class);
                    driver=new SafariDriver();
                    break;
            }
        }
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }

    public static void closeDriver() {

        if (driver != null) {
            driver.close();
            driver=null; //driver kapatinca degeri de silinsin diye null yaptik..cunku bir baska class icin calistirinca en bastan baslasin..
        }

    }

}
