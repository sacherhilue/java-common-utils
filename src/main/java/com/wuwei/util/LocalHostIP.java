package com.wuwei.util;

import java.net.*;
import java.util.Enumeration;

/**
 * @author wuwei
 * @date 2018/10/18 11:19
 */
public class LocalHostIP {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        //获取本机ip地址
        getLocalHostIP();
        getAllLocalHostIP();
    }

    /**
     * 获取本机地址
     *
     * @throws UnknownHostException
     */
    public static void getLocalHostIP() throws UnknownHostException {
        String name = InetAddress.getLocalHost().getHostName();
        String host = InetAddress.getLocalHost().getHostAddress();
        System.out.println(String.format("本机名称：%s, 本机地址：%s", name, host));
    }

    /**
     * 获取本机所有的网络接口地址
     *
     * @throws SocketException
     */
    public static void getAllLocalHostIP() throws SocketException {
        //获得本机的所有网络接口
        Enumeration<NetworkInterface> nifs = NetworkInterface.getNetworkInterfaces();
        while (nifs.hasMoreElements()) {
            NetworkInterface nif = nifs.nextElement();
            //获得与该网络接口绑定的IP地址，一般只有一个
            Enumeration<InetAddress> addresses = nif.getInetAddresses();
            while (addresses.hasMoreElements()) {
                InetAddress address = addresses.nextElement();
                if (address instanceof Inet4Address) { //只关心IPv4地址
                    String name = nif.getName();
                    String ip = address.getHostAddress();
                    System.out.println(String.format("网卡接口名称：%s, 网卡接口地址：%s", name, ip));
                }
            }
        }
    }
}
