package me.deepesh.springyelpcamp.service.impl;

import lombok.RequiredArgsConstructor;
import me.deepesh.springyelpcamp.exception.NotAllowedException;
import me.deepesh.springyelpcamp.exception.NotFoundException;
import me.deepesh.springyelpcamp.model.UserDetailsImpl;
import me.deepesh.springyelpcamp.repository.CampgroundRepository;
import me.deepesh.springyelpcamp.util.MapperUtil;
import me.deepesh.springyelpcamp.model.document.Campground;
import me.deepesh.springyelpcamp.model.document.User;
import me.deepesh.springyelpcamp.payload.CampgroundPayload;
import me.deepesh.springyelpcamp.service.CampgroundService;
import me.deepesh.springyelpcamp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CampgroundServiceImpl implements CampgroundService {

  private final UserService userService;

  private final CampgroundRepository campgroundRepository;

  @Override
  public CampgroundPayload createCampground(CampgroundPayload campground) {

    UserDetailsImpl userPrincipal =
        (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    final User byUsername = userService.getUserByUsername(userPrincipal.getUsername());

    final Campground campgroundDocument = MapperUtil.toDocument(campground);
    campgroundDocument.setAuthor(byUsername);

    final Campground savedCampgroundDocument = campgroundRepository.save(campgroundDocument);

    return MapperUtil.toPayload(savedCampgroundDocument);
  }

  @Override
  public CampgroundPayload save(final Campground campground) {

    return MapperUtil.toPayload(campgroundRepository.save(campground));
  }

  @Override
  public CampgroundPayload getCampground(String campgroundId) {

    final Campground foundCampgroundDocument = getCampgroundById(campgroundId);

    return MapperUtil.toPayload(foundCampgroundDocument);
  }

  @Override
  public CampgroundPayload updateCampground(
      String campgroundId, CampgroundPayload campgroundPayload) {

    final Campground foundCampgroundDocument = this.validateUserCampgroundAndGet(campgroundId);

    final Campground updatedCampground =
        MapperUtil.updateCampground(foundCampgroundDocument, campgroundPayload);

    return MapperUtil.toPayload(campgroundRepository.save(updatedCampground));
  }

  @Override
  public Campground validateUserCampgroundAndGet(String campgroundId) {

    final Campground foundCampgroundDocument = this.getCampgroundById(campgroundId);

    UserDetailsImpl userPrincipal =
        (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    final User byUsername = userService.getUserByUsername(userPrincipal.getUsername());

    if (!byUsername.getId().equals(foundCampgroundDocument.getAuthor().getId())) {
      throw new NotAllowedException("Campground does not belong to User :: " + byUsername.getId());
    }

    return foundCampgroundDocument;
  }

  @Override
  public Campground getCampgroundById(final String campgroundId) {

    return campgroundRepository
        .findById(campgroundId)
        .orElseThrow(() -> new NotFoundException("Campground not found."));
  }

  @Override
  public void deleteCampground(final String campgroundId) {

    final Campground foundCampgroundDocument = validateUserCampgroundAndGet(campgroundId);

    campgroundRepository.delete(foundCampgroundDocument);
  }
}
