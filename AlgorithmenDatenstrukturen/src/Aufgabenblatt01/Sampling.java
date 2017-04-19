package Aufgabenblatt01;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;

import Aufgabenblatt01.A.ListeA;
import Aufgabenblatt01.B.ListeB;
import Aufgabenblatt01.C.ListeC;

public class Sampling {

	static private void writeSample(FileWriter file, String s) {
		try {
			file.write(s);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
	}

	static private FileWriter[] openFile(String... names) {
		FileWriter[] files = new FileWriter[names.length];
		int i = 0;
		for (String name : names) {
			try {
				files[i++] = new FileWriter(name);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}

		return files;

	}

	static private void closeFile(FileWriter... files) {

		for (FileWriter file : files) {
			if (file != null) {
				try {
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					file = null;
				}
			}
		}
	}

	public static void fuelleListe(Liste<Integer> liste, int k, boolean randomPosition) {

		for (int i = 1; i <= k; i++) {

			int pos = 1;

			if (randomPosition) {
				// zuf�llige einf�ge position
				pos = (int) (Math.random() * i) + 1;
			}

			liste.insert(pos, i);
		}
	}

	public static void fuelleListe(Liste<Integer> liste, int k) {
		fuelleListe(liste, k, false);
	}

	// test Methoden
	static public String sampleInsert(FileWriter file, int N, Liste<Integer>... l) {
		String s = String.format("%d", N);
		for (Liste<Integer> list : l) {
			fuelleListe(list, N);
			list.statistikZaehler = 0;

			list.insert(N + 1, (Integer) N + 1);
			s += String.format(";%d", list.statistikZaehler);

		}
		s += "\n";
		writeSample(file, s);
		return s;
	}

	static public String sampleDeletePos(FileWriter file, int N, Liste<Integer>... l) {
		String s = String.format("%d", N);
		for (Liste<Integer> list : l) {
			fuelleListe(list, N);
			list.statistikZaehler = 0;
			
			list.delete(N);
			s += String.format(";%d", list.statistikZaehler);

		}
		s += "\n";
		writeSample(file, s);
		return s;
	}

	static public String sampleDeleteKey(FileWriter file, int N, Liste<Integer>... l) {
		String s = String.format("%d", N);
		Schluessel key = new Schluessel(1000);
		for (Liste<Integer> list : l) {
			fuelleListe(list, N);
			list.statistikZaehler = 0;

			list.delete(key);
			s += String.format(";%d", list.statistikZaehler);

		}
		s += "\n";
		writeSample(file, s);
		return s;
	}

	static public String sampleFind(FileWriter file, int N, Liste<Integer>... l) {
		String s = String.format("%d", N);
		Schluessel key = new Schluessel(1000);
		for (Liste<Integer> list : l) {
			fuelleListe(list, N);
			list.statistikZaehler = 0;

			list.find(key);
			s += String.format(";%d", list.statistikZaehler);

		}
		s += "\n";
		writeSample(file, s);
		return s;
	}

	static public String sampleRetrieve(FileWriter file, int N, Liste<Integer>... l) {
		String s = String.format("%d", N);
		for (Liste<Integer> list : l) {
			fuelleListe(list, N);
			list.statistikZaehler = 0;
			
			list.retrieve(N);
			s += String.format(";%d", list.statistikZaehler);

		}
		s += "\n";
		writeSample(file, s);
		return s;
	}

	static public String sampleConcat(int N, Liste<Integer> l1, Liste<Integer> l2) {
		String s = "";	
		fuelleListe(l1, N);
			fuelleListe(l2, N);
			
			l1.statistikZaehler = 0;

			l1.concat(l2);
			s += String.format(";%d", l1.statistikZaehler);
			
			return s;

	}

	static public void main(String[] args) {

		int N_BASE = 10;
		int N_MAX_POWER = 5;
		int N = 0;

		//long startTime = 0;
		//long time = 0;
		
		//Liste<?>[] listen = { new ListeA<Integer>(), new ListeB<Integer>(), new ListeC<Integer>() };

		FileWriter files[] = openFile("insert.txt","deletePos.txt", "deleteKey.txt", "find.txt","retrieve.txt","concat.txt");

		for (int i = 1; i <= N_MAX_POWER; i++) {
			N = (int) Math.pow(N_BASE, i);
			

			sampleInsert(files[0], N, new ListeA<Integer>(), new ListeB<Integer>(), new ListeC<Integer>());
			sampleDeletePos(files[1], N, new ListeA<Integer>(), new ListeB<Integer>(), new ListeC<Integer>());
			sampleDeleteKey(files[2], N, new ListeA<Integer>(), new ListeB<Integer>(), new ListeC<Integer>());
			sampleFind(files[3], N, new ListeA<Integer>(), new ListeB<Integer>(), new ListeC<Integer>());
			sampleRetrieve(files[4], N, new ListeA<Integer>(), new ListeB<Integer>(), new ListeC<Integer>());
			
			String s= String.format("%d",N);
			s += sampleConcat(N, new ListeA<Integer>(), new ListeA<Integer>() );
			s += sampleConcat(N, new ListeB<Integer>(), new ListeB<Integer>() );
			s += sampleConcat(N, new ListeC<Integer>(), new ListeC<Integer>() );
			s += "\n"; 
			writeSample(files[5], s);
			

			}
		closeFile(files);
		}

		

}