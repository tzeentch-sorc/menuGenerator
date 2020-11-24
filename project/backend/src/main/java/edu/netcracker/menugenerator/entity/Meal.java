package edu.netcracker.menugenerator.entity;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import edu.netcracker.menugenerator.util.MealType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "meal")
@TypeDef(name = "type_enum",
            typeClass = PostgreSQLEnumType.class)
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

  /*  @ManyToMany
    @JoinTable(	name = "productInMeal",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_id"))
    private List<Product> ingredients;
*/

}