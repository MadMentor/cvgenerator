package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.dto.ReachMeAtDto;
import com.cvgenerator.cvg.entity.ReachMeAt;
import com.cvgenerator.cvg.repo.BasicInformationRepo;
import com.cvgenerator.cvg.repo.ReachMeAtRepo;
import com.cvgenerator.cvg.service.ReachMeAtService;
import com.cvgenerator.cvg.validation.ReachMeAtValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ReachMeAtServiceImpl
 * Created On : 5/13/2024 7:37 AM
 **/
@Slf4j
@Service
public class ReachMeAtServiceImpl implements ReachMeAtService {
    private final ReachMeAtRepo reachMeAtRepo;
    private final BasicInformationRepo basicInformationRepo;

    public ReachMeAtServiceImpl(ReachMeAtRepo reachMeAtRepo, BasicInformationRepo basicInformationRepo) {
        this.reachMeAtRepo = reachMeAtRepo;
        this.basicInformationRepo = basicInformationRepo;
    }

    @Override
    public ReachMeAtDto save(ReachMeAtDto reachMeAtDto) {
        Map<String, String> map = ReachMeAtValidation.validate(reachMeAtDto);
        if (map.isEmpty()) {
            ReachMeAt entity = new ReachMeAt();
            entity.setId(reachMeAtDto.getId());
            entity.setContactType(reachMeAtDto.getContactType());
            entity.setDetails(reachMeAtDto.getDetails());
            entity.setBasicInformationId(basicInformationRepo.findById(reachMeAtDto.getBasicInformationId()).get());
            reachMeAtRepo.save(entity);
            log.info("ReachMeAt saved with id: {}", entity.getId());
        } else {
            log.info("Invalid reachMeAtDto: {}", reachMeAtDto);
        }

        return null;
    }

    @Override
    public ReachMeAtDto findById(Integer integer) {
        ReachMeAt entity = reachMeAtRepo.findById(integer).get();
        ReachMeAtDto dto = new ReachMeAtDto(entity.getId(), entity.getContactType(), entity.getDetails(),
                entity.getBasicInformationId().getId());
        return dto;
    }

    @Override
    public List<ReachMeAtDto> findAll() {
        List<ReachMeAt> reachMeAtList = reachMeAtRepo.findAll();
        List<ReachMeAtDto> reachMeAtDtoList = new ArrayList<>();
        for (ReachMeAt reachMeAt : reachMeAtList) {
            reachMeAtDtoList.add(new ReachMeAtDto(reachMeAt.getId(), reachMeAt.getContactType(),
                    reachMeAt.getDetails(), reachMeAt.getBasicInformationId().getId()));
        }
        return reachMeAtDtoList;
    }

    @Override
    public void deleteById(Integer integer) {
        ReachMeAt reachMeAt = reachMeAtRepo.findById(integer).get();
        reachMeAtRepo.delete(reachMeAt);
    }


}
