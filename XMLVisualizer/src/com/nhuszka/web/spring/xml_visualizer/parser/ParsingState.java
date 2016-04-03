package com.nhuszka.web.spring.xml_visualizer.parser;

import java.util.Collection;
import java.util.HashSet;

class ParsingState {

	private String beanId;
	private String beanClass;
	private Collection<String> referencedBeanIds = new HashSet<>();
	private boolean beanParsingDone;

	public void setBeanId(String id) {
		this.beanId = id;
	}

	public void setBeanClass(String beanClass) {
		this.beanClass = beanClass;
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

	public SpringBean getSpringBean() {
		SpringBean bean = new SpringBean();
		
		bean.setId(beanId);
		bean.setClazz(beanClass);
		for (String referencedBeanId : referencedBeanIds) {
			bean.addReferencedBeanId(referencedBeanId);
		}
		
		reset();
		return bean;
	}
	
	public void reset() {
		beanId = null;
		beanClass = null;
		referencedBeanIds.clear();
		beanParsingDone = false;
	}
}
