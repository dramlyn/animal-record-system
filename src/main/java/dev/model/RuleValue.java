package dev.model;

import java.util.Objects;

/**
 * Класс Значение правила.
 * Хранит в себе возможное значение свойства животного и флаг toCount, который сигнализирует, должно ли данное значение
 * свойства использоваться при подсчете или нет.
 * Пример:
 * Сколько животных - травоядных. Тогда поля этого объекта будут следующими - value : "травоядное", toCount: true.
 * Другой пример:
 * Сколько животных - не травоядных. Тогда поля этого объекта будут следующими - value : "травоядное", toCount: false.
 */
public class RuleValue {
    private String value;
    private boolean toCount;

    public RuleValue(String value, boolean toCount) {
        this.value = value;
        this.toCount = toCount;
    }

    public RuleValue() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isToCount() {
        return toCount;
    }

    public void setToCount(boolean toCount) {
        this.toCount = toCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        dev.model.RuleValue ruleValue = (dev.model.RuleValue) o;
        return toCount == ruleValue.toCount && Objects.equals(value, ruleValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, toCount);
    }

    @Override
    public String toString() {
        return "RuleValue{" +
                "value='" + value + '\'' +
                ", toCount=" + toCount +
                '}';
    }
}

