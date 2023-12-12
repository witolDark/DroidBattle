package Droid;
import Main.FileWrite;

import java.util.ArrayList;
import java.util.Objects;

public class DamageTalent {
    public static void Activate(Droid droid, ArrayList<Droid> enemies, int index) {
        enemies.get(index).setHealth(droid.getDamage() + 10);
        System.out.println(droid.getCodeName() + " uses skill " + enemies.get(index).getCodeName() + " with " + (droid.getDamage() + 10));
        FileWrite.writeToFile(droid.getCodeName() + " uses skill " + enemies.get(index).getCodeName() + " with " + (droid.getDamage() + 10),
                "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
        for (Droid enemy : enemies) {
            if (!enemy.getDead() && !enemy.getCodeName().equals(enemies.get(index).getCodeName())) {
                enemy.setHealth(droid.getDamage());
                System.out.println(droid.getCodeName() + " uses skill " + enemy.getCodeName() + " with " + droid.getDamage());
                FileWrite.writeToFile(droid.getCodeName() + " uses skill " + enemy.getCodeName() + " with " + droid.getDamage(),
                        "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
            }
        }
    }

    public static boolean damagerLogic(ArrayList<Droid> enemies) {
        int count = 0;
        for(Droid enemy : enemies) {
            if (!enemy.getDead()) count++;
        }
        return count > 1;
    }

    public static int damagerChooseEnemy(ArrayList<Droid> enemies) {
        int max = 0;
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i).getHealthPoint() > max)
                max = i;
            if (enemies.get(i).getHealthPoint() == 50)
                return i;
        }
        return max;
    }

}
