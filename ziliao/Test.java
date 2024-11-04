package com;

import com.google.gson.Gson;
import org.mmc.util.FileUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws IOException {
//        if (true) {
//            String[] arr1 = readFile("D:/部门.txt").split("\n");
//            String[] arr2 = readFile("D:/省.txt").split("\n");
//            String[] arr3 = readFile("D:/区.txt").split("\n");
//            StringBuilder sb = new StringBuilder();
//
//            for (int i = 0; i < arr1.length; i++) {
////                System.out.println(arr1[i] + "@" + arr2[i] + "@" + arr3[i]+ "\n");
//                sb.append(arr1[i] + "@" + arr2[i] + "@" + arr3[i]+ "\n");
//            }
//            FileUtils.writeFile("D:/tmp999.txt", sb.toString());
//            return;
//        }

        String[] arr1 = readFile("D:/部门.txt").split("\n");
        String[] arr2 = readFile("D:/省.txt").split("\n");
        String[] arr3 = readFile("D:/区.txt").split("\n");

        Map<String, Node> rootNodes = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            System.out.println(i + " " + arr1[i] + " = " + arr2[i] + " = " + arr3[i]);

            String key1 = arr1[i];
            String key2 = arr2[i];
            String key3 = arr3[i];

            addNode(rootNodes, key1, key2, key3);
        }

        // 转换为 JSON
        Gson gson = new Gson();
        ArrayList<Node> nodeList = new ArrayList<>(rootNodes.values());
        String json = gson.toJson(nodeList);
        System.out.println(json);
        FileUtils.writeFile("D:/json.txt", json);
    }

    private static void addNode(Map<String, Node> rootNodes, String key1, String key2, String key3) {
        Node rootNode = rootNodes.computeIfAbsent(key1, Node::new);
        Node bbNode = getOrAddChild(rootNode, key2);
        Node ccNode = getOrAddChild(bbNode, key3);
    }

    private static Node getOrAddChild(Node parent, String key) {
        for (Node child : parent.children) {
            if (child.key.equals(key)) {
                return child;
            }
        }
        Node newNode = new Node(key);
        parent.addChild(newNode);
        return newNode;
    }

    static class Node {
        String key;
        ArrayList<Node> children;

        public Node(String key) {
            this.key = key;
            this.children = new ArrayList<>();
        }

        public void addChild(Node child) {
            this.children.add(child);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key='" + key + '\'' +
                    ", children=" + children +
                    '}';
        }
    }


    private static String readFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString().trim(); // 移除末尾多余的换行符
    }

}