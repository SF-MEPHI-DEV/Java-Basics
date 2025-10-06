package ru.mephi.week6.lesson2.CustomStreams.Logging;

import java.io.IOException;
import java.io.Writer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Обёртка для Writer с логированием всех операций записи
 */
public class LoggingWriter extends Writer {

    private final Writer destination;
    private final String writerName;
    private long totalCharsWritten = 0;
    private final DateTimeFormatter timeFormatter;

    public LoggingWriter(Writer destination, String writerName) {
        if (destination == null) {
            throw new NullPointerException("Destination writer cannot be null");
        }
        this.destination = destination;
        this.writerName = writerName != null ? writerName : "Writer";
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        log("Создан LoggingWriter");
    }

    public LoggingWriter(Writer destination) {
        this(destination, "Writer");
    }

    /**
     * Записывает один символ
     */
    @Override
    public void write(int c) throws IOException {
        destination.write(c);
        totalCharsWritten++;

        char character = (char) c;
        String display = isPrintable(character) ? "'" + character + "'" : "\\u" + String.format("%04X", c);
        log("write(" + c + ") -> символ: " + display + ", всего записано: " + totalCharsWritten);
    }

    /**
     * Записывает массив символов
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        long startTime = System.nanoTime();
        destination.write(cbuf, off, len);
        long endTime = System.nanoTime();

        totalCharsWritten += len;
        double milliseconds = (endTime - startTime) / 1_000_000.0;

        log(String.format("write(char[%d], %d, %d) -> записано: %d символов, время: %.3f мс, всего: %d",
                cbuf.length, off, len, len, milliseconds, totalCharsWritten));

        // Показываем содержимое
        if (len <= 50) {
            String preview = new String(cbuf, off, len);
            preview = preview.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
            log("  Содержимое: \"" + preview + "\"");
        } else {
            String preview = new String(cbuf, off, 50);
            preview = preview.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
            log("  Начало: \"" + preview + "...\"");
        }
    }

    /**
     * Записывает строку
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        long startTime = System.nanoTime();
        destination.write(str, off, len);
        long endTime = System.nanoTime();

        totalCharsWritten += len;
        double milliseconds = (endTime - startTime) / 1_000_000.0;

        log(String.format("write(String, %d, %d) -> записано: %d символов, время: %.3f мс, всего: %d",
                off, len, len, milliseconds, totalCharsWritten));

        // Показываем содержимое
        if (len <= 50) {
            String preview = str.substring(off, off + len);
            preview = preview.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
            log("  Содержимое: \"" + preview + "\"");
        } else {
            String preview = str.substring(off, off + 50);
            preview = preview.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
            log("  Начало: \"" + preview + "...\"");
        }
    }

    /**
     * flush() с логированием
     */
    @Override
    public void flush() throws IOException {
        long startTime = System.nanoTime();
        destination.flush();
        long endTime = System.nanoTime();

        double milliseconds = (endTime - startTime) / 1_000_000.0;
        log(String.format("flush() завершён за %.3f мс", milliseconds));
    }

    /**
     * close() с логированием
     */
    @Override
    public void close() throws IOException {
        log("close() вызван, всего записано за время жизни: " + totalCharsWritten + " символов");
        destination.close();
        log("Writer закрыт");
    }

    private void log(String message) {
        String timestamp = LocalDateTime.now().format(timeFormatter);
        System.out.println("[" + timestamp + "] [LoggingWriter:" + writerName + "] " + message);
    }

    private boolean isPrintable(char c) {
        return c >= 32 && c < 127 || c == '\n' || c == '\r' || c == '\t';
    }

    public long getTotalCharsWritten() {
        return totalCharsWritten;
    }

    public String getWriterName() {
        return writerName;
    }
}
