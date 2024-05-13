package com.cvgenerator.cvg.dto;

import com.cvgenerator.cvg.entity.Experience;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjectsDto {


    private Integer project_id;

    private String projectName;

    private String roleInProject;

    private String description;

    private Boolean isRunning;

    private String liveURLPath;

    private String techStackUsed;

    private Experience experience;

    public ProjectsDto(Integer projectId) {
        this.project_id = projectId;
    }
}
