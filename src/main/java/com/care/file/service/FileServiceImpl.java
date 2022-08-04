package com.care.file.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

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
	public void getOneData(String id, Model model) {
		model.addAttribute("dto", mapper.getOneData(id));
	}
	public void modify(MultipartHttpServletRequest mul) {
		FileDTO dto = new FileDTO();
		dto.setId(mul.getParameter("id"));
		dto.setName(mul.getParameter("name"));
		System.out.println(mul.getParameter("id"));
		System.out.println(mul.getParameter("name"));
		MultipartFile m = mul.getFile("file");
		if(m.getSize() != 0	) {
			File d = new File(IMAGE_REPO+"/"+mul.getParameter("origin"));
			d.delete();
			
			SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHHmmss");
			String sysFileName = f.format(new Date());
			System.out.println( sysFileName );
			sysFileName +=m.getOriginalFilename();
			
			dto.setImgName(sysFileName);
		
			File saveFile = new File(IMAGE_REPO+"/"+ sysFileName);
			
			try {
				m.transferTo(saveFile);//해당위치에 파일 저장
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else {
			dto.setImgName(mul.getParameter("origin"));
		}
		System.out.println("id : "+dto.getId());
		System.out.println("name : "+dto.getName());
		System.out.println("img : "+dto.getImgName());
		
		mapper.modify(dto);
	}
	public void fileProcess02(MultipartHttpServletRequest mul) {
		Iterator<String> fileNames = mul.getFileNames();
		while(fileNames.hasNext()) {
			MultipartFile m = mul.getFile(fileNames.next());
			if(m.getSize() != 0) {
				System.out.println(m.getOriginalFilename());
				File file = new File(IMAGE_REPO+"/"+m.getOriginalFilename());
				try {
					m.transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}









