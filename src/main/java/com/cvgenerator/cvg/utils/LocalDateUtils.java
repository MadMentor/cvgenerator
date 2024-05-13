package com.cvgenerator.cvg.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
public class LocalDateUtils {
    public LocalDate convertStringToDate(String date) {
        LocalDate localDate = LocalDate.parse(date);
        return localDate;
    }
}
