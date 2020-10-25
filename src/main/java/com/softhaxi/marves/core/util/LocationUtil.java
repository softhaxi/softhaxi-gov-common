package com.softhaxi.marves.core.util;

import org.springframework.stereotype.Component;

@Component
public class LocationUtil {
    public double calculateDistanceKM(double latitude1, double longitude1, double latitude2, double longitude2) {
        long radius = 6371; // km
        double dLatitude = (latitude1 - latitude2) * (Math.PI / 180);
        double dLongitude = (longitude1 - longitude2) * (Math.PI / 180);

        double dLatitude1 = latitude1 * (Math.PI / 180);
        double dLatitude2 = latitude2 *  (Math.PI / 180);
        
        double a = Math.sin(dLatitude/2) * Math.sin(dLatitude/2) +
                Math.sin(dLongitude/2) * Math.sin(dLongitude/2) * Math.cos(dLatitude1) * Math.cos(dLatitude2); 
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return radius * c;
    }
    
}
