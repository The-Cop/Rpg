package com.thecop.rpg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TheCop on 15.12.2015.
 */
public class Main {

    private static final int DISPLAY_WIDTH = 180;

    public static void main(String[] args) {
        FightController fc = new FightController(generateChars());
        fc.start();
    }

    private static List<GameChar> generateChars(){
        List<GameChar> gameChars = new ArrayList<>();

        Spell fireSpell = new Spell(5,"Fire");
        Spell coldSpell = new Spell(10,"Cold");
        Spell electroSpell = new Spell(20,"Electro");

        GameChar gc1 = new GameChar(1, "Slow man");
        gc1.setSpell(fireSpell);
        GameChar gc2 = new GameChar(3, "Agile man");
        gc2.setSpell(coldSpell);
        GameChar gc3 = new GameChar(4, "MESSI");
        gc3.setSpell(electroSpell);
        GameChar gc4 = new GameChar(4, "MESSI2");
        gc4.setSpell(electroSpell);

        gameChars.add(gc1);
        gameChars.add(gc2);
        gameChars.add(gc3);
        gameChars.add(gc4);
        return gameChars;
    }

    private static final class FightController {
        private List<GameChar> gameChars;
        private long baseTimeValue;

        public FightController(long baseTimeValue, List<GameChar> gameChars) {
            this.baseTimeValue = baseTimeValue;
            this.gameChars = gameChars;
        }
        public FightController(List<GameChar> gameChars) {
            this.baseTimeValue = gameChars.stream().mapToLong(gc->gc.getSpeed()).distinct().reduce((i,acc)->i*acc).getAsLong()*2;
            System.out.println("Base value set to " + baseTimeValue);
            this.gameChars = gameChars;
        }

        public void start() {
            System.out.println("Starting fight");
            System.out.println("Gamechars size = " + gameChars.size());
            initCooldownTimers();
            System.out.println("Cooldown timers initialized");
            while (!isTheEnd()) {
                gameChars.forEach(GameChar::tickAndTurn);
            }
            gameChars.forEach(gc -> System.out.println(gc.charInfo()));
        }

        private void initCooldownTimers(){
            gameChars.forEach(gc->gc.initTurnTimer(baseTimeValue));
        }


        private boolean isTheEnd() {
            return gameChars.stream()
                    .anyMatch(gc -> gc.getTurnsCount() >= 100);
        }

        public List<GameChar> getGameChars() {
            return gameChars;
        }

        public void setGameChars(List<GameChar> gameChars) {
            this.gameChars = gameChars;
        }


        public long getBaseTimeValue() {
            return baseTimeValue;
        }

        public void setBaseTimeValue(long baseTimeValue) {
            this.baseTimeValue = baseTimeValue;
        }
    }

    private static final class GameChar {
        private int speed;
        private long turnsCount = 0;
        private Spell spell;
        private String name;
        private CooldownTimer turnTimer;

        public GameChar(int speed, String name) {
            this.speed = speed;
            this.name = name;
        }

        public void initTurnTimer(long baseTimerValue) {
            turnTimer = CooldownTimer.forSpeed(speed, baseTimerValue);
            System.out.println("Gamechar " + name + " initialized cdt: " + turnTimer.toString());
            if(spell!=null){
                spell.initCooldownTimer(baseTimerValue);
            }
        }

        public void tickAndTurn() {
            turnTimer.tick();
            if(spell!=null){
                spell.tick();
//                System.out.println("Spell " + getSpell().getName() + " cooldown: " + getSpell().getCurrentCooldown() + "/" + getSpell().getCooldown());
            }
            if (turnTimer.isReady()) {
//                System.out.println("Char " + getName() + " turned");
                turnTimer.reset();
                turnsCount++;
                if(spell!=null){
                    if(spell.canBeCast()){
                        spell.cast();
                    }
                }
            }
        }

        public String charInfo() {
            StringBuilder sb = new StringBuilder();
            sb.append("GameChar ")
                    .append(getName())
                    .append(" turned ")
                    .append(getTurnsCount())
                    .append(" times\n");
            if (getSpell() != null) {
                sb.append("Spell ")
                        .append(getSpell().getName())
                        .append(" was cast ")
                        .append(getSpell().getTimesCast())
                        .append(" times");
            }
            return sb.toString();
        }

        public int getSpeed() {
            return speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public long getTurnsCount() {
            return turnsCount;
        }

        public void setTurnsCount(long turnsCount) {
            this.turnsCount = turnsCount;
        }

        public Spell getSpell() {
            return spell;
        }

        public void setSpell(Spell spell) {
            this.spell = spell;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    private static final class Spell {
        private long cooldown;
        private long timesCast = 0;
        private String name;
        private CooldownTimer cooldownTimer;

        public Spell(long cooldown, String name) {
            this.cooldown = cooldown;
            this.name = name;
        }

        public void initCooldownTimer(long baseTimerValue) {
            cooldownTimer = CooldownTimer.forCooldown(cooldown, baseTimerValue);
            System.out.println("Spell " + name + " initialized cdt: " + cooldownTimer.toString());
        }

        public boolean canBeCast() {
            return cooldownTimer.isReady();
        }

        public void cast() {
            if (canBeCast()) {
                timesCast++;
//                System.out.println("Spell " + name + " casted");
                cooldownTimer.reset();
            }
        }

        public void tick() {
            cooldownTimer.tick();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getTimesCast() {
            return timesCast;
        }

        public void setTimesCast(long timesCast) {
            this.timesCast = timesCast;
        }

        public long getCooldown() {
            return cooldown;
        }

        public void setCooldown(long cooldown) {
            this.cooldown = cooldown;
        }

        public double getCurrentCooldown() {
            return cooldown * cooldownTimer.readiness();
        }

    }

    private static final class CooldownTimer {
        private static final double FACTOR = 20d;
        private double ticksToFire;
        private long currentTicks = 0;

        public static CooldownTimer forSpeed(long speed, long baseTimerValue) {
            CooldownTimer cdt = new CooldownTimer();
            cdt.setTicksToFire(((double) baseTimerValue) / speed);
            return cdt;
        }

        public static CooldownTimer forCooldown(long coolDown, long baseTimerValue) {
            CooldownTimer cdt = new CooldownTimer();
            double doubleCooldown = (double) coolDown;
            cdt.setTicksToFire(((double) baseTimerValue) / (1 / (doubleCooldown / FACTOR)));
            return cdt;
        }

        private CooldownTimer() {
        }

        public void tick() {
            if (currentTicks < ticksToFire) {
                currentTicks++;
            }
        }

        public double readiness() {
            if (isReady()) {
                return 1;
            }
            return ((double)currentTicks)/ticksToFire;
        }

        public boolean isReady() {
            return currentTicks >= ticksToFire;
        }

        public void reset() {
            currentTicks = 0;
        }

        public double getTicksToFire() {
            return ticksToFire;
        }

        public void setTicksToFire(double ticksToFire) {
            this.ticksToFire = ticksToFire;
        }

        public long getCurrentTicks() {
            return currentTicks;
        }

        public void setCurrentTicks(long currentTicks) {
            this.currentTicks = currentTicks;
        }

        @Override
        public String toString() {
            return "CooldownTimer{" +
                    "ticksToFire=" + ticksToFire +
                    ", currentTicks=" + currentTicks +
                    '}';
        }
    }
}
