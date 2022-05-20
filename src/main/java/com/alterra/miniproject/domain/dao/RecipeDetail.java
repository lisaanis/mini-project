package com.alterra.miniproject.domain.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import com.alterra.miniproject.domain.common.BaseEntityWithDeletedAt;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@Table(name = "M_RECIPE_DETAIL")
public class RecipeDetail extends BaseEntityWithDeletedAt{

    private static final long serialVersionUID = 5703123232205376654L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recipeId;

    @OneToOne(mappedBy = "detail")
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

    @Column(name = "ingredients", nullable = false)
    private String ingredients; 

    @Column(name = "directions", nullable = false)
    private String directions; 
}