package pokerBase;

import java.util.ArrayList;
import java.util.UUID;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import base.GameRuleCardsDAL;
import domain.DeckDomainModel;
import domain.GamePlayDomainModel;
import domain.GameRuleCardsDomainModel;
import domain.GameRuleDomainModel;
import domain.RuleDomainModel;
import logic.GameRuleBLL;

public class GamePlay extends GamePlayDomainModel {

	private ArrayList<Player> GamePlayers = new ArrayList<Player>();
	private ArrayList<GamePlayPlayerHand> GamePlayerHand = new ArrayList<GamePlayPlayerHand>();
	private ArrayList<GamePlayPlayerHand> GameCommonHand = new ArrayList<GamePlayPlayerHand>();
	private GameRuleDomainModel game_rule;
	private GameRuleCardsDomainModel game_rule_cards;
	private Deck GameDeck = null;
	
	public GamePlay(GameRuleDomainModel game_rule)
	{
		this.setGameID(UUID.randomUUID());
		this.setNbrOfCards(game_rule.getPLAYERNUMBEROFCARDS());
		this.setMaxNbrOfPlayers(game_rule.getMAXNUMBEROFPLAYERS());
		this.setNbrOfJokers(game_rule.getNUMBEROFJOKERS());
		this.setWildCards(null);
		this.game_rule = game_rule;
	}
	
	public GamePlay(GameRuleDomainModel game_rule, GameRuleCardsDomainModel game_rule_cards)
	{
		this(game_rule);
		this.game_rule_cards = game_rule_cards;
	}
	
	public GameRuleCardsDomainModel getGameRuleCards(){
		return this.game_rule_cards;
	}
	
	public GameRuleDomainModel getGameRule()
	{
		return this.game_rule;
	}
	public ArrayList<Player> getGamePlayers() {
		return GamePlayers;
	}

	public void setGamePlayers(ArrayList<Player> gamePlayers) {
		GamePlayers = gamePlayers;
	}
	
	public void addPlayerToGame(Player p)
	{
		GamePlayers.add(p);
	}

	public Deck getGameDeck() {
		return GameDeck;
	}

	public void setGameDeck(Deck gameDeck) {
		GameDeck = gameDeck;
	}
	
	public void addGamePlayPlayerHand(GamePlayPlayerHand GPPH)
	{
		GamePlayerHand.add(GPPH);
	}
	
	public void addGamePlayCommonHand(GamePlayPlayerHand GPCH)
	{
		GameCommonHand.add(GPCH);
	}

	public GamePlayPlayerHand FindCommonHand(GamePlay gme)
	{
		GamePlayPlayerHand GPCH = null;
		for (GamePlayPlayerHand GPPH: GameCommonHand)
		{
			if (GPPH.getGame().getGameID() == gme.getGameID())
			{
				GPCH = GPPH;
			}
		}		
		return GPCH;
	}
	
	public GamePlayPlayerHand FindPlayerGame(GamePlay gme, Player p)
	{
		GamePlayPlayerHand GPPHReturn = null;
		
	
		for (GamePlayPlayerHand GPPH: GamePlayerHand)
		{
			if (p.getiPlayerPosition() == GPPH.getPlayer().getiPlayerPosition())
			{
				GPPHReturn = GPPH;
			}
		}
		return GPPHReturn;
	}
	
	public static int[] getiCardstoDraw(){
		ArrayList<GameRuleCardsDomainModel> iCard_list = GameRuleCardsDAL.getCardsRules();
		int[] iCardsDrawn = new int[iCard_list.size()];
		
		int i = 0;
		for(GameRuleCardsDomainModel GRC : iCard_list){
			iCardsDrawn[i] = GRC.getNBROFCARDS();
			i++;
		}
		
		return iCardsDrawn;
	}
	
}
