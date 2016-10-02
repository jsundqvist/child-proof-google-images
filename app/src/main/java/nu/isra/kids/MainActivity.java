package nu.isra.kids;

import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.webkit.*;

public class MainActivity extends Activity 
{
	WebView myWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

		myWebView = (WebView) findViewById(R.id.webview);
		myWebView.setWebViewClient(new MyWebViewClient());
		WebSettings webSettings = myWebView.getSettings();
		webSettings.setJavaScriptEnabled(true);
		myWebView.loadUrl("https://www.google.com/imghp");
    }

	private class MyWebViewClient extends WebViewClient
	{
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url)
		{
			if (Uri.parse(url).getHost().contains("google."))
				return false;
			return true;
		}
		@Override
		public void onPageFinished(WebView view, String url) {
			super.onPageFinished(view, url);
			hide("div._RAf");
			hide("div._cy");
		}
		
		private void hide(String selector) {
			myWebView.evaluateJavascript("document.querySelector('"+selector+"').style.display='none';", null);
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		WebBackForwardList list = myWebView.copyBackForwardList();
		if (list.getCurrentIndex() > 1)
			myWebView.goBack();
		return true;
	}
}
