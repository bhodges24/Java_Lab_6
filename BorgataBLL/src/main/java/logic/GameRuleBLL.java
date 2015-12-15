package logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import domain.GameRuleCardsDomainModel;
import domain.GameRuleDomainModel;
import base.GameRuleCardsDAL;
import base.GameRuleDAL;

public class GameRuleBLL extends GameRuleDomainModel {

	public static ArrayList<GameRuleDomainModel> getRules() {
		
		return GameRuleDAL.getRules();

	}
	
	public static HashMap<String, GameRuleDomainModel> getRuleHashSet()
	{
		HashMap<String, GameRuleDomainModel> HashRuleSet = new HashMap();
		
		for (GameRuleDomainModel gr: getRules())
		{
			HashRuleSet.put(gr.getRULENAME(), gr);
		}
		return HashRuleSet;
		
	}
	
	public static GameRuleDomainModel FindDefaultGameRule(){
		ArrayList<GameRuleDomainModel> game_rules = getRules();
		
		for(GameRuleDomainModel GR : game_rules){
			if(GR.getDEFAULTGAME() == 1){
				return GR;
			}
		}
		
		return null;
	}
	
public static GameRuleDomainModel getGameRule(String RuleName) {
		
		ArrayList<GameRuleDomainModel> game_rules = new ArrayList<GameRuleDomainModel>();
		
		for (GameRuleDomainModel GR: GameRuleDAL.getRules())
		{
			if (GR.getRULENAME() == RuleName)
			{
				return GR;
			}
		}
		
		return null;

	}

	
}
