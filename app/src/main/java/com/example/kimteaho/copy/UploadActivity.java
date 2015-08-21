package com.example.kimteaho.copy;



import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


import android.app.Activity;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Browser;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;


public class UploadActivity extends Activity {


    TextView in,out;
    ImageButton uploadButton;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;

    String upLoadServerUri = null;

    private final int SELECT_IMAGE = 1;


    final String uploadFilePath = "storage/emulated/0/Pictures/";//경로를 모르겠으면, 갤러리 어플리케이션 가서 메뉴->상세 정보
    final String uploadFileName = "screenshot.png"; //전송하고자하는 파일 이름
    String FilePath,Filename;
    Uri uri = null;

    //52.68.141.174
    TextView txt_title;
    boolean isEndSelec= false;

    String subn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        Intent intent = getIntent();

        subn = intent.getExtras().getString("subn");


        ImageButton back = (ImageButton)findViewById(R.id.backprint);
        uploadButton = (ImageButton)findViewById(R.id.btn_next);
        in = (TextView)findViewById(R.id.inputId);
        out = (TextView)findViewById(R.id.inputPwd);

        txt_title = (TextView)findViewById(R.id.txt_upload_title);

        isEndSelec = false;

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

        upLoadServerUri = "http://52.68.141.174/php/upload.php";//서버컴퓨터의 ip주소
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                doSelectFile();


            }
        });

        /*OpenFileDialog dialog = new OpenFileDialog();

        dialog.Title = "Open File";
        dialog.Filter = "Text Files|*.txt|Image Files|*.jpg;*.gif;*.png|ALL|*.*";
        dialog.CheckFileExists = true;
        dialog.CheckPathExists = true;
        dialog.Multiselect = true;

        if (dialog.ShowDialog() == DialogResult.OK)
        {
            //MessageBox.Show(dialog.FileName);
            MessageBox.Show(dialog.FileNames.Length);
        }*/


        //doSelectFile();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case SELECT_IMAGE:
                if (resultCode == RESULT_OK) {

                    if(Build.VERSION.SDK_INT >= 19)
                    {
                        uri = data.getData();
                        /*final String id = DocumentsContract.getDocumentId(uri);
                        uri= ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));*/
                    }
                    else
                        uri = data.getData();

                    FilePath = uri.toString();
                    //Filename = data.getData().getLastPathSegment();
                    //Filename = data.
                    //Filename = getPathFromUri(uri);
                    Filename = getAbsolutePathFromUri(this,data.getData());
                    //String A = getAbsolutePathFromUri(data.getData().getEncodedPath(),Uri.parse(FilePath));
                    //String B = A.getPath();

                    in.setText(uri.toString());
                    out.setText(Filename);

                    //Toast.makeText(getApplication(),Filename,Toast.LENGTH_SHORT).show();

                    //File = getPath(a);
                    //Toast.makeText(getApplication(),File,Toast.LENGTH_SHORT).show();

                    //textFile.setText(FilePath);

                    dialog = ProgressDialog.show(UploadActivity.this, "", "Uploading file...", true);

                    new Thread(new Runnable() {
                        public void run() {
                            runOnUiThread(new Runnable() {
                                public void run() {
                                    //    messageText.setText("uploading started.....");
                                }
                            });

                            uploadFile(Filename);
                            isEndSelec = false;

                        }
                    }).start();
                }
                break;
        }
    }


    private void doSelectFile()
    {
        Intent i;

        if(Build.VERSION.SDK_INT > 18)
        {
            i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            i.setType("*/*");
        }
        else
        {
            i = new Intent(Intent.ACTION_GET_CONTENT);
            i.setType("*/*");
        }

        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try
        {
            startActivityForResult(i,SELECT_IMAGE);
        } catch (android.content.ActivityNotFoundException e)
        {
            e.printStackTrace();
        }




    }
    public String getPathFromUri(Uri uri){
     /*   Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
        cursor.close();*/

        Uri contentUri = null;

        contentUri = MediaStore.Files.getContentUri(uri.getPath());



        return contentUri.toString();
    }
    public static String getAbsolutePathFromUri(Context context, Uri uri) {
        if(Build.VERSION.SDK_INT >= 19 ) {
            final String id = DocumentsContract.getDocumentId(uri);
            final Uri contenturi = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            Cursor cursor = context.getContentResolver().query(contenturi, null, null, null, null);
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndexOrThrow("_data"));
            //String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            Log.d("path", path);

            return path;
        }
        else
        {
            Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
            Log.d("path", path);

            return path;
        }





    }



    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Files.FileColumns.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        startManagingCursor(cursor);
       // int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Files.FileColumns.DATA);


        cursor.moveToFirst();
        return cursor.getString(columnIndex);
    }



    public int uploadFile(String sourceFileUri) {

        String fileName = sourceFileUri;

        HttpURLConnection conn = null;
        DataOutputStream dos = null;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024 * 1024 * 1024;
        File sourceFile = new File(sourceFileUri);

        if (!sourceFile.isFile()) {

            dialog.dismiss();

            Log.e("uploadFile", "Source File not exist :"
                    +uploadFilePath + "" + uploadFileName);

            runOnUiThread(new Runnable() {
                public void run() {
              //      messageText.setText("Source File not exist :"
                //            +uploadFilePath + "" + uploadFileName);
                }
            });

            return 0;

        }
        else
        {
            try {

                // open a URL connection to the Servlet
                FileInputStream fileInputStream = new FileInputStream(sourceFile);
                URL url = new URL(upLoadServerUri);

                // Open a HTTP  connection to  the URL
                conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true); // Allow Inputs
                conn.setDoOutput(true); // Allow Outputs
                conn.setUseCaches(false); // Don't use a Cached Copy
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Connection", "Keep-Alive");
                conn.setRequestProperty("ENCTYPE", "multipart/form-data");
                conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                conn.setRequestProperty("uploaded_file", fileName);



                dos = new DataOutputStream(conn.getOutputStream());

                dos.writeBytes(twoHyphens + boundary + lineEnd);
                dos.writeBytes("Content-Disposition: form-data; name=\"uploaded_file\";filename=\""
                        + fileName + "\"" + lineEnd);

                dos.writeBytes(lineEnd);

                // create a buffer of  maximum size
                bytesAvailable = fileInputStream.available();

                bufferSize = Math.min(bytesAvailable, maxBufferSize);
                buffer = new byte[bufferSize];

                // read file and write it into form...
                bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                while (bytesRead > 0) {

                    dos.write(buffer, 0, bufferSize);
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);

                }

                // send multipart form data necesssary after file data...
                dos.writeBytes(lineEnd);
                dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

                /*conn.setRequestProperty("Content-Type","text/plain;boundary="+boundary);
                conn.setRequestProperty("usCd","23");*/

                // Responses from the server (code and message)
                serverResponseCode = conn.getResponseCode();
                String serverResponseMessage = conn.getResponseMessage();

                Log.i("uploadFile", "HTTP Response is : "
                        + serverResponseMessage + ": " + serverResponseCode);

                if(serverResponseCode == 200){

                    runOnUiThread(new Runnable() {
                        public void run() {

                            String msg = "File Upload Completed.\n\n See uploaded file here : \n\n"
                                    +uploadFileName;

                       //     messageText.setText(msg);
                         //   Toast.makeText(getApplication(), "File Upload Complete.",
                          //          Toast.LENGTH_SHORT).show();


                            String[] res = Filename.split("/");
                            Intent i = new Intent(UploadActivity.this,PrintOption1.class);
                            i.putExtra("filen",res[res.length-1]);
                            i.putExtra("subn",subn);
                            startActivity(i);
                        }
                    });

                }

                //close the streams //
                fileInputStream.close();
                dos.flush();
                dos.close();

            } catch (MalformedURLException ex) {

                dialog.dismiss();
                ex.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
              //          messageText.setText("MalformedURLException Exception : check script url.");
               //         Toast.makeText(getApplication(), "MalformedURLException",
                //                Toast.LENGTH_SHORT).show();
                    }
                });

                Log.e("Upload file to server", "error: " + ex.getMessage(), ex);
            } catch (Exception e) {

                dialog.dismiss();
                e.printStackTrace();

                runOnUiThread(new Runnable() {
                    public void run() {
                //        messageText.setText("Got Exception : see logcat ");
                 //       Toast.makeText(getApplication(), "Got Exception : see logcat ",
                  //              Toast.LENGTH_SHORT).show();
                    }
                });
                //Log.e("Upload file to server Exception", "Exception : "
                 //       + e.getMessage(), e);
            }
            dialog.dismiss();
            return serverResponseCode;

        } // End else block
    }




}
