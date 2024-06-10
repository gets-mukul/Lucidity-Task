package com.lucidity.task;

import com.lucidity.task.models.DeliveryRoute;
import com.lucidity.task.models.Location;
import com.lucidity.task.service.DeliveryPlanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Location[] locations = {new Location(12.9345, 77.6101), // Aman (starting point)
                new Location(12.9344, 77.6268), // R1
                new Location(12.9354, 77.6192), // R2
                new Location(12.9372, 77.6221), // C1
                new Location(12.9378, 77.6145)  // C2
        };

        // Preparation times in hours,
        // assuming all items will take same time to prepare by Restaurant
        double[] prepTimes = {0.5, 0.3}; // pt1, pt2

        // Define possible routes
        List<int[]> possibleRoutes = Arrays.asList(new int[]{0, 1, 3, 2, 4}, // Aman -> R1 -> C1 -> R2 -> C2
                new int[]{0, 2, 4, 1, 3}, // Aman -> R2 -> C2 -> R1 -> C1
                new int[]{0, 1, 2, 3, 4}, // Aman -> R1 -> R2 -> C1 -> C2
                new int[]{0, 2, 1, 4, 3}  // Aman -> R2 -> R1 -> C2 -> C1
        );

        DeliveryPlanner planner = new DeliveryPlanner(locations, prepTimes, 20); // speed 20 km/hr
        DeliveryRoute bestRoute = planner.findBestRoute(possibleRoutes);

        // Print the best route and the total time
        System.out.println("Best Route:");
        for (int point : bestRoute.getRoute()) {
            System.out.print(point + " ");
        }
        System.out.println("\nTotal Time: " + bestRoute.calculateTotalTime() + " hours");
    }

}
