package codification.test;

import java.util.Collections;

public class MiscTest extends GeneralTest{
	
	public static void main(String[] args) {
		System.out.println(abcSource.entropy(2));
		
		System.out.println(abcSource.calcY(2));
		
		System.out.println(abcSource.calcN(abcSource.calcY(2)));
		
		System.out.println(abcSource.getCodificacion());
		
		System.out.println(prob);
		
		System.out.println(abcSource.getOptimalCodificacion());
	}

}
