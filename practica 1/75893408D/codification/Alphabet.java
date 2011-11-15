package codification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Alphabet {

	private char[] symbols;

	public Alphabet() {
		symbols = createSymbols();
	}

	protected abstract char[] createSymbols();

	public List<Character> getSymbols() {
		return toList(symbols);
	}

	public int size() {
		return symbols.length;
	}

	private List<Character> toList(char[] symbols) {
		List<Character> res = new ArrayList<Character>();
		for (int i = 0; i < symbols.length; i++) {
			res.add(symbols[i]);
		}
		return res;
	}
}
