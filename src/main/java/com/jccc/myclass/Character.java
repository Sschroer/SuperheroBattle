package com.jccc.myclass;

import java.io.Serializable;
import java.util.Random;

/**
 * This is the character class.
 *
 * @author Stephen Schroer
 */
public class Character implements Serializable {

  Random rand = new Random();
  private String name;
  private int health;
  private int attack;
  private Type type;

  /**
   * This is the constructor for the character.
   */
  public Character(String name, Type type) {
    super();
    this.name = name;
    this.type = type;
    health = rand.nextInt(51) + 50;
    attack = rand.nextInt(21);
    // not doing an all args constructor because no
    // human-like characters know their exact health or attack.

  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;

  }

  public int getAttack() {
    return attack;
  }

  public void setAttack(int attack) {
    this.attack = attack;
  }

  public String getName() {
    return name;
  }

  public Type getType() {
    return type;
  }

  @Override
  public String toString() {
    return "Character [name=" + name + ", health=" + health + ", attack=" + attack + ", type="
        + type + "]";
  }

}