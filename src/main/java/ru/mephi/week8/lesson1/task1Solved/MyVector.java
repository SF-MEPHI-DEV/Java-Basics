package ru.mephi.week8.lesson1.task1Solved;

public class MyVector<E> {

    private E[] array;
    private int size;
    private int capacity;

    public MyVector() {
        capacity = 10;
        array = (E[]) new Object[capacity];
        size = 0;
    }

    public void add(E element) {
        if (size == capacity) {
            grow();
        }
        array[size++] = element;
    }

    private void grow() {
        capacity *= 2;
        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        // array = Arrays.copyOf(array, capacity);
    }

    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        return array[index];
    }

    public void remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(array[i]);
            if (i != size - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }
}
