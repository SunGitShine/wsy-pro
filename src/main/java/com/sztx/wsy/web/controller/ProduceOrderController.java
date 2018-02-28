package com.sztx.wsy.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.common.domain.ResultCode;
import com.sztx.wsy.core.service.ProduceOrderService;
import com.sztx.wsy.core.service.domain.request.OrderStatisticReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderListReq;
import com.sztx.wsy.core.service.domain.request.ProduceOrderProductVO;
import com.sztx.wsy.core.service.domain.request.ProduceOrderVO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDetailDO;
import com.sztx.wsy.dataaccess.domain.ProduceOrderProductDistributeDO;
import com.sztx.wsy.util.LoginUtil;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/order")
public class ProduceOrderController extends BaseController{
	
	@Autowired
	private ProduceOrderService produceOrderService;

	@RequestMapping(value = "/add")
	public Result add(HttpServletRequest request){
		
		Integer smsIsOpen = ParameterUtil.getInteger("smsIsOpen");//是否发短信通知
		Integer isCreatDeliveryNote = ParameterUtil.getInteger("isCreatDeliveryNote");//是否创建送货单
		
		ProduceOrderVO produceOrderVO = makeProduceOrderVO(request);
		
		produceOrderService.add(produceOrderVO, smsIsOpen, isCreatDeliveryNote);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findOrderList")
	public Result findOrderList(){
		
		ProduceOrderListReq req = ParameterUtil.parseObject(ProduceOrderListReq.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = produceOrderService.orderTotalCount(req);
		List<ProduceOrderVO> produceOrderVOs = produceOrderService.findOrderList(req, pageQuery);
		return ParameterUtil.pageSuccessResult(totalCount, produceOrderVOs);
	}
	
	@RequestMapping(value = "/distribute")
	public Result distribute(HttpServletRequest request){
		
		String data = request.getParameter("d");
		JSONObject json = JSONObject.parseObject(data);
		Integer type = json.getInteger("type");
		String orderNo = json.getString("produceOrderNo");
		
		List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs = JSONObject.parseArray(json.getString("orderDistributes"), ProduceOrderProductDistributeDO.class);
		produceOrderService.orderDistribute(produceOrderProductDistributeDOs, type, orderNo, LoginUtil.currentRealname());
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findByOrderNo")
	public Result findByOrderNo(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		Integer type = ParameterUtil.getInteger("type");
		ProduceOrderVO produceOrderVO = produceOrderService.findByOrderNo(orderNo,type);
		return ParameterUtil.commonSuccessResult("produceOrderVO", produceOrderVO);
	}
	
	@RequestMapping(value = "/delete")
	public Result delete(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		produceOrderService.delete(orderNo);
		
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/update")
	public Result update(HttpServletRequest request){
		
		ProduceOrderVO produceOrderVO = makeProduceOrderVO(request);
		Integer isCreatDeliveryNote = ParameterUtil.getInteger("isCreatDeliveryNote");//是否创建送货单
		
		produceOrderService.update(produceOrderVO, isCreatDeliveryNote);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/tailorFinish")
	public Result tailorFinish(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		ProduceOrderDO produceOrderDO = new ProduceOrderDO();
		produceOrderDO.setOrderNo(orderNo);
		produceOrderDO.setUpdateUser(LoginUtil.currentRealname());
		produceOrderDO.setTailorStatus(1);
		produceOrderDO.setTailorFinishTime(new Date());
		produceOrderDO.setTailorFinishUser(LoginUtil.currentRealname());
		
		
		produceOrderService.updateStatus(produceOrderDO, null);
		
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/vampFinish")
	public Result vampFinish(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		Integer smsIsOpen = ParameterUtil.getInteger("smsIsOpen");
		ProduceOrderDO produceOrderDO = new ProduceOrderDO();
		produceOrderDO.setOrderNo(orderNo);
		produceOrderDO.setUpdateUser(LoginUtil.currentRealname());
		produceOrderDO.setVampStatus(2);
		produceOrderDO.setVampFinishTime(new Date());
		produceOrderDO.setVampFinishUser(LoginUtil.currentRealname());
		
		produceOrderService.updateStatus(produceOrderDO,smsIsOpen);
		
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/soleFinish")
	public Result soleFinish(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		ProduceOrderDO produceOrderDO = new ProduceOrderDO();
		produceOrderDO.setOrderNo(orderNo);
		produceOrderDO.setUpdateUser(LoginUtil.currentRealname());
		produceOrderDO.setSoleStatus(2);
		produceOrderDO.setSoleFinishTime(new Date());
		produceOrderDO.setSoleFinishUser(LoginUtil.currentRealname());
		
		produceOrderService.updateStatus(produceOrderDO, null);
		
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/qcFinish")
	public Result qcFinish(){
		
		String orderNo = ParameterUtil.getString("orderNo");
		Integer smsIsOpen = ParameterUtil.getInteger("smsIsOpen");
		ProduceOrderDO produceOrderDO = new ProduceOrderDO();
		produceOrderDO.setOrderNo(orderNo);
		produceOrderDO.setUpdateUser(LoginUtil.currentRealname());
		produceOrderDO.setQcStatus(1);
		produceOrderDO.setQcFinishTime(new Date());
		produceOrderDO.setQcFinishUser(LoginUtil.currentRealname());
		
		produceOrderService.updateStatus(produceOrderDO, smsIsOpen);
		
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/updateDistribute")
	public Result updateDistribute(HttpServletRequest request){
		
		String data = request.getParameter("d");
		JSONObject json = JSONObject.parseObject(data);
		Integer type = json.getInteger("type");
		String orderNo = json.getString("produceOrderNo");
		
		List<ProduceOrderProductDistributeDO> produceOrderProductDistributeDOs = JSONObject.parseArray(json.getString("orderDistributes"), ProduceOrderProductDistributeDO.class);
		produceOrderService.updateDistribute(produceOrderProductDistributeDOs, type, orderNo, LoginUtil.currentRealname());
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/orderStatistic")
	public Result orderStatistic(){
		
		OrderStatisticReq req = ParameterUtil.parseObject(OrderStatisticReq.class);
		PageQuery pageQuery = getPageQuery();
		
		List<ProduceOrderDO> produceOrderDOs = produceOrderService.orderStatisticList(req, pageQuery);
		Integer totalCount = produceOrderService.orderStatisticCount(req);
		
		Integer totalShoeNum = 0;
		for(ProduceOrderDO produceOrderDO : produceOrderDOs){
			totalShoeNum = totalShoeNum + produceOrderDO.getOrderNum();
		}
		
		Result result = new Result(ResultCode.COMMON_SUCCESS, true);
		result.setProperty("produceOrderDOs", produceOrderDOs);
		result.setProperty("totalCount", totalCount);
		result.setProperty("totalShoeNum", totalShoeNum);
		return result;
	}
	
	public ProduceOrderVO makeProduceOrderVO(HttpServletRequest request){
		
		String data = request.getParameter("d");
		JSONObject json = JSONObject.parseObject(data);
		JSONArray orderProductArray = json.getJSONArray("produceOrderProductVOs");
			
		ProduceOrderVO produceOrderVO = ParameterUtil.parseObject(data, ProduceOrderVO.class);
		
		List<ProduceOrderProductVO> produceOrderProductVOs = new ArrayList<ProduceOrderProductVO>();
		for(int i=0; i<orderProductArray.size(); i++){
			
			JSONObject orderProductObj = orderProductArray.getJSONObject(i);
			ProduceOrderProductVO produceOrderProductVO = JSONObject.parseObject(orderProductObj.toJSONString(), ProduceOrderProductVO.class);
			
			JSONArray orderProductDetailArray = orderProductObj.getJSONArray("produceOrderProductDetailDOs");
			List<ProduceOrderProductDetailDO> produceOrderProductDetailDOs = new ArrayList<ProduceOrderProductDetailDO>();
			for(int j=0; j<orderProductDetailArray.size(); j++){
				
				JSONObject orderProductDetailObj = orderProductDetailArray.getJSONObject(j);
				ProduceOrderProductDetailDO produceOrderProductDetailDO = JSONObject.parseObject(orderProductDetailObj.toJSONString(), ProduceOrderProductDetailDO.class);
				produceOrderProductDetailDOs.add(produceOrderProductDetailDO);
			}
			produceOrderProductVO.setProduceOrderProductDetailDOs(produceOrderProductDetailDOs);
			produceOrderProductVOs.add(produceOrderProductVO);
		}
		produceOrderVO.setUpdateUser(LoginUtil.currentRealname());
		produceOrderVO.setCreateUser(LoginUtil.currentRealname());
		return produceOrderVO;
	}
}
