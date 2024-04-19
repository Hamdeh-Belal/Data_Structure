package project2;

public class DoublyLinkedList<T extends Comparable<T>> {
	private DNode<T> head;

	public DoublyLinkedList() {
		head = new DNode<>(null);
	}

	public void printAll_1() {
		System.out.print("Head -- >");

		DNode<T> curr = head;
		while (curr != null) {
			System.out.print(curr.toString() + "--->");
			curr = curr.getNext();
		}

		System.out.println("Null");
	}

	public void printAll() {
		printAll(head);

	}

	private void printAll(DNode<T> curr) {
		if (curr == head) {
			System.out.print("Head -->" + curr.toString() + "-->");
			printAll(curr = curr.getNext());
		} else if (curr == null)
			System.out.print("  Null  ");
		else {
			System.out.print(curr.toString() + "-->");
			printAll(curr = curr.getNext());
		}

	}

	public DNode<T> getHead() {
		return head;
	}

	public void setHead(DNode<T> head) {
		this.head = head;
	}

	public void insertAtHead(T data) {
		DNode<T> n = new DNode<T>(data);
		if (head.getNext() == null) {
			head.setNext(n);
			n.setPrev(head);

		} else {
			head.getNext().setPrev(n);
			n.setNext(head.getNext());
			n.setPrev(head);
			head.setNext(n);
		}
	}

	public void InsertAtLast(T data) {
		DNode<T> n = new DNode<T>(data);
		DNode<T> curr = head.getNext();
		while (curr != null && curr.getNext() != null) {
			curr = curr.getNext();
		}
		n.setPrev(curr);
		curr.setNext(n);

	}

	public int length() {
		if (head.getNext() == null)
			return 0;
		else {
			DNode<T> curr = head.getNext();
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

	private int lengthR1(DNode<T> curr) {
		if (curr == null)
			return 0;
		else
			return 1 + lengthR1(curr.getNext());

	}

	private int lengthR2Tail(DNode<T> curr, int sum) {
		if (curr == null)
			return sum;
		else
			return lengthR2Tail(curr.getNext(), ++sum);

	}

	public void insertSortedB(T data) {
		DNode<T> n = new DNode<T>(data);
		if (head.getNext() == null) // at the begin
			insertAtHead(data);
		else {
			DNode<T> curr = head.getNext();
			while (curr != null && (curr.getData().compareTo(n.getData()) < 0)) {
				if (curr.getNext() != null)
					curr = curr.getNext();
				else
					break;
			} // end of while loop !!!
			if (curr.getNext() == null) { // at the end of list incloud after head
				curr.setNext(n);
				n.setPrev(curr);
			}

			else { // between !
				curr.getNext().setPrev(n);
				n.setNext(curr.getNext());
				n.setPrev(curr);
				curr.setNext(n);
			}

		}
	}

	public void insertSorted(T data) {
		DNode<T> n = new DNode<T>(data);
		DNode<T> curr = head;
		while (curr.getNext() != null && curr.getNext().getData().compareTo(data) < 0)
			curr = curr.getNext();

		if (curr.getNext() == null) {
			n.setPrev(curr);
			curr.setNext(n);
		} else {
			curr.getNext().setPrev(n);
			n.setNext(curr.getNext());
			n.setPrev(curr);
			curr.setNext(n);

		}
	}

	public T deleteSorted(T data) {
		T temp = null;
		DNode<T> curr = head.getNext();
		while (curr != null && curr.getData().compareTo(data) < 0)
			curr = curr.getNext();

		if (curr != null && curr.getData().equals(data)) {
			temp = curr.getData();
			if (curr.getNext() == null)
				curr.getPrev().setNext(null);
			else {
				curr.getPrev().setNext(curr.getNext());
				curr.getNext().setPrev(curr.getPrev());
			}
		}

		return temp;
	}

	public DNode<T> findNode(T data) {
		DNode<T> curr = head.getNext();
		while (curr != null) {
			if (curr.getData().equals(data))
				return curr;
			curr = curr.getNext();
		}
		return null;
	}

	public T findData(T data) {
		DNode<T> curr = head.getNext();
		while (curr != null) {
			if (curr.getData().equals(data))
				return curr.getData();
			curr = curr.getNext();
		}
		return null;
	}

	public boolean findNodeB(T data) {
		DNode<T> curr = head.getNext();
		while (curr != null) {
			if (curr.getData().equals(data))
				return true;
			curr = curr.getNext();
		}
		return false;
	}

	@Override
	public String toString() {
		String s = "Head-->";
		DNode<T> curr = head.getNext();
		while (curr != null) {
			s += curr.getData() + "-->";
			curr = curr.getNext();
		}
		return s + "Null";
	}

}
