public class ProblemSet {

	public static void main(String args[]) {
		System.out.println(emailValidator(","));
	}

	public static String emailValidatorHelper(String email) {
		if (!(email.contains("@"))) {
			return "Invalid: Missing @";
		}
		if (!(email.indexOf("@") == email.lastIndexOf("@"))) {
			return "Invalid: Multiple @";
		}	

		if (email.startsWith(".") || email.endsWith(".")) {
			return "Invalid: Starts or ends with dot";
		}

		if (email.contains(" ")) {
			return "Invalid: Contains spaces";
		}

		int atIndex = email.indexOf("@");
		String local = email.substring(0, atIndex);

		if (local.length() > 64) {
			return "Invalid: Local part too long";
		}
		if (local.length() < 1) {
			return "Invalid: Local part too short";
		}

		String domain = email.substring(atIndex+1);

		if (!domain.contains(".")) {
			return "Invalid: No dot in domain";
		}

		int domainDotIndex = domain.lastIndexOf(".");
		String domainExtention = domain.substring(domainDotIndex+1);


		if (domainExtention.length() < 2 || domainExtention.length() > 6) {
			return "Invalid: Invalid domain extension length";
		}

		return email + ": Valid | Local: "  + local + " | Domain: " + domain;
	}

	public static String emailValidator(String input) {

		if (input.length()<)

		boolean containsOneComma = input.contains(",") && input.indexOf(",") == input.lastIndexOf(",");

		if (containsOneComma) {
			String input1 = input.substring(0, input.indexOf(','));
			String input2 = input.substring(input.indexOf(',')+2);
			return emailValidator(input1) + "\n" + emailValidator(input2);
		} else {
			return emailValidator(input);
		}
	}
}
