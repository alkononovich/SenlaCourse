package com.senla.training.kononovich.uicontroller;

import com.senla.training.kononovich.storage.Container;

import config.Configuration;
import serialization.Serialization;

public class SerializeUtilite {
	private static Serialization serial = new Serialization();
	private static Container cont = Container.getInstance();
	private static Configuration conf = Configuration.getInstance();
	
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
