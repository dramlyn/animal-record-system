package dev;

import com.google.gson.Gson;
import dev.model.Animal;
import dev.model.Rule;
import dev.processor.CountProcessor;

import java.util.*;

import static dev.tools.AnimalReader.getClassFromFile;

public class App
{
    public static void main( String[] args ) {
        Gson gson = new Gson();

        //SERIALIZE RULES

        /*Map<String, List<RuleValue>> map1 = new HashMap<>();
        map1.put("weight", Arrays.asList(new RuleValue("value", true), new RuleValue("value1", true)));
        map1.put("height", Arrays.asList(new RuleValue("value", true)));
        Rule rule1 = new Rule(map1);

        Map<String, List<RuleValue>> map2 = new HashMap<>();
        map2.put("weight", Arrays.asList(new RuleValue("value", true), new RuleValue("value1", true)));
        map2.put("height", Arrays.asList(new RuleValue("value3", true)));
        Rule rule2 = new Rule(map2);

        List<Rule> ruleList = Arrays.asList(rule1, rule2);
        System.out.println(gson.toJson(ruleList));*/



        //SERIALIZE ANIMALS

        /*Map<String, String> map1 = new HashMap<>();
        map1.put("weight","value");
        map1.put("height","value");
        Animal animal1 = new Animal(map1);

        Map<String, String> map2 = new HashMap<>();
        map2.put("weight","value");
        map2.put("height","value1");
        Animal animal2 = new Animal(map2);

        Map<String, String> map3 = new HashMap<>();
        map3.put("weight","value1");
        map3.put("height","value");
        Animal animal3 = new Animal(map3);

        List<Animal> list = Arrays.asList(animal1, animal2, animal3);*/



        // PROGRAM
        CountProcessor countProcessor = new CountProcessor();

        List<Integer> list = countProcessor.countAnimalsThatMatchRules(getClassFromFile("animals.txt", Animal[].class), getClassFromFile("rules.txt", Rule[].class));
        System.out.println(list);
    }
}
