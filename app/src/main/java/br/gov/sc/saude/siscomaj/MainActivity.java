package br.gov.sc.saude.siscomaj;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.view.KeyEvent;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private static final String TAG = "Main";
    final Activity activity = this;
    private int opt;
    private String ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ip = "http://172.20.20.181/";
        webView = (WebView) findViewById(R.id.webView1);

        //webView.setPadding(0, 0, 0, 0);
        //webView.setInitialScale(getScale());
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {

           @Override
           public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
               view.loadUrl(request.getUrl().toString());
                   return true;
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

}
