import java.io.*;
import java.net.*;

public class Ex06 {
public static void main(String ar[]){
	ServerSocket ss=null;
	try{
		ss=new ServerSocket(12345); //�ڽ��� 12345�� ��Ʈ�� ���ؼ� ������ ���׷��̵� ��ų�� �ֵ��� ���׷��̵�!!
		System.out.println("server ready");
		while(true){
		Socket s=ss.accept(); // ���� ������ �����ߴ��� Ȯ��
		System.out.println("s="+s);
		
		//�Է�
		InputStreamReader isr=new InputStreamReader(s.getInputStream());
		BufferedReader br=new BufferedReader(isr,512);
		String data=br.readLine();
		System.out.println("msg="+data);
		
		//���
		OutputStreamWriter osw=new OutputStreamWriter(s.getOutputStream());
		BufferedWriter bw=new BufferedWriter(osw);
		PrintWriter pw=new PrintWriter(bw);
		pw.println("msg recept fail");
		pw.flush();
		}
	}catch(IOException e){
		
	}
}
}
