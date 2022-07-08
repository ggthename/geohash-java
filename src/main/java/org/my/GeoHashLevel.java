package org.my;

public enum GeoHashLevel {
    ONE(2,3),
    TWO(5,5),
    THREE(7,8),
    FOUR(10,10),
    FIVE(12,13),
    SIX(15,15),
    SEVEN(17,18),
    EIGHT(20,20);

    private int latitudeBitsLength;
    private int longitudeBitsLength;

    GeoHashLevel(int latBits, int lngBits){
        this.latitudeBitsLength =latBits;
        this.longitudeBitsLength =lngBits;
    }

    public int getLoopCount(GcsType geoHashType){
        switch(geoHashType){
            case LATITUDE:
                return this.latitudeBitsLength;
            case LONGITUDE:
                return this.longitudeBitsLength;
        }
        return -1;
    }
}