package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.*;
import org.soulsplit.api.wrappers.SceneObject;
import paracutter.Data;

public class CutOak implements Strategy {

    private static SceneObject oak;

    @Override
    public boolean activate() {
        if (Skill.WOODCUTTING.getRealLevel() < 30
                && Skill.WOODCUTTING.getRealLevel() >= 15
                && !Inventory.isFull()
                && Players.getMyPlayer().getAnimation() == -1) {
            if (SceneObjects.getNearest(Data.oak) != null) {
                if (SceneObjects.getNearest(Data.oak).length > 0) {
                    for (SceneObject so : SceneObjects.getNearest(Data.oak)) {
                        if (so != null){
                            oak = so;
                            Logger.log("Cutting tree");
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void execute() {
        if (oak != null){
            if (oak.distanceTo() > 5){
                oak.getLocation().walkTo();
                while (Players.getMyPlayer().getAnimation() != -1){
                    Time.sleep(150);
                }
            }
            oak.interact(0);
            Time.sleep(2500);
        }
    }
}