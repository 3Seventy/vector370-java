package com.t70.vector.client.rest;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.WriterInterceptor;
import javax.ws.rs.ext.WriterInterceptorContext;
import javax.annotation.Priority;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.internal.util.collection.StringIgnoreCaseKeyComparator;
import org.glassfish.jersey.message.MessageUtils;

@PreMatching
@Priority(Integer.MIN_VALUE)
class RequestLoggingFilter implements ClientRequestFilter, ClientResponseFilter, WriterInterceptor
{
    private static final Logger s_logger = LogManager.getLogger(RequestLoggingFilter.class.getName());

    private static final String NOTIFICATION_PREFIX = "* ";
    private static final String REQUEST_PREFIX = "> ";
    private static final String RESPONSE_PREFIX = "< ";
    private static final String ENTITY_LOGGER_PROPERTY = RequestLoggingFilter.class.getName() + ".entityLogger";

    private static final Comparator<Map.Entry<String, List<String>>> COMPARATOR = new Comparator<Map.Entry<String, List<String>>>()
    {
        @Override
        public int compare(final Map.Entry<String, List<String>> o1, final Map.Entry<String, List<String>> o2)
        {
            return StringIgnoreCaseKeyComparator.SINGLETON.compare(o1.getKey(), o2.getKey());
        }
    };

    private static final int DEFAULT_MAX_ENTITY_SIZE = 8 * 1024;

    private final AtomicLong _id = new AtomicLong(0);

    private final boolean printEntity;

    private final int maxEntitySize;

    public RequestLoggingFilter()
    {
        this.printEntity = false;
        this.maxEntitySize = DEFAULT_MAX_ENTITY_SIZE;
    }

    private StringBuilder prefixId(final StringBuilder b, final long id)
    {
        b.append(Long.toString(id)).append(" ");
        return b;
    }

    private void printRequestLine(final StringBuilder b, final String note, final long id, final String method,
        final String uri)
    {
        prefixId(b, id).append(NOTIFICATION_PREFIX).append(note).append(" on thread ")
            .append(Thread.currentThread().getName()).append("\n");
        prefixId(b, id).append(REQUEST_PREFIX).append(method).append(" ").append(uri).append("\n");
    }

    private void printResponseLine(final StringBuilder b, final String note, final long id, final int status)
    {
        prefixId(b, id).append(NOTIFICATION_PREFIX).append(note).append(" on thread ")
            .append(Thread.currentThread().getName()).append("\n");
        prefixId(b, id).append(RESPONSE_PREFIX).append(Integer.toString(status)).append("\n");
    }

    private void printPrefixedHeaders(final StringBuilder b, final long id, final String prefix,
        final MultivaluedMap<String, String> headers)
    {
        for (final Map.Entry<String, List<String>> headerEntry : getSortedHeaders(headers.entrySet()))
        {
            final List<?> val = headerEntry.getValue();
            final String header = headerEntry.getKey();

            if (val.size() == 1)
            {
                prefixId(b, id).append(prefix).append(header).append(": ").append(val.get(0)).append("\n");
            }
            else
            {
                final StringBuilder sb = new StringBuilder();
                boolean add = false;

                for (final Object s : val)
                {
                    if (add)
                    {
                        sb.append(',');
                    }

                    add = true;
                    sb.append(s);
                }

                prefixId(b, id).append(prefix).append(header).append(": ").append(sb.toString()).append("\n");
            }
        }
    }

    private Set<Map.Entry<String, List<String>>> getSortedHeaders(final Set<Map.Entry<String, List<String>>> headers)
    {
        final TreeSet<Map.Entry<String, List<String>>> sortedHeaders = new TreeSet<Map.Entry<String, List<String>>>(
            COMPARATOR);
        sortedHeaders.addAll(headers);
        return sortedHeaders;
    }

    private InputStream logInboundEntity(final StringBuilder b, InputStream stream, final Charset charset)
        throws IOException
    {
        if (!stream.markSupported())
        {
            stream = new BufferedInputStream(stream);
        }

        stream.mark(maxEntitySize + 1);
        final byte[] entity = new byte[maxEntitySize + 1];
        final int entitySize = stream.read(entity);
        b.append(new String(entity, 0, Math.min(entitySize, maxEntitySize), charset));

        if (entitySize > maxEntitySize)
        {
            b.append("...more...");
        }

        b.append('\n');
        stream.reset();

        return stream;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.ws.rs.client.ClientRequestFilter#filter(javax.ws.rs.client. ClientRequestContext)
     */
    @Override
    public void filter(final ClientRequestContext context) throws IOException
    {
        final long id = this._id.incrementAndGet();
        final StringBuilder b = new StringBuilder();

        String method = context.getMethod();
        String url = context.getUri().toString();

        printRequestLine(b, "Sending client request", id, method, url);
        printPrefixedHeaders(b, id, REQUEST_PREFIX, context.getStringHeaders());

        if (printEntity && context.hasEntity())
        {
            final OutputStream stream = new LoggingStream(b, context.getEntityStream());
            context.setEntityStream(stream);
            context.setProperty(ENTITY_LOGGER_PROPERTY, stream);
            // not calling log(b) here - it will be called by the intercepter
        }
        else
        {
            s_logger.debug(b);
        }
    }

    @Override
    public void filter(final ClientRequestContext requestContext, final ClientResponseContext responseContext)
        throws IOException
    {
        final long id = this._id.incrementAndGet();
        final StringBuilder b = new StringBuilder();

        printResponseLine(b, "Client response received", id, responseContext.getStatus());
        printPrefixedHeaders(b, id, RESPONSE_PREFIX, responseContext.getHeaders());

        if (printEntity && responseContext.hasEntity())
        {
            responseContext.setEntityStream(logInboundEntity(
                b,
                responseContext.getEntityStream(),
                MessageUtils.getCharset(responseContext.getMediaType())));
        }

        s_logger.debug(b);
    }

    @Override
    public void aroundWriteTo(final WriterInterceptorContext writerInterceptorContext)
        throws IOException,
        WebApplicationException
    {
        final LoggingStream stream = (LoggingStream)writerInterceptorContext.getProperty(ENTITY_LOGGER_PROPERTY);
        writerInterceptorContext.proceed();
        if (stream != null)
        {
            MediaType mediaType = writerInterceptorContext.getMediaType();
            Charset charset = MessageUtils.getCharset(mediaType);

            StringBuilder b = stream.getStringBuilder(charset);

            s_logger.debug(b);
        }
    }

    private class LoggingStream extends OutputStream
    {
        private final StringBuilder b;
        private final OutputStream inner;
        private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

        LoggingStream(final StringBuilder b, final OutputStream inner)
        {
            this.b = b;
            this.inner = inner;
        }

        StringBuilder getStringBuilder(Charset charset)
        {
            // write entity to the builder
            final byte[] entity = baos.toByteArray();

            b.append(new String(entity, 0, Math.min(entity.length, maxEntitySize), charset));

            if (entity.length > maxEntitySize)
            {
                b.append("...more...");
            }

            b.append('\n');

            return b;
        }

        @Override
        public void write(final int i) throws IOException
        {
            if (baos.size() <= maxEntitySize)
            {
                baos.write(i);
            }

            inner.write(i);
        }
    }
}
