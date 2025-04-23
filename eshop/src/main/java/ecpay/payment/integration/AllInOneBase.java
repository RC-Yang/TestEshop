package ecpay.payment.integration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import ecpay.payment.integration.ecpayOperator.EcpayFunction;
import ecpay.payment.integration.errorMsg.ErrorMessage;
import ecpay.payment.integration.exception.EcpayException;

public class AllInOneBase {
	protected static String operatingMode;
	protected static String mercProfile;
	protected static String isProjectContractor;
	protected static String HashKey;
	protected static String HashIV;
	protected static String MerchantID;
	protected static String PlatformID;
	protected static String aioCheckOutUrl;
	protected static String doActionUrl;
	protected static String queryCreditTradeUrl;
	protected static String queryTradeInfoUrl;
	protected static String queryTradeUrl;
	protected static String tradeNoAioUrl;
	protected static String fundingReconDetailUrl;
	protected static String createServerOrderUrl;
	protected static Document verifyDoc;
	protected static String[] ignorePayment;
	public AllInOneBase() throws IOException{
//		try{
			Document doc;
			/* when using web project*/
//			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//			String configPath = URLDecoder.decode(classLoader.getResource("/payment_conf.xml").getPath(), "UTF-8");
//			doc = EcpayFunction.xmlParser(configPath);
			/* when using testing code*/
			//String paymentConfPath = "./src/main/resources/payment_conf.xml";
			//doc = EcpayFunction.xmlParser(paymentConfPath);
			
			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("payment_conf.xml");

			if (inputStream == null) {
			    throw new FileNotFoundException("找不到 payment_conf.xml，請確認已放入 src/main/resources/");
			}

			// 寫入一個臨時檔案
			File tempFile = File.createTempFile("payment_conf", ".xml");
			tempFile.deleteOnExit(); // 程式結束後刪除

			try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
			    byte[] buffer = new byte[1024];
			    int bytesRead;
			    while ((bytesRead = inputStream.read(buffer)) != -1) {
			        outputStream.write(buffer, 0, bytesRead);
			    }
			}

			// 傳遞實體檔案路徑字串給綠界 SDK
			doc = EcpayFunction.xmlParser(tempFile.getAbsolutePath());

			
			doc.getDocumentElement().normalize();
			//OperatingMode
			Element ele = (Element)doc.getElementsByTagName("OperatingMode").item(0);
			operatingMode = ele.getTextContent();
			//MercProfile
			ele = (Element)doc.getElementsByTagName("MercProfile").item(0);
			mercProfile = ele.getTextContent();
			//IsProjectContractor
			ele = (Element)doc.getElementsByTagName("IsProjectContractor").item(0);
			isProjectContractor = ele.getTextContent();
			//MID, HashKey, HashIV, PlatformID
			NodeList nodeList = doc.getElementsByTagName("MInfo");
			for(int i = 0; i < nodeList.getLength(); i++){
				ele = (Element)nodeList.item(i);
				if(ele.getAttribute("name").equalsIgnoreCase(mercProfile)){
					MerchantID = ele.getElementsByTagName("MerchantID").item(0).getTextContent();
					HashKey = ele.getElementsByTagName("HashKey").item(0).getTextContent();
					HashIV = ele.getElementsByTagName("HashIV").item(0).getTextContent();
					PlatformID = isProjectContractor.equalsIgnoreCase("N")? "" : MerchantID;
				}
			}
			// IgnorePayment
			ele = (Element)doc.getElementsByTagName("IgnorePayment").item(0);
			nodeList = ele.getElementsByTagName("Method");
			ignorePayment = new String[nodeList.getLength()];
			for(int i = 0; i < nodeList.getLength(); i++){
				ignorePayment[i] = nodeList.item(i).getTextContent();
			}
			if(HashKey == null){
				throw new EcpayException(ErrorMessage.MInfo_NOT_SETTING);
			}
//		} catch(UnsupportedEncodingException e){
//			e.printStackTrace();
//		}
	}
}
