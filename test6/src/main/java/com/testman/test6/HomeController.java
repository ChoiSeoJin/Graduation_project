package com.testman.test6;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	public String test(Model model) {
		logger.info("test controller......."); 
		
		int param1 = 1;
		int param2 = 2;
		model.addAttribute("param1", param1);
        model.addAttribute("param2", param2);


		return "home";
	}
		
	@ResponseBody
	@RequestMapping(value = "/testAction", method = RequestMethod.POST)

	public HashMap<String, Object> checkId(@RequestParam HashMap<String, Object> param, HttpServletResponse response ) {
	     
	    System.out.println(param);
	    System.out.println("id is "+param.get("test"));
	    ObjectMapper mapper = new ObjectMapper();
	    //your logic
	 
	    HashMap<String, Object> hashmap = new HashMap<String, Object>();
	    hashmap.put("KEY", "YES");
	 
	      
	    return hashmap;
	}
	
	@RequestMapping(value = "/scriptUpload", method = RequestMethod.POST)
	public String scriptUpload(@RequestParam("script") String script, @RequestParam("script_name") String script_name) {
		String path = "c://Script";
		String Fullpath = path + "//" + script_name + ".txt";
		System.out.println("scriptman: " + script);
		try{
			File dir = new File(path);
			if(!dir.isDirectory()) {
				dir.mkdirs();
			}
            // 파일 객체 생성
            File file = new File(Fullpath) ;
            
            // true 지정시 파일의 기존 내용에 이어서 작성
            FileWriter fw = new FileWriter(file,true) ;
             
            // 파일안에 문자열 쓰기
            fw.write(script);
            fw.flush();
 
            // 객체 닫기
            fw.close();
             
             
        }catch(Exception e){
            e.printStackTrace();
        }
		
		return "home";
	}
	
	@RequestMapping(value = "/fileUpload", method = RequestMethod.POST)  
	public Map fileUpload(MultipartHttpServletRequest req, HttpServletResponse rep) { 
		String Filepath = "c://File"; 
		String Datapath = "c://Data";
		String path = "c://aaa"; 
		Map returnObject = new HashMap(); 
		try { 
			
			
			MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req; 
			Iterator iter = mhsr.getFileNames(); 
			String x = (String)mhsr.getParameter("abc");
			if(x.equals("File")) 
				path = Filepath;	
			else if(x.equals("Data")) 
				path = Datapath;
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
				System.out.println("asdasd : " + path + File.separator + saveFileName);
				mfile.transferTo(serverFile); 
				Map file = new HashMap(); 
				file.put("origName", origName); 
				file.put("sfile", serverFile); 
				resultList.add(file); 
			} 
			
		
			
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
