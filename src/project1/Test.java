package project1;

import java.util.Date;

public class Test {

	public static void main(String[] args) {
		LinkedList<Integer> list = new LinkedList<>();
		list.insertSorted(10);
		list.insertSorted(9);
		list.insertSorted(999);
		list.insertSorted(5);
		list.insertSorted(-32);
		list.insertSorted(7);
		list.printAll();

	}

}
