package com.example.crudmn.controller;


import com.example.crudmn.entity.Envelope;
import com.example.crudmn.entity.Userpoll;
import com.example.crudmn.exception.InternalServerErrorException;
import com.example.crudmn.mapper.EnvelopeMapper;
import com.example.crudmn.repository.UserPollRepository;
import com.example.crudmn.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/envelope")
@CrossOrigin("*")
public class EnvelopeController {

    @Autowired
    private EnvelopeMapper envelopeRepository;

    @Autowired
    private UserPollRepository userPollRepository;

    //Authentication currentUser

    @GetMapping("/")
    public ResponseEntity<?> getEnveloppes(Authentication currentUser){


        try {

            UserDetails u = (UserDetails) currentUser.getPrincipal();

            Userpoll userpoll = userPollRepository.findByUsername(u.getUsername()).get();

            UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());

            List<Envelope> listenvelopes = envelopeRepository.findByUserId(user_.getId().toString());
            return  ResponseEntity.ok().body(listenvelopes);
        }catch (Exception ex){
              return  ResponseEntity.badRequest().body(ex.toString());
        }

    }


    @GetMapping("/envelopes/{envelopeId}")
    public ResponseEntity<?> getEnvelope(@PathVariable String envelopeId,Authentication currentUser ,HttpServletRequest request) {


        final Envelope envelope = envelopeRepository.findById(envelopeId);

        if (envelope == null)
           return  ResponseEntity.badRequest().body("No envelope with id (" + envelopeId + ") found");

        return  ResponseEntity.ok().body(envelope) ;
    }

    @PostMapping("/envelopes/search")
    public List<Envelope> searchEnvelopes(@RequestBody Map<String, String> body, HttpServletRequest request) {


        String queryName = body.get("name");
        String queryNotes = body.get("notes");

        return envelopeRepository.search("%" + queryName + "%", "%" + queryNotes + "%");
    }

    @DeleteMapping("/envelopes/{envelopeId}")
    public ResponseEntity<?> deleteEnvelope(@PathVariable String envelopeId, HttpServletRequest request) {


        final int rows = envelopeRepository.delete(envelopeId);

        if (rows < 1)
            throw new InternalServerErrorException("Error deleting envelope");

        if (rows > 1)
            throw new InternalServerErrorException("Error deleting envelope (>1)");

        return ResponseEntity.ok().build();
    }





    @PostMapping("/envelopes")
    public Envelope createEnvelope(@Valid @RequestBody Envelope body,Authentication currentUser, HttpServletRequest request) {
        /**
         * custom currency validation
         */
        Double val = Double.valueOf(body.getValue());

        UserDetails u = (UserDetails) currentUser.getPrincipal();

        Userpoll userpoll = userPollRepository.findByUsername(u.getUsername()).get();

        UserDetailsImpl user_ = new UserDetailsImpl(userpoll.getId().toString(),userpoll.getUsername(),userpoll.getEmail());


        if (!(val >= 0.01))
            throw new InternalServerErrorException("Value must be greater than one cent (0.01): " + val + " found");



        final Userpoll user = userPollRepository.findById(userpoll.getId()).get();

        if (user == null)
            throw new InternalServerErrorException("Authentication error. Please log in.");

        if (body.getNotes() == null)
            body.changeNotes("");

        final Envelope envelope = new Envelope(body.getName(), body.getValue(), body.getNotes(), user.getId().toString());

        final int rows = envelopeRepository.save(envelope);

        if (rows < 1)
            throw new InternalServerErrorException("Error saving envelope");

        if (rows > 1)
            throw new InternalServerErrorException("Error saving envelope (>1)");

        final Envelope savedEnvelope = envelopeRepository.findById(envelope.getId());

        if (savedEnvelope == null)
            throw new InternalServerErrorException("Error retrieving saved envelope");

        return savedEnvelope;
    }

    @PutMapping("/envelopes/{envelopeId}")
    public Envelope updateEnvelope(@PathVariable String envelopeId, @RequestBody Envelope envelopeRequest,
                                   HttpServletRequest request) {


        final Envelope envelope = envelopeRepository.findById(envelopeId);

        if (envelope == null)
            throw new InternalServerErrorException("No envelope with id (" + envelopeId + ") found");

        Boolean isChanged = false;

        String name = envelopeRequest.getName();
        Double value = envelopeRequest.getValue();
        String notes = envelopeRequest.getNotes();

        if (name != null && !envelope.getName().equals(name)) {
            envelope.changeName(name);
            isChanged = true;
        }

        if (value != null && !envelope.getValue().equals(value)) {
            envelope.changeValue(value);
            isChanged = true;
        }

        if (notes != null && !envelope.getNotes().equals(notes)) {
            envelope.changeNotes(notes);
            isChanged = true;
        }

        if (!isChanged) {
            throw new InternalServerErrorException("No changes detected");
        }

        final int rows = envelopeRepository.update(envelope);

        if (rows < 1)
            throw new InternalServerErrorException("Error updating envelope");

        if (rows > 1)
            throw new InternalServerErrorException("Error updating envelope (>1)");

        final Envelope updatedEnvelope = envelopeRepository.findById(envelope.getId());

        if (updatedEnvelope == null)
            throw new InternalServerErrorException("Error retrieving updated envelope");

        return updatedEnvelope;
    }





}
