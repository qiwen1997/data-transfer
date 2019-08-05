package com.yonyou.einvoice.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JsonUtil {

  @Value("${maxTimeFilePath}")
  private String maxTimeFilePath;

  @Value("${incrementJsonPath}")
  private String incrementJsonPath;

  /**
   * 找到记录最大时间的文件
   * @return
   */
  public File getMaxTimeFile(){
    File file = new File(maxTimeFilePath);
    File[] files=file.listFiles();
    for(int i=0;i<files.length;i++){
      if(files[i].getName().startsWith("max_ID")){
        return files[i];
      }
    }
    return null;
  }

  /**
   * 获得文件中的最大ID
   * @return
   */
  public String getMaxID(){
    File file=getMaxTimeFile();
    String id=null;
    if(file==null){
      return null;
    }else{
      Path path=file.toPath();
      try {
        BufferedReader bufferedReader=Files.newBufferedReader(path);
        id=bufferedReader.readLine();
        bufferedReader.close();
      } catch (IOException e) {
        log.error(e.getMessage());
      }
    }
    return id;
  }

  /**
   * 把增量文件中的where改为>ID
   * @param ID
   */
  public void setMaxID(String ID){
    Path path= Paths.get(incrementJsonPath);
    StringBuffer stringBuffer=new StringBuffer();
    try {
      BufferedReader bufferedReader=Files.newBufferedReader(path);
      String line=null;
      while((line=bufferedReader.readLine())!=null){
        stringBuffer.append(line);
      }
      bufferedReader.close();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    JSONObject jsonObject=JSONObject.parseObject(stringBuffer.toString());
    JSONObject job=jsonObject.getJSONObject("job");
    JSONArray content=job.getJSONArray("content");
    JSONObject reader=content.getJSONObject(0).getJSONObject("reader");
    JSONObject parameter=reader.getJSONObject("parameter");
    parameter.put("where","ID>"+ID+"");

    reader.put("parameter",parameter);
    content.getJSONObject(0).put("reader",reader);
    job.put("content",content);
    jsonObject.put("job",job);
    //System.out.println(jsonObject.toString());
    try {
      Files.write(path,jsonObject.toJSONString().getBytes());
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * 把增量文件中的where改为1=1
   * 使其变为全量
   */
  public void setFull(){
    Path path= Paths.get(incrementJsonPath);
    StringBuffer stringBuffer=new StringBuffer();
    try {
      BufferedReader bufferedReader=Files.newBufferedReader(path);
      String line=null;
      while((line=bufferedReader.readLine())!=null){
        stringBuffer.append(line);
      }
      bufferedReader.close();
    } catch (IOException e) {
      log.error(e.getMessage());
    }
    JSONObject jsonObject=JSONObject.parseObject(stringBuffer.toString());
    JSONObject job=jsonObject.getJSONObject("job");
    JSONArray content=job.getJSONArray("content");
    JSONObject reader=content.getJSONObject(0).getJSONObject("reader");
    JSONObject parameter=reader.getJSONObject("parameter");
    parameter.put("where","1=1");

    reader.put("parameter",parameter);
    content.getJSONObject(0).put("reader",reader);
    job.put("content",content);
    jsonObject.put("job",job);
    //System.out.println(jsonObject.toString());
    try {
      Files.write(path,jsonObject.toJSONString().getBytes());
    } catch (IOException e) {
      log.error(e.getMessage());
    }
  }
}
