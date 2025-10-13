package ru.mephi.week7.lesson2.task1;

public class Main {

    /**
     * <h2>Задача: реализация динамического массива MyVector</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Необходимо реализовать собственную версию динамического массива для хранения целых чисел (int).
     * Класс должен автоматически увеличивать свою вместимость при заполнении.</p>
     * <br>
     * <h2>Требования:</h2>
     * <ul>
     *     <li>Создать класс MyVector с внутренним массивом int[]</li>
     *     <li>Начальная вместимость (capacity): 10 элементов</li>
     *     <li>При заполнении массива увеличивать его размер в 2 раза</li>
     *     <li>Реализовать методы:
     *         <ul>
     *             <li>add(int element) - добавление элемента в конец</li>
     *             <li>get(int index) - получение элемента по индексу</li>
     *             <li>remove(int index) - удаление элемента по индексу</li>
     *             <li>size() - получение текущего количества элементов</li>
     *             <li>toString() - строковое представление списка</li>
     *         </ul>
     *     </li>
     *     <li>Обрабатывать выход за границы массива (IndexOutOfBoundsException)</li>
     *     <li>Использовать System.arraycopy() или Arrays.copyOf() для копирования массива</li>
     * </ul>
     * <br>
     * <h2>Пример использования:</h2>
     * <pre>
     * MyVector vector = new MyVector();
     * vector.add(10);
     * vector.add(20);
     * vector.add(30);
     * System.out.println(vector);  // 10, 20, 30
     * vector.remove(1);
     * System.out.println(vector);  // 10, 30
     * </pre>
     */

    public static void main(String[] args) {

    }

}
