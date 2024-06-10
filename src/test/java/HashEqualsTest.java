import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.Test;

public class HashEqualsTest {

    @Test
    void diff() {
        Map<Location, Integer> map = new HashMap<>();
        Location l1 = new Location(1, 1);
        Location l2 = new Location(1, 1);
        map.put(l1, 1);
        map.put(l2, 1);

        assertThat(map.size()).isEqualTo(2);
        assertThat(map.containsKey(l1)).isTrue();
        assertThat(map.containsKey(l2)).isTrue();
    }

    @Test
    void same() {
        Map<LocationWithOverride, Integer> map = new HashMap<>();
        LocationWithOverride l1 = new LocationWithOverride(1, 1);
        LocationWithOverride l2 = new LocationWithOverride(1, 1);
        map.put(l1, 1);
        map.put(l2, 1);

        assertThat(map.size()).isEqualTo(1);
        assertThat(map.containsKey(l1)).isTrue();
        assertThat(map.containsKey(l2)).isTrue();
    }

    class Location {

        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    class LocationWithOverride {

        int x;
        int y;

        public LocationWithOverride(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public boolean equals(Object obj) {
            LocationWithOverride o = (LocationWithOverride) obj;
            return x == o.x && y == o.y;
        }
    }
}
