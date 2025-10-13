package ru.mephi.week7.lesson2.task2;

public class Task2 {

    /**
     * <h2>Задача: реализация односвязного списка MyList</h2>
     * <br>
     * <h2>Описание:</h2>
     * <p>Необходимо реализовать односвязный список (linked list) для хранения целых чисел.
     * В отличие от массива, каждый элемент хранит ссылку на следующий элемент.</p>
     * <br>
     * <h2>Требования:</h2>
     * <ul>
     *     <li>Создать класс Node с полями:
     *         <ul>
     *             <li>int data - значение узла</li>
     *             <li>Node next - ссылка на следующий узел</li>
     *         </ul>
     *     </li>
     *     <li>Создать класс MyList с полями:
     *         <ul>
     *             <li>Node head - первый узел списка</li>
     *             <li>int size - количество элементов</li>
     *         </ul>
     *     </li>
     *     <li>Реализовать методы:
     *         <ul>
     *             <li>add(int data) - добавление элемента в конец</li>
     *             <li>get(int index) - получение элемента по индексу</li>
     *             <li>remove(int index) - удаление элемента по индексу</li>
     *             <li>size() - получение размера списка</li>
     *         </ul>
     *     </li>
     *     <li>Обрабатывать выход за границы списка (IndexOutOfBoundsException)</li>
     *     <li>Правильно обновлять ссылки при удалении элементов</li>
     * </ul>
     * <br>
     * <h2>Пример использования:</h2>
     * <pre>
     * MyList list = new MyList();
     * list.add(10);
     * list.add(20);
     * list.add(30);
     * System.out.println(list.get(1));  // 20
     * list.remove(1);
     * System.out.println(list.get(1));  // 30
     * </pre>
     * <br>
     * <h2>Подсказка:</h2>
     * <p>Для удаления элемента нужно перенаправить ссылку предыдущего узла на следующий за удаляемым.</p>
     */

    public static void main(String[] args) {

    }

}
