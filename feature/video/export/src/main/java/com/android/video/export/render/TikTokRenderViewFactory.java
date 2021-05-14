package com.android.video.export.render;

import android.content.Context;

import com.android.open9527.video.common.render.IRenderView;
import com.android.open9527.video.common.render.RenderViewFactory;
import com.android.open9527.video.common.render.TextureRenderView;


public class TikTokRenderViewFactory extends RenderViewFactory {

    public static TikTokRenderViewFactory create() {
        return new TikTokRenderViewFactory();
    }

    @Override
    public IRenderView createRenderView(Context context) {
        return new TikTokRenderView(new TextureRenderView(context));
    }
}
