package com.ritsuki.device.exception.handler;


import lombok.Data;

import java.util.Map;


@Data
class ExceptionResponse {
  private String message;
  private String timeStamp;
  private String trace;

  ExceptionResponse(Map<String, Object> errorAttributes) {
    this.setMessage(errorAttributes.get("message").toString());
    this.setTimeStamp(errorAttributes.get("timestamp").toString());
    this.setTrace(errorAttributes.get("trace").toString());
  }
}
