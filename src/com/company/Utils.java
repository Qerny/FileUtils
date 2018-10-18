package com.company;

import java.io.*;
import java.util.*;

//Класс для работы с файлами
public class Utils implements IOperations {

    //константа, текущее расположение (в консоли ее не будет)
    private final static String myFolder = "D:\\Me\\Java\\FileUtility\\Test";

    //выводит отсортированный по имени список директорий и файлов в текущей директории (revOrder == true - по убываанию, false - по возрастанию)
    public void PrintNamesDirAndFiles(boolean revOrder)
    {
        File initFolder = new File(myFolder);
        File[] files = initFolder.listFiles();
        List<File> lst = Arrays.asList(files);

        if(revOrder)
            Collections.sort(lst, Collections.reverseOrder());
        else
            Collections.sort(lst);

        for(int i=0; i<lst.size();i++)
            System.out.println(lst.get(i).getName());
    }

    //создает новую директорию с именем name, если такая существует - выводит ошибку
    public void CreateNewDir(String name)
    {
        File newDir = new File(myFolder + "\\" + name);
        if(!newDir.exists())
            newDir.mkdir();
        else
            System.err.println("Директория с таким именем уже существует");
    }

    //создает новый текстовый файл, если такой существует - выводит ошибку
    public void CreateNewTextFile(String text, String name)
    {
        File newFile = new File(myFolder + "\\" + name);
        try {
            if(!newFile.exists())
            {
                newFile.createNewFile();

                FileWriter fw = new FileWriter(newFile);
                fw.write(text);
                fw.close();
            }
            else
                System.err.println("Файл с таким именем уже существует");
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    //выводит содержимое текстовых файлов
    public void PrintTextFile(String ... names)
    {
        try
        {
            for(int i = 0; i<names.length; i++)
            {
                BufferedReader reader = new BufferedReader(new FileReader(myFolder + "\\" + names[i]));
                String line;
                while ((line = reader.readLine()) != null)
                    System.out.println(line);

                if (i < names.length-1)
                    System.out.println();
            }
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }
}