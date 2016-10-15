import java.io.*;
import java.net.*;
public class Ex04 {
	public static void main(String ar[]){
		ServerSocket ss=null;
		for(int i=1;i<=65535;i++){
			try{
			ss=new ServerSocket(i);
			
			}catch(IOException e){System.out.println(i+"번째는 사용중입니다.");
			}
		}
	}
}
