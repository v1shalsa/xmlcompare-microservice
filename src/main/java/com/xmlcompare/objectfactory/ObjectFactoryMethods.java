package com.xmlcompare.objectfactory;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.lang.annotation.*;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import com.xmlcompare.unmarshall.JaxbContextObj;

import sources.ObjectFactory;

public class ObjectFactoryMethods {
	
	
	public void parseObjects() 
	{		
		
		Object xmlobjects = getUnMarshalledObject();
		
		for (Field fd:xmlobjects.getClass().getDeclaredFields()) {
			
			try {
				fd.setAccessible(true);
				System.out.println("Property name :- "+fd.getName()+
						" Value :- "+fd.get(xmlobjects));
				Annotation[] antos = fd.getAnnotations();
				for(Annotation anto:antos) {
					
					String xe = "@javax.xml.bind.annotation.XmlElement";
					
					if(anto.toString().contains(xe)) {
						System.out.println("Annotation :- "+anto.toString());
					}
										
				}
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	//public void innerObjects(List <T> innobjects) {
		
	//}
	
	public <T>T getUnMarshalledObject() {
		
		try {
			JaxbContextObj jObj = new JaxbContextObj();
			
			Unmarshaller unmarsh = jObj.getJaxbcontext()
										.createUnmarshaller();
			
			File file = new File("src/main/resources/xml/boolean.xml");
			
			SAXBuilder saxBuilder = new SAXBuilder();
	         Document document = saxBuilder.build(file);
	         
	         String type = document.getRootElement().getName();
	         System.out.println("Root element :- "+type);
	 		
	 		Object ir = this.getType(type);
	 		
	 		ir = unmarsh.unmarshal(file);
	 		
	 		return (T) ir;
	         		
			
		} catch (JAXBException | JDOMException|IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	
	
	public <T> T getType(String objectname){		
		
		ObjectFactory objf = new ObjectFactory();
		
		Method[] objmethods = objf.getClass().getDeclaredMethods();
		
		for(Method mt:objmethods) {
			
			if(mt.getName().equals("create"+objectname)) {								
				
				return (T) mt.getReturnType();
				
			}
		}
		
		return null;
	}

}
