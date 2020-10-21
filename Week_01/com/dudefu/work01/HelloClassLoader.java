package dudefu.work01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class HelloClassLoader extends ClassLoader {

    public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {

        HelloClassLoader helloClassLoader = new HelloClassLoader();
        Class helloClass = helloClassLoader.findClass("Hello");
        Object hello = helloClass.newInstance();
        Method method = helloClass.getDeclaredMethod("hello");
        method.invoke(hello);
    }

    /**
     * 解析字节码得到class文件
     * @param name
     * @return
     */
    protected Class<?> findClass(String name) {

        byte[] bytes;
        byte[] bytesNew = new byte[0];

        try {

            File file = new File("G:\\IdeaWorkSpace\\JAVA-000\\Week_01\\com\\resources\\Hello.xlass");
            RandomAccessFile raf = new RandomAccessFile(file, "r");
            bytes = new byte[(int) raf.length()];
            raf.read(bytes);
            bytesNew = new byte[bytes.length];
            for (int i = 0; i < bytesNew.length; i++) {
                bytesNew[i] = (byte) (255 - bytes[i]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return super.defineClass(name, bytesNew, 0, bytesNew.length);
    }

}
