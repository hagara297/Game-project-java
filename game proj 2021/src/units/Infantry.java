package units;

import exceptions.FriendlyFireException;

public class Infantry extends Unit {

	public Infantry(int level, int maxSoldierConunt, double idleUpkeep, double marchingUpkeep, double siegeUpkeep) {
		super(level, maxSoldierConunt, idleUpkeep, marchingUpkeep, siegeUpkeep);
	}public void attack(Unit target) throws FriendlyFireException{
		super.attack(target);
		if(target instanceof Archer ) {
			if(getLevel()==1) {
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.getCurrentSoldierCount()));
				
			}else if(getLevel()==2) {
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.4*this.getCurrentSoldierCount()));
				
			}else if(getLevel()==3) {
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.5*this.getCurrentSoldierCount()));

			}
			
		}else if(target instanceof Infantry) {
				if(getLevel()==1) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.1*this.getCurrentSoldierCount()));
					
				}if(getLevel()==2) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.getCurrentSoldierCount()));
					
				}
			else if(getLevel()==3) {
				target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.3*this.getCurrentSoldierCount()));

			}}if(target instanceof Cavalry) {
				if(getLevel()==1) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.1*this.getCurrentSoldierCount()));
					
				}if(getLevel()==2) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.2*this.getCurrentSoldierCount()));
					
				}else if(getLevel()==3) {
					target.setCurrentSoldierCount(target.getCurrentSoldierCount()-(int)(0.25*this.getCurrentSoldierCount()));
				}
			}}
	
		}
	

