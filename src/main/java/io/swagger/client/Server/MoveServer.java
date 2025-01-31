package io.swagger.client.Server;

import io.swagger.client.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/moves")
public class MoveServer {

    //Konstruktor für Beispiel Moves
    public MoveServer() {
        moves.add(new NewMove("1","1",  MovementType.END, Align.S, 0));
        moves.add(new NewMove("2", "2", MovementType.END, Align.N, 44));
    }

    List<NewMove> moves = new ArrayList<>();

    @GetMapping("/getFirstMove")
    public NewMove getFirstMoveInList() {
        return moves.getFirst();
    }

    @GetMapping("/getLastMove")
    public NewMove getLastMoveInList() {
        return moves.getLast();
    }

    @GetMapping("/{id}")
    public NewMove getMoveById(@PathVariable(value = "id") int id) {
        return moves.get(id);
    }

    @GetMapping("/getMoveList")
    public List<NewMove> getMoveList() {
        return moves;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void newMove(@RequestBody NewMove move) {
        this.moves.add(move);
    }
}
