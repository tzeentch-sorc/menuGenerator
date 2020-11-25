package edu.netcracker.menugenerator.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class MealProductPK implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "meal_id")
    private Long mealId;

}
