package com.jsgygujun.流相关;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GuJun
 * @date 2020/11/25
 */
public class 流转换 {

    public static void main(String[] args) {
        流转换对象引用问题();
    }

    private static class Foo {
        private int id;
        private Map<String, String> params;

        private void addParam(String key, String value) {
            if (params == null) {
                params = new HashMap<>();
            }
            params.put(key, value);
        }

        @Override
        public String toString() {
            return "Foo{" +
                    "id=" + id +
                    ", params=" + params +
                    '}';
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    private static void 流转换对象引用问题() {
        List<Foo> foos = new ArrayList<>();
        {
            Foo foo = new Foo();
            foo.setId(1);
            foo.addParam("weight", "1");
            foos.add(foo);
        }
        {
            Foo foo = new Foo();
            foo.setId(2);
            foo.addParam("weight", "2");
            foos.add(foo);
        }
        {
            Foo foo = new Foo();
            foo.setId(3);
            foo.addParam("weight", "3");
            foos.add(foo);
        }

//        {
//            List<Foo> newFoos = foos
//                    .stream()
//                    .peek(foo -> foo.addParam("weight", "0")) // 修改了对象
//                    .collect(Collectors.toList());
//            System.out.println(foos);
//            System.out.println(newFoos);
//        }

        {
            List<Foo> newFoos2 = foos
                    .stream()
                    .map(foo -> {
                        Foo f = new Foo();
                        f.setId(foo.id);
                        f.addParam("weight", "0");
                        return f;
                    }) // 修改了对象
                    .collect(Collectors.toList()); // 未修改对象
            System.out.println(foos);
            System.out.println(newFoos2);
        }

    }

}
