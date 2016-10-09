package com.nhuszka.web.spring.xml_visualizer.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import org.springframework.stereotype.Component;

@Component
public class SpringBeanXMLParser {

	public Collection<SpringBean> parseBeanFiles(Collection<File> files) {
		Collection<SpringBean> beans = new ArrayList<>();

		for (File file : files) {
			beans.addAll(parseBeanFile(file));
		}

		return beans;
	}

	private Collection<SpringBean> parseBeanFile(File beanFile) {
		try {
			XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
			XMLEventReader xmlTagReader = xmlInputFactory.createXMLEventReader(new FileInputStream(beanFile));
			
			return processXML(xmlTagReader);
		} catch (FileNotFoundException | XMLStreamException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
		
	}

	private Collection<SpringBean> processXML(XMLEventReader xmlTagReader) throws XMLStreamException {
		Collection<SpringBean> beanList = new ArrayList<>();

		ParsingState state = new ParsingState();
		while (xmlTagReader.hasNext()) {
			processNextXMLTag(xmlTagReader, state);
			if (state.isBeanParsingDone()) {
				beanList.add(state.getSpringBean());
			}
		}
		
		return beanList;
	}

	private void processNextXMLTag(XMLEventReader xmlTagReader, ParsingState state) throws XMLStreamException {
		XMLEvent xmlTag = xmlTagReader.nextEvent();
		
		if (xmlTag.isStartElement()) { // xmlTag.getEventType() == XMLEvent.START_ELEMENT
			processStartTag(xmlTagReader, xmlTag, state);
		} else if (xmlTag.isEndElement()) { // xmlTag.getEventType() == XMLEvent.END_ELEMENT
			processEndTag(xmlTag, state);
		}
	}

	private void processStartTag(XMLEventReader xmlTagReader, XMLEvent xmlTag, ParsingState state)
			throws XMLStreamException {
		StartElement startElement = xmlTag.asStartElement();
		String tagName = startElement.getName().getLocalPart();
		
		if (isBean(tagName)) {
			String beanClass = getClazz(startElement);
			String beanId = getId(startElement);
			if (beanClass != null) {
				state.setBeanClass(beanClass);
				state.setBeanId(beanId);
			}
		} else if (isProperty(tagName)) {
			xmlTag = xmlTagReader.nextEvent();
			processRefAttribute(startElement, state);
		}
	}

	private boolean isProperty(String tagName) {
		return "property".equals(tagName);
	}

	private boolean isBean(String tagName) {
		return "bean".equals(tagName);
	}

	private String getId(StartElement startElement) {
		Attribute idAttr = getAttribute(startElement, "id");
		return idAttr != null ? idAttr.getValue() : null;
	}

	private Attribute getAttribute(StartElement startElement, String attribute) {
		return startElement.getAttributeByName(new QName(attribute));
	}

	private String getClazz(StartElement startElement) {
		Attribute classAttr = getAttribute(startElement, "class");
		return classAttr != null ? classAttr.getValue() : null;
	}
	
	private void processRefAttribute(StartElement startElement, ParsingState state) {
		Attribute refAttr = getAttribute(startElement, "ref");
		if (refAttr != null) {
			state.addReferencedBeanId(refAttr.getValue());
		}
	}
	
	private void processEndTag(XMLEvent xmlEvent, ParsingState state) {
		EndElement endElement = xmlEvent.asEndElement();
		String tagName = endElement.getName().getLocalPart();
		if (isBean(tagName) && state.isBeanParsingInProgress()) {
			state.setBeanParsingDone();
		}
	}
}