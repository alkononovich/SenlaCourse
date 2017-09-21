package task2;


public class Main {

	public static void main(String[] args) {
		int[] list = ThreeNumber.randomNumbs();
		Printer.printList(list);
		System.out.println(SumOfFirst.sumOfFirst(list));

	}

}
