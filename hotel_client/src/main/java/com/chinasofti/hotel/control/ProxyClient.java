package com.chinasofti.hotel.control;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

public class ProxyClient {
	public static<T>T getClient(Class<T>clazz,final String ip,final int port){
		return (T) Proxy.newProxyInstance(ProxyClient.class.getClassLoader(), new Class[]{clazz}, new InvocationHandler() {
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				Socket client=new Socket(ip,port);
				ObjectOutputStream oos=new ObjectOutputStream(client.getOutputStream());
				oos.writeUTF(method.getName());
				oos.flush();
				oos.writeObject(method.getParameterTypes());
				oos.flush();
				oos.writeObject(args);
				oos.flush();
				ObjectInputStream ois=new ObjectInputStream(client.getInputStream());
				return ois.readObject();
			}
		});
	}
}
