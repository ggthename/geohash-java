package org.my;

public enum GeoHashLevel {
    ONE(2,3, 1),
    TWO(5,5, 2),
    THREE(7,8, 3),
    FOUR(10,10, 4),
    FIVE(12,13, 5),
    SIX(15,15, 6),
    SEVEN(17,18, 7),
    EIGHT(20,20, 8);

    private int latitudeBitsLength;
    private int longitudeBitsLength;
    private int stringLength;

    GeoHashLevel(int latBits, int lngBits, int stringLength){
        this.latitudeBitsLength =latBits;
        this.longitudeBitsLength =lngBits;
        this.stringLength = stringLength;
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

    public int getStringLength(){
        return this.stringLength;
    }

}