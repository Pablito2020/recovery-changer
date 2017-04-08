package com.pablito.recoverychanger;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fr.castorflex.android.circularprogressbar.CircularProgressDrawable;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private final ExecuteAsRootBase RootUser = new ExecuteAsRootBase();

    private final int RECTYPE_STK_KK = 1;
    private final int RECTYPE_STK_LL = 2;
    private final int RECTYPE_TWRP_KK = 3;
    private final int RECTYPE_TWRP_LL = 4;
    private final int RECTYPE_PHL_KK = 5;
    private final int RECTYPE_PHL_LL = 6;

    private String getRecoveryName(int paramInt)
    {
        String str = "";

        if (paramInt == 1 || paramInt == 2) {
            str = getString(R.string.stock);
        }
        if (paramInt == 3 || paramInt == 4) {
            str = getString(R.string.twrp);
        }
        if (paramInt == 5 || paramInt == 6) {
            str = getString(R.string.philz);
        }
        return str;
    }

    private void disableButtons(){
        Button btn1 = (Button) findViewById(R.id.button);
        btn1.setEnabled(false);
        Button btn2 = (Button) findViewById(R.id.button2);
        btn2.setEnabled(false);
        Button btn3 = (Button) findViewById(R.id.button3);
        btn3.setEnabled(false);
        Button btn4 = (Button) findViewById(R.id.button4);
        btn4.setEnabled(false);
    }

    private void alertClose(String paramString1, String paramString2)
    {
        AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
        localAlertDialog.setTitle(paramString1);
        localAlertDialog.setMessage(paramString2);
        localAlertDialog.setButton(-1, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localAlertDialog.setButton(-2, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        localAlertDialog.show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView rootLabel = (TextView)findViewById(R.id.textView5);
        if (RootUser.canRunRootCommands()) {
            rootLabel.setTextColor(Color.parseColor("#aeea00"));
            rootLabel.append(getText(R.string.root_OK));
        }else{
            rootLabel.setTextColor(Color.parseColor("#ef5350"));
            rootLabel.append(getText(R.string.root_not_OK));
            disableButtons();
            alertClose(getString(R.string.root_not_OK_info1), getString(R.string.root_not_OK_info2));
        }
    }

    public void buttonOnClickStock(View v)
    {
        String paramString1 = getString(R.string.stock);
        String paramString2 = getString(R.string.stock_confirm);
        AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
        localAlertDialog.setTitle(paramString1);
        localAlertDialog.setMessage(paramString2);
        localAlertDialog.setButton(-1, getString(android.R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                ProgressDialog progress = new ProgressDialog(new ContextThemeWrapper(com.pablito.recoverychanger.MainActivity.this, R.style.dialog));
                progress.setIndeterminateDrawable(new CircularProgressDrawable
                        .Builder(com.pablito.recoverychanger.MainActivity.this)
                        .color(R.color.md_falcon_700)
                        .style(CircularProgressDrawable.STYLE_NORMAL)
                        .build());
                if (Build.VERSION.RELEASE.equals("4.4.2") || Build.VERSION.RELEASE.equals("4.4.4") || Build.VERSION.RELEASE.equals("5.1.1")) {
                    progress.setMessage(getText(R.string.Installing) + getRecoveryName(RECTYPE_STK_KK));
                    new copyRawToSD(progress, com.pablito.recoverychanger.MainActivity.this).execute(RECTYPE_STK_KK);
                } else {
                    progress.setMessage(getText(R.string.Installing) + getRecoveryName(RECTYPE_STK_LL));
                    new copyRawToSD(progress, com.pablito.recoverychanger.MainActivity.this).execute(RECTYPE_STK_LL);
                }
            }
        });
        localAlertDialog.setButton(-2, getString(android.R.string.cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt) {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localAlertDialog.show();
    }

    public void buttonOnClickTWRP(View v)
    {
        String paramString1 = getString(R.string.twrp);
        String paramString2 = getString(R.string.twrp_confirm);
        AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
        localAlertDialog.setTitle(paramString1);
        localAlertDialog.setMessage(paramString2);
        localAlertDialog.setButton(-1, getString(android.R.string.ok), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                ProgressDialog progress = new ProgressDialog(new ContextThemeWrapper(com.pablito.recoverychanger.MainActivity.this, R.style.dialog));
                progress.setIndeterminateDrawable(new CircularProgressDrawable
                        .Builder(com.pablito.recoverychanger.MainActivity.this)
                        .color(R.color.md_falcon_700)
                        .style(CircularProgressDrawable.STYLE_NORMAL)
                        .build());
                if (Build.VERSION.RELEASE.equals("4.4.2") || Build.VERSION.RELEASE.equals("4.4.4") || Build.VERSION.RELEASE.equals("5.1.1")){
                    progress.setMessage(getText(R.string.Installing) + getRecoveryName(RECTYPE_TWRP_KK));
                    new copyRawToSD(progress, com.pablito.recoverychanger.MainActivity.this).execute(RECTYPE_TWRP_KK);
                }
                else{
                    progress.setMessage(getText(R.string.Installing) + getRecoveryName(RECTYPE_TWRP_LL));
                    new copyRawToSD(progress, com.pablito.recoverychanger.MainActivity.this).execute(RECTYPE_TWRP_LL);
                }
            }
        });
        localAlertDialog.setButton(-2, getString(android.R.string.cancel), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localAlertDialog.show();
    }

    public void buttonOnClickPhillz(View v)
    {
        String paramString1 = getString(R.string.philz);
        String paramString2 = getString(R.string.philz_confirm);
        AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
        localAlertDialog.setTitle(paramString1);
        localAlertDialog.setMessage(paramString2);
        localAlertDialog.setButton(-1, getString(android.R.string.ok), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                ProgressDialog progress = new ProgressDialog(new ContextThemeWrapper(com.pablito.recoverychanger.MainActivity.this, R.style.dialog));
                progress.setIndeterminateDrawable(new CircularProgressDrawable
                        .Builder(com.pablito.recoverychanger.MainActivity.this)
                        .color(R.color.md_falcon_700)
                        .style(CircularProgressDrawable.STYLE_NORMAL)
                        .build());
                if (Build.VERSION.RELEASE.equals("4.4.2") || Build.VERSION.RELEASE.equals("4.4.4") || Build.VERSION.RELEASE.equals("5.1.1")){
                    progress.setMessage(getText(R.string.Installing) + getRecoveryName(RECTYPE_PHL_KK));
                    new copyRawToSD(progress, com.pablito.recoverychanger.MainActivity.this).execute(RECTYPE_PHL_KK);
                }
                else{
                    progress.setMessage(getText(R.string.Installing) + getRecoveryName(RECTYPE_PHL_LL));
                    new copyRawToSD(progress, com.pablito.recoverychanger.MainActivity.this).execute(RECTYPE_PHL_LL);
                }
            }
        });
        localAlertDialog.setButton(-2, getString(android.R.string.cancel), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localAlertDialog.show();
    }

    private void showRecoveryMSG(){
        String paramString1 = getString(R.string.recovery);
        String paramString2 = getString(R.string.recovery_confirm);
        AlertDialog localAlertDialog = new AlertDialog.Builder(this).create();
        localAlertDialog.setTitle(paramString1);
        localAlertDialog.setMessage(paramString2);
        localAlertDialog.setButton(-1, getString(android.R.string.ok), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                ArrayList localArrayList = new ArrayList();
                localArrayList.add("reboot recovery");
                localArrayList.add("busybox reboot recovery");
                localArrayList.add("toolbox reboot recovery");
                RootUser.execute(localArrayList);
            }
        });
        localAlertDialog.setButton(-2, getString(android.R.string.cancel), new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                paramAnonymousDialogInterface.dismiss();
            }
        });
        localAlertDialog.show();
    }

    public void buttonOnClickReboot(View v)
    {
        showRecoveryMSG();
    }

}