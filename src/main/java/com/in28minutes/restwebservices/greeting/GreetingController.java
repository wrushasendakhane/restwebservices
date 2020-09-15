package com.in28minutes.restwebservices.greeting;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/greeting")
public class GreetingController {

  @Autowired
  private MessageSource messageSource;

  // @GetMapping("")
  // public String greeting(@RequestHeader(value = "Accept-Language", required =
  // false) Locale locale) {
  // return messageSource.getMessage("good.morning", null, locale);
  // }

  @GetMapping("")
  public String greeting() {
    return messageSource.getMessage("good.morning", null, LocaleContextHolder.getLocale());
  }

}
