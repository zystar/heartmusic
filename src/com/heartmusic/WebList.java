package com.heartmusic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.heartmusic.service.DownService;
import com.heartmusic.sharedata.SkinData;
import com.heartmusic.websongs.Info;

public class WebList extends Activity {


	static WebList a;

	private Intent service;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.web_music_list);

		ImageButton returnBtn = (ImageButton)findViewById(R.id.returnBtn);

		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	    

		a = this;
		int skinData = SkinData.getState();
		LinearLayout webLL = (LinearLayout)findViewById(R.id.webLL);
		switch(skinData){
		case 1:
			webLL.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			webLL.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			webLL.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			webLL.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			webLL.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			webLL.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			webLL.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			webLL.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			webLL.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			webLL.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			webLL.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			webLL.setBackgroundResource(R.drawable.bk);
			break;
		}

        service=new Intent(this,DownService.class);
		Intent i = getIntent();
		String xml_name = i.getStringExtra("xml_name");

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}
		listView = (ListView) findViewById(R.id.web_list_view);
		showList(xml_name);
	}
	
	private ListView listView;
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter1;
	private ArrayList<String> musicnameList;

	public void showList(String xml_name){
		musicnameList = new ArrayList<String>();
		final List<Music> lInfos = parseXML(xml_name);
		list.clear();
		adapter1 = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter1);
		for (Iterator iterator = lInfos.iterator(); iterator.hasNext();) {
			Music info = (Music) iterator.next();
			musicnameList.add(info.getTitle());
			list.add("  " + info.getTitle());
		}
		adapter1 = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter1);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent,View v, int position,long id){
				String name = lInfos.get(position).getTitle();
				String url = "http://heartmusic.oschina.mopaas.com/mp3/"+name;
				Log.e("name", name);
				Intent i=new Intent();
				i.setClass(WebList.this,WebPlayUi.class);
				i.putExtra("path" , url);
				i.putExtra("Title" , name);
				i.putExtra("path" , url);
    	    	 i.putStringArrayListExtra("musicnameList", musicnameList);
				startActivity(i);
				Toast.makeText(getApplicationContext(), "正在播放...", Toast.LENGTH_SHORT).show();
			}
		});

		//为ListView添加长按下载事件
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override  
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,  
					int arg2, long arg3) {  
				String name = lInfos.get(arg2).getTitle();
				Toast.makeText( getApplicationContext(), "开始下载"+musicnameList.get(arg2).toString(), Toast.LENGTH_LONG).show();
				String downUrlString="http://heartmusic.oschina.mopaas.com/mp3/"+name;
				//downUrlString中不能包含空格，用以下语句转换
				downUrlString = downUrlString.replaceAll(" ", "%20");
				Log.e("downUrlString",downUrlString);
				String sdcardSave="DownFile";
				String filename=musicnameList.get(arg2).toString();
				Log.e("filename",filename);
				OutputStream outputStream=null;
				HttpURLConnection connection;
				/*
				 * 1.在AndroidMainfest.xml中进行权限配置
				 * <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
				 * 取得写入SDCard的权限
				 * 2.取得SDCard的路径： Environment.getExternalStorageDirectory()
				 * 3.检查要保存的文件上是否已经存在
				 * 4.不存在，新建文件夹，新建文件
				 * 5.将input流中的信息写入SDCard
				 * 6.关闭流
				 */  
				String SDcardPath=Environment.getExternalStorageDirectory()+"";
				Log.e("SDcardPath",SDcardPath);
				String pathString=SDcardPath+"/"+sdcardSave+"/"+filename;
				Log.e("pathString",pathString);
				File file=new File(pathString);

				startService(service);
				try {
					connection = (HttpURLConnection) new URL(downUrlString).openConnection();
					InputStream inputStream=connection.getInputStream();
					if (!file.exists()) {
						new File(SDcardPath+"/"+sdcardSave).mkdir();
					}
					File[] filelist=new File(SDcardPath+"/"+sdcardSave).listFiles();
					for (int i = 0; i < filelist.length; i++) {
						if (filelist[i].getName().equals(filename)) {
							Toast.makeText(getApplicationContext(), filename+"已经存在", Toast.LENGTH_LONG).show();
							return false ;
						}
					}
					file.createNewFile();
					System.err.println("开始下载");

					outputStream=new FileOutputStream(file);
					byte[] buffer=new byte[4*1024];
					while ((inputStream.read(buffer))!=-1) {
						outputStream.write(buffer);
					}
					outputStream.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					try {
						outputStream.close();
						Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_LONG).show();
						System.err.println("下载完成");
						//progressDialog.dismiss();
					} catch (Exception e2) {
						// TODO: handle exception        
						Toast.makeText(getApplicationContext(), "下载失败", Toast.LENGTH_LONG).show();
						//progressDialog.cancel();
					}

				}
				return true;  
			}  
		});
	}

	private List<Music> parseXML(String xml_name) {
		List<Music> infos = null;

		SAXParserFactory factory = SAXParserFactory.newInstance();
		try {
			URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/"+xml_name);

			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			if (connection.getReadTimeout() == 4) {

				Toast.makeText(getApplicationContext(), "请求超时",
						Toast.LENGTH_LONG).show();
				return null;
			}
			InputStream inputStream2 = connection.getInputStream();
			SAXParser parser = factory.newSAXParser();
			XMLReader reader = parser.getXMLReader();
			infos = new ArrayList<Music>();
			reader.setContentHandler(new InfoHandle(infos));
			reader.parse(new InputSource(inputStream2));

		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return infos;
	}
}
