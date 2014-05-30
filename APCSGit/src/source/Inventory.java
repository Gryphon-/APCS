package source;
import java.util.ArrayList;


//modified by Brandon, 5-22-14
/*
 Store separate instance variables for each type of armor 
 When equipping item, set Item from ArrayList to instance variable and remove from ArrayList
 
 */
public class Inventory implements CharDisplayable{
	
	private ItemEquipable HELMET, ARMOR, LEGGINGS, PRIM, SEC;
	
	private ArrayList<Items> inv;
	public static final int INV_MAX = 26;
	
	public Inventory(){
		inv = new ArrayList<Items>();
	}
	
	public ItemEquipable getHelmet(){
		return HELMET;
	}
	
	public ItemEquipable getArmor(){
		return ARMOR;
	}
	
	public ItemEquipable getPrimary(){
		return PRIM;
	}
	public ItemEquipable getSecondary(){
		return SEC;
	}
	public void add(Items added){
//		NEED TO FIX THIS
//		BUGGY (multiples by 2 instead of adding count)
		if(added.maxCount!=1){
			for(Items i:inv){
				if(i.id==added.id) {
					i.count+=added.count;
					return;
				}
			}
		}
		if (inv.size()==INV_MAX)
			return;
		else 
			inv.add(added);
	}
	
	public Items remove(int rem) {
		return inv.remove(rem);
	}
	
	public void remove (Items target){
		inv.remove(target);
	}
	
	public boolean isEmpty(){
		if (inv.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isFull(){
		if (inv.size() == INV_MAX){
			return true;
		}
		else{
			return false;
		}
	}
	
	public Items get(int num) {
		return inv.get(num);
	}
	
	public String toString(){
		String s = "";
		for (int i=0;i<INV_MAX;i++){
			s+=  (i+95) + "/t" + inv.get(i).getName() + "/n";
		}
		return s;
	}

	
	public char getDisplayChar() {
		return inv.size()==0?' ':inv.get(0).getDisplayChar();
	}
	
	public void equipItem(ItemEquipable item)
	{
		inv.remove(item);
		switch (item.getType()){
		case HEAD:
		{
			if (HELMET!=null)
			{
				inv.add(HELMET);
			}
			HELMET=item;
		}
		case CHEST:
		{
			if (ARMOR!=null)
			{
				inv.add(ARMOR);
			}
			ARMOR=item;
		}
		case LEGS:
		{
			if (LEGGINGS!=null)
			{
				inv.add(LEGGINGS);
			}
			LEGGINGS=item;
		}
		case PRIMARY:
		{
			if (PRIM!=null)
			{
				inv.add(PRIM);
			}
			PRIM=item;
			if (item instanceof ItemEquipable.twoHandWeapon)
			{
				inv.add(SEC);
				SEC=null;
			}
		}
		case SECONDARY:
		{
			if (SEC!=null)
			{
				inv.add(SEC);
			}
			if (PRIM!=null&&PRIM instanceof ItemEquipable.twoHandWeapon)
			{
				inv.add(PRIM);
				PRIM=null;
			}
			SEC=item;
			
		}
		}
	}
		
	
	public void unequipItem(ItemEquipable item){
		inv.add(item);
		switch (item.getType()){
		case HEAD:
		{
			HELMET=null;
		}
		case CHEST:
		{
			ARMOR=null;
		}
		case LEGS:
		{
			LEGGINGS=null;
		}
		case PRIMARY:
		{
			PRIM=null;
		}
		case SECONDARY:
		{
			SEC=null;
		}
	}
}
}
