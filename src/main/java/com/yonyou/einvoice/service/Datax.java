package com.yonyou.einvoice.service;

import java.io.File;

public interface Datax {

//  File[] getFileList();

  /**
   * 进行全量
   */
  void doFullTesk();

  /**
   * 查找最大ID，写入文件
   */
  void doMaxIDFile();

  /**
   * 进行增量
   */
  void doIncrementTest();
}
