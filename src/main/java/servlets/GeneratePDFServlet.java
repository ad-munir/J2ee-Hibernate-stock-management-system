package servlets;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlets.GeneratePDF;

import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/generatePDF")
public class GeneratePDFServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Set response content type
        response.setContentType("application/pdf");

        // Set the Content-Disposition header to trigger a file download
        response.setHeader("Content-Disposition", "attachment; filename=\"stock_summary.pdf\"");

        // Generate the PDF
        GeneratePDF.downloadPDF();

        // Get the generated PDF file
        String filePath = "C:\\Users\\hp\\OneDrive\\Documents\\master m2i\\s2\\jee\\stock_summary.pdf";
        java.io.File file = new java.io.File(filePath);

        // Create an output stream from the response
        try (OutputStream outStream = response.getOutputStream()) {
            // Open the PDF file
            java.nio.file.Path path = file.toPath();
            byte[] data = java.nio.file.Files.readAllBytes(path);

            // Write the PDF file to the output stream
            outStream.write(data);
            outStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
