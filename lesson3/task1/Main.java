package task1;

public class Main {
	public static void main (String[] args) {
		String[] list = new String[5];
		list[0] = "1";
		list[1] = "11";
		list[2] = "1asdf";
		list[3] = "1124df";
		list[4] = "1sdf";
		
		System.out.println(Sorter.maxLength(list));
		
		Sorter.sortUp(list);
		Printer.printList(list);
		
		Sorter.sortDown(list);
		Printer.printList(list);
	}
}
