package wsHomologador;


import java.io.File;
import java.io.FileWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class conectorRespuesta {
	public static respuesta miRespuesta = new respuesta();
	public static String $FILE_PATH_RESPUESTA=".\\data\\20223351205\\04_respuestas_pruebas\\";
	public static String $FILE_ZIP_SOURCE="";
	public static String $FILE_XML_RESPUESTA="";
	public static String $FILE_TXT = "";
	
	
	
	
	public static void main(String[] args) {
		
		System.out.println("--20223351205");
		
		String _file= args[0];
		$FILE_ZIP_SOURCE=$FILE_PATH_RESPUESTA+"R-"+_file+".zip";
		$FILE_XML_RESPUESTA = $FILE_PATH_RESPUESTA+"r-"+_file+".xml";
		$FILE_TXT = $FILE_PATH_RESPUESTA+_file+".txt";
		
		UnZip.descomprimir($FILE_ZIP_SOURCE,$FILE_PATH_RESPUESTA);
		
		
		File fXmlFile = new File($FILE_XML_RESPUESTA);
		try {
			
			
			
			
			String raya="----------------------------------------------------------------";
			
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
			
			
			doc.getDocumentElement().normalize();
			
	//		NodeList nList = doc.getElementsByTagName("Invoice");
			
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

			int numEntero = Integer.parseInt(miRespuesta.getResponseCode());
	//		if (numEntero==0) {
				
				 File archivo_hash=new File($FILE_TXT);
				 archivo_hash.delete();
				 FileWriter chanel_write=new FileWriter(archivo_hash,true);
				 chanel_write.write(miRespuesta.getDescripcionDocumento());
				 chanel_write.close();
				 	
	//		} else {
	//			 File archivo_hash=new File($FILE_TXT);
	//			 FileWriter chanel_write=new FileWriter(archivo_hash,true);
	//			 chanel_write.write(miRespuesta.getDescripcionDocumento());
	//			 chanel_write.write(miRespuesta.getResponseCode());
	//			 chanel_write.close();
				
	//		}
				
			
			  
		} catch (Exception e) {
	  		e.printStackTrace();
    	}
			
	
	
	}

}
