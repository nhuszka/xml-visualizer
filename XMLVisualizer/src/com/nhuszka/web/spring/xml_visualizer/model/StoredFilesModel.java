package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.List;

import com.nhuszka.web.spring.xml_visualizer.storage.StoredFileModel;

public class StoredFilesModel {

	private List<StoredFileModel> fileModels = new ArrayList<>();

	public List<StoredFileModel> getStoredFiles() {
		return fileModels;
	}

	public void addStoredFile(StoredFileModel storedFileModel) {
		fileModels.add(storedFileModel);
	}
}
