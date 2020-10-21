package com.ddcoding.collect;

import com.ddcoding.config.Config;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;

import java.io.File;
import java.io.IOException;

/**
 * Created by ddcoding on 2017/5/17.
 */
public class Test {
    @org.junit.Test
    public void mobintest() throws IOException {
        System.out.println(System.getProperty("mm"));
        System.out.println(Test.class.getResourceAsStream("/File_conf.properties"));
        System.out.println(Config.getStringProperty("tar_prefix"));

        File file = new File("E:\\collectProjectFile\\TEST.txt.down");
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.newInstance(conf);
        CollectorOptions collectorOptions = new CollectorOptions();
        collectorOptions.dateTime = "20170628";
        CollectFile collectFile = new CollectFile(fs, file, "F:\\test\\data");
        collectFile.copy();

    }

    @org.junit.Test
    public void test(){
        System.out.println(System.getProperty("java.io.tmpdir"));
    }
}
