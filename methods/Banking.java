package methods;

import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Bank;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Skill;

import paracutter.Data;

public class Banking implements Strategy {

	@Override
	public boolean activate() {
		if (Bank.isOpen() && Inventory.isFull()) {
			return true;
		}
		if (Bank.isOpen() && !Inventory.isFull() && Skill.WOODCUTTING.getRealLevel() < 45){
			return true;
		}
		if (Bank.isOpen() && !Inventory.isFull()
				&& Inventory.getCount(Data.runeaxe) == 0) {
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		if (Bank.isOpen() && Inventory.isFull()) {
			Bank.depositAllExcept(Data.runeaxe);
		}
		if (Bank.isOpen() && !Inventory.isFull() && Skill.WOODCUTTING.getRealLevel() < 45) {
			Bank.close();
		}
		if (Bank.isOpen() && !Inventory.isFull()
				&& Inventory.getCount(Data.runeaxe) == 0) {
			Bank.withdraw(Data.runeaxe, 1, 1000);
		}
		if (Bank.isOpen() && !Inventory.isFull() && Skill.WOODCUTTING.getRealLevel() >= 45 && Inventory.getCount(Data.runeaxe) > 0) {
			Bank.close();
		}
	}
}
