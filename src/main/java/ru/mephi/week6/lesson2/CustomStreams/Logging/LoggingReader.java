package ru.mephi.week6.lesson2.CustomStreams.Logging;

import java.io.IOException;
import java.io.Reader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Обёртка для Reader с логированием всех операций чтения
 */
public class LoggingReader extends Reader {

    private final Reader source;
    private final String readerName;
    private long totalCharsRead = 0;
    private final DateTimeFormatter timeFormatter;

    public LoggingReader(Reader source, String readerName) {
        if (source == null) {
            throw new NullPointerException("Source reader cannot be null");
        }
        this.source = source;
        this.readerName = readerName != null ? readerName : "Reader";
        this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");
        log("Создан LoggingReader");
    }

    public LoggingReader(Reader source) {
        this(source, "Reader");
    }

    /**
     * Читает один символ
     */
    @Override
    public int read() throws IOException {
        int ch = source.read();

        if (ch != -1) {
            totalCharsRead++;
            char character = (char) ch;
            String display = isPrintable(character) ? "'" + character + "'" : "\\u" + String.format("%04X", ch);
            log("read() -> символ: " + display + " (код: " + ch + "), всего прочитано: " + totalCharsRead);
        } else {
            log("read() -> конец потока (EOF)");
        }

        return ch;
    }

    /**
     * Читает массив символов
     */
    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        long startTime = System.nanoTime();
        int charsRead = source.read(cbuf, off, len);
        long endTime = System.nanoTime();

        if (charsRead > 0) {
            totalCharsRead += charsRead;
            double milliseconds = (endTime - startTime) / 1_000_000.0;

            log(String.format("read(char[%d], %d, %d) -> прочитано: %d символов, время: %.3f мс, всего: %d",
                    cbuf.length, off, len, charsRead, milliseconds, totalCharsRead));

            // Показываем первые символы
            if (charsRead <= 50) {
                String preview = new String(cbuf, off, charsRead);
                // Заменяем переводы строк для читаемости
                preview = preview.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
                log("  Содержимое: \"" + preview + "\"");
            } else {
                String preview = new String(cbuf, off, 50);
                preview = preview.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
                log("  Начало: \"" + preview + "...\"");
            }
        } else if (charsRead == -1) {
            log(String.format("read(char[%d], %d, %d) -> конец потока", cbuf.length, off, len));
        }

        return charsRead;
    }

    /**
     * skip() с логированием
     */
    @Override
    public long skip(long n) throws IOException {
        long skipped = source.skip(n);
        if (skipped > 0) {
            totalCharsRead += skipped;
            log("skip(" + n + ") -> пропущено: " + skipped + " символов");
        }
        return skipped;
    }

    /**
     * ready() с логированием
     */
    @Override
    public boolean ready() throws IOException {
        boolean isReady = source.ready();
        log("ready() -> " + isReady);
        return isReady;
    }

    /**
     * markSupported() с логированием
     */
    @Override
    public boolean markSupported() {
        boolean supported = source.markSupported();
        log("markSupported() -> " + supported);
        return supported;
    }

    /**
     * mark() с логированием
     */
    @Override
    public void mark(int readAheadLimit) throws IOException {
        source.mark(readAheadLimit);
        log("mark(" + readAheadLimit + ") установлен на позиции " + totalCharsRead);
    }

    /**
     * reset() с логированием
     */
    @Override
    public void reset() throws IOException {
        long beforeReset = totalCharsRead;
        source.reset();
        log("reset() вызван (позиция была " + beforeReset + ")");
    }

    /**
     * close() с логированием
     */
    @Override
    public void close() throws IOException {
        log("close() вызван, всего прочитано за время жизни: " + totalCharsRead + " символов");
        source.close();
        log("Reader закрыт");
    }

    private void log(String message) {
        String timestamp = LocalDateTime.now().format(timeFormatter);
        System.out.println("[" + timestamp + "] [LoggingReader:" + readerName + "] " + message);
    }

    private boolean isPrintable(char c) {
        return c >= 32 && c < 127 || c == '\n' || c == '\r' || c == '\t';
    }

    public long getTotalCharsRead() {
        return totalCharsRead;
    }

    public String getReaderName() {
        return readerName;
    }
}
