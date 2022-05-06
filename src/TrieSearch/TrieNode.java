package TrieSearch;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrieNode {
    char data;

    //Trong TrideNode cua children chua rat nhieu TrideNode children khac
    LinkedList<TrieNode> children;
    TrieNode parent;
    boolean isEnd;

    public TrieNode(char c) {
        data = c;
        children = new LinkedList<TrieNode>();
        isEnd = false;
    }

    public TrieNode getChild(char c) {
        if (children != null)
            for (TrieNode eachChild : children)
                if (eachChild.data == c)
                    return eachChild;
        return null;
    }

    protected List<String> getWords() {
        List<String> list = new ArrayList<String>();
        if (isEnd) {
            list.add(toString());
        }

        if (children != null) {
            for (int i=0; i< children.size(); i++) {
                if (children.get(i) != null) {
                    list.addAll(children.get(i).getWords());
                }
            }
        }
        return list;
    }

    public String toString() {
        if (parent == null) {
            return "";
        } else {
            //Them cac ky tu dang con thieu dang char vao roi chuyen no thanh dang string
            return parent.toString() + new String(new char[] {data});
        }
    }
}