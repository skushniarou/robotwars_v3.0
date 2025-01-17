package io.swagger.client.Services;

import io.swagger.client.ApiException;
import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Game;
import io.swagger.client.model.NewGame;

public class GameService {
	public static void createLobby(DefaultApi defaultApi) throws ApiException {
		NewGame newGame = new NewGame();
		String mapId = "d2d0b803-955d-4367-8fdd-c8c3f94fecbb";
		newGame.setMapId(mapId);
		defaultApi.apiGamesGamePost(newGame);
		Game newGameData = defaultApi.apiGamesGamePost(newGame);
		System.out.println("Spiel-ID :" + newGameData.getId());
	}



	public static void joinLobby(){

	}
}
