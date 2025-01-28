package io.swagger.client.Services;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Battlefield;
import io.swagger.client.model.Game;
import io.swagger.client.model.JoinGame;
import io.swagger.client.model.NewGame;

import static io.swagger.client.Services.InputService.userInputStr;
import static io.swagger.client.View.OtherView.displayInvalidInputSpecified;
import static io.swagger.client.View.OtherView.printOneLineInfo;


public class GameService {
	public static void createLobby(DefaultApi defaultApi, Battlefield battlefield) throws ApiException {
		NewGame newGame = new NewGame();
		String mapId = "d2d0b803-955d-4367-8fdd-c8c3f94fecbb";
		newGame.setMapId(mapId);
		defaultApi.apiGamesGamePost(newGame);
		Game newGameData = defaultApi.apiGamesGamePost(newGame);
		battlefield.setGameId(newGameData.getId());
		printOneLineInfo("Spiel-ID von dieser Lobby ist:" + battlefield.getGameId());
	}

	public static void joinLobby(DefaultApi defaultApi, Battlefield battlefield) throws ApiException {
		JoinGame joinGame = new JoinGame();
		joinGame.setRobotId(battlefield.getRobotId());
		defaultApi.apiGamesGameIdJoinPost(joinGame, battlefield.getRobotId());
	}

	public static String getGameIDStatus(DefaultApi defaultApi) {
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
			if(getGameIDStatus(defaultApi).equals(Game.StatusEnum.STARTED)){
				gameStarted = true;
				printOneLineInfo("Spiel gestartet. Let Robot Wars begin!!!");
			}
			try {
				printOneLineInfo("z-z-Z");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
	}
}
