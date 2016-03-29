package com.nhuszka.web.spring.xml_visualizer.storage;

public class StoredFileModel {
	
	private String id;
	private String fileName;
	
	private StoredFileModel(String id, String fileName) {
		this.id = id;
		this.fileName = fileName;
	}
	
	public String getId() {
		return id;
	}
	
	public String getFileName() {
		return fileName ;
	}
	
	public static StoredFileModel createModel(String id, String fileName) {
		return new StoredFileModel(id, fileName);
	}
}
