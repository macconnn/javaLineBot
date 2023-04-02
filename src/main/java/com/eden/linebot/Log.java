package com.eden.linebot;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Log {

    private final String LOG_PATH = "/Users/sylvia/Desktop/work/log/LineBot";
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");


    public void writeLog(String title , String msg){
        FileWriter fileWriter = null;
        Date now = new Date();

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = day.format(now);
        SimpleDateFormat time = new SimpleDateFormat("hh:mm:ss");
        String timeStr = time.format(now);

        String logName = "log_" + dateStr + ".log";
        String writeLogStr = String.format("(%s) [%s] %s \n" , timeStr , title , msg);

        File file = new File(LOG_PATH + "/" + logName);

        //check file doesn't exist, if not create new file
        try {
            if(!file.exists()){
                file.setExecutable(true);
                file.setReadable(true);
                file.setWritable(true);
                file.createNewFile();
            }
        }catch (Exception e){
            System.out.println("Create log file error");
        }

        //start write log
        try{
            fileWriter = new FileWriter(LOG_PATH + "/" + logName , true);
            fileWriter.write(writeLogStr);
        }catch (Exception e){
            System.out.println("Write log error");
        }finally {
            try {
                if(fileWriter != null)fileWriter.close();
            }catch (Exception e){
                System.out.println("Close log file error");
            }
        }






    }




}
