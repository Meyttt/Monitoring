package ru.voskhod.tests;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * Created by a.chebotareva on 13.04.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Walker walker = new Walker();
        Logger logger = Logger.getLogger(Main.class);
        logger.warn("Проверка мониторинга ГУЦ от "+new Date());
        try{
            walker.walk();
            logger.warn("Проверка прошла успешно.");
        } catch (AssertionError e) {
            e.printStackTrace();
            logger.error("Ошибка на странице "+walker.currentUrl);
            logger.error("Проверка провалена.");
        } catch (Exception e) {
            logger.error("Ошибка на странице "+walker.currentUrl);
            logger.error(e.getMessage());
            logger.error("Проверка провалена.");
        } finally {
            walker.closeDriver();
        }
    }


}
