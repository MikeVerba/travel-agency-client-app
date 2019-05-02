package com.example.travelagencyclientapp.app.models.mappers;

public abstract class Mapper<K,V>{
    public abstract V map(K key);
}
