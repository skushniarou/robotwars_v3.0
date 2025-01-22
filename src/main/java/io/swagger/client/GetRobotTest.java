package io.swagger.client;

import io.swagger.client.api.DefaultApi;
import io.swagger.client.model.Robot;

import java.util.List;

import static io.swagger.client.Services.InputService.userInputInt;


public class GetRobotTest {
	public static void main(String[] args) throws ApiException {

		String robotId = "376c9574-ae91-4e1c-9abd-e8d501c7d666";

		DefaultApi defaultApi = new DefaultApi();
		List<Robot> robots = defaultApi.apiRobotsGet();
		List<Robot> oneRobot = defaultApi.apiRobotsRobotIdGet(robotId);

		int ausw = userInputInt("1 oder 2");
		if (ausw == 1){
			for (Robot robot : robots) {
				System.out.println(robot);
			}
		} else {
			System.out.println(oneRobot);
		}
	}
}
