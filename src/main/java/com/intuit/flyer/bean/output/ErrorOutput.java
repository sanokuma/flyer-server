package com.intuit.flyer.bean.output;

import lombok.Data;

@Data
public class ErrorOutput {

  private String errorCode;
  private String errorMessage;
  private boolean success;

}
