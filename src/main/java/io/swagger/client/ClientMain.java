package io.swagger.client;

import io.swagger.client.Services.GameService;
import io.swagger.client.View.GameView;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.*;

import java.util.List;
import java.util.Scanner;

import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.Services.ValidationService.getValidBigDecimal;

public class ClientMain {
	public static void main(String[] args) throws ApiException {

		DefaultApi defaultApi = new DefaultApi();
		List<Robot> robots = defaultApi.apiRobotsGet();

		Scanner scanner = new Scanner(System.in);
		boolean gameRunning = true;

		while (gameRunning) {
			GameView.printMainMenu();
			String choice = userInputStr("Eingabe: ");

			switch (choice) {
				case "1":
					boolean gameMenuRunning = true;
					while (gameMenuRunning) {
						GameView.printGameMenu();
						String gameChoice = userInputStr("Eingabe: ");

						switch (gameChoice){
							case "1":
								GameService.createLobby(defaultApi);
								break;
							case "2":
								JoinGame joinGame = new JoinGame();

								String gameID = userInputStr("Game-ID eingeben:");
								joinGame.setRobotId(userInputStr("Robot-ID eingeben: "));
								defaultApi.apiGamesGameIdJoinPost(joinGame, gameID);
								break;
							case "3":
								System.out.println("Spiel abfragen:");
								System.out.print("Spiel-ID: ");
								String gameId = userInputStr("Eingabe: ");

								Game game = defaultApi.apiGamesGameIdGet(gameId);
								System.out.println("Gefundenes Spiel: " + game);
								break;
							case "4":
								System.out.println("Game Menü wird verlassen.");
								gameMenuRunning = false;
								break;
							default:
								System.out.println("Ungültige Eingabe. Bitte wählen Sie 1, 2, 3 oder 4.");
								break;
						}
					}
					break;
				case "2":
					boolean robotsMenuRunning = true;
					while (robotsMenuRunning) {
						GameView.printRobotMenu();
						String robotChoice = userInputStr("Eingabe: ");

						switch (robotChoice) {
							case "1":
								System.out.println("Roboter erstellen:");
								try {
									NewRobot newRobot = new NewRobot();
									System.out.print("Name: ");
									newRobot.setName(scanner.nextLine());
									newRobot.setHealth(getValidBigDecimal(scanner, "health: "));
									newRobot.setAttackDamage(getValidBigDecimal(scanner, "attackDamage: "));
									newRobot.setAttackRange(getValidBigDecimal(scanner, "attackRange: "));
									newRobot.setMovementRate(getValidBigDecimal(scanner, "movementRate: "));

									defaultApi.apiRobotsRobotPost(newRobot);
									System.out.println("Roboter erfolgreich erstellt");
								} catch (ApiException e) {
									System.err.println("Fehler beim Erstellen des Roboters: " + e.getResponseBody());
								}
								break;

							case "2":
								System.out.println("Liste aller Roboter:");
								if (robots.isEmpty()) {
									System.out.println("Keine Roboter gefunden.");
								} else {
									for (Robot robot : robots) {
										System.out.println(robot);
									}
								}
								break;

							case "3":
								System.out.println("Robot Menü wird verlassen.");
								robotsMenuRunning = false;
								break;

							default:
								System.err.println("Ungültige Eingabe. Bitte wählen Sie 1, 2 oder 3.");
								break;
						}
						System.out.println();
					}
				case "3":
					System.out.println("Programm wird beendet. Auf Wiedersehen!");
					gameRunning = false;
					break;

				default:
					System.err.println("Ungültige Eingabe. Bitte wählen Sie 1, 2 oder 3.");
					scanner.close();

			}
		}
	}

}