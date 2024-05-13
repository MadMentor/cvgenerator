package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.utils.FileStoreUtils;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BasicInformationServiceImpl implements BasicInformationService {

    private final BasicInformationRepo basicInformationRepo;
    private final FileStoreUtils fileStoreUtils;

    private final LocalDateUtils localDateUtils;

    public BasicInformationServiceImpl(BasicInformationRepo basicInformationRepo,
                                       FileStoreUtils fileStoreUtils,
                                       LocalDateUtils localDateUtils) {
        this.basicInformationRepo = basicInformationRepo;
        this.fileStoreUtils = fileStoreUtils;
        this.localDateUtils = localDateUtils;
    }

    @Override
    public BasicInformationDto save(BasicInformationDto basicInformationDto) {
        // data validation
        // TODO data validation
        // file validation

        // file store --> file path
        String photoFilePath = fileStoreUtils.uploadFile(basicInformationDto.getPhotoFile());
        // DTO convertToEntity
        BasicInformation entity
                = new BasicInformation();
        entity.setId(basicInformationDto.getId());
        entity.setGender(basicInformationDto.getGender());
        entity.setFirstName(basicInformationDto.getFirstName());
        entity.setMiddleName(basicInformationDto.getMiddleName());
        entity.setLastName(basicInformationDto.getLastName());
        entity.setDateOfBirth(localDateUtils.convertStringToDate(basicInformationDto.getDateOfBirth()));
        entity.setReligion(basicInformationDto.getReligion());
        entity.setNationality(basicInformationDto.getNationality());
        entity.setCurrentAddress(basicInformationDto.getCurrentAddress());
        entity.setBackground(basicInformationDto.getBackground());
        entity.setPhotoPath(photoFilePath);

        entity = basicInformationRepo.save(entity);

        return new BasicInformationDto(entity.getId());
    }

    @Override
    public BasicInformationDto findById(Integer integer) {
        //code
        return null;
    }

    @Override
    public List<BasicInformationDto> findAll() {
        // code
        return null;
    }

    @Override
    public void deleteById(Integer integer) {
        // cod e
    }
}
