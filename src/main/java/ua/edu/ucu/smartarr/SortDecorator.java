package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyComparator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

// Sorts elements using MyComparator to compare them
public class SortDecorator extends SmartArrayDecorator {

    private Object[] newArr;

    public SortDecorator(SmartArray smartArray, MyComparator myComparator) {
        super(smartArray);
        ArrayList helper = new ArrayList<>(Arrays.asList(this.smartArray.toArray()));
        Collections.sort(helper, myComparator);
        this.newArr = helper.toArray();
    }

    @Override
    public Object[] toArray() {
        return this.newArr.clone();
    }

    @Override
    public String operationDescription() {
        return this.smartArray.operationDescription() + "; " + "SortDecorator";
    }

    @Override
    public int size() {
        return this.newArr.length;
    }
}
