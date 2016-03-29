package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.List;


public class StoredFilesModel {
	
	private List<StoredFileModel> storedFiles = new ArrayList<>();

	public List<StoredFileModel> getStoredFiles() {
		return storedFiles;
	}

	public void addStoredFile(StoredFileModel fileModel) {
		storedFiles.add(fileModel);
	}
}
