package com.umeng.share;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.media.QQShareContent;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;
import com.umeng.socialize.weixin.media.CircleShareContent;
import com.umeng.socialize.weixin.media.WeiXinShareContent;

/**
 * Created by zf on 2015/4/23.
 */
public class Main extends Activity {

    Button button;

    // 首先在您的Activity中添加如下成员变量
    final UMSocialService mController = UMServiceFactory.getUMSocialService("com.umeng.share");

    private String title = "分享有礼，邀你参与";
    private String content = "山东福彩APP是山东省福彩中心授权发行的手机投注产品。";
    private String url = "http://app.zgxmcp.com:10000/agent/sdfc";
    private String imgUrl = "https://mmbiz.qlogo.cn/mmbiz/NRcqxSRAnDGMMfA6r5fBd6iboSspLwneXM1BibcsWQELm1aYqxibDZKa03fOtfmRo1CTM5RL3gZ0FOSn1ckUic0OGg/0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);


        setShareCfg();

        addQQ2Share();
        setQQContent();

        addWX2Share();
        setWXContent();

        button = (Button) this.findViewById(R.id.btn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mController.openShare(Main.this, false);
            }
        });

    }

    private void setQQContent() {
        QQShareContent qqShareContent = new QQShareContent();
//设置分享文字
        qqShareContent.setShareContent(content);
//设置分享title
        qqShareContent.setTitle(title);
//设置分享图片
        qqShareContent.setShareImage(new UMImage(Main.this, imgUrl));
//设置点击分享内容的跳转链接
        qqShareContent.setTargetUrl(url);
        mController.setShareMedia(qqShareContent);
    }


    private void addQQ2Share() {
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(Main.this, "1103881747",
                "gCZ4dZXtG5JMtBOe");
        qqSsoHandler.addToSocialSDK();
    }

    private void addWX2Share() {
        String appID = "wx20c8c11f73f30dec";
        String appSecret = "7cbdcdd720401a3c387e28a930538946";
// 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(Main.this, appID, appSecret);
        wxHandler.addToSocialSDK();
// 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(Main.this, appID, appSecret);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }

    private void setWXContent() {

        //设置微信好友分享内容
        WeiXinShareContent weixinContent = new WeiXinShareContent();
//设置分享文字
        weixinContent.setShareContent(content);
//设置title
        weixinContent.setTitle(title);
//设置分享内容跳转URL
        weixinContent.setTargetUrl(url);
//设置分享图片
        weixinContent.setShareImage(new UMImage(Main.this, imgUrl));
        mController.setShareMedia(weixinContent);


        //设置微信朋友圈分享内容
        CircleShareContent circleMedia = new CircleShareContent();
        circleMedia.setShareContent(content);
//设置朋友圈title
        circleMedia.setTitle(title);
        circleMedia.setShareImage(new UMImage(Main.this, imgUrl));
        circleMedia.setTargetUrl(url);
        mController.setShareMedia(circleMedia);
    }

    private void setShareCfg() {

        com.umeng.socialize.utils.Log.LOG = true;
        // 设置分享内容
//        mController.setShareContent("山东福彩APP是山东省福彩中心授权发行的手机投注产品。");
// 设置分享图片, 参数2为图片的url地址
//        mController.setShareMedia(new UMImage(Main.this,
//                "https://mmbiz.qlogo.cn/mmbiz/NRcqxSRAnDGMMfA6r5fBd6iboSspLwneXM1BibcsWQELm1aYqxibDZKa03fOtfmRo1CTM5RL3gZ0FOSn1ckUic0OGg/0"));


        mController.getConfig().removePlatform(SHARE_MEDIA.QZONE, SHARE_MEDIA.TENCENT, SHARE_MEDIA.SINA);

        mController.getConfig().setPlatformOrder(SHARE_MEDIA.WEIXIN,
                SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QQ);
    }
}
