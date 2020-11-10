package edu.netcracker.menugenerator.entity;

import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.Iterator;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "meal")
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    private String recipe;

    @NotBlank
    @Size(max = 120)
    private String mealtype;

    private Integer calories;

    private Integer portions;

    private double fats;

    private double carbohydrates;

    private Integer weight;

    private double proteins;

  /*  @ManyToMany
    @JoinTable(	name = "productInMeal",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Product> ingredients;
*/

}