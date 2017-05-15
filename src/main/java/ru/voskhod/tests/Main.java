package ru.voskhod.tests;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * Created by a.chebotareva on 13.04.2017.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Walker walker = null;
        Logger logger = Logger.getLogger(Main.class);
        logger.info("********Проверка подсистемы мониторинга от "+new Date()+"********");
        File log = new File(new File(".").getAbsolutePath()+"\\..\\"+"log.txt");
        FileWriter fileWriter = new FileWriter(log,true);
        try{
            walker = new Walker();
            walker.walk();
            logger.info("Проверка прошла успешно.");
            fileWriter.append("Проверка подсистемы мониторинга прошла успешно\r\n");
            fileWriter.flush();
            walker.closeDriver();
            System.exit(0);
        } catch (AssertionError e) {
            e.printStackTrace();
            logger.error("Ошибка на странице "+walker.currentUrl);
            logger.error(stackTraceToString(e));
            logger.error("Проверка провалена.");
            fileWriter.append("Проверка подсистемы мониторинга провалена\r\n");
            fileWriter.flush();
            walker.closeDriver();
            System.exit(1);
        } catch (Exception e) {
            logger.error("Ошибка на странице "+walker.currentUrl);
            logger.error(stackTraceToString(e));
            logger.error("Проверка провалена.");
            fileWriter.append("Проверка подсистемы мониторинга провалена\r\n");
            fileWriter.flush();
            walker.closeDriver();
            System.exit(1);
        }
    }
    public static String stackTraceToString(Throwable e){
        StringBuilder stringBuilder = new StringBuilder();
        for(StackTraceElement stackTraceElement:e.getStackTrace()){
            stringBuilder.append(stackTraceElement.toString());
            stringBuilder.append("\r\n");
        }
        return stringBuilder.toString();
    }


}
