package com.project.dcos;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)  
	public Map fileUpload(HttpServletRequest req, HttpServletResponse rep) { 
		String path = "c://aaa"; 
		Map returnObject = new HashMap(); 
		try { 
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
			Iterator iter = mhsr.getFileNames(); 
			MultipartFile mfile = null; 
			String fieldName = ""; 
			List resultList = new ArrayList(); 
			
			File dir = new File(path); 
			if (!dir.isDirectory()) { 
				dir.mkdirs(); 
			} 
			
			while (iter.hasNext()) { 
				fieldName = (String) iter.next(); 
				System.out.print("fieldName : ");
				System.out.println(fieldName);
				mfile = mhsr.getFile(fieldName);
				System.out.print("mfileName : ");
				System.out.println(mfile);
				String origName; 
				origName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8"); 
				System.out.print("origName : ");
				System.out.println(origName);
				System.out.print("realname : ");
				System.out.println(mfile.getOriginalFilename());
				if ("".equals(origName)) { 
					continue; 
				} 
				String ext = origName.substring(origName.lastIndexOf('.')); 
				System.out.print("ext : ");
				System.out.println(ext);
				String saveFileName = getUuid() + ext; 
				System.out.print("saveFileName : ");
				System.out.println(saveFileName);
				File serverFile = new File(path + File.separator + saveFileName); 
				mfile.transferTo(serverFile); 
				Map file = new HashMap(); 
				file.put("origName", origName); 
				file.put("sfile", serverFile); 
				resultList.add(file); 
			} 
			
		returnObject.put("files", resultList); 
		returnObject.put("params", mhsr.getParameterMap()); 
			
		}catch (UnsupportedEncodingException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		}catch (IllegalStateException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} catch (IOException e) { 
			// TODO Auto-generated catch block 
			e.printStackTrace(); 
		} 
		
		return null; 
	} 
	
	public static String getUuid() { 
		return UUID.randomUUID().toString().replaceAll("-", ""); 
	}


	
}














