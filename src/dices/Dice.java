package dices;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Dice {
    public static final int MAX_DICE_FACE = 6;
    public static final double LOW_RATE_DICE = 0.16;
    public static final double HIGH_RATE_DICE = 0.2;

    List<Integer> ratioList;

    public Dice(List<Integer> ratioList) {
        this.ratioList = ratioList;
    }

    public Dice(String ratioExpr) {
        this(Arrays.stream(ratioExpr.split(":")).map(Integer::parseInt).collect(Collectors.toList()));
    }

    public Dice() {
        // TODO Auto-generated constructor stub
    }

    public int getSelectIndex() {
        Random random = new Random();
        int sum = ratioList.stream().reduce(Integer::sum).get();
        int start = 0;
        int bound = 0;
        int ran = random.nextInt(sum);
        for (int i = 0; i < ratioList.size(); i++) {
            int ratio = ratioList.get(i);
            bound += ratio;
            if (ran >= start && ran < bound) {
                return i;
            }
            start += ratio;
        }
        return -1;
    }

    public int rollDice() {
        Dice rris = new Dice("20:16:16:16:16:16");
        return rris.getSelectIndex();
    }
}