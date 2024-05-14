package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.SkillDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SkillValidation {
    public Map<String, String> isSkillValid(SkillDto skillDto) {
        Map<String, String> errorMap = new HashMap<>();
        if (skillDto.getSkillName() == null) {
            errorMap.put("skillName", "Skill name cannot be null");
        }
        return errorMap;
    }

}
