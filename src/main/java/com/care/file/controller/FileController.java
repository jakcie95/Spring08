package com.care.file.controller;

import java.io.File;
import java.io.FileInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.care.file.service.FileService;

@Controller
public class FileController {
	@Autowired FileService fs;
	@GetMapping("form")
	public String form() {
		return "uploadForm";
	}
	@PostMapping("upload")
	/*public String upload(@RequestParam String id,
					@RequestParam String name,
					@RequestParam MultipartFile f) {
	*/
	public String upload(MultipartHttpServletRequest mul) {
		fs.fileProcess(mul);
		return "redirect:form";
	}
	@GetMapping("views")
	public String views(Model model) {
		fs.getData(model);
		return "result";
	}
	@GetMapping("download")
	public void download(String file,
						 HttpServletResponse response) 
										throws Exception{
		System.out.println("file : "+file);
		response.addHeader("Content-disposition", "attachment; fileName="+file);
		File f = new File(FileService.IMAGE_REPO+"/"+file);
		FileInputStream in = new FileInputStream(f);
		FileCopyUtils.copy(in, response.getOutputStream());
		in.close();
	}
	@GetMapping("delete")
	public String delete(String file, String id) {
		fs.delete(file, id);
		return "redirect:views";
	}
	@GetMapping("updateForm")
	public String updateForm(Model model,
						String id, String name, String file) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		model.addAttribute("file", file);
		System.out.println("id : "+id);
		System.out.println("name : "+name);
		System.out.println("file : "+file);
		return "updateForm";
	}
	@PostMapping("update")
	public String update(MultipartHttpServletRequest mul) {
		fs.update(mul);
		return "redirect:views";
	}
}








