package com.swap.utils;

import java.security.PrivateKey;
import java.security.cert.Certificate;
import com.itextpdf.text.pdf.security.ExternalSignature;

public class DsiComponents
{
	public static ExternalSignature pks;
	public static PrivateKey pKey;
	public static Certificate[] chain;
}
