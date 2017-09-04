package com.sztx.wsy.dataaccess.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sztx.wsy.common.domain.PageQuery;
import com.sztx.wsy.dataaccess.domain.AccountDO;

public interface AccountMysqlDAO {
	
	void add(AccountDO accountDO);

	void delete(String username);
	
	void updatePassword(AccountDO accountDO);
	
	AccountDO findByUserName(String username);
	
	Integer totalCount(@Param("accountDO")AccountDO accountDO);
	
	List<AccountDO> findByPage(@Param("accountDO")AccountDO accountDO,
			@Param("pageQuery")PageQuery pageQuery);
	
	List<AccountDO> findByType(@Param("type")Integer type);
}