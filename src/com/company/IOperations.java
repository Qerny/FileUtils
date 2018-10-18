package com.company;

public interface IOperations {
    void PrintNamesDirAndFiles(boolean revOrder);
    void CreateNewDir(String name);
    void CreateNewTextFile(String text, String name);
    void PrintTextFile(String ... names);
}