package work_scheduling;
import javax.swing.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

public class WorkTable {
	private static Vector<String[]> linevector = new Vector<>();
	private static JPanel panel = new JPanel(new GridBagLayout());
	WorkTable(Vector<String[]> info){
		String[] head = {"", "MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};
		this.linevector.add(head);
		for(String[] i : info)
			this.linevector.add(i);
	}
	private static void makeTable() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		int row = 0, col = 0;
		for(String[] line : linevector) {
			col = 0;
			for(String item : line) {
				JLabel tmp = new JLabel(item);
				gbc.gridx = col;
				gbc.gridy = row;
				col++;
				panel.add(tmp, gbc);
			}
			row++;
		}
		//WorkTable(info) 해서 오면 이걸 패널로 만들어야함
	}
	public static JPanel showTable() {
		makeTable();
		return panel;
	}
}
