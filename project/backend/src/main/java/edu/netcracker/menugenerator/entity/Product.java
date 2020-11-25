package edu.netcracker.menugenerator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "product")
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    private Integer calories;

    private double proteins;

    private double fats;

    private double carbohydrates;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MealProduct> mealProducts = new ArrayList<>();
}
