package com.company;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Выводит содержимое указанного текстового файла (или нескольких
//файлов). В случае вывода нескольких файлов их содержимое
//показывается последовательно. Если один или несколько файлов не
//существуют, выводит в stderr сообщение об этом файле.
public class CommandCAT extends Command {

    private String[] names = new String[10]; //имена файлов
    private String curDir;

    private static final String[] SET_VALUES = new String[] { "-E", "-n", "-En", "-nE" };
    private static final Set<String> KEYS = new HashSet<>(Arrays.asList(SET_VALUES));

    public CommandCAT(String valueCurDir)
    {
        curDir = valueCurDir;
        isRequiredKeys = true;
    }

    private boolean isShowDollarAtTheEndEachLine;   //добавляем знак доллара в конце каждой строки файла при выводе
    private boolean isNumberLines;  //нумеруем строки файлов

    //проверка перед выполнением, что все имена файлов корректны
    private boolean isCorrectNames()
    {
        int i = 0;
        while(i<names.length && names[i]!=null)
        {
            File newFile = new File(curDir + "\\" + names[i]);
            if(!newFile.exists())
                return false;
            i++;
        }
        return true;
    }

    //выводит содержимое текстовых файлов
    private void PrintTextFile(String ... names)
    {
        if(!isCorrectNames())
        {
            System.out.println("Файла с таким именем не существует");
            return;
        }
        try
        {
            for(int i = 0; i<names.length && names[i]!=null; i++)
            {
                BufferedReader reader = new BufferedReader(new FileReader(curDir + "\\" + names[i]));
                String line;
                int number = 1;
                while ((line = reader.readLine()) != null)
                {
                    System.out.println(ModifyStr(line,number));
                    number++;
                }

                if (i < names.length-1)
                    System.out.println();
            }
        }
        catch(IOException e)
        {
            System.err.println("Файла с таким именем не существует");
        }
    }

    //изменяет строку в соответствии с выбранными ключами
    private String ModifyStr(String source,int number)
    {
        if(isShowDollarAtTheEndEachLine)
            source+="$";
        if(isNumberLines)
            source = number + " " + source;
        return source;
    }

    //проверяем ключи на корректность и ставим соответствующие флажки
    public boolean setKeys(String[] strKeys)
    {
        int i = 0;
        boolean isCorrect = true;
        while (i<strKeys.length-1 && strKeys[i]!=null && KEYS.contains(strKeys[i]))//(strKeys[i].equals("-E") || strKeys[i].equals("-n")))
        {
            switch(strKeys[i])
            {
                case "-E":
                {
                    if(isShowDollarAtTheEndEachLine)
                        return false;
                    isShowDollarAtTheEndEachLine = true;
                    break;
                }
                case "-n":
                {
                    if(isNumberLines)
                        return false;
                    isNumberLines = true;
                    break;
                }
                case "-En":
                case "-nE":
                {
                    if(isShowDollarAtTheEndEachLine || isNumberLines)
                        return false;
                    isShowDollarAtTheEndEachLine = true;
                    isNumberLines = true;
                    break;
                }
            }
            i++;
        }
        int j = 0;
        if(strKeys[i]==null)
            return false;

        while(i<strKeys.length && strKeys[i]!=null)
        {
            names[j] = strKeys[i];
            i++;
            j++;
        }
        return isCorrect;
    }

    //запускаем комманду
    public void run()
    {
        PrintTextFile(names);
    }
}
