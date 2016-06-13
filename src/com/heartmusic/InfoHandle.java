package com.heartmusic;

import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.heartmusic.websongs.Info;


import android.util.Log;



public class InfoHandle extends DefaultHandler {

	private Music Mp3_info;
	private List<Music> List_Mp3_Infos;
	private String preString;

	public InfoHandle() {
		super();
	}


	public InfoHandle(List<Music> list_Mp3_Infos) {
		super();
		List_Mp3_Infos = list_Mp3_Infos;
	}


	@Override
	public void startDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.startDocument();
	}


	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		// TODO Auto-generated method stub
		preString=localName;
		if (preString.equals("resource")) {
			Mp3_info=new Music();
		}
		super.startElement(uri, localName, qName, attributes);
	}


	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		// TODO Auto-generated method stub
		preString=" ";
		Log.i("endElement_localName-------->", localName);
		if (localName.equals("resource")) {
			List_Mp3_Infos.add(Mp3_info);
		}
		super.endElement(uri, localName, qName);
	}



	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		// TODO Auto-generated method stub
		String dateString;
		dateString=new String(ch, start, length);if (preString.equals("mp3.name")) {
			Mp3_info.setTitle(dateString);
			Log.i("mp3.name--------->", dateString);
		}

		super.characters(ch, start, length);
	}



	@Override
	public void endDocument() throws SAXException {
		// TODO Auto-generated method stub
		super.endDocument();
	}


}