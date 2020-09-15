package com.in28minutes.restwebservices.filtering;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.converter.json.MappingJacksonValue;
import com.fasterxml.jackson.databind.ser.impl.*;
import com.fasterxml.jackson.databind.ser.FilterProvider;

@RestController
@RequestMapping("/filtering")
public class FilteringController {

  @GetMapping("/static")
  public StaticBean checkStaticFilterInAction() {
    return new StaticBean("field1-value", "field2-value", "field3-value");
  }

  @GetMapping("/dynamic")
  public MappingJacksonValue checkDynamicFilterInAction() {
    DynamicBean dynamicBean = new DynamicBean("field1-value", "field2-value", "field3-value");
    MappingJacksonValue mapping = new MappingJacksonValue(dynamicBean);

    SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");
    FilterProvider filters = new SimpleFilterProvider().addFilter("DynamicBeanFilter", filter);
    mapping.setFilters(filters);
    return mapping;
  }
}
