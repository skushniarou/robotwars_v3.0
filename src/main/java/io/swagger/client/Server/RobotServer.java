package io.swagger.client.Server;

import io.swagger.client.model.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/robot")
public class RobotServer {

    //Konstruktor für Beispiel Robotererstellung
    public RobotServer() {
        robots.add(new Robot("1", "testRobot", 5, 5, 5, 5));
        robots.add(new Robot("2", "testRobot2", 8, 3, 1, 2));
        robots.add(new Robot("3", "testRobot3", 1, 5, 3, 4));
    }

    List<Robot> robots = new ArrayList<Robot>();

    @GetMapping("/getFirstRobotInList")
    public Robot getFirstRobotInList() {
        return robots.getFirst();
    }

    @GetMapping("/getRobotList")
    public List<Robot> getRobotList() {
        return robots;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createRobot(@RequestBody Robot robot){
        this.robots.add(robot);
    }

    //Testausgabe
    @GetMapping
    public String hello() {
        return "Hello";
    }
}
