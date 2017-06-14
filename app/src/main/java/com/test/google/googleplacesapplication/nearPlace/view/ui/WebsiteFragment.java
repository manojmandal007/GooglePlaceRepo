package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseFragment;

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_ADDRESS_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_NAME_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_PHONE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_POS_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_RATING_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_WEBSITE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_WEBSITE_URL_KEY;

/**
 * Created by manoj on 14/6/17.
 */

public class WebsiteFragment extends BaseFragment {

    private WebView mWebView;
    private String mUrl;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_website);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mWebView = (WebView) view.findViewById(R.id.web_view);
    }

    private void extractArguments() {
        mUrl = getArguments().getString(PLACE_WEBSITE_URL_KEY);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        extractArguments();
        mActionBarIconListener.setActionBarTitle(getString(R.string.place_website));
        loadWebView();
    }

    private void loadWebView() {
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setFocusable(false);
        mWebView.setFocusableInTouchMode(false);
        mWebView.setLongClickable(false);

        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showProgressBar();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                // hide progress bar here
                hideProgressBar();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    view.loadUrl(request.getUrl().getPath());
                }
                return true;
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        mWebView.loadUrl(mUrl);
    }

    private void clearWebView() {
        if (mWebView != null) {
            mWebView.stopLoading();
            mWebView.loadUrl("about:blank");
            mWebView.reload();
            mWebView.clearCache(true);
            mWebView = null;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clearWebView();
    }

}
