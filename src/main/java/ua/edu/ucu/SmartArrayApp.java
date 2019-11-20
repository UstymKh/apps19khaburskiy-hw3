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
                int grade = 4;
                return ((Student) t).getYear() == 2 && ((Student) t).getGPA() >= grade;
            }
        };
        studentSmartArray = new FilterDecorator(studentSmartArray, predicate);
        MyComparator compare = new MyComparator() {
            @Override
            public int compare(Object o1, Object o2) {
                String surA = ((Student) o1).getSurname();
                String surAA = ((Student) o2).getSurname();
                int lA = surA.length();
                int lAA = surAA.length();
                int lmin = Math.min(lA, lAA);

                for (int i = 0; i < lmin; i += 1) {
                    int strAch = surA.charAt(i);
                    int strAAch = surAA.charAt(i);

                    if (strAch != strAAch) {
                        return strAch - strAAch;
                    }
                }
                if (lA != lAA) {
                    return lA - lAA;
                } else {
                    return 0;
                }
            }
        };
        studentSmartArray = new SortDecorator(studentSmartArray, compare);
        MyFunction function = new MyFunction() {
            @Override
            public Object apply(Object t) {
                return ((Student) t).getSurname() + " " + ((Student) t).getName();
            }
        };
        studentSmartArray = new MapDecorator(studentSmartArray, function);
        // Hint: to convert Object[] to String[] - use the following code
        Object[] result = studentSmartArray.toArray();
        for (Object st : result) {
            System.out.println(st.toString());
        }
        return Arrays.copyOf(result, result.length, String[].class);
    }
}
