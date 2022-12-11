package me.jysh.springyelpcamp.service;

import me.jysh.springyelpcamp.payload.ReviewPayload;

import java.util.List;

public interface ReviewService {

  List<ReviewPayload> getAllReviews(final String campgroundId);

  ReviewPayload createReview(final String campgroundId, final ReviewPayload reviewPayload);

  ReviewPayload updateReview(
      final String campgroundId, final String reviewId, final ReviewPayload updateReviewPayload);

  void deleteReview(String campgroundId, String reviewId);
}
