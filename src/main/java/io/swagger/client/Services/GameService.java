package io.swagger.client.Services;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Battlefield;
import io.swagger.client.model.Game;
import io.swagger.client.model.JoinGame;
import io.swagger.client.model.NewGame;

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
		defaultApi.apiGamesGameIdJoinPost(joinGame, battlefield.getGameId());
	}

	public static Enum<Game.StatusEnum> getGameIDStatus(DefaultApi defaultApi, Battlefield battlefield) {
		try {
			Game game = defaultApi.apiGamesGameIdGet(battlefield.getGameId());
			printOneLineInfo("Es wird gewartet. Spielstatus: " + game);
			return game.getStatus();
		} catch (ApiException e) {
			displayInvalidInputSpecified("Spiel-ID ungültig" + e.getResponseBody());
			return null;
		}
	}

	public static void checkGameStatus(DefaultApi defaultApi, Battlefield battlefield) throws ApiException {
		boolean gameStarted = false;
		while (!gameStarted){
			if(getGameIDStatus(defaultApi,battlefield).equals(Game.StatusEnum.STARTED)){
				gameStarted = true;
			} else{
			try {
				printOneLineInfo("z-z-Z");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
		}
		printOneLineInfo("Spiel gestartet. Let Robot Wars begin!!!");
	}
	}
}
