package com.care.file.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.dto.FileDTO;
import com.care.file.mybatis.FileMapper;

@Service
public class FileServiceImpl implements FileService{
	@Autowired FileMapper mapper;
	
	
	public void fileProcess(MultipartHttpServletRequest mul) {
		System.out.println(mul.getParameter("id"));
		System.out.println(mul.getParameter("name"));
		
		FileDTO dto = new FileDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		
		
		MultipartFile file = mul.getFile("f");
		System.out.println(file.getOriginalFilename());
		if(file.getSize() != 0) {//!file.isEmpty()
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			String sysFileName = f.format(new Date());
			System.out.println( sysFileName );
			sysFileName +=file.getOriginalFilename();
			
			dto.setImgName(sysFileName);
		
			File saveFile = new File(IMAGE_REPO+"/"+ sysFileName);
			
			try {
				file.transferTo(saveFile);//해당위치에 파일 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else {
			dto.setImgName("nan");
		}
		mapper.saveData(dto);
	}
	public void getData(Model model) {
		model.addAttribute("list", mapper.getData());
	}
	public void delete(String file, String id) {
		int result = mapper.delete(id);
		if(result == 1) {
			File d = new File(IMAGE_REPO+"/"+file);
			d.delete();
		}
	}
	public void update(MultipartHttpServletRequest mul){
		System.out.println(mul.getParameter("id"));
		System.out.println(mul.getParameter("name"));
		FileDTO dto = new FileDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
	}
}









