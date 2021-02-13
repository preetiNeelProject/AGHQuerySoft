package com.swap.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.Security;
import java.security.cert.Certificate;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFMergerUtility;
import org.apache.pdfbox.util.Splitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfSignatureAppearance;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.security.BouncyCastleDigest;
import com.itextpdf.text.pdf.security.DigestAlgorithms;
import com.itextpdf.text.pdf.security.ExternalDigest;
import com.itextpdf.text.pdf.security.ExternalSignature;
import com.itextpdf.text.pdf.security.MakeSignature;
import com.itextpdf.text.pdf.security.PrivateKeySignature;
import com.itextpdf.text.pdf.security.MakeSignature.CryptoStandard;
import com.swap.beans.Keys;

/*
 *	@Author
 *	Swapril Tyagi 
*/

@Component("dsi")
public class Dsi {
	@Autowired
	private Keys keys;

	/*
	 * private KeyStore getKeyStore(String pin,boolean flage,String obj) {
	 * 
	 * String pkcs11Config="name=eToken\nlibrary=C:/Windows/System32/bit4xpki.dll";
	 * ByteArrayInputStream pkcs11ConfigStream=new
	 * ByteArrayInputStream(pkcs11Config.getBytes());
	 * System.out.println("Stream configured"); SunPKCS11 providerPKCS11=new
	 * SunPKCS11(pkcs11ConfigStream); System.out.println("Provided created");
	 * Security.addProvider(providerPKCS11);
	 * System.out.println("Provider is "+providerPKCS11); KeyStore keyStore=null;
	 * try { System.out.println("Pin is "+pin);
	 * System.out.println("Provider is "+providerPKCS11);
	 * keyStore=KeyStore.getInstance("PKCS11",providerPKCS11);
	 * keyStore.load(pkcs11ConfigStream,pin.toCharArray()); } catch(Exception e)
	 * {System.out.println("Exception");e.printStackTrace();return keyStore;}
	 * System.out.println("returning keyStore"); return keyStore;
	 * 
	 * }
	 */
	/*
	 * public boolean signPdf(String pin, String src, String dest, String obj) { for
	 * (File file : new File(dest).listFiles()) file.delete(); KeyStore keyStore =
	 * getKeyStore(pin, true, obj); if (keyStore == null) return false;
	 * System.out.println("Received keys"); try {
	 * System.out.println("Inside Try block"); File srcDir = new File(src); for
	 * (String file : srcDir.list()) { String alias =
	 * keyStore.aliases().nextElement(); PDDocument doc = PDDocument.load(src + "/"
	 * + file); Splitter splitter = new Splitter(); List<PDDocument> pages =
	 * splitter.split(doc); System.out.println("File is " + src + "/" + file); int i
	 * = 1; for (Iterator<PDDocument> it = pages.iterator(); it.hasNext();) {
	 * PDDocument document = it.next(); document.save(keys.getRepository() +
	 * "dsi/splits/" + i + ".pdf"); document.close(); i++; } if (DsiComponents.pKey
	 * == null) DsiComponents.pKey = (PrivateKey) keyStore.getKey(alias,
	 * pin.toCharArray()); if (DsiComponents.chain == null) DsiComponents.chain =
	 * keyStore.getCertificateChain(alias); for (int j = 1; j < i; j++)
	 * signPages(DsiComponents.chain, DsiComponents.pKey, DigestAlgorithms.SHA256,
	 * keyStore.getProvider().getName(), CryptoStandard.CMS, j); PDFMergerUtility
	 * pdfMerger = new PDFMergerUtility(); for (int j = 1; j < i; j++)
	 * pdfMerger.addSource(keys.getRepository() + "dsi/signed/" + j + ".pdf");
	 * pdfMerger.setDestinationFileName(dest + "/" + file);
	 * pdfMerger.mergeDocuments(); doc.close(); System.out.println("Done"); } }
	 * catch (Exception e) { e.printStackTrace(); } return true; }
	 */

	private void signPages(Certificate[] certChain, PrivateKey pKey, String digestAlgo, String provider,
			CryptoStandard subFilter, int pageNo) {
		try {
			PdfReader pdfReader = new PdfReader(keys.getRepository() + "dsi/splits/" + pageNo + ".pdf");
			FileOutputStream fos = new FileOutputStream(keys.getRepository() + "dsi/signed/" + pageNo + ".pdf");
			PdfStamper pdfStamper = PdfStamper.createSignature(pdfReader, fos, '\0', null, true);
			PdfSignatureAppearance appearance = pdfStamper.getSignatureAppearance();
			ExternalDigest digest = new BouncyCastleDigest();
			if (DsiComponents.pks == null)
				DsiComponents.pks = new PrivateKeySignature(pKey, digestAlgo, provider);
			appearance.setVisibleSignature(new Rectangle(40, 48, 200, 200), 1, "first");
			MakeSignature.signDetached(appearance, digest, DsiComponents.pks, certChain, null, null, null, 0,
					subFilter);
			pdfStamper.flush();
			pdfStamper.close();
			fos.flush();
			fos.close();
			pdfReader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}