package com.nhuszka.web.spring.xml_visualizer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class XMLVisualizerController {
	
	@RequestMapping("/showForm")
	public ModelAndView showUploadForm() {
		ModelAndView mv = new ModelAndView("helloworld");
		mv.addObject("message", "Please upload XML files");
		return mv;
	}
}