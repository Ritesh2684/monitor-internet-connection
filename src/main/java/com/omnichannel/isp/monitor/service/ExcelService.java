package com.omnichannel.isp.monitor.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import com.omnichannel.isp.monitor.cache.InternetStateCacheManager;
import com.omnichannel.isp.monitor.exception.Errors;
import com.omnichannel.isp.monitor.exception.InternetMonitorServiceException;
import com.omnichannel.isp.monitor.model.InternetConnectionState;

import lombok.extern.slf4j.Slf4j;

/**
 * This class publish Internet connection Failures in Excel
 * @author JB25TV
 *
 */

@Slf4j
@Service
public class ExcelService {

	InternetStateCacheManager internetStateCache = InternetStateCacheManager.getCache();
	private HSSFWorkbook workBook;
	private String dest;

	/**
	 * This method publish Internet Connection Failures in Excel
	 * 
	 * @throws InternetMonitorServiceException {@link InternetMonitorServiceException}
	 */
	public void publishConnectionDataInExcel() {

		dest = "C:\\dev\\InternetConnectionDetails.xls";

		workBook = new HSSFWorkbook();
		// create a blank sheet
		HSSFSheet sheet = workBook.createSheet();

		int rownum = 0;

		generateWorkbook(workBook, sheet, rownum);

		// write File to Disk
		try {
			FileOutputStream out = new FileOutputStream(new File(dest));
			workBook.write(out);
			out.close();
			log.info("Internet Connection Details written successfully");
		} catch (FileNotFoundException fileNotFoundException) {
			log.error("File Not Found");
			throw new InternetMonitorServiceException(Errors.FILE_NOT_FOUND.getErrorCode(),
					Errors.FILE_NOT_FOUND.getErrorMessage());
		} catch (IOException ioException) {
			log.error("IOException while writing File with Internet Connection Details");
			throw new InternetMonitorServiceException(Errors.IO_EXCEPTION.getErrorCode(),
					Errors.IO_EXCEPTION.getErrorMessage());
		}

	}

	/**
	 * This method generates and populates data in Workbook
	 * 
	 * @param workBook {@link HSSFWorkbook}
	 * @param sheet  {@link HSSFSheet}
	 * @param rownum rowNumber
	 */
	private void generateWorkbook(HSSFWorkbook workBook, HSSFSheet sheet, int rownum) {
		List<InternetConnectionState> internetConnectionDetails = internetStateCache.getInternetConnectionDetails();

		rownum = setHeader(sheet, rownum);
		
		
		CellStyle style = workBook.createCellStyle();
		CreationHelper creationHelper = workBook.getCreationHelper();
		style.setDataFormat(creationHelper.createDataFormat().getFormat(
				"yyyy-mm-dd hh:mm:ss"));

		if (null != internetConnectionDetails) {
			for (InternetConnectionState internetConnection : internetConnectionDetails) {

				int cellNum = 0;
				Row row = sheet.createRow(rownum++);

				Cell internetStateCell = row.createCell(cellNum++);
				internetStateCell.setCellValue(internetConnection.getInternetConnectionStatus().name());

				Cell startDateTime = row.createCell(cellNum++);
				startDateTime.setCellValue(internetConnection.getStartDateTime());
				startDateTime.setCellStyle(style);

				Cell endDateTime = row.createCell(cellNum++);
				endDateTime.setCellValue(internetConnection.getEndDateTime());
				endDateTime.setCellStyle(style);

				if (null == internetConnection.getEndDateTime()) {
					internetConnection.setEndDateTime(LocalDateTime.now());
				}

				Cell duration = row.createCell(cellNum++);
				duration.setCellValue(ChronoUnit.SECONDS.between(internetConnection.getStartDateTime(),
						internetConnection.getEndDateTime()));

			}
		}

	}

	/**
	 * This method sets the header for workbook
	 * 
	 * @param sheet {@link HSSFSheet}
	 * @param rownum rownumber
	 * @return rownum
	 */
	private int setHeader(HSSFSheet sheet, int rownum) {
		int cellNum = 0;

		Row row = sheet.createRow(rownum++);
		Cell internetStateCell = row.createCell(cellNum++);
		internetStateCell.setCellValue("InternetState");

		Cell startDateTime = row.createCell(cellNum++);
		startDateTime.setCellValue("StartTime");

		Cell endDateTime = row.createCell(cellNum++);
		endDateTime.setCellValue("EndTime");

		Cell duration = row.createCell(cellNum++);
		duration.setCellValue("Duration");
		return rownum;
	}

}
