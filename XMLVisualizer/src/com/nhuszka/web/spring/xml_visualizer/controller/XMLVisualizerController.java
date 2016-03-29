package com.nhuszka.web.spring.xml_visualizer.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nhuszka.web.spring.xml_visualizer.model.FileUploadFormModel;
import com.nhuszka.web.spring.xml_visualizer.model.FilterFormModel;
import com.nhuszka.web.spring.xml_visualizer.model.StoredFilesModel;
import com.nhuszka.web.spring.xml_visualizer.storage.FileStorage;

@Controller
public class XMLVisualizerController {

	private static final String UPLOAD_PAGE = "uploadfile";
	private static final String FILTER_PAGE = "filter";

	@RequestMapping("/showForm")
	public ModelAndView showUploadForm() {
		return new ModelAndView(UPLOAD_PAGE, "uploadForm", new FileUploadFormModel());
	}
	
	@RequestMapping(value = "/saveFiles", method = RequestMethod.POST)
	public ModelAndView saveFiles(@ModelAttribute("uploadForm") FileUploadFormModel uploadForm, Model map)
			throws IllegalStateException, IOException {
		StoredFilesModel storedFilesModel = FileStorage.getInstance().storeFiles(uploadForm.getFiles());
		return new ModelAndView(FILTER_PAGE, "storedFilesModel", storedFilesModel);
	}
	
	@RequestMapping(value = "/createGraph", method = RequestMethod.POST)
	public ModelAndView createGraph(@ModelAttribute("filterForm") FilterFormModel filterFormModel, Model map)
			throws IllegalStateException, IOException {
		final List<String> ids = filterFormModel.getCheckboxes();
		final String beanPackageFilter = filterFormModel.getBeanPackageFilter();
		
//		new GraphCreator().demo();

		return new ModelAndView(FILTER_PAGE, "filterForm", filterFormModel);

	}
}