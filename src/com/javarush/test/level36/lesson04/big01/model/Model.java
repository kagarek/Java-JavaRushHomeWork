package com.javarush.test.level36.lesson04.big01.model;

/**
 * Created by Igor_Makarychev on 01.04.2016.
 */
public interface Model
{
    ModelData getModelData();
    void loadUsers();
    void loadDeletedUsers();
}
