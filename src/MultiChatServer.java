import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

class EchoServerThread implements Runnable {
	Socket child;
	ObjectInputStream ois;
	InputStream is;
	OutputStream os;
	ObjectOutputStream oos;
	
	String recieveData;
	

	public EchoServerThread(Socket child, ObjectInputStream ois, ObjectOutputStream oos) {
		try {
			System.out.println(child.getInetAddress() + "로 부터 연결 요청 받음");
			is = child.getInputStream();
			os = child.getOutputStream();
			oos = new ObjectOutputStream(os);
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}

	@Override
	public void run() {
		try {
			while(true) {
				recieveData = (String)ois.readObject();
				System.out.println(child.getInetAddress()+"의 메시지: "+recieveData);
				oos.writeObject(recieveData);
				oos.flush();
			}
		}catch(Exception e) {
			System.out.println("클라이언트 강제 종료");
		}finally {
			try {
				if(is != null) is.close();
				if(ois != null) ois.close();
				if(os != null) os.close();
				if(oos != null) oos.close();
				if(child != null) child.close();
			}catch (IOException ie) {
				
			}
		}
	}

}// EchoServerThread class

public class MultiChatServer {

	public static void main(String[] args) {
		
	}

}
