package com.brokenlinks.net;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;



public class BrokenLinkAndImageDemo {

	public static void main(String[] args) throws IOException {
		
		WebDriver driver;
		
		driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20000, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(200000, TimeUnit.SECONDS);
		
		//GET URL 
		
		//driver.get("https://makemysushi.com/Recipes/how-to-make-california-sushi-rolls");
		
		//links ==a
		//images ==img
		
		//get the list of all the links and images 
		List<WebElement> list =driver.findElements(By.tagName("a"));
		list.addAll(driver.findElements(By.tagName("img")));
		
		System.out.println("total active links and images :: > " + list.size());
		
		
		List<WebElement> activelinks = new ArrayList<WebElement>();
		
		//iterate list 
		
		for(int i =0; i <list.size(); i++)
		{
			System.out.println(list.get(i).getAttribute("href"));
			if(list.get(i).getAttribute("href") != null  && (! list.get(i).getAttribute("href").contains("javascript")))
			{
				activelinks.add(list.get(i)); //javascript null
			}
		}
		
		//get the size of activer link 
		
		System.out.println("size of active link and images ::>" +activelinks.size() );
		
		for (int j=0; j<activelinks.size(); j++)
		{
			URL url = new URL(activelinks.get(j).getAttribute("href"));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.connect();
			String response =conn.getResponseMessage();
			conn.disconnect();
			
			System.out.println(activelinks.get(j).getAttribute("href") + "---->" +response);
			
		}

	}

}
