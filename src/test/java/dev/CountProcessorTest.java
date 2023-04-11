package dev;


import dev.model.Animal;
import dev.model.Rule;
import dev.model.RuleValue;
import dev.processor.CountProcessor;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CountProcessorTest{

    private final CountProcessor countProcessor = new CountProcessor();
    private final Animal[] animals = setUpAnimals();

    public Animal[] setUpAnimals(){
        Animal animal1 = new Animal(Map.of("height", "tall", "weight", "light"));
        Animal animal2 = new Animal(Map.of("height", "small", "type", "herbivore"));
        Animal animal3 = new Animal(Map.of("height", "not-tall", "weight", "average"));
        Animal animal4 = new Animal(Map.of("habitat", "water", "height", "tall", "weight", "so heavy"));
        Animal animal5 = new Animal(Map.of("weight", "heavy", "height", "not-tall"));
        Animal animal6 = new Animal(Map.of("height", "not-tall"));
        return new Animal[]{animal1, animal2, animal3, animal4, animal5, animal6};
    }

    // Сколько животных - высоких?
    @Test
    public void testCountAnimals_oneRule_oneRuleProperty_oneRuleValue(){
        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("tall", true)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        // 1 4
        assertEquals(List.of(2), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }

    // Сколько животных - не высоких?
    @Test
    public void testCountAnimals_oneRule_oneRuleProperty_oneNotToCountRuleValue(){
        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("tall", false)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        // 2 3 5 6
        assertEquals(List.of(4), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }

    // Сколько животных – не высоких и они при этом либо легкие, либо средние?
    @Test
    public void testCountAnimals_oneRule_twoRuleProperty_twoRuleValues_withNotToCountValue(){
        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("tall", false)));
        ruleMap.put("weight", List.of(new RuleValue("light", true), new RuleValue("average", true)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        // 3
        assertEquals(List.of(1), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }

    // Сколько животных - не высоких и не легких
    @Test
    public void testCountAnimals_oneRule_twoRuleProperty_oneNotToCountRuleValues(){
        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("tall", false)));
        ruleMap.put("weight", List.of(new RuleValue("light", false)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        // 3 5
        assertEquals(List.of(2), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }
    // Сколько животных - не длинных и не маленьких
    @Test
    public void testCountAnimals_oneRule_oneRuleProperty_twoNotToCountRuleValues(){
        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("tall", false), new RuleValue("small", false)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        // 3 5 6
        assertEquals(List.of(3), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }

    @Test
    public void testCountAnimals_noAnimals_withRules(){
        Animal[] empty = new Animal[]{};

        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("tall", false), new RuleValue("small", false)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        assertEquals(List.of(0), countProcessor.countAnimalsThatMatchRules(empty, rules));
    }

    //1. Сколько животных - не высоких и не маленьких
    //2. Сколько животных - обитающих под водой
    //3. Сколько животных - не легких
    @Test
    public void testCountAnimals_threeRules(){
        Map<String, List<RuleValue>> ruleMap1 = new HashMap<>();
        ruleMap1.put("height", List.of(new RuleValue("tall", false), new RuleValue("small", false)));

        Map<String, List<RuleValue>> ruleMap2 = new HashMap<>();
        ruleMap2.put("habitat", List.of(new RuleValue("water", true)));

        Map<String, List<RuleValue>> ruleMap3 = new HashMap<>();
        ruleMap3.put("weight", List.of(new RuleValue("light", false)));

        Rule[] rules = new Rule[]{new Rule(ruleMap1), new Rule(ruleMap2), new Rule(ruleMap3)};

        // 3 5 6
        // 4
        // 3 4 5
        assertEquals(List.of(3, 1, 3), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }

    @Test
    public void testCountAnimals_ruleWithNonExistedProperty(){
        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("gjkjgk", List.of(new RuleValue("gdfg", true)));
        Rule[] rules = new Rule[]{new Rule(ruleMap)};

        assertEquals(List.of(0), countProcessor.countAnimalsThatMatchRules(animals, rules));
    }

    //1. Сколько животных - среднего роста?
    //2. Сколько животных - плотоядных?
    @Test
    public void testCountAnimals_oneAnimal_twoRules(){
        Animal animal = new Animal(Map.of("height", "small", "habitat", "terrestrial", "type", "carnivores"));
        Animal[] animalArr = new Animal[]{animal};

        Map<String, List<RuleValue>> ruleMap = new HashMap<>();
        ruleMap.put("height", List.of(new RuleValue("average", true)));

        Map<String, List<RuleValue>> ruleMap1 = new HashMap<>();
        ruleMap1.put("type", List.of(new RuleValue("carnivores", true)));
        Rule[] rules = new Rule[]{new Rule(ruleMap), new Rule(ruleMap1)};

        assertEquals(List.of(0, 1), countProcessor.countAnimalsThatMatchRules(animalArr, rules));
    }
}