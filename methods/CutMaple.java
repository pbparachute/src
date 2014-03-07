package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.*;
import org.soulsplit.api.wrappers.SceneObject;

import paracutter.Data;

public class CutMaple implements Strategy {

	private static SceneObject maple;

	@Override
	public boolean activate() {
		if (Skill.WOODCUTTING.getRealLevel() < 60
				&& Skill.WOODCUTTING.getRealLevel() >= 45
				&& Inventory.getCount(Data.runeaxe) > 0
				&& !Inventory.isFull()
				&& Players.getMyPlayer().getAnimation() == -1) {
			if (SceneObjects.getNearest(Data.maple) != null) {
				if (SceneObjects.getNearest(Data.maple).length > 0) {
					for (SceneObject so : SceneObjects.getNearest(Data.maple)) {
						if (so != null) {
							maple = so;
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
		if (maple != null) {
			if (maple.distanceTo() > 5) {
				maple.getLocation().walkTo();
				while (Players.getMyPlayer().getAnimation() != -1) {
					Time.sleep(150);
				}
			}
			if (Players.getMyPlayer().getAnimation() == -1) {
				maple.interact(0);
			}
			Time.sleep(2500);
		}
	}
}