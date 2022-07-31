package engine;

import java.util.ArrayList;
import buildings.ArcheryRange;
import buildings.Barracks;
import buildings.Building;
import buildings.EconomicBuilding;
import buildings.Farm;
import buildings.Market;
import buildings.MilitaryBuilding;
import buildings.Stable;
import exceptions.BuildingInCoolDownException;
import exceptions.FriendlyCityException;
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import exceptions.NotEnoughGoldException;
import exceptions.TargetNotReachedException;
import units.Army;
import units.Status;
import units.Unit;
public class Player {
	private String name;
	private ArrayList<City> controlledCities;
	private ArrayList<Army> controlledArmies;
	private double treasury;
	private double food;

	public Player(String name) {
		this.name = name;
		this.controlledCities = new ArrayList<City>();
		this.controlledArmies = new ArrayList<Army>();
	}

	public double getTreasury() {
		return treasury;
	}

	public void setTreasury(double treasury) {
		this.treasury = treasury;
	}

	public double getFood() {
		return food;
	}

	public void setFood(double food) {
		this.food = food;
	}

	public String getName() {
		return name;
	}

	public ArrayList<City> getControlledCities() {
		return controlledCities;
	}

	public ArrayList<Army> getControlledArmies() {
		return controlledArmies;
	}
public void recruitUnit(String type,String cityName) throws
BuildingInCoolDownException, MaxRecruitedException, NotEnoughGoldException{
	for(int i=0;i<controlledCities.size();i++) {
		if(this.getControlledCities().get(i).getName().equalsIgnoreCase(cityName)) {
	for(int k=0;k<this.getControlledCities().get(i).getMilitaryBuildings().size();k++) {
		if(this.getControlledCities().get(i).getMilitaryBuildings().get(k) instanceof ArcheryRange && type.equalsIgnoreCase("Archer")) {
			if(this.getControlledCities().get(i).getMilitaryBuildings().get(k).isCoolDown()==true) {
				throw new BuildingInCoolDownException("");
			}if(this.getControlledCities().get(i).getMilitaryBuildings().get(k).getCurrentRecruit()>=3) {
				throw new MaxRecruitedException("");
			}if(this.getTreasury()<this.getControlledCities().get(i).getMilitaryBuildings().get(k).getCost()) {
				throw new NotEnoughGoldException("");
			}this.setTreasury(getTreasury()-this.getControlledCities().get(i).getMilitaryBuildings().get(k).getRecruitmentCost());
			Unit l=this.getControlledCities().get(i).getMilitaryBuildings().get(k).recruit();
			this.getControlledCities().get(i).getDefendingArmy().getUnits().add(l);
			l.setParentArmy(this.getControlledCities().get(i).getDefendingArmy());
			
			
		}
		if(this.getControlledCities().get(i).getMilitaryBuildings().get(k) instanceof Barracks && type.equalsIgnoreCase("Infantry")) {
			if(this.getControlledCities().get(i).getMilitaryBuildings().get(k).isCoolDown()==true) {
				throw new BuildingInCoolDownException("");
			}if(this.getControlledCities().get(i).getMilitaryBuildings().get(k).getCurrentRecruit()>=3) {
				throw new MaxRecruitedException("");
			}if(this.getTreasury()<this.getControlledCities().get(i).getMilitaryBuildings().get(k).getCost()) {
				throw new NotEnoughGoldException("");
			}this.setTreasury(getTreasury()-this.getControlledCities().get(i).getMilitaryBuildings().get(k).getRecruitmentCost());
			Unit l=this.getControlledCities().get(i).getMilitaryBuildings().get(k).recruit();
			this.getControlledCities().get(i).getDefendingArmy().getUnits().add(l);
			l.setParentArmy(this.getControlledCities().get(i).getDefendingArmy());
			}
		if(this.getControlledCities().get(i).getMilitaryBuildings().get(k) instanceof Stable && type.equalsIgnoreCase("Cavalry")) {
			if(this.getControlledCities().get(i).getMilitaryBuildings().get(k).isCoolDown()==true) {
				throw new BuildingInCoolDownException("");
			}if(this.getControlledCities().get(i).getMilitaryBuildings().get(k).getCurrentRecruit()>=3) {
				throw new MaxRecruitedException("");
			}if(this.getTreasury()<this.getControlledCities().get(i).getMilitaryBuildings().get(k).getCost()) {
				throw new NotEnoughGoldException("");
			}this.setTreasury(getTreasury()-this.getControlledCities().get(i).getMilitaryBuildings().get(k).getRecruitmentCost());
			Unit l=this.getControlledCities().get(i).getMilitaryBuildings().get(k).recruit();
			this.getControlledCities().get(i).getDefendingArmy().getUnits().add(l);
			l.setParentArmy(this.getControlledCities().get(i).getDefendingArmy());
		
		}
	
	
	
		
		}
	}
	}
		}
	
	
	
	
	
	
	
public void build(String type,String cityName) throws NotEnoughGoldException{
	for(int l=0;l<this.getControlledCities().size();l++) {
		if(this.getControlledCities().get(l).getName().equals(cityName)) {
	if(type.equalsIgnoreCase("ArcheryRange") || type.equalsIgnoreCase("Barracks") || type.equalsIgnoreCase("Stable")) {
		MilitaryBuilding k=this.makeMilitaryBuilding(type);
		if(this.getTreasury()<k.getCost()) {
			throw new NotEnoughGoldException("no enough gold");
		}else if(this.getTreasury()>=k.getCost()) {
			Boolean flag=true;
		for(int i=0;i<this.getControlledCities().get(l).getMilitaryBuildings().size();i++) {
			if(this.getControlledCities().get(l).getMilitaryBuildings().get(i) instanceof Barracks && type.equalsIgnoreCase("Barracks")) {
				flag=false;
			}if(this.getControlledCities().get(l).getMilitaryBuildings().get(i) instanceof ArcheryRange && type.equalsIgnoreCase("ArcheryRange")) {
				flag=false;
			}if(this.getControlledCities().get(l).getMilitaryBuildings().get(i) instanceof Stable && type.equalsIgnoreCase("Stable")) {
				flag=false;
			}
		}if(flag==true) {
			this.getControlledCities().get(l).getMilitaryBuildings().add(k);
			this.setTreasury(this.getTreasury()-k.getCost());
		}else
			break;
		}
	}else {
		EconomicBuilding y=this.makeEconomicBuilding(type);
		if(this.getTreasury()<y.getCost()) {
			throw new NotEnoughGoldException("no enough gold");
		}else if(this.getTreasury()>=y.getCost()) {
			Boolean flag=true;
		for(int i=0;i<this.getControlledCities().get(l).getEconomicalBuildings().size();i++) {
			if(this.getControlledCities().get(l).getEconomicalBuildings().get(i) instanceof Market && type.equalsIgnoreCase("Market")) {
				flag=false;
			}if(this.getControlledCities().get(l).getEconomicalBuildings().get(i) instanceof Farm && type.equalsIgnoreCase("Farm")) {
				flag=false;
			}
		}if(flag==true) {
			this.getControlledCities().get(l).getEconomicalBuildings().add(y);
			this.setTreasury(this.getTreasury()-y.getCost());
		}else 
			break;
		}
	}}}
		
}	 
public void upgradeBuilding(Building b) throws NotEnoughGoldException, BuildingInCoolDownException, MaxLevelException {
	 if(this.getTreasury()<b.getCost()) {
		 throw new NotEnoughGoldException("No enough money");
	 }
	 if(b.isCoolDown()==true) {
		 throw new BuildingInCoolDownException("building is under constructions");
		 }
	 if(b.getLevel()>=3) {
		 throw new MaxLevelException("maxium level has been reached");
		 }
	 this.setTreasury(this.getTreasury()-b.getUpgradeCost());
	 b.upgrade();
	 }
	 
	 

public void initiateArmy(City city,Unit unit) {
	Army x=new Army(city.getName());
	x.getUnits().add(unit);
	city.getDefendingArmy().getUnits().remove(unit);
	unit.setParentArmy(x);
	getControlledArmies().add(x);
	
}
public void laySiege(Army army,City city) throws TargetNotReachedException,
FriendlyCityException{
	boolean f=false;
	String x=city.getName();	
	for(int i=0;i<this.controlledCities.size();i++) {
		if(this.controlledCities.get(i)==city) 
			f=true;	}
if(f==true) 
	throw new FriendlyCityException();
else if(!army.getCurrentLocation().equals(x)) {
	throw new TargetNotReachedException();
}
else {	
	
	army.setCurrentStatus(Status.BESIEGING);
    city.setUnderSiege(true);
    city.setTurnsUnderSiege(city.getTurnsUnderSiege()+1);
}
	
}

public EconomicBuilding makeEconomicBuilding(String x) {
	EconomicBuilding h=new Market();
	EconomicBuilding k=new Farm();
	if(x.equalsIgnoreCase("Market")) {
		return h;
	}else 
		return k;
}public MilitaryBuilding makeMilitaryBuilding(String x) {
	MilitaryBuilding h=new ArcheryRange();
	MilitaryBuilding k=new Barracks();
	MilitaryBuilding l=new Stable();
	if(x.equalsIgnoreCase("Stable")) {
		return l;
	}else if(x.equalsIgnoreCase("Barracks"))
		return k;
	else
		return h;
}public void loses10() {
	for(int i=0;i<this.getControlledArmies().size();i++) {
		this.getControlledArmies().get(i).lose10();
	}
}

}


