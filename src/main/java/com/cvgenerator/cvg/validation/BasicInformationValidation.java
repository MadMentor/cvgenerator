package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class BasicInformationValidation {

    public Map<String, String> isBasicInformationValid(BasicInformationDto basicInformationDto) {
        Map<String, String> errorMap = new HashMap<>();
        if (basicInformationDto.getFirstName() == null) {
            errorMap.put("firstName", "First name cannot be null");
        }

        return errorMap;
    }
}
