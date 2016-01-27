package com.thecop.rpg.chars;

public abstract class GameChar {

    protected int maxHp;
    protected int maxMp;
    protected int hp;
    protected int mp;
    protected int speed;
    protected int damage;
    protected int physicalResistance;
    protected int magicalResistance;

    public abstract int getMaxHp();

    public abstract int getMaxMp();

    public abstract int getHp();

    public abstract int getMp();

    public abstract int getSpeed();

    public abstract int getDamage();

    public abstract int getPhysicalResistance();

    public abstract int getMagicalResistance();
}
