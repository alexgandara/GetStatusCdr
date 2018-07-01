package wsHomologador;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.soap.util.mime.ByteArrayDataSource;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.itextpdf.text.pdf.PdfWriter;

import pe.gob.sunat.service.StatusResponse;
import pe.gob.sunat.servicio2.registro.electronico.comppago.consulta.ws.service.*;
import pe.gob.sunat.servicio2.registro.electronico.comppago.consulta.ws.*;
import pe.gob.sunat.servicio2.registro.electronico.comppago.consulta.*;



public class H_main_GetStatusCdr {
	
	public static String[] myParam = new String[1];

	
	
	
	// RUTAS Y ARCHIVOS
	public static respuesta miRespuesta = new respuesta();
	
	public static String $PATH_ARCHIVOS_PLANOS="";
	public static String $PATH_SIN_FIRMA="";
	public static String $PATH_CON_FIRMA="";
	public static String $FILE_NAME_XML="";
	public static String $PATH_RESPUESTAS="";
	public static String $PATH_PDFS="";
	public static String $PATH_TICKETS="";
	public static String $PATH_RESPUESTAS_STATUS="";
	public static String $PATH_CERTIFICADOS="";
	public static String $PATH_HASH="";
	public static String $PATH_CDR="";
	public static String $PATH_CDR_PDF="";
	public static String $FILE_PATH_NAME_RESPUESTA_CDR="";
	public static String $FILE_ZIP_SOURCE="";
	public static String $FILE_XML_RESPUESTA="";
	public static String $FILE_TXT="";
	public static String $FILE_CDR_PDF="";
	public static String $FILE_JPG="";
	public static String $PATH_FORMATOS="";
	
	
	
	
	
	
			
	// DATOS DEL EMISOR
	public static String $RUC="";
	public static String $RAZON_SOCIAL="";
	public static String $CODIGO_POSTAL="";
	public static String $DIRECCION="";
	public static String $CIUDAD="";
	public static String $PAIS="";
	
	
	// variables para la consulta
	public static String _rucComprobante="";
	public static String _tipoComprobante="";
	public static String _serieComprobante="";
	public static int _numeroComprobante=0;
	
			
	// DATOS DE LA LLAVE
	public static String $KEYSTORE="";
	public static String $PASSWORD_KEYSTORE="";
	public static String $PASSWORD_CERTIFICADO="";
	public static String $ALIAS_CERTIFICADO="";

	public static String $FILE_PATH_NAME_XML="";
	public static String $FILE_PATH_NAME_RESPUESTA="";
	public static String $FILE_PATH_NAME_RESPUESTA_XML="";
	public static String $FILE_PATH_NAME_RESPUESTA_XML2="";
	public static String $FILE_PATH_NAME_ZIP="";
	public static String $FILE_NAME_ZIP="";
	public static String $FILE_PATH_NAME_HASH="";
	public static String $FILE_NAME="";
	
	
	
	//public static  obj_get_status miStatus = new obj_get_status();
	
	
	public static void conectar(StatusResponse StatusResponse, String file_name, parametros misParametros, String _serie_original ) throws Exception {
		
		
		
		myParam[0]="";
	       
		
		readParam("path.fg");
		String _path=myParam[0];
		
		 int _tam_path = myParam[0].length();
		 
		 if (_tam_path>0) {
			 _path=myParam[0];
		 } else {
			 _path=".";
		 }
		
		 misParametros.set_ruta_path(_path);
		
		myParam[0]="";
	    
		
		
		$FILE_NAME=file_name;
		System.out.println(file_name);
		
		_rucComprobante =file_name.substring(0, 11);
		_tipoComprobante=file_name.substring(12, 14);
		_serieComprobante=file_name.substring(15, 19);
		int _tam = file_name.length();
		
		_numeroComprobante=Integer.parseInt(file_name.substring(20, _tam));
		
		System.out.println("Ruc    :"+_rucComprobante);
		System.out.println("Tipo   :"+_tipoComprobante);
		System.out.println("Serie  :"+_serieComprobante);
		System.out.println("Numero :"+_numeroComprobante);
		
		
		
		
		
		$PATH_ARCHIVOS_PLANOS=misParametros.get_ruta_base();
		$PATH_SIN_FIRMA=misParametros.get_ruta_xml_sin_firma();
		$PATH_CON_FIRMA=misParametros.get_ruta_xml_con_firma();
		$PATH_RESPUESTAS=misParametros.get_ruta_respuestas();
		$PATH_PDFS=misParametros.get_ruta_pdfs();
		$PATH_TICKETS=misParametros.get_ruta_tickets();
		$PATH_RESPUESTAS_STATUS=misParametros.get_ruta_respuestas_status();
		$PATH_CERTIFICADOS=misParametros.get_ruta_certificados();
		$PATH_HASH=misParametros.get_ruta_hash();
		$PATH_CDR=misParametros.get_ruta_cdr();
		$PATH_CDR_PDF=misParametros.get_ruta_cdr_pdf();
		$PATH_FORMATOS=misParametros.get_ruta_formatos();
		
		
		
		
		$RUC=misParametros.get_ruc();
		$RAZON_SOCIAL=misParametros.get_razon_social();
		$CODIGO_POSTAL=misParametros.get_codigo_postal();
		$DIRECCION=misParametros.get_direccion();
		$CIUDAD=misParametros.get_ciudad();
		$PAIS=misParametros.get_pais();
		
		$KEYSTORE=misParametros.get_keystore();
		$PASSWORD_KEYSTORE=misParametros.get_password_keystore();
		$PASSWORD_CERTIFICADO=misParametros.get_password_certificado();
		$ALIAS_CERTIFICADO=misParametros.get_alias_certificado();
		
		
		$FILE_PATH_NAME_XML = $PATH_CON_FIRMA+$FILE_NAME+".xml";
		$FILE_PATH_NAME_ZIP = $PATH_CON_FIRMA+$FILE_NAME+".zip";
		$FILE_PATH_NAME_HASH = $PATH_HASH+$FILE_NAME+".hash";
		$FILE_CDR_PDF=$PATH_CDR_PDF+"cdr_"+$FILE_NAME+".pdf";
		
		
		$FILE_NAME_ZIP=$FILE_NAME+".zip";
		$FILE_PATH_NAME_RESPUESTA_CDR=$PATH_CDR+"Cdr-"+$FILE_NAME+".zip";
		$FILE_PATH_NAME_RESPUESTA_XML=$PATH_CDR+"R-"+$FILE_NAME+".xml";
		
		$FILE_PATH_NAME_RESPUESTA_XML2=$PATH_RESPUESTAS+"R-"+$FILE_NAME+".xml";
		
		
		$FILE_JPG=$PATH_FORMATOS+"reporteCDR.jpg";
		

		
		
		
		
		
		
		
		BillConsultService service = new BillConsultService();
		HeaderHandlerResolver handlerResolver = new HeaderHandlerResolver($RUC);
		service.setHandlerResolver(handlerResolver);
		BillService port = service.getBillConsultServicePort();
		
		// BillService port = service.getBillServicePort();
		
		
		H_main_GetStatusCdr objeto = new H_main_GetStatusCdr();
	
		
		
		
		
		try{
			

		
			StatusResponse = port.getStatusCdr(_rucComprobante, _tipoComprobante, _serieComprobante, _numeroComprobante);
			System.out.println("La Respuesta es:"+StatusResponse.getStatusCode());
			if (StatusResponse.getStatusCode().equals("0004")) { 
			
			
			
				objeto.writeSmallBinaryFile(StatusResponse.getContent(),$FILE_PATH_NAME_RESPUESTA_CDR);
				UnZip.descomprimir($FILE_PATH_NAME_RESPUESTA_CDR,$PATH_CDR);
				UnZip.descomprimir($FILE_PATH_NAME_RESPUESTA_CDR,$PATH_RESPUESTAS);
				$FILE_ZIP_SOURCE=$PATH_CDR+"Cdr-"+file_name+".zip";
				$FILE_XML_RESPUESTA = $FILE_PATH_NAME_RESPUESTA_CDR;
				$FILE_TXT = $PATH_CDR+file_name+".txt";
				
				
				
				File fXmlFile = new File($FILE_PATH_NAME_RESPUESTA_XML);
				
				try {
					String raya="----------------------------------------------------------------";
				
					DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
					DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
					Document doc = dBuilder.parse(fXmlFile);
					doc.getDocumentElement().normalize();
					System.out.println("DATOS DEL DOCUMENTO");
					System.out.println(raya);
				
					miRespuesta.setDescripcionDocumento(doc.getDocumentElement().getNodeName());	
					System.out.println("Documento _ _ _ _ _ _ _ _ _ _ _: " + miRespuesta.getDescripcionDocumento());
				
					// cbc:ID	//id del documento
					NodeList nList_id = doc.getElementsByTagName("cbc:ID");
					Node nNode_id = nList_id.item(0);
					String _temp = nNode_id.getTextContent();
					miRespuesta.setId(_temp);
					System.out.println("Id del Documento_ _ _ _ _ _ _ _: " + miRespuesta.getId());
				
				
					// cbc:IssueDate
					NodeList nList_id_IssueDate = doc.getElementsByTagName("cbc:IssueDate");
					Node nNode_id_IssueDate = nList_id_IssueDate.item(0);
					miRespuesta.setIssueDate(nNode_id_IssueDate.getTextContent());
					System.out.println("Fecha de Emision_ _ _ _ _ _ _ _: " + miRespuesta.getIssueDate());
				
							
				
					// cbc:ID	//id del documento
					NodeList nList_id2 = doc.getElementsByTagName("cbc:ID");
					Node nNode_id2 = nList_id2.item(5);
					String _temp2 = nNode_id2.getTextContent();
					miRespuesta.setReferenceID(_temp2);
					System.out.println("Documento _ _ _ _ _ _ _ _ _ _ _: " + miRespuesta.getReferenceID());
					
					// cbc:ResponseCode
					NodeList nList_ResponseCode = doc.getElementsByTagName("cbc:ResponseCode");
					Node nNode_ResponseCode = nList_ResponseCode.item(0);
					String _tempResponseCode = nNode_ResponseCode.getTextContent();
					miRespuesta.setResponseCode(_tempResponseCode);
					System.out.println("Codigo de Respuesta _ _ _ _ _ _: " + miRespuesta.getResponseCode());
							

					// cbc:Description
					NodeList nList_Description = doc.getElementsByTagName("cbc:Description");
					Node nNode_Description = nList_Description.item(0);
					String _tempDescription = nNode_Description.getTextContent();
					miRespuesta.setDescripcionDocumento(_tempDescription);
					System.out.println("Codigo de Respuesta _ _ _ _ _ _: " + miRespuesta.getDescripcionDocumento());

					
//					public String _SignatureValue;
					NodeList nList_SignatureValue = doc.getElementsByTagName("SignatureValue");
					Node nNode_SignatureValue = nList_SignatureValue.item(0);
					String _temp01 = nNode_SignatureValue.getTextContent();
					miRespuesta.set_SignatureValue(_temp01);
			//		System.out.println("Signature Value _ _ _ _ _ _ _ _: " + _temp01);
					
					
					miRespuesta.set_value00(_temp01.substring(0, 40));
					miRespuesta.set_value01(_temp01.substring(40, 80));
					miRespuesta.set_value02(_temp01.substring(80, 120));
					miRespuesta.set_value03(_temp01.substring(120, 160));
					miRespuesta.set_value04(_temp01.substring(160, 200));
					miRespuesta.set_value05(_temp01.substring(200, 240));
					miRespuesta.set_value06(_temp01.substring(240, 280));
					miRespuesta.set_value07(_temp01.substring(280, 320));
					miRespuesta.set_value08(_temp01.substring(320, 344));
					
					
									
					
					
//					public String _DigestValue;
					NodeList nList_DigestValue = doc.getElementsByTagName("DigestValue");
					Node nNode_DigestValue = nList_DigestValue.item(0);
					String _tempDigestValue = nNode_DigestValue.getTextContent();
					miRespuesta.set_DigestValue(_tempDigestValue);
					System.out.println("Codigo de Hash _ _ _ _ _ _ _ _ : " + miRespuesta.get_DigestValue());
	
					
					
					
					
					NodeList nList_issueDate = doc.getElementsByTagName("cbc:IssueDate");
					Node nNode_IssueDate = nList_issueDate.item(0);
					String _temp_IssueeDate = nNode_IssueDate.getTextContent();
					miRespuesta.set_IssueDate(_temp_IssueeDate);
					System.out.println("Fha de Alta del Docto _ _ _ _ _: " + miRespuesta.get_IssueDate());

					
					
					
//					public String _ResponseDate;
					NodeList nList_ResponseDate = doc.getElementsByTagName("cbc:ResponseDate");
					Node nNode_ResponseDate = nList_ResponseDate.item(0);
					String _temp_ResponseDate = nNode_ResponseDate.getTextContent();
					miRespuesta.set_ResponseDate(_temp_ResponseDate);
					System.out.println("Fha de Creacion del CDR _ _ _ _: " + miRespuesta.get_ResponseDate());

					
					
					
//					public String _SenderParty;
					
					NodeList nList_SenderParty = doc.getElementsByTagName("cbc:ID");
					Node nNode_SenderParty = nList_SenderParty.item(2);
					String _temp_SenderParty = nNode_SenderParty.getTextContent();
					miRespuesta.set_SenderParty(_temp_SenderParty);
					System.out.println("Ruc Emisor _ _ _ _ _ _ _ _ _ _ : " + miRespuesta.get_SenderParty());
	
					
							
					
					
//					public String _ReceiverParty;
					NodeList nList_ReceiverParty = doc.getElementsByTagName("cbc:ID");
					Node nNode_ReceiverParty = nList_ReceiverParty.item(4);
					String _temp_ReceiverParty = nNode_ReceiverParty.getTextContent();
					miRespuesta.set_ReceiverParty(_temp_ReceiverParty);
					System.out.println("Ruc Receptor_ _ _ _ _ _ _ _ _ _: " + miRespuesta.get_ReceiverParty());

					
					
					
					// cac:PartyIdentification
					NodeList nList_PartyIdentification = doc.getElementsByTagName("cbc:ID");
					Node nNode_PartyIdentification = nList_PartyIdentification.item(6);
					String _temp_PartyIdentification = nNode_PartyIdentification.getTextContent();
					miRespuesta.set_PartyIdentification(_temp_PartyIdentification);
					System.out.println("Identificador del Docto_ _ _ _ : " + miRespuesta.get_PartyIdentification());

					
					
					String _temp_Note="";
					NodeList nList_Note = doc.getElementsByTagName("cbc:Note");
					Node nNode_Note = nList_Note.item(0);
					
					try {
						_temp_Note = nNode_Note.getTextContent();
					} catch (Exception e) {
						_temp_Note = "- - - - - - - - - - -";
					}
					
					miRespuesta.set_Note(_temp_Note);
					System.out.println("Notas _ _ _ _ _ _ _ _ _ _ _ _ _ : " + miRespuesta.get_Note());

					
					
					
					
				
					
					
					
					
					
					
					
					
					int numEntero = Integer.parseInt(miRespuesta.getResponseCode());
					File archivo_hash=new File($FILE_TXT);
					archivo_hash.delete();
					FileWriter chanel_write=new FileWriter(archivo_hash,true);
					chanel_write.write(miRespuesta.getDescripcionDocumento());
					chanel_write.write(" en la fecha:");
					chanel_write.write(miRespuesta.get_IssueDate());
					
					chanel_write.close();
				
				
					printPDFA4.imp_factura($FILE_PATH_NAME_RESPUESTA_XML, $FILE_CDR_PDF, $FILE_JPG, miRespuesta, _serie_original, _path);
				
				
				
					
				
				  
				} catch (Exception e) {
		  		e.printStackTrace();
				}
				
		
		
			}

			
			
			
			
			
			// -- - - - - - - - - - - - - - - - 
			
			
			} catch(javax.xml.ws.soap.SOAPFaultException soapFaultException){
			
			javax.xml.soap.SOAPFault fault = soapFaultException.getFault();
		    System.out.println("El error es: "+fault.getFaultCode());
		}
		
	}
	
	
	public static boolean isNullOrEmpty(String a) {
		return a == null || a.isEmpty();
		} 

	     
	void writeSmallBinaryFile(byte[] aBytes, String aFileName) throws IOException {
	    Path path = Paths.get(aFileName);
	    Files.write(path, aBytes);
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