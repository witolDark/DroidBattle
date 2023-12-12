package Droid;
import Main.FileWrite;

import java.util.ArrayList;

public class Droid {
    private final String codeName;
    private final DroidClass droidClass;
    private final Team team;
    private boolean isDead = false;
    private int healthPoint;
    private int damage;
    private int speed;
    public Droid(String codeName, DroidClass droidClass, Team team) {
        this.codeName = codeName;
        this.droidClass = droidClass;
        this.team = team;
        setAtributes();
    }

    public String getCodeName() {
        return codeName;
    }

    public boolean getDead() {
        return isDead;
    }

    public int getHealthPoint() {
        return  healthPoint;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public DroidClass getDroidClass() {
        return droidClass;
    }

    public Team getTeam() {
        return team;
    }

    public void setHealth(int damage) {
        if (!isDead) healthPoint -= damage;
        if (healthPoint <= 0) isDead = true;
    }

    public void Attack(ArrayList<Droid> enemies, int index) {
        enemies.get(index).setHealth(this.getDamage());
        System.out.println(this.getCodeName() + " atttacks " + enemies.get(index).getCodeName() + " with " + (this.getDamage()));
        FileWrite.writeToFile(this.getCodeName() + " atttacks " + enemies.get(index).getCodeName() + " with " + (this.getDamage()),
                "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
    }

    private void setAtributes() {
        switch (droidClass) {
            case Tank:
                healthPoint = (int) (1400 + Math.random() * 500);
                damage = (int) (10 + Math.random() * 10);
                speed = (int) (80 + Math.random() * 130);
                break;
            case Damage_Dealer:
                healthPoint = (int) (1000 + Math.random() * 500);
                damage = (int) (30 + Math.random() * 10);
                speed = (int) (90 + Math.random() * 140);
                break;
            default:
                healthPoint = 0;
                damage = 0;
                speed = 0;
        }
    }

    public void showAtributes() {
        if (!isDead) {
            System.out.println(codeName + "\t " + healthPoint + "\t " + damage + "\t " + droidClass + "\t Speed: " + speed + "\t Team" + team);
            FileWrite.writeToFile(codeName + "\t " + healthPoint + "\t " + damage + "\t " + droidClass + "\t Speed: " + speed + "\t Team" + team,
                    "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
        }
    }

    public enum DroidClass {
        Damage_Dealer,
        Tank
    }
    public enum Team {
        Blue,
        Red
    }
}
