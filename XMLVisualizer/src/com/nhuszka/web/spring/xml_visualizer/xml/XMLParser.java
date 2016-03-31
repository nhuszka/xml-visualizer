package com.nhuszka.web.spring.xml_visualizer.xml;

import java.io.File;
import java.util.List;

import com.nhuszka.web.spring.xml_visualizer.graph.GraphInput;
import com.nhuszka.web.spring.xml_visualizer.storage.DiskFileStorage;
import com.nhuszka.web.spring.xml_visualizer.storage.StoredFileModel;

public class XMLParser {

	private GraphInput<String> createDemoGraphInput() {
		GraphInput<String> graphInput = GraphInput.<String>createInput();
		
		for (int i = 1; i <= 15; ++i) {
			graphInput.addVertex("vertex" + i);
		}
		for (int i = 1; i <= 11; ++i) {
			graphInput.addEdge("vertex" + i, "vertex" + (i + 4));
		}
		
		return graphInput;
	}

	public GraphInput<String> parseXmlsToGraphInput(List<String> fileIds, String beanPackageFilter) {
		List<File> files = getStoredFiles(fileIds);
		
		// TODO parse (XML) files, add info to graphInput
		
		GraphInput<String> graphInput = createDemoGraphInput();
		return graphInput;
	}

	private List<File> getStoredFiles(List<String> fileIds) {
		DiskFileStorage diskFileStorage = DiskFileStorage.getInstance();
		
		List<StoredFileModel> fileModels = diskFileStorage.listStoredFileModels(fileIds);
		return diskFileStorage.readStoredFiles(fileModels);
	}
}
