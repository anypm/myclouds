package com.gitee.myclouds.admin.web.enums;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gitee.myclouds.toolbox.wrap.Dto;
import com.gitee.myclouds.toolbox.wrap.Dtos;

/**
 * 枚举类型管理
 * 
 * @author xiongchun
 *
 */
@Controller
@RequestMapping("enums")
public class EnumsController {

	@Autowired
	private EnumsService enumsService;

	@RequestMapping("init")
	public String init(ModelMap map) {

		return "modules/sys/enums";
	}

	/**
	 * 查询列表
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "list", method = { RequestMethod.POST, RequestMethod.GET }, produces = "application/json")
	@ResponseBody
	public String list(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		String jsonString = enumsService.list(inDto);
		return jsonString;
	}
	
	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "delete", method = { RequestMethod.POST }, produces = "application/json")
	@ResponseBody
	public Dto deleteParam(HttpServletRequest request) {
		Dto inDto = Dtos.newDto(request);
		return Dtos.newDto(enumsService.delete(inDto));
	}
	
}
