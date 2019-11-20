package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Base array for decorators
public class BaseArray implements SmartArray {
    private Object[] collection;

    public BaseArray(Object[] collection) {
        this.collection = Arrays.copyOf(collection, collection.length);
    }

    @Override
    public Object[] toArray() {
        return this.collection.clone();
    }

    @Override
    public String operationDescription() {
        return "BaseArray";
    }

    @Override
    public int size() {
        return this.collection.length;
    }
}
