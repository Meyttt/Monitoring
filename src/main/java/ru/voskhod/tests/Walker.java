package ru.voskhod.tests;

import org.apache.log4j.Logger;
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
    Logger logger = Logger.getLogger(Main.class);

    public void initDriver() throws IOException {
        System.setProperty("webdriver.chrome.driver", "data/chromedriver.exe");
        config= new Config("config.properties");
        WebDriver driver= new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        this.driver=driver;
        this.wait= new WebDriverWait(driver,30);
    }

    public void walk() throws IOException {
        initDriver();
        driver.get(config.get("urlMain"));
        
        wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("ВХОД В СИСТЕМУ")));
        driver.findElement(By.xpath("//*[@id=\"Login\"]")).sendKeys(config.get("login"));
        driver.findElement(By.xpath("//*[@id=\"Password\"]")).sendKeys(config.get("password"));
        driver.findElement(By.xpath("/html/body/div/div[3]/div/form/fieldset/table/tbody/tr[3]/td/input")).submit();
        logger.info("Проверка доступности страницы "+ config.get("service"));
        currentUrl=config.get("service");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Результаты мониторинга"));
        logger.info("Успешно.");
        logger.info("Проверка доступности страницы "+ config.get("serts"));
        currentUrl=config.get("serts");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Информация о событиях"));
        logger.info("Успешно.");
        logger.info("Проверка доступности страницы "+ config.get("users"));
        currentUrl=config.get("users");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Перечень пользователей подсистемы"));
        logger.info("Успешно.");
        logger.info("Проверка доступности страницы "+ config.get("roles"));
        currentUrl=config.get("roles");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Перечень ролей пользователей подсистемы"));
        logger.info("Успешно.");
        logger.info("Проверка доступности страницы "+ config.get("settings"));
        currentUrl=config.get("settings");
        driver.get(currentUrl);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/div/div[3]/div[1]")));
        Assert.assertTrue(driver.findElement(By.xpath("/html/body/div/div[3]/div[1]")).getText().contains("Перечень параметров подсистемы"));
        logger.info("Успешно.");
    }
    public void closeDriver() throws InterruptedException {
        if(driver!=null){
            Thread.sleep(3000);
            driver.quit();
        }
    }
}
