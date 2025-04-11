package com.e_commerce.grocery_mart.helper;

import com.e_commerce.grocery_mart.exception.AppException;
import com.e_commerce.grocery_mart.exception.ErrorCode;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Component
public class DateTimeConverter {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public LocalDate formatDate(LocalDate date) {
        String text = date.format(formatter);
        return LocalDate.parse(text, formatter);
    }
}
