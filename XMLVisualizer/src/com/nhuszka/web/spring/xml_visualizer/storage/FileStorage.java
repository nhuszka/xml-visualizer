package com.nhuszka.web.spring.xml_visualizer.storage;

import java.io.File;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
	
	List<StoredFileModel> storeFiles(List<MultipartFile> multipartFiles);
	
	List<StoredFileModel> listStoredFileModels(List<String> ids);
	
	File readStoredFile(StoredFileModel fileModel);
	
	List<File> readStoredFiles(List<StoredFileModel> fileModels);
}
