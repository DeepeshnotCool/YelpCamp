package me.jysh.springyelpcamp.service;

import me.jysh.springyelpcamp.model.document.Campground;
import me.jysh.springyelpcamp.payload.CampgroundPayload;

public interface CampgroundService {

  CampgroundPayload createCampground(final CampgroundPayload campground);

  CampgroundPayload save(final Campground campground);

  CampgroundPayload getCampground(final String campgroundId);

  Campground validateUserCampgroundAndGet(String campgroundId);

  Campground getCampgroundById(final String campgroundId);

  CampgroundPayload updateCampground(
      final String campgroundId, final CampgroundPayload campgroundPayload);

  void deleteCampground(final String campgroundId);
}
