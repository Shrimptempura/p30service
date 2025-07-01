package com.tech.prjm09.service;

import java.util.Map;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.IDao;

import jakarta.servlet.http.HttpServletRequest;

public class BModifyService implements BServiceInter {
	
	private final IDao iDao;

	public BModifyService(IDao iDao) {
		this.iDao = iDao;
	}

	@Override
	public void execute(Model model) {
		System.out.println(">>>>>>>>>BModifyService");
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		iDao.modify(bid, bname, btitle, bcontent);
	}

}
