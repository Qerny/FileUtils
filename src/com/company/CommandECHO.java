package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Создает в текущей директории текстовый файл с указанным именем и
//содержимым. В случае, если файл уже существует, печатает ошибку в
//stderr и возвращается с ненулевым статус кодом.
public class CommandECHO extends Command {

    private String nameFile;
    private String text;
    private String curDir;

    public CommandECHO(String valueCurDir)
    {
        curDir = valueCurDir;
        isRequiredKeys = true;
    }

    //создает новый текстовый файл, если такой существует - выводит ошибку
    private void CreateNewTextFile(String text, String name)
    {
        File newFile = new File(curDir + "\\" + name);
        try {
            if(!newFile.exists())
            {
                newFile.createNewFile();

                FileWriter fw = new FileWriter(newFile);
                fw.write(text);
                fw.close();
                System.out.println("Файл успешно создан и заполнен");
            }
            else
                System.err.println("Файл с таким именем уже существует");
        }
        catch(IOException e)
        {
            System.err.println(e.getMessage());
        }
    }

    //проверяем ключи на корректность и ставим соответствующие флажки
    public boolean setKeys(String[] strKeys)
    {
        if(strKeys.length>2 && strKeys[2]==null && strKeys[0]!=null && strKeys[1]!=null)
        {
            text = strKeys[0];
            nameFile = strKeys[1];
            return true;
        }
        else
            return false;
    }

    //запускаем команду
    public void run()
    {
        CreateNewTextFile(text,nameFile);
    }
}
