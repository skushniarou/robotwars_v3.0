package io.swagger.client.View;

public class GameView {
	public static void printMainMenu(){
		System.out.println("Willkommen zu Robot Wars!");
		System.out.println("Bitte wählen Sie ein Menü:");
		System.out.println("1. Game");
		System.out.println("2. Robots");
		System.out.println("3. Beenden");
	}

	public static void printGameMenu(){
		System.out.println("Game Menü:");
		System.out.println("1. Spiel erstellen");
		System.out.println("2. Spiel beitreten");
		System.out.println("3. Bestimmtes Spiel abfragen");
		System.out.println("4. Zurück");
	}

	public static void printRobotMenu(){
		System.out.println("Robots Menü:");
		System.out.println("1. Roboter erstellen");
		System.out.println("2. Liste aller Roboter anzeigen");
		System.out.println("3. Zurück");
	}

}
