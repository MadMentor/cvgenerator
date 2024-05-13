package com.cvgenerator.cvg.dto;

import com.cvgenerator.cvg.entity.BasicInformation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExperienceDto {


    private Integer experience_id;

    private String companyName;

    private String companyWebsite;

    private String address;

    private String contact;

    private String startDate;

    private String endDate;

    private String position;

    private String jobRole;

    private Boolean isCurrent;

    private BasicInformation basicInformation;


    public ExperienceDto(Integer experienceId) {
        this.experience_id = experienceId;
    }

    public ExperienceDto(String companyName, String companyWebsite, String address, String contact, String jobRole, String position, BasicInformation basicInformation) {
        this.companyName = companyName;
        this.companyWebsite = companyWebsite;
        this.address = address;
        this.contact = contact;
        this.jobRole = jobRole;
        this.position = position;
        this.basicInformation = basicInformation;
    }
}
