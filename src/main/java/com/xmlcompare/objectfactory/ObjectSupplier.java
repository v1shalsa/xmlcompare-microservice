package com.xmlcompare.objectfactory;

@FunctionalInterface
public interface ObjectSupplier {
	
	public <T>T getType(String type);

}
