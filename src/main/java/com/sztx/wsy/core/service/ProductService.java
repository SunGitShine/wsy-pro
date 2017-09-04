package com.sztx.wsy.core.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.ProductDO;

public interface ProductService {

	void add(ProductDO productDO);
	
	void update(ProductDO productDO);
	
	ProductDO findById(Integer id);
	
	Integer totalCount(ProductDO productDO);
	
	List<ProductDO> findByPage(ProductDO productDO, PageQuery pageQuery);
	
	String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException;
	
	void delete(Integer productId);
}
