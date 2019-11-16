package ua.edu.ucu;

import ua.edu.ucu.functions.MyComparator;
import ua.edu.ucu.functions.MyFunction;
import ua.edu.ucu.functions.MyPredicate;
import ua.edu.ucu.smartarr.*;

import java.util.Arrays;

public class SmartArrayApp {

    public static Integer[]
    filterPositiveIntegersSortAndMultiplyBy2(Integer[] integers) {

        MyPredicate pr = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                return ((Integer) t) > 0;
            }
        };

        MyComparator cmp = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((Integer) o1) - ((Integer) o2);
            }
        };

        MyFunction func = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return 2 * ((Integer) t);
            }
        };

        // Input: [-1, 2, 0, 1, -5, 3]
        SmartArray sa = new BaseArray(integers);

        sa = new FilterDecorator(sa, pr); // Result: [2, 1, 3];
        sa = new SortDecorator(sa, cmp); // Result: [1, 2, 3]
        sa = new MapDecorator(sa, func); // Result: [2, 4, 6]

        // Alternative
//        sa = new MapDecorator(
//                    new SortDecorator(
//                        new FilterDecorator(sa, pr),
//                    cmp),
//                func);
        Object[] result = sa.toArray();
        return Arrays.copyOf(result, result.length, Integer[].class);
    }

    public static String[]
    findDistinctStudentNamesFrom2ndYearWithGPAgt4AndOrderedBySurname(Student[] students) {
        SmartArray studentSmartArray = new BaseArray(students);
        studentSmartArray = new DistinctDecorator(studentSmartArray);
        MyPredicate predicate = new MyPredicate() {
            @Override
            public boolean test(Object t) {
                if (((Student) t).getYear() == 2 && ((Student) t).getGPA() >= 4) {
                    return true;
                }
                return false;
            }
        };
        studentSmartArray = new FilterDecorator(studentSmartArray, predicate);
        MyComparator compare = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String sur1 = ((Student) o1).getSurname();
                String sur2 = ((Student) o2).getSurname();
                int l1 = sur1.length();
                int l2 = sur2.length();
                int lmin = Math.min(l1, l2);

                for (int i = 0; i < lmin; i++) {
                    int str1_ch = sur1.charAt(i);
                    int str2_ch = sur2.charAt(i);

                    if (str1_ch != str2_ch) {
                        return str1_ch - str2_ch;
                    }
                }
                if (l1 != l2) {
                    return l1 - l2;
                } else {
                    return 0;
                }
            }
        };
        studentSmartArray = new SortDecorator(studentSmartArray, compare);
        MyFunction function = new MyFunction() {
            @Override
            public Object apply(Object t) {
                String result = ((Student) t).getSurname() + " " + ((Student) t).getName();
                return result;
            }
        };
        studentSmartArray = new MapDecorator(studentSmartArray, function);
        // Hint: to convert Object[] to String[] - use the following code
        Object[] result = studentSmartArray.toArray();
        return Arrays.copyOf(result, result.length, String[].class);
    }
}
