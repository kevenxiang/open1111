package com.open1111.dao;

import com.open1111.entity.Manager;

/**
 * 管理员Dao接口
 * @author xiang
 *
 */
public interface ManagerDao {
	
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public Manager getByUserName(String userName);
	
	/**
	 * 更新管理员信息
	 * @param manager
	 * @return
	 */
	public Integer update(Manager manager);

}
