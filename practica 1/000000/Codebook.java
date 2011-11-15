/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author alumno
 */
public class Codebook {
    
    private Map<Character, String> codebook = new TreeMap<Character, String>();
    
    public void add(char c, String s) {
        codebook.put(c, s);
    }
    
    public void erase(char c) {
        codebook.remove(c);
    }
    
    public String get(char c) {
        return codebook.get(c);
    }
    
    public List<String> codes() {
        return new ArrayList<String>(codebook.values());
    }
    
    public List<Character> simbolos() {
        return new ArrayList<Character>(codebook.keySet());
    }
    
    public int size() {
        return codebook.size();
    }
    
    @Override
    public String toString() {
        String res = new String();
        for (char c : codebook.keySet()) {
            res = res.concat(c + " -> " + get(c) + '\n');
        }
        return res;
    }
    
}
