package org.coral.server.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.lang3.StringUtils;

import com.google.protobuf.ServiceException;

public class FileUtilitys {

    public static String ReadFile(String Path){
        BufferedReader reader = null;
        StringBuffer laststr = new StringBuffer();
        try{
            FileInputStream fileInputStream = new FileInputStream(Path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            
            while((tempString = reader.readLine()) != null){
                laststr.append(tempString);
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            if(reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return laststr.toString();
    }
    
    /**
     * 删除文件
     * @param filePathname 文件路径
     * @throws ServiceException 业务异常
     */
    public static void deleteFile(String filePathname) throws Exception
    {
        // 如果路径为空或空字符串，直接返回不做处理
        if (StringUtils.isBlank(filePathname)){
            return;
        }
        
        // 定义删除的文件
        File fileObject = new File(filePathname);
        // 如果文件不存在，直接返回
        if (!fileObject.exists() || !fileObject.isFile())
        {
            return;
        }
        
        // 如果删除文件失败，则抛出异常
        if (!fileObject.delete())
        {
            throw new ServiceException("");
        }
    }
}
