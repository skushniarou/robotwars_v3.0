package io.swagger.client.model;

public class Battlefield {
	private String gameId;
	private String robotId;

	public Battlefield(String gameId, String robotId) {
		this.gameId = gameId;
		this.robotId = robotId;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getRobotId() {
		return robotId;
	}

	public void setRobotId(String robotId) {
		this.robotId = robotId;
	}
}
