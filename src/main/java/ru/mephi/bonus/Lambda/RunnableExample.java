package ru.mephi.bonus.Lambda;


public class RunnableExample {
    public static void main(String[] args) {
        // старый подход к функциональным интерфейса
        Runnable task = new Runnable() {
            @Override
            public void run() {
                System.out.println("Выполнение задачи в отдельном потоке");
            }
        };

        //Новый подход с лямбдами
        Runnable task_new = () -> System.out.println("Выполнение задачи в отдельном потоке");

        Thread thread = new Thread(task);
        thread.start();
    }
}
