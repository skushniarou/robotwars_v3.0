package io.swagger.client;

import io.swagger.client.Services.BattleService;
import io.swagger.client.Services.GameService;
import io.swagger.client.View.GameView;
import io.swagger.client.View.OtherView;
import io.swagger.client.api.DefaultApi;

import static io.swagger.client.Services.GameService.getGameIDStatus;
import static io.swagger.client.Services.GameService.joinLobby;
import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.Services.RobotService.robotMenu;
import static io.swagger.client.View.OtherView.*;

public class ClientMain {
	public static void main(String[] args) throws ApiException {

		DefaultApi defaultApi = new DefaultApi();


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
									robotMenu(defaultApi);
									GameService.joinLobby(defaultApi);
									GameService.checkGameStatus(defaultApi);
									BattleService.battleMenu();
								} catch (ApiException e) {
									OtherView.printOneLineInfoException("Problem bei Lobby erstellen. Folgende Fehler ist aufgetreten:\n" + e.getResponseBody());
								}
								break;
							//Lobby beitreten
							case "2":
								robotMenu(defaultApi);
								joinLobby(defaultApi);
								GameService.checkGameStatus(defaultApi);
								BattleService.battleMenu();
								break;
							//Bestimmtes Spiel abfragen
							case "3":
								getGameIDStatus(defaultApi);
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
					robotMenu(defaultApi);
				case "3":
					printOneLineInfo("Programm wird beendet. Auf Wiedersehen in Robot Wars!");
					gameRunning = false;
					break;

				default:
					displayInvalidInputBetweenTwoInt(1,3);
			}
		}
	}

}