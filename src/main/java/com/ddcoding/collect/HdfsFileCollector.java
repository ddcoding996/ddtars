package com.ddcoding.collect;


import com.ddcoding.config.Config;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by ddcoding on 2017/5/17.
 */
public class HdfsFileCollector extends Collector {

    public HdfsFileCollector(){
        type = "XX";  //采集的文件类型
        collectorPath = Config.collectorPath;   //采集根目录
        targetPath = Config.targetPath;
    }

    //获取新文件
    @Override
    public Map<String, ArrayList<CollectFile>> getNewFiles() {
//        File mobin_prefix_dir = new File(mobin_prefix);
//        if (!mobin_prefix_dir.exists()) {
//            log.warn("dir not exists : " + mobin_prefix_dir);
//            return null;
//        }
//
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        List<String> dirs = new ArrayList<>();
//        for (String file : mobin_prefix_dir.list()) {
//            dirs.add(file);
//        }

        FilenameFilter filter = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                String n = name.toLowerCase();
                return n.endsWith(DOWN) || n.endsWith(DONE);
            }
        };
        return getNewFiles(getDateDir(), filter);
    }

    @Override
    public String getFileDateTime(String fileName) {
        //xxxx_20170622205522
        int pos = fileName.lastIndexOf('_') + 1;
        return fileName.substring(pos, pos + 10);  //小时
    }
}
