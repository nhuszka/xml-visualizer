package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadFormModel {

	private List<MultipartFile> files = new ArrayList<>();
	
	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}