package org.youngjin.net.invoice;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.html.*;

@Component
public class InvoicePdfPrintView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document,
			PdfWriter writer, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		List<InvoiceGblContent> invoiceGblContentList = (List<InvoiceGblContent>) model.get("invoiceGblContentList");
		
		InvoiceGbl invoiceGbl = (InvoiceGbl) model.get("invoiceGblContentInfo");
		
		// create 6 column table
        PdfPTable table = new PdfPTable(6);
 
        // set the width of the table to 100% of page
        table.setWidthPercentage(100);
        
        // set relative columns width
        table.setWidths(new float[]{1.0f, 1.0f, 1.0f,1.0f,1.0f,1.0f});
 
        //-----------------Table Cells Label/Value------------------
 
        // 1st Row
        table.addCell(createLabelCell("TSP"));
        table.addCell(createValueCell(invoiceGbl.getInvoice().getTsp()));
        table.addCell(createLabelCell("IN/OUT"));
        table.addCell(createValueCell(invoiceGbl.getInvoice().getProcess()));
        table.addCell(createLabelCell("CODE"));
        table.addCell(createValueCell(invoiceGbl.getCode()));
 
        // 2nd Row
        table.addCell(createLabelCell("GBL NO"));
        table.addCell(createValueCell(invoiceGbl.getGblNo()));
        table.addCell(createLabelCell("RANK"));
        table.addCell(createValueCell(invoiceGbl.getRank()));
        table.addCell(createLabelCell("NAME"));
        table.addCell(createValueCell(invoiceGbl.getName()));
        
        table.setSpacingAfter(40);
		
		PdfPTable invoiceGblContentListTable = new PdfPTable(3);
 
        // set the width of the table to 100% of page
		invoiceGblContentListTable.setWidthPercentage(100);
        
        // set relative columns width
		invoiceGblContentListTable.setWidths(new float[]{1.0f, 1.0f, 1.0f});
		
		invoiceGblContentListTable.addCell(createLabelCell("CHARGING ITEMS"));
		invoiceGblContentListTable.addCell(createLabelCell("QUANTITY"));
		invoiceGblContentListTable.addCell(createLabelCell("AMOUNTS"));
		for(InvoiceGblContent invoiceGblContent : invoiceGblContentList){
			invoiceGblContentListTable.addCell(createValueCell(invoiceGblContent.getChargingItem()));
			invoiceGblContentListTable.addCell(createValueCell(invoiceGblContent.getQuantity()));
			invoiceGblContentListTable.addCell(createValueCell(invoiceGblContent.getAmount()));
		}
		
		PdfPTable remark = new PdfPTable(2);
		remark.setWidthPercentage(100);
        
        // set relative columns width
		remark.setWidths(new float[]{1.0f, 1.0f});
		remark.addCell(createLabelCellRemark("REMARK : "));
		remark.addCell(createLabelCellRemark(invoiceGbl.getRemark()));
		
		document.add(table);
		
		document.add(invoiceGblContentListTable);
		
		document.add(remark);
	}
 
    // create cells
    private static PdfPCell createLabelCell(String text){
 
    	Font font = new Font();
    	font.setColor(new Color(255, 255, 255));
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text, font));
        
        cell.setBackgroundColor(new Color(37, 73, 125));
        return cell;
    }
    private static PdfPCell createLabelCellRemark(String text){
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text));
        
        cell.setBorder(0);
        
        return cell;
    }
 
    // create cells
    private static PdfPCell createValueCell(String text){
 
        // create cell
        PdfPCell cell = new PdfPCell(new Phrase(text));
 
        // set style
        return cell;
    }
 
    public static void headerCellStyle(PdfPCell cell){
 
    // alignment
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
 
    // padding
        cell.setPaddingTop(0f);
        cell.setPaddingBottom(7f);
 
        // border
        cell.setBorder(0);
        cell.setBorderWidthBottom(2f);
 
    }
    public static void labelCellStyle(PdfPCell cell){
    // alignment
    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        // padding
        cell.setPaddingLeft(3f);
        cell.setPaddingTop(0f);
         
        // border
        cell.setBorder(0);
        cell.setBorderWidthBottom(1);
 
        // height
        cell.setMinimumHeight(18f);
    }
 
    public static void valueCellStyle(PdfPCell cell){
    // alignment
    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
 
        // padding
        cell.setPaddingTop(0f);
        cell.setPaddingBottom(5f);
 
        // border
        cell.setBorder(1);
        cell.setBorderWidthBottom(0.5f);
 
        // height
        cell.setMinimumHeight(18f);
    }

}
