package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyFunction;

// Map every element to another object using MyFunction
public class MapDecorator extends SmartArrayDecorator {

    private Object[] newArr;

    public MapDecorator(SmartArray smartArray, MyFunction myFunction) {
        super(smartArray);
        this.newArr = this.smartArray.toArray();
        for (int i = 0; i < this.smartArray.size(); i += 1) {
            newArr[i] = myFunction.apply(newArr[i]);
        }

    }

    @Override
    public Object[] toArray() {
        return this.newArr.clone();
    }

    @Override
    public String operationDescription() {
        return this.smartArray.operationDescription() + "; " + "MapDecorator";
    }

    @Override
    public int size() {
        return this.newArr.length;
    }
}
