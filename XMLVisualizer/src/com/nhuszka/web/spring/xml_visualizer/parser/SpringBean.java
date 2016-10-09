package com.nhuszka.web.spring.xml_visualizer.parser;

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
    
    @Override
    public String toString() {
    	final StringBuilder sb = new StringBuilder();
    	sb.append("SpringBean[");
    	sb.append(" id=").append(id);
    	sb.append(" class=").append(clazz);
    	sb.append(" referencedBeanIds=").append(referencedBeanIds);
    	return sb.toString();
    }
}
