package com.yonyou.einvoice.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class DataxImpl implements Datax{

  private Logger logger= LoggerFactory.getLogger(DataxImpl.class);

  @Value("${dataxPath}")
  private String dataxpath;

  @Value("${jsonPath}")
  private String jsonPath;

  @Value("${pythonPath}")
  private String pythonPath;

  @Override
  public File[] getFileList(){
    File file=new File(jsonPath);
    File[] files=file.listFiles((File f)->f.getName().endsWith(".json"));
    return files;
  }

  @Override
  public void doTesk(){
    File[] files=getFileList();
    for(File f:files){
      String cmd=pythonPath+" "+dataxpath+" "+f.getAbsolutePath();
      try{
        Process process=Runtime.getRuntime().exec(cmd);
        BufferedReader in=new BufferedReader(new InputStreamReader(process.getInputStream(),"UTF-8"));
        String line=null;
        while((line=in.readLine())!=null){
          logger.info(line);
        }
      } catch (IOException e) {
        logger.error(e.getMessage());
      }
    }
  }

}
