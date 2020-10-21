package com.ddcoding.collect;

/**
 * Created by ddcoding on 2017/6/12.
 */
public class Testm {
    public static void main(String[] args) throws InterruptedException {
           String dateTime = "2017122711";
           String str = "-dateTime " + dateTime + " "
                   + "-collectors XX "
                   + "-parallelizable true ";

           DataCollector.main(str.split(" "));

//         Logger log = LoggerFactory.getLogger(Testm.class);
//        log.info("555");
    }
}
