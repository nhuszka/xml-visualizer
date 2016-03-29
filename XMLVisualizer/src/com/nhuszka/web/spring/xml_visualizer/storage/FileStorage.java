package com.nhuszka.web.spring.xml_visualizer.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nhuszka.web.spring.xml_visualizer.model.StoredFileModel;
import com.nhuszka.web.spring.xml_visualizer.model.StoredFilesModel;

public class FileStorage {
	
	private static final String BASE_DIR = "D:\\";
	private static FileStorage FILE_STORAGE_INSTANCE;
	private Set<StoredFileModel> files = new HashSet<>();

	public static FileStorage getInstance() {
		if (FILE_STORAGE_INSTANCE == null) {
			FILE_STORAGE_INSTANCE = new FileStorage();
		}
		return FILE_STORAGE_INSTANCE;
	}
	
	public StoredFileModel getFileModel(String id) throws FileNotFoundException {
		for (StoredFileModel file : files)  {
			if (file.getId().equals(id)) {
				return file;
			}
		}
		throw new FileNotFoundException("Stored file not found with id: " + id);
	}
	
	public File readStoredFile(StoredFileModel fileModel) {
		String fullFilePath = getFullStoredFilePath(fileModel.getFileName(), fileModel.getId());
		return new File(fullFilePath);
	}
	
	public StoredFilesModel storeFiles(List<MultipartFile> multipartFiles) {
		StoredFilesModel model = new StoredFilesModel();
		
		for (MultipartFile multipartFile : multipartFiles) {
			try {
				if (!multipartFile.getOriginalFilename().isEmpty()) {
					model.addStoredFile(storeFile(multipartFile));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return model;
	}

	private StoredFileModel storeFile(MultipartFile multipartFile) throws IOException {
		byte[] bytes = multipartFile.getBytes();
		String md5Hash = DigestUtils.md5Hex(bytes);
		String fileName = multipartFile.getOriginalFilename();
		String fullFilePath = getFullStoredFilePath(fileName, md5Hash);
		
		Files.write(Paths.get(fullFilePath), bytes);
		StoredFileModel storedFile = StoredFileModel.createModel(md5Hash, fileName);
		
		files.add(storedFile);
		return storedFile;
		
	}
	
	private String getFullStoredFilePath(String originalName, String md5) {
		return BASE_DIR + originalName + "-" + md5;
	}
}
