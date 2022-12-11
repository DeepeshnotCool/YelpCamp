package me.jysh.springyelpcamp.controller;

import lombok.RequiredArgsConstructor;
import me.jysh.springyelpcamp.payload.ReviewPayload;
import me.jysh.springyelpcamp.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campgrounds/{campgroundId}/reviews")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ReviewController {

  private final ReviewService reviewService;

  @PostMapping
  public ResponseEntity<ReviewPayload> createReview(
      @PathVariable("campgroundId") final String campgroundId,
      @RequestBody @Valid final ReviewPayload createReviewPayload) {

    return ResponseEntity.status(HttpStatus.CREATED)
        .body(reviewService.createReview(campgroundId, createReviewPayload));
  }

  @GetMapping
  public ResponseEntity<List<ReviewPayload>> getAllReviews(
      @PathVariable("campgroundId") final String campgroundId) {

    return ResponseEntity.ok().body(reviewService.getAllReviews(campgroundId));
  }

  @PutMapping("/{reviewId}")
  public ResponseEntity<ReviewPayload> updateReview(
      @PathVariable("campgroundId") final String campgroundId,
      @PathVariable("reviewId") final String reviewId,
      @RequestBody @Valid final ReviewPayload updateReviewPayload) {

    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(reviewService.updateReview(campgroundId, reviewId, updateReviewPayload));
  }

  @DeleteMapping("/{reviewId}")
  public ResponseEntity<ReviewPayload> deleteReview(
      @PathVariable("campgroundId") final String campgroundId,
      @PathVariable("reviewId") final String reviewId) {

    reviewService.deleteReview(campgroundId, reviewId);

    return ResponseEntity.noContent().build();
  }
}
