package practica1;

import java.util.ArrayList;
import java.util.List;

public class Tst {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[] c= new char[6];
		c[0]='a';
		c[1]='b';
		c[2]='c';
		c[3]='d';
		c[4]='e';
		c[5]='f';
		Alfabeto al=new Alfabeto(c);
		List<Double> l=new ArrayList<Double>(5);
		l.add(0.25);l.add(0.10);l.add(0.15);l.add(0.05);l.add(0.20);l.add(0.25);
		//l.add(0.4);l.add(0.2);l.add(0.2);l.add(0.1);l.add(0.10);
		double fin;//=System.nanoTime();
		double total;
		Fuente f=new Fuente(al,l);
		//System.out.print("Entropia "+f.entropia(f.getProbabilidades(), 2));
		double ini;
		ini=System.nanoTime();
		CodeBook b=f.getOptimalCodificacion();
		fin=System.nanoTime();
		System.out.print(b.toString());
		total=(fin-ini)/1000;
		System.out.print("Tiempo: "+total+"\n");
		System.out.print("Longitud media: "+f.longitud(b)+"\n");
		System.out.print("Entropia "+f.entropia(f.getProbabilidades(), 2));
		
		
		ini=System.nanoTime();
		b=f.getCodificacion();
		fin=System.nanoTime();
		System.out.print(b.toString());
		total=(fin-ini)/1000;
		System.out.print("Tiempo: "+total+"\n");
		System.out.print("Longitud media: "+f.longitud(b)+"\n");
		System.out.print("Entropia "+f.entropia(f.getProbabilidades(), 2));
	
		///*ini=System.nanoTime();
		//System.out.print(f.getOptimalCodificacion().toString());
	//	fin=System.nanoTime();
		//total=(fin-ini)/1000;
		//System.out.print("Tiempo: "+total+"\n");/*
		
	}

}
