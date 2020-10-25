package com.softhaxi.marves.core.util;

import org.springframework.stereotype.Component;

@Component
public class LocationUtil {
    public double calculateDistanceKM(double latitude1, double longitude1, double latitude2, double longitude2) {
        long radius = 6371; // km
        double dLatitude = Math.toRadians(latitude1 - latitude2);
        double dLongitude = Math.toRadians(longitude1 - longitude2);

        double dLatitude1 = Math.toRadians(latitude1);
        double dLatitude2 = Math.toRadians(latitude2);
        
        double a = Math.sin(dLatitude/2) * Math.sin(dLatitude/2) +
                Math.sin(dLongitude/2) * Math.sin(dLongitude/2) * Math.cos(dLatitude1) * Math.cos(dLatitude2); 
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return radius * c;

        // double tetha = longitude1 - longitude2;
        // double dist = Math.sin(Math.toRadians(latitude1)) * Math.sin(Math.toRadians(latitude2)) +
        //     Math.cos(Math.toRadians(latitude1)) * Math.cos(Math.toRadians(latitude2)) *
        //     Math.cos(tetha);
        // dist = Math.cos(dist);
        // dist = Math.toDegrees(dist);
        // dist = dist * 60 * 1.1515;
        // //dist * 0.8684 // N : Nautical Miles
        // return dist * 1.609344;  // KM
    }
    
}
