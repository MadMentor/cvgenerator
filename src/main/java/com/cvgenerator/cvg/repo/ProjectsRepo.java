package com.cvgenerator.cvg.repo;

import com.cvgenerator.cvg.entity.Projects;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectsRepo extends JpaRepository<Projects,Integer> {
}
