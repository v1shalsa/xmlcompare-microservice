package com.xmlcompare.unmarshall;


import javax.xml.bind.JAXBContext;

import org.springframework.stereotype.Component;

import sources.ObjectFactory;

@Component
public class JaxbContextObj {
	
	public JAXBContext getJaxbcontext() {
		
		try {
			JAXBContext jaxbcontext = JAXBContext.newInstance(ObjectFactory.class);	
			return jaxbcontext;
		}catch(Exception e) {		
			e.printStackTrace();
			return null;
		}
			
	}

}
