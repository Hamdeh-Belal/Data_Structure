package project2;

public class CStack<T extends Comparable<T>> implements StackInterface<T> {
	CursorArray<T> cursorArray;
	int list;

	public CStack(int length) {
		super();
		if (length > 0)
			cursorArray = new CursorArray<>(length);
		else
			cursorArray = new CursorArray<>(100);

		list = cursorArray.createList();
	}

	@Override
	public void push(T data) {
		if (data != null)
			cursorArray.insertAtHead(data, list);

	}

	@Override
	public T pop() {
		return cursorArray.deleteAtHead(list);

	}

	@Override
	public T peek() {
		return cursorArray.getHead(list);
	}

	@Override
	public boolean isEmpty() {
		return cursorArray.isEmpty(list);
	}

	@Override
	public void claer() {
		cursorArray.removeList(list);
		this.cursorArray = new CursorArray<>(100);
		this.list = cursorArray.createList();

	}

	public void traversStac() {
		cursorArray.traversList(list);
	}

}
