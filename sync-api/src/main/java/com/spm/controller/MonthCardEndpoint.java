package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dtos.MonthlyCardDto;
import com.spm.service.MonthlyCardService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/monthlycards")
@Api(value = "Monthly cards Endpoint", description = "The URL to handle monthly cards endpoint")
public class MonthCardEndpoint{

	@Autowired
	private MonthlyCardService  monthCardsService;
	
	
	
	@RequestMapping(value = "/add", method = {RequestMethod.POST})
	@ApiOperation("Add new monthly card")
	public void addNewCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		monthCardsService.save(monthlyCardDto);
	}
	
	@RequestMapping(value = "/batchinsert", method = {RequestMethod.POST})
	@ApiOperation("Add new monthly card")
	public void batchinsert(@RequestBody List<MonthlyCardDto> monthlyCardDtos) {
		monthlyCardDtos.forEach(card -> {
			MonthlyCardDto existingCard = monthCardsService.findByCardCode(card.getCardCode());
			if(existingCard != null) {
				card.setId(existingCard.getId());
			}
		});
		monthCardsService.save(monthlyCardDtos);
	}
	
	@RequestMapping(value = "/batchsyncs", method = { RequestMethod.GET })
	@ApiOperation("sync all card")
	public @ResponseBody List<MonthlyCardDto> batchsyncs(@RequestParam(name="projectId") Long projectId) {
		return  monthCardsService.syncAllByProjectId(projectId);
	}
	
	@RequestMapping(value = "/update", method = {RequestMethod.PUT})
	@ApiOperation("Update existing monthly card")
	public void updateExistingCard(@RequestBody MonthlyCardDto monthlyCardDto) {
		monthCardsService.save(monthlyCardDto);
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Orders batch syncs")
	public @ResponseBody List<MonthlyCardDto> getAll() {
		return monthCardsService.findAll();
	}
	
	
	
}
