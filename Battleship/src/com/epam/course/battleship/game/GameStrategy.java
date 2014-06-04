package com.epam.course.battleship.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import com.epam.course.battleship.Point;

public class GameStrategy {

	private List<Point> unknownFields = new ArrayList<>();
	private List<Point> hits = new LinkedList<>();
	private final Random random = new Random();
	private Point lastTarget;
	
	public GameStrategy(Point boardSize) {
		for (int i = 0; i < boardSize.getY(); i++) {
			for (int j = 0; j < boardSize.getX(); j++) {
				unknownFields.add(new Point(j, i));
			}
		}
	}
	
	public Point getNextTarget() {
		lastTarget = calculateNextTarget();
		return lastTarget;
	}
	
	private Point calculateNextTarget() {
		for (Point hit : hits) {
			for (int i = -1; i <= 1; i++) {
				for (int j = -1; j <= 1; j++) {
					if (Math.abs(i) == Math.abs(j)) {
						continue;
					}
					
					Point potential = new Point(j + hit.getX(), i + hit.getY());
					if (unknownFields.contains(potential)) {
						return potential;
					}
				}
			}
		}
		
		return unknownFields.get(random.nextInt(unknownFields.size()));
	}
	
	public void lastTargetMissed() {
		unknownFields.remove(lastTarget);
	}
	
	public void lastTargetHit() {
		unknownFields.remove(lastTarget);
		hits.add(lastTarget);
	}
}
