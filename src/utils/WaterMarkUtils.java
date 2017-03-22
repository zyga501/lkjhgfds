package utils;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

public class WaterMarkUtils {
    private static void createMark(String souchFilePath, String targetFilePath,
                                  String markContent, Color markContentColor, float qualNum,
                                  String fontType, int fontSize, Color color) {
        markContentColor = color;
                          /* 构建要处理的源图片 */
        ImageIcon imageIcon = new ImageIcon(souchFilePath);
                          /* 获取要处理的图片 */
        Image image = imageIcon.getImage();
        // Image可以获得图片的属性信息
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        // 为画出与源图片的相同大小的图片（可以自己定义）
        BufferedImage bImage = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        // 构建2D画笔
        Graphics2D g = bImage.createGraphics();
                          /* 设置2D画笔的画出的文字颜色 */
        g.setColor(markContentColor);
                          /* 设置2D画笔的画出的文字背景色 */
        g.setBackground(Color.white);
                          /* 画出图片 */
        g.drawImage(image, 0, 0, null);
                          /* --------对要显示的文字进行处理-------------- */
        AttributedString ats = new AttributedString(markContent);
        Font font = new Font(fontType, Font.BOLD, fontSize);
        g.setFont(font);
                         /* 消除java.awt.Font字体的锯齿 */
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
                          /* 消除java.awt.Font字体的锯齿 */
        // font = g.getFont().deriveFont(30.0f);
        ats.addAttribute(TextAttribute.FONT, font, 0, markContent.length());
        AttributedCharacterIterator iter = ats.getIterator();
                          /* 添加水印的文字和设置水印文字出现的内容 ----位置 */
        FontMetrics fm = sun.font.FontDesignMetrics.getMetrics(font);
        int ww = fm.stringWidth(markContent);
        int hh = fm.getHeight();
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP,
                qualNum));
        g.drawString(iter, width>ww?(width-ww) / 2:0, height>hh?(height -hh )/ 2:0);
                          /* --------对要显示的文字进行处理-------------- */
        g.dispose();// 画笔结束
        try {
            // 输出 文件 到指定的路径
            FileOutputStream out = new FileOutputStream(targetFilePath);
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
            JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(bImage);
          //  param.setQuality(qualNum, true);
            encoder.encode(bImage, param);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void  markPic(String oldf,String newf,String title){
        new WaterMarkUtils().createMark(oldf,newf,
                title, Color.white,0.5f, "华文琥珀", 50,
                Color.GRAY);
    }
//    public static void main(String[] args) {
//       // new WaterMarkUtils().mark("D:\\unpic\\25_1_0.jpg", "d:/unpic/afterWatermark.jpg", Color.red, "水印效果测试");
//        new WaterMarkUtils().markPic("D:\\unpic\\25_1_0.jpg", "d:/unpic/afterWatermark.jpg",
//                "这是用java程序给图片添加的文字水印");
//    }
}
