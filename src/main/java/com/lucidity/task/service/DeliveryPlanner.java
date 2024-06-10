package com.lucidity.task.service;

import com.lucidity.task.models.DeliveryRoute;
import com.lucidity.task.models.Location;

import java.util.List;

public class DeliveryPlanner {
    private final Location[] locations;
    private final double[] prepTimes;
    private final double speed;

    public DeliveryPlanner(Location[] locations, double[] prepTimes, double speed) {
        this.locations = locations;
        this.prepTimes = prepTimes;
        this.speed = speed;
    }

    private double[][] calculateDistances() {
        int n = locations.length;
        double[][] distances = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                distances[i][j] = locations[i].distanceTo(locations[j]);
            }
        }
        return distances;
    }

    public DeliveryRoute findBestRoute(List<int[]> possibleRoutes) {
        double[][] distances = calculateDistances();
        DeliveryRoute bestRoute = null;
        double minTime = Double.MAX_VALUE;

        for (int[] route : possibleRoutes) {
            DeliveryRoute currentRoute = new DeliveryRoute(distances, prepTimes, route, speed);
            double time = currentRoute.calculateTotalTime();

            if (time < minTime) {
                minTime = time;
                bestRoute = currentRoute;
            }
        }
        return bestRoute;
    }
}
