package com.tech.command;

import java.util.ArrayList;

import org.springframework.ui.Model;

import com.tech.prjm09.dao.BDao;
import com.tech.prjm09.dto.BDto;

public class BListCommand implements BCommand {

	@Override
	public void execute(Model model) {
		
		BDao dao = new BDao();
		ArrayList<BDto> dtos = dao.list();
		
		model.addAttribute("list", dtos);
	}
	
}
