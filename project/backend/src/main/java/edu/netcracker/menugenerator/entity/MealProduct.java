package edu.netcracker.menugenerator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "MealProduct")
@Table(name = "product_in_meal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealProduct {
    @EmbeddedId
    @JsonBackReference
    private MealProductPK id = new MealProductPK();

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    @JsonManagedReference
    private Product product;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    @JsonBackReference
    private Meal meal;

    @Column(name = "weight")
    private double weight;
}
