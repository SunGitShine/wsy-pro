package com.sztx.wsy.core.service;

import java.util.List;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.AccountDO;

public interface AccountService {

	void add(AccountDO accountDO);
	
	void delete(String username);
	
	void updatePassword(AccountDO accountDO);
	
	AccountDO findByUserName(String username);
	
	Integer totalCount(AccountDO accountDO);
	
	List<AccountDO> findByPage(AccountDO accountDO, PageQuery pageQuery);
	
	AccountDO login(String username, String password);
}
