package ua.edu.ucu.smartarr;

// Remove duplicates from SmartArray. Use method equals() to compare objects
public class DistinctDecorator extends SmartArrayDecorator {
    private Object[] newArr;

    public DistinctDecorator(SmartArray smartArray) {
        super(smartArray);
        Object[] oldArr = this.smartArray.toArray();
        int delete = 0;
        for (int i = 0; i < oldArr.length; i++) {
            for (int k = i + 1; k < oldArr.length; i++) {
                if (oldArr[i].equals(oldArr[k])) {
                    oldArr[i] = null;
                    k = oldArr.length;
                    delete++;
                }
            }
        }
        this.newArr = new Object[oldArr.length - delete];
        int index = 0;
        for (Object element : oldArr) {
            if (element != null) {
                newArr[index] = element;
                index++;
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
