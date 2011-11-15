/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author Nicolás
 */
public class CodeBook {
    private TreeMap<Character, String> codebook;
    
    public CodeBook() {
        this.codebook = new TreeMap<Character, String>();
    }
    
    public void add(char c, String s) {
        this.codebook.put(c, s);
    }
    
    public void erase(char c) {
        this.codebook.remove(c);
    }
    
    public String get(char c) {
        return this.codebook.get(c);
    }
    
    public List<String> codes() {
        return new ArrayList<String>(this.codebook.values());
    }
    
    public List<Character> simbols() {
        return new ArrayList<Character>(this.codebook.keySet());
    }
    
    public int size() {
        return codebook.size();
    }
    
    @Override
    public String toString() {
        String res = new String();
        for (char c : this.codebook.keySet()) {
            res = res.concat(c + " -> " + get(c) + '\n');
        }
        return res;
    }
    
}
