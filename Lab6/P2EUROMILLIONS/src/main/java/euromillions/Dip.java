package euromillions;

import java.util.Objects;

import sets.SetOfNaturals;

import java.util.Random;

/**
 * A set of 5 numbers and 2 starts according to the Euromillions ranges.
 *
 * @author ico0
 */
public class Dip {
    private SetOfNaturals numbers;
    private SetOfNaturals starts;

    //Erase of magic numbers
    static int MAX_NUMBERS = 50;
    static int MIN_NUMBERS = 0;
    static int MAX_STARS = 10;
    static int MIN_STARS = 0;
    static int ARRAY_SIZE_NUMBERS = 5;
    static int ARRAY_SIZE_STARS = 2;

    public Dip() {
        numbers = new SetOfNaturals();
        starts = new SetOfNaturals();
    }

    public Dip(int[] arrayOfNumbers, int[] arrayOfStarts) {
        this();

        if (ARRAY_SIZE_NUMBERS == arrayOfNumbers.length && ARRAY_SIZE_STARS == arrayOfStarts.length) {
            //Throw IllegalArgumentException if number and start don't respect range
            for (int number : arrayOfNumbers){
                if (number <= MIN_NUMBERS || number > MAX_NUMBERS){
                    throw new IllegalArgumentException("wrong number, must be in [1,50]");
                }
            }
            for (int start : arrayOfStarts){
                if (start <= MIN_STARS || start > MAX_STARS){
                    throw new IllegalArgumentException("wrong start, must be in [1,10]");
                }
            }
            numbers.add(arrayOfNumbers);
            starts.add(arrayOfStarts);
        } else {
            throw new IllegalArgumentException("wrong number of elements in numbers/stars");
        }

    }

    public SetOfNaturals getNumbersColl() {
        return numbers;
    }

    public SetOfNaturals getStarsColl() {
        return starts;
    }

    public static Dip generateRandomDip() {
        Random generator = new Random();

        Dip randomDip = new Dip();
        for (int i = MIN_NUMBERS; i < ARRAY_SIZE_NUMBERS; i++) {
            int candidate = generator.nextInt(MAX_NUMBERS - 1) + 1;
            if (!randomDip.getNumbersColl().contains(candidate)) {
                randomDip.getNumbersColl().add(candidate);
            }
        }
        for (int i = MIN_STARS; i < ARRAY_SIZE_STARS; i++) {
            int candidate = generator.nextInt(MAX_STARS - 1) + 1;
            if (!randomDip.getStarsColl().contains(candidate)) {
                randomDip.getStarsColl().add(candidate);
            }
        }
        return randomDip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.numbers);
        hash = 29 * hash + Objects.hashCode(this.starts);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dip other = (Dip) obj;
        if (!Objects.equals(this.numbers, other.numbers)) {
            return false;
        }
        return Objects.equals(this.starts, other.starts);
    }


    /**
     * prepares a string representation of the data structure, formated for
     * printing
     *
     * @return formatted string with data
     */
    public String format() {
        StringBuilder sb = new StringBuilder();
        sb.append("N[");
        for (int number : getNumbersColl()) {
            sb.append(String.format("%3d", number));
        }
        sb.append("] S[");
        for (int star : getStarsColl()) {
            sb.append(String.format("%3d", star));
        }
        sb.append("]");
        return sb.toString();
    }
}
