package com.company;

public class Main {

    public static void main(String[] args) {
        Utils util = new Utils();

        util.PrintNamesDirAndFiles(true);
        util.CreateNewDir("newDir");
        util.CreateNewTextFile("Тестовые данные","test.txt");
        util.PrintTextFile("test.txt","test2.txt");
    }
}