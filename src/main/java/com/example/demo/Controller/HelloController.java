package com.example.demo.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/test")
public class HelloController {
    @GetMapping("/hello")
    public Map hello(){
        Map<String,String> rtn=new HashMap<>();
        rtn.put("你好","hello update12222");
        return rtn;
    }

    @GetMapping("/downloadFile")
    public void downloadFile(@RequestParam String imageUrl, HttpServletResponse response) {
        BufferedOutputStream out = null;
        try {
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;filename="+"文件名.jpg");
            //文件URL中带有中文,进行编码,防止读取不到文件
            out = new BufferedOutputStream(response.getOutputStream());
            String path=encode("https://arc-1302652971.cos.ap-guangzhou.myqcloud.com//prod//20201112/e8c4af0e4bf4428682df883458761b98.png","UTF-8");
            URL url = new URL(path);
            BufferedImage bufferedImage= ImageIO.read(url);
            Graphics2D g2=bufferedImage.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            Font font=new Font("微软雅黑", Font.PLAIN,48);
            g2.setFont(font);
            //设置颜色
            Color color=new Color(0,0,0);
            g2.setColor(color);
            g2.drawString("陈海芳",450f,1000f);
            ImageIO.write(bufferedImage,"png",out);
            bufferedImage.flush();
            //创建一个Buffer字符串
            byte[] buffer = new byte[1048576];
            //每次读取的字符串长度，如果为-1，代表全部读取完毕
            int len = 0;
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            out.write(buffer, 0, len);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //路径中文编码转换
    public static String encode(String str, String charset) throws UnsupportedEncodingException {
        String zhPattern = "[\\u4e00-\\u9fa5]";
        Pattern p = Pattern.compile(zhPattern);
        Matcher m = p.matcher(str);
        StringBuffer b = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(b, URLEncoder.encode(m.group(0), charset));
        }
        m.appendTail(b);
        return b.toString();
    }
    public static void main(String[] args) {
        Map<String, Object> map=new HashMap<>();
        map.put("id","123");
        map.put("id","12345");
        System.out.println(map.get("id"));
    }
}
