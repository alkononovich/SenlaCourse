package com.senla.training.kononovich.storage;

import java.util.List;

import com.senla.training.kononovich.entity.AbstractModel;

public interface IListEntity<E extends AbstractModel> {
	void add(E value);

	void update(int id, E value);

	void remove(int id);

	List<E> getList();

	void setList(List<E> value);

	int nextId();
}
