package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.List;

public class FilterFormModel {
		
	private List<String> checkboxes = new ArrayList<>();
	private String beanPackageFilter = "";

	public List<String> getCheckboxes() {
		return checkboxes;
	}

	public void setCheckboxes(List<String> checkboxes) {
		this.checkboxes = checkboxes;
	}

	public String getBeanPackageFilter() {
		return beanPackageFilter;
	}

	public void setBeanPackageFilter(String beanPackageFilter) {
		this.beanPackageFilter = beanPackageFilter;
	}
}
