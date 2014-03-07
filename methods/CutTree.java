package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.*;
import org.soulsplit.api.wrappers.SceneObject;
import paracutter.Data;

public class CutTree implements Strategy {

    private static SceneObject tree;

    @Override
    public boolean activate() {
        if (Skill.WOODCUTTING.getRealLevel() < 15
                && !Inventory.isFull()
                && Players.getMyPlayer().getAnimation() == -1) {
            if (SceneObjects.getNearest(Data.tree) != null) {
                if (SceneObjects.getNearest(Data.tree).length > 0) {
                    for (SceneObject so : SceneObjects.getNearest(Data.tree)) {
                        if (so != null){
                            tree = so;
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
        if (tree != null){
            if (tree.distanceTo() > 5){
                tree.getLocation().walkTo();
                while (Players.getMyPlayer().getAnimation() != -1){
                    Time.sleep(150);
                }
            }
            tree.interact(0);
            Time.sleep(2500);
        }
    }
}