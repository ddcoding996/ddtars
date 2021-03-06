package com.ddcoding.collect;

import com.ddcoding.config.Config;
import org.apache.hadoop.fs.FileSystem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Created by ddcoding on 2017/6/25.
 */
public class DataCollector {

    private static final Logger log = LoggerFactory.getLogger(DataCollector.class);

    static {
        initLog4j();
    }

    //目的：避免与Hadoop和Spark的日志混杂在一起
    public static synchronized void initLog4j(){
        String log4jFile = System.getProperty("log4j");
        InputStream in = null;
        //TODO 测试
        if (log4jFile != null) {
            try {
                in = new FileInputStream(new File(log4jFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (in ==null) {
            in = Config.class.getResourceAsStream("/log4j.properties");
        }

        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("日志配置加载完成");
    }

    public static void main(String[] args) {
        CollectorOptions options = new CollectorOptions(args);
        while (true) {
            try {
                run(options);
                if (options.dateTime != null) {  //只取某一个小时的数据，取完就退出
                     break;
                }
                Thread.sleep(options.checkInterval);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    private static void run(CollectorOptions options) throws Exception {
        log.info("Starting data collector, " + options);
        FileSystem fs = null;
        try {
            fs = FSUtils.getFileSystem();
            try(FSUtils.VolatileExecutor executor = FSUtils.createVolatileExecutor("mobinCollector")){
                executor.submitTasks(options.createCollectors(fs));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
