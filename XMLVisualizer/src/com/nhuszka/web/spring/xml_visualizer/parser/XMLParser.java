package com.nhuszka.web.spring.xml_visualizer.parser;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nhuszka.web.spring.xml_visualizer.graph.GraphInput;
import com.nhuszka.web.spring.xml_visualizer.storage.DiskFileStorage;
import com.nhuszka.web.spring.xml_visualizer.storage.StoredFileModel;

@Component
public class XMLParser {
	
	private final SpringBeanXMLParser springBeanXMLParser;
	
	@Autowired
	public XMLParser(SpringBeanXMLParser xmlParser) {
		this.springBeanXMLParser = xmlParser;
	}

	public GraphInput<String> parseXmlsToGraphInput(Collection<String> fileIds, String beanPackageFilter) {
		Collection<File> files = getStoredFiles(fileIds);
		Collection<SpringBean> beans = springBeanXMLParser.parseBeanFiles(files);

		return convertSpringBeansToGraphInput(beans, beanPackageFilter);
	}

	private Collection<File> getStoredFiles(Collection<String> fileIds) {
		DiskFileStorage diskFileStorage = DiskFileStorage.getInstance();

		Collection<StoredFileModel> fileModels = diskFileStorage.listStoredFileModels(fileIds);
		return diskFileStorage.readStoredFiles(fileModels);
	}

	private GraphInput<String> convertSpringBeansToGraphInput(Collection<SpringBean> beans, String beanPackageFilter) {
		GraphInput<String> graphInput = GraphInput.<String> createInput();

		Collection<String> filteredBeanIds = computeFilteredBeanIds(beans, beanPackageFilter);
		graphInput.addVertexes(filteredBeanIds);
		addEdges(graphInput, beans, filteredBeanIds);

		return graphInput;
	}
	
	private Collection<String> computeFilteredBeanIds(Collection<SpringBean> beans, String beanPackageFilter) {
		Collection<String> filteredBeanIds = new ArrayList<>();

		for (SpringBean bean : beans) {
			String clazz = bean.getClazz();
			if (StringUtils.isBlank(beanPackageFilter) || clazz.startsWith(beanPackageFilter)) {
				filteredBeanIds.add(bean.getId());
			}
		}

		return filteredBeanIds;
	}

	private void addEdges(GraphInput<String> graphInput, Collection<SpringBean> beans,
			Collection<String> idFilter) {
		final List<SpringBean> filteredBeans = beans
				.stream()
				.filter((bean) -> idFilter.contains(bean.getId()))
				.collect(Collectors.toList());

		for (SpringBean bean : filteredBeans) {
			addEdges(bean, idFilter, graphInput);
		}
	}

	private void addEdges(SpringBean bean, Collection<String> idFilter, GraphInput<String> graphInput) {
		final Collection<String> filteredReferencedBeanIds = bean.getReferencedBeanIds()
				.stream()
				.filter((id) -> idFilter.contains(id))
				.collect(Collectors.toList());
		
		String beanId = bean.getId();
		for (String referencedBeanId : filteredReferencedBeanIds) {
			graphInput.addEdge(beanId, referencedBeanId);
		}
	}
}
