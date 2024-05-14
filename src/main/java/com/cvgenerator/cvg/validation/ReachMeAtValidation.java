package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.ReachMeAtDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * ReachMeAtValidation
 * Created On : 5/13/2024 7:58 AM
 **/
@Slf4j
@Component
public class ReachMeAtValidation {

    public static Map<String, String> validate(ReachMeAtDto reachMeAtDto) {
        Map<String, String> map = new HashMap<>();
        if (reachMeAtDto.getDetails().isEmpty()) {
            map.put("Contact Details", "Contact details are empty");
        }
        return map;
    }
}
