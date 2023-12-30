package com.example.crudmn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.crudmn.models.Tutrial;
import com.example.crudmn.repository.TutrialRepository;



@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class TutorialController {
	
	@Autowired
	TutrialRepository tutorialRepository;
	
	
	@PostMapping("/tutorials")
	public ResponseEntity<Tutrial> createTutorial(@RequestBody Tutrial tutorial) {
	  try {
		  Tutrial _tutorial = tutorialRepository.save(new Tutrial(tutorial.getTitle(), tutorial.getDescription(), false));
	    return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	@GetMapping("/tutorials")
	public ResponseEntity<List<Tutrial>> getAllTutorials(@RequestParam(required = false) String title) {
	  try {
	    List<Tutrial> tutorials = new ArrayList<Tutrial>();

	    if (title == null)
	      tutorialRepository.findAll().forEach(tutorials::add);
	    else
	      tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

	    if (tutorials.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    return new ResponseEntity<>(tutorials, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}

	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutrial> getTutorialById(@PathVariable("id") String id) {
	  Optional<Tutrial> tutorialData = tutorialRepository.findById(id);

	  if (tutorialData.isPresent()) {
	    return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutrial>> findByPublished() {
	  try {
	    List<Tutrial> tutorials = tutorialRepository.findByPublished(true);

	    if (tutorials.isEmpty()) {
	      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<>(tutorials, HttpStatus.OK);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	
	
	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Tutrial> updateTutorial( @PathVariable("id") String id, @RequestBody Tutrial tutorial) {
	  Optional<Tutrial> tutorialData = tutorialRepository.findById(id);

	  if (tutorialData.isPresent()) {
	    Tutrial _tutorial = tutorialData.get();
	    _tutorial.setTitle(tutorial.getTitle());
	    _tutorial.setDescription(tutorial.getDescription());
	    _tutorial.setPublished(tutorial.isPublished());
	    return new ResponseEntity<>(tutorialRepository.save(_tutorial), HttpStatus.OK);
	  } else {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	  }
	}
	
	
	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") String id) {
	  try {
	    tutorialRepository.deleteById(id);
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
	  try {
	    tutorialRepository.deleteAll();
	    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	  } catch (Exception e) {
	    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	}
	

}
