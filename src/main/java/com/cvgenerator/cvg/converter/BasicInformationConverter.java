package com.cvgenerator.cvg.converter;

import com.cvgenerator.cvg.dto.BasicInformationDto;
import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.utils.FileStoreUtils;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import org.springframework.stereotype.Component;

@Component
public class BasicInformationConverter extends AbstractConverter<BasicInformationDto, BasicInformation> {
    private final LocalDateUtils localDateUtils;


    public BasicInformationConverter(LocalDateUtils localDateUtils) {
        this.localDateUtils = localDateUtils;

    }

    @Override
    public BasicInformationDto toDto(BasicInformation basicInformation) {
        return BasicInformationDto.builder()
                .id(basicInformation.getId())
                .firstName(basicInformation.getFirstName())
                .middleName(basicInformation.getMiddleName())
                .lastName(basicInformation.getLastName())
                .dateOfBirth(String.valueOf(basicInformation.getDateOfBirth()))
                .gender(basicInformation.getGender())
                .religion(basicInformation.getReligion())
                .nationality(basicInformation.getNationality())
                .currentAddress(basicInformation.getCurrentAddress())
                .background(basicInformation.getBackground())
                .photoPath(basicInformation.getPhotoPath())
                .build();
    }

    @Override
    public BasicInformation toEntity(BasicInformationDto basicInformationDto) {

        BasicInformation entity = new BasicInformation();

        entity.setId(basicInformationDto.getId());
        entity.setFirstName(basicInformationDto.getFirstName());
        entity.setLastName(basicInformationDto.getLastName());
        entity.setMiddleName(basicInformationDto.getMiddleName());
        entity.setDateOfBirth(localDateUtils.convertStringToDate(basicInformationDto.getDateOfBirth()));
        entity.setReligion(basicInformationDto.getReligion());
        entity.setNationality(basicInformationDto.getNationality());
        entity.setCurrentAddress(basicInformationDto.getCurrentAddress());
        entity.setBackground(basicInformationDto.getBackground());

       // entity.setPhotoPath(basicInformationDto.getPhotoPath());
        return entity;
    }
}
