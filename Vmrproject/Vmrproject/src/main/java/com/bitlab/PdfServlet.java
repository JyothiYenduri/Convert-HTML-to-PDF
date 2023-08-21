package com.bitlab;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import org.w3c.dom.DOMException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
;

public class PdfServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String name = request.getParameter("name");
	    String value = request.getParameter("value");

	    // Create a ByteArrayOutputStream to write PDF content
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();

	    try {
	        // Create a new PDF document
	        Document document = new Document();

	        // Set PdfWriter to write to the ByteArrayOutputStream
	        PdfWriter.getInstance(document, baos);

	        document.open();

	        // Add content to the PDF
	        document.add(new Paragraph("Name: " + name));
	        document.add(new Paragraph("Value: " + value));

	        // Close the document
	        document.close();
	    } catch (DocumentException e) {
	        e.printStackTrace();
	    }

	    // Get the PDF content from the ByteArrayOutputStream
	    byte[] pdfContent = baos.toByteArray();

	    // Set content type and send PDF data in response
	    response.setContentType("application/pdf");
	    response.setContentLength(pdfContent.length);
	    response.getOutputStream().write(pdfContent);

	    // Close resources
	    response.getOutputStream().flush();
	    response.getOutputStream().close();
	}

}

