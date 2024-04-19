package project2;

public class CircularQueue<T extends Comparable<T>> implements QInterface<T> {

	private int front;
	private int back;
	private int count;
	private T[] list;

	@SuppressWarnings("unchecked")
	public CircularQueue(int length) {
		super();
		front = -1;
		back = -1;
		count = 0;
		if (length > 0)
			list = (T[]) new Comparable[length];
		else
			list = (T[]) new Comparable[200];

	}

	@Override
	public void enqueue(T data) {
		if (count < list.length) { // free
			back = (++back) % list.length;
			list[back] = data;
			++count;
			if (count == 1) // first item
				front = back;
		} else
			System.out.println("out of space !!!!");

	}

	@Override
	public T dequeue() {
		if (!isEmpty()) {// not empty
			T temp = list[front];
			front = (++front) % list.length;
			--count;
			if (count == 0) // empty
				front = back = -1;
			return temp;
		}
		System.out.println("List is empty !!!!");
		return null;
	}

	@Override
	public T getFront() {
		return list[front];
	}

	@Override
	public boolean isEmpty() {
		return count == 0;
	}

	@Override
	public void clear() {
		count = 0;
		front = -1;
		back = -1;
	}

	public void traversList() {
		if (front != -1) {
			for (int i = front; i <= back; i++) {
				System.out.print(list[i].toString() + "-->");
			}
		}
	}
}
