package com.alterra.miniproject.domain.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeRequest implements Serializable {
    private static final long serialVersionUID =  -42256424879551154L;
    
    private Long userId; 
    private String recipeName;
    private Long categoryId;
    private String description; 
}
