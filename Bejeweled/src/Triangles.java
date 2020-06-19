import java.awt.Color;
import java.awt.Image;

public class Triangles extends Jewel {
	static Image triangImage;
	// I found the locations below by a little bit of guess and 
	// check to find a rectangle that bounded the image of the 
	// Emerald.
	static final int EM_X=940, EM_Y=770, EM_W = 120, EM_H=100;
	
	public Triangles(int r, int co) {
		super(Color.MAGENTA, getImage(), r, co);
	}

	private static Image getImage() {
		if(triangImage == null)
			triangImage= openImageFromSpriteSheet(EM_X, EM_Y, EM_W, EM_H);
		return triangImage;
	}

}
