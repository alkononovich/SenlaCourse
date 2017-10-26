package com.senla.training.kononovich.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ConfigPropery {
	String configName();

	String propertyName();

	Class<?> type();
}
