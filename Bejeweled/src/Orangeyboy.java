import java.awt.Color;
import java.awt.Image;

public class Orangeyboy extends Jewel {
	static Image orangeImage;
	// I found the locations below by a little bit of guess and 
	// check to find a rectangle that bounded the image of the 
	// Emerald.
	static final int EM_X=1455, EM_Y=425, EM_W = 120, EM_H=100;
	
	public Orangeyboy(int r, int co) {
		super(Color.ORANGE, getImage(), r, co);
	}

	private static Image getImage() {
		if(orangeImage == null)
			orangeImage= openImageFromSpriteSheet(EM_X, EM_Y, EM_W, EM_H);
		return orangeImage;
	}

}
