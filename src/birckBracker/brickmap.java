package birckBracker;

import java.awt.*;

public class brickmap {

	public int map[][];
	public int width;
	public int height;
	
	public brickmap(int row, int col){
		map = new int[row][col];
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++){
				map[i][j] = 1;
			}
		}
		width = 540/col;
		height = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0; i<map.length; i++) {
			for(int j=0; j<map[i].length; j++){
				if(map[i][j] > 0) {
					g.setColor(Color.gray);
					g.fillRect(j*width+80,i*height+50,width,height);
					
					g.setStroke(new BasicStroke(3));
					g.setColor(Color.white);
					g.drawRect(j*width+80,i*height+50,width,height);
				}
			}
		}
	}
	
	public void setbrickvalue(int v,int r, int c) {
		map[r][c] = v;
	}
}
