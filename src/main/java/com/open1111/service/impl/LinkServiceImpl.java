package com.open1111.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.open1111.dao.LinkDao;
import com.open1111.entity.Link;
import com.open1111.service.LinkService;

/**
 * 友情链接Service实现类
 * @author xiang
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService {
	
	@Resource
	private LinkDao linkDao;

	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return linkDao.getTotal(map);
	}

	public Integer add(Link link) {
		return linkDao.add(link);
	}

	public Integer update(Link link) {
		return linkDao.update(link);
	}

	public Integer delete(Integer id) {
		return linkDao.delete(id);
	}

}
