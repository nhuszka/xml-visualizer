package com.nhuszka.web.spring.xml_visualizer.xml;

import java.util.Collection;
import java.util.HashSet;

class ParsingState {

	private String beanId;
	private String beanClass;
	private Collection<String> referencedBeanIds = new HashSet<>();
	private boolean beanParsingDone;
	
	public void reset() {
		beanId = null;
		beanClass = null;
		referencedBeanIds.clear();
		beanParsingDone = false;
	}

	public String getBeanId() {
		return beanId;
	}

	public void setBeanId(String id) {
		this.beanId = id;
	}

	public String getBeanClass() {
		return beanClass;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
	}

	public Collection<String> getReferencedBeanIds() {
		return referencedBeanIds;
	}

	public void addReferencedBeanId(String referencedBeanId) {
		referencedBeanIds.add(referencedBeanId);
	}
	
	public boolean isBeanParsingInProgress() {
		return beanId != null;
	}

	public boolean isBeanParsingDone() {
		return beanParsingDone;
	}

	public void setBeanParsingDone() {
		this.beanParsingDone = true;
	}
}
