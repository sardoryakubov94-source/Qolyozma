package uz.qolyozma.app;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class MainActivity extends Activity {
    private WebView web;
    private volatile ByteArrayOutputStream buf;
    private volatile String fname = "qolyozma.pdf";
    private static final int REQ_SAVE = 7;

    @Override
    protected void onCreate(Bundle b) {
        super.onCreate(b);
        getWindow().setStatusBarColor(Color.parseColor("#0a0a0d"));
        getWindow().setNavigationBarColor(Color.parseColor("#0a0a0d"));
        web = new WebView(this);
        web.setBackgroundColor(Color.parseColor("#0a0a0d"));
        setContentView(web);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);
        s.setAllowFileAccess(true);
        web.addJavascriptInterface(new Bridge(), "QAndroid");
        web.setWebChromeClient(new WebChromeClient());
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView v, String url) {
                if (url.startsWith("file:")) return false;
                try { startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url))); } catch (Exception e) { }
                return true;
            }
        });
        web.loadUrl("file:///android_asset/index.html");
    }

    private String mime(String n) {
        String l = n.toLowerCase();
        if (l.endsWith(".pdf")) return "application/pdf";
        if (l.endsWith(".png")) return "image/png";
        return "application/octet-stream";
    }

    private class Bridge {
        @JavascriptInterface
        public void begin(String name) { buf = new ByteArrayOutputStream(); fname = name; }

        @JavascriptInterface
        public void chunk(String b64) {
            try { byte[] d = Base64.decode(b64, Base64.DEFAULT); buf.write(d, 0, d.length); } catch (Exception e) { }
        }

        @JavascriptInterface
        public void finish() {
            runOnUiThread(new Runnable() {
                public void run() {
                    Intent i = new Intent(Intent.ACTION_CREATE_DOCUMENT);
                    i.addCategory(Intent.CATEGORY_OPENABLE);
                    i.setType(mime(fname));
                    i.putExtra(Intent.EXTRA_TITLE, fname);
                    startActivityForResult(i, REQ_SAVE);
                }
            });
        }
    }

    @Override
    protected void onActivityResult(int req, int res, Intent data) {
        super.onActivityResult(req, res, data);
        if (req != REQ_SAVE || buf == null) return;
        if (res == RESULT_OK && data != null && data.getData() != null) {
            try {
                OutputStream o = getContentResolver().openOutputStream(data.getData());
                o.write(buf.toByteArray());
                o.close();
                Toast.makeText(this, "Saqlandi \u2713", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, "Saqlab bo\u02bblmadi", Toast.LENGTH_LONG).show();
            }
        }
        buf = null;
    }

    @Override
    public void onBackPressed() {
        if (web != null && web.canGoBack()) web.goBack(); else super.onBackPressed();
    }
}
