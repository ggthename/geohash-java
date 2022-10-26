package org.my;

import java.util.HashSet;
import java.util.Set;

public class GeoHashModule {

    static final char[] hashString= new char[]{'0','1','2','3','4','5','6','7','8','9','b','c','d','e','f','g',
            'h','j','k','m','n','p','q','r','s','t','u','v','w','x','y','z'};

    public static final double DIFF_LAT_1m = 0.0000089831;
    public static final double DIFF_LNG_1m = 0.0000089832;

    public static Set<String> bBoxes(GcsPoint sw, GcsPoint ne, GeoHashLevel geoHashLevel){

        Set<String> ret = new HashSet();

        GcsPoint nw = new GcsPoint(ne.getLatitude(), sw.getLongitude() );
        GcsPoint se = new GcsPoint(sw.getLatitude(), ne.getLongitude() );

        ret.add(GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(sw, geoHashLevel)));
        ret.add(GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(ne, geoHashLevel)));
        ret.add(GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(nw, geoHashLevel)));
        ret.add(GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(se, geoHashLevel)));

        //
        GcsPoint basePoint = new GcsPoint(sw.getLatitude(), sw.getLongitude());

        while(basePoint.getLatitude() >= sw.getLatitude() &&
                basePoint.getLatitude() <= ne.getLatitude() ){

            GcsPoint movingPoint = new GcsPoint(basePoint.getLatitude(), basePoint.getLongitude());

            while(movingPoint.getLongitude() >= sw.getLongitude() &&
                    movingPoint.getLongitude() <= ne.getLongitude()){

                String value = GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(movingPoint, geoHashLevel));
                ret.add(value);

                movingPoint=movingPoint.addLongitude(DIFF_LNG_1m * geoHashLevel.getCellWidthMeter());
            }
            // add boundary longitude
            String value = GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(new GcsPoint(movingPoint.getLatitude(), ne.getLongitude()), geoHashLevel));
            ret.add(value);
            //
            basePoint = basePoint.addLatitude(DIFF_LAT_1m * geoHashLevel.getCellHeightMeter());
        }
        // add boundary latitude
        String value = GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(new GcsPoint(ne.getLatitude(), basePoint.getLongitude()), geoHashLevel));
        ret.add(value);
        //

        return ret;
    }

    public static String getGeoHashValueFromCoordinate(GcsPoint gcsPoint, GeoHashLevel geoHashLevel){

        return GeoHashModule.getGeoHashUsingBase32(getBase32FromLongitudeLatitude(gcsPoint, geoHashLevel));

    }

    private static String getGeoHashUsingBase32(String input){
        int idx =0;
        String ret ="";
        while(idx < input.length()){
            int val = Integer.parseInt(input.substring(idx, idx+5), 2);
            ret+=hashString[val];
            idx = idx+5;
        }
        return ret;
    }

    private static String getBase32FromLongitudeLatitude(GcsPoint point, GeoHashLevel geoHashLevel){
        String longitudeBits = geoHashFromLongitudeLatitude(point, geoHashLevel, GcsType.LONGITUDE);
        String latitudeBits = geoHashFromLongitudeLatitude(point, geoHashLevel, GcsType.LATITUDE);
        String ret = "";

        int idx1=0;
        int idx2=0;

        while(idx1 < longitudeBits.length() && idx2 < latitudeBits.length()){
            ret += longitudeBits.charAt(idx1++);
            ret += latitudeBits.charAt(idx2++);
        }

        while(idx1 < longitudeBits.length()){
            ret += longitudeBits.charAt(idx1++);
        }

        return ret;
    }

    private static String geoHashFromLongitudeLatitude(GcsPoint gcsPoint, GeoHashLevel geoHashLevel, GcsType geoHashType){
        double val = gcsPoint.getValue(geoHashType);

        double left = geoHashType.getLeft();
        double right = geoHashType.getRight();
        String ret ="";
        int loopCount = geoHashLevel.getLoopCount(geoHashType);

        for(int i=0 ; i<loopCount; i++){
            double mid = left + (right-left)/2d ;

            if(val < mid){
                ret+="0";
                right = mid;
            }else{
                ret+="1";
                left = mid;
            }
        }
        return ret;
    }

}
