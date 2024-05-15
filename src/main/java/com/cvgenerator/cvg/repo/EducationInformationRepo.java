package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.EducationInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationInformationRepo extends JpaRepository<EducationInformation,Integer> {

}
