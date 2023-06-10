package org.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StringList stringList = new StringListImpl();
        stringList.add("a1");
        stringList.add("b2");
        stringList.add("c3");
        stringList.add(3, "d4");
        System.out.println(Arrays.toString(stringList.toArray()));
        stringList.remove(2);
        System.out.println(Arrays.toString(stringList.toArray()));
        stringList.clear();
        System.out.println(stringList.size());
    }

}