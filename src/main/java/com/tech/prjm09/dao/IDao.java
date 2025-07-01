package com.tech.prjm09.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tech.prjm09.dto.BDto;
import com.tech.prjm09.dto.ReBrdimgDto;

@Mapper
public interface IDao {
//	ArrayList<BDto> list();
//	ArrayList<BDto> list(int start, int end);
	ArrayList<BDto> list(int start, int end, String sk, String selNum);
	
	
	void write(String bname, String btitle, String bcontent);
	
	public int selBid();	// max
	
	void imgwrite(int bid, String originalFile, String changeFile);
	
	ArrayList<ReBrdimgDto> selectImg(String bid);
	
	BDto contentView(String sbid);
	BDto modifyView(String sbid);
	void modify(String bid, String bname, String btitle, String bcontent);
	BDto reply_view(String sbid);
	void reply(String bid, String bname, String btitle, String bcontent, 
			String bgroup, String bstep, String bindent);
	int replyShape(String strgroup, String strstep);
	void delete(String bid);
	void upHit(String sbid);
//	int selectBoardCount();
	int selectBoardCount(String sk, String selNum);



}
