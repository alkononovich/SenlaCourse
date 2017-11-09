package com.senla.training.kononovich.uicontroller;

import com.senla.training.kononovich.api.IContainer;
import com.senla.training.kononovich.dependencyinjection.DependencyInjection;
import com.senla.training.kononovich.storage.Container;

import config.IConfiguration;
import serialization.ISerialization;

public class SerializeUtilite {
	private static ISerialization serial = (ISerialization)DependencyInjection.getClassInstance(ISerialization.class);
	private static IContainer cont = (IContainer)DependencyInjection.getClassInstance(IContainer.class);
	private static IConfiguration conf = (IConfiguration)DependencyInjection.getClassInstance(IConfiguration.class);
	
	public static void serialize() {
		serial.serialToFile(cont, conf.getProps().getSerialPath());
	}
	
	public static void unserialize() {
		Container clone = (Container) serial.serialFromFile(conf.getProps().getSerialPath());
		cont.setBooks(clone.getBooks());
		cont.setOrders(clone.getOrders());
		cont.setClaims(clone.getClaims());
	}
}
