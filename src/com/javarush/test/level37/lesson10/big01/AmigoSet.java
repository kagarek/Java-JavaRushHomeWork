package com.javarush.test.level37.lesson10.big01;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Created by igormakarychev on 5/15/16.
 */
public class AmigoSet<E> extends AbstractSet<E> implements Serializable,Cloneable,Set<E> {

    private static final Object PRESENT = new Object();
    private transient HashMap<E,Object> map;

    public AmigoSet() {
        this.map = new HashMap<>();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        AmigoSet<E> amigoSet = new AmigoSet<>();
        try
        {
            amigoSet.addAll(this);
            amigoSet.map.putAll(this.map);
        }catch (Exception ex){
            throw new InternalError();
        }
        return amigoSet;
    }

    private void writeObject(java.io.ObjectOutputStream s) {
        try {
            s.defaultWriteObject();
            s.writeObject(new ArrayList<>(map.keySet()));
            s.writeInt(HashMapReflectionHelper.callHiddenMethod(map, "capacity"));
            s.writeFloat(HashMapReflectionHelper.callHiddenMethod(map, "loadFactor"));
        /*Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            s.writeObject(objects[i]);
        }*/
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    private void readObject(java.io.ObjectInputStream s) {
        try {
            s.defaultReadObject();
            ArrayList<E> set = (ArrayList<E>) s.readObject();
            int mapCapacity = s.readInt();
            float mapLoadFactor = s.readFloat();
            map = new HashMap<>(mapCapacity, mapLoadFactor);
            for (E e : set) {
                map.put(e, PRESENT);
            }
        } catch (ClassNotFoundException | IOException e) {
            //e.printStackTrace();
        }
    }

    public AmigoSet(Collection<? extends E> collection) {
        this.map = new HashMap<>((int) Math.max(16,collection.size()/.75f));
        this.addAll(collection);
    }

    @Override
    public Iterator iterator() {
        return map.keySet().iterator();
    }

    @Override
    public int size() {
        return map.keySet().size();
    }

    @Override
    public boolean isEmpty() {
        return super.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return super.contains(o);
    }

    public boolean add(E e) {
        try {
            map.put(e, PRESENT);
            return true;
        }
        catch (Exception exc) {
            return false;
        }
    }

    @Override
    public boolean remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void clear() {
        super.clear();
    }

}
