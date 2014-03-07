package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Bank;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Players;
import org.soulsplit.api.methods.SceneObjects;
import org.soulsplit.api.methods.Skill;
import org.soulsplit.api.wrappers.SceneObject;

import paracutter.Data;

public class RunOpenBank implements Strategy {
	private static SceneObject booth;

	@Override
	public boolean activate() {
		if (Inventory.isFull() && !Bank.isOpen()) {
			if (SceneObjects.getNearest(Data.booth) != null) {
				if (SceneObjects.getNearest(Data.booth).length > 0) {
					for (SceneObject so : SceneObjects.getNearest(Data.booth)) {
						if (so != null) {
							booth = so;
							return true;
						}

					}
				}
			}
		}
		if (!Bank.isOpen()) {
			if (Skill.WOODCUTTING.getRealLevel() >= 45
					&& Inventory.getCount(Data.runeaxe) < 1) {
				if (SceneObjects.getNearest(Data.booth) != null) {
					if (SceneObjects.getNearest(Data.booth).length > 0) {
						for (SceneObject so : SceneObjects
								.getNearest(Data.booth)) {
							if (so != null) {
								booth = so;
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}

	@Override
	public void execute() {
		if (!Bank.isOpen()) {
			if (booth != null) {
				if (booth.distanceTo() > 5) {
					booth.getLocation().walkTo();
					while (Players.getMyPlayer().getAnimation() != -1) {
						Time.sleep(150);
					}
				}
				booth.interact(0);
				Time.sleep(2500);
			}
		}
	}
}
