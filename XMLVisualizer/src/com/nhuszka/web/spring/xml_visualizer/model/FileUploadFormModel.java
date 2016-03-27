package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadFormModel {

	private List<String> checkboxes = new ArrayList<>();
	private List<MultipartFile> files = new ArrayList<>();
	private String beanPackageFilter = "";

	public List<String> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(List<String> checkboxes) {
		this.checkboxes = checkboxes;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	public String getBeanPackageFilter() {
		return beanPackageFilter;
	}

	public void setBeanPackageFilter(String beanPackageFilter) {
		this.beanPackageFilter = beanPackageFilter;
	}
}