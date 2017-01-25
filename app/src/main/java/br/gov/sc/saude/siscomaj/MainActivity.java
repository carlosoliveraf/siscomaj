package br.gov.sc.saude.siscomaj;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.view.KeyEvent;
import android.widget.Toast;
import android.support.design.widget.Snackbar;


import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private static final String TAG = "Main";
    final Activity activity = this;
    private int opt;
    private String ip;
    private boolean isPageLoadedComplete;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ip = "http://200.19.222.7/";
        webView = (WebView) findViewById(R.id.webView1);
        isPageLoadedComplete = false; //declare at class level

        //webView.setPadding(0, 0, 0, 0);
        //webView.setInitialScale(getScale());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.canGoBack();

        Timer myTimer = new Timer();
        //Start this timer when you create you task
        myTimer.schedule(new LoaderTask(), 9500); // 3000 is delay in millies


        webView.setWebViewClient(new WebViewClient() {

           @Override
           public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
               view.loadUrl(request.getUrl().toString());
                   return true;

              }

            @Override
            public void onLoadResource(WebView view, String url) {
                // Check to see if there is a progress dialog

                   // Toast.makeText(getApplicationContext(), "teste erro site", Toast.LENGTH_LONG).show();

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                isPageLoadedComplete = true;
                // Page is done loading;
                // hide the progress dialog and show the webview

                webView.setEnabled(true);

            }

            @Override
            public void onReceivedSslError (WebView view, SslErrorHandler handler, SslError error) {
                Toast.makeText(getApplicationContext(), "Erro de conexão, por favor verifique sua rede.", Toast.LENGTH_LONG).show();
                //final Snackbar snackBar = Snackbar.make(findViewById(R.id.activity_first), "Erro de conexão, por favor verifique sua rede!", Snackbar.LENGTH_LONG);
                //snackBar.show();
                Intent nav = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(nav);
                handler.proceed();
            }

            @Override
            public void onReceivedError(WebView view, int errorCod,String description, String failingUrl) {
                Toast.makeText(getApplicationContext(), "Erro de conexão, por favor verifique sua rede.", Toast.LENGTH_LONG).show();
                //final Snackbar snackBar = Snackbar.make(findViewById(R.id.activity_first), "Erro de conexão, por favor verifique sua rede!", Snackbar.LENGTH_LONG);
                //snackBar.show();
                Intent nav = new Intent(getApplicationContext(), FirstActivity.class);
                startActivity(nav);
            }



        });


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            opt = 1;
        } else {
            opt = extras.getInt("opt");
        }
        //webView.loadUrl("http://172.22.27.52/siscomaj/index.php");
        if(opt == 1) {
            webView.loadUrl(ip+"index.php/Especial:Autenticar-se");
        }else{
            webView.loadUrl(ip+"index.php/Especial:Redefinir_autentica%C3%A7%C3%A3o");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (webView.canGoBack()) {
                        webView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }

        }
        return super.onKeyDown(keyCode, event);
    }


    private class LoaderTask extends TimerTask {


        @Override
        public void run() {
            //System.out.println("Times Up");
            if(isPageLoadedComplete){
            }else{

                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Erro de conexão, por favor verifique sua rede.", Toast.LENGTH_LONG).show();
                        Intent nav = new Intent(getApplicationContext(), FirstActivity.class);
                        startActivity(nav);
                    }
                });
                //show error message as per you need.
            }
        }
    }
}
