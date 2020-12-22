package edu.netcracker.menugenerator.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import edu.netcracker.menugenerator.dto.ProfileDto;
import edu.netcracker.menugenerator.dto.MealFilters;
import edu.netcracker.menugenerator.services.JsonService;
import edu.netcracker.menugenerator.util.exceptions.JsonParsingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class JsonServiceImpl implements JsonService {

    @Override
    public String toJson(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    @Override
    public MealFilters parseMealFilters(String src){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());
        try {
            return objectMapper.readValue(src, MealFilters.class);
        } catch (JsonProcessingException e) {
            log.error("Parsing failure: {}", (Object) e.getStackTrace());
            throw new JsonParsingException();
        }
    }

    @Override
    public ProfileDto parseProfileDto(String src){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new Hibernate5Module());
        try {
            return objectMapper.readValue(src, ProfileDto.class);
        } catch (JsonProcessingException e) {
            log.error("Parsing failure: {}", (Object) e.getStackTrace());
            throw new JsonParsingException();
        }
    }
}
