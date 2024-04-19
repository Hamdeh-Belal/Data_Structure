package project0;

public interface Listable<T> {
	void add(T x);

	T delete(int y);

	boolean find(T data);

	void clear();

	void print();

}