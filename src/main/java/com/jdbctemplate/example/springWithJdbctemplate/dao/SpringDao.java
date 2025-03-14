package com.jdbctemplate.example.springWithJdbctemplate.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.jdbctemplate.example.springWithJdbctemplate.candidate.Candidate;
import com.jdbctemplate.example.springWithJdbctemplate.config.SqlQueryConfig;
import com.jdbctemplate.example.springWithJdbctemplate.exception.ExceptionHandling;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class SpringDao {

    @Autowired
    private JdbcTemplate template;

    @Autowired
    private SqlQueryConfig queryConfig;

    public int insert(Candidate candidate) throws DataAccessException {
        log.info("Insert method started for candidate: {}", candidate);
        String sql = queryConfig.getInsert();  
        try {
            int rowsAffected = template.update(sql, candidate.getId(), candidate.getName(), candidate.getEmail());
            log.info("Insert method ended successfully. Rows affected: {}", rowsAffected);
            return rowsAffected;
        } catch (DataAccessException e) {
            log.error("Exception in insert(): {}", e.getMessage(), e);
            throw new ExceptionHandling("Error inserting candidate with ID: " + candidate.getId());
        }
    }

    public int update(int id, Candidate candidate) {
        log.info("Update method started for ID: {} with candidate: {}", id, candidate);
        String sql = queryConfig.getUpdate();
        try {
            int rowsAffected = template.update(sql, candidate.getName(), candidate.getEmail(), id);
            log.info("Update method ended successfully for ID: {}. Rows affected: {}", id, rowsAffected);
            return rowsAffected;
        } catch (DataAccessException e) {
            log.error("Exception in update(): {}", e.getMessage(), e);
            throw new ExceptionHandling("Error updating candidate with ID: " + id);
        }
    }

    public int delete(int id) {
        log.info("Delete method started for ID: {}", id);
        String sql = queryConfig.getDeleteById();
        try {
            int rowsAffected = template.update(sql, id);
            log.info("Delete method ended successfully for ID: {}. Rows affected: {}", id, rowsAffected);
            return rowsAffected;
        } catch (DataAccessException e) {
            log.error("Exception in delete(): {}", e.getMessage(), e);
            throw new ExceptionHandling("Error deleting candidate with ID: " + id);
        }
    }

    public Candidate getById(int id) {
        log.info("GetById method started for ID: {}", id);
        String sql = queryConfig.getGetById();
        try {
            Candidate candidate = template.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Candidate.class));
            log.info("GetById method ended successfully for ID: {}. Candidate: {}", id, candidate);
            return candidate;
        } catch (DataAccessException e) {
            log.error("Exception in getById(): {}", e.getMessage(), e);
            throw new ExceptionHandling("Error fetching candidate with ID: " + id);
        }
    }

    public List<Candidate> getAll() {
        log.info("GetAll method started");
        String sql = queryConfig.getGetAll();
        try {
            List<Candidate> candidates = template.query(sql, new BeanPropertyRowMapper<>(Candidate.class));
            log.info("GetAll method ended successfully. Total candidates fetched: {}", candidates.size());
            return candidates;
        } catch (DataAccessException e) {
            log.error("Exception in getAll(): {}", e.getMessage(), e);
            throw new ExceptionHandling("Error fetching all candidates");
        }
    }

}
