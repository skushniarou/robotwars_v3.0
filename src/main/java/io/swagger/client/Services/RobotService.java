package io.swagger.client.Services;

import io.swagger.client.ApiException;
import io.swagger.client.View.OtherView;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.NewRobot;

import java.util.Scanner;

import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.Services.ValidationService.getValidBigDecimal;
import static io.swagger.client.View.OtherView.printOneLineInfo;
import static io.swagger.client.View.OtherView.printOneLineInfoException;

public class RobotService {
	public static void createRobot(DefaultApi defaultApi){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Roboter erstellen:");
		try {
			NewRobot newRobot = new NewRobot();
			newRobot.setName(userInputStr("Name: "));
			newRobot.setHealth(getValidBigDecimal(scanner, "health: "));
			newRobot.setAttackDamage(getValidBigDecimal(scanner, "attackDamage: "));
			newRobot.setAttackRange(getValidBigDecimal(scanner, "attackRange: "));
			newRobot.setMovementRate(getValidBigDecimal(scanner, "movementRate: "));

			defaultApi.apiRobotsRobotPost(newRobot);
			printOneLineInfo("Robot ist erfolgreich erstellt!");
		} catch (ApiException e) {
			printOneLineInfoException("Fehler beim Erstellen des Roboters: " + e.getResponseBody());
		}
	}
}
