package com.javarush.test.level36.lesson10.bonus01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/* Осваиваем ClassLoader и Reflection
Аргументом для класса Solution является абсолютный путь к пакету,
например, "C:\JavaRushHomeWork\src\com\javarush\test\level36\lesson10\bonus01\data\second".
Имя пакета может содержать File.separator.
В этом пакете находятся только скомпилированные классы.
Известно, что каждый класс имеет конструктор без параметров и реализует интерфейс HiddenClass.
Считайте все классы с файловой системы, создайте фабрику - реализуйте метод getHiddenClassObjectByKey.
Известно, что есть только один класс, простое имя которого начинается с String key без учета регистра.
*/
public class Solution {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("/Users/igormakarychev/Documents/Java projects/Java-JavaRushHomeWork/src/com/javarush/test/level36/lesson10/bonus01/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        String dirrectory =packageName.replaceAll("[/\\\\]",File.separator);
        File dir = new File(dirrectory);
        String[] classFiles = dir.list();
        for (String classFile:classFiles){
            final String finalPath = dirrectory+File.separator;
            ClassLoader loader = new ClassLoader()
            {
                @Override
                protected Class<?> findClass(String name) throws ClassNotFoundException
                {
                    try
                    {
                        byte[] temp = getBytesFromFile(finalPath+name+".class");
                        return defineClass(null,temp,0,temp.length);
                    }
                    catch (IOException e)
                    {
                        throw new ClassNotFoundException();
                    }
                }
            };
            String className = classFile.substring(0,classFile.lastIndexOf("."));
            Class clazz = loader.loadClass(className);
            if (clazz.isInstance(HiddenClass.class))
                hiddenClasses.add(clazz);
        }

    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for(Class clazz: hiddenClasses){
            if(clazz.getSimpleName().toLowerCase().startsWith(key)){
                try
                {
                    Constructor[] constructors = clazz.getDeclaredConstructors();
                    constructors[0].setAccessible(true);
                    return (HiddenClass) constructors[0].newInstance(null);
                }
                catch (InstantiationException e) {}
                catch (IllegalAccessException e) {}
                catch (InvocationTargetException e) {}
            }
        }
        return null;
    }

    public static byte[] getBytesFromFile(String path) throws IOException
    {
        File file = new File(path);
        InputStream is = new FileInputStream(file);
        // Get the size of the file
        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];
        // Read in the bytes
        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length
                && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }
        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+path);
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
