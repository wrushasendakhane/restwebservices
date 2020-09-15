package com.in28minutes.restwebservices.filtering;

import com.fasterxml.jackson.annotation.JsonFilter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@JsonFilter("DynamicBeanFilter")
public class DynamicBean {

  private String field1;
  private String field2;
  private String field3;

}
