package org.jack.job;


import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SpringbootTestJob {

    @Scheduled(cron = "0/5 * * * * ?")
    private void test() {
        System.out.println("job test");

    }
}
