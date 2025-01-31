package com.cvgenerator.cvg.service.impl;

import com.cvgenerator.cvg.converter.ReachMeAtConverter;
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
import java.util.Optional;

/**
 * ReachMeAtServiceImpl
 * Created On : 5/13/2024 7:37 AM
 **/
@Slf4j
@Service
public class ReachMeAtServiceImpl implements ReachMeAtService {
    private final ReachMeAtRepo reachMeAtRepo;
    private final ReachMeAtConverter reachMeAtConverter;

    public ReachMeAtServiceImpl(ReachMeAtRepo reachMeAtRepo,
                                ReachMeAtConverter reachMeAtConverter) {
        this.reachMeAtRepo = reachMeAtRepo;
        this.reachMeAtConverter = reachMeAtConverter;
    }

    @Override
    public ReachMeAtDto save(ReachMeAtDto reachMeAtDto) {
        Map<String, String> map = ReachMeAtValidation.validate(reachMeAtDto);
        if (map.isEmpty()) {
            ReachMeAt entity = reachMeAtConverter.toEntity(reachMeAtDto);
            entity = reachMeAtRepo.save(entity);
            log.info("ReachMeAt saved with id: {}", entity.getId());
            return reachMeAtConverter.toDto(entity);
        } else {
            log.info("Invalid reachMeAtDto: {}", reachMeAtDto);
            throw new RuntimeException("Error in request");
        }
    }

    @Override
    public ReachMeAtDto findById(Integer integer) {
        Optional<ReachMeAt> optionalReachMeAt = reachMeAtRepo.findById(integer);
        if (optionalReachMeAt.isPresent()) {
            ReachMeAt reachMeAt = optionalReachMeAt.get();
            return reachMeAtConverter.toDto(reachMeAt);
        } else {
            log.info("Invalid Id: {}", integer);
            return null;
        }
    }

    @Override
    public List<ReachMeAtDto> findAll() {
        List<ReachMeAt> reachMeAtList = reachMeAtRepo.findAll();
        List<ReachMeAtDto> reachMeAtDtoList = new ArrayList<>();
        for (ReachMeAt reachMeAt : reachMeAtList) {
            reachMeAtDtoList.add(reachMeAtConverter.toDto(reachMeAt));
        }
        return reachMeAtDtoList;
    }

    @Override
    public void deleteById(Integer integer) {
        Optional<ReachMeAt> optionalReachMeAt = reachMeAtRepo.findById(integer);
        if (optionalReachMeAt.isPresent()) {
            reachMeAtRepo.deleteById(integer);
            log.info("ReachMeAt deleted with id: {}", integer);
        } else {
            log.error("Invalid Id: {}", integer);
        }
    }
}
