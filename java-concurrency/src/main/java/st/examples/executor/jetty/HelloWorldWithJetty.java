package st.examples.executor.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class HelloWorldWithJetty extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {
        final ExecutorService executor = Executors.newCachedThreadPool();

        for (final char c : "Hello from Java!".toCharArray()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executor.submit(new Runnable() {
                public void run() {
                    try {
                        resp.getWriter().println(Thread.currentThread() + ":\t" + String.valueOf(c));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        }

        executor.shutdown();

        try {
            executor.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        Server server = new Server(8085);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        context.addServlet(new ServletHolder(new HelloWorldWithJetty()), "/*");
        server.start();
        server.join();
    }

//    public static void startTestServer() throws Exception {
//        ServletHandler servletHandler = new ServletHandler();
//        servletHandler.addServletWithMapping(TestServlet.class, "/*");
//
//        server = new Server(SOLR_SERVICE_PORT);
//        server.setHandler(servletHandler);
//        server.start();
//    }

}
