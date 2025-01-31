package io.swagger.client.Server;

import io.swagger.client.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/game")
public class GameServer {

    //Konstruktor für Beispiel Game
    public GameServer() {
        games.add(new Game("1", "1", new ArrayList<PlayerRobot>(), new ArrayList<Move>(), Game.StatusEnum.STARTED));
        games.add(new Game("2", "1", new ArrayList<PlayerRobot>(), new ArrayList<Move>(), Game.StatusEnum.INITIAL));
    }

    List<Game> games = new ArrayList<Game>();

    @GetMapping("/games")
    public List<Game> getGameList() {
        return games;
    }

    @GetMapping("{id}")
    public Game getGameById(@PathVariable(value = "id") int id) {
        return games.get(id);
    }

    @GetMapping("/{id}/moves")
    public List<Move> getMovesFromGame(@PathVariable(value = "id") int id) {
        return games.get(id).getMoves();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newGame(@RequestBody Game game) {
        this.games.add(game);
    }

}
