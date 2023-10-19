package test;


import com.sun.net.httpserver.*;

import java.io.*;

public class Test {
    static final String REQUEST_METHOD_GET="GET",REQUEST_METHOD_POST="POST",REQUEST_METHOD_PUT="PUT",REQUEST_METHOD_DELETE="DELETE",REQUEST_METHOD_OPTIONS="OPTIONS";
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if (REQUEST_METHOD_GET.equals(t.getRequestMethod())) {
                String get = "Get HTTP Request";
                t.sendResponseHeaders(200, get.length());
                t.getResponseBody().write(get.getBytes());

//                doGet(t);
            } else if (REQUEST_METHOD_POST.equals(t.getRequestMethod())) {
//                doPost(t);
            } else if (REQUEST_METHOD_PUT.equals(t.getRequestMethod())) {
//                doPut(t);
            } else if (REQUEST_METHOD_DELETE.equals(t.getRequestMethod())) {
//                doDelete(t);
            } else if (REQUEST_METHOD_OPTIONS.equals(t.getRequestMethod())) {
//                doOptions(t);
            } else {
                System.out.println("rrr");
                String np="Unsupported request\n"+t.getRequestBody().toString();
                t.sendResponseHeaders(200, np.length());
                t.getResponseBody().write(np.getBytes());

            }
            t.getResponseBody().close();
            t.getRequestBody().close();
//            // Reject non-POST requests
//            if (!t.getRequestMethod().equals("POST")) {
//                String np="NON-POST request\n"+t.getRequestBody().toString();
//                t.sendResponseHeaders(405, np.length());
//                t.getResponseBody().write(np.getBytes());
//                t.getResponseBody().close();
//                return;
//            }
//            String response = "Response from server:\n";
//            InputStream is = t.getRequestBody();
//            OutputStream os = t.getResponseBody();
//            String callbackBody = IOUtils.toString(is, (String) null);
//            if(callbackBody.length()==0)
//            {
//                String empty="Request is Empty!";
//                response+=empty;
//                t.sendResponseHeaders(200, response.length());
//                os.write(response.getBytes());
//            }
//            else
//            {
//                JSONObject requestBody = new JSONObject(callbackBody);
//                if(requestBody.has("error"))
//                {
//                    System.out.println(requestBody.get("error"));
//                    String error="Error received!";
//                    response+=error;
//                    t.sendResponseHeaders(200, response.length());
//                    os.write(error.getBytes());
//                }
//            }
//            is.close();
//            os.close();
        }
    }
}
