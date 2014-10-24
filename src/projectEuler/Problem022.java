package projectEuler;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;


public class Problem022 extends Problem {

	@Override
	protected String solve() {
		try {
			getNamesFromFile();

			long sumValues = 0;

			for (int i = 0; i < getNames().size(); i++) {
				sumValues += getNames().get(i).getNameValue() * (i + 1);
			}

			return sumValues + "";

		}
		catch (IOException exc) {
			return "There was an error while reading the file";
		}
	}

	private void getNamesFromFile() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("Problem22Names"));

		String line;
		while ((line = reader.readLine()) != null) {
			String names[] = line.replace("\"", "").split(",");
			for (String name : names) {
				addName(new Name(name));
			}
			Collections.sort(getNames());
		}
		reader.close();
	}

	public ArrayList<Name> getNames() {
		return names;
	}

	public void addName(Name name) {
		if (name != null) {
			names.add(name);
		}
	}

	private ArrayList<Name> names = new ArrayList<Name>();

	private class Name implements Comparable<Object> {

		public Name(String name) {
			setName(name);
			setNameValue();
		}

		private void setName(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		private String name;

		private void setNameValue() {
			nameValue = 0;
			for (int value : getName().toLowerCase().toCharArray()) {
				nameValue += value - 97 + 1;
			}
		}

		public int getNameValue() {
			return nameValue;
		}

		private int nameValue;

		@Override
		public int compareTo(Object object) throws ClassCastException {
			if (object == null || object.getClass() != this.getClass())
				throw new ClassCastException();
			int alphabeticalValueThis = 0, alphabeticalValueOther = 0;

			char[] nameThis = getName().toCharArray(), nameOther = ((Name) object).getName()
					.toCharArray();
			int i = 0;
			while (alphabeticalValueThis == alphabeticalValueOther) {
				try {
					alphabeticalValueThis += nameThis[i];
					alphabeticalValueOther += nameOther[i];
				}
				catch (IndexOutOfBoundsException exc) {
					if (nameThis.length == nameOther.length)
						return 0;
					else if (nameThis.length > nameOther.length)
						return 1;
					else
						return -1;
				}
				i++;
			}

			if (alphabeticalValueThis < alphabeticalValueOther)
				return -1;
			else
				return 1;
		}

	}

}
