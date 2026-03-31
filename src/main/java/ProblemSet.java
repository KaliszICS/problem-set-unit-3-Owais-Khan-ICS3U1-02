
import java.util.Scanner;

public class ProblemSet {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Input two emails: ");
		String userInput = input.nextLine();
		System.out.println(emailValidator(userInput));
	}

	public static boolean containsOnlyLetters(String string) {
		if (string.length() == 0) {
			return true;
		}
		String firstChar = string.substring(0, 1);
		if (firstChar.toLowerCase() == firstChar.toUpperCase()) {
			return false;
		}
		return containsOnlyLetters(string.substring(1));
	}

	public static String emailValidatorHelper(String email) {
		if (!(email.contains("@"))) {
			return email + ": Invalid: Missing @";
		}
		if (!(email.indexOf("@") == email.lastIndexOf("@"))) {
			return email + ": Invalid: Multiple @";
		}	

		if (email.startsWith(".") || email.endsWith(".")) {
			return email + ": Invalid: Starts or ends with dot";
		}

		if (email.contains(" ")) {
			return email + ": Invalid: Contains spaces";
		}

		int atIndex = email.indexOf("@");
		String local = email.substring(0, atIndex);

		if (local.length() > 64) {
			return email + ": Invalid: Local part too long";
		}
		if (local.length() < 1) {
			return email + ": Invalid: Local part too short";
		}

		String domain = email.substring(atIndex+1);

		if (!domain.contains(".")) {
			return email + ": Invalid: No dot in domain";
		}

		int domainDotIndex = domain.lastIndexOf(".");
		String domainExtention = domain.substring(domainDotIndex+1);


		if (domainExtention.length() < 2 || domainExtention.length() > 6) {
			return email + ": Invalid: Invalid domain extension length";
		}

		if (!containsOnlyLetters(domainExtention)){
			return email + ": Invalid: Domain extension contains non-letters";
		}

		if (domain.equals("gmail.com")) {
			return email + ": Valid (Gmail normalized) | Local: "  + local + " | Domain: " + domain;
		}


		return email + ": Valid | Local: "  + local + " | Domain: " + domain;
	}

	public static String emailValidator(String input) {

		boolean containsOneComma = input.contains(", ") && input.indexOf(", ") == input.lastIndexOf(", ");

		if (containsOneComma) {
			if (input.length()<4) {
				return "Bro are you even tring to type an email?";
			}

			String input1 = input.substring(0, input.indexOf(','));
			String input2 = input.substring(input.indexOf(',')+2);
			return emailValidatorHelper(input1) + "\n" + emailValidatorHelper(input2);
		} else {
			return emailValidatorHelper(input);
		}
	}
}
