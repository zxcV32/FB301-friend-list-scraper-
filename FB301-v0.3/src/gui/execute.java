package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class execute extends JFrame {

	private JPanel contentPane;
	static ChromeDriver driver;
	JTextArea textArea;
	private JButton btnStopAndClose;
	static boolean stop=false;
	private JLabel lblCopyright;
	/**
	 * Create the frame.
	 */
	public execute(String username,String password,String filename,String osname) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 760, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "logs", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 13, 718, 393);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 18, 700, 362);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setForeground(Color.LIGHT_GRAY);
		textArea.setBackground(Color.DARK_GRAY);
		
		btnStopAndClose = new JButton("stop,save and close in between");
		btnStopAndClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			stop=true;
			}
		});
		btnStopAndClose.setBounds(239, 419, 260, 25);
		contentPane.add(btnStopAndClose);
		
		lblCopyright = new JLabel("Copyright \u00A9 2017 zxcV32 | Email: c34r534w@gmail.com");
		lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
		lblCopyright.setBounds(12, 503, 718, 16);
		contentPane.add(lblCopyright);
		
		textArea.append("starting new browser session...\n");
			Thread fb301=new Thread(){
				public void run(){
					scrape(username,password,filename,osname);
				}
			};
			fb301.start();					
		
	}
	
	void scrape(String username,String password,String filename,String osname){
		String  executableLocation;
		try{
			
			if(osname=="windows")
				executableLocation="data"+File.separator+osname+File.separator+"chromedriver.exe";
			else 
				executableLocation="data"+File.separator+osname+File.separator+"chromedriver";
			
					 
				try{ 
					System.setProperty("webdriver.chrome.bin", osname);
					System.setProperty("webdriver.chrome.driver",executableLocation);
					 driver= new ChromeDriver();
				}
		catch(IllegalStateException x){
			textArea.append(">>ERROR<<\nPossible causes:\n1) either \""+executableLocation+"\" do not exist or does not have execute permission\n2) Path of chrome is wrong!\n");
			return;
		}
		 JavascriptExecutor js = ((JavascriptExecutor) driver);

		driver.get("https://www.facebook.com/?_rdr=p");	
		(driver.findElement(By.id("email"))).sendKeys(username);
		(driver.findElement(By.id("pass"))).sendKeys(password); 
		textArea.append("Logging In..\n");
		(driver.findElement(By.id("loginbutton"))).submit();		
		Thread.sleep(4000);
		driver.get("https://m.facebook.com/profile.php?v=friends");
		Thread.sleep(4000);
		ArrayList<String> friends=new ArrayList<String>();
		ArrayList<String> names=new ArrayList<String>();
		int error=0;String nm;
		int i=1;int x=2;int y=3;int totFr=0;
		String url;
		BufferedWriter br2 = null;
			while(error<10&&!stop){											
	    		try{									
	    			url = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div["+x+"]/div["+i+"]/div[2]/div/h"+y+"/a")).getAttribute("href");
		    		System.out.println(url);
	    			if(url.length()>25){
		    			try {
							url=url.substring(0,url.indexOf("refid")-1);
						} catch (Exception e) {
						}
			    		friends.add(url);
			    		nm = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div["+x+"]/div["+i+"]/div[2]/div/h"+y+"/a")).getText();
			    		names.add(nm);
			    		textArea.append(++totFr+": "+nm+"\t\t"+url+"\n");
		    		}
	    		++i;error=0;
				//js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	    		((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div/div["+x+"]/div["+i+"]/div[2]/div/h"+y+"/a"))); 
	    		Thread.sleep(100);
	    		}catch(Exception e){
	    			++error;i=1;++x;y=1;
	    			Thread.sleep(1000);
	    		}
	    		 textArea.setCaretPosition(textArea.getDocument().getLength());
			}
	    Thread.sleep(10000);
	    	driver.quit();
	    	textArea.append("saving list to "+filename+" ...\n");
	   	textArea.append("Friends extracted: "+friends.size());
    	br2 = new BufferedWriter(new FileWriter(filename+".txt"));
	    for(i=0;i<friends.size();++i){
		    br2.write(friends.get(i)+"\n");
	    }
	    textArea.append(" friends profile url saved to "+filename+".txt");
	    textArea.append("\nyou may close the application...\n");
	    br2.close();
		}catch(Exception x){
			System.out.println("##################"+x);
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			driver.quit();
		}
	}	
}
