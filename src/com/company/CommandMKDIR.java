package com.company;

import java.io.File;

//Создает в текущей директории новую директорию с указанным
//именем. В случае, если директория уже существует, печатает ошибку
//в stderr
public class CommandMKDIR extends Command {
    private String nameDir;
    private String curDir;

    public CommandMKDIR(String valueCurDir)
    {
        curDir = valueCurDir;
        isRequiredKeys = true;
    }

    //создает новую директорию с именем name, если такая существует - выводит ошибку
    private void CreateNewDir(String name)
    {
        File newDir = new File(curDir + "\\" + name);
        if(!newDir.exists())
        {
            newDir.mkdir();
            System.out.println("Директория успешно создана");
        }
        else
            System.err.println("Директория с таким именем уже существует");
    }

    //проверяем ключи на корректность и ставим соответствующие флажки
    public boolean setKeys(String[] strKeys)
    {
        if(strKeys.length>=1 && strKeys[0]!=null && strKeys[1]==null)
        {
            nameDir = strKeys[0];
            return true;
        }
         else
             return false;
    }

    //запускаем команду
    public void run()
    {
        CreateNewDir(nameDir);
    }
}
