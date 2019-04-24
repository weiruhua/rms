package com.chinasofti.hotel.control;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ControlThread implements Runnable{
		//客户端对象
		private Socket client;
		//输入输出流
		private Object o;
	
		public ControlThread(Socket client,Object o){
			super();
			this.client=client;
			this.o=o;
			
			
		}
		public void run(){
			try {
				ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
				String methodName=ois.readUTF();
				Class<?>[] methodTypes=(Class<?>[])ois.readObject();
				Object[] methodParams=(Object[])ois.readObject();
				Method method=o.getClass().getMethod(methodName, methodTypes);
				Object result = method.invoke(o,methodParams);
				ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
				oos.writeObject(result);
				oos.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

		
		
	}
