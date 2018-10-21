package com.company;

public abstract class Command {
    //есть ли обязательные ключи или параметры
    public boolean isRequiredKeys;
    //проверяем ключи на корректность и ставим соответствующие флажки
    abstract boolean setKeys(String[] Keys);
    //запускаем команду
    abstract void run();
}
