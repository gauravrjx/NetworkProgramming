package chapter5;

import java.io.*;
import java.net.*;
import java.util.*;

public class SimpleResponseCache extends ResponseCache {

    private final Map<URI, CacheResponse> cacheMap = new HashMap<>();

    @Override
    public CacheResponse get(URI uri, String requestMethod, Map<String, List<String>> requestHeaders) throws IOException {
        System.out.println("Fetched from Respones Cache!");
        return cacheMap.get(uri);
    }

    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        return new SimpleCacheRequest(uri);
    }

    private class SimpleCacheRequest extends CacheRequest {
        private final ByteArrayOutputStream out = new ByteArrayOutputStream();
        private final URI uri;
        private boolean aborted = false;

        public SimpleCacheRequest(URI uri) {
            this.uri = uri;
        }

        @Override
        public OutputStream getBody() {
            return new FilterOutputStream(out) {
                @Override
                public void close() throws IOException {
                    if (!aborted) {
                        cacheMap.put(uri, new SimpleCacheResponse(((ByteArrayOutputStream) out).toByteArray()));
                    }
                    super.close();
                }
            };
        }

        @Override
        public void abort() {
            // Handle aborting the cache request
        }

        // @Override
        // public void close() throws IOException {
        //     cacheMap.put(uri, new SimpleCacheResponse(out.toByteArray()));
        // }
    }

    private class SimpleCacheResponse extends CacheResponse {
        private final byte[] data;

        public SimpleCacheResponse(byte[] data) {
            this.data = data;
        }

        @Override
        public Map<String, List<String>> getHeaders() {
            return Collections.emptyMap();
        }

        @Override
        public InputStream getBody() {
            return new ByteArrayInputStream(data);
        }
    }

    public static void main(String[] args) throws Exception {
        ResponseCache.setDefault(new SimpleResponseCache());

        // Example usage of URL with caching enabled
        URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
        URLConnection conn = url.openConnection();
        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();

        // Calling the API again to fetch the same data
        URLConnection cachedConn = url.openConnection();
        BufferedReader cachedReader = new BufferedReader(new InputStreamReader(cachedConn.getInputStream()));

        while ((line = cachedReader.readLine()) != null) {
            System.out.println(line);
        }
        cachedReader.close();
    }
}

