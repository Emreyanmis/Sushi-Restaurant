package sushigame.view;

import java.util.Comparator;

import sushigame.model.Chef;

public class BalanceComparator implements Comparator<Chef> {
	@Override
	public int compare(Chef x, Chef y) {
		return (int) (Math.round(y.getBalance() * 100.0) -  Math.round(x.getBalance() * 100));
	}			
}
