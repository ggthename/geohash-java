package org.my;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode
public class GcsPoint implements Serializable {
    private final double latitude;
    private final double longitude;

    public GcsPoint(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
        if(Math.abs(latitude) > 90 || Math.abs(longitude) > 180){
            throw new IllegalArgumentException("out of range");
        }
    }

    public double getValue(GcsType geoHashType){
        switch (geoHashType){
            case LONGITUDE: return this.longitude;
            case LATITUDE: return this.latitude;
        }
        return 0d;
    }
}
