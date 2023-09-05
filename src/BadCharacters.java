import java.util.Random;

public class BadCharacters extends Character{
    boolean useCriticalRate = false;
    boolean useDefenseRate = false;
    int attackChoiceNo = 1; // 1) cr-false, df-false 2) cr-false, df-true 3) cr-true, df-false 1) cr-true, df-true *** 1 = 4 there is no difference actually (XX, XO, OX, OO) ın the same ones there is no difference
    // 1- return power, 2- return power but less, 3- return rick.health,
    public BadCharacters(String name, Inventory inventory, double power, double criticalRate,
                         double defenseRate, String goodQuote, String badQuote) {
        super(name, inventory, power, criticalRate, defenseRate, goodQuote, badQuote);
        health = 200;
    }

    @Override
    public void act() {
    }
    public double attackTo(MainCharacter rick) {
        switch (decidingAttackChoice(rick)) {
            case 1 -> {
                return rick.health - power;
            }
            case 2 -> {
                return ((rick.health) - (1-rick.defenseRate) * power);
            }
            case 3 -> {
                return rick.health = 0;
            }
        }
        return power;
    }
    public boolean isUseCriticalRate() {
        useCriticalRate = true;
        Random random = new Random();
        double limitCriticNo = (criticalRate * 10);
        double randomNO = random.nextDouble(11);
        if (randomNO <= limitCriticNo) {
            useCriticalRate = true;
        }
        return useCriticalRate;
    }
    public boolean isUseDefenseRate(MainCharacter rick) {
        useDefenseRate = false;
        Random random = new Random();
        double limitDefenseNo = (rick.defenseRate * 10);
        double randomNO = random.nextDouble(11);
        if (randomNO <= limitDefenseNo) {
            useDefenseRate = true;
            System.out.println("WOW What a tricky move you have defensed yourself.You'll get less damage!");
        }
        return useDefenseRate;
    }

    public int decidingAttackChoice (MainCharacter rick) {
        isUseDefenseRate(rick);
        isUseCriticalRate();
        if (useDefenseRate == useCriticalRate) {
            attackChoiceNo = 1;
        } else if (isUseDefenseRate(rick)) {
            attackChoiceNo = 2;
        } else {
            attackChoiceNo = 3;
        }
        return attackChoiceNo;
    }

}
