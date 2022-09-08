package org.my;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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

}
