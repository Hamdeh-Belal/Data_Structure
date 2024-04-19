package project1;

public class LinkedList<T extends Comparable<T>> {
	private Node<T> head;

	public LinkedList() {
		head = new Node<>(null);
	}

	public void printAll_1() {
		System.out.print("Head -- >");

		Node<T> curr = head;
		while (curr != null) {
			System.out.print(curr.toString() + "--->");
			curr = curr.getNext();
		}

		System.out.println("Null");
	}

	public void printAll() {
		printAll(head);

	}

	private void printAll(Node<T> curr) {
		if (head == null) {
			System.out.print("Head -->Null");
			return;
		}

		if (curr == head) {
			System.out.print("Head -->" + curr + "-->");
			printAll(curr = curr.getNext());
		} else if (curr == null)
			System.out.print("  Null  ");
		else {
			System.out.print(curr.toString() + "-->");
			printAll(curr = curr.getNext());
		}

	}

	public Node<T> getHead() {
		return head;
	}

	public void setHead(Node<T> head) {
		this.head = head;
	}

	public void insertAtHead(T data) {
		Node<T> n = new Node<T>(data);
		n.setNext(head.getNext());
		head.setNext(n);

	}

	public void InsertAtLast(T data) {
		Node<T> n = new Node<T>(data);
		Node<T> curr = head.getNext();
		Node<T> prev = head.getNext();
		while (curr != null) {
			prev = curr;
			curr = curr.getNext();
		}
		prev.setNext(n);
	}

	public int length() {
		if (head.getNext() == null)
			return 0;
		else {
			Node<T> curr = head.getNext();
			int index = 0;
			while (curr != null) {
				index++;
				curr = curr.getNext();
			}
			return index;
		}
	}

	public int lengthRTail() {
		return lengthR2Tail(head.getNext(), 0);
	}

	public int lengthR1() {

		return lengthR1(head.getNext());
	}

	private int lengthR1(Node<T> curr) {
		if (curr == null)
			return 0;
		else
			return 1 + lengthR1(curr.getNext());

	}

	private int lengthR2Tail(Node<T> curr, int sum) {
		if (curr == null)
			return sum;
		else
			return lengthR2Tail(curr.getNext(), ++sum);

	}

	public void insertSorted(T data) {
		Node<T> n = new Node<T>(data);
		Node<T> curr = head.getNext();
		Node<T> prev = head.getNext();
		while (curr != null && (curr.getData().compareTo(n.getData()) < 0)) {
			prev = curr;
			curr = curr.getNext();
		} // end of while loop !!!
		if (curr == prev) {
			n.setNext(head.getNext());
			head.setNext(n);
		} else if (curr == null)
			prev.setNext(n);
		else {
			n.setNext(curr);
			prev.setNext(n);
		}

	}

	public T deleteSorted(T data) { // delete sorted and unsorted
		T temp = null;
		Node<T> prev = head.getNext();
		Node<T> curr = head.getNext();
		while (curr != null && !(curr.getData().equals(data))) {
			prev = curr;
			curr = curr.getNext();
		}
		if (curr != null) {
			if (curr == head.getNext()) {
				temp = head.getNext().getData();
				head.setNext(head.getNext().getNext());
			} else {
				prev.setNext(curr.getNext());
				temp = curr.getData();
			}
		}

		return temp;
	}

	public Node<T> findNode(T data) {
		Node<T> curr = head.getNext();
		while (curr != null) {
			if (curr.getData().equals(data))
				return curr;
			curr = curr.getNext();
		}
		return null;
	}

	public T findData(T data) {
		Node<T> curr = head.getNext();
		while (curr != null) {
			if (curr.getData().equals(data))
				return curr.getData();
			curr = curr.getNext();
		}
		return null;
	}
}
