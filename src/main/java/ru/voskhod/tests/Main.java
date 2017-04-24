package ru.voskhod.tests;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Date;

/**
 * Created by a.chebotareva on 13.04.2017.
 */
public class Main {
    public static void main(String[] args) {
        Walker walker = new Walker();
        Logger logger = Logger.getLogger(Main.class);
        logger.warn("Проверка от "+new Date());
        try{
            walker.walk();
            logger.warn("Проверка прошла успешно.");
        } catch (AssertionError e) {
            e.printStackTrace();
            logger.error("Ошибка на странице "+walker.currentUrl);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            walker.closeDriver();
        }
    }


}
