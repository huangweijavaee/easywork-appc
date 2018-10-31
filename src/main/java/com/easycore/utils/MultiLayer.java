package com.easycore.utils;

import java.io.File;

public class MultiLayer {
	private String entity_pack;
	private String ctrl_pack;
	private String srv_pack;
	private String dao_pack;
	private String rpc_pack;
	private boolean f_mvn;

	public MultiLayer(String entity_pack, String ctrl_pack, String srv_pack, String dao_pack, String rpc_pack,
			boolean f_mvn) {
		// 构造传值
		this.entity_pack = entity_pack;
		this.ctrl_pack = ctrl_pack;
		this.srv_pack = srv_pack;
		this.dao_pack = dao_pack;
		this.rpc_pack = rpc_pack;
		this.f_mvn = f_mvn;
		// 根据entity生成ctrl/srv/dao/rpc
		makedirs();
		genLayers();
	}

	// 生成各层目录
	private void makedirs() {
		String partx = f_mvn ? "main/java/" : "";
		// 生成controller层目录
		String ctrl_path = MyFileUtils.getCurrentSrcPath() + partx + ctrl_pack.replace(".", "\\") + "\\";
		File ctrl_dir = new File(ctrl_path);
		ctrl_dir.mkdirs();
		// 生成service层目录
		String service_path = MyFileUtils.getCurrentSrcPath() + partx + srv_pack.replace(".", "\\") + "\\";
		File service_dir = new File(service_path);
		service_dir.mkdirs();
		// 生成dao层目录
		String dao_path = MyFileUtils.getCurrentSrcPath() + partx + dao_pack.replace(".", "\\") + "\\";
		File dao_dir = new File(dao_path);
		dao_dir.mkdirs();
		// 生成rpc层目录
		String rpc_path = MyFileUtils.getCurrentSrcPath() + partx + rpc_pack.replace(".", "\\") + "\\";
		File rpc_dir = new File(rpc_path);
		rpc_dir.mkdirs();
	}

	// 扫描entity生成controller/service/dao
	private void genLayers() {
		// 获取持久层路径
		String partx = f_mvn ? "main/java/" : "";
		String entity_path = MyFileUtils.getCurrentSrcPath() + partx + entity_pack.replace(".", "\\") + "\\";
		File entity_dir = new File(entity_path);
		File[] files = entity_dir.listFiles();
		// 生成HomeController
		genDemoCtrl();
		// 遍历生成controller/service/dao
		for (File file : files) {
			String name = file.getName().trim();
			int lastdot = name.lastIndexOf(".");
			String entity = name.substring(0, lastdot);
			// 生成代码
			genCtrl(entity);
			genService(entity);
			genDao(entity);
		}
		// 生成rpc
		genRpc();
	}

	// 生成HomeController代码
	private void genDemoCtrl() {
		String partx = f_mvn ? "main/java/" : "";
		String ctrl_path = MyFileUtils.getCurrentSrcPath() + partx + ctrl_pack.replace(".", "\\") + "\\";
		StringBuilder sb = new StringBuilder("package " + ctrl_pack + ";\r\n\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		sb.append("import org.springframework.stereotype.Controller;\r\n");
		sb.append("import org.springframework.web.bind.annotation.PathVariable;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
		sb.append("import com.easycore.utils.BaseController;\r\n\r\n");
		sb.append("@Controller\r\n");
		sb.append("public class HomeController extends BaseController {\r\n\r\n");
		sb.append("\t@RequestMapping(\"/\")\r\n");
		sb.append("\tpublic String " + "view() {\r\n");
		sb.append("\t\treturn \"demo/view\";\r\n");
		sb.append("\t}\r\n\r\n");
		sb.append("\t@RequestMapping(\"/openWork/{view}\")\r\n");
		sb.append("\tpublic String " + "openWork(@PathVariable(\"view\") String view) {\r\n");
		sb.append("\t\treturn \"demo/\" + view;\r\n");
		sb.append("\t}\r\n\r\n");
		sb.append("}");
		MyFileUtils.writeFile(ctrl_path, "HomeController.java", sb.toString(), 0);
	}

	// 根据entity生成controller代码
	private void genCtrl(String entity) {
		String partx = f_mvn ? "main/java/" : "";
		String ctrl_path = MyFileUtils.getCurrentSrcPath() + partx + ctrl_pack.replace(".", "\\") + "\\";
		String custName = entity_pack.replace(".entity", "").replace("com.", "").replace("easywork.", "").replace(".",
				"/");
		StringBuilder sb = new StringBuilder("package " + ctrl_pack + ";\r\n\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		sb.append("import org.springframework.stereotype.Controller;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n");
		sb.append("import com.easycore.utils.BaseController;\r\n");
		sb.append("import " + entity_pack + "." + entity + ";\r\n");
		sb.append("import " + srv_pack + "." + entity + "Service" + ";\r\n\r\n");
		sb.append("@Controller\r\n");
		sb.append("@RequestMapping(\"/" + custName + "/" + entity + "\")\r\n");
		sb.append("public class " + entity + "Controller extends BaseController {\r\n");
		sb.append("\t@Autowired\r\n");
		sb.append("\tprivate " + entity + "Service " + entity.toLowerCase() + "Service;\r\n\r\n");
		sb.append("}");
		MyFileUtils.writeFile(ctrl_path, entity + "Controller.java", sb.toString(), 0);
	}

	// 根据entity生成service代码
	private void genService(String entity) {
		String partx = f_mvn ? "main/java/" : "";
		String service_path = MyFileUtils.getCurrentSrcPath() + partx + srv_pack.replace(".", "\\") + "\\";
		StringBuilder sb = new StringBuilder("package " + srv_pack + ";\r\n\r\n");
		sb.append("import org.springframework.beans.factory.annotation.Autowired;\r\n");
		sb.append("import org.springframework.stereotype.Service;\r\n");
		sb.append("import org.springframework.transaction.annotation.Transactional;\r\n");
		sb.append("import " + entity_pack + "." + entity + ";\r\n");
		sb.append("import " + dao_pack + "." + entity + "Repository" + ";\r\n\r\n");
		sb.append("@Transactional\r\n");
		sb.append("@Service\r\n");
		sb.append("public class " + entity + "Service {\r\n");
		sb.append("\t@Autowired\r\n");
		sb.append("\tprivate " + entity + "Repository " + entity.toLowerCase() + "Repository;\r\n\r\n");
		sb.append("\tpublic " + entity + "Repository " + "get" + entity + "Repository() {\r\n");
		sb.append("\t\treturn " + entity.toLowerCase() + "Repository;\r\n");
		sb.append("\t}\r\n\r\n");
		sb.append("}");
		MyFileUtils.writeFile(service_path, entity + "Service.java", sb.toString(), 0);
	}

	// 根据entity生成dao代码
	private void genDao(String entity) {
		String partx = f_mvn ? "main/java/" : "";
		String dao_path = MyFileUtils.getCurrentSrcPath() + partx + dao_pack.replace(".", "\\") + "\\";
		StringBuilder sb = new StringBuilder("package " + dao_pack + ";\r\n\r\n");
		sb.append("import java.io.Serializable;\r\n");
		sb.append("import org.springframework.data.jpa.repository.JpaRepository;\r\n");
		sb.append("import " + entity_pack + "." + entity + ";\r\n");
		sb.append("import " + dao_pack + "." + entity + "Repository" + ";\r\n\r\n");
		sb.append(
				"public interface " + entity + "Repository extends JpaRepository<" + entity + ", Serializable> {\r\n");
		sb.append("}");
		MyFileUtils.writeFile(dao_path, entity + "Repository.java", sb.toString(), 0);
	}

	// 生成rpc代码
	private void genRpc() {
		String partx = f_mvn ? "main/java/" : "";
		String rpc_path = MyFileUtils.getCurrentSrcPath() + partx + rpc_pack.replace(".", "\\") + "\\";
		StringBuilder sb = new StringBuilder("package " + rpc_pack + ";\r\n\r\n");
		sb.append("import org.springframework.cloud.netflix.feign.FeignClient;\r\n");
		sb.append("import org.springframework.web.bind.annotation.RequestMapping;\r\n\r\n");
		sb.append("@FeignClient(\"mystery-app-x\")\r\n");
		sb.append("public interface DemoService {\r\n");
		sb.append("\t@RequestMapping(\"/\")\r\n");
		sb.append("\tpublic String methodA();\r\n");
		sb.append("}");
		MyFileUtils.writeFile(rpc_path, "DemoService.java", sb.toString(), 0);
	}
}