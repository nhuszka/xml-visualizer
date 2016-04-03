package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.Collection;

public class FilterFormModel {
		
	private Collection<String> ids = new ArrayList<>();
	private String beanPackageFilter = "";

	public Collection<String> getIds() {
		return ids;
	}

	public void setIds(Collection<String> ids) {
		this.ids = ids;
	}

	public String getBeanPackageFilter() {
		return beanPackageFilter;
	}

	public void setBeanPackageFilter(String beanPackageFilter) {
		this.beanPackageFilter = beanPackageFilter;
	}
}
