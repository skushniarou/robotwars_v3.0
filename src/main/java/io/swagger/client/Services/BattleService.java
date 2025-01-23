package io.swagger.client.Services;

import io.swagger.client.View.BattleView;

import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.View.OtherView.displayInvalidInputBetweenTwoInt;
import static io.swagger.client.View.OtherView.printOneLineInfo;

public class BattleService {
	public static void battleMenu(){
		boolean battleRunning = false;
		while (battleRunning) {
			BattleView.printBattleMenu();
			String playerChoice = userInputStr("Eingabe: ");

			switch (playerChoice) {
				case "1":

					break;

				case "2":

					break;

				case "3":
					printOneLineInfo("Robot Menü wird verlassen.");

					break;

				//Zug beenden
				case "4":
					break;
				default:
					displayInvalidInputBetweenTwoInt(1, 4);
					break;
			}
		}
	}
}
