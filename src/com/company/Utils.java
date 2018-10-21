package com.company;


//Класс со вспомогательными операциями
public class Utils {

    private static final String myFolder = "D:\\VSU HW\\5 семестр\\Java\\FileUtils\\test";

    //получить все ключи из строки
    private static String[] getKeys(String strKeys)
    {
        int len = 10;
        String[] keys = new String[len];
        int startIndex = 0;
        int endIndex = strKeys.indexOf(" ");
        int i = 0;

        while (endIndex!=-1)
        {
            keys[i] = strKeys.substring(startIndex,endIndex);
            startIndex = endIndex+1;
            endIndex = strKeys.indexOf(" ",startIndex+1);
            i++;
        }

        keys[i]=strKeys.substring(startIndex,strKeys.length());

        return keys;
    }

    //выполнить операцию из строки при условии, что она корректная
    public static void RunOperation(String source)
    {
        int endIndex = source.indexOf(" ");
        String strCmd;
        String strKeys;
        boolean isKeys;

        //если операция единственная в строке, то ключей нет
        if(endIndex==-1)
        {
            strCmd = source.toString();     ///////////////////////////////////////
            isKeys = false;
            strKeys = "";
        }
        else
        {
            strCmd = source.substring(0,endIndex);
            strKeys = source.substring(endIndex+1,source.length());
            isKeys = true;
        }

        //выбираем операцию (команду)
        Command cmd;
        switch (strCmd) {
            case "ls":
            {
                cmd = new CommandLS(myFolder);
            }
            break;
            case "mkdir": {
                cmd = new CommandMKDIR(myFolder);
            }
            break;
            case "echo": {
                cmd = new CommandECHO(myFolder);
            }
            break;
            case "cat": {
                cmd = new CommandCAT(myFolder);
            }
            break;
            default:
            {
                System.out.println("Команда введена некорректно");
                return;
            }
        }

        if (isKeys)
        {
            if(cmd.setKeys(getKeys(strKeys)))
                cmd.run();
            else
                System.out.println("Команда введена некорректно");
        }
        else
            if(cmd.isRequiredKeys)
                System.out.println("Команда введена некорректно");
            else
                cmd.run();
    }
}