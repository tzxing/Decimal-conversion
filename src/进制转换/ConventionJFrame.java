package 进制转换;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.String;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ConventionJFrame extends JFrame implements ActionListener
{
	private JTextField text[][];
	private JLabel label[];
	private static String TextFieldName[]={"十进制","二进制","八进制","十六进制"};
	private MessageJDialog  jdialog;
	int x;

	
	public ConventionJFrame()
	{
		super("int整数的进制转换");
		this.setBounds(300,200, 800, 150);
		this.setResizable(false);
		this.setLayout(new GridLayout(4,3));
		label=new JLabel[TextFieldName.length];
		text=new JTextField[TextFieldName.length][2];
		for(int i=0;i<TextFieldName.length;i++)
		{	
			text[i][0]=new JTextField(20);
			text[i][0].setEditable(true);
			text[i][0].addActionListener(this);
			text[i][1]=new JTextField(20);
			text[i][1].setEditable(false);
		}
		
		for(int i=0;i<TextFieldName.length;i++)
		{
			label[i]=new JLabel(TextFieldName[i]);
			label[i].setHorizontalAlignment(JLabel.RIGHT);
			this.add(label[i]);
			this.add(text[i][0]);
			this.add(text[i][1]);
		}
		
		this.setVisible(true);
		
		jdialog=new MessageJDialog();
	}
	
	private class MessageJDialog extends JDialog
	{
		private JLabel label;
		
		public MessageJDialog()
		{
			super(ConventionJFrame.this,"提示",true);
			this.setSize(300, 80);
			label=new JLabel("",JLabel.CENTER);
			this.add(label);
			this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		}
		private void show(String message)
		{
			label.setText(message);
			this.setLocation(ConventionJFrame.this.getX()+100, ConventionJFrame.this.getY()+100);
			this.setVisible(true);
		}
		
	}

	public void actionPerformed(ActionEvent ev)
	{
		int index=0;
		for(int i=0;i<TextFieldName.length;i++)
		{
			if(text[i][0]==ev.getSource())
				index=i;
		}
		try
		{
			if(index==0)
				x=Integer.parseInt(text[index][0].getText());
			else
				x=Integer.parseInt(text[index][0].getText(), (int)Math.pow(2, index>1?index+1:index));
			text[0][0].setText(Integer.toString(x));
			text[1][0].setText(addZero(Integer.toBinaryString(x), 32));
			text[2][0].setText(addZero(Integer.toOctalString(x), 11));
			text[3][0].setText(addZero(Integer.toHexString(x), 8));
			text[0][1].setText(Integer.toString(-x));
			text[1][1].setText(addZero(Integer.toBinaryString(-x), 32));
			text[2][1].setText(addZero(Integer.toOctalString(-x), 11));
			text[3][1].setText(addZero(Integer.toHexString(-x), 8));
		
		}
		catch(NumberFormatException ex)
		{
			jdialog.show(""+text[index][0].getText()+"不符合进制格式");
		}
		finally 
		{
		}
	}
	
	public String addZero(String str,int x)
	{
		int y=str.length();
		String ret;
		if(y<x)
		{
			char temp[]=new char[x];
			for(int i=0;i<x-y;i++)
			{
				temp[i]='0';
			}
			for(int i=x-y;i<x;i++)
			{
				temp[i]=str.charAt(i-x+y);
			}
			ret=new String(temp);
			return ret;
		}
		else
			return str;
	}


	public static void main(String[] args)
	{
		new ConventionJFrame();

	}
}
