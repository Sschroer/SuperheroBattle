package com.jccc.myclass;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;

/**
 * This is the class for EpicBattle.
 *
 * @author Stephen Schroer
 *
 */
public class EpicBattle {

  public static ArrayList<Character> villainList = new ArrayList<>();
  public static ArrayList<Character> heroList = new ArrayList<>();
  private static int heroWinCount;
  private static int villainWinCount;

  /**
   * This is the main EpicBattle Program.
   */
  public static void main(String[] args) throws ClassNotFoundException, IOException {

    heroList.add(makeCharacter("Izuku Midoriya", Type.HERO));
    heroList.add(makeCharacter("Katsuki Bakugo", Type.HERO));
    heroList.add(makeCharacter("Shoto Todoroki", Type.HERO));

    villainList.add(makeCharacter("Tomura Shigaraki", Type.VILLAIN));
    villainList.add(makeCharacter("Dabi", Type.VILLAIN));
    villainList.add(makeCharacter("Himiko Toga", Type.VILLAIN));

    writeToSavefile("originalStats.save", heroList, villainList);

    setCount();
    battleSimulation();

    restoreHealth("originalStats.save");

    System.out.println();
    System.out.println();

    battleSimulation();

    restoreHealth("originalStats.save");

    System.out.println();
    System.out.println();

    System.out.println("****HERO attacks first!****");
    attackSwitch();
    battleSimulation();

  }

  /**
   * This method makes new characters. Types are villain and Hero.
   */
  public static Character makeCharacter(String name, Type type) {
    return new Character(name, type);
  }

  /**
   * This method runs the battle simulation.
   */
  public static void battleSimulation() {
    Random rand = new Random();
    //setCount(); not needed as it is called in main method.

    while (heroList.size() > 0 || villainList.size() > 0) {

      if (heroList.size() == 0 ^ villainList.size() == 0) {

        if (heroWinCount > villainWinCount) {
          System.out.println("Heroes Win!");
          System.out.println(heroWinCount + " : " + villainWinCount);

        } else if (heroWinCount < villainWinCount) {
          System.out.println("Villians Win!");
          System.out.println(heroWinCount + " : " + villainWinCount);
        }

        setCount();
        break;
      }

      int heroIndex = rand.nextInt(heroList.size());
      int villainIndex = rand.nextInt(villainList.size());
      Character villain = villainList.get(villainIndex);
      Character hero = heroList.get(heroIndex);
      boolean isDead = false;

      System.out.println("~~~" + hero.getName() + " vs " + villain.getName() + "~~~");

      while (!isDead) {

        xattacksY(villain, hero);

        if (hero.getHealth() <= 0) {
          System.out.println("The " + villain.getType() + " " + villain.getName() + " wins!");
          hero.setHealth(0); // no such thing as neg. health
          heroList.remove(heroIndex);
          givePoint(villain);
          isDead = true;
          printStats(hero, villain);
          continue;
        }

        xattacksY(hero, villain);

        if (villain.getHealth() <= 0) {
          System.out.println("The " + hero.getType() + " " + hero.getName() + " wins!");
          villain.setHealth(0);
          villainList.remove(villainIndex);
          givePoint(hero);
          isDead = true;
          printStats(hero, villain);
        }

      }
    }

  }

  /**
   * This is the attack method. Its parameters are not communitive.
   */
  public static void xattacksY(Character attacker, Character defender) {
    System.out.println("The " + attacker.getType() + " attacks!");
    defender.setHealth(defender.getHealth() - attacker.getAttack());
  }

  /**
   * Prints the toString() of the hero and villain.
   */
  public static void printStats(Character hero, Character villain) {

    System.out.println();
    System.out.println("---FINAL HEATLH---");
    System.out.println(hero.toString());
    System.out.println(villain.toString());
    System.out.println();
  }

  /**
   * This method will switch the start pattern of the next battle simulation.
   */
  public static void attackSwitch() {
    ArrayList<Character> temp;
    temp = villainList;
    villainList = heroList;
    heroList = temp;
  }

  /**
   * this method checks the winners type and adds one to the correct counter.
   */
  private static void givePoint(Character winner) {
    if (winner.getType() == Type.HERO) {
      heroWinCount++;
    } else {
      villainWinCount++;
    }
  }

  /**
   * saves all character data to a new file.
   *
   */
  public static String writeToSavefile(String fileName, ArrayList<Character> team1,
      ArrayList<Character> team2) throws IOException {

    FileOutputStream fos = new FileOutputStream(fileName);
    ObjectOutputStream oos = new ObjectOutputStream(fos);

    oos.writeObject(team1);
    oos.writeObject(team2);
    oos.close();
    
    return fileName;
  }

  /**
   * reolads save data into character lists.
   */
  @SuppressWarnings("unchecked")
  public static void restoreHealth(String fileName) throws IOException, ClassNotFoundException {

    FileInputStream fis = new FileInputStream(fileName);
    ObjectInputStream ois = new ObjectInputStream(fis);

    heroList = (ArrayList<Character>) ois.readObject();
    villainList = (ArrayList<Character>) ois.readObject();
    ois.close();
  }

  /**
   * This method sets/resets the win count.
   */
  public static void setCount() {
    villainWinCount = 0;
    heroWinCount = 0;
  }
}
