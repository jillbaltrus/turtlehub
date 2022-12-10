package com.justforfun.deltazeta.model;

import com.justforfun.deltazeta.model.enums.PointTypeValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PointReport {

  private final PointTypeValue pointTypeValue;
  private int earned;
  private int required;
}
