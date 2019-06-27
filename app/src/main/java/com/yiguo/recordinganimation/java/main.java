package com.yiguo.recordinganimation.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

class main {

    public static void main(String[] args) {
        //findCharNum();
        //HashMapTest();
        threadWati();

        System.out.println("输出log: wait(1mills)");
    }

    private   static void threadWati() {
        Object o = new Object();
        System.out.println("1.threadWati");

        synchronized (o) {
            try {
                o.wait(3000);
                System.out.println("输出log: wait()");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    //hashMap测试
    private static void HashMapTest() {
        Map<String, Integer> map = new HashMap<>();

    }


    public static void findCharNum() {
        String st = "fdafdadf";
        //找出字符串中出现最多的字符
        char[] chars = st.toCharArray();//转换单个字符
        //hashMap排序
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            String aChar = String.valueOf(chars[i]);
            if (map.containsKey(aChar)) {
                Integer sum = map.get(aChar);
                map.put(aChar, ++sum);
            } else {
                map.put(aChar, 0);
            }
        }

        Set<String> keySet = map.keySet();//获得key集合
        List<String> keyList = new ArrayList<>(keySet);
        Collection<Integer> values = map.values();//获得value集合 出现次数
        List<Integer> valuesList = new ArrayList<>(values);
        Integer max1 = Collections.max(valuesList);//出现次数最多


        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<String, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Integer> item = iterator.next();
            Integer value = item.getValue();
            if (value == max1) {
                System.out.println("最多的字符：" + item.getKey());
            }
        }


        //list集合排序
        ArrayList<String> list = new ArrayList<>();
        ArrayList<Integer> sumList = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            String aChar = String.valueOf(chars[i]);
            if (list.contains(aChar)) {
                int i1 = list.indexOf(aChar);
                Integer integer = sumList.get(i1);
                integer = integer + 1;
                sumList.set(i1, integer);
                // sumList.add(i1, integer);这个并不会覆盖原来的index
            } else {
                list.add(list.size(), aChar);
                sumList.add(sumList.size(), 1);
            }
        }
        Integer max = Collections.max(sumList);
        for (int i = 0; i < sumList.size(); i++) {
            if (sumList.get(i) == max) {
                String s = list.get(i);
                System.out.println("最多的字符：" + s);
            }
        }

    }

}

