package com.eden.linebot;

import org.ini4j.Wini;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class FileOperation {

    public String getIniInfo(String key){
        String result = null;
        Wini wini;
        File AbsolutePathFile = new File("");
        String configPath = AbsolutePathFile.getAbsolutePath() + "/config.ini";

        File file = new File(configPath);

        try {
            wini = new Wini(file);
            result = wini.get("Main" , key);
        }catch (Exception e){
            writeLog("Error" , "Get ini info error");
        }

        return result;

    }

    public void writeLog(String title , String msg) {
        String LOG_PATH = getIniInfo("log_path");

        FileWriter fileWriter = null;
        Date now = new Date();

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = day.format(now);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        String timeStr = time.format(now);

        String logName = "log_" + dateStr + ".log";
        String writeLogStr = String.format("(%s) [%s] %s \n", timeStr, title, msg);

        File file = new File(LOG_PATH + "/" + logName);

        //check file doesn't exist, if not create new file
        try {
            if (!file.exists()) {
                file.setExecutable(true);
                file.setReadable(true);
                file.setWritable(true);
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Create log file error");
        }

        //start write log
        try {
            fileWriter = new FileWriter(LOG_PATH + "/" + logName, true);
            fileWriter.write(writeLogStr);
            System.out.println(writeLogStr);
        } catch (Exception e) {
            System.out.println("Write log error");
        } finally {
            try {
                if (fileWriter != null) fileWriter.close();
            } catch (Exception e) {
                System.out.println("Close log file error");
            }
        }
    }



}
