package me.deepesh.springyelpcamp.service;

import me.deepesh.springyelpcamp.payload.CampgroundPayload;
import me.deepesh.springyelpcamp.model.document.Campground;

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
