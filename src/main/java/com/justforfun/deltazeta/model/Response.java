package com.justforfun.deltazeta.model;

import com.justforfun.deltazeta.model.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Response {

  private ResponseCode responseCode;
  private int code;
  private String message;

  public Response(ResponseCode responseCode, String message) {
    this.responseCode = responseCode;
    this.code = responseCode.getCode();
    this.message = message;
  }

}
