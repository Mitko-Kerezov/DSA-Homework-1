package homework1;

import java.util.LinkedList;

import homework1.FileEncoder61700;

public class EntryPoint {
	public static void main(String[] args) {
		FileEncoder61700 fenc = new FileEncoder61700();
		LinkedList<Character> key = new LinkedList<Character>();
		for (int i = 255; i > -1; i--) {
			key.add((char) i);
		}
		
		long time;
		time = System.currentTimeMillis();
		fenc.encode("C:\\Users\\Dimitar\\Desktop\\in1.jpg", "C:\\Users\\Dimitar\\Desktop\\out1.enc", key);
		fenc.decode("C:\\Users\\Dimitar\\Desktop\\out1.enc", "C:\\Users\\Dimitar\\Desktop\\in1Dec.jpg", key);
		time = System.currentTimeMillis() - time;
		System.out.println(time + " miliseconds");

		time = System.currentTimeMillis();
		fenc.encode("C:\\Users\\Dimitar\\Desktop\\fixlist.txt", "C:\\Users\\Dimitar\\Desktop\\fixlistEnc", key);
		fenc.decode("C:\\Users\\Dimitar\\Desktop\\fixlistEnc", "C:\\Users\\Dimitar\\Desktop\\fixlistDec.txt", key);
		time = System.currentTimeMillis() - time;
		System.out.println(time + " miliseconds");

		time = System.currentTimeMillis();
		fenc.encode("C:\\Users\\Dimitar\\Desktop\\Repo.txt", "C:\\Users\\Dimitar\\Desktop\\RepoEnc", key);
		fenc.decode("C:\\Users\\Dimitar\\Desktop\\RepoEnc", "C:\\Users\\Dimitar\\Desktop\\RepoDec.txt", key);
		time = System.currentTimeMillis() - time;
		System.out.println(time + " miliseconds");

		time = System.currentTimeMillis();
		fenc.encode("C:\\Users\\Dimitar\\Desktop\\LARGEFILE.txt", "C:\\Users\\Dimitar\\Desktop\\LARGEFILEENC", key);
		fenc.decode("C:\\Users\\Dimitar\\Desktop\\LARGEFILEENC", "C:\\Users\\Dimitar\\Desktop\\LARGEFILEDEC.txt", key);
		time = System.currentTimeMillis() - time;
		System.out.println(time + " miliseconds");
		
		
		
	}
}
