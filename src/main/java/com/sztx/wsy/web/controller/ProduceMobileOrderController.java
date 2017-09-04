package com.sztx.wsy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.ProduceOrderService;
import com.sztx.wsy.core.service.domain.request.ProduceOrderListReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderVO;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/mobile/order")
public class ProduceMobileOrderController extends BaseController{
	
	@Autowired
	private ProduceOrderService produceOrderService;
	
	@RequestMapping(value = "/findOrderList")
	public Result findOrderList(){
		
		ProduceOrderListReq req = ParameterUtil.parseObject(ProduceOrderListReq.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = produceOrderService.orderTotalCount(req);
		List<ProduceOrderVO> produceOrderVOs = produceOrderService.findOrderList(req, pageQuery);
		return ParameterUtil.pageSuccessResult(totalCount, produceOrderVOs);
	}
	
	@RequestMapping(value = "/findByOrderNo")
	public Result findByOrderNo(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		Integer type = ParameterUtil.getInteger("type");
		ProduceOrderVO produceOrderVO = produceOrderService.findByOrderNo(orderNo,type);
		return ParameterUtil.commonSuccessResult("produceOrderVO", produceOrderVO);
	}
}
