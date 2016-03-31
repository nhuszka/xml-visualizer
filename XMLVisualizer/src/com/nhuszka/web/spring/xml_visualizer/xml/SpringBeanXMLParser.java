package com.nhuszka.web.spring.xml_visualizer.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

class SpringBeanXMLParser {

	public List<SpringBean> parseBeanFiles(List<File> files) {
		List<SpringBean> beans = new ArrayList<>();

		for (File file : files) {
			beans.addAll(parseBeanFile(file));
		}

		return beans;
	}

	private List<SpringBean> parseBeanFile(File beanFile) {
		List<SpringBean> beanList = new ArrayList<>();
		
		try {
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader xmlTagReader = xmlInputFactory.createXMLEventReader(new FileInputStream(beanFile));
			
			ParsingState state = new ParsingState();
			while (xmlTagReader.hasNext()) {
				processNextXMLTag(xmlTagReader, state);
				if (state.isBeanParsingDone()) {
					beanList.add(createSpringBean(state));
					state.reset();
				}
			}
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}

		return beanList;
	}

	private void processNextXMLTag(XMLEventReader xmlTagReader, ParsingState state) throws XMLStreamException {
		XMLEvent xmlTag = xmlTagReader.nextEvent();
		
		if (xmlTag.isStartElement()) {
			processStartTag(xmlTagReader, xmlTag, state);
		} else if (xmlTag.isEndElement()) {
			processEndTag(xmlTag, state);
		}
	}

	private void processStartTag(XMLEventReader xmlTagReader, XMLEvent xmlTag, ParsingState state)
			throws XMLStreamException {
		StartElement startElement = xmlTag.asStartElement();
		String tagName = startElement.getName().getLocalPart();
		
		if ("bean".equals(tagName)) {
			String beanClass = getClazz(startElement);
			String beanId = getId(startElement);
			if (beanClass != null) {
				state.setBeanClass(beanClass);
				state.setBeanId(beanId);
			}
		} else if ("property".equals(tagName)) {
			xmlTag = xmlTagReader.nextEvent();
			processRefAttribute(startElement, state);
		}
	}


	private String getId(StartElement startElement) {
		Attribute idAttr = startElement.getAttributeByName(new QName("id"));
		if (idAttr != null) {
			return idAttr.getValue();
		}
		return null;
	}

	private String getClazz(StartElement startElement) {
		Attribute classAttr = startElement.getAttributeByName(new QName("class"));
		if (classAttr != null) {
			return classAttr.getValue();
		}
		return null;
	}
	
	private void processEndTag(XMLEvent xmlEvent, ParsingState state) {
		EndElement endElement = xmlEvent.asEndElement();
		String tagName = endElement.getName().getLocalPart();
		if ("bean".equals(tagName) && state.isBeanParsingInProgress()) {
			state.setBeanParsingDone();
		}
	}
	
	private void processRefAttribute(StartElement startElement, ParsingState state) {
		Attribute refAttr = startElement.getAttributeByName(new QName("ref"));
		if (refAttr != null) {
			state.addReferencedBeanId(refAttr.getValue());
		}
	}
	
	private SpringBean createSpringBean(ParsingState state) {
		SpringBean bean = new SpringBean();
		
		bean.setId(state.getBeanId());
		bean.setClazz(state.getBeanClass());
		for (String referencedBeanId : state.getReferencedBeanIds()) {
			bean.addReferencedBeanId(referencedBeanId);
		}
		
		return bean;
	}
}