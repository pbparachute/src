package methods;

import org.parabot.environment.api.utils.Time;
import org.parabot.environment.scripts.framework.Strategy;
import org.soulsplit.api.methods.Inventory;
import org.soulsplit.api.methods.Menu;
import org.soulsplit.api.wrappers.Item;

import paracutter.Data;

public class DropAll implements Strategy {

	@Override
	public boolean activate() {
		if (Inventory.isFull()) {
			System.out.println("Checking: Inv full?");
			return true;
		}
		return false;
	}

	@Override
	public void execute() {
		for (Item i : Inventory.getItems()) {
			if (i.getId() != Data.runeaxe && i.getId() != Data.ironaxe){
				System.out.println(i.getId());
				Menu.drop(i);
				Time.sleep(3000);
				System.out.println("Inv full: dropping");
			}
		}

	}
}
