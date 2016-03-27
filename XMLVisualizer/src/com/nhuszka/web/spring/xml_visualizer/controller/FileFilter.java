package com.nhuszka.web.spring.xml_visualizer.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

public class FileFilter {

	public static List<MultipartFile> filterFilesByIndexes(List<MultipartFile> files, List<String> indexes) {
		try {
			return indexes.stream()
					.map((index) -> files.get(Integer.parseInt(index)))
					.filter((file) -> !file.getName().isEmpty())
					.collect(Collectors.toList());
		} catch (NumberFormatException nfe) {
			nfe.printStackTrace();
			return Collections.emptyList();
		}
	}
}
