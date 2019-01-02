package operation;

import static org.testng.Assert.fail;

import java.awt.*;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import org.apache.commons.math3.random.RandomGenerator;
import org.junit.Test;
import org.junit.platform.engine.reporting.ReportEntry;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.opentest4j.TestAbortedException;
import org.testng.Reporter;
import org.testng.TestNG;

import com.sun.net.httpserver.Authenticator.Result;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import junit.framework.TestFailure;

public class UIOperation {

	WebDriver driver;
    public UIOperation(WebDriver driver){
        this.driver = driver;
    }
    public void perform(Properties p,String operation,String objectName,String objectType,String value) throws Exception{
        System.out.println("");
        
        switch (operation.toUpperCase()) {
        case "CLICK":
        	
            //Perform click
            driver.findElement(this.getObject(p,objectName,objectType)).click();
            
            break;
        case "SAVE":
        	
            //Perform click
            driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();
            
            break;
        case "SETTEXT":
        	
            //Set text on control
            driver.findElement(this.getObject(p,objectName,objectType)).sendKeys(value);
            
            break;
        case "GOTOURL":
        	
            //Get url of application
            driver.get(p.getProperty(value));
            
            break;
        case "GETTEXT":
            
        	//Get text of an element
            driver.findElement(this.getObject(p,objectName,objectType)).getText();
            
            break;
        case "SELECTCUSTOMER":
        	 
        	// Store all the elements of same category in the list of WebLements 
       	    List  oRadioButton = driver.findElements(By.name("optionsRadios"));
       	 
        	 // This will check that if the value equals Company AAA. If True then the first radio button is selected
        	 if(value.equals("Company AAA")){
        	 
        	 // This will select the First radio button        	 
        		 ((WebElement)oRadioButton.get(0)).click();
        	 
        	 // This will check that if the value equals Company BBB. If True then the second radio button is selected
        	 }else if (value.equals("Company BBB")){
        	 
        	 // This will select the Second radio button 	 
        	 ((WebElement) oRadioButton.get(1)).click();
        	 
        	 }
            break;
        case "SELECTROLE":
       	 
//        	//Store all the elements in the drop down in a variable
        	Select role = new Select(driver.findElement(this.getObject(p,objectName,objectType)));      	

//        	//Selecting the value from the data sheet
       	 	role.selectByVisibleText(value);         	    

            break;
	    	    
	    case "CHECKUSERNAME":
	    	String celtext = "";
	    	List  col = driver.findElements(By.xpath("//table[@columns='columnCollection']//tr//th"));
	         System.out.println("No of cols are : " +col.size()); 
	         
	         //No.of rows 
	         List  rows = driver.findElements(By.xpath("//table[@columns='columnCollection']//tr//td[1]")); 
	         System.out.println("No of rows are : " + rows.size());

	         WebElement line = driver.findElement(By.xpath("//table[@columns='columnCollection']//tr//td[3]"));
	         line.getText();
	         System.out.println(line.getText());
	         
	         WebElement mytable = driver.findElement(By.xpath("/html/body/table/tbody"));
		    	
	         //To locate rows of table. 
		    	List < WebElement > rows_table = mytable.findElements(By.tagName("tr"));
		    	
		    	//To calculate no of rows In table.		    	
		    	int rows_count = rows_table.size();
		    	
		    	//Loop will execute till the last row of table.
		    	for (int row = 0; row < rows_count; row++) {
		    	  
		    		//To locate columns(cells) of that specific row.
		    	    List < WebElement > Columns_row = rows_table.get(row).findElements(By.tagName("td"));
		    	   
		    	    //To calculate no of columns (cells). In that specific row.
		    	    int columns_count = Columns_row.size();
		    	    System.out.println("Number of cells In Row " + row + " are " + columns_count);
		    	    
		    	    //Loop will execute till the last cell of that specific row.
		    	    for (int column = 0; column < columns_count; column++) {

		    	    	// To retrieve text from that specific cell.
		    	        celtext = Columns_row.get(column).getText();
		    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
		    	        
		    	        //Validates to see if Username already exists
		    	        if (celtext.equals(value)){
		    	        	System.out.println("User name is not unique");
                           fail("Username is not unique");		  
		    	        	break;		    	        }
		    	    }
		    	    
		    	}
		    	    System.out.println("-------------------------------------------------- ");
		    break;		    
        
	    case "CLEAR":
          	 
        	//Clears all textfields
        	driver.findElement(By.name("FirstName")).clear();
        	driver.findElement(By.name("LastName")).clear();
        	driver.findElement(By.name("UserName")).clear();
        	driver.findElement(By.name("Password")).clear();
        	driver.findElement(By.name("Email")).clear();
        	driver.findElement(By.name("Mobilephone")).clear();
        	
            break;
        
	    case "VERIFYUSER":
	    	String strCelText = "";
	    	List  strCListol = driver.findElements(By.xpath("//table[@columns='columnCollection']//tr//th"));
	         System.out.println("No of cols are : " +strCListol.size()); 
	         
	         //No.of rows 
	         List  strRowsList = driver.findElements(By.xpath("//table[@columns='columnCollection']//tr//td[1]")); 
	         System.out.println("No of rows are : " + strRowsList.size());

	         WebElement newline = driver.findElement(By.xpath("//table[@columns='columnCollection']//tr//td[3]"));
	         newline.getText();
	         System.out.println(newline.getText());
	         
	         WebElement table = driver.findElement(By.xpath("/html/body/table/tbody"));
		    	
	         //To locate rows of table. 
		    	List < WebElement > rowTable = table.findElements(By.tagName("tr"));
		    	
		    	//To calculate no of rows In table.		    	
		    	int rowCount = rowTable.size();
		    	
		    	//Loop will execute till the last row of table.
		    	for (int row = 0; row < rowCount; row++) {
		    	  
		    		//To locate columns(cells) of that specific row.
		    	    List < WebElement > Columns_row = rowTable.get(row).findElements(By.tagName("td"));
		    	   
		    	    //To calculate no of columns (cells). In that specific row.
		    	    int columns_count = Columns_row.size();
		    	    System.out.println("Number of cells In Row " + row + " are " + columns_count);
		    	    
		    	    //Loop will execute till the last cell of that specific row.
		    	    for (int column = 0; column < columns_count; column++) {

		    	    	// To retrieve text from that specific cell.
		    	        celtext = Columns_row.get(column).getText();
		    	        System.out.println("Cell Value of row number " + row + " and column number " + column + " Is " + celtext);
		    	        
		    	        //Validates to see if Username already exists
		    	        if (celtext.equals(value)){
		    	        	System.out.println("User created");
		    	        	
		    	        	Reporter.log(value + "Exists");
		    	        	break;		    	        }
		    	    }
		    	    
		    	}
		    	    System.out.println("-------------------------------------------------- ");
		    break;
        default:
            break;
        }
    }
    
    /**
     * Find element BY using object type and value
     * @param p
     * @param objectName
     * @param objectType
     * @return
     * @throws Exception
     */
    private By getObject(Properties p,String objectName,String objectType) throws Exception{
        //Find by xpath
        if(objectType.equalsIgnoreCase("XPATH")){
            
            return By.xpath(p.getProperty(objectName));
        }
        //find by class
        else if(objectType.equalsIgnoreCase("CLASSNAME")){
            
            return By.className(p.getProperty(objectName));
            
        }
        //find by name
        else if(objectType.equalsIgnoreCase("NAME")){
            
            return By.name(p.getProperty(objectName));
            
        }
        //Find by css
        else if(objectType.equalsIgnoreCase("CSS")){
            
            return By.cssSelector(p.getProperty(objectName));
            
        }
        //find by link
        else if(objectType.equalsIgnoreCase("LINK")){
            
            return By.linkText(p.getProperty(objectName));
            
        }
        //find by partial link
        else if(objectType.equalsIgnoreCase("PARTIALLINK")){
            
            return By.partialLinkText(p.getProperty(objectName));
            
        }else
        {
            throw new Exception("Wrong object type");
        }
    }
    
}
