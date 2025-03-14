package com.jdbctemplate.example.springWithJdbctemplate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbctemplate.example.springWithJdbctemplate.candidate.Candidate;
import com.jdbctemplate.example.springWithJdbctemplate.dao.SpringDao;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/candidate")
public class OneController {

    @Autowired
    private SpringDao dao;

    @PostMapping("/insert")
    public ResponseEntity<String> getInsert(@RequestBody Candidate candidate) {
        log.info("Insert method started");

        dao.insert(candidate);

        log.info("Insert method ended for candidate: {}", candidate);
        return ResponseEntity.ok("Inserted Successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCandidate(@PathVariable int id, @RequestBody Candidate candidate) {
        log.info("Update method started for candidate ID: {}", id);

        dao.update(id, candidate);

        log.info("Update method ended for candidate ID: {}", id);
        return ResponseEntity.ok("Candidate updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        log.info("Delete method started for candidate ID: {}", id);

        dao.delete(id);

        log.info("Delete method ended for candidate ID: {}", id);
        return ResponseEntity.ok("Candidate deleted");
    }

    @GetMapping("/get/{id}")
    public Candidate getCandidateById(@PathVariable int id) {
        log.info("Get candidate by ID started for ID: {}", id);

        Candidate candidate = dao.getById(id);

        log.info("Get candidate by ID ended for ID: {}", id);
        return candidate;
    }

    @GetMapping("/get")
    public List<Candidate> getAllCandidates() {
        log.info("Get all candidates method started");

        List<Candidate> candidates = dao.getAll();

        log.info("Get all candidates method ended. Total candidates fetched: {}", candidates.size());
        return candidates;
    }

}
