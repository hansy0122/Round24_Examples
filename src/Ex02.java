import java.io.*;
import java.net.*;

public class Ex02 {
	public static void main(String ar[]) {
		InetAddress ia = null;
		InetAddress ia1 = null;
		InetAddress[] ia2 = null;
		try {
			ia = InetAddress.getLocalHost();
			ia1 = InetAddress.getByName("www.naver.com");
			ia2 = InetAddress.getAllByName("www.naver.com");
		} catch (UnknownHostException e) {
		}

		System.out.println("ia.hostaddress=" + ia.getHostAddress());
		System.out.println("ia.hostname=" + ia.getHostName());
		System.out.println();
		System.out.println("ia1.hostaddress=" + ia1.getHostAddress());
		System.out.println("ia1.hostname=" + ia1.getHostName());
		System.out.println();
		for(int i=0;i<ia2.length;i++){
		System.out.println("ia2.hostaddress=" + ia2[i].getHostAddress());
		System.out.println("ia2.hostname=" + ia2[i].getHostName());}
	}

}
