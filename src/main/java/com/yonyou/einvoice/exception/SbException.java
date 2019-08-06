package com.yonyou.einvoice.exception;

/**
 * 异常类
 *
 * @author qiwen
 */
public class SbException extends RuntimeException {


  private static final long serialVersionUID = 1L;

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  private Integer code;

  public SbException(Integer code, String message) {
    super(message);
    this.code = code;
  }

}
