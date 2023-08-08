package servlets;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import DAO.ArticlesDAO;
import beans.Articles_stock;



public class GeneratePDF {
	
	
	public static void downloadPDF() {
		try {
			
			String file_name = "C:\\Users\\hp\\OneDrive\\Documents\\master m2i\\s2\\jee\\stock_summary.pdf";
			Document document = new Document();
			
			PdfWriter.getInstance(document, new FileOutputStream(file_name));
			document.open();
			
			

			
			Image logo = Image.getInstance("C:\\Users\\hp\\OneDrive\\Documents\\master m2i\\s2\\jee\\projet2023\\Commercial-management\\src\\main\\webapp\\stock-system\\img\\rayban.png");
			logo.scalePercent(17);
			logo.setAbsolutePosition(36, document.getPageSize().getHeight() - 36 - logo.getScaledHeight());
			document.add(logo);
			
			Font paraFont = new Font(Font.FontFamily.HELVETICA, 17);
			Paragraph para = new Paragraph("Stock Summary", paraFont);
			para.setAlignment(Element.ALIGN_CENTER);
			// Add spacing before the paragraph to center it vertically
            para.setSpacingBefore(25);
            para.setSpacingAfter(75);
			document.add(para);
			
			
			DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy", Locale.ENGLISH);
			Date date = new Date();
			Phrase timePhrase = new Phrase("" + dateFormat.format(date));
			document.add(timePhrase);
            // Create the table
            PdfPTable table = new PdfPTable(5); // Number of columns
            table.setTotalWidth(500);
            table.setLockedWidth(true); // Lock the width of the table
            
            // Set the relative widths of the columns
            float[] columnWidths = {1,6, 7, 2,2}; // Relative widths of columns (change as per your requirement)
            table.setWidths(columnWidths);
            
            // Define header font
            Font headerFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.WHITE);
            
			PdfPCell cell = new PdfPCell(new Phrase("ID", headerFont));
			cell.setMinimumHeight(20f);
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase("Name", headerFont));
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell= new PdfPCell(new Phrase("Description", headerFont));
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell= new PdfPCell(new Phrase("Price", headerFont));
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			cell= new PdfPCell(new Phrase("Quantity", headerFont));
			cell.setBackgroundColor(BaseColor.BLUE);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(cell);
			
			
			table.setHeaderRows(1);
			
			
			// Set the minimum height for cells
            float minimumHeight = 20f;
			
            // Initialize the application context
         	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
         	
         	// Retrieve the ArticlesDAO bean & the Articles_stock bean
    		ArticlesDAO dao = (ArticlesDAO) context.getBean("ArticlesDAO") ;
    		List<Articles_stock> articles = dao.getAll(); 
		

		    for (Articles_stock article : articles) {
               PdfPCell cell1 = new PdfPCell(new Phrase(article.getCode_article() + ""));
               cell1.setMinimumHeight(minimumHeight);
               table.addCell(cell1);

               PdfPCell cell3 = new PdfPCell(new Phrase(article.getName()));
               cell3.setMinimumHeight(minimumHeight);
               table.addCell(cell3);

               PdfPCell cell2 = new PdfPCell(new Phrase(article.getDescription()));
               cell2.setMinimumHeight(minimumHeight);
               table.addCell(cell2);

               PdfPCell cell4 = new PdfPCell(new Phrase(article.getPrice() + " MAD"));
               cell4.setMinimumHeight(minimumHeight);
               cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(cell4);

               PdfPCell cell5 = new PdfPCell(new Phrase(article.getQuantity() + ""));
               cell5.setMinimumHeight(minimumHeight);
               cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
               table.addCell(cell5);
            }

			
			document.add(table);
			document.close();
			
			System.out.println("finished");
			
		}catch (Exception e) {
			System.err.print(e);
		}
	}

	public static void main(String[] args) {
		
		GeneratePDF.downloadPDF();
	}
}