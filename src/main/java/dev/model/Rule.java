package dev.model;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Класс Правило.
 * Хранит в себе отображение, построенное по следующей логике:
 * Свойство животного, по которому нужно вести подсчет, на лист возможных значения.
 * Значение представляет собой класс RuleValue.
 */
public class Rule {
    private Map<String, List<RuleValue>> mapRulePropertyToValue;

    public Rule(Map<String, List<RuleValue>> mapRulePropertyToValue) {
        this.mapRulePropertyToValue = mapRulePropertyToValue;
    }

    public Rule() {
    }

    public Map<String, List<RuleValue>> getMapRulePropertyToValue() {
        return mapRulePropertyToValue;
    }

    public void setMapRulePropertyToValue(Map<String, List<RuleValue>> mapRulePropertyToValue) {
        this.mapRulePropertyToValue = mapRulePropertyToValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rule rule = (Rule) o;
        return Objects.equals(getMapRulePropertyToValue(), rule.getMapRulePropertyToValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMapRulePropertyToValue());
    }

    @Override
    public String toString() {
        return "Rule{" +
                "mapRulePropertyToValue=" + mapRulePropertyToValue +
                '}';
    }
}
