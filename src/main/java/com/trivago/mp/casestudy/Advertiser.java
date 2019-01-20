package com.trivago.mp.casestudy;

import javax.annotation.Generated;
import java.util.*;

/**
 * Stores the id and name of an advertiser. An Advertiser is a company provides offers for hotel stays.
 */
public class Advertiser {
    private final int id;
    private final String name;

    /**
     * The many-to-many relationship from advertisers to hotels.
     */
    private List<Hotel> hotels;

    public Advertiser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setHotels(final Collection<Hotel> hotels) {
        this.hotels = new ArrayList<>(hotels);
    }

    /**
     * A unique id as specified in the corresponding .csv file
     *
     * @return
     */
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Collection<Hotel> getHotels() {
        return Collections.unmodifiableCollection(hotels);
    }

    @Override
    public String toString() {
        return "Advertiser{" + "id=" + id + ", name='" + name + '\'' + '}';
    }

    @Override
    @Generated("IDEA")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Advertiser that = (Advertiser) o;
        return id == that.id;
    }

    @Override
    @Generated("IDEA")
    public int hashCode() {
        return Objects.hash(id);
    }
}
