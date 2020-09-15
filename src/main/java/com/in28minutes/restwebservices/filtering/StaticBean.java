package com.in28minutes.restwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StaticBean {

  @JsonIgnore
  private String field1;
  private String field2;
  private String field3;

}
