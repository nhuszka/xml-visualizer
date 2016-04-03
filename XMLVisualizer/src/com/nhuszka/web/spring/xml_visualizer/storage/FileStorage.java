package com.nhuszka.web.spring.xml_visualizer.storage;

import java.io.File;
import java.util.Collection;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorage {
	
	Collection<StoredFileModel> storeFiles(Collection<MultipartFile> multipartFiles);
	
	Collection<StoredFileModel> listStoredFileModels(Collection<String> ids);
	
	File readStoredFile(StoredFileModel fileModel);
	
	Collection<File> readStoredFiles(Collection<StoredFileModel> fileModels);
}
