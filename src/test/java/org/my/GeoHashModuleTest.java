package org.my;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.my.GeoHashModule.DIFF_LAT_1m;
import static org.my.GeoHashModule.DIFF_LNG_1m;

public class GeoHashModuleTest {

    final Map<GcsPoint, String> map = new HashMap();

    @Before
    public void before(){
        map.put(new GcsPoint(37.566300, 126.977946),"wydm9qwx");
        map.put(new GcsPoint(42.583, -5.581),"ezs41pbp");
        map.put(new GcsPoint(42.19995, -6.00008),"eze8p6qb");
    }

    @Test
    public void getGeoHashUsingBase32Test_level2(){

        //1. GCS Point별
        //2. Level 1~8
        //3. 총 Point 수 x 8 테스트
        map.keySet().stream().forEach(gcsPoint -> {

            for(GeoHashLevel level : GeoHashLevel.values()){
                String output = GeoHashModule.getGeoHashValueFromCoordinate(gcsPoint, level);
                Assert.assertEquals(map.get(gcsPoint).substring(0,level.getStringLength()), output);
            }

        });
    }

    @Test
    public void bBoxes_level(){
        double lat = 37.57270365;
        double lng = 126.98815886;

        GcsPoint gcsPoint = new GcsPoint(lat, lng);

        //가로 400, 세로 450에 대한 테스트 케이스
        GcsPoint southWestPoint = new GcsPoint(gcsPoint.getLatitude() - (DIFF_LAT_1m * 200),
                gcsPoint.getLongitude() - (DIFF_LNG_1m * 225));
        GcsPoint northEastPoint = new GcsPoint(gcsPoint.getLatitude() + (DIFF_LAT_1m * 200),
                gcsPoint.getLongitude() + (DIFF_LNG_1m * 225));

        final Map<GeoHashLevel, Integer> expected = new HashMap();
        expected.put(GeoHashLevel.ONE, 1);
        expected.put(GeoHashLevel.TWO, 1);
        expected.put(GeoHashLevel.THREE, 1);
        expected.put(GeoHashLevel.FOUR, 1);
        expected.put(GeoHashLevel.FIVE, 2);
        expected.put(GeoHashLevel.SIX, 2);
        expected.put(GeoHashLevel.SEVEN, 12);
        expected.put(GeoHashLevel.EIGHT, 275);

        for(GeoHashLevel level : GeoHashLevel.values()) {
            Set<String> set = GeoHashModule.bBoxes(southWestPoint, northEastPoint, level);
            Assert.assertEquals(Optional.ofNullable(expected.get(level)), Optional.ofNullable(set.size()));
//            System.out.println(set.size());
        }

    }

}
