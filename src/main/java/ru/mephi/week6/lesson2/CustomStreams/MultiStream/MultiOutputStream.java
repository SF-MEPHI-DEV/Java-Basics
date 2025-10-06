package ru.mephi.week6.lesson2.CustomStreams.MultiStream;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Декоратор для записи данных одновременно в несколько потоков
 *
 * Расширение идеи TeeOutputStream - позволяет писать в произвольное количество потоков
 */
public class MultiOutputStream extends OutputStream {

    private final List<OutputStream> streams;
    private final boolean[] closeOnExit;

    /**
     * Конструктор с массивом потоков
     *
     * @param streams массив потоков для записи
     */
    public MultiOutputStream(OutputStream... streams) {
        if (streams == null || streams.length == 0) {
            throw new IllegalArgumentException("At least one stream required");
        }

        this.streams = new ArrayList<>(Arrays.asList(streams));
        this.closeOnExit = new boolean[streams.length];
        Arrays.fill(this.closeOnExit, true);
    }

    /**
     * Конструктор с возможностью указать, какие потоки закрывать
     *
     * @param streams массив потоков
     * @param closeOnExit массив флагов закрытия (true = закрывать при close())
     */
    public MultiOutputStream(OutputStream[] streams, boolean[] closeOnExit) {
        if (streams == null || streams.length == 0) {
            throw new IllegalArgumentException("At least one stream required");
        }
        if (closeOnExit != null && closeOnExit.length != streams.length) {
            throw new IllegalArgumentException("closeOnExit array must match streams array length");
        }

        this.streams = new ArrayList<>(Arrays.asList(streams));
        this.closeOnExit = closeOnExit != null ? closeOnExit.clone() : new boolean[streams.length];

        if (closeOnExit == null) {
            Arrays.fill(this.closeOnExit, true);
        }
    }

    @Override
    public void write(int b) throws IOException {
        for (OutputStream stream : streams) {
            stream.write(b);
        }
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        for (OutputStream stream : streams) {
            stream.write(b, off, len);
        }
    }

    @Override
    public void flush() throws IOException {
        for (OutputStream stream : streams) {
            stream.flush();
        }
    }

    @Override
    public void close() throws IOException {
        IOException firstException = null;

        for (int i = 0; i < streams.size(); i++) {
            if (closeOnExit[i]) {
                try {
                    streams.get(i).close();
                } catch (IOException e) {
                    if (firstException == null) {
                        firstException = e;
                    }
                }
            }
        }

        if (firstException != null) {
            throw firstException;
        }
    }

    /**
     * Добавляет поток в список
     */
    public void addStream(OutputStream stream, boolean closeOnExit) {
        streams.add(stream);
        boolean[] newCloseFlags = new boolean[this.closeOnExit.length + 1];
        System.arraycopy(this.closeOnExit, 0, newCloseFlags, 0, this.closeOnExit.length);
        newCloseFlags[this.closeOnExit.length] = closeOnExit;
    }

    /**
     * Возвращает количество потоков
     */
    public int getStreamCount() {
        return streams.size();
    }
}
