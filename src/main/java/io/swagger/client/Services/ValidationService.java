package io.swagger.client.Services;

import java.math.BigDecimal;
import java.util.Scanner;

public class ValidationService {
	public static BigDecimal getValidBigDecimal(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			if (scanner.hasNextBigDecimal()) {
				return scanner.nextBigDecimal();
			} else {
				System.out.println("Ungültige Eingabe. Bitte geben Sie eine Zahl ein.");
				scanner.next(); // Ungültige Eingabe überspringen
			}
		}
	}
}
