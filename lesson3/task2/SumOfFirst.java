package task2;

public class SumOfFirst {
	public static int sumOfFirst (int[] list) {
		int sum = 0;
		for (int i = 0; i < list.length; i++)
			sum += (list[i] / 100);
		return sum;
	}
}
