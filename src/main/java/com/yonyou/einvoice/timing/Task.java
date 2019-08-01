package com.yonyou.einvoice.timing;

import com.yonyou.einvoice.service.Datax;
import com.yonyou.einvoice.service.DataxImpl;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Configuration
@EnableScheduling
public class Task {

  private Logger logger= LoggerFactory.getLogger(Task.class);

  @Autowired
  private Datax datax;

  @Scheduled(cron="${cron}")
  private void configureTasks(){
    logger.info("定时任务启动"+ LocalDateTime.now());
    datax.doTesk();
  }
}
