package edu.netcracker.menugenerator.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "profile")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;
    private int height;
    private int weight;
    @Column(name = "age")
    private int age;
    @Column(name ="is_male")
    private Boolean male;
    @Column(name = "activity")
    private double activity;

    @Column(name = "daily_fats")
    private double dailyFats;
    @Column(name = "daily_carbohydrates")
    private double dailyCarbohydrates;
    @Column(name = "daily_proteins")
    private double dailyProteins;
    @Column(name = "daily_calories")
    private double dailyCalories;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    private User user;

    public Profile(User user){
        this.user = user;
    }
}
