package com.nhuszka.web.spring.xml_visualizer.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nhuszka.web.spring.xml_visualizer.graph.GraphCreator;
import com.nhuszka.web.spring.xml_visualizer.model.FileUploadFormModel;
import com.nhuszka.web.spring.xml_visualizer.model.FilterFormModel;
import com.nhuszka.web.spring.xml_visualizer.model.StoredFilesModel;
import com.nhuszka.web.spring.xml_visualizer.storage.FileStorage;

@Controller
public class XMLVisualizerController {

	private static final String UPLOAD_PAGE = "uploadfile";
	private static final String FILTER_PAGE = "filter";

	@RequestMapping(value = "/showUploadForm", method = RequestMethod.GET)
	public ModelAndView showUploadForm() {
		return new ModelAndView(UPLOAD_PAGE, "uploadForm", new FileUploadFormModel());
	}
	
	@RequestMapping(value = "/uploadFiles", method = RequestMethod.POST)
	public String saveFiles(HttpServletRequest request, @ModelAttribute("uploadForm") FileUploadFormModel uploadForm, Model model)
			throws IllegalStateException, IOException {
		StoredFilesModel storedFilesModel = FileStorage.getInstance().storeFiles(uploadForm.getFiles());
		
		request.getSession().setAttribute("storedFilesModel", storedFilesModel);
		
		return FILTER_PAGE;
	}
	
	@RequestMapping(value = "/showGraph", method = RequestMethod.POST)
	public String createGraph(HttpServletRequest request, @ModelAttribute("filterForm") FilterFormModel filterFormModel, Model model)
			throws IllegalStateException, IOException {
		final List<String> ids = filterFormModel.getCheckboxes();
		final String beanPackageFilter = filterFormModel.getBeanPackageFilter();
		
		// TODO: read XML files, define beans and their relationship
		
		model.addAttribute("beanPackageFilter", filterFormModel.getBeanPackageFilter());
		model.addAttribute("graphBase64", getDemoGraphEncodedBase64());
		
		return FILTER_PAGE;
	}

	private String getDemoGraphEncodedBase64() {
		List<String> beans = new ArrayList<>();
		for (int i = 1; i <= 30; ++i) {
			beans.add("bean" + i);
		}

		Map<String, String> edges = new HashMap<>();
		for (int i = 1; i < 24; ++i) {
			edges.put("bean" + i, "bean" + (i + 4));
		}
		
		return new GraphCreator().createGraphInBase64String(beans, edges);
	}
}