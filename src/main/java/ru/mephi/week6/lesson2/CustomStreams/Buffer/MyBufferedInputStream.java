package ru.mephi.week6.lesson2.CustomStreams.Buffer;

import java.io.IOException;
import java.io.InputStream;

public class MyBufferedInputStream extends InputStream {

    private final InputStream source;
    private final byte[] buffer;
    private int bufferPosition = 0;
    private int bufferSize = 0;
    private final int capacity;
    private boolean closed = false;

    private static final int DEFAULT_BUFFER_SIZE = 8192; // 8 KB по умолчанию

    /**
     * Конструктор с размером буфера по умолчанию
     */
    public MyBufferedInputStream(InputStream source) {
        this(source, DEFAULT_BUFFER_SIZE);
    }

    /**
     * Конструктор с заданным размером буфера
     */
    public MyBufferedInputStream(InputStream source, int bufferSize) {
        if (source == null) {
            throw new NullPointerException("Source stream cannot be null");
        }
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Buffer size must be positive");
        }

        this.source = source;
        this.capacity = bufferSize;
        this.buffer = new byte[bufferSize];

        System.out.println("[MyBufferedInputStream] Создан с буфером " + bufferSize + " байт");
    }

    /**
     * Читает один байт из буфера или из источника
     */
    @Override
    public int read() throws IOException {
        checkClosed();
        if (bufferPosition >= bufferSize) {
            fillBuffer();
            if (bufferSize <= 0) {
                return -1;
            }
        }

        return buffer[bufferPosition++] & 0xFF;
    }

    /**
     * Читает массив байтов (более эффективно)
     */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        checkClosed();

        if (b == null) {
            throw new NullPointerException("Buffer is null");
        }
        if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        }
        if (len == 0) {
            return 0;
        }

        int totalRead = 0;

        // Сначала читаем из буфера, если там есть данные
        if (bufferPosition < bufferSize) {
            int availableInBuffer = bufferSize - bufferPosition;
            int toReadFromBuffer = Math.min(len, availableInBuffer);

            System.arraycopy(buffer, bufferPosition, b, off, toReadFromBuffer);
            bufferPosition += toReadFromBuffer;
            totalRead += toReadFromBuffer;
            off += toReadFromBuffer;
            len -= toReadFromBuffer;
        }

        // Если запрошено больше, чем размер буфера - читаем напрямую
        if (len >= capacity) {
            int directRead = source.read(b, off, len);
            if (directRead > 0) {
                totalRead += directRead;
            }
            System.out.println("[MyBufferedInputStream] Прямое чтение: " + directRead + " байт");
            return totalRead > 0 ? totalRead : directRead;
        }

        // Если нужно ещё - заполняем буфер и читаем из него
        if (len > 0) {
            fillBuffer();
            if (bufferSize > 0) {
                int toRead = Math.min(len, bufferSize);
                System.arraycopy(buffer, 0, b, off, toRead);
                bufferPosition = toRead;
                totalRead += toRead;
            }
        }

        return totalRead > 0 ? totalRead : -1;
    }

    /**
     * Заполняет буфер данными из источника
     */
    private void fillBuffer() throws IOException {
        bufferPosition = 0;
        bufferSize = 0;

        int read = source.read(buffer, 0, capacity);
        if (read > 0) {
            bufferSize = read;
            System.out.println("[MyBufferedInputStream] Буфер заполнен: " + read + " байт");
        } else {
            System.out.println("[MyBufferedInputStream] Достигнут конец источника");
        }
    }

    /**
     * Возвращает количество доступных байтов
     */
    @Override
    public int available() throws IOException {
        checkClosed();
        int inBuffer = bufferSize - bufferPosition;
        int inSource = source.available();
        return inBuffer + inSource;
    }

    /**
     * Пропускает байты
     */
    @Override
    public long skip(long n) throws IOException {
        checkClosed();

        if (n <= 0) {
            return 0;
        }

        long skipped = 0;

        // Сначала пропускаем в буфере
        int inBuffer = bufferSize - bufferPosition;
        if (inBuffer > 0) {
            long skipInBuffer = Math.min(n, inBuffer);
            bufferPosition += (int) skipInBuffer;
            skipped += skipInBuffer;
            n -= skipInBuffer;
        }

        // Затем в источнике
        if (n > 0) {
            bufferPosition = 0;
            bufferSize = 0;
            skipped += source.skip(n);
        }

        System.out.println("[MyBufferedInputStream] Пропущено: " + skipped + " байт");
        return skipped;
    }

    /**
     * Поддержка mark/reset
     */
    @Override
    public boolean markSupported() {
        return true;
    }

    private int markPosition = -1;
    private int markLimit = 0;

    @Override
    public synchronized void mark(int readlimit) {
        markLimit = readlimit;
        markPosition = bufferPosition;
        System.out.println("[MyBufferedInputStream] Mark установлен на позиции " + markPosition);
    }

    @Override
    public synchronized void reset() throws IOException {
        checkClosed();
        if (markPosition < 0) {
            throw new IOException("Mark not set");
        }
        bufferPosition = markPosition;
        System.out.println("[MyBufferedInputStream] Позиция восстановлена: " + bufferPosition);
    }

    /**
     * Закрытие потока
     */
    @Override
    public void close() throws IOException {
        if (!closed) {
            closed = true;
            source.close(); // Закрываем оборачиваемый поток
            System.out.println("[MyBufferedInputStream] Поток закрыт");
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

    public int getBufferSize() {
        return capacity;
    }

    public int getBufferPosition() {
        return bufferPosition;
    }
}
