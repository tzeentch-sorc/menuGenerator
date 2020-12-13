package edu.netcracker.menugenerator.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_to_meal_list")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Preference {
    @EmbeddedId
    @JsonBackReference
    private PreferencePK id = new PreferencePK();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @MapsId("mealId")
    @JoinColumn(name = "meal_id")
    @JsonBackReference
    private Meal meal;

    @Column(name = "banned")
    private boolean banned;
}
