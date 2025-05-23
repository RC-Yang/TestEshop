package com.example;

import java.io.InputStream;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.ServletContextAware;

//Spring容器啟動時，直接new出所有bean(除prototype、lazy之外)
public class TestTranslationBean implements ServletContextAware{//讓 Spring Bean 在初始化時獲得 ServletContext 物件
	
	private ServletContext servletContext;//雖然已經有實作介面，理論上本就有該物件，但必須刻意這樣寫，才能動態處理該物件
	private Map<String, Map<String, String>> translations = new HashMap<>();
	
	@Override
    public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
    }

	 @PostConstruct
	 public void init() {
		 try (InputStream is = getClass().getClassLoader().getResourceAsStream("i18n/translation_template.xlsx");
				 XSSFWorkbook workbook = new XSSFWorkbook(is)) {

	            XSSFSheet sheet = workbook.getSheetAt(0);

	            XSSFRow header = sheet.getRow(0);
	            List<String> langList = new ArrayList<>();

	            // 第0欄是 key，從第1欄開始是語言
	            for (int i = 1; i < header.getLastCellNum(); i++) {
	                langList.add(header.getCell(i).getStringCellValue().trim());
	            }

	            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
	                XSSFRow row = sheet.getRow(rowIndex);
	                if (row == null) continue;

	                XSSFCell keyCell = row.getCell(0);
	                if (keyCell == null) continue;

	                String key = keyCell.getStringCellValue().trim();
	                if (key.isEmpty()) continue;

	                Map<String, String> langMap = new HashMap<>();
	                for (int i = 1; i < row.getLastCellNum(); i++) {
	                    XSSFCell valueCell = row.getCell(i);
	                    String value = (valueCell != null) ? valueCell.getStringCellValue().trim() : "";
	                    langMap.put(langList.get(i - 1), value);
	                }

	                translations.put(key, langMap);
	            }

	            System.out.println("翻譯表載入完成：" + translations.size() + " 筆");
	            servletContext.setAttribute("translator", this);

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	 
	 	public String getText(String key, String locale) {
	        return translations.get(key).get(locale);
	    }
}
