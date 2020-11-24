package edu.netcracker.menugenerator.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import edu.netcracker.menugenerator.entity.MealFilters;
import edu.netcracker.menugenerator.services.JsonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
            return null;
        }
    }
}
