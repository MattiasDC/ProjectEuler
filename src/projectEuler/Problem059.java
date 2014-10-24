package projectEuler;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;


public class Problem059 extends Problem {

	@Override
	protected String solve() {
		BufferedReader reader;
		String line = null;
		String parsedCipher = "";
		try {
			reader = new BufferedReader(new FileReader("Problem59Cipher"));
			line = reader.readLine();
			for (String cipher : line.split(",")) {
				int value = Integer.parseInt(cipher);
				if (value < 10)
					parsedCipher += "00" + value;
				else if (value < 100)
					parsedCipher += "0" + value;
				else
					parsedCipher += value;
			}
			reader.close();
		}
		catch (IOException e) {
		}

		System.out
				.println("This problem can be solved by running the self made XOR program. The cipher u should use is:");
		System.out.println(parsedCipher);

		String text = JOptionPane
				.showInputDialog("Paste the ASCII values of the original text in here");
		int answer = 0;

		for (int i = 0; i < text.length(); i += 3)
			answer += Integer.parseInt(text.substring(i, i + 3));

		return answer + "";

	}

}
