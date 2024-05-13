package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.dto.ProjectsDto;
import com.cvgenerator.cvg.entity.Projects;
import com.cvgenerator.cvg.repo.ProjectsRepo;
import com.cvgenerator.cvg.service.ProjectsService;
import com.cvgenerator.cvg.validation.ProjectsValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProjectsServiceImpl implements ProjectsService {


    private final ProjectsRepo projectsRepo;


    public ProjectsServiceImpl(ProjectsRepo projectsRepo) {
        this.projectsRepo = projectsRepo;
    }

    @Override
    public ProjectsDto save(ProjectsDto projectsDto){


        // for validation of a data provide by the user
        Map<String,String> errorMap = ProjectsValidation.isProjectsValid(projectsDto);

        if(errorMap.isEmpty()){
        Projects entity = new Projects();
        entity.setProject_id(projectsDto.getProject_id());
        entity.setProjectName(projectsDto.getProjectName());
        entity.setDescription(projectsDto.getDescription());
        entity.setRoleInProject(projectsDto.getRoleInProject());
        entity.setLiveURLPath(projectsDto.getLiveURLPath());
        entity.setTechStackUsed(projectsDto.getTechStackUsed());
        entity.setExperience(projectsDto.getExperience());
        entity.setIsRunning(projectsDto.getIsRunning());
        entity = projectsRepo.save(entity);

        log.info("Projects saved with id : {}",entity.getProject_id());

        return  new ProjectsDto(entity.getProject_id());

        }else {
            log.info("Invalid projects information provided !!!!: {}",projectsDto);
        }
        return null ;
    }

    @Override
    public ProjectsDto findById(Integer id) {

        Projects projectsEntity = projectsRepo.findById(id).get();
        ProjectsDto projectsDto = new ProjectsDto(projectsEntity.getProject_id(),
                projectsEntity.getProjectName(),
                projectsEntity.getRoleInProject(),
                projectsEntity.getDescription(),
                projectsEntity.getIsRunning(),
                projectsEntity.getLiveURLPath(),
                projectsEntity.getTechStackUsed(),
                projectsEntity.getExperience());

        return projectsDto;
    }

    @Override
    public List<ProjectsDto> findAll() {
        List<Projects> projectsList = this.projectsRepo.findAll();
        List<ProjectsDto> projectsDtoList = new ArrayList<>();

        for (Projects projects : projectsList){
            projectsDtoList.add(new ProjectsDto(projects.getProject_id(),
                    projects.getProjectName(),
                    projects.getRoleInProject(),
                    projects.getDescription(),
                    projects.getIsRunning(),
                    projects.getLiveURLPath(),
                    projects.getTechStackUsed(),
                    projects.getExperience()));
        }

        return projectsDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        this.projectsRepo.deleteById(id);
    }
}
