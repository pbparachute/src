package paracutter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.Painter;

import methods.Banking;
import methods.CutMaple;
import methods.CutOak;
import methods.CutTree;
import methods.CutWillow;
import methods.CutYew;
import methods.DropAll;
import methods.Logger;
import methods.RunOpenBank;

import org.parabot.environment.input.Mouse;
import org.parabot.environment.scripts.Category;
import org.parabot.environment.scripts.Script;
import org.parabot.environment.scripts.ScriptManifest;
import org.parabot.environment.scripts.framework.Strategy;

@ScriptManifest(author = "parachute", category = Category.WOODCUTTING, description = "Powerchopper", name = "paraCutter", servers = { "Soulsplit" }, version = 1.0)
public class Core extends Script {
	static ArrayList<Strategy> jobs = new ArrayList<>();

	@Override
	public boolean onExecute() {
		jobs.add(new CutTree());
		jobs.add(new CutOak());
		jobs.add(new CutWillow());
		jobs.add(new CutMaple());
		// jobs.add(new DropAll());
		jobs.add(new RunOpenBank());
		jobs.add(new CutYew());
		jobs.add(new Banking());
		provide(jobs);
		return true;
	}

	@Override
	public void onFinish() {

	}

}
