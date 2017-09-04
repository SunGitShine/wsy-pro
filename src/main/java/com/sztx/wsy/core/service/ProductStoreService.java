package com.sztx.wsy.core.service;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListReq;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateListRsp;
import com.sztx.wsy.core.service.domain.request.ProductStoreOperateRequest;
import com.sztx.wsy.dataaccess.domain.ProductStoreDO;

public interface ProductStoreService {

	List<ProductStoreDO> findProductStoreDetail(Integer productId);
	
	void productOutputOrInput(ProductStoreOperateRequest request);
	
	Integer totalCount(ProductStoreOperateListReq request);
	
	List<ProductStoreOperateListRsp> productOutputOrInputList(ProductStoreOperateListReq request, PageQuery pageQuery);
	
	void updateSotreSet(List<ProductStoreDO> productStoreDOs, Integer productId);
}
