package com.aol.utils;

import org.openqa.selenium.By;

import com.aol.framework.WebDriver.WebDriverWrapper;
import com.aol.supportportal.ui.page.FullPage;

public class PageUtils extends FullPage{
	
	public PageUtils(WebDriverWrapper driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	
	public void downloadProduct()
	{
		
		driver.findElement(By.linkText("Download Now")).click();
	}
	
	public void activateProduct() 
	{
		
	}

}
