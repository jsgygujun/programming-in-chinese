package com.jsgygujun.流相关;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author GuJun
 * @date 2020/11/13
 */
public class Collect案例 {

    public static void main(String[] args) {
        toMap例子();
    }

    private static void toMap例子() {
        List<Item> items = new ArrayList<>();
        items.add(new Item("1", 0.1f));
        items.add(new Item("2", 0.2f));
        items.add(new Item("3", 0.3f));
        items.add(new Item("4", 0.4f));
        items.add(new Item("2", 0.2f));
        items.add(new Item("3", 0.3f));
        Map<String, Item> map = items.stream().collect(Collectors.toMap(Item::getId, item -> item, (v1, v2) -> new Item(v1.getId(), v1.getScore()+v2.getScore())));
        System.out.println(map);
    }

    private static class Item {
        private String id;
        private float score;

        Item() {}

        Item(String id, float score) {
            this.id = id;
            this.score = score;
        }

        String getId() { return id; }

        float getScore() { return score; }

        @Override
        public String toString() {
            return "Item{" +
                    "id='" + id + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
