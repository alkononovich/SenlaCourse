package task3;

public class Store {
	private int place;
	private int size;
	private Dress[] store;
	
	public Store(int size) {
		this.size = size;
		store = new Dress[size];
		place = 0;
	}
	
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
	public void addDress(Dress dr) {
		if (place < size) {
			store[place] = dr;
			place++;
		}
		else System.out.println("Склад заполнен");
	}
	public void addDress(Dress dr, int place) {
		if (place < size && place >= 0)
			store[place] = dr;
		else System.out.println("Такого номера места на складе нет");
	}
	
	public void freePlaces() {
		for (int i = 0; i < size; i++) {
			if (store[i] == null)
				System.out.print(i + " ");
		}
	}
	
	public void removeDress (int i) {
		store[i] = null;
	}
	
	public int sumWeight() {
		int sum = 0;
		for (int i = 0; i < size; i++) {
			if (store[i] != null)
				sum += store[i].weight;
		}
		return sum;
	}
	
}
