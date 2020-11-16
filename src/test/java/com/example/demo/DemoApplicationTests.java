package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() throws IOException {
        URL url = new URL("https://oss.xiamentiyu.com/match/81ba2f453f6e373a95bea1056e35d962123.png1595500950246");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        File parent =new File("E:\\");
        File font2BImg=new File(parent,"test.jpg");
        File bg=new File(parent,"timg.jpg");
        //裁剪图片
        //BufferedImage bufferedImage= ImageIO.read(bg).getSubimage(100,160,1000,600);
        BufferedImage bufferedImage= ImageIO.read(url);
        Graphics2D g2=bufferedImage.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        Font font=new Font(Font.DIALOG_INPUT,Font.ITALIC,20);
        g2.setFont(font);
        g2.drawString("这里 在这里 11 aaa",100f,127f);
        g2.drawString("第二行",160f,200f);
        ImageIO.write(bufferedImage,"png",font2BImg);
        bufferedImage.flush();
    }
    @Test
    void testJSON(){
        String str="[{\"Cmdty_Ordr_No\":\"20101808361701166301\",\"Sub_Ordr_Id\":\"105000079411426101808363536447001\"}]";
        JSONArray jsonArray = JSONArray.parseArray(str);
        System.out.println(jsonArray.getJSONObject(0).getString("Sub_Ordr_Id"));
    }

    @Test
    void testData(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH点mm分ss秒");
        Long seconds=Long.valueOf("1604546546585");
        System.out.println(sdf.format(new Date(Long.valueOf(seconds))));
    }


    /**
     * 从网络Url中下载文件
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static void  downLoadFromUrl(String urlStr,String fileName,String savePath) throws IOException{
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(3*1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);

        //文件保存位置
        File saveDir = new File(savePath);
        if(!saveDir.exists()){
            saveDir.mkdir();
        }
        File file = new File(saveDir+File.separator+fileName);
        FileOutputStream fos = new FileOutputStream(file);
        fos.write(getData);
        if(fos!=null){
            fos.close();
        }
        if(inputStream!=null){
            inputStream.close();
        }


        System.out.println("info:"+url+" download success");

    }



    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static  byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
