package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Players;
import org.soulsplit.api.methods.SceneObjects;
import org.soulsplit.api.methods.Skill;
import org.soulsplit.api.wrappers.SceneObject;

import paracutter.Data;

public class CutYew implements Strategy {
	private static SceneObject yew;

	@Override
	public boolean activate() {
		if (Skill.WOODCUTTING.getRealLevel() >= 60 && !Inventory.isFull()
				&& Players.getMyPlayer().getAnimation() == -1
				&& Inventory.getCount(Data.runeaxe) > 0) {
			if (SceneObjects.getNearest(Data.yew) != null) {
				if (SceneObjects.getNearest(Data.yew).length > 0) {
					for (SceneObject so : SceneObjects.getNearest(Data.yew)) {
						if (so != null) {
							yew = so;
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
		if (yew != null) {
			if (yew.distanceTo() > 5) {
				yew.getLocation().walkTo();
				while (Players.getMyPlayer().getAnimation() != -1) {
					Time.sleep(150);
				}
			}
			if (Players.getMyPlayer().getAnimation() == -1) {
				yew.interact(0);
			}
			Time.sleep(2500);
		}
	}

}
