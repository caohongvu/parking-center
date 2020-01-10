package com.spm.service;

import java.util.List;

import com.spm.dto.MonthlyCardDto;
import com.spm.dto.ResultObject;

/**
 * Created by Vincent 23/05/2018
 */
public interface MonthlyCardService {

    MonthlyCardDto save(MonthlyCardDto monthlyCardDto);
    
    List<MonthlyCardDto> save(List<MonthlyCardDto> monthlyCardDtos);

    void delete(MonthlyCardDto monthlyCardDto);
    
    List<MonthlyCardDto> findAll();
    
    ResultObject<List<MonthlyCardDto>> findById(Long monthlyCardId);

}
 