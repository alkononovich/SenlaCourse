package com.senla.training.kononovich.storage;

import java.util.ArrayList;
import java.util.List;

import com.senla.training.kononovich.entity.Client;

public class ClientList implements IListEntity<Client> {

	private List<Client> clients;
	private int iterator = 0;

	@Override
	public void add(Client client) {
		client.setId(nextId());
		getList().add(client);
	}

	@Override
	public void update(int id, Client client) {
		for (Client row : getList()) {
			if (row.getId() == id) {
				row = client;
			}
		}
	}

	@Override
	public void remove(int id) {
		Client toBeDeleted = null;
		for (Client client : getList()) {
			if (client.getId() == id) {
				toBeDeleted = client;
				break;
			}
		}
		getList().remove(toBeDeleted);
	}

	@Override
	public List<Client> getList() {
		if (clients == null)
			clients = new ArrayList<Client>();
		return clients;
	}

	@Override
	public void setList(List<Client> clients) {
		this.clients = clients;

	}

	@Override
	public int nextId() {
		if (!getList().isEmpty()) {
			iterator = getList().get(getList().size() - 1).getId();
		}
		return ++iterator;
	}
}
