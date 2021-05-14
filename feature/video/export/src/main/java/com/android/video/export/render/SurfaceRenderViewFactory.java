package com.android.video.export.render;

import android.content.Context;

import com.android.open9527.video.common.render.IRenderView;
import com.android.open9527.video.common.render.RenderViewFactory;


public class SurfaceRenderViewFactory extends RenderViewFactory {

    public static SurfaceRenderViewFactory create() {
        return new SurfaceRenderViewFactory();
    }

    @Override
    public IRenderView createRenderView(Context context) {
        return new SurfaceRenderView(context);
    }
}
