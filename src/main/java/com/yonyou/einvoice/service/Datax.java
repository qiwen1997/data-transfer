package com.yonyou.einvoice.service;

import java.io.File;

public interface Datax {

//  File[] getFileList();

  /**
   * 查找最大ID，写入文件
   */
  void doMaxIDFile();

  /**
   * 进行增量
   */
  void doIncrementFile();
}
