package com.company;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Выводит названия директорий и файлов в текущей директории,
//отсортированных по имени, по возрастанию.
public class CommandLS extends Command  {//implements ICommand {
    private boolean isReverseSort = false;  //сортирует по убыванию
    private boolean isShowSubdirectories = false;   //выводит не только в текущей директории, но и во всех вложенных
    private String curDir;

    public CommandLS(String valueCurDir)
    {
        curDir = valueCurDir;
        isRequiredKeys = false;
    }

    //вспомогательный метод для печати
    private void PrintNamesDirAndFiles(String myFolder, String spaces, boolean isShowSubdir, boolean revOrder)
    {
        File initFolder = new File(myFolder);
        File[] files = initFolder.listFiles();
        List<File> lst = Arrays.asList(files);

        if(revOrder)
            lst.sort(Collections.reverseOrder());
        else
            Collections.sort(lst);

        for(int i=0; i<lst.size();i++)
        {
            System.out.println(spaces + lst.get(i).getName());
            if(isShowSubdir)
                if(lst.get(i).isDirectory())
                    PrintNamesDirAndFiles(myFolder+"\\"+lst.get(i).getName(),spaces+"  ",isShowSubdir,revOrder);
        }
    }

    //проверяем ключи на корректность и ставим соответствующие флажки
    public boolean setKeys(String[] keys)
    {
        int i = 0;
        boolean isCorrect = true;

        while(i<keys.length && keys[i]!=null)
        {
            //выбираем ключи
            switch(keys[i])
            {
                case "-r":
                {
                    if (isReverseSort)
                        return false;
                    isReverseSort = true;
                    break;
                }
                case "-R":
                {
                    if(isShowSubdirectories)
                        return false;
                    isShowSubdirectories = true;
                    break;
                }
                default:    //если ключ - не один из вышеперечисленных, то команда введена некорректна
                    isCorrect = false;
            }
            i++;
        }
        return isCorrect;
    }

    //запускаем команду
    public void run()
    {
        PrintNamesDirAndFiles(curDir,"",isShowSubdirectories,isReverseSort);
    }
}
