/**Decompiled by DJ v3.12.12.98 Copyright 2014 Atanas Neshkov  Date: 27-09-2014 22:11:39
 * Home Page:  http://www.neshkov.com/dj.html - Check often for new version!
 * Decompiler options: packimports(3) 
 * Source File Name:   StrUtil.java **/

package com.plash.configurator.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// TODO: Auto-generated Javadoc

/**
 * The Class StrUtil.
 *
 * @author Satish
 */

public class StrUtil  {

	public static String[] nonNull(String str[]) {
		return str != null ? str : new String[0];
	}

	/**
	 * Generate date in random number format
	 * This method generates Hash of given date
	 * @return the date
	 */
	public Date generateDate() {
		long offset = Timestamp.valueOf("2015-09-01 00:00:00").getTime();
		long end = Timestamp.valueOf("2015-09-30 00:00:00").getTime();
		long diff = end - offset + 1;
		Timestamp rand = new Timestamp(offset + (long) (Math.random() * diff));

		return rand;
	}

	/**
	 * Generate decremented dates.
	 *
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public List generateDecrementedDates() throws Exception {
		Date enddate = StrUtil.getDate("2015-09-30", "yyyy-MM-dd");

		Date startdate = StrUtil.getDate("2015-06-01", "yyyy-MM-dd");
		List<Date> dates = new ArrayList<Date>();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(startdate);

		while (calendar.getTime().before(enddate)) {
			Date result = calendar.getTime();
			dates.add(result);
			calendar.add(Calendar.DATE, 1);
		}
		return dates;
	}

	/**
	 * Gets the random index.
	 *
	 * @param arrayLength
	 *            the array length
	 * @return the random index
	 */
	public int getRandomIndex(int arrayLength) {

		Random random = new Random();

		int min = 0;
		int max = arrayLength - 1;
		int randomNumber = random.nextInt((max - min) + 1) + min;

		return randomNumber;
	}

	/**
	 * Gets the random int.
	 *
	 * @param max
	 *            the max integer value
	 * @param min
	 *            the min integer value
	 * @return the random integer
	 */
	public int getRandomInt(int max, int min) {
		// variable so that it is not re-seeded every call.
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	/**
	 * Replace double quote.
	 *
	 * @param temp
	 *            the temp - String that wants to replace double quote
	 * @return the string
	 */
	public static String replaceDoubleQuote(String temp) {
		temp = temp.replaceAll("\"", "");
		return temp;
	}

	/**
	 * Replace unicode.
	 *
	 * @param data
	 *            the data - Input String 
	 * @return the string
	 */
	public static String replaceUnicode(String data) {
		data = data.replaceAll("\u003c", "<");
		data = data.replaceAll("\u003e", ">");
		data = data.replaceAll("\u0027", "'");
		data = data.replaceAll("\u003d", "=");

		return data;
	}

	/**
	 * Non null.
	 *
	 * @param s
	 *            the Input String 
	 * @return the string - which is not null
	 */
	public static String nonNull(String s) {
		return s != null ? s : "";
	}

	/**
	 * Clean.
	 *
	 * @param source
	 *            the source - Input String
	 * @return the string - cleaned all Special Characters
	 */
	public static String clean(String source) {
		source = Jsoup.parse(source).text();
		// source = source.replaceAll("\\P{Alnum}", "");
		source = source.replaceAll("[^A-Za-z0-9.,!? ]", "");
		source = source.trim();
		// System.out.println(source);
		return source;
	}

	/**
	 * Gets the thumb.
	 *
	 * @param webUrl
	 *            the web url
	 * @param description
	 *            the description
	 * @return the thumb
	 * @throws Exception
	 *             the exception
	 */
	public static String getThumb(String webUrl, String description)
			throws Exception {

		Document doc1 = null;

		/**
		 * From following I changed the code. It may be the old code so please
		 * have look...
		 */
		String imageUrl = null;
//System.out.println("description for image extraction "+description);
		try {
			doc1 = Jsoup.parse(description);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		Elements ele1 = doc1.getElementsByTag("img");
//System.out.println("image from desc:"+ele1.toString());
		// boolean bd = ele1.get(0).text().isEmpty();
		// System.out.println(doc.html()+"   size "+ele1.size()
		// +" img "+ele1.get(0).attr("src"));

		if (ele1.size() != 0) {
			// Element ele2 = doc.select("img").get(1);

			imageUrl = ele1.get(0).attr("src");
			//System.out.println("Src: "+imageUrl);
			// System.out.println("image "+ele1.get(0).attr("src"));
			return imageUrl;
		}

		/**
		 * following code in else statement is the same old code
		 */

		else if(!webUrl.equals("") && webUrl.contains("//")) {
			Document doc = null;
			try {
				// System.out.println(webUrl);
				doc = Jsoup
						.connect(webUrl)
						.userAgent(
								"Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
						.referrer("http://www.google.com").timeout(10000).get();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			//System.out.println(doc.html());
			Elements metaOgImage = null;
			/*
			 * if(doc!=null){ metaOgImage =
			 * doc.select("meta[property=og:image]"); if (metaOgImage!=null) {
			 * imageUrl = StrUtil.nonNull(metaOgImage.attr("content"));
			 * if(!"".equalsIgnoreCase(imageUrl)){ System.out.println("1");
			 * return imageUrl; } } }
			 */
			Elements elements = null;
			try {
				elements = doc.getElementsByTag("img");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			String retStr = "";
			if (elements != null) {

				if (elements.size() > 1) {
					try {
						// System.out.println("2");
						retStr = elements.get(1).attr("src");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					return retStr;
				} else {
					try {
						// System.out.println("3");
						retStr = elements.get(0).attr("src");
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
					return retStr;
				}
			} else if (doc != null) {
				metaOgImage = doc.select("meta[property=og:image]");
				if (metaOgImage != null) {
					imageUrl = StrUtil.nonNull(metaOgImage.attr("content"));
					if (!"".equalsIgnoreCase(imageUrl)) {
						// System.out.println("1");
						return imageUrl;
					}
				}
			}
		}

		return "";
	}


	/**
	 * Gets the image urls.
	 *
	 * @param description
	 *            the description
	 * @return the image urls
	 * @throws Exception
	 *             the exception
	 */
	public static List getImageUrls(String description)
			throws Exception {

		Document doc1 = null;
		String imageUrl = null;		
		try {
			doc1 = Jsoup.parse(description);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}

		Elements ele1 = doc1.getElementsByTag("img");
		List imglist = new ArrayList();
		if (ele1 != null) {

			for (Element ele : ele1) {
				String img = ele.attr("src");
				imglist.add(img);
			}
			//System.out.println("image url list"+imglist);
			
		}

		return imglist;
	}
	
	/**
	 * Gets the unique id.
	 *
	 * @return the unique id
	 */
	public static String getUniqueId() {
		UUID idOne = UUID.randomUUID();
		String temp = String.valueOf(idOne);
		if (temp.indexOf("-") > 0) {
			temp = temp.substring(0, temp.indexOf("-"));
		}
		return temp;
	}

	/**
	 * Gets the unique identifier.
	 *
	 * @return the unique identifier
	 */
	public static String getUniqueIdentifier() {
		UUID idOne = UUID.randomUUID();
		String temp = String.valueOf(idOne);
		return temp;
	}

	/**
	 * Gets the unique identifier pay U.
	 *
	 * @return the unique identifier pay U
	 */
	public static String getUniqueIdentifierPayU() {
		UUID idOne = UUID.randomUUID();
		String temp = String.valueOf(idOne);
		/*if (temp.length() > 30) {
			temp = temp.substring(0, 30);
		}*/
		if (temp.length() > 18) {
			temp = temp.substring(0, 18);
		}
		return temp;
	}

	/**
	 * De slash.
	 *
	 * @param str
	 *            the Input String
	 * @return the string - with escape slash
	 */
	public static String deSlash(String str) {
		str = str.replaceAll("\\/", "");
		return str;
	}

	/**
	 * Random value.
	 *
	 * @return the integer - Random number
	 */
	public static Integer randomValue() {
		Random random = new Random();
		Integer retInt = new Integer(0);
		if (random.nextBoolean()) {
			retInt = new Integer(1);
		}
		return retInt;
	}

	/**
	 * Gets the date.
	 *
	 * @param datestr
	 *            the datestr
	 * @param format
	 *            the format
	 * @return the date
	 * @throws Exception
	 *             the exception
	 */
	public static Date getDate(String datestr, String format) throws Exception {
		Date dt = null;
		try {
			dt = (new SimpleDateFormat(format)).parse(datestr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dt;
	}
	
	/**
	 * Gets the percentage.
	 *
	 * @param unitprice
	 *            the unitprice
	 * @param quantity
	 *            the quantity
	 * @param percent
	 *            the percent
	 * @return the percentage
	 */
	public static Double getPercentage(Double unitprice, int quantity,
			Double percent) {
		Double total = unitprice.doubleValue() * quantity;
		Double finalprice = total * (1 - (percent / 100));
		return finalprice;
	}



	/**
	 * Main old.
	 *
	 * @param args
	 *            the args
	 */
	public static void mainOld(String args[]) {
		Double d = new Double("20.00");
		Double percent = new Double(20);
		int quantity = 12;

		Double total = d.doubleValue() * quantity;
		Double finalprice = total * (1 - (percent / 100));

		System.out.println(finalprice);
	}

	/**
	 * Main just old.
	 *
	 * @param args
	 *            the args
	 */
	/*public static void mainJustOld(String args[]) {
		String temp = FileUtils.getFile(new File("C:/home/trunextapplereceipt/receipt.txt"));
		//System.out.println(temp);
		byte[] encodedBytes = Base64.encodeBase64(temp.getBytes());
		System.out.println("encodedBytes " + new String(encodedBytes));
		byte[] decodedBytes = Base64.decodeBase64(encodedBytes);
		System.out.println("decodedBytes " + new String(decodedBytes));
	}*/
	public static void main(String args[]) throws Exception {
		String	str1="";
		/*File file = new File("D:/test.txt");
		
		try {
		str1 = TestImgDowProc.deserializeString(file);
			//System.out.println("=="+str1);
		} catch (IOException e) {

			e.printStackTrace();https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html
		}*/
	/*	String s ="<h1><!--block-->A JSON event-based convention for WebSockets</h1><div><!--block--><br><a href=\"https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html\">https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html</a><br><br></div><h1><!--block-->A JSON event-based convention<a href=\"https://sk.spring.io/spring/docs/current/html/websocket.html\">https://sk.spring.io/spring/docs//html/websocket.html</a> for WebSockets</h1>";
		String d = "https://docs.spring.io/spring/docs/current/spring-framework-reference/html/websocket.html";
		String db = "https://accounts.alore.io:8080/t/e/1492686285561/1492403205344/1494021652115/1493112756234";
		//List l = extractHrefUrlsReturnList(d);
		Document doc = null;
		doc = Jsoup.parse(s, "", Parser.xmlParser());
		doc.select("img").remove();
		Elements element = doc.getElementsByTag("a");
		//String f =doc.getElementsByAttributeValueMatching("href",d).text();
		//String res = s.replace(doc.getElementsByAttributeValueMatching("href",d).text(),"dddffdfdf");
		//element = element.get(0).absUrl("href").replace(element.get(0).absUrl("href"),"dsafaf");
		String re= "href=\""+d+"\"";
		//System.out.println("re: "+re);
		String nel = "href=\""+db+"\"";
		//System.out.println("db: "+nel);
		s= s.replaceAll(re,nel);*/
		//System.out.println("doc-"+s);

		//System.out.println(getUrlDomainName("accounts.alore.io"));


		/*String temp1 = FileUtils.getFile(new File("C:/home/plash/inapp/1.txt"));
		String temp2 = FileUtils.getFile(new File("C:/home/plash/inapp/2.txt"));
		temp1 = temp1.trim();
		temp2 = temp2.trim();
		if(temp1.equalsIgnoreCase(temp2)){
			System.out.println("Match");
		}else{
			System.out.println("Mismatch");
		}		*/

		String s = "<!--block--><br>•&nbsp; &nbsp; The first impression- Subject lines and first introductory paragraphs<br>•&nbsp; &nbsp; Elements that must be focused for reply-worthy emails.<br>•&nbsp; &nbsp; Timing it right - best time to send emails.<br>•&nbsp; &nbsp; Preparing for the calls.<br>•&nbsp; &nbsp; Ways to engage on follow ups.<br><br><br><br><br><br><br>\n" +
				"</div>\n" +
				"<ul>\n" +
				"   <li>\n" +
				"      <!--block-->Automatically select the user's current country using an IP lookup\n" +
				"   </li>";
		System.out.println("llllllll: "+cleanJunkCharacters(s));
	}
	
	/**
	 * Isemail.
	 *
	 * @param str
	 *            the Input String
	 * @return the boolean - True if Email otherwise False
	 */
	public static Boolean isemail(String str) {		
		
		boolean email= str.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\" +
				".[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$");		
		
        return email;
    }
	
	/**
	 * Checks if is mobile number.
	 *
	 * @param mobileNo
	 *            the mobile no
	 * @return the string - Mobile number
	 */
	public static String isMobileNumber(String mobileNo) {
		  
		  String mobNo = mobileNo;		
		  String regex = "((\\+*)((0[ -]+)*|(91 )*)(\\d{12}+|\\d{10}+))|\\d{5}([- ]*)\\d{6}";
		  
		  //checking whether mobile number starts with "0"
		  if(mobNo.startsWith("0"))
		   mobNo=mobNo.substring(0, mobNo.length());
		  //checking whether mobile number starts with "+91"
		  else if(mobNo.startsWith("+"))
		   mobNo=mobNo.substring(3, mobNo.length());
		  if(mobNo.matches(regex))
		  {
			  if(mobNo.length()>10){
				  System.out.println("invalid mobile number");
				   return mobNo="invalid";
			  }else{
		   System.out.println("valid mobile number"+mobNo);
		   return mobNo;
			  }
		  }
		  else
		  {
			  System.out.println("invalid mobile number");
		   return mobNo="invalid";
		   }
		
	}

	public static Double removeDecimalExtras(Double d, int decimalcount){
		//Three(3) decimals for UAE
		DecimalFormat df = null;
		if(!d.isNaN()){
			if(decimalcount==3){
				df = new DecimalFormat("###.###");
			}else{
				df = new DecimalFormat("###.##");
			}

			Double retD =  new Double(0);
			try {
				//	System.out.println("retD "+d);
				retD = new Double((df.format(d)));

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("Error in removing extra decimals from Double");
				System.out.println(e.getMessage());
			}
			return retD;
		}
		return d;
	}

	
	/**
	 * Returns a list with all links contained in the input.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 */
	public static List<String> extractUrls(String text) {
		List<String> containedUrls = new ArrayList<String>();
		String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
		Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
		Matcher urlMatcher = pattern.matcher(text);

		while (urlMatcher.find()) {
			containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
		}

		return containedUrls;
	}

	public static String extractHrefUrls(String text) {
		List<String> containedUrls = new ArrayList<String>();
		Document doc = null;
		doc = Jsoup.parse(text, "", Parser.xmlParser());
		doc.select("img").remove();
		Elements element = doc.getElementsByTag("a");
		return element.toString();
	}

	public static List<String> extractHrefUrlsReturnList(String text) {
		List<String> containedUrls = new ArrayList<>();
		Document doc;
		doc = Jsoup.parse(text, "", Parser.xmlParser());
		doc.select("img").remove();
		Elements element = doc.getElementsByTag("a");
		for (Element anElement : element) {
			String d = anElement.attr("href");
			containedUrls.add(d);
		}
		return containedUrls;
	}

	public static Map<String,String> extractHrefUrlsReturnMap(String text) {
		Map<String,String> map=new HashMap<>();
		Document doc;
		doc = Jsoup.parse(text, "", Parser.xmlParser());
		doc.select("img").remove();
		Elements element = doc.getElementsByTag("a");
		for (Element anElement : element) {
			String url = anElement.attr("href");
            String anchr=anElement.text();
			map.put(url,anchr);
		}
		return map;
	}

	/**
	 * Returns a list with all Emails contained in the input String.
	 *
	 * @param text
	 *            the text
	 * @return the list
	 */ 
	public static List<String> extractEmails(String text) {
		List<String> containedEmails = new ArrayList<String>();
		 Matcher m = Pattern.compile("[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+").matcher(text);
		    
		    while (m.find()) {
		        //System.out.println(m.group());
		    	containedEmails.add(m.group());
		    }
		    //System.out.println(containedEmails);
		return containedEmails;
	}

/*

	public static String getCurrentReply(String msgcontent) {

		String body = "";
		Document doc = null;
		doc = Jsoup.parse(msgcontent, "", Parser.xmlParser());
		body = EmailBodyParser.extractbody(doc,SAVETODB);
		return  body;
	}
*/


	/**
	 * Gets the url domain name.
	 *
	 * @param url
	 *            the url
	 * @return the url domain name
	 */
	public static String getUrlDomainName(String url) {
		String domainName = new String(url);

		int index = domainName.indexOf("://");

		if (index != -1) {
			// keep everything after the "://"
			domainName = domainName.substring(index + 3);
		}
		index = domainName.indexOf('/');
		if (index != -1) {
			// keep everything before the '/'
			domainName = domainName.substring(0, index);

		}
		// check for and remove a preceding 'www'
		// followed by a '.'
		domainName = domainName.replaceFirst("^www.*?\\.", "");

		return domainName;
	}
	public static String[] getStringArray(String str){
		str = str.replace("[", "");
		str = str.replace("]", "");
		String[] strarray = str.split(",");
		return strarray;
	}

	public static String replaceRepliedfromSubject(String subject) {

		try {
			subject = subject.replaceAll("Re:", "");
			subject = subject.replaceAll("RE:", "");
			subject = subject.replaceAll("Re: ", "");
			subject = subject.replaceAll("RE: ", "");
			subject = subject.trim();
		} catch (Exception e) {
			e.printStackTrace();
			return subject;
		}
		return subject;

	}

	/**
	 * Clean junk characters.
	 *
	 * @param html
	 *            the html input
	 * @return the string - with cleaned junk character
	 */
	public static String cleanJunkCharacters(String html)
	{

		//html = html.replaceAll("&#039;", "'");
		html = html.replaceAll("’", "'");
		html = html.replaceAll("’", "'");
		html = html.replaceAll(" ", " ");
		//html = html.replaceAll("’", "&rsquo;");
		//html = html.replaceAll("“", "&#8220;");
		//html = html.replaceAll("”", "&#8221;");
		//html = html.replaceAll("‘", "&lsquo;");
		html = html.replaceAll("’", "'");
		//html = html.replaceAll("â��", "");
		//html = html.replaceAll("â", "");

		//html = html.replaceAll("—", "&#8211;");
		//html = html.replaceAll("…", "&#8230;");
		//html = html.replaceAll("–", "&#8212;");
		html = html.replaceAll("•", "&#8226");
		html = html.replaceAll("…", "...");

		html = html.replaceAll("“", "\"");
		html = html.replaceAll("”", "\"");
		html = html.replaceAll("–", "-");
		html = html.replaceAll("—", "-");

		html = html.replaceAll("&nbsp;", " ");
		html = html.replaceAll("\u00A0;", " ");
		html = html.replaceAll(";", ";");
		html = html.replaceAll(";", ";");
		html = html.replaceAll("Ã¼", "A");
		html = html.replaceAll("Ã©", "A");
		html = html.replaceAll("Ã", "A");
		html = html.replaceAll("ﬁ", "fi");
		return html;
	}

	public static String computeSignature(String baseString, String keyString) throws Exception {
		SecretKey secretKey = null;
		byte[] keyBytes = keyString.getBytes();
		secretKey = new SecretKeySpec(keyBytes, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secretKey);
		byte[] text = baseString.getBytes();
		String sign= Base64.getUrlEncoder().encodeToString(mac.doFinal(text));
		return sign.replace("=","%3D");
	}



}
