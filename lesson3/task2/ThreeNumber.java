package task2;

public class ThreeNumber {
	public static int[] randomNumbs() {
		int[] list = new int[3];
		for (int i = 0; i < 3; i++)
			list[i] = new java.util.Random().nextInt(999);
		return list;
	}
}
