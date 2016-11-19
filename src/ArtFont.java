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
	JComboBox<String> fontType;//������ʽ������, 
	JComboBox<String> fontSize;//�����С������
	JComboBox<String> windowStyle;//������ʽ������
	JCheckBox boldBx;// ���尴ť
	JCheckBox italicBx;// б�尴ť
	JButton colorBtn;// ��ɫ��ť��
	String[] fontNames;// ��������;
	String[] fontSizes;// �����С��

	JLabel label;// ������ʾ��ǩ��
	JTextField inputText;// ���������
	JTextArea txtArea;// ������ʾ��;
	JPanel northPanel;// �������ã�
	JPanel centerPanel;// ��ʾЧ����
	JPanel southPanel;//��ʽ����

	Font font;
	int boldStyle, italicStyle, underlineStyle;
	int fontSizeStyle;
	String fontNameStyle;
	Color colorStyle = new Color(66, 132, 243);// ���������Ĭ����ɫΪ��ɫ;
	String[] style = { "Ĭ����ʾЧ��", "Windows��ʾЧ��", "Unix��ʾЧ��" };

	public ArtFont() {
		super("��������");
		// ����Ĭ������
		boldStyle = 0;
		italicStyle = 0;
		underlineStyle = 0;
		fontSizeStyle = 10;
		fontNameStyle = "����";
		font = new Font(fontNameStyle, boldStyle + italicStyle, fontSizeStyle);
		northPanel = getNorthPanel();
		centerPanel = getCenterPanel();
		southPanel = getSouthPanel();
		// ��������;
		Container container = getContentPane();
		container.setLayout(new BorderLayout());
		//��northPanel��ӵ�����ı���
		container.add(northPanel,BorderLayout.NORTH);
		//��centerPanel��ӵ�������в�
		container.add(centerPanel,BorderLayout.CENTER);
		//��southPanel��ӵ�������ϲ�
		container.add(southPanel,BorderLayout.SOUTH);
		setSize(500, 300);
		//������λ����Ļ������
		setVisible(true);	

	}	
	private JPanel getNorthPanel() {
		JPanel panel = new JPanel();
		label = new JLabel("���룺");
		boldBx = new JCheckBox("����");
		italicBx = new JCheckBox("б��");
		boldBx.addActionListener(new ActionListener() {
			//����
			//���´�����چ��£��д��Ż�
			public void actionPerformed(ActionEvent e) {
				//�Ӵָ�ѡ��ѡ��

				if (e.getSource() == boldBx) {

					if (boldBx.isSelected()) {
						//�Ӵ���б��ѡ��
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
						//�Ӵ�δѡ����бѡ��
						if (italicBx.isSelected()) {
							//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.ITALIC, 20));

							boldStyle = 0;
							italicStyle = Font.ITALIC;
						}
						else{
							//�Ӵֺ���б��ûѡ��
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
				//���б�帴ѡ��ѡ����

				if (e.getSource() == italicBx) {


					if (italicBx.isSelected()) {
						//txtArea.setFont(new Font(txtArea.getFont().getFontName(), Font.ITALIC, 20));
						//��б�ʹ��嶼ѡ��
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
						//���б�帴ѡ���״̬û��ѡ����(ʹ��toString�ǿ��Ե�)
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
		colorBtn = new JButton("��ɫ");
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


	//�ı�ֵ�ı��¼�JTextFieldû����Ӧ�¼�����
	class inputValueChange implements DocumentListener {
		public void changedUpdate(DocumentEvent e) {
			//�ı����Ըı䴥��
			txtArea.setText("�ı����Ը�����/n"+inputText.getText());
		}
		public void insertUpdate(DocumentEvent e) {
			//�ı����Ӵ���
			txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
			txtArea.setText(""+inputText.getText());
		}
		public void removeUpdate(DocumentEvent e) {
			//�ı����ٴ���
			txtArea.setFont(new Font(txtArea.getFont().getFontName(), boldStyle + italicStyle, fontSizeStyle));
			txtArea.setText(""+inputText.getText());
		}
	}
	//�ı�����
	private JPanel getCenterPanel() {
		JPanel panel = new JPanel();
		txtArea = new JTextArea(0,0);
		txtArea.setEditable(false);
		txtArea.setBackground(panel.getBackground());//�뱳��ͬɫ
		panel.add(txtArea);
		return panel;
	}
	private JPanel getSouthPanel() {
		JPanel panel = new JPanel();
		fontNameStyle = new String();
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		fontNames = ge.getAvailableFontFamilyNames();
		fontType = new JComboBox<>(fontNames);//��������
		fontSizes = new String[63];//�����С
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
					if (s.equals("Windows��ʾЧ��"))
						className = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
					else if (s.equals("Unix��ʾЧ��"))
						className = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
					else if (s.equals("Ĭ����ʾЧ��"))
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
