package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadFormModel {

	private Collection<MultipartFile> files = new ArrayList<>();
	
	public Collection<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(Collection<MultipartFile> files) {
		this.files = files;
	}
}