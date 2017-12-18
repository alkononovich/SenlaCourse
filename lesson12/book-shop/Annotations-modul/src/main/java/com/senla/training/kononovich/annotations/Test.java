package com.senla.training.kononovich.annotations;

public class Test {

	public static void main(String[] args) {
		MyClass obj = new MyClass();
		AutoConfigurer ac = new AutoConfigurer();
		ac.configureObj(obj);
		System.out.println((MyClass)obj);
	}

}
