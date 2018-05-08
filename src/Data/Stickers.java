package Data;

import java.util.ArrayList;

public class Stickers {
//	private static int STICKERAMOUNT = 10;
//	Integer[] stickers = new Integer[STICKERAMOUNT];
//	public Stickers() {
//		for (int i = 0; i < STICKERAMOUNT; i++) {
//			this.stickers[i] = 0;
//		}
//	}
//	
//	public void add(int stickerID) {
//		this.stickers[stickerID]++;
//	}
//	
//	public void remove(int stickerID) {
//		if (stickers[stickerID] > 0) {
//			this.stickers[stickerID]--;
//		} else {
//			System.out.println("Stock of sticker No. " + stickerID +  " is already empty.");
//		}
//	}
//	
//	public Integer[] getStickers() {
//		return stickers;
//	}
	
	private ArrayList<Sticker> stickers = new ArrayList<>();
	private int stickersCount = 0;
	private static int stickersMax = 10;
	
	public Stickers() {
		
	}
	
	public void add(Sticker s) {
		stickers.add(s);
		stickersCount++;
	}
	
	public void remove(Sticker s) {
		if (stickers.contains(s)) {
			stickers.remove(s);
			stickersCount--;
		} else {
			System.out.println("Sticker was not found");
		}
	}
	
	public int getStickerCount() {
		return stickersCount;
	}
	
	public int getStickerMax()	{
		return stickersMax;
	}
	
	public ArrayList<Sticker> getStickers() {
		return stickers;
	}
}
