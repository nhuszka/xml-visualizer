package com.nhuszka.web.spring.xml_visualizer.model;

import java.util.ArrayList;
import java.util.Collection;

import com.nhuszka.web.spring.xml_visualizer.storage.StoredFileModel;

public class StoredFilesModel {

	private Collection<StoredFileModel> fileModels = new ArrayList<>();

	public Collection<StoredFileModel> getStoredFiles() {
		return fileModels;
	}

	public void addStoredFile(StoredFileModel storedFileModel) {
		fileModels.add(storedFileModel);
	}
}
