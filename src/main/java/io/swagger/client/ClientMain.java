package io.swagger.client;

import io.swagger.client.Services.GameService;
import io.swagger.client.Services.RobotService;
import io.swagger.client.View.GameView;
import io.swagger.client.View.OtherView;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.*;

import java.util.List;

import static io.swagger.client.Services.GameService.getGameID;
import static io.swagger.client.Services.GameService.joinLobby;
import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.View.OtherView.*;

public class ClientMain {
	public static void main(String[] args) throws ApiException {

		DefaultApi defaultApi = new DefaultApi();
		List<Robot> robots = defaultApi.apiRobotsGet();

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
							//Lobby erstellen
							case "1":
								try {
									GameService.createLobby(defaultApi);
									GameService.checkGameStatus(defaultApi);
									GameService.joinLobby(defaultApi);
								} catch (ApiException e) {
									OtherView.printOneLineInfoException("Problem bei Lobby erstellen. Folgende Fehler ist aufgetreten:\n" + e.getResponseBody());
								}
								break;
							//Lobby beitreten
							case "2":
								joinLobby(defaultApi);
								break;
							//Bestimmtes Spiel abfragen
							case "3":
								getGameID(defaultApi);
								break;
							//Zurück in Main-Menü
							case "4":
								printOneLineInfo("Game Menü wird verlassen.");
								gameMenuRunning = false;
								break;
							default:
								displayInvalidInputBetweenTwoInt(1,4);
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
								RobotService.createRobot(defaultApi);
								break;

							case "2":
								printOneLineInfo("Liste aller Roboter:");
								if (robots.isEmpty()) {
									printOneLineInfo("Keine Roboter gefunden.");
								} else {
									for (Robot robot : robots) {
										System.out.println(robot);
									}
								}
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
				case "3":
					printOneLineInfo("Programm wird beendet. Auf Wiedersehen in Robotwars!");
					gameRunning = false;
					break;

				default:
					displayInvalidInputBetweenTwoInt(1,3);
			}
		}
	}

}