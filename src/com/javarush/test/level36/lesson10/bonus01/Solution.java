package com.javarush.test.level36.lesson10.bonus01;


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
    private java.util.ArrayList<Class> hiddenClasses = new java.util.ArrayList<>();
    private String packageName;
    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution("./out/production/JavaRushHomeWork/com/javarush/test/level36/lesson10/bonus01/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplse"));
        System.out.println(solution.getHiddenClassObjectByKey("hiddenclassimplf"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        java.io.File dir = new java.io.File(packageName);
        final String finalPath = dir.getAbsolutePath() + java.io.File.separator;

        String[] classFiles = dir.list();
        for (String classFile:classFiles){
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
                    catch (java.io.IOException e)
                    {
                        throw new ClassNotFoundException();
                    }
                }
            };
            String className = classFile.substring(0,classFile.lastIndexOf("."));
            Class clazz = loader.loadClass(className);

            for(Class cInterface : clazz.getInterfaces()) {
                if (cInterface.getSimpleName().equals("HiddenClass"))
                    hiddenClasses.add(clazz);
            }
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        for(Class clazz: hiddenClasses){
            if(clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())){
                try
                {
                    java.lang.reflect.Constructor[] constructors = clazz.getDeclaredConstructors();
                    for (int i = 0; i < constructors.length; i++) {
                        if (constructors[i].getParameterTypes().length == 0) {
                            constructors[i].setAccessible(true);
                            return (HiddenClass) constructors[i].newInstance();
                        }
                    }
                }
                catch (Exception e) {}
            }
        }
        return null;
    }

    public static byte[] getBytesFromFile(String path) throws java.io.IOException
    {
        java.io.File file = new java.io.File(path);
        java.io.InputStream is = new java.io.FileInputStream(file);
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
            throw new java.io.IOException("Could not completely read file "+path);
        }
        // Close the input stream and return bytes
        is.close();
        return bytes;
    }
}
