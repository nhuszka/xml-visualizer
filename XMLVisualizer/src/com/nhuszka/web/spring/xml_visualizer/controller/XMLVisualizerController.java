package com.nhuszka.web.spring.xml_visualizer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.nhuszka.web.spring.xml_visualizer.model.FileUploadFormModel;

@Controller
public class XMLVisualizerController {

	private static final String UPLOAD_PAGE = "uploadfile";
	private static final String GRAPH_PAGE = "graph";

	@RequestMapping("/showForm")
	public ModelAndView showUploadForm() {
		return new ModelAndView(UPLOAD_PAGE, "uploadForm", new FileUploadFormModel());
	}
	
	@RequestMapping(value = "/saveFiles", method = RequestMethod.POST)
	public String saveFiles(@ModelAttribute("uploadForm") FileUploadFormModel uploadForm, Model map)
			throws IllegalStateException, IOException {
		
		final List<MultipartFile> files = 
				FileFilter.filterFilesByIndexes(uploadForm.getFiles(), uploadForm.getCheckboxes());
		final String beanPackageFilter = uploadForm.getBeanPackageFilter();
		
		for (MultipartFile file : files) {
			System.out.println(file.getOriginalFilename());
		}

		return GRAPH_PAGE;
	}
}