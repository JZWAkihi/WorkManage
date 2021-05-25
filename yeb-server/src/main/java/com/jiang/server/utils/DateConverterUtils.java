package com.jiang.server.utils;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/***
 * 日期转换工具类
 */
@Component
public class DateConverterUtils implements Converter<String, LocalDate> {
    @Override
    public LocalDate convert(String source) {

        try {
            return LocalDate.parse(source, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
