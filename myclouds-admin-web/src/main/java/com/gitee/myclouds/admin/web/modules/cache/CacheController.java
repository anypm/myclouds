package com.gitee.myclouds.admin.web.modules.cache;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gitee.myclouds.toolbox.wrap.Dto;
import com.gitee.myclouds.toolbox.wrap.Dtos;

/**
 * 缓存模块
 * 
 * @author xiongchun
 *
 */
@Controller
@RequestMapping("sys/cache")
public class CacheController {
	
	@Autowired
	private CacheService cacheService;
	
	/**
	 * 同步配置类缓存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "syncCfgCache", method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Dto syncCfgCache(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		return Dtos.newDto(cacheService.syncCfgCache(inDto));
	}
	
}