package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.List;

public class FilterFormModel {
		
	private List<String> ids = new ArrayList<>();
	private String beanPackageFilter = "";

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public String getBeanPackageFilter() {
		return beanPackageFilter;
	}

	public void setBeanPackageFilter(String beanPackageFilter) {
		this.beanPackageFilter = beanPackageFilter;
	}
}
