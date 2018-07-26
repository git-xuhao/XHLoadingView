
# 概述

在设计应用的时候，我们应该热爱极简主义，简单就是好的，对于很多用户来说，复杂的东西并不受欢迎。
我要实现的是根据不同的情况去显示不同的加载效果，随用随调，效果是借鉴于某一项目的效果，我认为有必要提取出来改善封装一下，供以后使用。情况大致分为：加载中、无网络、无数据、加载失败等，这些仅仅就需要一个View 就可以搞定啦！


# **效果图**：

![这里写图片描述](https://github.com/git-xuhao/XHLoadingView/raw/master/screenhot/test_loading.gif)

# Usage
```

        mLoadingView.withLoadEmptyText("≥﹏≤ , 啥也木有 !")
                    .withEmptyIcon(R.drawable.disk_file_no_data)
                    .withBtnEmptyEnnable(false)
                    .withErrorIco(R.drawable.ic_chat_empty)
                    .withLoadErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !")
                    .withBtnErrorText("臭狗屎!!!")
                    .withLoadNoNetworkText("你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走")
                    .withNoNetIcon(R.drawable.ic_chat_empty)
                    .withBtnNoNetText("网弄好了，重试")
                    .withLoadingIcon(R.drawable.loading_animation)
                    .withLoadingText("加载中...")
                    .withOnRetryListener(new XHLoadingView.OnRetryListener() {
                @Override
                public void onRetry() {
                    SnackbarUtil.show(mLoadingView,"已经在努力重试了",0);
                }
            }).build();
        
       .....

       if(mLoadState.contains(LOADING)){
            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.setState(LoadingState.STATE_LOADING);

        }else if(mLoadState.contains(LOADING_EMPTY)){
            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.setState(LoadingState.STATE_EMPTY);

        }else if(mLoadState.contains(LOADING_NONETWORK)){
            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.setState(LoadingState.STATE_NO_NET);
        }else if(mLoadState.contains(LOADING_ERROR)){
           mLoadingView.setVisibility(View.VISIBLE);
           mLoadingView.setState(LoadingState.STATE_ERROR);
       }
```


apk：[DownLoad][2]

Blog：[http://xuhaoblog.com/Android-custom-loadingview.html][3]




# License

    Copyright 2016 Xuhao
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


  [1]: http://img.blog.csdn.net/20160519152132230
  [2]: https://github.com/git-xuhao/XHLoadingView/blob/master/apk/app-debug.apk?raw=true
  [3]: http://xuhaoblog.com/Android-custom-loadingview.html
