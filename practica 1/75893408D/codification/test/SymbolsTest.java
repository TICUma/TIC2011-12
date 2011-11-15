package codification.test;

import java.util.List;

public class SymbolsTest extends GeneralTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Character> msg = abcSource.getSymbols(100);
		System.out.println(msg);

		int[] ocurrences = new int[abcSource.getAlfabeto().size()];
		for (Character c : msg) {
			ocurrences[abcSource.getAlfabeto().getSymbols().indexOf(c)] += 1;
		}

		for (Character c : abcSource.getAlfabeto().getSymbols()) {
			System.out.println(c
					+ ": "
					+ ocurrences[abcSource.getAlfabeto().getSymbols()
							.indexOf(c)]);
		}

	}
}
