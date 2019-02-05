package org.paumard.lambdamasterclass.part1;

import org.junit.Test;
import org.paumard.lambdamasterclass.part1.model.Person;

import java.util.Comparator;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class Test03_Comparator {

    private Person michael = new Person("Michael", "Jackson", 51);
    private Person rod = new Person("Rod", "Stewart", 71);
    private Person paul = new Person("Paul", "McCartney", 74);
    private Person mick = new Person("Mick", "Jagger", 73);
    private Person jermaine = new Person("Jermaine", "Jackson", 61);

    /**
     * Write a comparator that compare instances of the Person
     * class using the following rules:
     * - the instances are first compared using their last names
     * - then compared with their first names
     * - then compared with their age
     * - if this comparator is used to sort a list of Person
     * instances, and this list contains null values, then these
     * null values should be put at the end of the sorted list.
     */
    @Test
    public void comparator_1() {
        Comparator<Person> cmp = (first, second) -> {
            if (Objects.isNull(first) && Objects.isNull(second)) return 0;
            if (Objects.isNull(first)) return 1;
            if (Objects.isNull(second)) return -1;

            return Comparator
                    .comparing(Person::getLastName)
                    .thenComparing(Person::getFirstName)
                    .thenComparing(Person::getAge)
                    .compare(first, second) ;
        };

        assertThat(cmp.compare(michael, rod)).isLessThan(0);
        assertThat(cmp.compare(paul, paul)).isEqualTo(0);
        assertThat(cmp.compare(michael, jermaine)).isGreaterThan(0);
        assertThat(cmp.compare(mick, null)).isLessThan(0);
        assertThat(cmp.compare(null, mick)).isGreaterThan(0);
    }
}
