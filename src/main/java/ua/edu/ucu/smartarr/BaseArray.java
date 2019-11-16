package ua.edu.ucu.smartarr;

// Base array for decorators
public class BaseArray implements SmartArray {
    private Object[] collection;

    public BaseArray(Object[] colection) {
        this.collection = colection;
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
