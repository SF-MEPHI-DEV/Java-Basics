package ru.mephi.week1.lesson1;

public class Task4 {

    /**
     * <p>Согласно статье в Википедии: Игра жизни, представляет собой клеточный автомат,
     * * разработанный британским математиком Джоном Хортоном Конвеем в 1970 году".
     * * Игровое поле состоит из сетки из {@code m x n} ячеек, где каждая ячейка имеет начальное
     * * состояние: живая (обозначается цифрой 1) или мертвая (обозначается цифрой 0).
     * * Каждая ячейка взаимодействует со своими восемью соседями (по горизонтали, вертикали, диагонали),
     * * используя следующие четыре правила (взятые из приведенной выше статьи в Википедии):</p>
     * <ul>
     * <li>Любая живая ячейка, у которой меньше двух живых соседей, умирает, как будто из-за нехватки населения.</li>
     * <li>Любая живая клетка с двумя или тремя живыми соседями продолжает жить до следующего поколения.</li>
     * <li>Любая живая клетка с более чем тремя живыми соседями умирает, как бы от перенаселения.</li>
     * <li>Любая мертвая клетка с ровно тремя живыми соседями становится живой клеткой, как бы размножаясь.</li>
     * </ul>
     * <p>Следующее состояние создается путем одновременного применения вышеуказанных правил к каждой ячейке
     * * текущего состояния, в которой одновременно происходят рождения и смерти. Учитывая текущее состояние
     * * таблицы {@code mxn}, найдите следующее состояние.</p>
     */

    public static void main(String[] args) {

        int[][] board = {
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        };

        // todo: write code

    }

}
