package com.dougnoel.sentinel.webdrivers;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.dougnoel.sentinel.configurations.Configuration;
import com.dougnoel.sentinel.enums.PageObjectType;
import com.dougnoel.sentinel.framework.PageManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import io.appium.java_client.windows.WindowsDriver;
import io.appium.java_client.windows.WindowsElement;

/**
 * Manages all drivers. Maintains as few drivers as possible for test execution.
 */
public class Driver {
	private static final Logger log = LogManager.getLogger(Driver.class);
	//Map each page object name to a driver, which may be the same driver
	private static EnumMap<PageObjectType, SentinelDriver> drivers = new EnumMap<> (PageObjectType.class);
	private static SentinelDriver currentDriver = null;
	
	private Driver() {
        // Exists only to defeat instantiation.
    }

    /**
     * Returns the WebDriver instance. This will silently instantiate the WebDriver if that has not been done yet.
     * 
     * @return WebDriver the created Selenium WebDriver
     */
    public static WebDriver getDriver() {
    	PageObjectType pageObjectType = PageManager.getPage().getPageObjectType();
    	if (pageObjectType == PageObjectType.EXECUTABLE) {
    		currentDriver = drivers.computeIfAbsent(pageObjectType, driver -> new SentinelDriver(WinAppDriverFactory.createWinAppDriver()));
    	}
    		else {
    		currentDriver = drivers.computeIfAbsent(pageObjectType, driver -> new SentinelDriver(WebDriverFactory.getWebDriver()));
        }
    	return currentDriver.getWebDriver();
    }

    /**
     * Quits all drivers and removes them from the list of active drivers.
     */
    public static void quitAll() {
    	drivers.forEach((driverType, driver) -> driver.quit());
    	drivers.clear();
    	currentDriver = null;
    }
    
    /**
     * Quit the current driver and remove all references of it.
     */
    public static void quit() {
    	//Search the map for the driver and delete all references to it
    	
    }
    
    /**
     * Closes the current window and moves the driver to the previous window. If no previous window exists,
     * we call close to clean up.
     */
    public static void close() {
    	//close the current SentinelDriver window which calls the WindowList
    }
    
    public static void goToNextWindow() {
    	
    }
    
    public static void goToPreviousWindow() {
    	
    }
    
	/**
	 * Emulate clicking the browser's forward button.
	 */
	public static void navigateForward() {
		currentDriver.navigate().forward();
	}

	/**
	 * Emulate clicking the browser's back button.
	 */
	public static void navigateBack() {
		currentDriver.navigate().back();
	}

	/**
	 * Emulate clicking the browser's refresh button.
	 */
	public static void refresh() {
		currentDriver.navigate().refresh();
	}
}