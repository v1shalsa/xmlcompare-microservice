package com.xmlcompare.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.xmlcompare.objectfactory.ObjectFactoryMethods;

@SpringBootApplication
public class CompareStarter {

	public static void main(String[] args) {

		SpringApplication.run(CompareStarter.class, args);
		
		ObjectFactoryMethods ofm = new ObjectFactoryMethods();
		
		ofm.parseObjects();
		
		System.out.println("Line no 20");
	}

}
