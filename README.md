[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-AndroidProgressLayout-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1175)

AndroidProgressLayout
=====================

<p>
<a target="_blank" href="https://raw.github.com/antonkrasov/AndroidProgressLayout/master/art/demo.gif"><img src="https://raw.github.com/antonkrasov/AndroidProgressLayout/master/art/demo.gif" align="left" hspace="40px"
height="500px" /></a>

If you tired from wrapping your Views with RelativeLayout to add ProgressBar then this
small view will help you. It extends from RelativeLayout, so you shouldn't add additional layouts
to use it, just think about it as a RelativeLayout with auto added ProgressBar.
</p>

  - [Features](#features)
  - [Getting Started](#getting-started)
  - [Including in your project](#including-in-your-project)
  - [Developed By](#developed-by)
  - [License](#license)

<br /><br /><br /><br /><br /><br /><br /><br /><br /><br />

### Features

  - show/hide progress bar
  - setup background to hide your views when progress bar is showing

### Getting started

    Add com.github.androidprogresslayout.ProgressLayout to your layout

```xml

    <com.github.androidprogresslayout.ProgressLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:progressLayout="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:id="@+id/progress_layout"
        progressLayout:progressBackground="#33B5E5"
        progressLayout:progress="false"
        >

        <WebView
            android:id="@+id/web_view"
            android:layout_width="match_parent"
            android:layout_height="match_parent" />

    </com.github.androidprogresslayout.ProgressLayout>

```

  Now you can use progressLayout.setProgress(true / false) to show or hide progress bar.


```java
    webView.setWebViewClient(new WebViewClient() {
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    progressLayout.showProgress();
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    progressLayout.showContent();
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    Log.d(TAG, "onReceivedError: " + errorCode + " : " + description + " : " + failingUrl);

                    super.onReceivedError(view, errorCode, description, failingUrl);
                    
                    progressLayout.showErrorText(description);
                }
            });
```

### Including in your project

```groovy
    allprojects {
        repositories {
            jcenter()
        }
    }

    dependencies {
        compile 'com.github.androidprogresslayout:library:1.2@aar'
    }
```

##Developed By

  Anton Krasov - <anton.krasov@gmail.com>

<a href="https://twitter.com/ntnkrsv"><img src="https://raw.githubusercontent.com/antonkrasov/AndroidSocialNetworks/master/other/sn_icons/twitter.png" width="100px" height="81px" /></a><br/>

## License

AndroidProgressLayout is made available under the [MIT license](http://opensource.org/licenses/MIT):

<pre>
The MIT License (MIT)

Copyright (c) 2014 Anton Krasov

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
</pre>
