package buildings;

import exceptions.BuildingInCoolDownException; 
import exceptions.MaxLevelException;
import exceptions.MaxRecruitedException;
import units.Archer;
import units.Unit;

public class ArcheryRange extends MilitaryBuilding {

	public ArcheryRange() {
		super(1500, 800, 400);

	}

	@Override
	public Unit recruit() throws BuildingInCoolDownException, MaxRecruitedException {
		if(this.isCoolDown()==true) {
			throw new BuildingInCoolDownException();
		}if(this.getCurrentRecruit()==this.getMaxRecruit()) {
			throw new MaxRecruitedException();
		}Archer x=null;
		if(this.getLevel()==1)
			x=new Archer(1,60,0.4,0.5,0.6);
		
	else if(this.getLevel()==2)
		x=new Archer(2,60,0.4,0.5,0.6);
		
	else if(this.getLevel()==3)
		x=new Archer(3,70,0.5,0.6,0.7);
		
		this.setCurrentRecruit(this.getCurrentRecruit()+1);

		return x;
	
	}

		
			
	public void upgrade() throws BuildingInCoolDownException, MaxLevelException {
		super.upgrade();

		 if(this.isCoolDown()==false) {
			 
			 if(this.getLevel()==1) {
				 this.setUpgradeCost(700);
				 this.setLevel(2);
				 this.setRecruitmentCost(450);
				 }
				 else {
				 this.setLevel(3);
				 this.setRecruitmentCost(500);
				 }
			 this.setCoolDown(true);
				 
				
			 }
			
			 
			 }
		

	}
