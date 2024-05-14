package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.dto.EducationInformationDto;
import com.cvgenerator.cvg.entity.EducationInformation;
import com.cvgenerator.cvg.repo.EducationInformationRepo;
import com.cvgenerator.cvg.service.EducationInformationService;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class EducationInformationServiceImpl implements EducationInformationService {

    private final EducationInformationRepo educationInformationRepo;
    private final LocalDateUtils localDateUtils;

    public EducationInformationServiceImpl(EducationInformationRepo educationInformationRepo,
                                           LocalDateUtils localDateUtils) {
        this.educationInformationRepo = educationInformationRepo;
        this.localDateUtils = localDateUtils;
    }

    @Override
    public EducationInformationDto save(EducationInformationDto educationInformationDto) {
        EducationInformation entity = new EducationInformation();

        entity.setId(educationInformationDto.getId());
        entity.setInstituteName(educationInformationDto.getInstituteName());
        entity.setInstituteAddress(educationInformationDto.getInstituteAddress());
        entity.setLevel(educationInformationDto.getLevel());
        entity.setLevelDetail(educationInformationDto.getLevelDetail());
        entity.setDivisionOrGrade(educationInformationDto.getDivisionOrGrade());

        // entity.setFromYearDate(localDateUtils.convertStringToDate(educationInformationDto.getFromYearDate()));
        //if isRunning  then toYear date is not required.....
        // entity.setToYearDate(localDateUtils.convertStringToDate(educationInformationDto.getToYearDate()));
        entity.setIsRunning(educationInformationDto.getIsRunning());

        // validation for isRunning .....
        // if, is running is true then fromYearDate required and
        // if , false then fromYearDate  and toYearDate both are required


        if (entity.getIsRunning()) {
            entity.setFromYearDate(localDateUtils.convertStringToDate(educationInformationDto.getFromYearDate()));
        } else {
            entity.setFromYearDate(localDateUtils.convertStringToDate(educationInformationDto.getFromYearDate()));
            entity.setToYearDate(localDateUtils.convertStringToDate(educationInformationDto.getToYearDate()));

        }
        entity.setEducationType(educationInformationDto.getEducationType());
        entity = educationInformationRepo.save(entity);

        return new EducationInformationDto(entity.getId());
    }

    @Override
    public EducationInformationDto findById(Integer integer) {
        return null;
    }

    @Override
    public List<EducationInformationDto> findAll() {
        return null;
    }

    @Override
    public void deleteById(Integer integer) {

    }
}
