package dev.processor;

import dev.model.Animal;
import dev.model.Rule;
import dev.model.RuleValue;

import java.util.*;

/**
 * Класс-процессор, предназначенный для подсчета животных по правилам.
 *
 * @method countAnimalsThatMatchRules. Метод принимает массивы животных и правил.
 * Для каждого правила метод подсчитывает кол-во животных, которые подходят под это правило.
 * Возвращается список чисел, где каждое число, это ответ на соответствующий вопрос. То есть,
 * ответ на 1 вопрос будет находиться в списке на 1 позиции, и так далее.
 */
public class CountProcessor {
    public List<Integer> countAnimalsThatMatchRules(Animal[] animals, Rule[] rules) {
        List<Integer> output = new ArrayList<>();

        for (Rule rule : rules) {
            output.add(countAnimalsForRule(animals, rule));
        }

        return output;
    }

    private int countAnimalsForRule(Animal[] animals, Rule rule){
        int count = animals.length;
        for(Animal animal: animals){
            Map<String, String> animalProperty = animal.getAnimalProperties();

            for(Map.Entry<String, List<RuleValue>> ruleProperty: rule.getMapRulePropertyToValue().entrySet()){
                if(!animalProperty.containsKey(ruleProperty.getKey())){
                    count--;
                    break;
                }
                boolean isSuitableForRuleProperty = true;
                for(RuleValue ruleValue : ruleProperty.getValue()){
                    boolean toCount = ruleValue.isToCount();
                    boolean isEquals = animalProperty.get(ruleProperty.getKey()).equalsIgnoreCase(ruleValue.getValue());
                    if(!isEquals && toCount){
                        isSuitableForRuleProperty = false;
                    }
                    if(isEquals){
                        isSuitableForRuleProperty = toCount;
                        break;
                    }
                }
                if(!isSuitableForRuleProperty){
                    count--;
                    break;
                }
            }
        }
        return count;
    }
}
