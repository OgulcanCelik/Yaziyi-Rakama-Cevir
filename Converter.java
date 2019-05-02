import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Converter {

    //turkish numbers as words
	public static List<String> allowedStrings = Arrays.asList("bir", "iki", "üç", "dört", "beş", "altı", "yedi",
			"sekiz", "dokuz", "on", "yirmi", "otuz", "kırk", "elli", "altmış", "yetmiş", "seksen", "doksan", "yüz",
			"bin", "milyon", "milyar");

	//add character to position and shift
	public static String addChar(String str, char ch, int position) {
		StringBuilder sb = new StringBuilder(str);
		sb.insert(position, ch);
		return sb.toString();
	}


	//generate spaces between numbers as words to provide legit input for main method
	public static String generateSpaces(String input, String[] words) {
		for (String word : words) {

			if (input.contains(word)) {
				int addToIndex;
				int wordLength = word.length();
				//special case for on in milyon
				if (input.charAt(input.indexOf(word) - 1) == 'y')
					continue;
				if (input.charAt(input.indexOf(word) - 1) != ' ') {
					addToIndex = input.indexOf(word);
					input = addChar(input, ' ', addToIndex);
				}
				if (input.charAt(input.indexOf(word) + wordLength) != ' ') {
					addToIndex = input.indexOf(word) + wordLength;
					input = addChar(input, ' ', addToIndex);
				}
			}
		}
		return input;
	}
	
	
	//check numbers as words and generate spaces
	public static String NormalizeText(String input) {
		input = addChar(input, ' ', 0);
		input = addChar(input, ' ', input.length());
		String[] words = allowedStrings.toArray(new String[0]);
		if (!input.substring(1, 4).equals("bin")&& !input.contains("milyon")) {

			String firstHalf = input.substring(0, input.indexOf("bin"));
			String secondHalf = input.substring(input.indexOf("bin") + 3, input.length());
			firstHalf = addChar(firstHalf, ' ', firstHalf.length());
			secondHalf = addChar(secondHalf, ' ', 0);
			firstHalf = generateSpaces(firstHalf, words);
			secondHalf = generateSpaces(secondHalf, words);
			return firstHalf +"bin"+ secondHalf;

		} 
		else if (input.contains("milyon") && input.contains("bin")){
			String firstHalf = input.substring(0, input.indexOf("milyon"));
			String secondHalf = input.substring(input.indexOf("milyon") + 6, input.indexOf("bin"));
			String thirdHalf = input.substring(input.indexOf("bin")+3, input.length());
			firstHalf = addChar(firstHalf, ' ', firstHalf.length());
			secondHalf = addChar(secondHalf, ' ', 0);
			secondHalf = addChar(secondHalf, ' ', secondHalf.length());
			thirdHalf = addChar(thirdHalf, ' ',0 );
			firstHalf = generateSpaces(firstHalf, words);
			secondHalf = generateSpaces(secondHalf, words);
			thirdHalf = generateSpaces(thirdHalf, words);
			return firstHalf +"milyon"+ secondHalf + "bin" + thirdHalf;
			
		}
		else if (input.contains("milyon")&& !input.contains("bin")) {

			String firstHalf = input.substring(0, input.indexOf("milyon"));
			String secondHalf = input.substring(input.indexOf("milyon") + 6, input.length());
			firstHalf = addChar(firstHalf, ' ', firstHalf.length());
			secondHalf = addChar(secondHalf, ' ', 0);
			firstHalf = generateSpaces(firstHalf, words);
			secondHalf = generateSpaces(secondHalf, words);
			return firstHalf +"milyon"+ secondHalf;

		}
		else {
			input = generateSpaces(input, words);
		}

		return input;

	}
	
	//convert given numbers as words to numbers with the input of words splitted in an array by spaces
	public static long TextToNumber(String input) {

		boolean isValidInput = true;
		long result = 0;
		long finalResult = 0;

		if (input != null && input.length() > 0) {
			// input = input.replaceAll("-", " ");
			// input = input.toLowerCase().replaceAll(" and", " ");
			String[] splittedParts = input.trim().split("\\s+");

			for (String str : splittedParts) {
				if (!allowedStrings.contains(str)) {
					isValidInput = false;
					System.out.println("Invalid word found : " + str);
					break;
				}
			}

			if (splittedParts[0].equals("bin")) {
				String[] temp = new String[splittedParts.length + 1];
				temp[0] = "bir";
				for (int i = 0; i < splittedParts.length; i++) {
					temp[i + 1] = splittedParts[i];
				}
				splittedParts = temp.clone();
			}
			if (splittedParts[0].equals("yüz")) {
				String[] temp = new String[splittedParts.length + 1];
				temp[0] = "bir";
				for (int i = 0; i < splittedParts.length; i++) {
					temp[i + 1] = splittedParts[i];
				}
				splittedParts = temp.clone();
			}
			if (isValidInput) {
				for (String str : splittedParts) {
					if (str.equalsIgnoreCase("bir")) {
						result += 1;
					} else if (str.equalsIgnoreCase("iki")) {
						result += 2;
					} else if (str.equalsIgnoreCase("üç")) {
						result += 3;
					} else if (str.equalsIgnoreCase("dört")) {
						result += 4;
					} else if (str.equalsIgnoreCase("beş")) {
						result += 5;
					} else if (str.equalsIgnoreCase("altı")) {
						result += 6;
					} else if (str.equalsIgnoreCase("yedi")) {
						result += 7;
					} else if (str.equalsIgnoreCase("sekiz")) {
						result += 8;
					} else if (str.equalsIgnoreCase("dokuz")) {
						result += 9;
					} else if (str.equalsIgnoreCase("on")) {
						result += 10;
					} else if (str.equalsIgnoreCase("yirmi")) {
						result += 20;
					} else if (str.equalsIgnoreCase("otuz")) {
						result += 30;
					} else if (str.equalsIgnoreCase("kırk")) {
						result += 40;
					} else if (str.equalsIgnoreCase("elli")) {
						result += 50;
					} else if (str.equalsIgnoreCase("altmış")) {
						result += 60;
					} else if (str.equalsIgnoreCase("yetmiş")) {
						result += 70;
					} else if (str.equalsIgnoreCase("seksen")) {
						result += 80;
					} else if (str.equalsIgnoreCase("doksan")) {
						result += 90;
					} else if (str.equalsIgnoreCase("yüz")) {
						if (result == 0) {
							result += 100;
						} else
							result *= 100;
					} else if (str.equalsIgnoreCase("bin")) {
						if (result == 0) {
							result += 1000;
						} else {
							result *= 1000;
							finalResult += result;
							result = 0;
						}
					} else if (str.equalsIgnoreCase("milyon")) {
						result *= 1000000;
						finalResult += result;
						result = 0;
					} else if (str.equalsIgnoreCase("milyar")) {
						result *= 1000000000;
						finalResult += result;
						result = 0;
					}
				}

				finalResult += result;
				result = 0;

			}
		}
		return finalResult;
	}

	public static void main(String args[]) {

		System.out.println("Yazıyla bir sayı girin, çıkış için q:");
		Scanner scan = new Scanner(System.in);
		String inputString = scan.nextLine();
		while (!inputString.equals("q")) {
			System.out.println(inputString);
			System.out.println("Dummy text is: " + inputString);
			String spaced = NormalizeText(inputString);
			System.out.println("Spaced out text is: " + spaced);
			Long numbers = TextToNumber(spaced);
			System.out.println("Converted to numbers: " + numbers);
			System.out.println("Yazıyla bir sayı girin:");
			inputString = scan.nextLine();
			scan.close();
		}
	}
}
