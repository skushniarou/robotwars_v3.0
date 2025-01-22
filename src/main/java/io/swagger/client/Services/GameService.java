package io.swagger.client.Services;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Game;
import io.swagger.client.model.JoinGame;
import io.swagger.client.model.NewGame;

import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.View.OtherView.displayInvalidInputSpecified;
import static io.swagger.client.View.OtherView.printOneLineInfo;


public class GameService {
	public static void createLobby(DefaultApi defaultApi) throws ApiException {
		NewGame newGame = new NewGame();
		String mapId = "d2d0b803-955d-4367-8fdd-c8c3f94fecbb";
		newGame.setMapId(mapId);
		defaultApi.apiGamesGamePost(newGame);
		Game newGameData = defaultApi.apiGamesGamePost(newGame);
		printOneLineInfo("Spiel-ID von dieser Lobby ist:" + newGameData.getId());
	}

	public static void joinLobby(DefaultApi defaultApi){
		JoinGame joinGame = new JoinGame();
		String gameID = userInputStr("Game-ID eingeben:");
		joinGame.setRobotId(userInputStr("Robot-ID eingeben: "));
		defaultApi.apiGamesGameIdJoinPost(joinGame, gameID);
	}

	public static String getGameID(DefaultApi defaultApi) {
		String gameId = userInputStr("Bitte Spiel-ID eingeben: ");
		try {
			Game game = defaultApi.apiGamesGameIdGet(gameId);
			printOneLineInfo("Gefundenes Spiel: " + game);
			return game.getStatus().toString();
		} catch (ApiException e) {
			displayInvalidInputSpecified("Spiel-ID" + e.getResponseBody());
			return null;
		}
	}

	public static void checkGameStatus(DefaultApi defaultApi) throws ApiException {
		boolean gameStarted = false;
		while (!gameStarted){
			if(getGameID(defaultApi).equals(Game.StatusEnum.STARTED)){
				gameStarted = true;
			}
			try {
				Thread.sleep(10000);
				printOneLineInfo("z-z-Z");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
