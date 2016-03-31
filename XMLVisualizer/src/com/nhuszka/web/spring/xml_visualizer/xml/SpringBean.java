package com.nhuszka.web.spring.xml_visualizer.xml;

import java.util.Collection;
import java.util.HashSet;

public class SpringBean {

    private String id;
    private String clazz;
    private Collection<String> referencedBeanIds = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String value) {
        this.id = value;
    }
    
    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }
    
    public void addReferencedBeanId(String otherBeanId) {
    	if (!referencedBeanIds.contains(otherBeanId)) {
    		referencedBeanIds.add(otherBeanId);
    	}
    }
    
    public Collection<String> getReferencedBeanIds() {
    	return referencedBeanIds;
    }
}
