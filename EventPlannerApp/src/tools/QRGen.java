/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import java.awt.image.BufferedImage;

/**
 *
 * @author Hendrik
 */
public class QRGen 
{
    /**
     * generates a qr code from a string
     * @param Content
     * @return BufferedImage QRCode
     */
    	public static BufferedImage generateQRCode(String Content)
	{
		BufferedImage out = null;
		
		try
		{
                    QRCodeWriter writer = new QRCodeWriter();
                    BitMatrix matrix = writer.encode(Content, BarcodeFormat.QR_CODE, 512, 512);
                    out = MatrixToImageWriter.toBufferedImage(matrix);
		}
		catch(Exception e)
		{
			
		}
		
		return out;
	}
}
