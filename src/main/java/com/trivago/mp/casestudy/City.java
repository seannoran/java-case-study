package com.trivago.mp.casestudy;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * Stores the id and name of a city.
 */
public class City {
    private final int id;
    private final String name;

    public City(final int id,
                final String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * A unique ID identifying the city.
     *
     * @return an integer ID.
     */
    public int getId() {
        return id;
    }

    /**
     * The human-readable name of the city.
     * @return The human-readable city name, e.g. {@code Düsseldorf}
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    @Generated("IDEA")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final City that = (City) o;
        return id == that.id;
    }

    @Override
    @Generated("IDEA")
    public int hashCode() {
        return Objects.hash(id);
    }
}
