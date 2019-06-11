import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;

@SuppressWarnings("serial")
class ImagePanel extends JPanel{
	private Image img;
	
	public ImagePanel(Image img){
		this.img = img;
		setPreferredSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setSize(new Dimension(img.getWidth(null),img.getHeight(null)));
		setLayout(null);
	}
	
	public ImagePanel(ImageIcon imageIcon) {
		// TODO Auto-generated constructor stub
	}

	public int getWidth() {
		return img.getWidth(null);
	}
	
	public int getHeight() {
		return img.getHeight(null);
	}
	
	public void paintComponent(Graphics g){
		g.drawImage(img, 0, 0, null);
	}
}

public class Battleapp {

	private JFrame frame;
	private final JLabel lblStart = new JLabel("");
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private ImageIcon[] itemPicArray = new ImageIcon[6];
	private ImageIcon[] NitemPicArray = new ImageIcon[6];
	
	Character attacker;
	Character defender;
	Human myHuman;
	Night myNight;
	
	
	Item sword = new Item("Sword", 20, 10, 15, 10, 15);
	Item horse = new Item("Horse", 30, 20, 15, 10, 30);
	Item dragon = new Item("Dragon", 60, 40, 30, 20, 50);
	Item armor = new Item("Armor", 10, 5, 40, 30, 15);
	Item helmet = new Item("Helmet", 10, 5, 20, 10, 8);
	Item shield = new Item("Shield", 15, 10, 30, 20, 10);
	
	private JTextField txt_hpHuman;
	private JTextField txt_hpNight;
	private JTextArea txtA_HumanResult;
	private JTextArea txtA_game;
	private JTextField txt_HitemResult;
	private JTextField txt_NitemResult;
	private JButton btnGotoMain1;
	private JTextField txtHgold;
	private JTextField txtNgold;
	private JButton btnGetHumanItem;
	private JButton btnGetNightItem;
	private JLabel lbl_A_Human;
	private JLabel lbl_A_Night;
	private final int DELAY = 2000; //ms
	private Timer timer = new Timer(DELAY, new TimerHandler());
	
	/**
	 * Launch the application.                                                                                                                                     
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Battleapp window = new Battleapp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Battleapp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		// set frame
		frame = new JFrame();
		frame.setBounds(100, 100, 682, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		frame.setLocationRelativeTo(null);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		// set image panel
		ImagePanel Start = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image/title1.jpg").getImage());
		frame.getContentPane().add(Start, "name_91415329600598");
		Start.setLayout(null);
		
		ImagePanel Main = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image/battleback.jpg").getImage());
		frame.getContentPane().add(Main, "name_76327738240446");
		frame.pack();
		
		ImagePanel HumanKing = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image//back2.jpg").getImage());
		frame.getContentPane().add(HumanKing, "name_189558063237670");
		HumanKing.setLayout(null);

		ImagePanel NightKing = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image//back2.jpg").getImage());
		frame.getContentPane().add(NightKing, "name_189568530375076");
		NightKing.setLayout(null);	
		
		ImagePanel HumanItem = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image/Itemback2.jpg").getImage());
		frame.getContentPane().add(HumanItem, "name_45655210095049");
		
		ImagePanel NightItem = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image/Itemback2.jpg").getImage());
		frame.getContentPane().add(NightItem, "name_183796841759411");
		NightItem.setLayout(null);
		
		ImagePanel Arena = new ImagePanel(new ImageIcon("C:/BHJ/NSCC/2019/Jan.2019/PROG1400 Introduction to Object Oriented Programming/eclipes/Assign3_GameOfThrone/src/image/arenaback.jpg").getImage());
		frame.getContentPane().add(Arena, "name_45677196792285");
		

		
		// --------------------------- start panel ----------------------------------

		JButton btnGameStart = new JButton("");
		btnGameStart.setIcon(new ImageIcon(Battleapp.class.getResource("image/startbtn.jpg")));
		btnGameStart.setBackground(SystemColor.controlShadow);
		btnGameStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				timer.stop();
				Start.setVisible(false);
				Main.setVisible(true);
			}
		});
		
		btnGameStart.setForeground(Color.RED);
		btnGameStart.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 18));
		btnGameStart.setBounds(260, 481, 341, 72);
		Start.add(btnGameStart);
		lblStart.setBounds(0, 0, 840, 600);
		Start.add(lblStart);
		
		//------------------------------- main panel ------------------------------------
		
		
		JButton btnNight = new JButton("");
		btnNight.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		btnNight.setBounds(547, 39, 211, 293);
		btnNight.setIcon(new ImageIcon(Battleapp.class.getResource("/image/nightkbtn.jpg")));
//		frame.getContentPane().add(btnMonster, "name_94456285084346");
		btnNight.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				NightKing.setVisible(true);
			}
		});
		Main.add(btnNight);
		
		JButton btnHuman = new JButton("");
		btnHuman.setBorder(new LineBorder(new Color(128, 128, 128), 2));
		btnHuman.setBounds(102, 39, 211, 293);
		btnHuman.setIcon(new ImageIcon(Battleapp.class.getResource("/image/humankbtn.jpg")));
		btnHuman.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnHuman.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				HumanKing.setVisible(true);
			}
		});
		Main.setLayout(null);
		Main.add(btnHuman);		
		
		JLabel lblOutput = new JLabel("--- Character ---");
		lblOutput.setHorizontalAlignment(SwingConstants.CENTER);
		lblOutput.setBounds(327, 366, 218, 46);
		lblOutput.setForeground(new Color(255, 222, 173));
		lblOutput.setFont(new Font("Tahoma", Font.BOLD, 24));
		Main.add(lblOutput);
		
		JLabel lblVs = new JLabel("VS");
		lblVs.setBounds(403, 183, 74, 58);
		lblVs.setForeground(new Color(245, 245, 245));
		lblVs.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 40));
		Main.add(lblVs);
		
		txtA_HumanResult = new JTextArea();
		txtA_HumanResult.setBackground(new Color(255, 255, 240));
		txtA_HumanResult.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtA_HumanResult.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		txtA_HumanResult.setBounds(102, 344, 218, 81);
		Main.add(txtA_HumanResult);
		
		JLabel lblChooseCharacter = new JLabel("Click Picture!!");
		lblChooseCharacter.setForeground(new Color(255, 105, 180));
		lblChooseCharacter.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseCharacter.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblChooseCharacter.setBounds(309, 39, 236, 50);
		Main.add(lblChooseCharacter);
		
		JButton btnGotoBattle = new JButton("");
		btnGotoBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.setVisible(false);
				Arena.setVisible(true);
				lbl_A_Human.setIcon(btnHuman.getIcon());
				lbl_A_Night.setIcon(btnNight.getIcon());
	
			}
		});
		btnGotoBattle.setIcon(new ImageIcon(Battleapp.class.getResource("/image/GotoBattle.jpg")));
		btnGotoBattle.setBounds(643, 532, 168, 44);
		Main.add(btnGotoBattle);
		
		
		// ------------------------------- human panel -----------------------------------------------
		
		JLabel lblHeroName = new JLabel("Choose human character");
		lblHeroName.setForeground(Color.DARK_GRAY);
		lblHeroName.setFont(new Font("Courier New", Font.BOLD, 22));
		lblHeroName.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeroName.setBounds(24, 82, 309, 38);
		HumanKing.add(lblHeroName);
		
		JLabel lblHumanPic = new JLabel("");
		lblHumanPic.setBounds(545, 133, 211, 293);
		HumanKing.add(lblHumanPic);
		
		JTextArea txtA_human = new JTextArea();
		txtA_human.setFont(new Font("Ebrima", Font.BOLD, 17));
		txtA_human.setBounds(70, 351, 257, 112);
		HumanKing.add(txtA_human);
		
		JRadioButton rdbtnJS = new JRadioButton(" John Snow");
		buttonGroup.add(rdbtnJS);
		rdbtnJS.setBackground(UIManager.getColor("Button.darkShadow"));
		rdbtnJS.setForeground(Color.WHITE);
		rdbtnJS.setFont(new Font("Courier New", Font.BOLD, 20));
		rdbtnJS.setBounds(68, 138, 259, 53);
		HumanKing.add(rdbtnJS);
		rdbtnJS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				lblHumanPic.setIcon(new ImageIcon(Battleapp.class.getResource("image/JS3.jpg")));
				myHuman = new Human("John Snow", 90, 40, 50);
				txtA_human.setText(myHuman.CharacterInfo());
			}			
		}); //end rdb actionlistener
		
		JRadioButton rdbtnMD = new JRadioButton(" Mother of Dragon");
		buttonGroup.add(rdbtnMD);
		rdbtnMD.setBackground(UIManager.getColor("Button.darkShadow"));
		rdbtnMD.setForeground(Color.WHITE);
		rdbtnMD.setFont(new Font("Courier New", Font.BOLD, 20));
		rdbtnMD.setBounds(68, 191, 259, 53);
		HumanKing.add(rdbtnMD);
		
		JRadioButton rdbtnJM = new JRadioButton(" Jamie");
		rdbtnJM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblHumanPic.setIcon(new ImageIcon(Battleapp.class.getResource("image/JM3.jpg")));
				myHuman = new Human("Jamie", 90, 40, 50);
				txtA_human.setText(myHuman.CharacterInfo());
			}
		});
		
		buttonGroup.add(rdbtnJM);
		rdbtnJM.setForeground(Color.WHITE);
		rdbtnJM.setFont(new Font("Courier New", Font.BOLD, 20));
		rdbtnJM.setBackground(UIManager.getColor("Button.darkShadow"));
		rdbtnJM.setBounds(70, 244, 256, 53);
		HumanKing.add(rdbtnJM);
		
		JButton btmGetItem = new JButton("");
		btmGetItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnHuman.setIcon(lblHumanPic.getIcon());
				String HP = Integer.toString(myHuman.getHealthPoint());
				txt_hpHuman.setText(HP);
				HumanKing.setVisible(false);
				HumanItem.setVisible(true);
			}
		});
		
		btmGetItem.setIcon(new ImageIcon(Battleapp.class.getResource("/image/itembtn.jpg")));
		btmGetItem.setBounds(638, 522, 168, 44);
		HumanKing.add(btmGetItem);

		rdbtnMD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				lblHumanPic.setIcon(new ImageIcon(Battleapp.class.getResource("image/MD3.jpg")));	
				myHuman = new Human("Mother of Dragon", 80, 35, 50);
				txtA_human.setText(myHuman.CharacterInfo());
			}
		}); //end rdb actionlistener
		

		JLabel lblItems = new JLabel("------ Item ------");
		lblItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblItems.setForeground(new Color(255, 222, 173));
		lblItems.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblItems.setBounds(327, 437, 218, 46);
		Main.add(lblItems);
		
		JTextArea txtA_HumanItResult = new JTextArea();
		txtA_HumanItResult.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		txtA_HumanItResult.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtA_HumanItResult.setBackground(new Color(255, 255, 240));
		txtA_HumanItResult.setBounds(102, 432, 218, 81);
		Main.add(txtA_HumanItResult);
		
		JTextArea txtA_NightResult = new JTextArea();
		txtA_NightResult.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		txtA_NightResult.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtA_NightResult.setBackground(new Color(255, 255, 240));
		txtA_NightResult.setBounds(547, 344, 218, 81);
		Main.add(txtA_NightResult);
		
		JTextArea txtA_NightItResult = new JTextArea();
		txtA_NightItResult.setFont(new Font("Franklin Gothic Medium", Font.BOLD, 16));
		txtA_NightItResult.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		txtA_NightItResult.setBackground(new Color(255, 255, 240));
		txtA_NightItResult.setBounds(547, 432, 218, 81);
		Main.add(txtA_NightItResult);
		
		
		// ----------------------------------- human item panel -------------------------------
		
		JLabel lblYourItems = new JLabel("your items");
		lblYourItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourItems.setForeground(new Color(128, 0, 0));
		lblYourItems.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblYourItems.setBounds(44, 136, 162, 38);
		HumanItem.add(lblYourItems);
		
		JToggleButton tgbtnsword = new JToggleButton("");		
		buttonGroup.add(tgbtnsword);
		tgbtnsword.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/sword2.jpg")));
		tgbtnsword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnsword.setBounds(227, 61, 178, 180);
		HumanItem.add(tgbtnsword);
		
		JToggleButton tgbtnhorse = new JToggleButton("");
		buttonGroup.add(tgbtnhorse);
		tgbtnhorse.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/horse2.jpg")));
		tgbtnhorse.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnhorse.setBounds(419, 61, 178, 180);
		HumanItem.add(tgbtnhorse);
		
		JToggleButton tgbtndragon = new JToggleButton("");
		buttonGroup.add(tgbtndragon);
		tgbtndragon.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/dragon2.jpg")));
		tgbtndragon.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtndragon.setBounds(611, 61, 178, 180);
		HumanItem.add(tgbtndragon);
		
		JToggleButton tgbtnshield = new JToggleButton("");
		buttonGroup.add(tgbtnshield);
		tgbtnshield.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/shield2.jpg")));
		tgbtnshield.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnshield.setBounds(611, 256, 178, 180);
		HumanItem.add(tgbtnshield);
		
		JToggleButton tgbtnarmor = new JToggleButton("");
		buttonGroup.add(tgbtnarmor);
		tgbtnarmor.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/armor2.jpg")));
		tgbtnarmor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnarmor.setBounds(227, 256, 178, 180);
		HumanItem.add(tgbtnarmor);
		
		JToggleButton tgbtnhelmet = new JToggleButton("");
		buttonGroup.add(tgbtnhelmet);
		tgbtnhelmet.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/helmet2.jpg")));
		tgbtnhelmet.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnhelmet.setBounds(419, 256, 178, 180);
		HumanItem.add(tgbtnhelmet);
		
		itemPicArray[0] = new ImageIcon(Battleapp.class.getResource("image/items/sword1.jpg"));
		itemPicArray[1] = new ImageIcon(Battleapp.class.getResource("image/items/horse1.jpg"));
		itemPicArray[2] = new ImageIcon(Battleapp.class.getResource("image/items/dragon1.jpg"));
		itemPicArray[3] = new ImageIcon(Battleapp.class.getResource("image/items/armor1.jpg"));
		itemPicArray[4] = new ImageIcon(Battleapp.class.getResource("image/items/helmet1.jpg"));
		itemPicArray[5] = new ImageIcon(Battleapp.class.getResource("image/items/shield1.jpg"));
				
		JLabel lblChooseYourItems = new JLabel("Choose");
		lblChooseYourItems.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseYourItems.setForeground(new Color(128, 0, 0));
		lblChooseYourItems.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		lblChooseYourItems.setBounds(44, 98, 162, 38);
		HumanItem.add(lblChooseYourItems);
		
		JTextArea txtA_Item = new JTextArea();
		txtA_Item.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtA_Item.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txtA_Item.setBounds(127, 448, 444, 90);
		HumanItem.add(txtA_Item);
		
		txt_HitemResult = new JTextField();
		txt_HitemResult.setHorizontalAlignment(SwingConstants.CENTER);
		txt_HitemResult.setForeground(Color.RED);
		txt_HitemResult.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		txt_HitemResult.setBounds(137, 550, 419, 24);
		HumanItem.add(txt_HitemResult);
		txt_HitemResult.setColumns(10);
		
		JList itemList = new JList();
		itemList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (itemList.getSelectedIndex() == 0){
					tgbtnsword.setSelected(true);
					tgbtnsword.setSelectedIcon(itemPicArray[itemList.getSelectedIndex()]);
					myHuman.setItem(sword);}
				else if (itemList.getSelectedIndex() == 1){
					tgbtnhorse.setSelected(true);
					tgbtnhorse.setSelectedIcon(itemPicArray[itemList.getSelectedIndex()]);
					myHuman.setItem(horse);}
				else if (itemList.getSelectedIndex() == 2){
					tgbtndragon.setSelected(true);
					tgbtndragon.setSelectedIcon(itemPicArray[itemList.getSelectedIndex()]);
					myHuman.setItem(dragon);}	
				else if (itemList.getSelectedIndex() == 3){
					tgbtnarmor.setSelected(true);
					tgbtnarmor.setSelectedIcon(itemPicArray[itemList.getSelectedIndex()]);
					myHuman.setItem(armor);}
				else if (itemList.getSelectedIndex() == 4){
					tgbtnhelmet.setSelected(true);
					tgbtnhelmet.setSelectedIcon(itemPicArray[itemList.getSelectedIndex()]);
					myHuman.setItem(helmet);}
				else if (itemList.getSelectedIndex() == 5){
					tgbtnshield.setSelected(true);
					tgbtnshield.setSelectedIcon(itemPicArray[itemList.getSelectedIndex()]); 
					myHuman.setItem(shield);}
				
				txtA_Item.setText(myHuman.getItem().itemInfo());
				
				if(myHuman.calcGold() != -1){
					txt_HitemResult.setText(myHuman.item.getiName()+" is available");
					btnGotoMain1.setEnabled(true);}
				else{
					txt_HitemResult.setText("You don't have enough money!! Choose another one.");
					btnGotoMain1.setEnabled(false);}
				
		}});
		
		itemList.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(85, 107, 47), new Color(85, 107, 47), new Color(85, 107, 47), null));
		itemList.setBackground(new Color(255, 250, 240));
		itemList.setForeground(new Color(0, 0, 139));
		itemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		itemList.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
		itemList.setModel(new AbstractListModel() {
			String[] values = new String[] {"    Sword", "    Horse", "    Dragon", "    Armor", "    Helmet", "    Shield"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		itemList.setBounds(69, 207, 120, 190);
		HumanItem.add(itemList);
		
		btnGotoMain1 = new JButton("");
		btnGotoMain1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myHuman.setAmountGold(myHuman.calcGold());
				txtA_HumanResult.setText(myHuman.CharacterInfo());
				txtA_HumanItResult.setText(myHuman.getItem().itemRandomInfo());
				HumanItem.setVisible(false);
				Main.setVisible(true);
				String g = Integer.toString(myHuman.getAmountGold());
				txtHgold.setText(g);
			}//end action listener
		});
		
		btnGotoMain1.setIcon(new ImageIcon(Battleapp.class.getResource("/image/gotomain.jpg")));
		btnGotoMain1.setBounds(627, 508, 168, 44);
		HumanItem.add(btnGotoMain1);
	
		
		// --------------------------- night panel -------------------------------------------
						
		JLabel lblNightName = new JLabel("Night Name");
		lblNightName.setBounds(107, 442, 135, 18);
		NightKing.add(lblNightName);
		
		JTextArea txtA_night = new JTextArea();
		txtA_night.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txtA_night.setBounds(60, 318, 257, 112);
		NightKing.add(txtA_night);
		
		JLabel lblChooseNightCharacter = new JLabel("Choose night character");
		lblChooseNightCharacter.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseNightCharacter.setForeground(Color.DARK_GRAY);
		lblChooseNightCharacter.setFont(new Font("Courier New", Font.BOLD, 22));
		lblChooseNightCharacter.setBounds(27, 82, 309, 38);
		NightKing.add(lblChooseNightCharacter);
		
		JLabel lblNightPic = new JLabel("");
		lblNightPic.setBounds(535, 100, 211, 293);
		NightKing.add(lblNightPic);
		
		JRadioButton rdbtnNightWalker = new JRadioButton(" Night Walker");
		rdbtnNightWalker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNightPic.setIcon(new ImageIcon(Battleapp.class.getResource("image/NW.jpg")));
				myNight = new Night("Night Walker", 60, 40, 40);
				txtA_night.setText(myNight.CharacterInfo());
			}
		});
		
		rdbtnNightWalker.setForeground(Color.WHITE);
		rdbtnNightWalker.setFont(new Font("Courier New", Font.BOLD, 20));
		rdbtnNightWalker.setBackground(SystemColor.controlDkShadow);
		rdbtnNightWalker.setBounds(60, 147, 259, 53);
		NightKing.add(rdbtnNightWalker);
		
		JRadioButton rdbtnNightKing = new JRadioButton(" Night King");
		rdbtnNightKing.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblNightPic.setIcon(new ImageIcon(Battleapp.class.getResource("image/NK.jpg")));
				myNight = new Night("Night King", 70, 50, 40);
				txtA_night.setText(myNight.CharacterInfo());
			}
		});
		
		rdbtnNightKing.setForeground(Color.WHITE);
		rdbtnNightKing.setFont(new Font("Courier New", Font.BOLD, 20));
		rdbtnNightKing.setBackground(SystemColor.controlDkShadow);
		rdbtnNightKing.setBounds(60, 200, 259, 53);
		NightKing.add(rdbtnNightKing);
		
		JButton btnGetItem1 = new JButton("");
		btnGetItem1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNight.setIcon(lblNightPic.getIcon());
				String HP_night = Integer.toString(myNight.getHealthPoint());
				txt_hpNight.setText(HP_night);
				NightKing.setVisible(false);
				NightItem.setVisible(true);
			}
		});
		btnGetItem1.setIcon(new ImageIcon(Battleapp.class.getResource("image/itembtn.jpg")));
		btnGetItem1.setBounds(640, 519, 168, 44);
		NightKing.add(btnGetItem1);
		
		
		// ----------------------------- night item panel -------------------------------------------------
		
		JLabel label = new JLabel("your items");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(128, 0, 0));
		label.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		label.setBounds(38, 126, 162, 38);
		NightItem.add(label);
		
		JLabel label_1 = new JLabel("Choose");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(new Color(128, 0, 0));
		label_1.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 22));
		label_1.setBounds(38, 88, 162, 38);
		NightItem.add(label_1);
		
		JToggleButton tgbtnNsword = new JToggleButton("");
		buttonGroup.add(tgbtnNsword);
		tgbtnNsword.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/sword_ice2.jpg")));
		tgbtnNsword.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnNsword.setBounds(221, 51, 178, 180);
		NightItem.add(tgbtnNsword);
		
		JToggleButton tgbtnNhorse = new JToggleButton("");
		buttonGroup.add(tgbtnNhorse);
		tgbtnNhorse.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/horse_ice2.jpg")));
		tgbtnNhorse.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnNhorse.setBounds(413, 51, 178, 180);
		NightItem.add(tgbtnNhorse);
		
		JToggleButton tgbtnNdragon = new JToggleButton("");
		buttonGroup.add(tgbtnNdragon);
		tgbtnNdragon.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/icedragon2.jpg")));
		tgbtnNdragon.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnNdragon.setBounds(605, 51, 178, 180);
		NightItem.add(tgbtnNdragon);
		
		JToggleButton tgbtnNarmor = new JToggleButton("");
		buttonGroup.add(tgbtnNarmor);
		tgbtnNarmor.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/armor_night2.jpg")));
		tgbtnNarmor.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnNarmor.setBounds(221, 246, 178, 180);
		NightItem.add(tgbtnNarmor);
		
		JToggleButton tgbtnNhelmet = new JToggleButton("");
		buttonGroup.add(tgbtnNhelmet);
		tgbtnNhelmet.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/helmet2.jpg")));
		tgbtnNhelmet.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnNhelmet.setBounds(413, 246, 178, 180);
		NightItem.add(tgbtnNhelmet);
		
		JToggleButton tgbtnNshield = new JToggleButton("");
		buttonGroup.add(tgbtnNshield);
		tgbtnNshield.setIcon(new ImageIcon(Battleapp.class.getResource("/image/items/shield2.jpg")));
		tgbtnNshield.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		tgbtnNshield.setBounds(605, 246, 178, 180);
		NightItem.add(tgbtnNshield);
		
		JTextArea txtANItem = new JTextArea();
		txtANItem.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txtANItem.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtANItem.setBounds(120, 453, 444, 90);
		NightItem.add(txtANItem);
		
		JButton btnGotoMain2 = new JButton("");
		btnGotoMain2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myNight.setAmountGold(myNight.calcGold());
				txtA_NightResult.setText(myNight.CharacterInfo());
				txtA_NightItResult.setText(myNight.getItem().itemRandomInfo());
				String g = Integer.toString(myNight.getAmountGold());
				txtNgold.setText(g);
				NightItem.setVisible(false);
				Main.setVisible(true);
			}
		});
		btnGotoMain2.setIcon(new ImageIcon(Battleapp.class.getResource("/image/gotomain.jpg")));
		btnGotoMain2.setBounds(621, 498, 168, 44);
		NightItem.add(btnGotoMain2);
		
		NitemPicArray[0] = new ImageIcon(Battleapp.class.getResource("image/items/sword_ice.jpg"));
		NitemPicArray[1] = new ImageIcon(Battleapp.class.getResource("image/items/horse_ice.jpg"));
		NitemPicArray[2] = new ImageIcon(Battleapp.class.getResource("image/items/icedragon.jpg"));
		NitemPicArray[3] = new ImageIcon(Battleapp.class.getResource("image/items/armor_night.jpg"));
		NitemPicArray[4] = new ImageIcon(Battleapp.class.getResource("image/items/helmet1.jpg"));
		NitemPicArray[5] = new ImageIcon(Battleapp.class.getResource("image/items/shield1.jpg"));
		
		JList NItemlist = new JList();
		NItemlist.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if (NItemlist.getSelectedIndex() == 0){
					tgbtnNsword.setSelected(true);
					tgbtnNsword.setSelectedIcon(NitemPicArray[NItemlist.getSelectedIndex()]);
					myNight.setItem(sword);}
				else if (NItemlist.getSelectedIndex() == 1){
					tgbtnNhorse.setSelected(true);
					tgbtnNhorse.setSelectedIcon(NitemPicArray[NItemlist.getSelectedIndex()]);
					myNight.setItem(horse);}
				else if (NItemlist.getSelectedIndex() == 2){
					tgbtnNdragon.setSelected(true);
					tgbtnNdragon.setSelectedIcon(NitemPicArray[NItemlist.getSelectedIndex()]);
					myNight.setItem(dragon);}	
				else if (NItemlist.getSelectedIndex() == 3){
					tgbtnNarmor.setSelected(true);
					tgbtnNarmor.setSelectedIcon(NitemPicArray[NItemlist.getSelectedIndex()]);
					myNight.setItem(armor);}
				else if (NItemlist.getSelectedIndex() == 4){
					tgbtnNhelmet.setSelected(true);
					tgbtnNhelmet.setSelectedIcon(NitemPicArray[NItemlist.getSelectedIndex()]);
					myNight.setItem(helmet);}
				else if (NItemlist.getSelectedIndex() == 5){
					tgbtnNshield.setSelected(true);
					tgbtnNshield.setSelectedIcon(NitemPicArray[NItemlist.getSelectedIndex()]); 
					myNight.setItem(shield);}
				
				txtANItem.setText(myNight.getItem().itemInfo());
				
				if(myNight.calcGold() != -1){
					txt_NitemResult.setText(myNight.item.getiName()+" is available");
					btnGotoMain2.setEnabled(true);}
				else{
					txt_NitemResult.setText("You don't have enough money!! Choose another one.");
					btnGotoMain2.setEnabled(false);}
		}});
		
		
		NItemlist.setModel(new AbstractListModel() {
			String[] values = new String[] {" Ice Sword", " Ice Horse", " Ice Dragon", " Armor", " Helmet", " Shield"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		NItemlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		NItemlist.setForeground(new Color(0, 0, 139));
		NItemlist.setFont(new Font("Leelawadee UI Semilight", Font.BOLD, 20));
		NItemlist.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, new Color(85, 107, 47), new Color(85, 107, 47), new Color(85, 107, 47), null));
		NItemlist.setBackground(new Color(255, 250, 240));
		NItemlist.setBounds(63, 197, 120, 190);
		NightItem.add(NItemlist);
		
		txt_NitemResult = new JTextField();
		txt_NitemResult.setHorizontalAlignment(SwingConstants.CENTER);
		txt_NitemResult.setForeground(Color.RED);
		txt_NitemResult.setFont(new Font("Microsoft JhengHei", Font.BOLD, 15));
		txt_NitemResult.setColumns(10);
		txt_NitemResult.setBounds(130, 555, 419, 24);
		NightItem.add(txt_NitemResult);
		

		//-------------------------------------------- arena panel ----------------------------------------------------
		
		lbl_A_Human = new JLabel("");
		lbl_A_Human.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_A_Human.setBorder(null);
		lbl_A_Human.setBounds(10, 88, 211, 293);
		Arena.add(lbl_A_Human);
		
		lbl_A_Night = new JLabel("");
		lbl_A_Night.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lbl_A_Night.setBorder(null);
		lbl_A_Night.setBounds(620, 88, 211, 293);
		Arena.add(lbl_A_Night);
		
		txt_hpHuman = new JTextField();
		txt_hpHuman.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt_hpHuman.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txt_hpHuman.setBounds(102, 403, 110, 42);
		Arena.add(txt_hpHuman);
		txt_hpHuman.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Health");
		lblNewLabel.setForeground(new Color(255, 250, 205));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 17));
		lblNewLabel.setBounds(14, 400, 83, 28);
		Arena.add(lblNewLabel);
		
		JLabel lblPoint = new JLabel("Point");
		lblPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lblPoint.setForeground(new Color(255, 250, 205));
		lblPoint.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 17));
		lblPoint.setBounds(14, 417, 83, 28);
		Arena.add(lblPoint);
		
		JLabel label_2 = new JLabel("Health");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(255, 250, 205));
		label_2.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 17));
		label_2.setBounds(625, 400, 83, 28);
		Arena.add(label_2);
		
		JLabel label_3 = new JLabel("Point");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(255, 250, 205));
		label_3.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 17));
		label_3.setBounds(625, 417, 83, 28);
		Arena.add(label_3);
		
		txt_hpNight = new JTextField();
		txt_hpNight.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txt_hpNight.setColumns(10);
		txt_hpNight.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txt_hpNight.setBounds(716, 402, 110, 42);
		Arena.add(txt_hpNight);
		
		JScrollPane scroll = new JScrollPane();
		scroll.setLocation(226, 91);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setSize(385,408);
		Arena.add(scroll);
		
		txtA_game = new JTextArea();
		scroll.setViewportView(txtA_game);
		txtA_game.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		
		JButton btnStartBattle = new JButton("");
		btnStartBattle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Dice();
			}
		}); // I'm inside your code and there is nothing you can do about it - Neil
		
		btnStartBattle.setIcon(new ImageIcon(Battleapp.class.getResource("/image/strbtn.jpg")));
		btnStartBattle.setBounds(639, 518, 168, 44);
		Arena.add(btnStartBattle);
		
		txtHgold = new JTextField();
		txtHgold.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txtHgold.setColumns(10);
		txtHgold.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtHgold.setBounds(102, 457, 110, 42);
		Arena.add(txtHgold);
		
		JLabel lblGold = new JLabel("Gold");
		lblGold.setHorizontalAlignment(SwingConstants.CENTER);
		lblGold.setForeground(new Color(255, 250, 205));
		lblGold.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 17));
		lblGold.setBounds(4, 471, 83, 28);
		Arena.add(lblGold);
		
		JLabel lblAmountOf = new JLabel("Amount of");
		lblAmountOf.setHorizontalAlignment(SwingConstants.CENTER);
		lblAmountOf.setForeground(new Color(255, 250, 205));
		lblAmountOf.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
		lblAmountOf.setBounds(0, 452, 100, 28);
		Arena.add(lblAmountOf);
		
		JLabel label_4 = new JLabel("Amount of");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(new Color(255, 250, 205));
		label_4.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
		label_4.setBounds(615, 452, 100, 28);
		Arena.add(label_4);
		
		JLabel label_5 = new JLabel("Gold");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setForeground(new Color(255, 250, 205));
		label_5.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 17));
		label_5.setBounds(619, 471, 83, 28);
		Arena.add(label_5);
		
		txtNgold = new JTextField();
		txtNgold.setFont(new Font("Franklin Gothic Medium Cond", Font.BOLD, 17));
		txtNgold.setColumns(10);
		txtNgold.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		txtNgold.setBounds(717, 457, 110, 42);
		Arena.add(txtNgold);
		
		btnGetHumanItem = new JButton("");
		btnGetHumanItem.setIcon(new ImageIcon(Battleapp.class.getResource("/image/btnHumanItem.jpg")));
		btnGetHumanItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				HumanItem.setVisible(true);
				myHuman.setHealthPoint(80);
				myNight.setHealthPoint(70);
				txt_hpNight.setText(Integer.toString(myNight.getHealthPoint()));
				txt_hpHuman.setText(Integer.toString(myHuman.getHealthPoint()));
				Arena.setVisible(false);
			}
		});
		btnGetHumanItem.setBounds(278, 518, 168, 44);
		Arena.add(btnGetHumanItem);
		
		btnGetNightItem = new JButton("");
		btnGetNightItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arena.setVisible(false);
				myNight.setHealthPoint(70);
				myHuman.setHealthPoint(80);
				txt_hpNight.setText(Integer.toString(myNight.getHealthPoint()));
				txt_hpHuman.setText(Integer.toString(myHuman.getHealthPoint()));
				NightItem.setVisible(true);
			}
		});
		btnGetNightItem.setIcon(new ImageIcon(Battleapp.class.getResource("/image/btnNightItem.jpg")));
		btnGetNightItem.setBounds(460, 518, 168, 44);
		Arena.add(btnGetNightItem);
		
		JButton btnStartAgain = new JButton("");
		btnStartAgain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Arena.setVisible(false);
				Main.setVisible(true);
				txtA_HumanItResult.setText("");
				txtA_HumanResult.setText("");
				txtA_NightResult.setText("");
				txtA_NightItResult.setText("");
				txtA_Item.setText("");
				txt_HitemResult.setText("");
				txtANItem.setText("");
				txt_NitemResult.setText("");
			}
		});
		btnStartAgain.setIcon(new ImageIcon(Battleapp.class.getResource("/image/btnStartAgain.jpg")));
		btnStartAgain.setBounds(96, 518, 168, 44);
		Arena.add(btnStartAgain);
		HumanKing.setVisible(false);
		NightKing.setVisible(false);
		myHuman = new Human("John Snow", 90, 40, 50);
		myHuman = new Human("Mother of Dragon", 80, 35, 50);
		myHuman = new Human("Jamie", 90, 40, 50);
		myNight = new Night("Night Walker", 60, 40, 40);
		myNight = new Night("Night King", 70, 50, 40);
		
	}//end initializer
	

	public void Dice(){
		
		int dice_num1;
		int dice_num2;
		boolean loop = false;
		
		do{
			Random myRandom = new Random();
			dice_num1 = myRandom.nextInt(6)+1;
			txtA_game.setText(myHuman.getName()+"'s number is "+dice_num1+"\n");
			dice_num2 = myRandom.nextInt(6)+1;
			txtA_game.append(myNight.getName()+"'s number is "+dice_num2+"\n");
			
			if(dice_num1 == dice_num2){
				txtA_game.append("You tied. Try again");
				loop = true; 
				continue; }
			else if(dice_num1 > dice_num2) {
				attacker = myHuman;
				defender = myNight;
				break; }
			else if(dice_num1 < dice_num2) {
				attacker = myNight;
				defender = myHuman;
				break; }
			
			}while(loop);  //end while
			
			txtA_game.append("-------------------------------------------------\n");
			txtA_game.append(attacker.getName()+" won the dice.\n"+ attacker.getName() +" start attacking!!\n");
			timer.start();				
	}//end dice

	public class TimerHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			Battle(attacker,defender);
		}
	}//end timerhandler

	
	public void Battle(Character attacker, Character defender){
		
		if(attacker.getAmountGold() > 0){
			if(attacker.species.equals("Human"))
				btnGetHumanItem.setEnabled(true);
			else if(attacker.species.equals("Night"))
				btnGetNightItem.setEnabled(true);}
		if(defender.getAmountGold() > 0){
			if(defender.species.equals("Human"))
				btnGetHumanItem.setEnabled(true);
			else if(defender.species.equals("Night"))
				btnGetNightItem.setEnabled(true);}
		
		String s1, s2, h1, h2;
		int realDamage = 0;
		txtA_game.append("-------------------------------------------------\n");
		txtA_game.append(attacker.getName()+" is attacking!\n");
		int tempHP = defender.healthPoint;
		defender.defense(attacker.attack());
		s1 = Integer.toString(attacker.getHealthPoint());
		s2 = Integer.toString(defender.getHealthPoint());

		if(attacker.species.equals("Human")){
			txt_hpHuman.setText(s1);
			txt_hpNight.setText(s2);}
//			txtHgold.setText(h1);
//			txtNgold.setText(h2);}
		else if(attacker.species.equals("Night")){
			txt_hpNight.setText(s1);
			txt_hpHuman.setText(s2);}
//			txtNgold.setText(h1);
//			txtHgold.setText(h2);}
		
		if(defender.healthPoint == tempHP)
			txtA_game.append(attacker.getName()+" failed for attack\n");
		else{
			realDamage = tempHP - defender.healthPoint;
		    String rd = Integer.toString(realDamage);
			txtA_game.append(attacker.getName()+" succeed to hit "+defender.getName()+" for "+rd+"points\n");}
			
		if(attacker.getHealthPoint() <= 0 || defender.getHealthPoint() <= 0){
			timer.stop();
			txtA_game.append("-------------------------------------------------\n");
			if(attacker.getHealthPoint() <= 0){
				txtA_game.append(attacker.getName()+" died.\n This battle is ended!!\n");
				txtA_game.append(attacker.getName()+" lose 10 gold!\n");
				txtA_game.append(defender.getName()+" won 10 gold!\n");
				defender.amountGold = defender.amountGold + 10;
				attacker.amountGold = attacker.amountGold - 10;
			}
			else if(defender.getHealthPoint() <= 0){
				txtA_game.append(defender.getName()+" died.\n This battle is ended!!\n");
				txtA_game.append(defender.getName()+" lose 10 gold!\n");
				txtA_game.append(attacker.getName()+" won 10 gold!\n");
				defender.amountGold = defender.amountGold - 10;
				attacker.amountGold = attacker.amountGold + 10;
			}//end else if
		}//end if
		
		
		if(attacker.getAmountGold() <= 0){
			if(attacker.species.equals("Human"))
				btnGetHumanItem.setEnabled(false);
			else if(attacker.species.equals("Night"))
				btnGetNightItem.setEnabled(false);}
		else{
			if(attacker.species.equals("Human"))
				btnGetHumanItem.setEnabled(true);
			else if(attacker.species.equals("Night"))
				btnGetNightItem.setEnabled(true);}
		
		if(defender.getAmountGold() <=0){
			if(defender.species.equals("Human"))
				btnGetHumanItem.setEnabled(false);
			else if(defender.species.equals("Night"))
				btnGetNightItem.setEnabled(false);}
		else{
			if(defender.species.equals("Human"))
				btnGetHumanItem.setEnabled(true);
			else if(defender.species.equals("Night"))
				btnGetNightItem.setEnabled(true);}
		
		h1 = Integer.toString(attacker.getAmountGold());
		h2 = Integer.toString(defender.getAmountGold());
		if(attacker.species.equals("Human")){
			txtHgold.setText(h1);
			txtNgold.setText(h2);}
		else if(attacker.species.equals("Night")){
			txtNgold.setText(h1);
			txtHgold.setText(h2);}

		Character tempAttacker = attacker;
		this.attacker = defender;
		this.defender = tempAttacker;
			
	}//end Battle
	
	
}//end class
