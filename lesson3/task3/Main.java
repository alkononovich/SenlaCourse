package task3;

public class Main {

	public static void main(String[] args) {
		Store store = new Store(5);
		store.addDress(new Boots());
		store.addDress(new Cap());
		store.addDress(new EarFlaps());
		store.addDress(new Sandals());
		store.addDress(new Shoes());
		
		System.out.println(store.sumWeight());

	}

}
