package com.gitee.myclouds.system.module.home;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gitee.myclouds.common.util.MyListUtil;
import com.gitee.myclouds.common.util.MyUtil;
import com.gitee.myclouds.common.wrapper.Dto;
import com.gitee.myclouds.common.wrapper.Dtos;
import com.gitee.myclouds.system.common.vo.MenuVO;

/**
 * 首页服务
 * 
 * @author xiongchun
 * 
 */
@Service
public class HomeService {
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * 查询用户功能菜单列表
	 * 
	 * @param inDto
	 * @return
	 */
	public List<MenuVO> listMenus(Dto inDto) {
		List<MenuVO> menuVOs = sqlSession.selectList("sql.home.listMenus", inDto);
		String ctxPath = inDto.getString("ctxPath");
		for (MenuVO menuVO : menuVOs) {
			String url = menuVO.getUrl();
			if (MyUtil.isNotEmpty(url)) {
				menuVO.setUrl(ctxPath + (StringUtils.startsWith(url, "/") ? url : "/" + url));
			}
		}
		String jql = "SELECT * FROM :MyList WHERE parent_id = :parent_id";
		List<MenuVO> level1Menus = MyListUtil.list(menuVOs, MenuVO.class, jql, Dtos.newDto("parent_id", 1));
		for (MenuVO menuVO : level1Menus) {
			List<MenuVO> level2Menus = MyListUtil.list(menuVOs, MenuVO.class, jql, Dtos.newDto("parent_id", menuVO.getId()));
			if (MyUtil.isNotEmpty(level2Menus)) {
				menuVO.setSubMenus(level2Menus);
			}
		}
		return level1Menus;
	}
	
}
