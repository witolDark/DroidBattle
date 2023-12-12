package Droid;
import Droid.Droid.DroidClass;
import Main.FileWrite;

import java.util.ArrayList;

public class TankTalent {
    public static void Activate(ArrayList<Droid> teamMates) {
        if (!teamMates.isEmpty()) {
            for (Droid teamMate : teamMates) {
                if (teamMate.getDroidClass() != DroidClass.Tank) {
                    teamMate.setHealth(-50);
                    System.out.println(teamMate.getCodeName() + " healed by 50 hp");
                    FileWrite.writeToFile(teamMate.getCodeName() + " healed by 50 hp",
                            "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
                }
            }
        }
    }

    public static boolean tankLogic(ArrayList<Droid> teamMates) {
        for (Droid teamMate : teamMates)
            return teamMate.getHealthPoint() <= 1000 && !teamMate.getDead() && teamMate.getDroidClass() != DroidClass.Tank;
        return false;
    }
}
