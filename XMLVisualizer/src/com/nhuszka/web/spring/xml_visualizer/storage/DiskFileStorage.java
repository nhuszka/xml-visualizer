package com.nhuszka.web.spring.xml_visualizer.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import com.nhuszka.web.spring.xml_visualizer.xml.FileValidator;

public class DiskFileStorage implements FileStorage {
	
	private static final String BASE_DIR = "D:\\XMLVisualizer-temp\\";
	private static DiskFileStorage FILE_STORAGE_INSTANCE;
	private Collection<StoredFileModel> files = new HashSet<>();

	public static DiskFileStorage getInstance() {
		if (FILE_STORAGE_INSTANCE == null) {
			FILE_STORAGE_INSTANCE = new DiskFileStorage();
		}
		return FILE_STORAGE_INSTANCE;
	}

	@Override
	public List<StoredFileModel> storeFiles(List<MultipartFile> multipartFiles) {
		List<StoredFileModel> models = new ArrayList<>();
		createBaseDirOnDemand();
		for (MultipartFile multipartFile : multipartFiles) {
			try {
				if (isValidSpringXMLBeanFile(multipartFile)) {
					models.add(storeFile(multipartFile));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return models;
	}
	
	private static void createBaseDirOnDemand() {
		if (Files.notExists(Paths.get(BASE_DIR))) {
			try {
				Files.createDirectory(Paths.get(BASE_DIR));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean isValidSpringXMLBeanFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.getOriginalFilename().isEmpty()) {
			return false;
		}
		return FileValidator.isValidSpringBeanFile(multipartFile.getBytes());
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
	
	@Override
	public List<StoredFileModel> listStoredFileModels(List<String> ids) {
		List<StoredFileModel> fileModels = new ArrayList<>();
		
		for (String id : ids) {
			try {
				fileModels.add(getStoredFileModel(id));
			} catch (FileNotFoundException fnfe) {
				fnfe.printStackTrace();
			}
		}
		
		return fileModels;
	}
	
	private StoredFileModel getStoredFileModel(String id) throws FileNotFoundException {
		for (StoredFileModel file : files)  {
			if (file.getId().equals(id)) {
				return file;
			}
		}
		throw new FileNotFoundException("Stored file not found with id: " + id);
	}
	
	@Override
	public File readStoredFile(StoredFileModel fileModel) {
		String fullFilePath = getFullStoredFilePath(fileModel.getFileName(), fileModel.getId());
		return new File(fullFilePath);
	}

	@Override
	public List<File> readStoredFiles(List<StoredFileModel> fileModels) {
		List<File> storedFiles = new ArrayList<>();
		
		for (StoredFileModel fileModel : fileModels) {
			storedFiles.add(readStoredFile(fileModel));
		}
		
		return storedFiles;
	}
}
