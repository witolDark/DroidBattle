package DroidBattle;

import Droid.DamageTalent;
import Droid.Droid;
import Droid.TankTalent;
import Main.FileWrite;

import java.util.ArrayList;

import static java.util.Collections.swap;


public class DroidBattle {
    private final Droid droid11;
    private final Droid droid12;
    private final Droid droid13;
    private final Droid droid21;
    private final Droid droid22;
    private final Droid droid23;

    private final ArrayList<Droid> droids = new ArrayList<Droid>();
    private final ArrayList<Droid> teamRed = new ArrayList<Droid>();
    private final ArrayList<Droid> teamBlue = new ArrayList<Droid>();

    public DroidBattle(Droid droid11, Droid droid12, Droid droid13,
                       Droid droid21, Droid droid22, Droid droid23) {
        this.droid11 = droid11;
        this.droid12 = droid12;
        this.droid13 = droid13;
        this.droid21 = droid21;
        this.droid22 = droid22;
        this.droid23 = droid23;

        droids.add(droid11);
        droids.add(droid12);
        droids.add(droid13);
        droids.add(droid21);
        droids.add(droid22);
        droids.add(droid23);

        sortDroids();
        fillTeams();

        if (teamRed.size() < droids.size() / 2 || teamBlue.size() < droids.size() / 2) throw new RuntimeException("Team assembly error");
    }

    public void fillTeams() {
                    for (int i = 0; i < droids.size(); i++) {
                        switch (droids.get(i).getTeam()) {
                            case Red:
                                teamRed.add(droids.get(i));
                                break;
                            case Blue:
                                teamBlue.add(droids.get(i));
                    break;
            }
        }
    }

    public boolean checkTeam(ArrayList<Droid> team) {
        int count = 0;
        for (Droid droid : team)
            if (droid.getDead()) count++;
        return count == team.size();
    }

    public void doGame() {
        int round = 0;
        while (!checkTeam(teamRed) && !checkTeam(teamBlue)) {
            System.out.println("=======Round " + ++round + "=======");
            FileWrite.writeToFile("=======Round " + ++round + "=======",
                    "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
            doRound(droids, teamRed, teamBlue);
            for (int i = 0; i < droids.size(); i++)
                droids.get(i).showAtributes();
        }
        if (checkTeam(teamRed)) {
            System.out.println("Team Blue won");
            FileWrite.writeToFile("Team Blue won",
                    "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");
        }
        else {
            System.out.println("Team Red won");
            FileWrite.writeToFile("Team Red won",
                    "C:\\Users\\witold\\IdeaProjects\\DroidBattle\\Log");

        }
    }

    private void doRound(ArrayList<Droid> droids, ArrayList<Droid> teamRed, ArrayList<Droid> teamBlue) {
        for (int i = 0; i < droids.size(); i++) {
            if (!droids.get(i).getDead())
                doAction(droids.get(i), droids, teamRed, teamBlue, checkAction(droids.get(i), teamRed, teamBlue));
        }
    }

    private void doAction(Droid droid, ArrayList<Droid> droids, ArrayList<Droid> teamRed, ArrayList<Droid> teamBlue, boolean checkedAction) {
        if (checkedAction) {
            switch (droid.getDroidClass()) {
                case Tank:
                    switch (droid.getTeam()) {
                        case Red:
                            TankTalent.Activate(teamRed);
                            break;
                        case Blue:
                            TankTalent.Activate(teamBlue);
                            break;
                    }
                    break;
                case Damage_Dealer:
                    switch (droid.getTeam()) {
                        case Red:
                            DamageTalent.Activate(droid, teamBlue, DamageTalent.damagerChooseEnemy(teamBlue));
                            break;
                        case Blue:
                            DamageTalent.Activate(droid, teamRed, DamageTalent.damagerChooseEnemy(teamRed));
                            break;
                    }
                    break;
            }
        } else {
            switch (droid.getTeam()) {
                case Red:
                    droid.Attack(teamBlue, DamageTalent.damagerChooseEnemy(teamBlue));
                    break;
                case Blue:
                    droid.Attack(teamRed, DamageTalent.damagerChooseEnemy(teamBlue));
                    break;
            }
        }
    }

    private boolean checkAction(Droid droid, ArrayList<Droid> teamRed, ArrayList<Droid> teamBlue) {
        boolean action = false;
        switch (droid.getDroidClass()) {
            case Tank:
                switch (droid.getTeam()) {
                    case Red:
                        action = TankTalent.tankLogic(teamRed);
                        break;
                    case Blue:
                        action = TankTalent.tankLogic(teamBlue);
                        break;
                }
                break;
            case Damage_Dealer:
                switch (droid.getTeam()) {
                    case Red:
                        action = DamageTalent.damagerLogic(teamRed);
                        break;
                    case Blue:
                        action = DamageTalent.damagerLogic(teamBlue);
                        break;
                }
                break;
        }
        return action;
    }

    private void sortDroids() {
        for (int i = 0; i < droids.size(); i++) {
            int maxInd = i;
            for (int j = i; j < droids.size(); j++) {
                if (droids.get(j).getSpeed() > droids.get(maxInd).getSpeed())
                    maxInd = j;
            }
            swap(droids, i, maxInd);
        }
    }
}
