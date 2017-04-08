package com.pablito.recoverychanger;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by R9280 on 30/12/2015.
 * Altered by eltifo on 20/01/2016.
 * And by Pablito2020 on 07/04/2017
 */
public class copyRawToSD extends AsyncTask<Integer, Void, Void>{
    ProgressDialog progress;
    ExecuteAsRootBase RootUser = new ExecuteAsRootBase();
    MainActivity act;
    public copyRawToSD(ProgressDialog progress, MainActivity act) {
        this.progress = progress;
        this.act = act;
    }

    public String getRecoveryFile(int rectype){
        String str = "";
        switch (rectype){
            case 1:
                str = "stockkk.img";
                break;
            case 2:
                str = "stockll.img";
                break;
            case 3:
                str = "twrpkk.img";
                break;
            case 4:
                str = "twrpll.img";
                break;
            case 5:
                str = "philzkk.img";
                break;
            case 6:
                str = "philzll.img";
        }
        return str;
    }

    @Override
    protected void onPreExecute() {

        //set message of the dialog
        //show dialog
        progress.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Integer... params) {
        try {
            Integer identifier = params[0];
            String str = "res/raw/";
            String file = str.concat(getRecoveryFile(identifier));
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(file);
            FileOutputStream out = new FileOutputStream("/storage/emulated/0/tmprec.img");
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
            ArrayList localArrayList = new ArrayList();
            localArrayList.add("dd if=/storage/emulated/0/tmprec.img of=/dev/recovery");
            RootUser.execute(localArrayList); //Need root permissions
        }
        catch (IOException e){
            e.getMessage();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        //Delete temprec.img
        File file = new File("/storage/emulated/0/tmprec.img");
        progress.dismiss();
        Toast.makeText(act, act.getString(R.string.toast), Toast.LENGTH_SHORT).show();
        boolean deleted = file.delete();
        super.onPostExecute(result);
    }

}