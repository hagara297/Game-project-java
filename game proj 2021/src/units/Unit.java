package units;

import exceptions.FriendlyFireException;

public abstract class Unit {
	private int level;
	private int maxSoldierCount;
	private int currentSoldierCount;
	private double idleUpkeep;
	private double marchingUpkeep;
	private double siegeUpkeep;
	private Army parentArmy;

	public Unit(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		this.level = level;
		this.maxSoldierCount = maxSoldierConunt;
		this.currentSoldierCount=maxSoldierConunt;
		this.idleUpkeep = idleUpkeep;
		this.marchingUpkeep = marchingUpkeep;
		this.siegeUpkeep = siegeUpkeep;
		
		

	}

	public int getCurrentSoldierCount() {
		return currentSoldierCount;
	}

	public void setCurrentSoldierCount(int currentSoldierCount) {
		this.currentSoldierCount=currentSoldierCount;
		if(this.currentSoldierCount<=0) {
			this.currentSoldierCount=0;
			if(this.parentArmy!=null) {
				this.parentArmy.handleAttackedUnit(this); 
			}
		}
		
	}

	public int getLevel() {
		return level;
	}

	public int getMaxSoldierCount() {
		return maxSoldierCount;
	}

	public double getIdleUpkeep() {
		return idleUpkeep;
	}

	public double getMarchingUpkeep() {
		return marchingUpkeep;
	}

	public double getSiegeUpkeep() {
		return siegeUpkeep;
	}

	public Army getParentArmy() {
		return parentArmy;
	}

	public void setParentArmy(Army parentArmy) {
		this.parentArmy = parentArmy;
	} 
	public void attack(Unit target) throws FriendlyFireException{
		if(parentArmy.getUnits().contains(target)) {
		throw new FriendlyFireException("this is a friendly army");
		}
	
		
		
		}public boolean EqualUnits(Unit x) {
			if(level==x.getLevel() && maxSoldierCount==x.getMaxSoldierCount() && idleUpkeep==x.getIdleUpkeep() && marchingUpkeep==x.getMarchingUpkeep() && siegeUpkeep==x.getSiegeUpkeep()) {
				return true;
			}return false;
		}public void Reset() {
			this.setCurrentSoldierCount(this.getMaxSoldierCount());
		}
		
}
