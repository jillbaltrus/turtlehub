package com.justforfun.deltazeta.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseCode {
  OK(200), BAD_REQUEST(400), UNAUTHORIZED(403), NOT_FOUND(404);

  private int code;
}
