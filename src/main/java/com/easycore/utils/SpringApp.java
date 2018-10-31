package com.easycore.utils;

public class SpringApp {
	private String entity_pack;
	private boolean f_mvn;

	public SpringApp(String entity_pack, boolean f_mvn) {
		this.entity_pack = entity_pack;
		this.f_mvn = f_mvn;
		genApp();
	}

	private void genApp() {
		String app_pack = entity_pack.replace(".entity", "");
		String partx = f_mvn ? "main/java/" : "";
		String app_path = MyFileUtils.getCurrentSrcPath() + partx
				+ entity_pack.replace(".entity", "").replace(".", "\\") + "\\";
		StringBuilder sb = new StringBuilder("package " + app_pack + ";\r\n\r\n");
		sb.append("import org.springframework.boot.SpringApplication;\r\n");
		sb.append("import org.springframework.boot.autoconfigure.SpringBootApplication;\r\n");
		sb.append("import org.springframework.cloud.netflix.eureka.EnableEurekaClient;\r\n\r\n");
		sb.append("import org.springframework.cloud.netflix.feign.EnableFeignClients;\r\n\r\n");
		sb.append("@EnableFeignClients\r\n");
		sb.append("@EnableEurekaClient\r\n");
		sb.append("@SpringBootApplication\r\n");
		sb.append("public class Start {\r\n");
		sb.append("\tpublic static void main(String[] args) {\r\n");
		sb.append("\t\tSpringApplication.run(Start.class, args);\r\n");
		sb.append("\t}\r\n");
		sb.append("}");
		MyFileUtils.writeFile(app_path, "Start.java", sb.toString(), 0);
	}
}