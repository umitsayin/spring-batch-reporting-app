package com.batchapp.config.batch.steps;

import com.batchapp.dto.InvoiceDto;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class InvoiceWriter implements ItemWriter<InvoiceDto> {

    @Override
    public void write(Chunk<? extends InvoiceDto> chunk) throws Exception {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = dateFormat.format(now);

        String fileName = "invoices_" + todayDate + ".xlsx";
        String filePath = "" + fileName;

        File file = new File("src/main/resources/invoices/" + filePath);

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Fatura Listesi");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Invoice Number");
        headerRow.createCell(1).setCellValue("Invoice Name");
        headerRow.createCell(2).setCellValue("Customer Email");
        headerRow.createCell(3).setCellValue("Total Price");
        headerRow.createCell(4).setCellValue("Currency");

        int rowNum = 1;
        for (InvoiceDto invoice : chunk.getItems()) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(invoice.id());
            row.createCell(1).setCellValue(invoice.name());
            row.createCell(2).setCellValue(invoice.customerEmail());
            row.createCell(3).setCellValue(invoice.totalPrice());
            row.createCell(4).setCellValue(invoice.currency());
        }

        FileOutputStream outputStream = new FileOutputStream(file);
        workbook.write(outputStream);
        outputStream.close();
    }
}
