package com.example.kimteaho.copy;



import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.net.MalformedURLException;
import java.net.URLEncoder;


public class UploadActivity extends ActionBarActivity {


    TextView in,out;
    Button uploadButton;
    int serverResponseCode = 0;
    ProgressDialog dialog = null;

    String upLoadServerUri = null;

    private final int SELECT_IMAGE = 1;


    final String uploadFilePath = "storage/emulated/0/Pictures/";//경로를 모르겠으면, 갤러리 어플리케이션 가서 메뉴->상세 정보
    final String uploadFileName = "screenshot.png"; //전송하고자하는 파일 이름
    String FilePath,Filename;

    //52.68.141.174



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        Button back = (Button)findViewById(R.id.upload_back);
        uploadButton = (Button)findViewById(R.id.upload_button);
        in = (TextView)findViewById(R.id.inputId);
        out = (TextView)findViewById(R.id.inputPwd);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(1);
                finish();
            }
        });

        upLoadServerUri = "http://52.68.141.174/php/upload.php?filename=";//서버컴퓨터의 ip주소
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog = ProgressDialog.show(UploadActivity.this, "", "Uploading file...", true);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                            //    messageText.setText("uploading started.....");
                            }
                        });

                        uploadFile(FilePath);

                    }
                }).start();
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


        doSelectFile();



    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case SELECT_IMAGE:
                if (resultCode == RESULT_OK) {
                    //FilePath = data.getData().getPath();

                    //Filename = data.
                    Filename = getAbsoluteNameFromUri(this,data.getData());
                    FilePath = getAbsolutePathFromUri(this,data.getData());
                    //String A = getAbsolutePathFromUri(data.getData().getEncodedPath(),Uri.parse(FilePath));
                    //String B = A.getPath();
                    //Filename = data.getData().getPath();


                    in.setText(FilePath);
                    out.setText(Filename);

                    Toast.makeText(getApplication(),Filename,Toast.LENGTH_SHORT).show();

                    //File = getPath(a);
                    //Toast.makeText(getApplication(),File,Toast.LENGTH_SHORT).show();

                    //textFile.setText(FilePath);
                }
                break;
        }
    }


    private void doSelectFile()
    {
        Intent i = new Intent(Intent.ACTION_GET_CONTENT);

       // Intent i = new Intent(Intent.ACTION_PICK);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("file/*");                                    // i.setType("*/*") 으로 했을시에 기본프로그램으로 선택이 되지만
                                                                // 경로가 절대경로가 아니고 상대경로이고 이 것을 정확히
        //i.addCategory(Intent.CATEGORY_OPENABLE);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        try
        {
            startActivityForResult(i, SELECT_IMAGE);
        } catch (android.content.ActivityNotFoundException e)
        {
            e.printStackTrace();
        }




    }
    public String getPathFromUri(Uri uri){
        Cursor cursor = getContentResolver().query(uri, null, null, null, null );
        cursor.moveToNext();
        String path = cursor.getString( cursor.getColumnIndex( "_data" ) );
        cursor.close();


        return path;
    }
    public static String getAbsolutePathFromUri(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
        //Filename = cursor.getColumnName(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));
        Log.d("path", path);

        return path;
    }
    public static String getAbsoluteNameFromUri(Context context, Uri uri) {
        Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();

        String path = cursor.getString(cursor.getColumnIndex(MediaStore.MediaColumns.DATA));

        int length = path.length();
        int a = path.lastIndexOf("/");
        path = path.substring(a+1);
        //Filename =
        Log.d("path", path);

        return path;
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
                Filename = URLEncoder.encode(Filename,"UTF-8");

                URL url = new URL(upLoadServerUri + Filename);
                //url = new URL(uploadFileName);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
