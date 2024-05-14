package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.ProjectsConverter;
import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.entity.Experience;
import com.cvgenerator.cvg.entity.Projects;
import com.cvgenerator.cvg.repo.ExperienceRepo;
import com.cvgenerator.cvg.repo.ProjectsRepo;
import com.cvgenerator.cvg.service.ProjectsService;
import com.cvgenerator.cvg.validation.ProjectsValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ProjectsServiceImpl implements ProjectsService {


    private final ProjectsRepo projectsRepo;
    private final ExperienceRepo experienceRepo;

    private final ProjectsConverter projectsConverter;


    public ProjectsServiceImpl(ProjectsRepo projectsRepo, ExperienceRepo experienceRepo, ProjectsConverter projectsConverter) {
        this.projectsRepo = projectsRepo;
        this.experienceRepo = experienceRepo;
        this.projectsConverter = projectsConverter;
    }

    @Override
    public ProjectsDto save(ProjectsDto projectsDto) {

        // for validation of a data provide by the user
        Map<String, String> errorMap = ProjectsValidation.isProjectsValid(projectsDto);

        if (errorMap.isEmpty()) {
            Projects entity = projectsConverter.toEntity(projectsDto);
            entity = projectsRepo.save(entity);

            log.info("Projects saved with id : {}", entity.getProjectId());

            return projectsConverter.toDto(entity);

        } else {
            log.error("Invalid projects information provided !!!!: {}", projectsDto);
            throw new RuntimeException("Error in request");
        }
    }

    @Override
    public ProjectsDto findById(Integer id) {

        Optional<Projects> projectsOptional = projectsRepo.findById(id);
        if (projectsOptional.isPresent()) {
            Projects projects = projectsOptional.get();
            return projectsConverter.toDto(projects);

        }else {
            log.error("Invalid Id: {}", id);
            return null;
        }
    }

    @Override
    public List<ProjectsDto> findAll() {
        List<Projects> projectsList = this.projectsRepo.findAll();
        List<ProjectsDto> projectsDtoList = new ArrayList<>();

        for (Projects projects : projectsList) {
            projectsDtoList.add(projectsConverter.toDto(projects));
        }
        return projectsDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        Optional<Projects> optionalProjects = projectsRepo.findById(id);
        if(optionalProjects.isPresent()){
            projectsRepo.deleteById(id);
            log.info("Successfully deleted with id :{}",id);
        }else {
            log.error("Invalid id: {}",id);
        }
    }
}
