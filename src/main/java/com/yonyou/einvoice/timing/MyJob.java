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
    log.info("任务开始：" + LocalDateTime.now());
    String OldID=jsonUtil.getMaxID();
    datax.doMaxIDFile();
    String NewId=jsonUtil.getMaxID();
    jsonUtil.setMaxID(OldID,NewId);
    datax.doIncrementTest();
    log.info("任务结束："+LocalDateTime.now());
  }
}
