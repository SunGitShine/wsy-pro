package com.sztx.wsy.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.common.domain.Result;
import com.sztx.wsy.core.service.AccountService;
import com.sztx.wsy.dataaccess.domain.AccountDO;
import com.sztx.wsy.util.ParameterUtil;

@Controller
@RequestMapping(value = "/account")
public class AccountController extends BaseController{

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "/add")
	public Result add(){
		
		AccountDO accountDO = ParameterUtil.parseObject(AccountDO.class);
		accountService.add(accountDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/delete")
	public Result delete(){
		
		String username = ParameterUtil.getString("username");
		accountService.delete(username);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/updatePassword")
	public Result updatePassword(){
		
		AccountDO accountDO = ParameterUtil.parseObject(AccountDO.class);
		accountService.updatePassword(accountDO);
		return ParameterUtil.commonSuccessResult();
	}
	
	@RequestMapping(value = "/findByUserName")
	public Result findByUserName(){
		
		String username = ParameterUtil.getString("username");
		AccountDO accountDO = accountService.findByUserName(username);
		return ParameterUtil.commonSuccessResult("accountDO", accountDO);
	}
	
	@RequestMapping(value = "/findByPage")
	public Result findByPage(){
		
		AccountDO accountDO = ParameterUtil.parseObject(AccountDO.class);
		PageQuery pageQuery = getPageQuery();
		
		Integer totalCount = accountService.totalCount(accountDO);
		List<AccountDO> accountDOs = accountService.findByPage(accountDO, pageQuery);
		return ParameterUtil.pageSuccessResult(totalCount, accountDOs);
	}
}
