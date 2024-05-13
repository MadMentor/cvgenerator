package com.cvgenerator.cvg.validation;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.entity.Projects;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.Map;

@Validated
public class ProjectsValidation {


    public static Map<String, String> isProjectsValid(ProjectsDto projectsDto) {
        Map<String, String> errorMap = new HashMap<>();
        if (projectsDto.getProject_id() == null) {
            errorMap.put("id is null ", "Id cannot be null");
        } else if (projectsDto.getProjectName().isEmpty()) {
            errorMap.put("project is empty ","project name is mandatory");
        } else if (projectsDto.getRoleInProject().isEmpty()) {
            errorMap.put("role in projects is empty ","specify role in project is mandatory");
        } else if (projectsDto.getTechStackUsed().isEmpty()) {
            errorMap.put("teckstack used in projects is empty ","pls provide the teckstack used in project ");
        }

        return errorMap;
    }
}
