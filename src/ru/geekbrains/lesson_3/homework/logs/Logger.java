package ru.geekbrains.lesson_3.homework.logs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Logger {
    File file;
    public Logger(File file) {
        this.file = file;
    }

    public void writeToLog(String msg){
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, true)
        )) {
            writer.write(msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFromLog(){
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            List<String> list = new ArrayList<>();
            String str;
            while ((str = reader.readLine()) != null){
                list.add(str);
            }
            if (list.size() > 100){
                for (int i = list.size() - 100; i < list.size(); i++) {
                    System.out.println(list.get(i));
                }
            } else {
                for (String s : list){
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
