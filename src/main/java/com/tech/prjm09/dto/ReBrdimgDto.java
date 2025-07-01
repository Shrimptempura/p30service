package com.tech.prjm09.dto;

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
public class ReBrdimgDto {

	private int rebno;
	private int bid;
	private String reborgfile;
	private String rebchgfile;
}
