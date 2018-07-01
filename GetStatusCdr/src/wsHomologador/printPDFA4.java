package wsHomologador;



import imprimePDF.palabras;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.activation.DataHandler;
import javax.sql.DataSource;

import org.apache.soap.util.mime.ByteArrayDataSource;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;



public class printPDFA4 {
	
	
	//private static PdfWriter writer;

	
	public static String[] myParam = new String[1];
	
	public static palabras[] arregloPalabras = new palabras[10];
	// private static String FILE = "c:/temp/FirstPdf.pdf";
	


	public static void imp_factura(String _file_xml,  String _file_pdf, String _file_jpg, respuesta miRespuesta, String _serie_original, String _path) throws Exception {
		//String reportePDF = ".\\data\\20525719953\\05_pdfs\\xxx.pdf"; 
		
		myParam[0]="";
	       
		
	//	readParam("path.fg");
	//	String _path=myParam[0];
		
		 int _tam_path = myParam[0].length();
		 
		 if (_tam_path>0) {
			 _path=myParam[0];
		 } else {
			 _path=".";
		 }
		
		 
		 
		
		
		String FONT_BOLD = _path+"\\resources\\fonts\\FrankfurtGothic-Bold.ttf";
		String FONT_CALIBRI = _path+"\\resources\\fonts\\calibri.ttf";
		String FONT_ARIAL_NARROW = _path+"\\resources\\fonts\\arial-narrow.ttf";
		 
		 Font BLUE_BOLD = new Font(FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLUE);
		
		
		 String FONT = _path+"\\resources\\fonts\\Consolas.ttf";
		
		String reportePDF = _file_pdf;
		System.out.println("Pdf:"+_file_pdf);
		System.out.println(_path);
		
		 // 
		String formato_factura = _file_jpg;
		
		
	
        
		 	//Document document = new Document();
		 	//Document document = new Document(PageSize.A4.rotate(), 10, 10, 10, 10);
			Document document = new Document();
	        // step 2
	       
	        
	       // Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
          //  PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
            
            PdfWriter writer =
    	            PdfWriter.getInstance(document, new FileOutputStream(reportePDF));
            
	        // step 3
	        document.open();

	        BaseFont bf_bold = BaseFont.createFont(FONT_BOLD,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        
	        BaseFont bf_cal = BaseFont.createFont(FONT_CALIBRI,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        BaseFont bf_arial_na = BaseFont.createFont(FONT_ARIAL_NARROW,  BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        
	        
	        
	        BaseFont bf = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
	        Font console = new Font(bf, 7);
	        
	        System.out.println("jpg:"+formato_factura );
	        
			Image img = Image.getInstance(formato_factura);
			img.scalePercent(23);
			img.setAbsolutePosition(0, 5); // horizontal , vertical
			document.add(img);
	       
	        // step 4
	     
	
	        
	         
	        // ruc  emisor
	        PdfContentByte canvas = writer.getDirectContent(); //  getDirectContentUnder();
	        writer.setCompressionLevel(0);
	        
	        
	         
	        
	        
	        
	        // ruc del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 250+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Número Identificador del Proceso de Recepción");	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	   
	        
	        
	        // ruc del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 250+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	       	canvas.showText(":  "+miRespuesta.getId());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	   
	        
	        //+miRespuesta.getId()
	        //
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 235+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Fecha de Recepción del Documento Electrónico Procesado");
	       	// (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        //+miRespuesta.getIssueDate()
	        	        
	        
	        
	        // ruc del receptor
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 235+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	       	canvas.showText(":  "+miRespuesta.getIssueDate());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 	       
		        
		        
	        if (_serie_original.length()>0) {
	        
	        	canvas.saveState();                               // q
	        	canvas.beginText();                               // BT
	        	canvas.moveText(20, 220+375);                         // 36 788 Td
	        	canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	        	canvas.showText("Documento que se dio de Baja");
	        	// 	(Hello World)Tj
	        	canvas.endText();                                 // ET
	        	canvas.restoreState();                            // Q
	        	//	+miRespuesta.getIssueDate()
	        	        
	        
	        
	        	// ruc del receptor
	        	canvas.saveState();                               // q
	        	canvas.beginText();                               // BT
	        	canvas.moveText(250, 220+375);                         // 36 788 Td
	        	canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	        	canvas.showText(":  "+_serie_original);	        // (Hello World)Tj
	        	canvas.endText();                                 // ET
	        	canvas.restoreState();                            // Q
	        }
	        

	        
	        
	  	     // Mensajes o notas asociados a la constancia de recepción  
	  	     
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 205+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Mensajes o Notas Asociados a la Constancia de Recepción");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	        String _desc0=miRespuesta.get_Note();
	        String textoFormateado0 = String.format("%-210s", _desc0);	
	        
	        
	        String _desc000=textoFormateado0.substring(0, 70).trim();
	        String _desc001=textoFormateado0.substring(70, 140).trim();
	        String _desc002=textoFormateado0.substring(140, 210).trim();
	      
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 205+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	       	canvas.showText(": "+_desc000);	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q



	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 195+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	       	canvas.showText("  "+_desc001);	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q


	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 185+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 7); // /F1 12 Tf
	       	canvas.showText("  "+_desc002);	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q

	        
	        
	        
	        
	        
	        
	        //	// Número de RUC del Receptor de la constancia
	  	     
	  	     
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 170+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Número de RUC del Emisor del Documento Electrónico");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 170+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	       	canvas.showText(":  "+miRespuesta.get_ReceiverParty());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
			
	        
	        
	        
	        //Identificador del documento electrónico enviado
			
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 155+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Identificador del Documento Electrónico Enviado");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 155+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	       	canvas.showText(":  "+miRespuesta.getReferenceID());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
			
	
	  	       
	    
	        // Código de respuesta del envío
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 140+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Código de Respuesta del Envío");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 140+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	       	canvas.showText(":  "+miRespuesta.getResponseCode());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	        
	        
	        
	        
	        //Descripción de la respuesta del envío
	        
	        
	        
	        
	        
	        // Código de respuesta del envío
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 110+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Descripción de la Respuesta del Envío");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        	
	       
	        
	        
	        String _desc=miRespuesta.getDescripcionDocumento();
	        String textoFormateado = String.format("%-300s", _desc);	
	        
	        
	        String _desc00=textoFormateado.substring(0, 60);
	        String _desc01=textoFormateado.substring(60, 120);
	        String _desc02=textoFormateado.substring(120, 180);
	        String _desc03=textoFormateado.substring(180, 240);
	        String _desc04=textoFormateado.substring(240, 300);
	        		
	        		
	        
	        
	        
	        	
	        	canvas.saveState();                               // q
	        	canvas.beginText();                               // BT
	        	canvas.moveText(250, 110+375);                         // 36 788 Td
	        	canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        	canvas.showText(_desc00);	        // (Hello World)Tj
	        	canvas.endText();                                 // ET
	        	canvas.restoreState();                            // Q
	        	
	        	
	        	canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(250, 100+375);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		        canvas.showText(_desc01.trim());	        // (Hello World)Tj
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		        

	        	canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(250, 90+375);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		        canvas.showText(_desc02.trim());	        // (Hello World)Tj
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		        
		        
	        	canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(250, 80+375);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
		        canvas.showText(_desc03.trim());	        // (Hello World)Tj
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
		        
	        	
	        	canvas.saveState();                               // q
		        canvas.beginText();                               // BT
		        canvas.moveText(250, 70+375);                         // 36 788 Td
		        canvas.setFontAndSize(bf, 8); // /F1 12 Tf
		        canvas.showText(_desc04.trim());	        // (Hello World)Tj
		        canvas.endText();                                 // ET
		        canvas.restoreState();                            // Q
	       
	        

	        
	        
	        
	        
	        
	        
	        
	        
	        
	        // Identificador del documento electrónico procesado
	        

	        // Código de respuesta del envío
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 50+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Identificador del Documento Electrónico Procesado");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 50+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	        canvas.showText(":  "+miRespuesta.get_PartyIdentification());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  
	        
	        
	        //miRespuesta.get_DigestValue()

	        // Código de respuesta del envío
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 35+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Codigo Hash del Certificado de Recepción CDR");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 35+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_bold, 8); // /F1 12 Tf
	        canvas.showText(":  "+miRespuesta.get_DigestValue());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  


	        
	        // SignatureValue
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(20, 20+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf_cal, 8); // /F1 12 Tf
	       	canvas.showText("Firma Electrónica de la Sunat (SignatureValue)");
	        canvas.endText();                                 // ET
	        canvas.restoreState();                            // Q
	 
	        
	 
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 20+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value00());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  


	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 10+375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value01());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  

	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value02());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  


	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375-10);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value03());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  


	        
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375-20);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value04());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  

	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375-30);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value05());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  


	        
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375-40);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value06());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  

	        
	        
	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375-50);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value07());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  
	        

	        canvas.saveState();                               // q
	        canvas.beginText();                               // BT
	        canvas.moveText(250, 375-60);                         // 36 788 Td
	        canvas.setFontAndSize(bf, 9); // /F1 12 Tf
	        canvas.showText(miRespuesta.get_value08());	        // (Hello World)Tj
	        canvas.endText();                                 // ET
	        canvas.restoreState();  

	        
	        
	        
	        
	        // step 5
	        document.close();		
		
		
		
	}

	

	public static void readParam(String _file_parametros) throws Exception {
		
		InputStream is_param = new FileInputStream(_file_parametros);
		DataSource ds_param = (DataSource) new ByteArrayDataSource(is_param,"application/octet-stream");
		DataHandler dhandler_param = new DataHandler((javax.activation.DataSource) ds_param);
		
		Object content = dhandler_param.getContent();
		
		BufferedReader br = null;
		
		try {

			String sCurrentLine;
			br = new BufferedReader(new InputStreamReader((InputStream) content));

			while ((sCurrentLine = br.readLine()) != null) {
				myParam[0]=sCurrentLine;
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
	
	
	  

}
