package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.ExperienceConverter;
import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.entity.Experience;
import com.cvgenerator.cvg.entity.Projects;
import com.cvgenerator.cvg.repo.ExperienceRepo;
import com.cvgenerator.cvg.service.ExperienceService;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import com.cvgenerator.cvg.validation.ExperienceValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.kerberos.KerberosTicket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepo experienceRepo;
    private final LocalDateUtils localDateUtils;
    private final ExperienceConverter experienceConverter;


    public ExperienceServiceImpl(ExperienceRepo experienceRepo, LocalDateUtils localDateUtils, ExperienceConverter experienceConverter) {
        this.experienceRepo = experienceRepo;
        this.localDateUtils = localDateUtils;
        this.experienceConverter = experienceConverter;
    }

    @Override
    public ExperienceDto save(ExperienceDto experienceDto) {

        /// for validation use only
        Map<String, String> errorMap = ExperienceValidation.isExperienceValid(experienceDto);

        //if errorMap is empty then everything is fine and valid
        if (errorMap.isEmpty()) {
            Experience entity = experienceConverter.toEntity(experienceDto);
            entity = experienceRepo.save(entity);

            log.info("Experience saved with id : {}", entity.getExperienceId());

            return experienceConverter.toDto(entity);

        } else {
            log.error("Invalid experience information provided  : {}", experienceDto);
            throw new RuntimeException("Error in request");

        }
    }

    @Override
    public ExperienceDto findById(Integer id) {

        // use Optional method to Rid of null Exception
        Optional<Experience> optionalExperience = experienceRepo.findById(id);

        if (optionalExperience.isPresent()) {
            Experience experience = optionalExperience.get();
            return experienceConverter.toDto(experience);
        } else {
            log.error("Invalid Id: {}", id);
            return null;
        }
    }

    @Override
    public List<ExperienceDto> findAll() {
        List<Experience> experienceList = this.experienceRepo.findAll();
        List<ExperienceDto> experienceDtoList = new ArrayList<>();

        //iterate every object of Experience
        for (Experience experience : experienceList) {
            experienceDtoList.add(experienceConverter.toDto(experience));
        }
        return experienceDtoList;
    }

    @Override
    public void deleteById(Integer id) {

        Optional<Experience> optionalExperience = experienceRepo.findById(id);
        if (optionalExperience.isPresent()) {
            experienceRepo.deleteById(id);
            log.info("Successfully deleted with id :{}", id);
        } else {
            log.error("Invalid id: {}", id);
        }
    }
}
