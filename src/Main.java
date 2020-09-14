import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static class Field {
		String name;
		String value;
		String valueType;

		public Field(String name, String value, String valueType) {
			this.name = name;
			this.value = value;
			this.valueType = valueType;
		}
	}

	public static class Section {
		String name;
		ArrayList<Field> fields;

		public Section(String name) {
			this.name = name;
			fields = new ArrayList<Field>();
		}
	}

	public static class INIFile {
		ArrayList<Section> sections;

		public INIFile() {
			sections = new ArrayList<Section>();
		}

		public Field findField(String section, String field) throws Exceptions.NoSuchPairException {
			for (int i = 0; i < sections.size(); i++) {
				if (sections.get(i).name.equals(section)) {
					// System.out.println("yes");
					for (int j = 0; j < sections.get(i).fields.size(); j++) {
						if (sections.get(i).fields.get(j).name.equals(field)) {
							return sections.get(i).fields.get(j);
						}
					}
				}
			}
			throw new Exceptions.NoSuchPairException(
					"No such pair as: Section " + section + ", integer field " + field);
		}

		public int findInt(String section, String field)
				throws Exceptions.NoSuchPairException, Exceptions.MissMatchException {
			Field f = findField(section, field);
			if (f.valueType.equals("int")) {
				return Integer.valueOf(f.value);
			} else {
				throw new Exceptions.MissMatchException("Expected field type is int, field type is " + f.valueType);
			}
		}

		public double findDouble(String section, String field)
				throws Exceptions.NoSuchPairException, Exceptions.MissMatchException {
			Field f = findField(section, field);
			if (!f.valueType.equals("String")) {
				return Double.valueOf(f.value);
			} else {
				throw new Exceptions.MissMatchException("Expected field type is int, field type is " + f.valueType);
			}
		}

		public String findString(String section, String field) throws Exceptions.NoSuchPairException {
			Field f = findField(section, field);
			return f.value;
		}
	}

	public static void parseSection(String line, INIFile ini) {
		int i = 1;
		while (line.charAt(i) != ']') {
			i++;
		}
		// System.out.println(line.substring(1, i));
		Section s = new Section(line.substring(1, i));
		ini.sections.add(s);
	}

	public static void parseField(String line, INIFile ini) {
		int i = 0;
		while (line.charAt(i) != ' ') {
			i++;
		}
		String n = line.substring(0, i);
		// System.out.println(n);
		int doubleFlag = 0;
		int StringFlag = 0;
		i++;
		while ((line.charAt(i) == ' ') || (line.charAt(i) == '=')) {
			i++;
		}
		int j = i;
		while ((j < line.length()) && (line.charAt(j) != ';') && (line.charAt(j) != ' ')) {
			if (line.charAt(j) > 57) {
				StringFlag++;
			}
			if (line.charAt(j) == '.') {
				doubleFlag++;
			}
			j++;
		}
		String type = "";
		if ((StringFlag > 0) || (doubleFlag > 1)) {
			type = "String";
		} else {
			if (doubleFlag > 0) {
				type = "double";
			} else {
				type = "int";
			}
		}
		// System.out.println(line.substring(i, j) + " " + type);
		Field f = new Field(n, line.substring(i, j), type);
		ini.sections.get(ini.sections.size() - 1).fields.add(f);
	}

	public static boolean checkSection(String line) {
		return (line.charAt(0) == '[');
	}

	public static boolean checkCommentary(String line) {
		return (line.charAt(0) == ';');
	}

	public static boolean checkFileExtensionIsINI(String str) {
		return ((str.charAt(str.length() - 4) == '.') && (str.charAt(str.length() - 3) == 'i')
				&& (str.charAt(str.length() - 2) == 'n') && (str.charAt(str.length() - 1) == 'i'));
	}

	public static INIFile parse(String str) throws FileNotFoundException, Exceptions.IncorrectFileExtensionException {
		if (checkFileExtensionIsINI(str)) {
			Scanner sc = new Scanner(new File(str));
			INIFile ini = new INIFile();
			String st = "";
			while (sc.hasNextLine() && st != null) {
				st = sc.nextLine();
				// System.out.println(st);
				if (st.length() == 0) {
					continue;
				}
				if (checkSection(st)) {
					parseSection(st, ini);
					continue;
				}
				if (checkCommentary(st)) {
					continue;
				}
				parseField(st, ini);
			}
			sc.close();
			return ini;
		} else {
			throw new Exceptions.IncorrectFileExtensionException("Incorrect file format of file: " + str);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		INIFile ini = parse("input.ini");
		System.out.println(ini.findString("ADC_DEV", "Driver"));
		System.out.println(ini.findInt("COMMON", "LogNCDM"));
		System.out.println(ini.findDouble("COMMON", "LogNCDM"));
		System.out.println(ini.findDouble("COMMO", "LogNCDM"));
		System.out.println(ini.findInt("ADC_DEV", "Driver"));
		sc.close();

	}

}
