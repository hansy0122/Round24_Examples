import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import java.net.*;

public class Ex07 extends JApplet implements ActionListener{
private Container con;
private JTextField ip_tf=new JTextField();
private JButton conn_bt=new JButton("����");
private JTextArea view_ta=new JTextArea();
private JScrollPane view_jsp=new JScrollPane(view_ta);

public void init(){
	con=this.getContentPane();
	con.setLayout(new BorderLayout());
	JPanel jp=new JPanel(new BorderLayout());
	jp.add("Center",ip_tf);
	jp.add("East",conn_bt);
	jp.setBorder(new TitledBorder("���� ����"));
	con.add("North", jp);
	view_jsp.setBorder(new TitledBorder("��� ���"));
	con.add("Center", view_jsp);
	
}
public void start(){
	ip_tf.addActionListener(this);
	conn_bt.addActionListener(this);
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==ip_tf||e.getSource()==conn_bt){
		String ip=ip_tf.getText();
		try{
			URL url=new URL(ip);
			URLConnection uc=url.openConnection();
			uc.setDoInput(true); //get���
			//uc.setDoOutput(true);//post ���
			
			InputStreamReader isr=new InputStreamReader(uc.getInputStream());
			BufferedReader br=new BufferedReader(isr,512);
			while(true){
				String s=br.readLine();
				if(s==null)break;
				view_ta.append(s+"\n");
				
			}
			br.close();
		}catch(IOException ee){}
		
	}
}
}
