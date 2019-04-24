package com.chinasofti.hotel.excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import com.chinasofti.hotel.control.RMSService;
import com.chinasofti.hotel.control.RMSServiceImpl;
import com.chinasofti.hotel.domain.Food;
import com.chinasofti.hotel.domain.Orders;


public class ExcelUtil {
    private RMSService sb;
    private List<Orders> list;
	/*public ExcelUtil(RMSService sb) {
		// TODO Auto-generated constructor stub
		this.sb=new RMSServiceImpl();
	}*/
	
	/*public ExcelUtil() {
		this.sb=new RMSServiceImpl();
		
	}*/
	
	public ExcelUtil() {
		super();
		this.sb=new RMSServiceImpl();
	}

	public void setExcel() {
		//查询所有订单
		
		list = this.sb.selectAllOrders();
		// 第一步创建workbook
		HSSFWorkbook wb = new HSSFWorkbook();

		// 第二步创建sheet
		HSSFSheet sheet = wb.createSheet("测试");

		// 第三步创建行row:添加表头0行
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		
		// style.setAlignment(HSSFCellStyle.ALIGN_CENTER); //居中
		sheet.setColumnWidth(0, 252*36+323);
		// 第四步创建单元格
		HSSFCell cell = row.createCell(0); // 第一个单元格
		cell.setCellValue("订单编号");
		cell.setCellStyle(style);
		cell = row.createCell(1); // 第二个单元格
		cell.setCellValue("员工编号");
		cell.setCellStyle(style);
		cell = row.createCell(2); 
		cell.setCellValue("会员卡号");
		cell.setCellStyle(style);
		cell = row.createCell(3); 
		cell.setCellValue("订单日期");
		cell.setCellStyle(style);
		cell = row.createCell(4); 
		cell.setCellValue("订单总价");
		cell.setCellStyle(style);
		// 第五步插入数据
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0; i < list.size(); i++) {
			// 创建行
			//Discuss d=this.sb.selectDiscussByno(list.get(i).getOrderNo());
			row = sheet.createRow(i + 1);
			// 创建单元格并且添加数据
			row.createCell(0).setCellValue(list.get(i).getOid());
			row.createCell(1).setCellValue(list.get(i).getE1().getEmpId());
			row.createCell(2).setCellValue(list.get(i).getC1().getCno());
			String time=sdf.format(list.get(i).getOdate());
			row.createCell(3).setCellValue(time);
			row.createCell(4).setCellValue(list.get(i).getTotal());
		}
		// 第六步将生成excel文件保存到指定路径下
		try {
			FileOutputStream fout = new FileOutputStream("e:/RMS/全部订单.xls");
			wb.write(fout);
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Excel文件生成成功...");
	}

}
