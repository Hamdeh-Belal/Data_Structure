package project0;

public class MyList<T> implements Listable<T> {

	private T[] array;
	private int index = 0;

	public MyList(int size) {
		array = (T[]) new Object[size];

	}

	@Override
	public void add(T x) {
		if (index < array.length)
			array[index++] = x;

		else {
			replace();
			if (index < array.length)
				array[index++] = x;
			else
				System.out.println("index out of range!!");
		}

	}

	@Override
	public T delete(int y) {

		if (y < index) {
			T temp = array[y];
			for (int i = index + 1; i < index; i++)
				array[i - 1] = array[i];
			index--;
			return temp;

		} else
			System.out.println("error !");
		return null;
	}

	@Override
	public boolean find(T data) {
		for (int i = 0; i < index; i++)
			if (array.equals(array[i]))
				return true;
		return false;
	}

	@Override
	public void clear() {
		array = (T[]) new Object[array.length];
		index = 0;

	}

	@Override
	public void print() {
		for (int i = 0; i < index; i++) {
			System.out.println(array[i]);
		}

	}

	private void replace() {
		T[] temp = (T[]) new Object[(array.length) * 2];
		for (int i = 0; i < array.length; i++) {
			temp[i] = array[i];
		}
		this.array = temp;
	}

	public int getSize() {
		return index;
	}

	public T get(int count) {
		if (count < index)
			return array[count];
		return null;
	}
}
