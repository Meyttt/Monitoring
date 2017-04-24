package ru.voskhod.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.TimeoutException;

/**
 * Created by a.chebotareva on 17.04.2017.
 */
public class Walker {
    Config config;
    WebDriver driver;
    WebDriverWait wait;
    String currentUrl;

    public void initDriver() throws IOException {
        System.setProperty("webdriver.chrome.driver", "data/chromedriver.exe");
        config= new Config("config.properties");
        WebDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver=driver;
        this.wait= new WebDriverWait(driver,20);
    }

    public void walk() throws IOException {
        initDriver();
        driver.get(config.get("urlMain"));
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Результаты мониторинга аккредитованных удостоверяющих центров в части доступности списков аннулированных сертификатов")));
        }catch (TimeoutException e){
            if(driver.findElements(By.linkText("ВХОД В СИСТЕМУ")).size()>0){
                driver.findElement(By.xpath("//*[@id=\"Login\"]")).sendKeys(config.get("login"));
                driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(config.get("password"));
                driver.findElement(By.xpath("/html/body/div/div[3]/div/form/fieldset/table/tbody/tr[3]/td/input")).submit();
            }
        }
        currentUrl=config.get("service");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Результаты мониторинга"));
        currentUrl=config.get("serts");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Информация о событиях"));
        currentUrl=config.get("users");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Перечень пользователей подсистемы"));
        currentUrl=config.get("roles");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Перечень ролей пользователей подсистемы"));
        currentUrl=config.get("settings");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Перечень параметров подсистемы"));
    }
    public void closeDriver(){
        if(driver!=null){
            driver.close();
            driver.quit();
        }
    }
}