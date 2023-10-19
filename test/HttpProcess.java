package test;

import com.sun.net.httpserver.*;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.*;


public class HttpProcess {

    static final String REQUEST_METHOD_GET="GET",REQUEST_METHOD_POST="POST",REQUEST_METHOD_PUT="PUT",REQUEST_METHOD_DELETE="DELETE",REQUEST_METHOD_OPTIONS="OPTIONS";
    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            if (REQUEST_METHOD_GET.equals(t.getRequestMethod())) {
                String np="This is the response from the server for the request of:\n"+REQUEST_METHOD_GET+"\nby "+t.getRequestBody().toString()+"\n";
                t.sendResponseHeaders(200, np.length());
                t.getResponseBody().write(np.getBytes());
//                doGet(t);
            } else if (REQUEST_METHOD_POST.equals(t.getRequestMethod())) {
                String nap="Post request received: \n";
                String callbackBody = IOUtils.toString(t.getRequestBody(), (String) null);
                if(callbackBody.length()>0)
                {
                    JSONObject object = new JSONObject((callbackBody));
                    if(object.has("new user"))
                    {
                        String name=object.getString("new user");
                        String email=object.getString("email");
                        String password=object.getString("password");
                        nap+="New User: "+name+"\nEmail: "+email+"\n";
                        t.sendResponseHeaders(200, nap.length());
                        t.getResponseBody().write(nap.getBytes());
                        // write user data to database
                    }
                    if(object.has("new product"))
                    {
                        // write product data to database
                    }
                    if(object.has("like product"))
                    {
                        // write like product data to database
                    }
                    if(object.has("delete product"))
                    {

                    }
                }
                else
                {
                    String np="Empty post request\n"+t.getRequestBody().toString()+"\n";
                    t.sendResponseHeaders(200, np.length());
                    t.getResponseBody().write(np.getBytes());
                }
//                doPost(t);
            } else if (REQUEST_METHOD_PUT.equals(t.getRequestMethod())) {
//                doPut(t);
            } else if (REQUEST_METHOD_DELETE.equals(t.getRequestMethod())) {
//                doDelete(t);
            } else if (REQUEST_METHOD_OPTIONS.equals(t.getRequestMethod())) {
//                doOptions(t);
            } else {
                String np="Unsupported request\n"+t.getRequestBody().toString()+"\n";
                t.sendResponseHeaders(200, np.length());
                t.getResponseBody().write(np.getBytes());
            }
            t.getResponseBody().close();
            t.getRequestBody().close();
        }
    }

}
