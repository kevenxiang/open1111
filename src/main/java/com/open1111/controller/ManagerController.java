package com.open1111.controller;

import javax.annotation.Resource;
import org.apache.shiro.subject.Subject;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.open1111.entity.Manager;
import com.open1111.service.ManagerService;
import com.open1111.util.Md5Util;
import com.open1111.util.ResponseUtil;

import net.sf.json.JSONObject;

/**
 * 管理员Controller层
 * @author xiang
 *
 */
@Controller
@RequestMapping("/manager2")
public class ManagerController {
	
	@Resource
	private ManagerService managerService;
	
	/**
	 * 用户登录
	 * @param manager
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(Manager manager,HttpServletResponse response) throws Exception {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(manager.getUserName(), Md5Util.md5(manager.getPassword(), Md5Util.SALT));
		JSONObject result = new JSONObject();
		try {
			subject.login(token);
			result.put("success", true);
		} catch (Exception e) {
			result.put("success", false);
			result.put("errorInfo", "用户名或者密码错误！");
			e.printStackTrace();
		}
		ResponseUtil.write(response, result);
		return null;
	}

}
























