package mylist;

public interface MyList<E> {
    void add(E e);

    void add(int index, E e);

    E get(int index);

    E delete(int index);

    int size();

    String toString();
}
