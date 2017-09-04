package com.sztx.wsy.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sztx.wsy.common.domain.BusinessException;
import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.core.service.AccountService;
import com.sztx.wsy.dataaccess.dao.AccountMysqlDAO;
import com.sztx.wsy.dataaccess.domain.AccountDO;
import com.sztx.wsy.util.StringUtil;
import com.sztx.wsy.util.ValidateUtil;

@Service("accountService")
public class AccountServiceImpl implements AccountService{

	@Autowired
	private AccountMysqlDAO accountMysqlDAO;
	
	@Override
	public void add(AccountDO accountDO) {
		
		chackParam(accountDO);
		AccountDO registerAccount = accountMysqlDAO.findByUserName(accountDO.getUsername());
		if(registerAccount != null){//改用户名已存在
			throw new BusinessException("该用户名已经存在");
		}
		accountMysqlDAO.add(accountDO);
	}

	@Override
	public void delete(String username) {
		
		ValidateUtil.isNotBlank(username, "用户名不能为空");
		accountMysqlDAO.delete(username);
	}

	@Override
	public void updatePassword(AccountDO accountDO) {
		
		ValidateUtil.isNotBlank(accountDO.getUsername(), "用户名不能为空");
		ValidateUtil.isNotBlank(accountDO.getPassword(), "密码不能为空");
		accountMysqlDAO.updatePassword(accountDO);
	}

	@Override
	public AccountDO findByUserName(String username) {
		
		ValidateUtil.isNotBlank(username, "用户名不能为空");
		return accountMysqlDAO.findByUserName(username);
	}

	@Override
	public Integer totalCount(AccountDO accountDO) {
		return accountMysqlDAO.totalCount(accountDO);
	}

	@Override
	public List<AccountDO> findByPage(AccountDO accountDO, PageQuery pageQuery) {
		return accountMysqlDAO.findByPage(accountDO, pageQuery);
	}

	public void chackParam(AccountDO accountDO){
		if(StringUtil.isEmpty(accountDO.getUsername()) || accountDO.getType() == null 
				|| StringUtil.isEmpty(accountDO.getRealname()) || StringUtil.isEmpty(accountDO.getMobile())){
			throw new BusinessException("参数异常");
		}
	}

	@Override
	public AccountDO login(String username, String password) {
		
		ValidateUtil.isNotBlank(username, "用户名不能为空");
		ValidateUtil.isNotBlank(password, "密码不能为空");
		AccountDO exsitAccount = findByUserName(username);
		if(exsitAccount == null){
			throw new BusinessException("用户不存在");
		}
		if(!password.equals(exsitAccount.getPassword())){
			throw new BusinessException("密码错误");
		}
		return exsitAccount;
	}
}
