package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.dto.ExperienceDto;
import com.cvgenerator.cvg.entity.Experience;
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

@Slf4j
@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepo experienceRepo;
    private final LocalDateUtils localDateUtils;


    public ExperienceServiceImpl(ExperienceRepo experienceRepo, LocalDateUtils localDateUtils) {
        this.experienceRepo = experienceRepo;
        this.localDateUtils = localDateUtils;
    }

    @Override
    public ExperienceDto save(ExperienceDto experienceDto) {

        Map<String, String> errorMap = ExperienceValidation.isExperienceValid(experienceDto);

        if (errorMap.isEmpty()) {
            Experience entity = new Experience();
            entity.setExperience_id(experienceDto.getExperience_id());
            entity.setAddress(experienceDto.getAddress());
            entity.setContact(experienceDto.getContact());
            entity.setPosition(experienceDto.getPosition());
            entity.setCompanyName(experienceDto.getCompanyName());
            entity.setCompanyWebsite(experienceDto.getCompanyWebsite());
            entity.setJobRole(experienceDto.getJobRole());
            entity.setIsCurrent(experienceDto.getIsCurrent());
            if (experienceDto.getIsCurrent()) {
                entity.setStartDate(localDateUtils.convertStringToDate(experienceDto.getStartDate()));
            } else {
                entity.setStartDate(localDateUtils.convertStringToDate(experienceDto.getStartDate()));
                entity.setEndDate(localDateUtils.convertStringToDate(experienceDto.getEndDate()));
            }
            entity.setBasicInformation(experienceDto.getBasicInformation());
            entity = experienceRepo.save(entity);

            log.info("Experience saved with id : {}", entity.getExperience_id());

            return new ExperienceDto(entity.getExperience_id());

        } else {
            log.info("Invalid experience information provided  : {}", experienceDto);

        }
        return null;

    }

    @Override
    public ExperienceDto findById(Integer id) {

        Experience entity = experienceRepo.findById(id).get();
        ExperienceDto experienceDto = new ExperienceDto(entity.getCompanyName(),
                entity.getCompanyWebsite(),
                entity.getAddress(),
                entity.getContact(),
                entity.getJobRole(),
                entity.getPosition(),
                entity.getBasicInformation());
        return experienceDto;
    }

    @Override
    public List<ExperienceDto> findAll() {
        List<Experience> experienceList = this.experienceRepo.findAll();
        List<ExperienceDto> experienceDtoList = new ArrayList<>();

        for (Experience experience : experienceList) {
            experienceDtoList.add(new ExperienceDto(experience.getCompanyName(),
                    experience.getCompanyWebsite(),
                    experience.getAddress(),
                    experience.getContact(),
                    experience.getJobRole(),
                    experience.getPosition(),
                    experience.getBasicInformation()));
        }

        return experienceDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        this.experienceRepo.deleteById(id);
    }

}
