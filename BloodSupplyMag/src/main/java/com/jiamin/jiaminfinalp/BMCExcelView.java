package com.jiamin.jiaminfinalp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.jiamin.pojo.User;
import com.jiamin.pojo.VitalSign;
import com.jiamin.tools.BloodBankInventory;

public class BMCExcelView extends AbstractExcelView{
	private User user;
	private List<BloodBankInventory> bbiList;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		HSSFSheet sheet = workbook.createSheet("blood inventory");
		sheet.setDefaultColumnWidth(9);

		HSSFCell cell1 = getCell(sheet, 0, 1);
		setText(cell1, user.getOrgan().getOrganName());
		
		int count = 2;
		HSSFRow sheetRow = sheet.createRow(1);
		sheetRow.createCell(0).setCellValue("BloodBnak name");
		sheetRow.createCell(1).setCellValue("Type A");
		sheetRow.createCell(2).setCellValue("Type B");
		sheetRow.createCell(3).setCellValue("Type AB");
		sheetRow.createCell(4).setCellValue("Type O");
		sheetRow.createCell(5).setCellValue("Type A ratio");
		sheetRow.createCell(6).setCellValue("Type B ratio");
		sheetRow.createCell(7).setCellValue("Type AB ratio");
		sheetRow.createCell(8).setCellValue("Type O ratio");
		for(BloodBankInventory bbi : bbiList){
			HSSFRow sheetRowx = sheet.createRow(count++);
			sheetRowx.createCell(0).setCellValue(bbi.getBbName());
			sheetRowx.createCell(1).setCellValue(bbi.getTypea()+"ml");
			sheetRowx.createCell(2).setCellValue(bbi.getTypeb()+"ml");
			sheetRowx.createCell(3).setCellValue(bbi.getTypeab()+"ml");
			sheetRowx.createCell(4).setCellValue(bbi.getTypeo()+"ml");
			sheetRowx.createCell(5).setCellValue(bbi.getTypearatio());
			sheetRowx.createCell(6).setCellValue(bbi.getTypebratio());
			sheetRowx.createCell(7).setCellValue(bbi.getTypeabratio());
			sheetRowx.createCell(8).setCellValue(bbi.getTypeoratio());
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BloodBankInventory> getBbiList() {
		return bbiList;
	}

	public void setBbiList(List<BloodBankInventory> bbiList) {
		this.bbiList = bbiList;
	}
	
	
}
