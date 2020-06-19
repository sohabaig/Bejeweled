import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BejeweledGrid {
	/**
	 * The 2D array of jewels
	 */


	private Jewel[][] jewels;
	/** Offsets for drawing the grid of Jewels*/
	public static final int OFFSET_X = 40, OFFSET_Y = 20; 

	Jewel selected = null;

	public BejeweledGrid() {
		// need something here!!

		jewels = new Jewel[10][10];
		for(int r=0; r<jewels.length;r++) {
			for(int c=0; c<jewels[r].length;c++) {
				jewels[r][c]=randomJewel(r,c);

			}
		}


	}

	/** directs each Jewel to draw itself.  The Jewel knows its row
	 * and column and can ask the grid for the offset info
	 * @param g Graphics context onto which the Jewels will draw themselves
	 */
	public void draw(Graphics g) {

		for(int r=0; r<jewels.length;r++) {
			for(int c=0; c<jewels.length;c++) {
				if(jewels[r][c]!=null) {
					jewels[r][c].draw(g);
				}
			}
		}

	}

	/** swaps the Jewels at the specified locations.  Also must
	 * tell the Jewels to set their rows and cols appropriately.
	 * @param r1 row of Jewel 1
	 * @param c1 col of Jewel 1
	 * @param r2 row of Jewel 2
	 * @param c2 col of Jewel 2
	 */
	public void swap(int r1, int c1, int r2, int c2) {
		Jewel temp = jewels[r1][c1];
		jewels[r1][c1]=jewels[r2][c2];
		jewels[r2][c2]= temp;

		jewels[r1][c1].moveTo(r2,  c2);
		jewels[r2][c2].moveTo(r1, c1);



		// more of course tell the Jewels they are changing locations
	}

	/**
	 * Creates an ArrayList<Jewel> of all Jewels that are part of a
	 * three-in-a-row.  The same Jewel may appear in the List more than
	 * once.
	 * @return
	 */
	public ArrayList<Jewel> _3InARow() {
		ArrayList<Jewel> list = new ArrayList<>();
		list.addAll(_3InARowVert());
		list.addAll(_3InARowHor());
		return list;
	}

	/**
	 * 
	 * @return returns an ArrayList of Jewels that make up three or more in 
	 *         a row vertically or an empty list if fewer than 3 in a row exist
	 */
	private ArrayList<? extends Jewel> _3InARowVert() {

		ArrayList<Jewel> vert = new ArrayList<>();

		for(int c=0; c<jewels.length; c++) {
			for(int r=0; r<jewels[c].length-2; r++) {
				if(jewels[r][c].equals(jewels[r+1][c])&&jewels[r+1][c].equals(jewels[r+2][c])) {
					vert.add(jewels[r][c]);
					vert.add(jewels[r+1][c]);
					vert.add(jewels[r+2][c]);
				}
			}
		}

		return vert;
	}
	/**  
	 * @return returns an ArrayList of Jewels that make up three or more in 
	 *         a row horizontally or an empty list if fewer than 3 in a row exist
	 */
	private ArrayList<Jewel> _3InARowHor() {

		ArrayList<Jewel>horiz = new ArrayList<>();
		for(int r=0; r<jewels.length; r++) {
			for(int c=0; c<jewels[r].length-2; c++) {
				if(jewels[r][c].equals(jewels[r][c+1])&&jewels[r][c+1].equals(jewels[r][c+2])) {
					horiz.add(jewels[r][c]);
					horiz.add(jewels[r][c+1]);
					horiz.add(jewels[r][c+2]);
				}
			}
		}

		return horiz;
	}
	/**
	 * This method drops all Jewels that should drop...
	 * Basically, any Jewel that has no Jewel below it needs to 
	 * be in a higher row.  Lots of ways to approach this.
	 */
	public void drop() {

		ArrayList<Jewel> list= new ArrayList<>();


		for(int c=0; c<jewels.length;c++) {
			for(int r=0; r<jewels[c].length;r++) {
				if(jewels[r][c]!=null) {
					list.add(jewels[r][c]);
					jewels[r][c]=null;	

				}
			}
			

		}

	}

	/**
	 * Fill in any empty positions in the grid with randomly selected Jewel
	 */
	public void refill() {

		for(int r=0; r<jewels.length;r++) {
			for(int c=0; c<jewels[r].length;c++) {

				if(jewels[r][c]==null) {
					jewels[r][c]=randomJewel(r,c);


				}
			}
		}



	}

	/**
	 * Generates a random Jewel that will have its starting location
	 * at the specified row and col.
	 * @param r row where the Jewel will be placed
	 * @param c col where the Jewel will be placed
	 * @return random type of Jewel constructed at r,c
	 */
	private Jewel randomJewel(int r, int c) {

		int rand = (int)(Math.random()*5)+1;
		if(rand==1)
			jewels[r][c]=new Emerald(r,c);
		if(rand==2)
			jewels[r][c]=new Ruby(r,c);
		if(rand==3)
			jewels[r][c]=new Triangles(r,c);
		if(rand==4)
			jewels[r][c]=new Diamond(r,c);
		if(rand==5)
			jewels[r][c]=new Orangeyboy(r,c);

		return jewels[r][c];
	}
	/**
	 * This method is called by the game when a click has been made 
	 * on the panel.  Must determine if the click makes sense (is it 
	 * a valid location, is there a Jewel at the location, can that 
	 * Jewel be clicked).  If it is the first click, then note it and
	 * the next click will attempt a move (maybe).
	 * @param me
	 */
	public void justClicked(MouseEvent me) {


		int x = me.getX();
		int y = me.getY();
		int c=-1, r=-1;
		if(selected == null) {	
			//first click

			if(x>OFFSET_X&&x<OFFSET_X+jewels[0].length*Jewel.SQUARE_SIZE) {
				r=(x-OFFSET_X)/Jewel.SQUARE_SIZE;
				//gets row
			}
			else
				System.out.println("Please choose another valid spot!");

			if(y>OFFSET_Y&&x<OFFSET_X+jewels.length*Jewel.SQUARE_SIZE) {
				c=(y-OFFSET_Y)/Jewel.SQUARE_SIZE;
				//gets col
			}
			else
				System.out.println("Please choose another valid spot!");
			if(r>=0&&c>=0) {
				selected=jewels[r][c];
				System.out.println("First click!");
				//sets selected
			}


		}


		else {
			//second click
			Jewel second;
			if(x>OFFSET_X&&x<OFFSET_X+jewels[0].length*Jewel.SQUARE_SIZE) {
				r=(x-OFFSET_X)/Jewel.SQUARE_SIZE;
				//gets row
			}
			else
				System.out.println("Please choose another valid spot!");

			if(y>OFFSET_Y&&x<OFFSET_X+jewels.length*Jewel.SQUARE_SIZE) {
				c=(y-OFFSET_Y)/Jewel.SQUARE_SIZE;
				//gets col
			}
			else
				System.out.println("Please choose another valid spot!");
			if(r>=0&&c>=0) {
				second=jewels[r][c];
				System.out.println("Second click!");
				//sets selected
				if(second.nextTo(selected)==true) {
					swap(second.getRow(), second.getCol(), selected.getRow(), selected.getCol());
					ArrayList<Jewel>list = _3InARow();
					if(list.size()>0) {
						for(int q=0;q<list.size();q++) {
							int row= list.get(q).getRow();
							int col = list.get(q).getCol();
							if(jewels[row][col]!=null)
								jewels[row][col]=null;
						}
					}

					drop();
					refill();


				}

			}

			selected = null;
		}



	}

}




