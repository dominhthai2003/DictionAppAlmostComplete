package TrieSearch;


import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Trie {
    private TrieNode root;
    private ArrayList <String> words = new ArrayList<>();

    public Trie() {
        root = new TrieNode(' ');
        try {
            FileReader fileReader = new FileReader("E:\\Javafx\\DictionaryApplication\\Batman.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String read = "";
            String word = "";
            String definition = "";
            int line = 0;
            while ((read = bufferedReader.readLine()) != null) {
                line++;
                if (read.startsWith("@")) {
                    word = read.replace("@", "");
                } else if (read.equals("")) {
                    this.insert(word);
                    if (!words.contains(word)) {
                        words.add(word);
                    }
                    word = "";
                    definition = "";
                } else {
                    definition = definition + read + "\n";
                }
            }
            if (!words.contains(word)) {
                this.insert(word);
                words.add(word);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insert(String word) {
        if (search(word) == true)
            return;

        TrieNode current = root;
        TrieNode pre;
        for (char ch : word.toCharArray()) {
            pre = current;
            TrieNode child = current.getChild(ch);
            if (child != null) {
                current = child;
                child.parent = pre;
            } else {
                current.children.add(new TrieNode(ch));
                current = current.getChild(ch);
                current.parent = pre;
            }
        }
        current.isEnd = true;
    }

    public boolean search(String word) {
        TrieNode current = root;
        for (char ch : word.toCharArray()) {
            if (current.getChild(ch) == null)
                return false;
            else {
                current = current.getChild(ch);
            }
        }
        if (current.isEnd == true) {
            return true;
        }
        return false;
    }

    public List<String> autocomplete(String prefix) {
        TrieNode lastNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChild(prefix.charAt(i));
            if (lastNode == null)
                return new ArrayList<String>();
        }
        return lastNode.getWords();
    }
}