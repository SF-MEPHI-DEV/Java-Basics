package ru.mephi.week8.lesson1.task3Solved;

class Task {
    private int id;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Task{id=" + id + "}";
    }
}
