package ru.mephi.week6.lesson2.CustomStreams.CheckSum;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

/**
 * Декоратор для вычисления контрольной суммы при записи
 *
 * Полезен для:
 * - Проверки целостности данных
 * - Обнаружения повреждений
 * - Валидации записи
 */
public class ChecksumOutputStream extends OutputStream {

    private final OutputStream destination;
    private final Checksum checksum;
    private long bytesWritten = 0;

    public ChecksumOutputStream(OutputStream destination, Checksum checksum) {
        if (destination == null) {
            throw new NullPointerException("Destination stream cannot be null");
        }
        if (checksum == null) {
            throw new NullPointerException("Checksum cannot be null");
        }
        this.destination = destination;
        this.checksum = checksum;
    }

    public ChecksumOutputStream(OutputStream destination) {
        this(destination, new CRC32());
    }

    @Override
    public void write(int b) throws IOException {
        destination.write(b);
        checksum.update(b);
        bytesWritten++;
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        destination.write(b, off, len);
        checksum.update(b, off, len);
        bytesWritten += len;
    }

    @Override
    public void flush() throws IOException {
        destination.flush();
    }

    @Override
    public void close() throws IOException {
        destination.close();
    }

    /**
     * Возвращает текущую контрольную сумму
     */
    public long getChecksumValue() {
        return checksum.getValue();
    }

    /**
     * Возвращает количество записанных байтов
     */
    public long getBytesWritten() {
        return bytesWritten;
    }

    /**
     * Сбрасывает контрольную сумму
     */
    public void resetChecksum() {
        checksum.reset();
        bytesWritten = 0;
    }
}
