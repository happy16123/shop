package com.myshop.domain;

import lombok.Data;

@Data
public class FileVO {
	
	private String uuid;
	private String uploadPath;
	private String fileName;
	private String fileType;
	
	private Long bno;
}
