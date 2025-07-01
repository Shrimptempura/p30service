package com.tech.prjm09.dto;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 생성자로 값을 받아 넣기 위해
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class BDto {

	private int bid;
	private String bname;
	private String btitle;
	private String bcontent;
	private Timestamp bdate;
	private int bhit;
	private int bgroup;
	private int bstep;
	private int bindent;
}
