import java.io.*;
import java.net.*;
//Ŭ���̾�Ʈ
public class Ex05 {
public static void main(String ar[]){
	InetAddress ia=null;
	try{
		ia=InetAddress.getByName("172.30.82.1");
	}catch(UnknownHostException e){}
	Socket soc=null;
	try{
		soc=new Socket(ia,12345); // ������ ��ȭ�� �ɰڽ��ϴ�.
	
		//���
		OutputStreamWriter osw=new OutputStreamWriter(soc.getOutputStream());
		BufferedWriter bw=new BufferedWriter(osw);
		PrintWriter pw=new PrintWriter(bw);
		pw.println("hellow2");
		pw.flush();
		
		//�Է�
		InputStreamReader isr=new InputStreamReader(soc.getInputStream());
		BufferedReader br=new BufferedReader(isr,512);
		String data=br.readLine();
		System.out.println("������ �亯="+data);
		
	}catch(IOException ie){}
}
}
