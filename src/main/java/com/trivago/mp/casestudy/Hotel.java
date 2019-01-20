package com.trivago.mp.casestudy;

import javax.annotation.Generated;
import java.util.Objects;

/**
 * Stores all relevant information about a particular hotel.
 */
public class Hotel {
    private final int id;
    private final String name;
    private final int rating;
    private final int stars;

    public Hotel(int id, String name, int rating, int stars) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.stars = stars;
    }

    /**
     * A unique id as specified in the corresponding .csv file
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * The English name of the hotel
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * The ratings given by user feedback, between 0-100
     *
     * @return
     */
    public int getRating() {
        return rating;
    }

    /**
     * The star rating for the given hotel, between 1-5
     *
     * @return
     */
    public int getStars() {
        return stars;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name='" + name + '\'' + ", rating=" + rating + ", stars=" + stars + '}';
    }

    @Override
    @Generated("IDEA")
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Hotel that = (Hotel) o;
        return id == that.id;
    }

    @Override
    @Generated("IDEA")
    public int hashCode() {
        return Objects.hash(id);
    }
}
