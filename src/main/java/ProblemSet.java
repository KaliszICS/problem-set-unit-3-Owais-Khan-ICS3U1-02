/**
	* File: Problem Set Unit 3: Email Validator and Parser
	* Author: Owais Ali Khan
	* Date Created: March 29, 2026
	* Date Last Modified: April 7, 2026

	This program takes in 1 or 2 emails seperated by a comma and validates them based on a set of rules and exceptions.
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

	// This function validates the email by applying the basic rules and then the exceptions.
	public static String emailValidatorHelper(String email) {

		String gmailNormalized = ""; // Message to concatenate to final output if email ends in "gmail.com".

		if (!(email.contains("@"))) {
			return email + ": Invalid: Missing @";
		}

		// Ensure there is exactly one '@' symbol.
		if (email.indexOf("@") != email.lastIndexOf("@")) {
			return email + ": Invalid: Multiple @";
		}	

		int atIndex = email.indexOf("@");  // Find position of '@' to split local part and domain.
		String local;

		// Gmails ingnore "._+" in the local part.
		if (email.endsWith("@gmail.com")) {
			gmailNormalized = " (Gmail normalized)";
			local = email.substring(0, atIndex); // Take the local part (everything before @).
			local = local.replaceAll("\\.", "")
						 .replaceAll("\\+", "")
						 .replaceAll("_", "");
		} else {
			local = email.substring(0, atIndex);
		}

		// Use local to check for leading "." in case it was normalized.
		if (local.startsWith(".") || email.endsWith(".")) {
			return email + ": Invalid: Starts or ends with dot";
		}

		if (email.contains(" ")) {
			return email + ": Invalid: Contains spaces";
		}

		if (local.length() > 64) {
			return email + ": Invalid: Local part too long";
		}
		if (local.length() < 1) {
			return email + ": Invalid: Local part too short";
		}

		// localRaw is used to return the unnormalized local in case of exception C.
		String localRaw = email.substring(0, atIndex);  
		String domain = email.substring(atIndex + 1);  // Take the domain (everthing after @).

		if (!domain.contains(".")) {
			return email + ": Invalid: No dot in domain";
		}
		if (domain.startsWith(".")) {
			return email + ": Invalid: Domain starts with a dot";
		}

		// Find the position of the last "." in domain to parse the domain extension. 
		int domainDotIndex = domain.lastIndexOf(".");
		String domainExtension = domain.substring(domainDotIndex+1);  // Get the domain extension (everything after the last ".").


		if (domainExtension.length() < 2 || domainExtension.length() > 6) {
			return email + ": Invalid: Invalid domain extension length";
		}

		// Exception B doen not allow the domain to have the characters '+' and '_'.
		if (domain.contains("+") || domain.contains("_")) {
			return email + ": Invalid: Domain contains invalid characters (\'+\' and/or \'_\')";
		}
		
		// Final output.
		return email + ": Valid" + gmailNormalized + " | Local: "  + localRaw + " | Domain: " + domain;
	}

	// This function parses the input into 1 or 2 emails depending on the user input.
	public static String emailValidator(String input) {

		// Check if the input contains ", " seperating the emails.
		int commaIndex = input.indexOf(", ");

		// If there is no ", " then assume it is 1 email, else split it into 2.
		if (commaIndex == -1) {
			return emailValidatorHelper(input);  // 1 email
	
		} else {
			String input1 = input.substring(0, commaIndex);
			String input2 = input.substring(commaIndex + 2);
			return emailValidatorHelper(input1) + "\n" + emailValidatorHelper(input2);  // 2 emails
		} 
	}
}

