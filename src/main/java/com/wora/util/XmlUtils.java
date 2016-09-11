package com.wora.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.xpath.XPathAPI;
import org.w3c.dom.CDATASection;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSParser;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.SAXException;

/*
 * Created on 18-May-04
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */

/**
 * @author erkan.dogan
 *
 *         To change the template for this generated type comment go to Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class XmlUtils {

	private static Logger log = LogManager.getLogger(XmlUtils.class);

	public static final SimpleDateFormat fmtDate = new SimpleDateFormat("dd.mm.yyyy", Locale.ENGLISH);
	public static final SimpleDateFormat fmtTimeStamp = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss", Locale.ENGLISH);
	public static final SimpleDateFormat fmtTime = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);

	private static DOMImplementationRegistry domImpRegistry = null;
	private static DOMImplementationLS domImpLS = null;
	private static DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
	/**
	 * XML validator
	 */
	private static Validator validator;

	/**
	 * @return DOM Implementation Load Save
	 * @throws Exception
	 */
	private static DOMImplementationLS getDOMImplementationLS() throws Exception {
		if (domImpLS == null) {
			domImpLS = (DOMImplementationLS) getDOMImplementationRegistry().getDOMImplementation("LS");
		}
		return domImpLS;
	}

	/**
	 * @return DOM Implementation Registry
	 * @throws Exception
	 */
	private static DOMImplementationRegistry getDOMImplementationRegistry() throws Exception {
		if (domImpRegistry == null) {
			domImpRegistry = DOMImplementationRegistry.newInstance();
		}
		return domImpRegistry;
	}

	/**
	 * This method converts xml string to DOM Document
	 *
	 * @param xmlString
	 *            XML string @return DOM Document @throws Exception @throws
	 */
	public static Document loadXmlFromString(String xmlString) throws Exception {
		LSParser builder = getDOMImplementationLS().createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
		LSInput input = getDOMImplementationLS().createLSInput();
		input.setStringData(xmlString);
		return builder.parse(input);
	}

	public static Document loadXmlFromFile(String fileName) {

		try {

			LSParser builder = getDOMImplementationLS().createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
			LSInput input = getDOMImplementationLS().createLSInput();
			FileInputStream fis = new FileInputStream(fileName);

			input.setByteStream(fis);
			return builder.parse(input);

		} catch (Exception ex) {
			log.error(ex, ex);
			return null;
		}
	}

	public static Document loadXmlFromFile(File xmlFile) {

		try {

			LSParser builder = getDOMImplementationLS().createLSParser(DOMImplementationLS.MODE_SYNCHRONOUS, null);
			LSInput input = getDOMImplementationLS().createLSInput();

			InputStream inputStream = new FileInputStream(xmlFile);

			input.setByteStream(inputStream);
			return builder.parse(input);

		} catch (Exception ex) {
			log.error(ex, ex);
			return null;
		}
	}

	/**
	 * This method convert DOM Document to string
	 *
	 * @param xmlDoc
	 *            DOM Document
	 * @return Xml String
	 */
	public static String xmlToString(Document xmlDoc) {
		return xmlToString(xmlDoc, "UTF-8");
	}

	/**
	 * This method convert DOM Document to string
	 *
	 * @param xmlDoc
	 *            DOM Document
	 * @return Xml String
	 */
	public static String xmlToString(Document xmlDoc, String encoding) {

		log.debug("xmlToString ...");
		try {

			LSSerializer serializer = getDOMImplementationLS().createLSSerializer();

			serializer.getDomConfig().setParameter("format-pretty-print", true);

			LSOutput lsOutput = getDOMImplementationLS().createLSOutput();
			lsOutput.setEncoding(encoding);
			Writer stringWriter = new StringWriter();
			lsOutput.setCharacterStream(stringWriter);
			serializer.write(xmlDoc, lsOutput);

			return stringWriter.toString();
		} catch (Exception e) {
			log.error(e, e);
		}

		return null;
	}

	/**
	 * Xml dokumani disk'e kaydeder.
	 *
	 * @param xmlDoc
	 *            : Xml dokuman
	 * @param fileName
	 *            : dosya ismi
	 * @throws Exception
	 */
	public static boolean xmlToFile(Document xmlDoc, String baseDir, String fileName) throws Exception {
		return xmlToFile(xmlDoc, baseDir, fileName, "UTF-8");
	}

	/**
	 * Xml dokumani disk'e kaydeder.
	 *
	 * @param xmlDoc
	 *            : Xml dokuman
	 * @param fileName
	 *            : dosya ismi
	 * @throws Exception
	 */
	public static boolean xmlToFile(Document xmlDoc, String baseDir, String fileName, String encoding) throws Exception {

		log.debug("Xml file saving");
		log.debug("Basedir : " + baseDir);
		log.debug("FileName : " + fileName);
		log.debug("Encoding : " + encoding);
		LSSerializer serializer = getDOMImplementationLS().createLSSerializer();

		serializer.getDomConfig().setParameter("format-pretty-print", true);

		LSOutput lsOutput = getDOMImplementationLS().createLSOutput();
		lsOutput.setEncoding(encoding);

		FileOutputStream fos = new FileOutputStream(baseDir + File.separator + fileName);
		lsOutput.setByteStream(fos);
		boolean written = serializer.write(xmlDoc, lsOutput);
		fos.close();
		return written;

	}

	public static String transformXmlXsl(Document xmlDoc, String xslFileName) throws Exception {

		try {
			xmlDoc.normalize();

			Source xmlSource = new DOMSource(xmlDoc);
			StringWriter stWriter = new StringWriter();
			Result result = new StreamResult(stWriter);

			File f = new File(xslFileName);
			Transformer trans = null;
			if (f.exists()) {

				trans = StyleSheetCache.newTransformer(xslFileName);
			} else {
				log.error("Xsl File not found..xslFileName : " + xslFileName);
				throw new Exception("Xsl File not found..");
			}

			trans.transform(xmlSource, result);

			return stWriter.toString();
		} catch (Exception e) {
			throw e;
		}
	}

	public static String transformXmlXsl(Document xmlDoc, InputStream xslStream) throws Exception {

		try {
			xmlDoc.normalize();

			StreamSource streamSource = new StreamSource(xslStream);
			Source xmlSource = new DOMSource(xmlDoc);
			StringWriter stWriter = new StringWriter();
			Result result = new StreamResult(stWriter);

			Transformer trans = null;
			TransformerFactory transFact = TransformerFactory.newInstance();
			trans = transFact.newTransformer(streamSource);

			trans.transform(xmlSource, result);

			return stWriter.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			xslStream.close();
		}
	}

	/**
	 * Xml file is transformed into new Xml by using Xslt file.
	 */
	public static Document getTransformedXml(Document xmlDoc, String xslFileName) throws Exception {

		String xmlSource = transformXmlXsl(xmlDoc, xslFileName);
		log.debug("Transfermed xml Source : " + xmlSource);
		return loadXmlFromString(xmlSource);
	}

	/**
	 * Xml file is transformed into new String by using Xslt file.
	 */
	public static String getTransformedXmlSource(Document xmlDoc, String xslFileName) throws Exception {
		String xmlSource = transformXmlXsl(xmlDoc, xslFileName);

		return xmlSource;
	}

	/**
	 * This method validates Dom Document according to specified XSD file
	 *
	 * @param xmlDoc
	 * @param xsdFileName
	 * @throws Exception
	 */
	public static void isValidXML(Document xmlDoc, String xsdFileName) throws Exception {
		log.trace("Validating xmlDoc document..");
		// validate the DOM tree
		getXsdValidator(xsdFileName).validate(new DOMSource(xmlDoc));
		log.trace("xmlDoc document is valid");
	}

	/**
	 * @param xsdFileName
	 *            XSD file name
	 * @return XSD validator
	 * @throws SAXException
	 */
	private static Validator getXsdValidator(String xsdFileName) throws SAXException {

		if (validator == null) {
			log.debug("Creating xsd validator. XSD File name : " + xsdFileName);
			// create a SchemaFactory capable of understanding WXS schemas
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

			// load a WXS schema, represented by a Schema instance
			Source schemaFile = new StreamSource(new File(xsdFileName));
			Schema schema = factory.newSchema(schemaFile);

			validator = schema.newValidator();
		}

		// create a Validator instance, which can be used to validate an
		// instance document
		return validator;
	}

	/**
	 * This method created DOM Document
	 *
	 * @param rootElementName
	 *            Root element Name
	 * @return DOM Document
	 */
	public static Document createDocumentWithNamespaces(String rootElementName, String xmlNamespaceURI) {
		try {
			DocumentBuilder db = documentBuilderFactory.newDocumentBuilder();
			DOMImplementation domImpl = db.getDOMImplementation();
			return domImpl.createDocument(xmlNamespaceURI, rootElementName, null);
		} catch (Exception e) {
			log.error(e, e);
		}
		return null;
	}

	public static Element addTextNode(Document xmlDoc, Element ndParent, String nodeName, String nodeValue) {

		Element ndNode = xmlDoc.createElement(nodeName);
		Text ndTextNode = xmlDoc.createTextNode(nodeName);
		ndTextNode.setData(nodeValue);
		ndNode.appendChild(ndTextNode);
		ndParent.appendChild(ndNode);
		return ndNode;

	}

	public static Element findElement(Node nd, String element) {

		Element res = null;
		try {
			NodeList ndList = XPathAPI.selectNodeList(nd, element);
			if (ndList.getLength() > 0) {
				res = (Element) ndList.item(0);
			}
		} catch (Exception ex) {
			log.error(ex, ex);
		}
		return res;
	}

	/**
	 * @param map
	 * @param string
	 * @return
	 */
	public static String getAtributeValue(NamedNodeMap map, String string) {
		if (map.getNamedItem(string) != null) {
			return map.getNamedItem(string).getNodeValue();
		} else {
			return null;
		}
	}

	public static Element addEmptyNode(Document xmlDoc, Element ndParent, String nodeName) {
		Element ndNode = xmlDoc.createElement(nodeName);
		ndParent.appendChild(ndNode);
		return ndNode;
	}

	public static void addNode(Document xmlDoc, Element ndParent, String nodeName, String nodeValue) {
		Element ndNode = xmlDoc.createElement(nodeName);
		Text ndTextNode = xmlDoc.createTextNode(nodeName);
		ndTextNode.setData(nodeValue);
		ndNode.appendChild(ndTextNode);
		ndParent.appendChild(ndNode);
	}

	public static CDATASection extractCDATASection(Node nd) {
		CDATASection cdata = null;
		try {
			NodeList ndlst = nd.getChildNodes();
			for (int ii = 0; ii < ndlst.getLength(); ii++) {
				// System.out.println(ndlst.item(ii).getNodeType());
				if (ndlst.item(ii).getNodeType() == Node.CDATA_SECTION_NODE) {
					cdata = (CDATASection) ndlst.item(ii);
					return cdata;
				}
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return cdata;
	}

	public static Text extractCDATASectionText(Node nd) {
		Text text = null;

		try {
			NodeList ndlst = nd.getChildNodes();
			for (int ii = 0; ii < ndlst.getLength(); ii++) {
				// System.out.println(ndlst.item(ii).getNodeType());
				if (ndlst.item(ii).getNodeType() == Node.TEXT_NODE) {
					text = (Text) ndlst.item(ii);
					return text;
				}
			}
		} catch (Exception e) {
			log.error(e, e);
		}
		return text;
	}

	/**
	 * @param nd
	 * @param element
	 * @return
	 */
	public static Element findNode(Node nd, String element) {

		Element res = null;
		try {
			NodeList ndList = XPathAPI.selectNodeList(nd, element);
			if (ndList.getLength() > 0) {
				res = (Element) ndList.item(0);
			}
		} catch (Exception ex) {
			log.error(ex, ex);
		}
		return res;
	}

	private static java.util.Date getParsedDate(String type, String parseAs, String value) throws ParseException {

		String format = type;

		if (parseAs != null && parseAs.trim().length() > 0) {
			format = parseAs;
		}

		if (format.equalsIgnoreCase("date")) {
			return fmtDate.parse(value);
		} else if (format.equalsIgnoreCase("time")) {
			return fmtTime.parse(value);
		} else if (format.equalsIgnoreCase("timestamp")) {
			return fmtTimeStamp.parse(value);
		}

		return null;
	}

	public static String getValueFromXMLDoc(Document xmlDoc, String columnName) throws Exception {
		Node firstChild = xmlDoc.getFirstChild();
		for (int i = 0; i < firstChild.getChildNodes().getLength(); i++) {
			Node item = firstChild.getChildNodes().item(i);
			if (item.hasAttributes()) {
				if (item.getAttributes().getNamedItem("name").getNodeValue().equals(columnName)) {
					return item.getAttributes().getNamedItem("value").getNodeValue().trim();
				}
			}
		}
		throw new Exception("Column '" + columnName + "' could not found in xml file: " + XmlUtils.xmlToString(xmlDoc));
	}

	public static String getFieldsValue(Document xmlDoc, String fieldId) throws TransformerException {
		String fieldValue = "";
		NodeList fields = XPathAPI.selectNodeList(xmlDoc, "//Field");
		for (int i = 0; i < fields.getLength(); i++) {
			Element fieldElement = (Element) fields.item(i);
			NamedNodeMap fieldAttributes = fieldElement.getAttributes();
			for (int j = 0; j < fieldAttributes.getLength(); j++) {
				String attrName = fieldAttributes.item(j).getNodeName();
				String attrValue = fieldAttributes.item(j).getNodeValue();

				if (attrName.equalsIgnoreCase("id") && attrValue.equalsIgnoreCase(fieldId)
						&& fieldAttributes.item(j + 1).getNodeName().equalsIgnoreCase("value")) {
					fieldValue = fieldAttributes.item(j + 1).getNodeValue();
					return fieldValue;
				}
			}
		}
		return "";
	}

}