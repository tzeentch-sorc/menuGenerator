package edu.netcracker.menugenerator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import edu.netcracker.menugenerator.util.MealType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "meal")
@TypeDef(name = "type_enum",
            typeClass = PostgreSQLEnumType.class)
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    private String recipe;

    private int calories;
    private int portions;
    private double proteins;
    private double fats;
    private double carbohydrates;
    private int weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", columnDefinition = "meal_type_enum")
    @Type(type = "type_enum")
    private MealType type;

    private String picture;

    @OneToMany(mappedBy = "meal", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<MealProduct> mealProducts = new ArrayList<>();
}