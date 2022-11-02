# geohash for java
- get a geohash value from a coordinate (latitude,longitude)
- get the geohash values included in a specific area (with southwest, northease points)

# geohash length	lat bits	lng bits	lat error	lng error	km error
Digits and precision in km
--------------------------
| geohash length | lat bits | lng bits | lat error | lng error  | ~km error |
|----------------|----------|----------|-----------|------------|-----------|
| 1              | 2        | 3        | ±23       | ±23        | ±2500     |
| 2              | 5        | 5        | ±2.8      | ±5.6       | ±630      |
| 3              | 7        | 8        | ±0.70     | ±0.70      | ±78       |
| 4              | 10       | 10       | ±0.087    | ±0.18      | ±20       |
| 5              | 12       | 13       | ±0.022    | ±0.022     | ±2.4      |
| 6              | 15       | 15       | ±0.0027   | ±0.0055    | ±0.61     |
| 7              | 17       | 18       | ±0.00068  | ±0.00068   | ±0.076    |
| 8              | 20       | 20       | ±0.000085 | ±0.00017   | ±0.019    |

# reference
http://geohash.org/

# Sample Code
https://github.com/ggthename/geohash-java/blob/master/src/test/java/org/my/GeoHashModuleTest.java
