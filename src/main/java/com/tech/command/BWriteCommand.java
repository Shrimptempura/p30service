package com.tech.command;

import java.util.Map;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.BDao;

import jakarta.servlet.http.HttpServletRequest;

public class BWriteCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		// request 사용, 키값 request..
		Map<String, Object> map = model.asMap();
		HttpServletRequest request = (HttpServletRequest) map.get("request");
		
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BDao dao = new BDao();
		dao.write(bname, btitle, bcontent);
	}

}
