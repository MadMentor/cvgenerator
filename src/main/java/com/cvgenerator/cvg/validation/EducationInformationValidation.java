package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.EducationInformationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class EducationInformationValidation {

    public  static Map<String, String> validate(EducationInformationDto educationInformationDto) {
        Map<String, String> errorMap = new HashMap<>();
        if (educationInformationDto.getInstituteName().isEmpty()){
            errorMap.put("instituteName", "Institute Name cannot be null");
        }
        return errorMap;
    }

}
