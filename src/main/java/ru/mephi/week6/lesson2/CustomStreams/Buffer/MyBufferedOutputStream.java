package ru.mephi.week6.lesson2.CustomStreams.Buffer;

import java.io.IOException;
import java.io.OutputStream;

public class MyBufferedOutputStream extends OutputStream {

    private final OutputStream destination; // Оборачиваемый поток (куда пишем)
    private final byte[] buffer;            // Внутренний буфер
    private int position = 0;               // Текущая позиция в буфере
    private final int capacity;             // Размер буфера
    private boolean closed = false;         // Закрыт ли поток

    private static final int DEFAULT_BUFFER_SIZE = 8192; // 8 KB по умолчанию

    /**
     * Конструктор с размером буфера по умолчанию
     */
    public MyBufferedOutputStream(OutputStream destination) {
        this(destination, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Конструктор с заданным размером буфера
     */
    public MyBufferedOutputStream(OutputStream destination, int bufferSize) {
        if (destination == null) {
            throw new NullPointerException("Destination stream cannot be null");
        }
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Buffer size must be positive");
        }

        this.destination = destination;
        this.capacity = bufferSize;
        this.buffer = new byte[bufferSize];

        System.out.println("[MyBufferedOutputStream] Создан с буфером " + bufferSize + " байт");
    }

    /**
     * Записывает один байт в буфер
     */
    @Override
    public void write(int b) throws IOException {
        checkClosed();

        // Если буфер заполнен - сбрасываем его
        if (position >= capacity) {
            flushBuffer();
        }

        // Записываем байт в буфер
        buffer[position++] = (byte) b;
    }

    /**
     * Записывает массив байтов
     */
    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        checkClosed();

        if (b == null) {
            throw new NullPointerException("Buffer is null");
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return;
        }

        // Если данных больше, чем размер буфера - пишем напрямую
        if (len >= capacity) {
            flushBuffer(); // Сначала сбрасываем текущий буфер
            destination.write(b, off, len); // Затем пишем напрямую
            System.out.println("[MyBufferedOutputStream] Прямая запись: " + len + " байт");
            return;
        }

        // Если данные не влезают в текущий буфер - сбрасываем
        if (len > capacity - position) {
            flushBuffer();
        }

        // Копируем данные в буфер
        System.arraycopy(b, off, buffer, position, len);
        position += len;
    }

    /**
     * Сбрасывает буфер в назначение
     * ВАЖНО: этот метод должен вызываться перед close()!
     */
    @Override
    public void flush() throws IOException {
        checkClosed();
        flushBuffer();
        destination.flush(); // Также сбрасываем вложенный поток
        System.out.println("[MyBufferedOutputStream] flush() завершён");
    }

    /**
     * Внутренний метод для сброса буфера
     */
    private void flushBuffer() throws IOException {
        if (position > 0) {
            destination.write(buffer, 0, position);
            System.out.println("[MyBufferedOutputStream] Буфер сброшен: " + position + " байт");
            position = 0;
        }
    }

    /**
     * Закрытие потока
     * ВАЖНО: автоматически вызывает flush()
     */
    @Override
    public void close() throws IOException {
        if (!closed) {
            try {
                flush(); // Обязательно сбрасываем буфер перед закрытием!
            } finally {
                closed = true;
                destination.close(); // Закрываем вложенный поток
                System.out.println("[MyBufferedOutputStream] Поток закрыт");
            }
        }
    }

    /**
     * Проверка, не закрыт ли поток
     */
    private void checkClosed() throws IOException {
        if (closed) {
            throw new IOException("Stream closed");
        }
    }

    /**
     * Статистика для отладки
     */
    public int getBufferSize() {
        return capacity;
    }

    public int getBufferPosition() {
        return position;
    }

    /**
     * Количество байтов в буфере, ожидающих записи
     */
    public int getBufferedCount() {
        return position;
    }
}
