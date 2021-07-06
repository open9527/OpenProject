package com.android.open9527.video.exo;

import androidx.annotation.Nullable;

import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.TransferListener;

/**
 * @author open_9527
 * Create at 2021/7/6
 **/
public final class ExoHttpDataSourceFactory extends HttpDataSource.BaseFactory {

    private final String userAgent;
    private final @Nullable
    TransferListener listener;
    private final int connectTimeoutMillis;
    private final int readTimeoutMillis;
    private final boolean allowCrossProtocolRedirects;

    /**
     Constructs a GSYExoHttpDataSourceFactory. Sets {@link
    ExoDefaultHttpDataSource#DEFAULT_CONNECT_TIMEOUT_MILLIS} as the connection timeout, {@link
    ExoDefaultHttpDataSource#DEFAULT_READ_TIMEOUT_MILLIS} as the read timeout and disables
     cross-protocol redirects.
     @param userAgent The User-Agent string that should be used.
     */
    public ExoHttpDataSourceFactory(String userAgent) {
        this(userAgent, null);
    }

    /**
     Constructs a GSYExoHttpDataSourceFactory. Sets {@link
    ExoDefaultHttpDataSource#DEFAULT_CONNECT_TIMEOUT_MILLIS} as the connection timeout, {@link
    ExoDefaultHttpDataSource#DEFAULT_READ_TIMEOUT_MILLIS} as the read timeout and disables
     cross-protocol redirects.
     @param userAgent The User-Agent string that should be used.
     @param listener  An optional listener.
     @see #ExoHttpDataSourceFactory(String, TransferListener, int, int, boolean)
     */
    public ExoHttpDataSourceFactory(String userAgent, @Nullable TransferListener listener) {
        this(userAgent, listener, ExoDefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                ExoDefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS, false);
    }

    /**
     @param userAgent                   The User-Agent string that should be used.
     @param connectTimeoutMillis        The connection timeout that should be used when requesting remote
     data, in milliseconds. A timeout of zero is interpreted as an infinite timeout.
     @param readTimeoutMillis           The read timeout that should be used when requesting remote data, in
     milliseconds. A timeout of zero is interpreted as an infinite timeout.
     @param allowCrossProtocolRedirects Whether cross-protocol redirects (i.e. redirects from HTTP
     to HTTPS and vice versa) are enabled.
     */
    public ExoHttpDataSourceFactory(
            String userAgent,
            int connectTimeoutMillis,
            int readTimeoutMillis,
            boolean allowCrossProtocolRedirects) {
        this(
                userAgent,
                /* listener= */ null,
                connectTimeoutMillis,
                readTimeoutMillis,
                allowCrossProtocolRedirects);
    }

    /**
     @param userAgent                   The User-Agent string that should be used.
     @param listener                    An optional listener.
     @param connectTimeoutMillis        The connection timeout that should be used when requesting remote
     data, in milliseconds. A timeout of zero is interpreted as an infinite timeout.
     @param readTimeoutMillis           The read timeout that should be used when requesting remote data, in
     milliseconds. A timeout of zero is interpreted as an infinite timeout.
     @param allowCrossProtocolRedirects Whether cross-protocol redirects (i.e. redirects from HTTP
     to HTTPS and vice versa) are enabled.
     */
    public ExoHttpDataSourceFactory(
            String userAgent,
            @Nullable TransferListener listener,
            int connectTimeoutMillis,
            int readTimeoutMillis,
            boolean allowCrossProtocolRedirects) {
        this.userAgent = userAgent;
        this.listener = listener;
        this.connectTimeoutMillis = connectTimeoutMillis;
        this.readTimeoutMillis = readTimeoutMillis;
        this.allowCrossProtocolRedirects = allowCrossProtocolRedirects;
    }

    @Override
    protected ExoDefaultHttpDataSource createDataSourceInternal(
            HttpDataSource.RequestProperties defaultRequestProperties) {
        ExoDefaultHttpDataSource dataSource =
                new ExoDefaultHttpDataSource(
                        userAgent,
                        connectTimeoutMillis,
                        readTimeoutMillis,
                        allowCrossProtocolRedirects,
                        defaultRequestProperties);
        if (listener != null) {
            dataSource.addTransferListener(listener);
        }
        return dataSource;
    }
}
