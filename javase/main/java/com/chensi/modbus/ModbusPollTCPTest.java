package com.chensi.modbus;

import com.intelligt.modbus.jlibmodbus.Modbus;
import com.intelligt.modbus.jlibmodbus.master.ModbusMaster;
import com.intelligt.modbus.jlibmodbus.master.ModbusMasterFactory;
import com.intelligt.modbus.jlibmodbus.tcp.TcpParameters;
import org.junit.Test;

import java.net.InetAddress;

public class ModbusPollTCPTest {

    public static void main(String[] args) {
        try {
            // 设置主机TCP参数
            TcpParameters tcpParameters = new TcpParameters();

            // 设置TCP的ip地址
            InetAddress adress = InetAddress.getByName("127.0.0.1");

            // TCP参数设置ip地址
            // tcpParameters.setHost(InetAddress.getLocalHost());
            tcpParameters.setHost(adress);

            // TCP设置长连接
            tcpParameters.setKeepAlive(true);
            // TCP设置端口，这里设置是默认端口502
            tcpParameters.setPort(Modbus.TCP_PORT);


            // 创建一个主机
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);

            int slaveId = 1;//从机地址
            int offset = 0;//寄存器读取开始地址
            int quantity = 8;//读取的寄存器数量

            while (true) {
                try {

                    if (!master.isConnected()) {
                        master.connect();// 开启连接
                    }

                    // 读取对应从机的数据，readInputRegisters读取的写寄存器，功能码04
                    //int[] registerValues = master.readInputRegisters(slaveId, offset, quantity);
                    int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);
                    // 控制台输出
                    for (int value : registerValues) {
                        System.out.println(" Value: " + value);
                    }
                    System.out.println("----------------");
                    //修改3号位为7165
                    master.writeSingleRegister(slaveId, 2, 65535);
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 测试连接modbus rtu 协议解析
     */
    @Test
    public void test2() {
        try {
            // 设置主机TCP参数
            TcpParameters tcpParameters = new TcpParameters();

            // 设置TCP的ip地址
            InetAddress adress = InetAddress.getByName("127.0.0.1");

            // TCP参数设置ip地址
            tcpParameters.setHost(adress);

            // TCP设置长连接
            tcpParameters.setKeepAlive(true);
            // TCP设置端口，这里设置是默认端口502
            tcpParameters.setPort(Modbus.TCP_PORT);

            // 创建一个主机
            ModbusMaster master = ModbusMasterFactory.createModbusMasterTCP(tcpParameters);
            Modbus.setAutoIncrementTransactionId(true);

            int slaveId = 1;//从机地址
            int offset = 0;//线圈读取开始地址
            int quantity = 8;//读取的线圈数量

            while (true) {
                try {

                    if (!master.isConnected()) {
                        master.connect();// 开启连接
                    }

                    // 读取对应从机的数据，readInputRegisters读取的写寄存器，功能码04
                    //int[] registerValues = master.readInputRegisters(slaveId, offset, quantity);
                    int[] registerValues = master.readHoldingRegisters(slaveId, offset, quantity);
                    boolean[] resultForCoils = master.readCoils(slaveId, offset, quantity);

                    // 控制台输出
                    for (boolean value : resultForCoils) {
                        System.out.println(" Value: " + value);
                    }
                    System.out.println("----------------");
                    //修改3号位为7165
                    //master.writeSingleRegister(slaveId, 2, 65535);
                    Thread.sleep(1000);

                } catch (Exception e) {
                    e.printStackTrace();
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

