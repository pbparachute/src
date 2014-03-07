package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.*;
import org.soulsplit.api.wrappers.SceneObject;
import paracutter.Data;

public class CutWillow implements Strategy {

    private static SceneObject willow;

    @Override
    public boolean activate() {
        if (Skill.WOODCUTTING.getRealLevel() >= 30
                && Skill.WOODCUTTING.getRealLevel() < 45
                && !Inventory.isFull()
                && Players.getMyPlayer().getAnimation() == -1) {
            if (SceneObjects.getNearest(Data.willow) != null) {
                if (SceneObjects.getNearest(Data.willow).length > 0) {
                    for (SceneObject so : SceneObjects.getNearest(Data.willow)) {
                        if (so != null){
                            willow = so;
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
        if (willow != null){
            if (willow.distanceTo() > 5){
                willow.getLocation().walkTo();
                while (Players.getMyPlayer().getAnimation() != -1){
                    Time.sleep(150);
                }
            }
            willow.interact(0);
            Time.sleep(2500);
        }
    }
}