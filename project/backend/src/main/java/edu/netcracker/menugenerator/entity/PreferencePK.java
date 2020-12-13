package edu.netcracker.menugenerator.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class PreferencePK implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "meal_id")
    private Long mealId;

}
