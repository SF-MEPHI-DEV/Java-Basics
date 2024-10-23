package ru.mephi.week8.lesson1.task1;

public class MyVector {

    private int size;
    private int capacity;
    private int[] array;

    public MyVector() {
        capacity = 10;
        array = new int[capacity];
        size = 0;
    }

    public void add(int element) {
        if (size == capacity) {
            grow();
        }
        array[size++] = element;
    }

    private void grow() {
        capacity *= 2;
        int[] newArray = new int[capacity];
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
        // array = Arrays.copyOf(array, capacity);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return array[index];
    }

    public int size() {
        return size;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size -= 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(array[i]);
            if (i != size - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}

