package codification.test;

import java.util.ArrayList;
import java.util.List;

import codification.ABCAlphabet;
import codification.Source;

public class GeneralTest {

	protected static List<Float> prob = new ArrayList<Float>();
	protected static Source abcSource = new Source(new ABCAlphabet(), prob);
	static {
		prob.add(0.5f);
		prob.add(0.25f);
		prob.add(0.25f);
	}

}
