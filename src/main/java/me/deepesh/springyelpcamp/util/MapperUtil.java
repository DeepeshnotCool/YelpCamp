package me.deepesh.springyelpcamp.util;

import me.deepesh.springyelpcamp.model.document.Campground;
import me.deepesh.springyelpcamp.model.document.Review;
import me.deepesh.springyelpcamp.payload.CampgroundPayload;
import me.deepesh.springyelpcamp.payload.ReviewPayload;

import java.util.Arrays;

public class MapperUtil {

  private MapperUtil() {}

  public static Campground updateCampground(
      final Campground campground, final CampgroundPayload campgroundPayload) {

    campground.setName(campgroundPayload.getName());
    campground.setDescription(campgroundPayload.getDescription());
    campground.setImage(campgroundPayload.getImage());
    campground.setPrice(campgroundPayload.getPrice());
    campground.setPhone(campgroundPayload.getPhone());
    campground.setLocation(campgroundPayload.getLocation());
    campground.setTags(Arrays.asList(campgroundPayload.getTags().split(",")));

    return campground;
  }

  public static Campground toDocument(final CampgroundPayload campgroundPayload) {

    final Campground campground = new Campground();

    return updateCampground(campground, campgroundPayload);
  }

  public static CampgroundPayload toPayload(final Campground campground) {

    final CampgroundPayload campgroundPayload = new CampgroundPayload();

    campgroundPayload.setId(campground.getId());
    campgroundPayload.setAuthorId(campground.getAuthor().getId());
    campgroundPayload.setName(campground.getName());
    campgroundPayload.setDescription(campground.getDescription());
    campgroundPayload.setImage(campground.getImage());
    campgroundPayload.setPrice(campground.getPrice());
    campgroundPayload.setPhone(campground.getPhone());
    campgroundPayload.setLocation(campground.getLocation());
    campgroundPayload.setTags(String.join(",", campground.getTags()));

    return campgroundPayload;
  }

  public static Review updateReview(final Review review, final ReviewPayload reviewPayload) {

    review.setText(reviewPayload.getText());

    return review;
  }

  public static Review toDocument(final ReviewPayload reviewPayload) {

    final Review review = new Review();

    return updateReview(review, reviewPayload);
  }

  public static ReviewPayload toPayload(final Review review) {

    final ReviewPayload reviewPayload = new ReviewPayload();

    reviewPayload.setId(review.getId());
    reviewPayload.setText(review.getText());
    reviewPayload.setAuthorId(review.getAuthor().getId());

    return reviewPayload;
  }
}
