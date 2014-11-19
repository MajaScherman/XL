package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import model.MainSheet;

public class SlotLabel extends ColoredLabel implements ActionListener, Observer {
	private char col;
	private int row;
	private CurrentAddress curr;
	
    public SlotLabel(char col, int row, CurrentAddress curr) {
        super("                    ", Color.WHITE, RIGHT);
        this.col = col;
        this.row = row;
    }

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//TODO kalla på att currentLabel i mainsheet ska uppdateras
		curr.setCurrent(col + "" + row);
	}

	@Override
	public void update(Observable observer, Object obj) {
		//TODO hämta labelns innehåll från model
		if (curr.getCurrent().equals(col + "" + row)) {
			setBackground(Color.YELLOW);
		} else {
			setBackground(Color.WHITE);
		}
		//inte helt s�ker p� om observer faktiskt kommer vara en MainSheet..? kan n�n kolla upp det
		//TODO byta ut denna instanceof om det finns n�t b�ttre
		MainSheet sheet;
		if (observer instanceof MainSheet) {
			sheet = (MainSheet) observer;
			String text = sheet.getSlotText(col + "" + row);
			Adjustment adj = new Adjustment(20); //Antog att bredden p� varje SlotLabel skulle vara 20, d� denna klassens konstruktor skapas med 20 blanksteg.
			//tror inte denna ska centreras: tror att siffror ska alignas till v�nster och kommentarer till h�ger.
			//funderar d�rf�r p� att l�gga till tv� metoder i MainSheet: isExpr(String address) och isComment(String address).
			setText(adj.center(text));
		}
	}
    
}