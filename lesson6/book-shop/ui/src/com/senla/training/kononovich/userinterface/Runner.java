package com.senla.training.kononovich.userinterface;

import com.senla.training.kononovich.userinterface.menu.MenuController;

import serialization.Serialization;

public class Runner {

	public static void main(String[] args) {
		Serialization.serialFromFile();
		new MenuController().run();
		Serialization.serialToFile();
	}

}
