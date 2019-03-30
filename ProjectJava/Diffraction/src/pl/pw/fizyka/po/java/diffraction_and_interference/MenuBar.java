package pl.pw.fizyka.po.java.diffraction_and_interference;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MenuBar extends JMenuBar 
{

	public MenuBar() 
	{
		menu1 = new JMenu("Plik");
		menu2 = new JMenu("Pomoc");
		subMenu2 = new JMenu("Item jakis");
		 
        JMenuItem menu1Item1 = new JMenuItem("Zamknij");
		JMenuItem menu1Item2 = new JMenuItem("Nowy");
		JMenuItem menu1Item3 = new JMenuItem("Zapisz");
		JMenuItem menu1Item4 = new JMenuItem("Otworz plik...");
		
		JMenuItem menu2Item1 = new JMenuItem("Jak uzywac aplikacji");
		JMenuItem menu2Item2 = new JMenuItem("Dowiedz sie wiecej");
		
		JMenuItem submenu2Item1 = new JMenuItem("Cos tu bedzie");
		
        this.add(menu1);
        this.add(menu2);
        menu2.add(subMenu2);
        
        
       
		menu1.add(menu1Item1);
		menu1Item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
		
		menu1Item1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				
			}
		});
		
		
		menu2Item1.addActionListener(new ActionListener() {
		
		      public void actionPerformed(ActionEvent e) {
		         subMenu2.setText("Kliknieto");
		      }
		   });	
		
		menu1.add(menu1Item2);
		menu1.add(menu1Item3);
		menu1.add(menu1Item4);
		
		menu2.add(menu2Item1);
		menu2.add(menu2Item2);
		subMenu2.add(submenu2Item1);
		
		
		
		
	}
	JMenu menu1;
	JMenu menu2;
	JMenu subMenu2;
	JMenuItem menu1Item1;
	JMenuItem menu1Item2;
	JMenuItem menu1Item3;
	JMenuItem menu1Item4;
	JMenuItem menu2Item1;
	JMenuItem menu2Item2;
	JMenuItem submenu2Item1;
	
}
