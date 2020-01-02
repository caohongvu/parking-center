package com.spm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spm.dto.OrderDto;
import com.spm.dto.ResultObject;
import com.spm.search.form.OrderSearchForm;
import com.spm.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Created by Vincent on 02/10/2018
 */
@RestController
@RequestMapping("/orders")
@Api(value = "parking Endpoint", description = "The URL to handle parking endpoint")
public class OrderEndpoint {

	@Autowired
	private OrderService parkingService;
	
	
	
	@RequestMapping(value = "/batchinsert", method = {RequestMethod.POST})
	@ApiOperation("Orders batch syncs")
	public void batchSync(@RequestBody List<OrderDto> ordersDtos) {
		for(OrderDto orderDto : ordersDtos) {
			OrderDto existingDto = parkingService.findByOrderId(orderDto.getOrderId());
			if(existingDto != null && (existingDto.getCheckoutTime() == null || existingDto.getCheckoutTime()==  0)) {
				//just update existing record with new data
				orderDto.setId(existingDto.getId());
			}
			parkingService.save(orderDto);
		}
		
	}
	
	@RequestMapping(value = "", method = {RequestMethod.GET})
	@ApiOperation("Get all in/out logs")
	public @ResponseBody ResultObject<List<OrderDto>> getAll(
			@RequestParam(name="page", required  =  false, defaultValue="0") int page,
			@RequestParam(name="cardCode", required  =  false) String cardCode,
			@RequestParam(name="cardStt", required  =  false) String cardStt,
			@RequestParam(name="dateFrom", required  =  false) String dateFrom,
			@RequestParam(name="dateTo", required  =  false) String dateTo,
			@RequestParam(name="carNumber", required  =  false) String carNumber ) {
		Pageable paging = PageRequest.of(page, 100);
		OrderSearchForm orderSearchForm = new OrderSearchForm();
		if(cardCode != null && !cardCode.isEmpty()) {
			orderSearchForm.setCardCode(cardCode);
		}
		if(cardStt != null && !cardStt.isEmpty()) {
			orderSearchForm.setCardStt(cardStt);
		}
		if(carNumber != null && !carNumber.isEmpty()) {
			orderSearchForm.setCarNumber(carNumber);
		}
		if(dateFrom != null && !dateFrom.isEmpty()) {
			orderSearchForm.setDateFrom(dateFrom);
		}
		if(dateTo != null && !dateTo.isEmpty()) {
			orderSearchForm.setDateTo(dateTo);
		}
		
		return parkingService.findAll(paging, orderSearchForm);
	}
	 
	
}