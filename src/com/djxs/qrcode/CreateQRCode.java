package com.djxs.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode{

	public static void main(String[] args) throws IOException {
		int ver =20;
		//��������
		Qrcode qrcdoe = new Qrcode();
		qrcdoe.setQrcodeVersion(ver);
		qrcdoe.setQrcodeEncodeMode('B');
		int  imageSize = 67+12*(ver-1);
		//ͼƬ�������
		BufferedImage bufferedImage = new BufferedImage(imageSize, imageSize, BufferedImage.TYPE_INT_RGB);
		//��������
		Graphics2D gs = bufferedImage.createGraphics();
		//���ñ���ɫ
		gs.setBackground(Color.WHITE);
		//����ǰ��ɫ
		gs.setColor(Color.BLACK);
		//��ջ���
		gs.clearRect(0, 0, imageSize, imageSize);
		
		String str = "BEGIN:VCARD\r\n" +
			"PHOTO;VALUE=uri:http://img3.duitang.com/uploads/people/201407/24/20140724173921_NCPJL.jpeg\r\n "+ 
		   "FN:����:����\r\n"+
		   "ORG:ѧУ���ӱ��Ƽ�ʦ��ѧԺ	Ժϵ:��ѧ����Ϣ�Ƽ�ѧԺ\r\n"+
		   "TITLE:ѧ��\r\n" + 
		   "TEL;WORK;VOICE:5831801\r\n"+
		   "TEL;HOME;VOICE:15028568078\r\n"+
		   "TEL;CELL;VOICE:13315381643\r\n"+
		   "ADR;WORK:�ӱ��Ƽ�ʦ��ѧԺ\r\n"+
		   "ADR;HOME:�ӱ�����\r\n"+
		   "URL:http://www.baidu.com\r\n "+
		   	"EMAIL;HOME:2633396815@qq.com\r\n" + 
		   "END:VCARD";
		
		int startR = 255;
		int startG = 0;
		int startB = 0;
		

		int endR = 0;
		int endG = 0;
		int endB = 255;
		//�õ���λ����  ����ȷ����ά���Ǹ�������ɫ
		boolean[][] calQrcode = qrcdoe.calQrcode(str.getBytes("UTF-8"));
		
		System.out.println(calQrcode.length);
		int x = 2;
		for (int i = 0; i < calQrcode.length; i++) {
			for (int j = 0; j < calQrcode.length; j++) {
				if(calQrcode[i][j]){
					int num1 = startR + (endR - startR) * (i+1)/calQrcode.length;
					int num2 = startG + (endG - startG) * (i+1)/calQrcode.length;
					int num3 = startB + (endB - startB) * (i+1)/calQrcode.length;
					
					Color color = new Color(num1, num2, num3);
					
					gs.setColor(color);
					//�����ɫ
					gs.fillRect(i*3+x, j*3+x, 3, 3);
				}
			}
		}
		Image logo =ImageIO.read(new File("d:/logo.jpg"));
		int logoSize=20;
		int logoX= (imageSize-logoSize)/2;
		gs.drawImage(logo, logoX, logoX, logoSize, logoSize,null);
		
		gs.dispose();
		bufferedImage.flush();
		//�����ά��ͼƬ
		ImageIO.write(bufferedImage, "png", new File("d:/qrcode1.jpg"));
		System.out.println("ok");
	}
}