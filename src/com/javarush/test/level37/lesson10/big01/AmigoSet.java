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

    private void writeObject(java.io.ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"capacity"));
        s.writeObject(HashMapReflectionHelper.callHiddenMethod(map,"loadFactor"));
        s.writeObject(map.keySet().size());
        Object[] objects = map.keySet().toArray();
        for (int i = 0; i < objects.length; i++) {
            s.writeObject(objects[i]);
        }
    }

    private void readObject(java.io.ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        int mapCapacity = (int) s.readObject();
        float mapLoadFactor = Float.parseFloat(s.readObject().toString());
        int size = (int) s.readObject();
        map = new HashMap<>(mapCapacity,mapLoadFactor);
        for (int i = 0; i < size; i++) {
            E e = (E) s.readObject();
            map.put(e,PRESENT);
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

 /*   public static void main(String[] args) throws IOException, ClassNotFoundException {
        AmigoSet<Integer> amigoSet = new AmigoSet<Integer>(Arrays.asList(1,3,4,7));
        ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream("/Users/igormakarychev/Documents/Java projects/Java-JavaRushHomeWork/src/com/javarush/test/level37/lesson10/big01/tmp.txt"));
        oos.writeObject(amigoSet);

        oos.close();

        ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream("/Users/igormakarychev/Documents/Java projects/Java-JavaRushHomeWork/src/com/javarush/test/level37/lesson10/big01/tmp.txt"));

        AmigoSet<Integer> amigoSet_deserial = (AmigoSet<Integer>) ois.readObject();

        ois.close();
        System.out.println();
    }*/
}