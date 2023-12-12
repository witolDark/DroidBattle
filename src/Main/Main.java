package Main;
import Droid.Droid;
import Droid.Droid.DroidClass;
import DroidBattle.DroidBattle;

public class Main {
    public static void main(String[] args) {
        // Blue Team
        Droid droid11 = new Droid("Gortash",DroidClass.Tank, Droid.Team.Blue);
        Droid droid12 = new Droid("Orin", DroidClass.Damage_Dealer, Droid.Team.Blue);
        Droid droid13 = new Droid("Keteric", DroidClass.Damage_Dealer, Droid.Team.Blue);
        // Red Team
        Droid droid21 = new Droid("Mintara", DroidClass.Tank, Droid.Team.Red);
        Droid droid22 = new Droid("Witold", DroidClass.Damage_Dealer, Droid.Team.Red);
        Droid droid23 = new Droid("ShadowHeart", DroidClass.Damage_Dealer, Droid.Team.Red);
        // Start game
        DroidBattle droidBattle = new DroidBattle(droid11, droid12, droid13, droid21, droid22, droid23);
        droidBattle.doGame();
    }
}
