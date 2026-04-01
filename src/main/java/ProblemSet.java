/**
	* File: Problem Set Unit 3: Email Validator and Parser
	* Author: Owais Ali Khan
	* Date Created: March 29, 2026
	* Date Last Modified: March 31, 2026

	This program takes in 1 or 2 emails seperated by a comma and validates them based on a set of rules.
	*/


import java.util.Scanner;

public class ProblemSet {

	public static void main(String args[]) {

		// Get the input from the user.
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
	

	// This function validates the email by applying the basic rules and then the exeptions.
	public static String emailValidatorHelper(String email) {

		if (!(email.contains("@"))) {
			return email + ": Invalid: Missing @";
		}

		// Compare the first and last index of @, if they differ, then there is more than 1.
		if (!(email.indexOf("@") == email.lastIndexOf("@"))) {
			return email + ": Invalid: Multiple @";
		}	

		if (email.startsWith(".") || email.endsWith(".")) {
			return email + ": Invalid: Starts or ends with dot";
		}

		if (email.contains(" ")) {
			return email + ": Invalid: Contains spaces";
		}

		int atIndex = email.indexOf("@");  // Take the index of the @ so we can parse the local part and the domain.
		String local = email.substring(0, atIndex);  // Take the local part (everything before @)

		if (local.length() > 64) {
			return email + ": Invalid: Local part too long";
		}
		if (local.length() < 1) {
			return email + ": Invalid: Local part too short";
		}

		String domain = email.substring(atIndex+1);  // Take the doamin (everthing after @)

		if (!domain.contains(".")) {
			return email + ": Invalid: No dot in domain";
		}

		// Get the index of the last "." in domain to parse the domain extension. 
		int domainDotIndex = domain.lastIndexOf(".");
		String domainExtention = domain.substring(domainDotIndex+1);  // Get the domain extension (everything after the last ".")


		if (domainExtention.length() < 2 || domainExtention.length() > 6) {
			return email + ": Invalid: Invalid domain extension length";
		}

		if (!containsOnlyLetters(domainExtention)){
			return email + ": Invalid: Domain extension contains non-letters";
		}

		// Exeption B only allows the local part to have the characters "+" and "_".
		if (domain.contains("+") || domain.contains("_")) {
			return email + ": Invalid: Domain contains \'+\' and/or \'_\' ";
		}
		
		// Exeption C applies to emails that have the domain "gmail.com" so we add "(Gmail normalized)" to the final output.
		if (domain.equals("gmail.com")) {
			return email + ": Valid (Gmail normalized) | Local: "  + local + " | Domain: " + domain;
		}

		// Final output.
		return email + ": Valid | Local: "  + local + " | Domain: " + domain;
	}

	// This function parses the input into 1 or 2 emails depending on the user input.
	public static String emailValidator(String input) {

		// Check if the input contains 1 ", ".
		boolean containsOneComma = input.contains(", ") && input.indexOf(", ") == input.lastIndexOf(", ");

		// Split the input into 2 emails if a comma is present, otherwise assume it is 1.
		if (containsOneComma) {

			// Handle edge cases where user may use invalid format.
			if (input.length()<4) {
				return "Bro are you even tring to type an email?";
			}

			// Get 2 emails.
			String input1 = input.substring(0, input.indexOf(','));
			String input2 = input.substring(input.indexOf(',')+2);

			return emailValidatorHelper(input1) + "\n" + emailValidatorHelper(input2);  // 2 emails
		} else {
			return emailValidatorHelper(input);  // 1 email
		}
	}
}
