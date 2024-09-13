package ru.mephi.week3.lesson1;

import java.util.Arrays;

public class Task4Solved {

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
        // 0 - dead
        // 1 - alive
        // 2 - going to be alive on next step but now is dead
        // 3 - going to be dead on next step but now is alive

        for (int i = 0; i < board.length; i++) {
            for (int q = 0; q < board[i].length; q++) {

                int bufValue;
                int count = 0;
                // above row
                if (i > 0) {
                    if (q > 0) count += board[i - 1][q - 1] % 2;
                    count += board[i - 1][q] % 2;
                    if (q < board[i - 1].length - 1) count += board[i - 1][q + 1] % 2;
                }
                // present row
                if (q > 0) count += board[i][q - 1] % 2;
                if (q < board[i].length - 1) count += board[i][q + 1] % 2;
                // down row
                if (i < board.length - 1) {
                    if (q > 0) count += board[i + 1][q - 1] % 2;
                    count += board[i + 1][q] % 2;
                    if (q < board[i + 1].length - 1) count += board[i + 1][q + 1] % 2;
                }

                int nextState = 0;
                if (count < 2 || count > 3) nextState = 0;
                else if (board[i][q] == 1 && (count == 2 || count == 3)) nextState = 1;
                else if (board[i][q] == 0 && count == 3) nextState = 1;

                if (board[i][q] == 0 && nextState == 0) bufValue = 0;
                else if (board[i][q] == 1 && nextState == 1) bufValue = 1;
                else if (board[i][q] == 0 && nextState == 1) bufValue = 2;
                else bufValue = 3;


                board[i][q] = bufValue;

            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int q = 0; q < board[i].length; q++) {
                board[i][q] = (board[i][q] == 0 || board[i][q] == 3) ? 0 : 1;
            }
        }

        for (int i = 0; i < board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
        }

    }

}
