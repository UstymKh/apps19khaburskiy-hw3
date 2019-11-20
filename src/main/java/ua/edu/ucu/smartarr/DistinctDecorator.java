package ua.edu.ucu.smartarr;

import java.util.Arrays;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator {
    private Object[] newArr;

    public DistinctDecorator(SmartArray smartArray) {
        super(smartArray);
        Object[] oldArr = Arrays.copyOf(this.smartArray.toArray(), this.smartArray.size());
        int delete = 0;
        for (int i = 0; i < oldArr.length - 1; i += 1) {
            for (int k = i + 1; k < oldArr.length; k += 1) {
                if (oldArr[i].equals(oldArr[k])) {
                    oldArr[i] = null;
                    delete += 1;
                    break;
                }
            }
        }
        this.newArr = new Object[oldArr.length - delete];
        int index = 0;
        for (Object element : oldArr) {
            if (element != null) {
                newArr[index] = element;
                index += 1;
            }
        }
    }

    @Override
    public Object[] toArray() {
        return this.newArr.clone();
    }

    @Override
    public String operationDescription() {
        return this.smartArray.operationDescription() + "; " + "DistinctDecorator";
    }

    @Override
    public int size() {
        return this.newArr.length;
    }
}
