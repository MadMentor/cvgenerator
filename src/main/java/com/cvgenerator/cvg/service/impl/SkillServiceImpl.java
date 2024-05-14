package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.dto.SkillDto;
import com.cvgenerator.cvg.entity.Skill;
import com.cvgenerator.cvg.repo.SkillRepo;
import com.cvgenerator.cvg.service.SkillService;
import com.cvgenerator.cvg.utils.FileStoreUtils;
import com.cvgenerator.cvg.utils.LocalDateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SkillServiceImpl implements SkillService {
    private final SkillRepo skillRepo;

    public SkillServiceImpl(SkillRepo skillRepo) {
        this.skillRepo = skillRepo;
    }

    @Override
    public SkillDto save(SkillDto skillDto) {
        Skill entity = new Skill();
        entity.setSkillId(skillDto.getSkillId());
        entity.setSkillName(skillDto.getSkillName());
        entity.setSkillType(skillDto.getSkillType());
        entity.setSkillDescription(skillDto.getSkillDescription());
        entity.setBasicInfoId(skillDto.getBasicInfoId());
        try {
            entity = skillRepo.save(entity);
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
        return new SkillDto(entity.getSkillId());
    }

    @Override
    public SkillDto findById(Integer id) {
        Optional<Skill> entity = skillRepo.findById(id);
        if (entity.isPresent()) {
            return new SkillDto(entity.get().getSkillId(),
                    entity.get().getSkillType(),
                    entity.get().getSkillName(),
                    entity.get().getSkillDescription(),
                    entity.get().getBasicInfoId());
        }
        return null;
    }

    @Override
    public List<SkillDto> findAll() {
        List<Skill> skillList = skillRepo.findAll();
        List<SkillDto> skillDtoList = new ArrayList<>();
        for (Skill skill : skillList) {
            skillDtoList.add(new SkillDto(skill.getSkillId(),
                    skill.getSkillType(),
                    skill.getSkillName(),
                    skill.getSkillDescription(),
                    skill.getBasicInfoId()));
        }
        return skillDtoList;
    }

    @Override
    public void deleteById(Integer id) {
        skillRepo.deleteById(id);
    }
}
