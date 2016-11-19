import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ArtFont extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JComboBox<String> fontType;//字体样式下拉框, 
	JComboBox<String> fontSize;//字体大小下拉框
	JComboBox<String> windowStyle;//窗体样式下拉框
	JCheckBox boldBx;// 粗体按钮
	JCheckBox italicBx;// 斜体按钮
	JButton colorBtn;// 颜色按钮；
	String[] fontNames;// 字体名称;
	String[] fontSizes;// 字体大小；

	JLabel label;// 输入提示标签；
	JTextField inputText;// 文字输入框；
	JTextArea txtArea;// 文字显示区;
	JPanel northPanel;// 字体设置；
	JPanel centerPanel;// 显示效果区
	JPanel southPanel;//样式设置

	Font font;
	int boldStyle, italicStyle, underlineStyle;
	int fontSizeStyle;
	String fontNameStyle;
	Color colorStyle = new Color(66, 132, 243);// 设置字体的默认颜色为黑色;
	String[] style = { "默认显示效果", "Windows显示效果", "Unix显示效果" };

	public ArtFont() {
		super("字体设置");
		// 设置默认字体
		boldStyle = 0;
		italicStyle = 0;
		underlineStyle = 0;
		fontSizeStyle = 10;
		fontNameStyle = "楷体";
		font = new Font(fontNameStyle, boldStyle + italicStyle, fontSizeStyle);
		northPanel = getNorthPanel();
		centerPanel = getCenterPanel();
		southPanel = getSouthPanel();
		// 设置容器;
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		//将northPanel添加到窗体的北部
		container.add(northPanel,BorderLayout.NORTH);
		//将centerPanel添加到窗体的中部
		container.add(centerPanel,BorderLayout.CENTER);
		//将southPanel添加到窗体的南部
		container.add(southPanel,BorderLayout.SOUTH);
		setSize(500, 300);
		//将窗体位于屏幕的中央
		setVisible(true);	

	}	
	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		label = new JLabel("输入：");
		boldBx = new JCheckBox("粗体");
		italicBx = new JCheckBox("斜体");
		boldBx.addActionListener(new ActionListener() {
			//粗体
			//以下代码过于啰嗦，有待优化
			public void actionPerformed(ActionEvent e) {
				//加粗复选框被选中

				if (e.getSource() == boldBx) {

					if (boldBx.isSelected()) {
						//加粗倾斜被选中
						if (italicBx.isSelected()) {
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.BOLD + Font.ITALIC, 20));
							boldStyle = Font.BOLD;
							italicStyle = Font.ITALIC;
						}else{
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.BOLD, 20));
							boldStyle = Font.BOLD;
							italicStyle = 0;
						}
					}else{
						//加粗未选中倾斜选中
						if (italicBx.isSelected()) {
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.ITALIC, 20));

							boldStyle = 0;
							italicStyle = Font.ITALIC;
						}
						else{
							//加粗和倾斜都没选中
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), 5, 20));
							boldStyle = 0;
							italicStyle = 0;
						}
					}				
					txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
				}
			}
		});
		italicBx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//如果斜体复选框被选中则

				if (e.getSource() == italicBx) {


					if (italicBx.isSelected()) {
						//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.ITALIC, 20));
						//倾斜和粗体都选中
						if (boldBx.isSelected()) {
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.BOLD + Font.ITALIC, 20));
							boldStyle = Font.BOLD;
							italicStyle = Font.ITALIC;
						}else{
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.ITALIC, 20));
							boldStyle = 0;
							italicStyle = Font.ITALIC;
						}
					}else{
						//如果斜体复选框的状态没被选中则(使用toString是可以的)
						//txtArea.setFont(new Font(txtArea.getFont().toString(), 5, 20));
						if (boldBx.isSelected()) {
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.BOLD, 20));
							boldStyle = Font.BOLD;
							italicStyle = 0;
						}else{
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.ITALIC, 20));
							boldStyle = 0;
							italicStyle = 0;
						}
					}
					txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
				}
			}

		});

		inputText = new JTextField(15);
		inputText.getDocument().addDocumentListener(new inputValueChange());
		colorBtn = new JButton("颜色");
		colorBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == colorBtn)
					txtArea.setForeground(colorStyle);
			}
		});
		panel.add(label);
		panel.add(inputText);
		panel.add(boldBx);
		panel.add(italicBx);
		panel.add(colorBtn);
		return panel;
	}	


	//文本值改变事件JTextField没有相应事件处理
	class inputValueChange implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			//文本属性改变触发
			txtArea.setText("文本属性更改了/n"+inputText.getText());
		}
		public void insertUpdate(DocumentEvent e) {
			//文本增加触发
			txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
			txtArea.setText(""+inputText.getText());
		}
		public void removeUpdate(DocumentEvent e) {
			//文本减少触发
			txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
			txtArea.setText(""+inputText.getText());
		}
	}
	//文本区域
	private JPanel getCenterPanel() {
		JPanel panel = new JPanel();
		txtArea = new JTextArea(0,0);
		txtArea.setEditable(false);
		txtArea.setBackground(panel.getBackground());//与背景同色
		panel.add(txtArea);
		return panel;
	}
	private JPanel getSouthPanel() {
		JPanel panel = new JPanel();
		fontNameStyle = new String();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fontNames = ge.getAvailableFontFamilyNames();
		fontType = new JComboBox<>(fontNames);//字体名称
		fontSizes = new String[63];//字体大小
		for (int i = 0; i < fontSizes.length; i++) {
			fontSizes[i] = Integer.toString(i+10);
		}
		fontSize = new JComboBox<>(fontSizes);
		windowStyle = new JComboBox<>(style);

		fontType.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == fontType)
					txtArea.setFont(new Font(fontType.getSelectedItem().toString(), boldStyle + italicStyle, fontSizeStyle));
			}
		});
		fontSize.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (e.getSource() == fontSize){
					fontSizeStyle = Integer.parseInt(fontSize.getSelectedItem().toString());
					txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
				}
			}
		});

		windowStyle.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(e.getSource() == windowStyle){
					String s = e.getItem().toString();
					String className = "";
					if (s.equals("Windows显示效果"))
						className = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					else if (s.equals("Unix显示效果"))
						className = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
					else if (s.equals("默认显示效果"))
						className = UIManager.getCrossPlatformLookAndFeelClassName();
					try {
						UIManager.setLookAndFeel(className);
						SwingUtilities.updateComponentTreeUI(getContentPane());
					} catch (Exception de) {
						System.out.println("Exception happened!");
					}
				}
			}
		});
		panel.add(fontType);
		panel.add(fontSize);
		panel.add(windowStyle);
		return panel;		
	}
	public static void main(String args[]) {
		ArtFont artFont = new ArtFont();
		artFont.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
