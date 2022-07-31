package engine;

import java.util.ArrayList;

import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import units.Army;

public class City {
	private String name;
	private ArrayList<EconomicBuilding> economicalBuildings;
	private ArrayList<MilitaryBuilding> militaryBuildings;
	private Army defendingArmy;
	private int turnsUnderSiege;
	private boolean underSiege;

	public City(String name) {
		this.name = name;
		economicalBuildings = new ArrayList<EconomicBuilding>();
		militaryBuildings = new ArrayList<MilitaryBuilding>();
	    defendingArmy = new Army(name);
		turnsUnderSiege = -1;
	}

	public ArrayList<EconomicBuilding> getEconomicalBuildings() {
		return economicalBuildings;
	}

	public ArrayList<MilitaryBuilding> getMilitaryBuildings() {
		return militaryBuildings;
	}

	public Army getDefendingArmy() {
		return defendingArmy;
	}

	public void setDefendingArmy(Army defendingArmy) {
		this.defendingArmy = defendingArmy;
	}

	public int getTurnsUnderSiege() {
		return turnsUnderSiege;
	}

	public void setTurnsUnderSiege(int turnsUnderSiege) {
		this.turnsUnderSiege = turnsUnderSiege;
	}

	public String getName() {
		return this.name;
	}

	public boolean isUnderSiege() {
		return underSiege;
	}

	public void setUnderSiege(boolean underSiege) {
		this.underSiege = underSiege;
	}
	public MilitaryBuilding getMilitaryBuilding(String x) {
		for(int i=0;i<militaryBuildings.size();i++) {
			if(x.equalsIgnoreCase("Archer")) {
				if(militaryBuildings.get(i) instanceof ArcheryRange) {
					return militaryBuildings.get(i);
				}
			}if(x.equalsIgnoreCase("Infantry")) {
				if(militaryBuildings.get(i) instanceof Barracks) {
					return militaryBuildings.get(i);
				}
			}if(x.equalsIgnoreCase("Cavalry")) {
				if(militaryBuildings.get(i) instanceof Stable) {
					return militaryBuildings.get(i);
				}
			}
		}
		return null;
	}
	public void reseteconomicalBuildings() {
		for(int i=0;i<economicalBuildings.size();i++) {
			economicalBuildings.get(i).setCoolDown(false);
		}
	}public void resetmilitaryBuildings() {
		for(int i=0;i<militaryBuildings.size();i++) {
			militaryBuildings.get(i).setCoolDown(false);
		}
		
	}public void resetcurrentRecruit() {
		for(int i=0;i<this.getMilitaryBuildings().size();i++) {
			this.getMilitaryBuildings().get(i).setCurrentRecruit(0);
		}
	
		
		
		
}
	}
