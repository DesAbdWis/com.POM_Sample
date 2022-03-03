package tests;

import org.openqa.selenium.Keys;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.AmazonPage;
import utilities.ConfigReader;
import utilities.Driver;

public class D29_DataProvider {

    // amazon sayfasina gidip asagidaki kelimeler icin arama yaptirin
    // ve arma sonuclarini yazdirin
    // Nutella, Mehmet, Zafer, Seref, Phone

    @Test (dataProvider = "arananlarListesi") //asagida @DataProvider notasyonuna sahip olan method calisir..
    public void test01(String arananKelime){ //methodattan gelen arananKelime parametresini kullanir..
        Driver.getDriver().get(ConfigReader.getProperty("amazon_url"));

        AmazonPage amazonPage=new AmazonPage();

        // amazonPage.aramaKutusu.sendKeys("Nutella" + Keys.ENTER);
        amazonPage.aramaKutusu.sendKeys(arananKelime + Keys.ENTER);  //arananKelime parametresini burda kullanir
        System.out.println(amazonPage.sonucYazisiElementi.getText()); //1-48 of 117 results for "Nutella"
        //Driver.closeDriver();
    }

    @DataProvider
    public Object[] arananlarListesi(){

    Object[] arananKelime = new Object[5];
        // Nutella, Mehmet, Zafer, Seref, Phone
        arananKelime[0]= "Nutella";
        arananKelime[1]= "Mehmet";
        arananKelime[2]= "Zafer";
        arananKelime[3]= "Seref";
        arananKelime[4]= "Phone";

        return arananKelime;
    }
}
