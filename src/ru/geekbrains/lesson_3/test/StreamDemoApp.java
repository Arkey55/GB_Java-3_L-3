package ru.geekbrains.lesson_3.test;

import java.io.*;
import java.util.Arrays;

public class StreamDemoApp {
    public static void main(String[] args) {
        createFile();
        File file = new File("out/production/GB_Java-3_L-3/ru/geekbrains/lesson_3/test/demo.txt");
//        byteInputStream();
//        byteOutputStream();

//        fileOutputStream(file);
//        fileOutputStream2(file);
//        fileInputStream(file);
//        bufInOutStream(file);
//        dataInOutStream(file);
    }
    private static void byteInputStream (){
        byte[] arr = {0, 127, -1};
        ByteArrayInputStream in = new ByteArrayInputStream(arr);
        int x;
        while ((x = in.read()) != -1){
            System.out.print(x + " ");
        }
    }

    private static void byteOutputStream(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        out.write(10);
        out.write(11);
        byte[] arr = out.toByteArray();
        System.out.println(Arrays.toString(arr));
    }

    private static void fileOutputStream(File file){
        byte[] outData = "Java".getBytes();
        try(FileOutputStream out = new FileOutputStream(file, true)){
            out.write(outData);
        } catch (IOException e){
            throw new RuntimeException("SWW", e);
        }
    }

    private static void fileOutputStream2(File file){
        byte[] outData = new byte[1024 * 1024];
        for (int i = 0; i < outData.length; i++) {
            outData[i] = 65;
        }
        try(FileOutputStream out = new FileOutputStream(file,true)){
            for (int i = 0; i < outData.length; i++) {
                out.write(outData[i]);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void fileInputStream(File file){
        byte[] buf = new byte[20];
        try(FileInputStream in = new FileInputStream(file)){
            int count;
            while ((count = in.read()) > 0){
                for (int i = 0; i < count; i++) {
                    System.out.println((char) buf[i]);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void bufInOutStream(File file){
        try(OutputStream out = new BufferedOutputStream(
                new FileOutputStream(file)
        )){
            for (int i = 0; i < 1000; i++) {
                out.write(i);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        try(InputStream in = new BufferedInputStream(
                new FileInputStream(file)
        )){
            int x;
            while ((x = in.read()) != -1){
                System.out.println((char) x);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void dataInOutStream (File file){
        try (DataOutputStream out = new DataOutputStream(
                new FileOutputStream(file)
        )){
            out.writeInt(128);
            out.writeLong(128L);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (DataInputStream in = new DataInputStream(new FileInputStream("demo.txt"))) {
            System.out.println(in.readInt());
            System.out.println(in.readLong());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private static void createFile(){
        try {
            File newFile = new File("demo.txt");
            newFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
