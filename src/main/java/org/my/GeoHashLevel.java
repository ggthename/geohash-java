package org.my;
public enum GeoHashLevel {
    ONE(2,3, 1, 5000, 5000),
    TWO(5,5, 2, 1250*1000, 625* 1000),
    THREE(7,8, 3, 156 * 1000, 156* 1000),
    FOUR(10,10, 4, 39.1 * 1000, 39.1 * 1000 ),
    FIVE(12,13, 5, 4.89*1000, 4.89*1000),
    SIX(15,15, 6, 1.22*1000, 0.61*1000),
    SEVEN(17,18, 7, 153, 153),
    EIGHT(20,20, 8, 38.2, 19.1);

    private int latitudeBitsLength;
    private int longitudeBitsLength;
    private int stringLength;
    private double cellWidthMeter;
    private double cellHeightMeter;

    GeoHashLevel(int latBits, int lngBits, int stringLength, double cellWidthMeter, double cellHeightMeter){
        this.latitudeBitsLength =latBits;
        this.longitudeBitsLength =lngBits;
        this.stringLength = stringLength;
        this.cellWidthMeter = cellWidthMeter;
        this.cellHeightMeter = cellHeightMeter;
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

    public double getCellHeightMeter() {
        return cellHeightMeter;
    }

    public double getCellWidthMeter() {
        return cellWidthMeter;
    }
}