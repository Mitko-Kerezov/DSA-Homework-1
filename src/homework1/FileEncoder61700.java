package homework1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class FileEncoder61700 implements FileEncoderFN {

	private static boolean[] isPrimeArray;

	public FileEncoder61700() {
		init();
	}

	@Override
	public void encode(String sourceFile, String destinationFile,
			LinkedList<Character> key) {
		// for faster indexing
		ArrayList<Character> myKey = new ArrayList<Character>(key);
		File inputFile = new File(sourceFile);
		if (!inputFile.exists() || inputFile.isDirectory()) {
			System.out.println("File does not exist or is directory");
			return;
		}

		// the input file in byte[] so I can close the reader
		byte[] inputByteContent = null;
		try (FileInputStream inputFileStream = new FileInputStream(inputFile)) {
			inputByteContent = new byte[inputFileStream.available()];
			inputFileStream.read(inputByteContent);
			inputFileStream.close();
		} catch (Exception e) {
			System.err.println("An exception occured while reading :(");
		}

		// the output file in byte[] so I can write the whole of it at once
		byte[] outputByteContent = new byte[inputByteContent.length];
		for (int i = 0; i < inputByteContent.length; ++i) {
			if (isPrime(i)) {
				outputByteContent[i] = inputByteContent[i];
			} else {
				outputByteContent[i] = (byte) ((char) myKey
						.get(inputByteContent[i]) + 128);
			}
		}

		try (FileOutputStream outputFileStream = new FileOutputStream(
				destinationFile)) {
			outputFileStream.write(outputByteContent);
			outputFileStream.close();
		} catch (Exception ex) {
			System.err.println("An exception occured while writing :(");
		}
	}

	@Override
	public void decode(String encodedFile, String destinationFile,
			LinkedList<Character> key) {
		// for faster search
		HashMap<Character, Byte> myKey = new HashMap<Character, Byte>();
		Iterator<Character> it = key.listIterator();
		for (byte index = 0; it.hasNext(); index++) {
			myKey.put(it.next(), index);
		}

		File inputFile = new File(encodedFile);
		if (!inputFile.exists() || inputFile.isDirectory()) {
			System.out.println("File does not exist or is directory");
			return;
		}
		// the input file in byte[] so I can close the reader
		byte[] inputByteContent = null;
		try (FileInputStream inputFileStream = new FileInputStream(inputFile)) {
			inputByteContent = new byte[inputFileStream.available()];
			inputFileStream.read(inputByteContent);
			inputFileStream.close();
		} catch (Exception e) {
			System.err.println("An exception occured while reading :(");
		}
		// the output file in byte[] so I can write the whole of it at once
		byte[] outputByteContent = new byte[inputByteContent.length];
		for (int i = 0; i < inputByteContent.length; ++i) {
			if (isPrime(i)) {
				outputByteContent[i] = inputByteContent[i];
			} else {
				// кастни си...
				outputByteContent[i] = myKey.get(Character
						.valueOf((char) (inputByteContent[i] + 128)));
			}
		}

		try (FileOutputStream outputFileStream = new FileOutputStream(
				destinationFile)) {
			outputFileStream.write(outputByteContent);
			outputFileStream.close();
		} catch (Exception ex) {
			System.err.println("An exception occured while writing :(");
		}
	}

	private boolean isPrime(int num) {
		return isPrimeArray[num];
	}

	private void init() {
		int N = 307200; // 300 kb at most
		isPrimeArray = new boolean[N + 1];
		isPrimeArray[1] = true;
		for (int i = 2; i <= N; i++) {
			isPrimeArray[i] = true;
		}

		for (int i = 2; i * i <= N; i++) {

			if (isPrimeArray[i]) {
				for (int j = i; i * j <= N; j++) {
					isPrimeArray[i * j] = false;
				}
			}
		}
	}

}
