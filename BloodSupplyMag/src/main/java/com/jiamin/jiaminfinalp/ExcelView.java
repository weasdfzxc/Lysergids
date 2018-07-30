package com.jiamin.jiaminfinalp;

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

public class ExcelView extends AbstractExcelView{
	private User user;
	
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {


		HSSFSheet sheet = workbook.createSheet("Vitalsign record");
		sheet.setDefaultColumnWidth(9);

		HSSFCell cell1 = getCell(sheet, 0, 1);
		setText(cell1, user.getFirstName());
		HSSFCell cell2 = getCell(sheet, 0, 2);
		setText(cell2, user.getLastName());
		HSSFCell cell3 = getCell(sheet, 0, 3);
		setText(cell3, user.getDateOfBirth());
		HSSFCell cell4 = getCell(sheet, 0, 4);
		setText(cell4, user.getGender());
		
		int count = 2;
		HSSFRow sheetRow = sheet.createRow(1);
		sheetRow.createCell(0).setCellValue("Date");
		sheetRow.createCell(1).setCellValue("Healthy?");
		sheetRow.createCell(2).setCellValue("Bloodtype");
		sheetRow.createCell(3).setCellValue("Hemoglobin");
		sheetRow.createCell(4).setCellValue("Infection");
		sheetRow.createCell(5).setCellValue("Diabetes");
		sheetRow.createCell(6).setCellValue("TempCondition");
		sheetRow.createCell(7).setCellValue("PermCondition");
		for(VitalSign vs : user.getVitalSignHistory()){
			HSSFRow sheetRowx = sheet.createRow(count++);
			sheetRowx.createCell(0).setCellValue(vs.getDate());
			sheetRowx.createCell(1).setCellValue(vs.getIsHealthy());
			sheetRowx.createCell(2).setCellValue(vs.getBloodtype());
			sheetRowx.createCell(3).setCellValue(vs.getHemoglobin());
			sheetRowx.createCell(4).setCellValue(vs.getInfection());
			sheetRowx.createCell(5).setCellValue(vs.getDiabetes());
			sheetRowx.createCell(6).setCellValue(vs.getTempCondition());
			sheetRowx.createCell(7).setCellValue(vs.getPermCondition());
		}

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
