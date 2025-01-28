package io.swagger.client;

import io.swagger.client.Services.BattleService;
import io.swagger.client.Services.GameService;
import io.swagger.client.Services.RobotService;
import io.swagger.client.View.GameView;
import io.swagger.client.View.OtherView;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Battlefield;

import static io.swagger.client.Services.GameService.getGameIDStatus;
import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.Services.RobotService.robotMenu;
import static io.swagger.client.View.OtherView.*;

public class ClientMain {
	public static void main(String[] args) throws ApiException {

		DefaultApi defaultApi = new DefaultApi();
		Battlefield battlefield = new Battlefield("","");
		String gameID = "";


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
									GameService.createLobby(defaultApi,battlefield);
									RobotService.robotMenu(defaultApi,battlefield);
									GameService.joinLobby(defaultApi,battlefield);
									GameService.checkGameStatus(defaultApi,battlefield);
									BattleService.battleMenu();
								} catch (ApiException e) {
									OtherView.printOneLineInfoException("Problem bei Lobby erstellen. Folgende Fehler ist aufgetreten:\n" + e.getResponseBody());
								}
								break;
							//Lobby beitreten
							case "2":
								RobotService.robotMenu(defaultApi, battlefield);
								GameService.joinLobby(defaultApi,battlefield);
								GameService.checkGameStatus(defaultApi, battlefield);
								BattleService.battleMenu();
								break;
							//Bestimmtes Spiel abfragen
							case "3":
								getGameIDStatus(defaultApi, battlefield);
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
					robotMenu(defaultApi, battlefield);
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