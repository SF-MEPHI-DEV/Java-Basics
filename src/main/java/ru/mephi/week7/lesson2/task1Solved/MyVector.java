package ru.mephi.week7.lesson2.task1Solved;

import java.util.Arrays;

public class MyVector {

    private int[] array;
    private int size;
    private int capacity;
    //private int maxCapacityStep = 8;

    public MyVector(int capacity) {
        this.capacity = capacity;
    }

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
    }

    public int get(int index) {
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
