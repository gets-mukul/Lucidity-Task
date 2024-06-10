package com.lucidity.task.models;

public class DeliveryRoute {
    private final double[][] distances;
    private final double[] prepTimes;
    private final int[] route;
    private final double speed;

    public DeliveryRoute(double[][] distances, double[] prepTimes, int[] route, double speed) {
        this.distances = distances;
        this.prepTimes = prepTimes;
        this.route = route;
        this.speed = speed;
    }

    private double travelTime(double distance) {
        return distance / speed;
    }

    public double calculateTotalTime() {
        double totalTime = 0;

        for (int i = 0; i < route.length - 1; i++) {
            int from = route[i];
            int to = route[i + 1];
            totalTime += travelTime(distances[from][to]);

            // Add preparation time when picking up from a restaurant
            if (from == 0 && (to == 1 || to == 2)) { // from Aman to Restaurant R1 or R2
                totalTime += prepTimes[to - 1];
            }
        }
        return totalTime;
    }

    public int[] getRoute() {
        return route;
    }
}
