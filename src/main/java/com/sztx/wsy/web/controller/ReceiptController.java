package com.sztx.wsy.web.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.common.domain.ResultCode;
import com.sztx.wsy.core.service.ReceiptService;
import com.sztx.wsy.core.service.domain.request.ReceiptPageReq;
import com.sztx.wsy.dataaccess.domain.ReceiptDO;
import com.sztx.wsy.util.LoginUtil;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/balance")
public class ReceiptController extends BaseController{

	@Autowired
	private ReceiptService receiptService;
	
	@RequestMapping(value = "/creatReceipt")
	public Result creatReceipt(HttpServletRequest request){
		
		ReceiptDO receiptDO = ParameterUtil.parseObject(ReceiptDO.class);
		receiptDO.setCreateUser(LoginUtil.currentRealname());
		
		receiptService.createReceipt(receiptDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/getReceipt")
	public Result getReceipt(HttpServletRequest request){
		
		String orderNo = ParameterUtil.getString("orderNo");
		
		ReceiptDO receiptDO = receiptService.getReceipt(orderNo);
		Result result = new Result(ResultCode.COMMON_SUCCESS, true);
		result.setProperty("receiptDO", receiptDO);
		return result;
	}
	
	@RequestMapping(value = "/updateStatus")
	public Result updateStatus(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		receiptService.updateBalanceStatus(orderNo);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/print")
	public Result print(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		receiptService.print(orderNo);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findBalanceList")
	public Result findBalanceList(){
		
		ReceiptPageReq receiptPageReq = ParameterUtil.parseObject(ReceiptPageReq.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = receiptService.totalCount(receiptPageReq);
		List<ReceiptDO> receiptDOs = receiptService.findByPage(receiptPageReq, pageQuery);
		Map<String, Integer> sumMap = receiptService.getSumMsg(receiptPageReq);
		
		Result result = new Result(ResultCode.COMMON_SUCCESS, true);
		result.setPage(totalCount, receiptDOs);
		result.setProperty("sumMap", sumMap);
		return result;
	}
	
	@RequestMapping(value = "/editReceipt")
	public Result editReceipt(){
		
		ReceiptDO receiptDO = ParameterUtil.parseObject(ReceiptDO.class);
		receiptDO.setUpdateUser(LoginUtil.currentRealname());
		
		receiptService.updateReceipt(receiptDO);
		return ParameterUtil.commonSuccessResult();
	}
	
}
