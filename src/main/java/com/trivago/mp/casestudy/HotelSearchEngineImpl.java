package com.trivago.mp.casestudy;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * TODO: Implement this class.
 * Your task will be to implement two functions, one for loading the data which is stored as .csv files in the ./data
 * folder and one for performing the actual search.
 */
public class HotelSearchEngineImpl implements HotelSearchEngine {

    /**
     * The delimiter used to indicate which values in the CSVs belong to which attributes.
     */
    private static final String COLUMN_DELIMITER = ";";

    /**
     * The set of unique available advertisers.
     */
    private final Set<Advertiser> advertisers = new HashSet<>();

    /**
     * The set of unique available cities.
     */
    private final Set<City> cities = new HashSet<>();

    /**
     * The set of unique available hotels.
     */
    private final Set<Hotel> hotels = new HashSet<>();

    @Override
    public void initialize() {
        initializeAdvertisers();
        initializeCities();
        initializeHotels();
        initializeHotelAdvertisers();
    }

    @Override
    public List<HotelWithOffers> performSearch(final String cityName,
                                               final DateRange dateRange,
                                               final OfferProvider offerProvider) {

        final List<HotelWithOffers> offers = new ArrayList<>();

        // the list of hotels filtered by city, grouped by advertiser
        advertisers.forEach(advertiser -> {
            final List<Integer> hotelIdsInCity = getHotelIds(advertiser, cityName);
            offerProvider.getOffersFromAdvertiser(advertiser, hotelIdsInCity, dateRange)
                    .forEach((hotelId, offer) -> {
                        final Hotel hotel = getHotelById(hotelId);

                        //get existing or construct new hotel offer
                        final HotelWithOffers hotelWithOffers = offers.stream()
                                .filter(it -> Objects.equals(it.getHotel(), hotel))
                                .findFirst()
                                .orElse(new HotelWithOffers(hotel));
                        final List<Offer> hotelOffers = hotelWithOffers.getOffers();
                        hotelOffers.add(offer);
                        hotelWithOffers.setOffers(hotelOffers);

                        offers.add(hotelWithOffers); // update offers
                    });
                });

        return offers;

    }

    private List<Integer> getHotelIds(final Advertiser advertiser, final String cityName) {
        return advertiser.getHotels().stream()
                .filter(hotel -> hotel.getCity().getName().equalsIgnoreCase(cityName))
                .map(Hotel::getId)
                .collect(Collectors.toList());
    }

    /**
     * Initializes the {@link #advertisers} from the database.
     */
    private void initializeAdvertisers() {
        boolean firstLineRead = false;
        try (final BufferedReader br = new BufferedReader(new FileReader("data/advertisers.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!firstLineRead) {
                    firstLineRead = true;
                } else {
                    final String[] values = line.split(COLUMN_DELIMITER);
                    final int id = Integer.parseInt(values[0]);
                    final String name = values[1];
                    advertisers.add(new Advertiser(id, name));
                }
            }
        } catch (final IOException e) {

        }
    }

    /**
     * Initializes the {@link #advertisers} from the database.
     */
    private void initializeCities() {
        boolean firstLineRead = false;
        try (final BufferedReader br = new BufferedReader(new FileReader("data/cities.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!firstLineRead) {
                    firstLineRead = true;
                } else {
                    final String[] values = line.split(COLUMN_DELIMITER);
                    final int id = Integer.parseInt(values[0]);
                    final String name = values[1];
                    cities.add(new City(id, name));
                }
            }
        } catch (final IOException e) {

        }
    }

    /**
     * Initializes the {@link #hotels} from the database.
     */
    private void initializeHotels() {
        boolean firstLineRead = false;
        try (final BufferedReader br = new BufferedReader(new FileReader("data/hotels.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!firstLineRead) {
                    firstLineRead = true;
                } else {
                    final String[] values = line.split(COLUMN_DELIMITER);
                    final int id = Integer.parseInt(values[0]);
                    final String name = values[4];
                    final int cityId = Integer.parseInt(values[1]);
                    final int rating = Integer.parseInt(values[5]);
                    final int stars = Integer.parseInt(values[6]);

                    final City city = getCityById(cityId);

                    hotels.add(new Hotel(id, name, city, rating, stars));
                }
            }
        } catch (final IOException e) {

        }
    }

    /**
     * Initializes the many-to-many mapping from {@link Hotel} to {@link Advertiser} from the database.
     * This updates existing hotels with the references to their advertisers.
     */
    private void initializeHotelAdvertisers() {
        boolean firstLineRead = false;
        try (final BufferedReader br = new BufferedReader(new FileReader("data/hotel_advertiser.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!firstLineRead) {
                    firstLineRead = true;
                } else {
                    final String[] values = line.split(COLUMN_DELIMITER);
                    final int hotelId = Integer.parseInt(values[0]);
                    final int advertiserId = Integer.parseInt(values[1]);

                    // update many to many relationship from advertiser model obj
                    final Hotel hotel = getHotelById(hotelId);
                    final Advertiser advertiser = getAdvertiserById(advertiserId);
                    final Collection<Hotel> hotelsForAdvertiser = advertiser.getHotels();
                    hotelsForAdvertiser.add(hotel);
                    advertiser.setHotels(hotelsForAdvertiser);

                    // update many to many relationship from hotel model obj
                    final Collection<Advertiser> advertisersForHotel = hotel.getAdvertisers();
                    advertisersForHotel.add(advertiser);
                    hotel.setAdvertisers(advertisersForHotel);
                }
            }
        } catch (final IOException e) {

        }
    }

    private Hotel getHotelById(final int hotelId) {
        return hotels.stream()
                .filter(it -> it.getId() == hotelId)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    private Advertiser getAdvertiserById(final int advertiserId) {
        return advertisers.stream()
                .filter(it -> it.getId() == advertiserId)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }

    private City getCityById(final int cityId) {
        return cities.stream()
                .filter(it -> it.getId() == cityId)
                .findAny()
                .orElseThrow(IllegalStateException::new);
    }
}
