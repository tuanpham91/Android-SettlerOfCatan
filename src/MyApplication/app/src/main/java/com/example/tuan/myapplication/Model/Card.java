package com.example.tuan.myapplication.model;

/**
 * Created by Tuan on 18.07.2017.
 */

public  class Card {
    public CardType type;
    public Card (CardType type ) {
        this.type = type;
    }
    public enum CardType {
        LONGEST_ROAD, BIGGEST_ARMY, STREET_BUILD, MONOPOLY , KNIGHT , INVENTION, VICTORY_POINT;
    }
    public static CardType fromStringDe(String type){
        switch (type) {
            case "Ritter" :
                return CardType.KNIGHT;
            case "Strassenbau" :
                return CardType.STREET_BUILD;
            case "Monopol" :
                return CardType.MONOPOLY;
            case "Erfindung" :
                return CardType.INVENTION;
            case "Siegpunkt" :
                return CardType.VICTORY_POINT;
            default:
                return null;
        }
    }

    public static Card getCardFromString(String a) {
        switch (a) {
            case "Ritter" :
                return new Card(CardType.KNIGHT);
            case "Straßenbau" :
                return new Card(CardType.STREET_BUILD);
            case "Monopol" :
                return new Card(CardType.MONOPOLY);
            case "Erfindung" :
                return new Card(CardType.INVENTION;
            case "Siegpunkt" :
                return new Card(CardType.VICTORY_POINT);

        }
    }
}
