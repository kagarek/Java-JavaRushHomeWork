package solved.level19.lesson10.bonus02;

/* Свой FileWriter
Реализовать логику FileConsoleWriter
Должен наследоваться от FileWriter
При записи данных в файл, должен дублировать эти данные на консоль
*/

import java.io.FileWriter;
import java.io.IOException;

public class FileConsoleWriter extends FileWriter
{

    private FileWriter fileWriter;

    public FileConsoleWriter(String filename) throws IOException
    {
        super(filename);
        this.fileWriter = new FileWriter(filename);
    }

    public void write(int c) throws IOException
    {
        System.out.println((char)c);
        fileWriter.write(c);
        fileWriter.flush();
    };

    public void write(char cbuf[]) throws IOException
    {
        System.out.println(cbuf);

        fileWriter.write(cbuf);
        fileWriter.flush();
    };

    public void write(char cbuf[], int off, int len) throws IOException
    {
        System.out.println(String.valueOf(cbuf).substring(off, off + len));

        fileWriter.write(cbuf, off, len);
        fileWriter.flush();
    };

    public void write(String str) throws IOException
    {
        System.out.println(str);

        fileWriter.write(str);
        fileWriter.flush();
    };

    public void write(String str, int off, int len) throws IOException
    {
        System.out.println(str.substring(off,off+len));

        fileWriter.write(str, off, len);
        fileWriter.flush();
    };

    public void flush() throws IOException {
        fileWriter.flush();
    }

    public void close() throws IOException {
        fileWriter.close();
    }

    public String getEncoding() {
        return fileWriter.getEncoding();
    }
}
