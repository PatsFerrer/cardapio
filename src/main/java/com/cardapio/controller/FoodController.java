package com.cardapio.controller;

import com.cardapio.food.Food;
import com.cardapio.food.FoodRepository;
import com.cardapio.food.FoodRequestDTO;
import com.cardapio.food.FoodResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("food")
public class FoodController {

    @Autowired
    private FoodRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public void saveFood(@RequestBody FoodRequestDTO data){
        Food foodData = new Food(data);
        repository.save(foodData);
        return;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<FoodResponseDTO> getAll(){

        List<FoodResponseDTO> foodList = repository.findAll().stream().map(FoodResponseDTO::new).toList();
        return foodList;
    }

    @DeleteMapping("/{id}")
    public void deleteFood(@PathVariable Long id){
        repository.deleteById(id);
    }

    @PutMapping("/{id}")
    public Food updateFood(@PathVariable Long id, @RequestBody Food foodDetails){
        Food food = repository.findById(id).get();

        food.setTitle(foodDetails.getTitle());
        food.setPrice(foodDetails.getPrice());
        food.setImage(foodDetails.getImage());

        return repository.save(food);
    }
}
