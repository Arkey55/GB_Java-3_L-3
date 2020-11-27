package ru.geekbrains.lesson_3.files;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    public void createFile(){
        File f = new File("out/production/GB_Java-3_L-3/ru/geekbrains/lesson_3/files/test_"+ this.getClass().getSimpleName() + ".txt");
        if(!f.exists()){
            try {
//                f.mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
