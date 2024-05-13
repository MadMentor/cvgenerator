package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.BasicInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasicInformationRepo extends JpaRepository<BasicInformation, Integer> {
}
