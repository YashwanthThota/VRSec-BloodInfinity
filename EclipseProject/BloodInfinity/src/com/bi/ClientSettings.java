package com.bi;

import java.net.InetAddress;

public class ClientSettings {

	public static String serverIp = "10.0.2.2";
	public static String serverPort = "8084";
	
	public static String project = "BloodInfinity";
	
	public static String un = "guest";
	public static String pw = "guest";
	public static String ut = "guest";	
	
	public static String httpServerUrl = "http://" + serverIp + ":"
			+ serverPort + "/"+project+"/";
	
}
