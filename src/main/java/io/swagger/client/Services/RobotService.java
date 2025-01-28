package io.swagger.client.Services;

import io.swagger.client.ApiException;
import io.swagger.client.View.GameView;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Battlefield;
import io.swagger.client.model.NewRobot;
import io.swagger.client.model.Robot;

import java.util.List;
import java.util.Scanner;

import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.Services.ValidationService.getValidBigDecimal;
import static io.swagger.client.View.OtherView.*;

public class RobotService {
	public static void createRobot(DefaultApi defaultApi,Battlefield battlefield){
		Scanner scanner = new Scanner(System.in);
		System.out.println("Roboter erstellen:");
		try {
			NewRobot newRobot = new NewRobot();
			newRobot.setName(userInputStr("Name: "));
			newRobot.setHealth(getValidBigDecimal(scanner, "health: "));
			newRobot.setAttackDamage(getValidBigDecimal(scanner, "attackDamage: "));
			newRobot.setAttackRange(getValidBigDecimal(scanner, "attackRange: "));
			newRobot.setMovementRate(getValidBigDecimal(scanner, "movementRate: "));

			Robot robot = defaultApi.apiRobotsRobotPost(newRobot);
			battlefield.setRobotId(robot.getId());
			printOneLineInfo("Robot ist erfolgreich erstellt! ID: " + battlefield.getRobotId());
		} catch (ApiException e) {
			printOneLineInfoException("Fehler beim Erstellen des Roboters: " + e.getResponseBody());
		}
	}

	public static void chooseRobotFromList (DefaultApi defaultApi, Battlefield battlefield) throws ApiException {
		List<Robot> robots = defaultApi.apiRobotsGet();

		printOneLineInfo("Liste aller Roboter:");
		if (robots.isEmpty()) {
			printOneLineInfo("Keine Roboter gefunden.");
		} else {
			for (Robot robot : robots) {
				System.out.println(robot);
			}
		}
		battlefield.setRobotId(userInputStr("Robot-ID kopieren und einfügen: "));
	}

	public static void robotMenu(DefaultApi defaultApi, Battlefield battlefield) throws ApiException {
		boolean robotsMenuRunning = true;
		while (robotsMenuRunning) {
			GameView.printRobotMenu();
			String robotChoice = userInputStr("Eingabe: ");

			switch (robotChoice) {
				case "1":
					createRobot(defaultApi,battlefield);
					break;

				case "2":
					chooseRobotFromList(defaultApi,battlefield);
					break;

				case "3":
					printOneLineInfo("Robot Menü wird verlassen.");
					robotsMenuRunning = false;
					break;

				default:
					displayInvalidInputBetweenTwoInt(1,3);
					break;
			}
			System.out.println();
		}
	}
}
