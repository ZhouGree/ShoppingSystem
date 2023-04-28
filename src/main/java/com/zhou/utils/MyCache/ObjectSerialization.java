package com.zhou.utils.MyCache;

import com.zhou.po.MyCache.MyCache;

import java.io.*;
import java.util.Map;

public class ObjectSerialization {
    public void Serialization(Map<String, MyCache> CaCheMap){

        try {
            String name = CaCheMap.keySet() + ".txt";
            // 创建一个输出流，将对象序列化到本地文件中
            FileOutputStream fileOut = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);

            // 将对象写入输出流中
            out.writeObject(CaCheMap.values());

            // 关闭输出流
            out.close();

            // 关闭文件输出流
            fileOut.close();

            System.out.println("对象已经成功序列化到本地文件中！");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void Read(Map<String, MyCache> CaCheMap){
        String fileName = CaCheMap.keySet() + ".txt";
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            // 从文件中读取对象
            Object object = objectInputStream.readObject();
            // 将对象转换为缓存对象
            MyCache cache = (MyCache) object;
            // 使用缓存对象
            // ...
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
