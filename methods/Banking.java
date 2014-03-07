package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Bank;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Skill;
import org.soulsplit.api.wrappers.Item;

import paracutter.Data;

public class Banking implements Strategy {

	@Override
	public boolean activate() {
		if (Bank.isOpen() && Inventory.isFull()) {
			return true;
		}
		if (Bank.isOpen() && !Inventory.isFull()
				&& Skill.WOODCUTTING.getRealLevel() < 45) {
			return true;
		}
		if (Bank.isOpen() && Skill.WOODCUTTING.getRealLevel() >= 45) {
			if (Inventory.getCount(Data.runeaxe) < 1) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void execute() {
		Item axe = Bank.getItem(Data.runeaxe);
		if (Bank.isOpen() && Inventory.isFull()) {
			Bank.depositAllExcept(Data.runeaxe);
		}
		if (Bank.isOpen()) {
			if (Skill.WOODCUTTING.getRealLevel() >= 45) {
				if (Inventory.getCount(Data.runeaxe) < 1) {
					Logger.log("yolo");
					if(axe != null){
						axe.interact(0);
					}
					Time.sleep(2500);
				}
			} else if(!Inventory.isFull()){
				Logger.log("Closing");
				Bank.close();
			}

		}
	}
}
