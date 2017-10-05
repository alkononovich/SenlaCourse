package com.senla.training.kononovich.service.comparators;

import java.util.Comparator;

public class ComparatorManager {
	public static Comparator bookCostComparator = new BookCostComparator();
	public static Comparator bookCountComparator = new BookCountComparator();
	public static Comparator bookDateComparator = new BookDateComparator();
	public static Comparator bookNameComparator = new BookNameComparator();
	public static Comparator bookReceiptDateComparator = new BookReceiptDateComparator();
	public static Comparator orderCostComparator = new OrderCostComparator();
	public static Comparator orderDateComparator = new OrderDateComparator();
	public static Comparator orderStatusComparator = new OrderStatusComparator();
}
