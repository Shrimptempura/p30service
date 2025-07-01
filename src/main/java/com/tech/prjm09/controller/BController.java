package com.tech.prjm09.controller;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tech.prjm09.dao.IDao;
import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.dto.ReBrdimgDto;
import com.tech.prjm09.service.BContentViewService;
import com.tech.prjm09.service.BListService;
import com.tech.prjm09.service.BServiceInter;
import com.tech.prjm09.util.SearchVO;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class BController {
	
	private BServiceInter bServiceInter;
	
	@Autowired
	private IDao iDao;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@RequestMapping("list")
	public String list(HttpServletRequest request, SearchVO searchVO, Model model) {
		System.out.println("list() ctr");
		//
		model.addAttribute("request", request);
		model.addAttribute("searchVo", searchVO);
		bServiceInter = new BListService(iDao);
		
		bServiceInter.execute(model);
		
		return "list";
	}
	
	@RequestMapping("write_view")
	public String write_view(Model model) {
		System.out.println("wirte_view() ctr");
		return "write_view";
	}
		
	@RequestMapping("write")
	public String write(MultipartHttpServletRequest mtfRequest, Model model) {
		System.out.println("write() ctr");
		
		
		String bname = mtfRequest.getParameter("bname");
		String btitle = mtfRequest.getParameter("btitle");
		String bcontent = mtfRequest.getParameter("bcontent");
		
		System.out.println("title: " + btitle);
		iDao.write(bname, btitle, bcontent);
		
		String workPath = System.getProperty("user.dir");
		
//		String root = "C:\\hsts2025\\sts25_work\\prjm29replyboard_mpsupdown_multi\\"
//				+ "src\\main\\resources\\static\\files";
		String root = workPath + "\\src\\main\\resources\\static\\files";
		
		List<MultipartFile> fileList = mtfRequest.getFiles("file");
		
		int bid = iDao.selBid();
		System.out.println("bid>>>" + bid);
		
		for (MultipartFile mf : fileList) {
			String originalFile = mf.getOriginalFilename();
			System.out.println("files: " + originalFile);
			long longtime = System.currentTimeMillis();
			
			String changeFile = longtime + "_" + originalFile;
			System.out.println("change files: " + changeFile);
			
			String pathfile = root + "\\" + changeFile;
			try {
				if (!originalFile.equals("")) {
					mf.transferTo(new File(pathfile));
					System.out.println("upload success");
					
					// db에 기록
					iDao.imgwrite(bid, originalFile, changeFile);
					System.out.println("rebrdimgtv write sucess");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return "redirect:list";
	}
	
	@GetMapping("download")
	public String download(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String bid = request.getParameter("bid");
		String fname = request.getParameter("f");
		System.out.println(fname + " : " + bid);
		
		// 첨부파일이다
		response.setHeader("Content-Disposition", 
				"Attachment;filename = " + URLEncoder.encode(fname, "UTF-8"));
		
		String workPath = System.getProperty("user.dir");
		String realPath = workPath + "\\src\\main\\resources\\static\\files\\" + fname;
		
		FileInputStream fin = new FileInputStream(realPath);
		ServletOutputStream sout = response.getOutputStream();
		
		byte[] buf = new byte[1024];
		int size = 0;
		
		while ((size = fin.read(buf, 0, 1024)) != -1) {
			sout.write(buf, 0, size);
		}
		
		fin.close();
		sout.close();
		
//		BDto dto = iDao.contentView(bid);
//		model.addAttribute("content_view", dto);
		
		return "content_view?bid = " + bid;
	}
	
	@GetMapping("content_view")
	public String content_view(HttpServletRequest request, Model model) {
		System.out.println("content_view() ctr");		
		model.addAttribute("request", request);
		
		bServiceInter = new BContentViewService(iDao);
		bServiceInter.execute(model);
		
		return "content_view";
	}
	
	@GetMapping("modify_view")
	public String modify_view(HttpServletRequest request, Model model) {
		System.out.println("modify_view() ctr");
//		model.addAttribute("request", request);
//		command = new BModifyViewCommand();
//		command.execute(model);
		
		String bid = request.getParameter("bid");
		BDto dto = iDao.modifyView(bid);
		
		model.addAttribute("content_view", dto);
		
		return "modify_view";
	}
	
	@PostMapping("modify")
	public String modify(HttpServletRequest request, Model model) {
		System.out.println("modify() ctr");		
//		model.addAttribute("request", request);
//		command = new BModifyCommand();
//		command.execute(model);
		
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		iDao.modify(bid, bname, btitle, bcontent);
		
		return "redirect:list";
	}
	
	@GetMapping("reply_view")
	public String reply_view(HttpServletRequest request, Model model) {
		System.out.println("reply_view() ctr");		
//		model.addAttribute("request", request);
//		command = new BReplyViewCommand();
//		command.execute(model);
		
		String bid = request.getParameter("bid");
		
		BDto dto = iDao.reply_view(bid);
		model.addAttribute("reply_view", dto);
		
		return "reply_view";
	}
	
	@PostMapping("reply")
	public String reply(HttpServletRequest request, Model model) {
		System.out.println("reply() ctr");
//		model.addAttribute("request", request);
//		command = new BReplyCommand();
//		command.execute(model);
		String bid = request.getParameter("bid");
		String bname = request.getParameter("bname");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		String bgroup = request.getParameter("bgroup");
		String bstep = request.getParameter("bstep");
		String bindent = request.getParameter("bindent");
		
		iDao.replyShape(bgroup, bstep);
		iDao.reply(bid, bname, btitle, bcontent, bgroup, bstep, bindent);
		
		return "redirect:list";
	}
	
	@RequestMapping("delete")
	public String delete(HttpServletRequest request, Model model) {
		System.out.println("delete ctr");
//		model.addAttribute("request", request);
//		command = new BDeleteCommand();
//		command.execute(model);
		
		String bid = request.getParameter("bid");
		iDao.delete(bid);
		
		return "redirect:list";
	}
	
	
	
}
