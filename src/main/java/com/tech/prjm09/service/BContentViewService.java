package com.tech.prjm09.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.dto.ReBrdimgDto;

import jakarta.servlet.http.HttpServletRequest;

public class BContentViewService implements BServiceInter {
	
	private final IDao iDao;

	public BContentViewService(IDao iDao) {
		this.iDao = iDao;
	}

	@Override
	public void execute(Model model) {
		System.out.println(">>>>>>>>>BContentViewService");
		
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		iDao.upHit(bid);
				
		BDto dto = iDao.contentView(bid);
		model.addAttribute("content_view", dto);
		
		ArrayList<ReBrdimgDto> imgList = iDao.selectImg(bid);
		model.addAttribute("imgList", imgList);
	
	}

}
