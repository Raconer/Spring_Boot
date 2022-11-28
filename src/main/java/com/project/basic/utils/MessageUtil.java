package com.project.basic.utils;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtil {

    
    @Resource
    private MessageSource source;

    static MessageSource messageSource;
  
    @PostConstruct
    public void initialize() {
        this.messageSource = source;
    }

    public static String getMessage(String code) {
        try{
            Locale locale = LocaleContextHolder.getLocale();

            return messageSource.getMessage(code, null, locale);
        }catch (Exception e){
           return code + " messageSource can't find";
       }
      }

}
