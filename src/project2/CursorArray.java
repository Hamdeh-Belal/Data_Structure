package project2;

public class CursorArray<T extends Comparable<T>> {
	private CNode<T>[] cursorArray;

	@SuppressWarnings("unchecked")
	public CursorArray(int length) {
		if (length > 0)
			cursorArray = new CNode[length];
		else
			cursorArray = new CNode[101];
		initialization();
	}

	public int initialization() {
		for (int i = 0; i < cursorArray.length - 1; i++)
			cursorArray[i] = new CNode<>(null, i + 1);
		cursorArray[cursorArray.length - 1] = new CNode<>(null, 0);
		return 0;
	}

	public T getHead(int l) {
		return cursorArray[cursorArray[l].getNext()].getData();
	}

	public int malloc() {
		int p = cursorArray[0].getNext();
		cursorArray[0].setNext(cursorArray[p].getNext());
		return p;
	}

	public void free(int p) {
		cursorArray[p] = new CNode<T>(null, cursorArray[0].getNext());
		cursorArray[0].setNext(p);
	}

	public boolean isNull(int l) {
		return cursorArray[l] == null;
	}

	public boolean isEmpty(int l) {
		return cursorArray[l].getNext() == 0;
	}

	public boolean isLast(int p) {
		return cursorArray[p].getNext() == 0;
	}

	public int createList() {
		int l = malloc();
		if (l == 0)
			System.out.println("Error: Out of space!!!");
		else
			cursorArray[l] = new CNode<T>(null, 0);
		return l;
	}

	public void insertSorted(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int m = malloc();
		if (m != 0) {
			while (cursorArray[l].getNext() != 0 && cursorArray[cursorArray[l].getNext()].getData().compareTo(data) < 0) {
				l = cursorArray[l].getNext();
			}
			cursorArray[m] = new CNode<T>(data, cursorArray[l].getNext());
			cursorArray[l].setNext(m);
		}
	}

	public void insertAtHead(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			cursorArray[p] = new CNode<T>(data, cursorArray[l].getNext());
			cursorArray[l].setNext(p);
		} else
			System.out.println("Error: Out of space!!!");
	}

	public void insertAtLast(T data, int l) {
		if (isNull(l)) // list not created
			return;
		int p = malloc();
		if (p != 0) {
			while (!isNull(l) && !isEmpty(l))
				l = cursorArray[l].getNext();
		}
		cursorArray[p] = new CNode<T>(data, cursorArray[l].getNext());
		cursorArray[l].setNext(p);
	}

	public void traversList(int l) {
		System.out.print("list_" + l + "-->");
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].getNext();
			System.out.print(cursorArray[l] + "-->");
		}
		System.out.println("null");
	}

	public int find(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			l = cursorArray[l].getNext();
			if (cursorArray[l].getData().equals(data))
				return l;
		}
		return -1; // not found
	}

	public int findPrevious(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].getNext()].getData().equals(data))
				return l;
			l = cursorArray[l].getNext();
		}
		return -1; // not found
	}

	public int findPreviousSorted(T data, int l) {
		while (!isNull(l) && !isEmpty(l)) {
			if (cursorArray[cursorArray[l].getNext()].getData().compareTo(data) < 0)
				l = cursorArray[l].getNext();
			if (cursorArray[cursorArray[l].getNext()].getData().equals(data))
				return l;

		}

		return -1; // not found
	}

	public Node<T> delete(T data, int l) {
		int p = findPrevious(data, l);
		if (p != -1) {
			int c = cursorArray[p].getNext();
			CNode<T> temp = cursorArray[c];
			cursorArray[p].setNext(temp.getNext());
			free(c);
		}
		return null;
	}

	public T deleteSorted(T data, int l) {
		int p = findPreviousSorted(data, l);
		if (p != -1) {
			int c = cursorArray[p].getNext();
			CNode<T> temp = cursorArray[c];
			cursorArray[p].setNext(temp.getNext());
			free(c);
			return temp.getData();
		}
		return null;
	}

	public T deleteLast(int l) {
		int p = l;
		if (isNull(l)) // list not created
			return null;
		else {
			while (!isNull(l) && !isEmpty(l)) {
				p = l;
				l = cursorArray[l].getNext();
			}
			if (p == l)
				return null;
			T temp = cursorArray[l].getData();
			cursorArray[p].setNext(0); 
			free(l);
			return temp;
		}
	}

	public int lengthI(int l) {
		int count = 0;
		if (!isNull(l)) {
			if (isEmpty(l))
				return 1;
			else {
				while (cursorArray[l].getNext() != 0) {
					count++;
					l = cursorArray[l].getNext();

				}
			}
			return count;
		}
		return -1;
	}

	public int lengthR(int l) {
		return lengthR(l, 0);

	}

	private int lengthR(int l, int count) {
		if (isNull(l))
			return -1;
		else if (cursorArray[l].getNext() == 0)
			return count;
		else {
			l = cursorArray[l].getNext();
			return lengthR(l, ++count);
		}

	}

//	public void removeListI (int l) {
//		if(isNull(l))
//			return ;
//		while (!isNull(l) && !isEmpty(l)) {
//			int m =l;
//			l = cursorArray[l].next;
//			System.out.println(cursorArray[l].data+" l = "+l+" ");
//			free(m);
//		}			
//	}
	public void removeList(int l) {
		while (!isEmpty(l))
			deleteAtHead(l);
		free(l);
	}

	public T deleteAtHead(int l) {
		T temp = cursorArray[cursorArray[l].getNext()].getData();
		cursorArray[l].setNext( cursorArray[cursorArray[l].getNext()].getNext());
		return temp;
	}

	public int removeListR(int l) {
		if (isNull(l) || isEmpty(l))
			return 1;
		else {
			int m = l;
			l = cursorArray[l].getNext();
			free(m);
			System.out.println(cursorArray[m].getData() + " l = " + m + " ");
			return removeListR(l);
		}
	}

	/*
	 * public void traverseList (int l) { if (!isNull(l)) {
	 * System.out.print("Head --->"); while (cursorArray[l].next!=0) {
	 * l=cursorArray[l].next;
	 * System.out.print("{"+cursorArray[l].data+" , "+l+"}"+"--->");
	 * 
	 * } System.out.print("Null"); } }
	 */
}
