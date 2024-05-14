package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.BasicInformationConverter;
import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.entity.EducationInformation;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import com.cvgenerator.cvg.service.BasicInformationService;
import com.cvgenerator.cvg.utils.FileStoreUtils;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BasicInformationServiceImpl implements BasicInformationService {

    private final BasicInformationRepo basicInformationRepo;
    private final FileStoreUtils fileStoreUtils;
    private final LocalDateUtils localDateUtils;
    private final BasicInformationConverter basicInformationConverter;

    public BasicInformationServiceImpl(BasicInformationRepo basicInformationRepo,
                                       FileStoreUtils fileStoreUtils,
                                       LocalDateUtils localDateUtils,
                                       BasicInformationConverter basicInformationConverter) {
        this.basicInformationRepo = basicInformationRepo;
        this.fileStoreUtils = fileStoreUtils;
        this.localDateUtils = localDateUtils;
        this.basicInformationConverter = basicInformationConverter;
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
    public BasicInformationDto findById(Integer id) {
        Optional<BasicInformation> optionalBasicInformation = basicInformationRepo.findById(id);
        if (optionalBasicInformation.isPresent()) {
            BasicInformation basicInformation = optionalBasicInformation.get();
            return basicInformationConverter.toDto(basicInformation);
        } else {
            log.info("Invalid Id: {}", id);
            return null;
        }
    }

    @Override
    public List<BasicInformationDto> findAll() {
        List<BasicInformation> basicInformationList = basicInformationRepo.findAll();
        List<BasicInformationDto> basicInformationDtoList = new ArrayList<>();
        for (BasicInformation basicInformation : basicInformationList) {
            basicInformationDtoList.add(basicInformationConverter.toDto(basicInformation));
        }
        return basicInformationDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        basicInformationRepo.deleteById(id);
        log.info("Basic Information deleted with id: {}", id);
    }
}
