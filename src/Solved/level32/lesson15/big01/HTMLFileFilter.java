package solved.level32.lesson15.big01;

import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * Created by igormakarychev on 12/27/15.
 */
public class HTMLFileFilter extends FileFilter
{
    @Override
    public boolean accept(File f)
    {
        return  f.isDirectory() ||
                f.getName().toLowerCase().endsWith(".htm") ||
                f.getName().toLowerCase().endsWith(".html");
    }

    @Override
    public String getDescription()
    {
        return "HTML и HTM файлы";
    }
}
