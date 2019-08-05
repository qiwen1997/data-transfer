package com.yonyou.einvoice.timing;


import com.yonyou.einvoice.service.Datax;
import com.yonyou.einvoice.util.JsonUtil;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class MyJob implements Job {

  @Autowired
  private JsonUtil jsonUtil;

  @Autowired
  private Datax datax;

  @Override
  public void execute(JobExecutionContext context) throws JobExecutionException {
    log.info("任务执行：" + LocalDateTime.now());
    String ID=jsonUtil.getMaxID();
    jsonUtil.setMaxID(ID);
    datax.doIncrementTest();
    datax.doMaxIDFile();
    log.info("任务结束："+LocalDateTime.now());
  }
}
