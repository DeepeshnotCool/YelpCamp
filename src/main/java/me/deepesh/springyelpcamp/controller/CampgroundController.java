package me.deepesh.springyelpcamp.controller;

import lombok.RequiredArgsConstructor;
import me.deepesh.springyelpcamp.payload.CampgroundPayload;
import me.deepesh.springyelpcamp.service.CampgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/campgrounds")
@PreAuthorize("hasRole('USER')")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CampgroundController {

  private final CampgroundService campgroundService;

  @GetMapping("/{campgroundId}")
  public ResponseEntity<CampgroundPayload> getCampground(
      @PathVariable("campgroundId") final String campgroundId) {

    return ResponseEntity.ok().body(campgroundService.getCampground(campgroundId));
  }

  @PostMapping
  public ResponseEntity<CampgroundPayload> createCampground(
      @RequestBody @Valid final CampgroundPayload campground) {

    return ResponseEntity.ok().body(campgroundService.createCampground(campground));
  }

  @PutMapping("/{campgroundId}")
  public ResponseEntity<CampgroundPayload> updateCampground(
      @PathVariable("campgroundId") final String campgroundId,
      @RequestBody @Valid final CampgroundPayload campground) {

    return ResponseEntity.ok().body(campgroundService.updateCampground(campgroundId, campground));
  }

  @DeleteMapping("/{campgroundId}")
  public ResponseEntity<CampgroundPayload> deleteCampground(
      @PathVariable("campgroundId") final String campgroundId) {

    campgroundService.deleteCampground(campgroundId);

    return ResponseEntity.noContent().build();
  }
}
