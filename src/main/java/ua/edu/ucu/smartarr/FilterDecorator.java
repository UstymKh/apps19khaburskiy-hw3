package ua.edu.ucu.smartarr;

import ua.edu.ucu.functions.MyPredicate;

import java.util.Arrays;
import java.util.LinkedHashSet;

// Tests every element and removes it if it doesn't satisfy MyPredicate
public class FilterDecorator extends SmartArrayDecorator {

    private Object[] newArr;

    public FilterDecorator(SmartArray smartArray, MyPredicate myPredicate) {
        super(smartArray);
        LinkedHashSet helper = new LinkedHashSet(Arrays.asList(this.smartArray.toArray()));
        for (Object el : this.smartArray.toArray()) {
            if (!myPredicate.test(el)) {
                helper.remove(el);
            }
        }
        this.newArr = helper.toArray();

    }

    @Override
    public Object[] toArray() {
        return this.newArr.clone();
    }

    @Override
    public String operationDescription() {
        return this.smartArray.operationDescription() + "; " + "FilterDecorator";
    }

    @Override
    public int size() {
        return this.newArr.length;
    }
}
