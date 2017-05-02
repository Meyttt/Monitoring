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
        Walker walker = new Walker();
        Logger logger = Logger.getLogger(Main.class);
        logger.warn("Проверка подсистемы мониторинга от "+new Date());
        File log = new File(new File(".").getAbsolutePath()+"\\..\\"+"log.txt");
        FileWriter fileWriter = new FileWriter(log,true);
        try{
            walker.walk();
            logger.warn("Проверка прошла успешно.");
            fileWriter.append("Проверка подсистемы мониторинга прошла успешно\n");
        } catch (AssertionError e) {
            e.printStackTrace();
            logger.error("Ошибка на странице "+walker.currentUrl);
            logger.error(stackTraceToString(e));
            logger.error("Проверка провалена.");
            fileWriter.append("Проверка подсистемы мониторинга провалена\n");
        } catch (Exception e) {
            logger.error("Ошибка на странице "+walker.currentUrl);
            logger.error(stackTraceToString(e));
            logger.error("Проверка провалена.");
            fileWriter.append("Проверка подсистемы мониторинга провалена\n");
        } finally {
            fileWriter.flush();
            walker.closeDriver();
        }
    }
    public static String stackTraceToString(Throwable e){
        StringBuilder stringBuilder = new StringBuilder();
        for(StackTraceElement stackTraceElement:e.getStackTrace()){
            stringBuilder.append(stackTraceElement.toString());
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }


}
