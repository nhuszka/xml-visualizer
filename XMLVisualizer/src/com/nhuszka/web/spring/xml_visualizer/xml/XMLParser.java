package com.nhuszka.web.spring.xml_visualizer.xml;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.nhuszka.web.spring.xml_visualizer.graph.GraphInput;
import com.nhuszka.web.spring.xml_visualizer.storage.DiskFileStorage;
import com.nhuszka.web.spring.xml_visualizer.storage.StoredFileModel;

public class XMLParser {

	public GraphInput<String> parseXmlsToGraphInput(List<String> fileIds, String beanPackageFilter) {
		List<File> files = getStoredFiles(fileIds);
		List<SpringBean> beans = new SpringBeanXMLParser().parseBeanFiles(files);
		
		return convertSpringBeansToGraphInput(beans, beanPackageFilter);
	}

	private List<File> getStoredFiles(List<String> fileIds) {
		DiskFileStorage diskFileStorage = DiskFileStorage.getInstance();
		
		List<StoredFileModel> fileModels = diskFileStorage.listStoredFileModels(fileIds);
		return diskFileStorage.readStoredFiles(fileModels);
	}
	
	private GraphInput<String> convertSpringBeansToGraphInput(List<SpringBean> beans, String beanPackageFilter) {
		GraphInput<String> graphInput = GraphInput.<String>createInput();
		
		Map<String, String> beansClasses = computeBeansClassesWithPackageFilter(beans, beanPackageFilter);
		Collection<String> beanIds = beansClasses.keySet();
		
		graphInput.addVertexes(beanIds);
		addEdges(graphInput, beans, beanIds);
		
		return graphInput;
	}

	private void addEdges(GraphInput<String> graphInput, List<SpringBean> beans, Collection<String> beanIds) {
		for (SpringBean bean : beans) {
			String beanId = bean.getId();
		
			for (String referencedBeanId : bean.getReferencedBeanIds()) {
				if (beanIds.contains(beanId) && beanIds.contains(referencedBeanId)) {
					graphInput.addEdge(bean.getId(), referencedBeanId);
				}
			}
		}
	}

	private Map<String, String> computeBeansClassesWithPackageFilter(List<SpringBean> beans, String beanPackageFilter) {
		Map<String, String> beansClasses = new HashMap<>();
		
		for (SpringBean bean : beans) {
			String clazz = bean.getClazz();
			if (StringUtils.isBlank(beanPackageFilter) || clazz.startsWith(beanPackageFilter)) {
				beansClasses.put(bean.getId(), bean.getClazz());
			}
		}
		
		return beansClasses;
	}
}
