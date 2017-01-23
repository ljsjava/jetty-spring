public class StartMain {
	
	public static void main(String[] args) {
		try {
			String path = "config/log4j.xml";
			String osName = System.getProperty("os.name");
			if(osName.contains("Windows")){
				path = StartMain.class.getClassLoader().getResource("config/log4j.xml").getPath();
			}
			
			System.out.println("os.name = "+osName+", path = "+path);
			DOMConfigurator.configureAndWatch(path);
			int port = 8091;
			Server server = new Server(port);
			
			WebAppContext webAppContext = new WebAppContext();
			webAppContext.setContextPath("/test");
			webAppContext.setResourceBase("/");
			
			ServletHolder holder = new ServletHolder(new DispatcherServlet());
	        holder.setInitParameter("contextConfigLocation", "classpath:config/propholder.xml");
	        webAppContext.addServlet(holder, "*.html");
	        
	        server.setHandler(webAppContext);
	        
	        server.start();
	        server.join();
	        
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
