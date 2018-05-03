package com.jingguitan.simplereader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;

public class ListMembers {
	public static void listOfDeceased(ArrayList<Individual> arraylist){
		Collections.sort(arraylist);
		System.out.println("List of individuals who are deceased:\n");
		for(Individual indi:arraylist){
			if(indi.getAlive()!=true){
				System.out.println(indi.getName());
			}
		}
		System.out.println("\n");
	}
	
	public static void listLivingMarried(ArrayList<Individual> arraylist1,ArrayList<Family> arraylist2){
		Collections.sort(arraylist1);;
		Collections.sort(arraylist2);
		String husb="",wife="",wife2="";
		System.out.println("List of all living married couples\n");
			for(Family fam:arraylist2){
				if(fam.getDivorced()=="NA"){
					for(Individual indi:arraylist1){
						if(fam.getHusbandName()==indi.getName()&&indi.getAlive()==true){
							husb=fam.getHusbandName();
							wife=fam.getWifeName();
						}
						if(wife==indi.getName()&&indi.getAlive()==true){
							wife=indi.getName();	
							if(wife!=wife2){
								System.out.println("Husband:"+husb+"\tWife:"+wife);	
							}
							wife2=indi.getName();
							husb=fam.getHusbandName();
						}
					}
				}	
			}
		System.out.println("\n");
	}
}
						  
			


