package com.yonyou.einvoice.execute;

import com.yonyou.einvoice.entity.optionEnum;
import com.yonyou.einvoice.service.Datax;
import com.yonyou.einvoice.util.JsonUtil;
import java.util.Scanner;
import lombok.extern.slf4j.Slf4j;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Interface implements CommandLineRunner {

  @Autowired
  private Interface anInterface;

  @Autowired
  private JsonUtil jsonUtil;

  @Autowired
  private Datax datax;

  @Override
  public void run(String... args) throws Exception {
    Scanner scanner=new Scanner(System.in);
    System.out.println("输入数字1，进行一次全量");
    System.out.println("输入数字2，进行一次增量");
    System.out.println("输入数字3，进行定时增量");
    System.out.println("输入数字4，退出");
    Integer option=scanner.nextInt();
    if(optionEnum.ONE_FUll.getKey().equals(option)){
      log.info("进行一次全量");
      datax.doFullTesk();
      datax.doMaxIDFile();
      anInterface.run();
    }else if (optionEnum.ONE_INCREMENT.getKey().equals(option)){
      log.info("进行一次增量");
      if(jsonUtil.getMaxTimeFile()==null){
        log.info("目录中无最大ID记录文件，请先进行一次全量，或部署以max_ID为开头的文件，内容为最大ID");
      }else{
        String ID=jsonUtil.getMaxID();
        jsonUtil.setMaxID(ID);
        datax.doIncrementTest();
        datax.doMaxIDFile();
      }
      anInterface.run();
    }else if(optionEnum.TIMING_INCREMENT.getKey().equals(option)){
      System.out.println("进行定时增量");


    }else if(optionEnum.EXIT.getKey().equals(option)){
      log.info("退出");
      return;
    }else{
      log.info("非法输入,请重新输入");
      anInterface.run();
    }
  }
}
