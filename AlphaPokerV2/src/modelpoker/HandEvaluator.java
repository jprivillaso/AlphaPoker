package modelpoker;

import java.util.*;


/**
 * Class that can calculate a numerical value for a hand of playing cards.
 */

public class HandEvaluator {

    /**
     * Public constructor for the HandEvaluator.
     */
    HandEvaluator(){ }

    /**
     * Returns the value of the current hand.
     * @return the value of the current hand.
     */
    public long getHandValue(ArrayList fullHand)
    {
        this.fullHand = fullHand;
        fullHandSortedByPairs = pairSort(fullHand);
        fullHandSortedByValue = valueSort(fullHand);
        fullHandSortedByValueNoSame = valueNoSameSort(fullHand);
        majorSuit = findMajorSuit();

        long value;
        String hValue = "1";

        value = getRoyalFlush();
        if(value != -1)
            return value;

        value = getStraightFlush();
        if(value != -1)
            return value;

        value = get4Kind();
        if(value != -1)
            return value;

        value = getFullHouse();
        if(value != -1)
            return value;

        value = getFlush();
        if(value != -1)
            return value;

        value = getStraight();
        if(value != -1)
            return value;

        value = get3Kind();
        if(value != -1)
            return value;

        value = get2Pair();
        if(value != -1)
            return value;

        value = getPair();
        if(value != -1)
            return value;

        for(int i = 0; i < 5; i++)
        {
            hValue += numberFormat(((Card)fullHandSortedByValue.get(i)).getValue());
        }

        return Long.parseLong(hValue);
    }

    //works
    private long getRoyalFlush()
    {
        ArrayList best5Hand = new ArrayList();
        long handValue;
        if(majorSuit.equals(""))
            return -1;

        for(int i = 14; i > 9; i--)
        {
            for(int k = 0; k < 7; k++)
            {
                if (((Card)fullHand.get(k)).getValue() == i &&
                		((Card)fullHand.get(k)).getSuit().equals(majorSuit))
                {
                    best5Hand.add(fullHand.get(k));
                    break;
                }
            }
        }

        if(best5Hand.size() == 5)
            return 101413121110L;
        else return -1;
    }

    //works
    private long getStraightFlush()
    {
        ArrayList flushHand = new ArrayList();
        boolean isStraight = false;

        String hValue = "9";

        if(majorSuit.equals("") || fullHandSortedByValueNoSame.size() < 5)
            return -1;
        else
        {
            for(int i = 0; i < 7; i++)
            {
                if(((Card)fullHandSortedByValue.get(i)).getSuit().equals(majorSuit))
                {
                    flushHand.add(fullHandSortedByValue.get(i));
                }
            }

            /*****************************************************************************************/
            if(((Card)flushHand.get(0)).getValue() == 14)
            {
                 String icon = "images/backCard1.png";

                 flushHand.add(new Card(
                        0, 1, "AceLow", icon, true));
            }

            for(int i = 0; i < (flushHand.size() - 4); i++)
            {
                if( ((Card)flushHand.get(0 + i)).getValue() -
                		(((Card)flushHand.get(4 + i)).getValue()) == 4)
                {
                    isStraight = true;
                    for(int k = 0; k < 5; k++)
                    {
                        if( ((Card)flushHand.get(k + i)).getValue() == 1)
                        {
                            hValue += "14";
                        }
                        else
                        {
                            hValue += numberFormat(((Card)flushHand.get(k + i)).getValue());
                        }
                    }
                    break;
                }
            }

            if(isStraight)
                return Long.parseLong(hValue);
            else return -1;
        }
    }

    //works
    private long get4Kind()
    {
        String hValue = "8";
        int pairValue;
        if(fullHandSortedByPairs.size() > 3)
        {
            if(((Card)fullHandSortedByPairs.get(0)).getValue() ==
            	((Card)fullHandSortedByPairs.get(1)).getValue() &&
            	((Card)fullHandSortedByPairs.get(0)).getValue() ==
            	((Card)fullHandSortedByPairs.get(2)).getValue() &&
            	((Card)fullHandSortedByPairs.get(0)).getValue() ==
            	((Card)fullHandSortedByPairs.get(3)).getValue())

              {
            pairValue = ((Card)fullHandSortedByPairs.get(0)).getValue();
            hValue += numberFormat(pairValue) + numberFormat(pairValue) +
                    numberFormat(pairValue) + numberFormat(pairValue);

            for(int i = 0; i < 7; i++)
            {
                if(((Card)fullHandSortedByValue.get(i)).getValue() != pairValue)
                {
                     hValue += numberFormat(
                    		 ((Card)fullHandSortedByValue.get(i)).getValue());
                     break;
                }
            }

            return Long.parseLong(hValue);
        } else return -1;
        } else return -1;

    }

    //works
    private long getFullHouse()
    {
        String hValue = "7";
        int pair1Value;
        int pair2Value;
        if(fullHandSortedByPairs.size() > 4)
        {
        if(((Card)fullHandSortedByPairs.get(0)).getValue() ==
        	((Card)fullHandSortedByPairs.get(1)).getValue() &&
        	((Card)fullHandSortedByPairs.get(0)).getValue() ==
        	((Card)fullHandSortedByPairs.get(2)).getValue() &&
        	((Card)fullHandSortedByPairs.get(3)).getValue() ==
        	((Card)fullHandSortedByPairs.get(4)).getValue())
        {
            pair1Value = ((Card)fullHandSortedByPairs.get(0)).getValue();
            pair2Value = ((Card)fullHandSortedByPairs.get(3)).getValue();

            hValue += numberFormat(pair1Value) + numberFormat(pair1Value) +
                    numberFormat(pair1Value) + numberFormat(pair2Value) +
                    numberFormat(pair2Value);

            return Long.parseLong(hValue);
        }
        else return -1;
        } else return -1;
    }

    //works
    private long getFlush()
    {
        ArrayList best5Hand = new ArrayList();
        int count = 0;
        String hValue = "6";

        if(majorSuit.equals(""))
            return -1;
        else
        {
            for(int i = 0; i < 7; i++)
            {
                if(((Card)fullHandSortedByValue.get(i)).getSuit().equals(majorSuit))
                {
                    best5Hand.add(fullHandSortedByValue.get(i));
                    count++;
                }

                if(count == 5)
                    break;
            }

            for(int k = 0; k < 5; k++)
            {
                hValue += numberFormat(
                		((Card)best5Hand.get(k)).getValue());
            }

            return Long.parseLong(hValue);
        }
    }

    //works
    private long getStraight()
    {
        String hValue = "5";
        boolean isStraight = false;
        ArrayList straightHand =
                new ArrayList(fullHandSortedByValueNoSame.size());
        straightHand.addAll(fullHandSortedByValueNoSame);

        if(straightHand.size() < 5)
            return -1;

        /*****************************************************************************************/
        if(((Card)straightHand.get(0)).getValue() == 14)
        {
             String icon = "images/backCard1.png";
             straightHand.add(new Card(
                     0, 1, "AceLow", icon, true));
        }

        for(int i = 0; i < (straightHand.size() - 4); i++)
        {
            if((((Card)straightHand.get(0 + i)).getValue() -
            		((Card)straightHand.get(4 + i)).getValue()) == 4)
            {
                isStraight = true;
                for(int k = 0; k < 5; k++)
                {
                    if(((Card)straightHand.get(k + i)).getValue() == 1)
                    {
                        hValue += "14";
                    }
                    else
                    {
                    hValue += numberFormat(((Card)straightHand.get(k + i)).getValue());
                    }
                }
                break;
            }
        }

        if(isStraight)
            return Long.parseLong(hValue);
        else return -1;
    }

    //works
    private long get3Kind()
    {
        String hValue = "4";
        int count = 0;
        int index = 0;
        int pairValue;

        if(fullHandSortedByPairs.size() > 2)
        {
        if(((Card)fullHandSortedByPairs.get(0)).getValue() ==
        	((Card)fullHandSortedByPairs.get(1)).getValue() &&
        	((Card)fullHandSortedByPairs.get(0)).getValue() ==
        	((Card)fullHandSortedByPairs.get(2)).getValue())
        {
            pairValue = ((Card)fullHandSortedByPairs.get(0)).getValue();
            hValue += numberFormat(pairValue) + numberFormat(pairValue) +
                    numberFormat(pairValue);

            while(count < 2)
            {
                if(((Card)fullHandSortedByValue.get(index)).getValue() != pairValue)
                {
                    hValue += numberFormat(
                    		((Card)fullHandSortedByValue.get(index)).getValue());
                    count++;
                }
                index++;
            }

            return Long.parseLong(hValue);
        }
        else return -1;
        } else return -1;
    }

    //works
    private long get2Pair()
    {
        String hValue = "3";
        int pair1Value;
        int pair2Value;
        int pairValueSwap;

        if(fullHandSortedByPairs.size() > 3)
        {

        if(((Card)fullHandSortedByPairs.get(0)).getValue() ==
        	((Card)fullHandSortedByPairs.get(1)).getValue() &&
        	((Card)fullHandSortedByPairs.get(2)).getValue() ==
        	((Card)fullHandSortedByPairs.get(3)).getValue())
        {

            pair1Value = ((Card)fullHandSortedByPairs.get(0)).getValue();
            pair2Value = ((Card)fullHandSortedByPairs.get(2)).getValue();

            if(pair1Value < pair2Value)
            {
                pairValueSwap = pair2Value;
                pair2Value = pair1Value;
                pair1Value = pairValueSwap;
            }

            hValue += numberFormat(pair1Value) + numberFormat(pair1Value) +
                    numberFormat(pair2Value) + numberFormat(pair2Value);

            for(int i = 0; i < 7; i++)
            {
                if((((Card)fullHandSortedByValue.get(i)).getValue() != pair1Value) &&
                        (((Card)fullHandSortedByValue.get(i)).getValue() != pair2Value))
                {
                     hValue += numberFormat(
                    		 ((Card)fullHandSortedByValue.get(i)).getValue());
                     break;
                }
            }

            return Long.parseLong(hValue);
        }
        else return -1;
        } else return -1;
    }

    //works
    private long getPair()
    {
        String hValue = "2";
        int count = 0;
        int index = 0;
        int pairValue;

        if(fullHandSortedByPairs.size() > 1)
        {
        if(((Card)fullHandSortedByPairs.get(0)).getValue() ==
        	((Card)fullHandSortedByPairs.get(1)).getValue())
        {
            pairValue = ((Card)fullHandSortedByPairs.get(0)).getValue();
            hValue += numberFormat(pairValue) + numberFormat(pairValue);

            while(count < 3)
            {
                if(((Card)fullHandSortedByValue.get(index)).getValue() != pairValue)
                {
                    hValue += numberFormat(
                    		((Card)fullHandSortedByValue.get(index)).getValue());
                    count++;
                }
                index++;
            }

            return Long.parseLong(hValue);
        }
        else return -1;
        } else return -1;
    }

    //Returns the name of the suit if there are 5 or more
    //of it and returns null if there is not.
    private String findMajorSuit()
    {
        int count = 0;
        String[] suits = {"Club", "Spade", "Heart", "Diamond"};
        for(int j = 0; j < suits.length; j++)
        {
        	String s = suits[j];
        	
            for(int i = 0; i < 7; i++)
            {
                if(((Card)fullHand.get(i)).getSuit().equals(s))
                    count++;
            }

            if(count > 4)
                return s;
            else count = 0;
        }
        return "";
    }

    //Sorts from high to low
    private ArrayList valueSort(ArrayList hand)
    {
        ArrayList handCopy = new ArrayList(hand.size());
        handCopy.addAll(hand);
        int highValue = 0;
        int highCardIndex = -1;
        boolean duplicate = false;
        ArrayList sorted = new ArrayList();
        while(!handCopy.isEmpty())
        {
            for(int i = 0; i < handCopy.size(); i++)
            {
                if(((Card)handCopy.get(i)).getValue() > highValue)
                {
                    highValue = ((Card)handCopy.get(i)).getValue();
                    highCardIndex = i;
                }
            }

            sorted.add(handCopy.remove(highCardIndex));
            
            highValue = 0;
            highCardIndex = -1;
        }
        return sorted;
    }

    //Sorts from high to low removing multiples
    private ArrayList valueNoSameSort(ArrayList hand)
    {
        ArrayList handCopy = new ArrayList(hand.size());
        handCopy.addAll(hand);
        int highValue = 0;
        int highCardIndex = -1;
        boolean duplicate = false;
        ArrayList sorted = new ArrayList();
        while(!handCopy.isEmpty())
        {
            for(int i = 0; i < handCopy.size(); i++)
            {
                if(((Card)handCopy.get(i)).getValue() > highValue)
                {
                    highValue = ((Card)handCopy.get(i)).getValue();
                    highCardIndex = i;
                }
            }

            if(sorted.isEmpty())
            {
                sorted.add(handCopy.remove(highCardIndex));
            }
            else
            {
                for(int k = 0; k < sorted.size(); k++)
                {
                    if(((Card)handCopy.get(highCardIndex)).getValue() == ((Card)sorted.get(k)).getValue())
                        duplicate = true;
                }

                if(duplicate)
                {
                    Card c = ((Card)handCopy.remove(highCardIndex));
                }
                else
                {
                    sorted.add(handCopy.remove(highCardIndex));
                }
            }
            highValue = 0;
            highCardIndex = -1;
        }
        return sorted;
    }

    //Sorts from greatest pairs to greatest value to high card to low card
    //ex. three of kind first then a pair then the last two cards from high to low
    //ex. higher pair then other pair then the last three cards from high to low
    private ArrayList pairSort(ArrayList hand)
    {
        ArrayList handCopy = new ArrayList(hand.size());
        handCopy.addAll(hand);
        ArrayList hvs = valueSort(handCopy);//handValueSort
        ArrayList pc = new ArrayList();//pairCards *** integer
        ArrayList hps = new ArrayList(hand.size());//handPairSort

        for (int i = 0; i < hvs.size(); i++)
        {
            if(hvs.size() - i > 1)
            {
                if(((Card)hvs.get(i)).getValue() == ((Card)hvs.get(i+1)).getValue())
                {
                    if(hvs.size() - i > 2)
                    {
                        if(((Card)hvs.get(i)).getValue() == ((Card)hvs.get(i+2)).getValue())
                        {
                            if(hvs.size() - i > 3)
                            {
                                if(((Card)hvs.get(i)).getValue() == ((Card)hvs.get(i+3)).getValue())
                                {
                                    pc.add(new Integer(i));
                                    pc.add(new Integer(i+1));
                                    pc.add(new Integer(i+2));
                                    pc.add(new Integer(i+3));
                                }
                                else
                                {
                                    pc.add(new Integer(i));
                                    pc.add(new Integer(i+1));
                                    pc.add(new Integer(i+2));
                                }
                            }
                            else
                            {
                                pc.add(new Integer(i));
                                pc.add(new Integer(i+1));
                                pc.add(new Integer(i+2));
                            }
                        }
                        else
                        {
                            pc.add(new Integer(i));
                            pc.add(new Integer(i+1));
                        }
                    }
                    else
                    {
                        pc.add(new Integer(i));
                        pc.add(new Integer(i+1));
                    }
                }
            }
        }//end for

       // Card c;
       // boolean bool = false;

        for(int k = 0; k < pc.size(); k++)
        {
            for(int j = 0; j < pc.size(); j++)
            {
                if (k != j && pc.get(k) == pc.get(j))
                {
                    pc.set(j,new Integer(-1));
                }
            }
        }

        for(int x = 0; x < pc.size(); x++)
        {
            if(((Integer)pc.get(x)).intValue() != -1)
                hps.add(((Card)hvs.get(((Integer)pc.get(x)).intValue())));
        }

//        for(int j = 0; j < hps.size(); j++)
//        {
//            c = hps.get(j);
//            for(int q = 0; q < hps.size(); q++)
//            {
//                if(c.getValue() == hps.get(q).getValue() &&
//                        c.getSuit().equals(hps.get(q).getSuit()) && q != j)
//                {
//                    bool = true;
//                }
//            }
//
//            if(!bool)
//            {
//                hps.add(c);
//            }
//            else
//            {
//                bool = false;
//            }
//        }

        return hps;
    }

    //Makes all numbers double digit. ex. 4 -> 04, 11 -> 11
    private String numberFormat(int x)
    {
        if(x > 9)
        {
            return Integer.toString(x);
        }
        else
        {
            return "0" + Integer.toString(x);
        }
    }    
    
    private String majorSuit;
    private ArrayList fullHand;
    private ArrayList fullHandSortedByPairs;
    private ArrayList fullHandSortedByValue;
    private ArrayList fullHandSortedByValueNoSame;
    
}
