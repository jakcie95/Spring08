package com.care.file.mybatis;

import java.util.List;

import com.care.file.dto.FileDTO;

public interface FileMapper {
	public void saveData(FileDTO dto);
	public List<FileDTO> getData();
	public int delete(String id);
	public int update(String id);
	public FileDTO getOneData(String id);
	public void modify(FileDTO dto);
}
