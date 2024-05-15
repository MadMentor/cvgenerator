package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.dto.ProjectsDto;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Validated
public class ExperienceValidation {


    public static Map<String, String> isExperienceValid(ExperienceDto experienceDto) {
        Map<String, String> errorMap = new HashMap<>();
        if (experienceDto.getExperience_id() == null) {
            errorMap.put("id is null ", "Id cannot be null");
        } else if (experienceDto.getCompanyName().isEmpty()) {
            errorMap.put("company name is empty ","company name is mandatory");
        } else if (experienceDto.getCompanyWebsite().isEmpty()) {
            errorMap.put("company website  is empty ","specify company website is mandatory");
        } else if (experienceDto.getAddress().isEmpty()) {
            errorMap.put("address is empty "," company address is not provide  ");
        }else if (experienceDto.getPosition().isEmpty()) {
            errorMap.put("position  is empty "," position is not provide  ");
        }else if (experienceDto.getJobRole().isEmpty()) {
            errorMap.put("job role  is empty "," job role  is not provide  ");
        }else if (experienceDto.getStartDate().isEmpty()) {
            errorMap.put("start date is missing "," start date is not provide  ");
        }else if (experienceDto.getEndDate().isEmpty()) {
            errorMap.put("Ending date is missing  "," end date  is not provide  ");
        }

        return errorMap;
    }
}
