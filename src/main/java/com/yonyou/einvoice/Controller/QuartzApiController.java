package com.yonyou.einvoice.Controller;

import com.yonyou.einvoice.timing.QuartzManager;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 这里并没有采用restful风格 只是简单封装了一下api
 *
 * @author yvan
 *
 */
@RestController
@RequestMapping("/quartz")
public class QuartzApiController {
  @Autowired
  private QuartzManager quartzManager;

  @RequestMapping("/start")
  public void startQuartzJob() {
    try {
      quartzManager.startJob();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping("/info")
  public String getQuartzJob(String name, String group) {
    String info = null;
    try {
      info = quartzManager.getJobInfo(name, group);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return info;
  }

  @RequestMapping("/modify")
  public boolean modifyQuartzJob(String name, String group, String time) {
    boolean flag = true;
    try {
      flag = quartzManager.modifyJob(name, group, time);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
    return flag;
  }

  @RequestMapping(value = "/pause")
  public void pauseQuartzJob(String name, String group) {
    try {
      quartzManager.pauseJob(name, group);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/pauseAll")
  public void pauseAllQuartzJob() {
    try {
      quartzManager.pauseAllJob();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }
  @RequestMapping(value = "/resume")
  public void resumeQuartzJob(String name, String group) {
    try {
      quartzManager.resumeJob(name, group);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/resumeAll")
  public void resumeAllQuartzJob() {
    try {
      quartzManager.resumeAllJob();
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/delete")
  public void deleteJob(String name, String group) {
    try {
      quartzManager.deleteJob(name, group);
    } catch (SchedulerException e) {
      e.printStackTrace();
    }
  }

}

