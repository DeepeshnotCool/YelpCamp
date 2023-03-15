package me.deepesh.springyelpcamp.service.impl;

import lombok.RequiredArgsConstructor;
import me.deepesh.springyelpcamp.exception.NotAllowedException;
import me.deepesh.springyelpcamp.exception.RequestValidationException;
import me.deepesh.springyelpcamp.repository.ReviewRepository;
import me.deepesh.springyelpcamp.util.MapperUtil;
import me.deepesh.springyelpcamp.model.document.Campground;
import me.deepesh.springyelpcamp.model.document.Review;
import me.deepesh.springyelpcamp.model.document.User;
import me.deepesh.springyelpcamp.payload.ReviewPayload;
import me.deepesh.springyelpcamp.service.CampgroundService;
import me.deepesh.springyelpcamp.service.ReviewService;
import me.deepesh.springyelpcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReviewServiceImpl implements ReviewService {

  private final UserService userService;

  private final CampgroundService campgroundService;

  private final ReviewRepository reviewRepository;

  @Override
  public List<ReviewPayload> getAllReviews(final String campgroundId) {

    final Campground campgroundById = campgroundService.getCampgroundById(campgroundId);

    return campgroundById.getReviews().stream()
        .map(MapperUtil::toPayload)
        .collect(Collectors.toList());
  }

  @Override
  public ReviewPayload createReview(final String campgroundId, final ReviewPayload reviewPayload) {

    final Campground campgroundById = campgroundService.getCampgroundById(campgroundId);

    final User loggedInUser = userService.getLoggedInUser();

    if (isAlreadyReviewed(loggedInUser, campgroundById)) {

      throw new RequestValidationException(
          "You have already reviewed the campground :: " + campgroundId);
    }

    final Review review = MapperUtil.toDocument(reviewPayload);

    review.setAuthor(loggedInUser);

    final Review savedReview = reviewRepository.save(review);

    campgroundById.getReviews().add(savedReview);

    campgroundService.save(campgroundById);

    return MapperUtil.toPayload(review);
  }

  private boolean isAlreadyReviewed(final User user, final Campground campground) {

    return campground.getReviews().stream()
        .anyMatch(review -> review.getAuthor().getId().equals(user.getId()));
  }

  @Override
  public ReviewPayload updateReview(
      final String campgroundId, final String reviewId, final ReviewPayload updateReviewPayload) {

    final Review reviewById = validateUserReviewAndGet(reviewId);

    final Campground campgroundById = campgroundService.getCampgroundById(campgroundId);

    if (isNotCampgroundReview(campgroundById, reviewById.getId())) {

      throw new RequestValidationException(
          "Review does not being to campground :: " + campgroundId);
    }

    final Review review = MapperUtil.updateReview(reviewById, updateReviewPayload);

    final Review savedReview = reviewRepository.save(review);

    return MapperUtil.toPayload(savedReview);
  }

  private Review validateUserReviewAndGet(String reviewId) {

    final Review reviewById = getReview(reviewId);

    final User loggedInUser = userService.getLoggedInUser();

    if (!reviewById.getAuthor().getId().equals(loggedInUser.getId())) {

      throw new NotAllowedException("Review does not being to user :: " + loggedInUser.getId());
    }

    return reviewById;
  }

  private Review getReview(String reviewId) {

    return reviewRepository
        .findById(reviewId)
        .orElseThrow(() -> new RequestValidationException("No Review found with id :: " + reviewId));
  }

  private boolean isNotCampgroundReview(final Campground campgroundById, final String reviewId) {

    return campgroundById.getReviews().stream()
        .noneMatch(review -> review.getId().equals(reviewId));
  }

  @Override
  public void deleteReview(final String campgroundId, final String reviewId) {

    final Review reviewById = validateUserReviewAndGet(reviewId);

    final Campground campgroundById = campgroundService.getCampgroundById(campgroundId);

    if (isNotCampgroundReview(campgroundById, reviewById.getId())) {

      throw new RequestValidationException(
          "Review does not being to campground :: " + campgroundId);
    }

    reviewRepository.delete(reviewById);

    final List<Review> updatedReviewList =
        campgroundById.getReviews().stream()
            .filter(review -> !review.getId().equals(reviewId))
            .collect(Collectors.toList());

    campgroundById.setReviews(updatedReviewList);

    campgroundService.save(campgroundById);
  }
}
