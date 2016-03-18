package com.javarush.test.level33.lesson15.big01.strategies;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by Igor_Makarychev on 15.01.2016.
 */
public class FileBucket
{
    private Path path;

    public FileBucket()
    {
        try
        {
            this.path = Files.createTempFile(null, null);
            File file;

            if (!Files.exists(path)) file = new File(Files.createFile(path).toString());
            else file = new File(path.toString());

            file.deleteOnExit();
        }
        catch (IOException e)
        {
            //e.printStackTrace();
        }
    }

    public long getFileSize() {
        try
        {
            return Files.size(path);
        }
        catch (IOException e)
        {
            //e.printStackTrace();
        }

        return -1L;
    }

    public void putEntry(Entry entry) {
        try (
                OutputStream file = new FileOutputStream(path.toString());
                OutputStream buffer = new BufferedOutputStream(file);
                ObjectOutput output = new ObjectOutputStream(buffer);
        ){
            while (entry != null)
            {
                output.writeObject(entry);
                entry = entry.next;
            }
        }
        catch(IOException ex){
            //ex.printStackTrace();
        }
    }

    public Entry getEntry() {

        Entry e = null;
        try(
                InputStream file = new FileInputStream(path.toString());
                InputStream buffer = new BufferedInputStream(file);
                ObjectInput input = new ObjectInputStream (buffer);
        ){
            if (Files.size(path) == 0) return null;
            e = (Entry) input.readObject();
            Entry subE = e.next;
            while (subE != null)
            {
                subE = (Entry) input.readObject();
                subE = subE.next;
            }

        }
        catch(ClassNotFoundException ex){
            //ex.printStackTrace();
        }
        catch(IOException io){
            //io.printStackTrace();
        }

        return e;
    }

    public void remove() {
        try
        {
            Files.delete(path);
        }
        catch (IOException e)
        {
            //e.printStackTrace();
        }
    }
}
