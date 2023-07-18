import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientEx {
	Socket client = null;
	
	String ipAddress;
	static final int PORT = 5000;
	
	BufferedReader read;
	
	InputStream is;
	ObjectInputStream ois;
	
	OutputStream os;
	ObjectOutputStream oos;
	
	String sendData;
	String recieveData;
	
	public ClientEx(String ipAddress) {
		this.ipAddress = ipAddress;
		
		try {
			System.out.println("****사용자 채팅 프로그램****");
			
			client = new Socket(ipAddress,PORT);
			read = new BufferedReader(new InputStreamReader (System.in));
			
			os = client.getOutputStream();
			oos = new ObjectOutputStream(os);
			
			is = client.getInputStream();
			ois = new ObjectInputStream(is);
			
			System.out.print("입력 -> ");
			while((sendData = read.readLine()) != null) {
				oos.writeObject(sendData);
				oos.flush();
				
				if(sendData.equals("quit")) {
					break;
				}
				
				recieveData = (String)ois.readObject();
				System.out.println(client.getInetAddress()+"로 부터 받은 메시지: "+recieveData);
				System.out.println("입력 => ");
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.exit(0);
		}finally {
			try {
				if(is != null) is.close();
				if(ois != null) ois.close();
				if(os != null) os.close();
				if(oos != null) oos.close();
				if(client != null) client.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		new ClientEx("192.168.41.65");
	}

}
