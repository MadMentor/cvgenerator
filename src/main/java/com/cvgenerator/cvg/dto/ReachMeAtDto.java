package com.cvgenerator.cvg.dto;

import com.cvgenerator.cvg.entity.BasicInformation;
import com.cvgenerator.cvg.enums.ContactType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ReachMeAtDto
 * Created On : 5/13/2024 7:35 AM
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReachMeAtDto {
    private Integer id;

    private ContactType contactType;

    private String details;

    private BasicInformation basicInformationId;
}
