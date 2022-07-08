package org.my;

import org.junit.Assert;
import org.junit.Test;

public class GeoHashModuleTest {

    @Test
    public void getGeoHashUsingBase32Test_level8(){

        GcsPoint gcsPoint = new GcsPoint(37.566300, 126.977946);
        String expected = "wydm9qwx";
        String output = GeoHashModule.getGeoHashValueFromCoordinate(gcsPoint, GeoHashLevel.EIGHT);

        Assert.assertEquals(expected, output);
    }

    @Test
    public void getGeoHashUsingBase32Test_level2(){

        GcsPoint gcsPoint = new GcsPoint(37.566300, 126.977946);
        String expected = "wy";
        String output = GeoHashModule.getGeoHashValueFromCoordinate(gcsPoint, GeoHashLevel.TWO);

        Assert.assertEquals(expected, output);
    }

}
