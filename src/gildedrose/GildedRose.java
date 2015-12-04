package gildedrose;

import java.util.ArrayList;
import java.util.List;

public class GildedRose {

	public static final int UPPER_LIMIT_QUALITY = 50;
	public static final int  = 0;
	public static final int LOWER_LIMIT_QUALITY = 0;
	public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
	public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
	public static final String AGED_BRIE = "Aged Brie";
	public static final int UPPER_LIMIT_SELLING = 11;
	public static final int LOWER_LIMMIT_SELLING = 6;
	private static List<Item> items = null;

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("OMGHAI!");

		items = new ArrayList<Item>();
		items.add(new Item("+5 Dexterity Vest", 10, 20));
		items.add(new Item(AGED_BRIE, 2, 0));
		items.add(new Item("Elixir of the Mongoose", 5, 7));
		items.add(new Item(SULFURAS, 0, 80));
		items.add(new Item(BACKSTAGE, 15, 20));
		items.add(new Item("Conjured Mana Cake", 3, 6));

		updateQuality();
	}

	public static void updateQuality() {


		for (int item = 0; item < items.size(); item++) {

			if ((!nameSameAsAGEDBRIEOf(item)) && !nameSameAsBACKSTAGEOf(item)) {
				if (qualityOf(item) > LOWER_LIMIT_QUALITY && nameSameAsSULFURASOf(item)) {
						reduceCurrentQualityOf(item);
				}
			}
			else{
				if (qualityOf(item) < UPPER_LIMIT_QUALITY) {
					increaseQualityOf(item);

					if (nameSameAsBACKSTAGEOf(item)) {
						if (sellinOf(item) < UPPER_LIMIT_SELLING && qualityOf(item) < UPPER_LIMIT_QUALITY) {
							increaseQualityOf(item);
						}
						if (sellinOf(item) < LOWER_LIMMIT_SELLING && qualityOf(item) < UPPER_LIMIT_QUALITY) {
								increaseQualityOf(item);
						}
					}
				}
			}

			if (!nameSameAsSULFURASOf(item)) {
				reduceCurrentItemSellin(item);
			}
			if (sellinOf(item) < 0) {
				if (!nameSameAsAGEDBRIEOf(item)) {
					if (!nameSameAsBACKSTAGEOf(item) && qualityOf(item) > LOWER_LIMIT_QUALITY && !nameSameAsSULFURASOf(item)) {
								reduceCurrentQualityOf(item);
					}
					else {
						setZeroQualityOf(item);
					}
				}
				else if (qualityOf(item) < UPPER_LIMIT_QUALITY) {
						increaseQualityOf(item);
				}
			}
		}
	}

	private static double sellinOf(int i) {
		return items.get(i).getSellIn();
	}

	private static void setZeroQualityOf(int i) {
		items.get(i).setQuality(0);
	}

	private static void reduceCurrentItemSellin(int i) {
		items.get(i).setSellIn(items.get(i).getSellIn() - 1);
	}

	private static void increaseQualityOf(int i) {
		items.get(i).setQuality(items.get(i).getQuality() + 1);
	}

	private static double qualityOf(int i) {
		return items.get(i).getQuality();
	}

	private static boolean nameSameAsBACKSTAGEOf(int i) {
		return BACKSTAGE.equals(items.get(i).getName());
	}

	private static boolean nameSameAsAGEDBRIEOf(int i) {
		return AGED_BRIE.equals(items.get(i).getName());
	}

	private static boolean nameSameAsSULFURASOf(int i) {
		return !SULFURAS.equals(getNameOfItem(i));
	}

	private static Object getNameOfItem(int i) {
		return items.get(i).getName();
	}

	private static void reduceCurrentQualityOf(int i) {
		items.get(i).setQuality(qualityReduceForOne(i));
	}

	private static Object qualityReduceForOne(int i) {
		return items.get(i).getQuality() - 1;
	}

	/*
	 * minimal addition to ensure testability
	 */
	public static void setItems(List<Item> items) {
		GildedRose.items = items;
	}
}