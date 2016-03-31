package com.nhuszka.web.spring.xml_visualizer.storage;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class FileValidator {

	private static final String SPRING_BEANS_XSD_URI = "http://www.springframework.org/schema/beans/spring-beans.xsd";

	public static boolean isValidSpringBeanFile(byte[] fileBytes) {
		Source fileSource = new StreamSource(new ByteArrayInputStream(fileBytes));
		
		return checkValidSpringBeanXMLFile(fileSource);
	}
	
	public static boolean isValidSpringBeanFile(File file) {
		Source fileSource = new StreamSource(file);
		
		return checkValidSpringBeanXMLFile(fileSource);
	}

	private static boolean checkValidSpringBeanXMLFile(Source fileSource) {
		try {
			Schema springBeanSchema = getSchema(XMLConstants.W3C_XML_SCHEMA_NS_URI, SPRING_BEANS_XSD_URI);
			springBeanSchema.newValidator().validate(fileSource);
		} catch (SAXException | IOException e) {
			return false;
		}
		return true;
	}

	private static Schema getSchema(String schemaNamespaceURI, String xsdFileURI) throws MalformedURLException, SAXException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(schemaNamespaceURI);
		return schemaFactory.newSchema(new URL(xsdFileURI));
	}
}
