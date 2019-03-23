package com.nyayadhish.droidgenesis.lib.Utilities;

import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.webkit.MimeTypeMap;

import java.io.ByteArrayOutputStream;


public class MyFileUtils {

    private static final String TAG = MyFileUtils.class.getSimpleName();

    /*public static String encodeFileToBase64Binary(String uri) throws IOException {
        File selectedFile = new File(uri);
        Log.i(TAG, "encodeFileToBase64Binary: Selected file" + selectedFile);
        byte[] bytes = FileUtils.readFileToByteArray(selectedFile);
        String encoded = Base64.encodeToString(bytes,Base64.DEFAULT);

        //String encodedString = new String(encoded);
        return encoded;
    }
*/
    public static String getRealPathFromURI(Uri contentUri, Context mContext) {
        String[] proj = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(mContext, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
    public static String getFilePathFromContentUri(Uri selectedVideoUri,
                                             ContentResolver contentResolver) {
        String filePath;
        String[] filePathColumn = {MediaStore.MediaColumns.DATA};

        Cursor cursor = contentResolver.query(selectedVideoUri, filePathColumn, null, null, null);
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        return filePath;
    }

    public static String getImagePath(Uri uri, ContentResolver contentResolver){
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
    }


    public static String getFilesPath(Uri uri, ContentResolver contentResolver){
        Cursor cursor = contentResolver.query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":")+1);
        cursor.close();

        cursor = contentResolver.query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.MediaColumns._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA));
        cursor.close();

        return path;
    }


        public static String getMimeType(String ext) {
            if (ext.contains("png") || ext.contains("jpg") || ext.contains("jpeg"))
                return TYPE.IMAGE + "/*";
            else if (ext.contains("pdf"))
                return "application/" + TYPE.PDF;
            else if (ext.contains("ppt"))
                return "application/" + "vnd.ms-powerpoint";
            else if (ext.contains("mp4") || ext.contains("mkv") || ext.contains("mov"))
                return "video/" + ext;
            else if (ext.contains("docx"))
                return "application/" + "msword";
            else if (ext.contains("xls"))
                return "application/" + "vnd.ms-excel";
            else
                return TYPE.UNKNOWN;
        }

    public static String bitmapToBase64(Bitmap image) {
        if (image != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream(125000);
            image.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            Log.i(TAG, "bitmapToBase64: baos = " + baos);
            byte[] b = baos.toByteArray();
            return Base64.encodeToString(b, Base64.DEFAULT);

        }
        return null;
    }

    public static String getExtensionFromUri(Uri uri, ContentResolver cR) {
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    public static String getRealPathFromURI(Uri contentURI, ContentResolver mContentResolver) {
        String result;
        Cursor cursor = mContentResolver.query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }
    public interface TYPE {
        String PDF = "pdf";
        String IMAGE = "image";
        String VIDEO = "video";
        String UNKNOWN = "other";
        String PRESENTATION = "ppt";
        String DOCX = "docx";
        String EXCEL = "excel";
    }





}

